package com.doppio.common.logging;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.doppio.common.model.AccessLog;
import com.doppio.common.model.MenuSession;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.service.AccessCommonService;

import org.springframework.web.context.request.ServletRequestAttributes;


@Component("loggingAspect")
@Aspect
public class LoggingAspect {


	@Autowired
	AccessCommonService accessCommonService;

	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


	public void logBefore(JoinPoint joinPoint) {
		logger.debug("=============================================== LoggingAspect logBefore()");
	}

	public void logAfter(JoinPoint joinPoint) {
		logger.debug("=============================================== LoggingAspect logAfter()");
	}

	/**
	* 실행 전후
	* @param joinPoint
	* @return
	* @throws Throwable
	*/
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		logger.debug("=============================================== LoggingAspect logAround()");
		
		Object thisObj 		= joinPoint.getTarget();
		String className 	= thisObj.getClass().getName();
		long currentTime 	= System.currentTimeMillis();


		Map<String, ?> paramsMap  = null;
		StringBuffer paramsBuffer = new StringBuffer();
		 // 애플리케이션에서 Request 객체를 읽어옴
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        /*Request -----------------------------------------------------------------------------*/
		for (Object obj : joinPoint.getArgs()) {

			paramsMap = getParameterClassLoad(obj); // Parameter Class VO --> HashMap Conversion
			if(paramsMap == null) continue;

			for (Entry<String, ?> entry : paramsMap.entrySet()) {
			    if(paramsBuffer.length() > 0) paramsBuffer.append(", ");
			    paramsBuffer.append("{"+entry.getKey()+":");
			    paramsBuffer.append(entry.getValue()+"}");
			}
		}
		/* ------------------------------------------------------------------------------------*/

		AccessLog accessLogVo = new AccessLog();
		String requestUrl 	  = request.getRequestURI();
		String menuId         = "";
		//String menuId 		= (String)request.getSession().getAttribute("_codeSesson");
    	String regId          = "guest";

		if(logger.isDebugEnabled()){
			logger.debug("=================================================");
			logger.debug(">>>>>>>>> LOGGING START >>>>>>>>>>");
			logger.debug("[REQUEST URL]:" +requestUrl);
			logger.debug("[class]:" + className);
			logger.debug("[method]:" + joinPoint.getSignature().getName());
			logger.debug("[params]:" + paramsBuffer.toString());

	 	}

		Object returnObj = joinPoint.proceed();
		String executeTime = String.valueOf(System.currentTimeMillis()-currentTime);
		if(logger.isDebugEnabled()){
			logger.debug("[class]:" + className);
			logger.debug("[method]:" + joinPoint.getSignature().getName());
			logger.debug("[execute time]: {}ms", executeTime);
			logger.debug(">>>>>>>>>> LOGGING END >>>>>>>>>>");
			logger.debug("=================================================");
		}

		/*사용자 로그인 정보 설정 ------------------------------------------------------------------*/
    	final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
        	regId 	= ((CustomUser)auth.getPrincipal()).getMemberCd();
        }
       /*-------------------------------------------------------------------------------------*/
        /*실행프로그램 --------------------------------------------------------------------------*/
        MenuSession acessMenu = (MenuSession)request.getAttribute("acessMenu");


        /*메뉴테이블에 존재하는 프로그램 실행 이고 상세로그 생성여부가 'Y' 일경우에만 DtlCommand에 값설정*/
        if(acessMenu != null && StringUtils.isNotEmpty(acessMenu.getWebPage()) && StringUtils.isNotEmpty(acessMenu.getMenuId()) ){
        	String findUrl = acessMenu.getWebPage();
        	int idx = findUrl.lastIndexOf(".");
        	if(idx > 0) findUrl = findUrl.substring(0,idx);

        	if(requestUrl.contains(findUrl)){
        		accessLogVo.setMenuId(acessMenu.getMenuId());		// 실행메뉴아이디 설정
        		accessLogVo.setResPgmUrl(acessMenu.getWebPage());	// 접근 WEB PAGE URL 설정

        		if("Y".equals(acessMenu.getDtlLogYn())){
		        	 StringBuffer sbDtlCommand = new StringBuffer();

		    		sbDtlCommand.append("==LOGGING START\n");
		    		sbDtlCommand.append("[REQUEST URL :" +requestUrl+"]\n");
		    		sbDtlCommand.append("[CLASS :" + className+"]\n");
		    		sbDtlCommand.append("[METHOD :" + joinPoint.getSignature().getName()+"]\n");
		    		sbDtlCommand.append("[PARAMS :" + paramsBuffer.toString()+"]\n");
		    		sbDtlCommand.append("[EXECUTE TIME : {}ms"+ executeTime+"]\n");
		    		sbDtlCommand.append("LOGGING END==");

		    		accessLogVo.setDtlCommand(sbDtlCommand.toString());         // 추가세부정보(parameter정보상세)
		    	}
        	}
        }
        /*-------------------------------------------------------------------------------------*/


    	/* 접근로그 생성 ------------------------------------------------------------------------*/
    	accessLogVo.setRegId(regId);								// Default guest
    	accessLogVo.setResFullUrl(requestUrl);						// 접근 URL
    	accessLogVo.setResIp(request.getRemoteAddr());				// 접근 아이피
    	accessLogVo.setSessionId(request.getSession().getId());		// SESSION ID

    	accessLogVo.setExecuteTime(executeTime);					// 실행시간(ms)

    	accessCommonService.insertAccessLog(accessLogVo);			// 접근 로그 insert



		return returnObj;
	}

	/**
	 * Parameter Class [VO]
	 * @param Object [ParameterVO]
	 * @return Map<String, Map>
	 */
	private Map<String, ?> getParameterClassLoad(Object obj){

		if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) return null;

		Map<String, Object> map = null;
		try{
			Class<? extends Object> paramClass = obj.getClass();
			String className = paramClass.getName();
			//logger.debug("========["+className+"]=========================================");

			if(obj instanceof String || obj instanceof Integer) {
				if(obj != null) map.put("className", (String) obj);
			}else if(obj instanceof Map){
				if(obj != null)map = (HashMap)obj;
			}
			else{
				Field[] fields = paramClass.getDeclaredFields();
				map = new HashMap<>();
				String filedName   = "";
				String methodName  = "";
				String methodValue = null;
				for(Field field : fields){
					filedName = field.getName();
					methodName = "get"+StringUtils.capitalize(filedName);

					try {
						methodValue =  (String)paramClass.getDeclaredMethod(methodName).invoke(obj);

		            	if(methodValue != null) map.put(filedName, methodValue);
		            }catch(Exception ex){
		            	logger.error("getParameterClassLoad error : " +ex.getMessage());
		            }
				}
			}
		}catch(Exception ex){logger.debug(ex.getMessage());}
		return map;
	}




}
