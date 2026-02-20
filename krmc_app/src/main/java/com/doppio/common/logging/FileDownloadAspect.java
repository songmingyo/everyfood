package com.doppio.common.logging;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.MenuSession;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.service.AccessCommonService;


@Component("fileDownloadAspect")
@Aspect
public class FileDownloadAspect {


	@Autowired
	AccessCommonService accessCommonService;
	
	 @Value("#{config['File.Sys.Path']}")				// 웹루트 경로 (웹서비스용 파일업로드 패스)
		public String FileSysPath;
	
	 @Value("#{config['header.Authorization.url']}")
	 	public String conUrl;
	
	

	private static final Logger logger = LoggerFactory.getLogger(FileDownloadAspect.class);


	public void logBefore(JoinPoint joinPoint) {
		logger.debug("===============================================FileDownloadAspect logBefore()");
	}

	public void logAfter(JoinPoint joinPoint) {
		
		
		logger.debug("===============================================FileDownloadAspect logAfter()");
	}

	/**
	* 실행 전후
	* @param joinPoint
	* @return
	* @throws Throwable
	*/
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		logger.debug("=============================================== FileDownloadAspect logAround()");
		
		long currentTime 	= System.currentTimeMillis(); 
		
		 // 애플리케이션에서 Request 객체를 읽어옴
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String resIp =  request.getRemoteAddr(); 
        
        Object returnObj = joinPoint.proceed();  
      
        AttachFileVo attachFileVo = null;
        logger.debug("=============================================== ModelAndView Model  Find Object AttachFileVo ");
        if(returnObj instanceof ModelAndView)  { 
        	ModelAndView mav = (ModelAndView)returnObj;
        	Map<String, Object> modelMap = mav.getModel();
        	
        	attachFileVo = (AttachFileVo)modelMap.get("downloadFile");
        }

        if(attachFileVo == null) return returnObj;
        
        logger.debug("=============================================== Start FileDownload LOG INSERT  ");
        String executeTime = String.valueOf(System.currentTimeMillis()-currentTime); 
        
		/*사용자 로그인 정보 설정 ------------------------------------------------------------------*/
        String regId = "guest";
    	final Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
        	regId 	= ((CustomUser)auth.getPrincipal()).getMemberCd();
        }
       /*-------------------------------------------------------------------------------------*/

        /*실행프로그램 확인 -----------------------------------------------------------------------*/
        MenuSession acessMenu = (MenuSession)request.getAttribute("acessMenu");
        
        /*메뉴테이블에 존재하는 프로그램 실행 이고 상세로그 생성여부가 'Y' 일경우에만 DtlCommand에 값설정*/
        if(acessMenu != null && StringUtils.isNotEmpty(acessMenu.getWebPage()) && StringUtils.isNotEmpty(acessMenu.getMenuId()) ){
        	acessMenu.getMenuId();		// 실행메뉴아이디 
        	acessMenu.getWebPage();		// 접근 WEB PAGE URL 설정
        	acessMenu.getTitle();
        	
        	//실행프로그램명[실행프로그램 아이디]
        	attachFileVo.setDwldCmd(attachFileVo.getOrgFileNm()+" | "+acessMenu.getTitle()+"["+acessMenu.getMenuId()+"]");  
        }
        /*-------------------------------------------------------------------------------------*/
      
        /*로그정보 설정  -------------------------------------------------------------------------*/
        attachFileVo.setFileInfo(FileSysPath+attachFileVo.getFilePathNm()+attachFileVo.getSaveFileNm());	//파일전체 경로 
        attachFileVo.setExecuteTime(executeTime);								//FileDownLoadController 실행시간
        attachFileVo.setResIp(resIp);   										//접근아이피
        attachFileVo.setWorkId(regId);											//접근자 고유번호 
        /*-------------------------------------------------------------------------------------*/
        
        accessCommonService.insertFileDownloadHist(attachFileVo);		//다운로드 파일로그 INSERT
        
        logger.debug("=============================================== End FileDownload LOG INSERT  ");
        
		return returnObj;
	}
	
	
	
	public Object excellogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		
		logger.debug("=============================================== FileDownloadAspect excellogAround()");
		AttachFileVo attachFileVo = new AttachFileVo();
		
		long currentTime = System.currentTimeMillis();
		 // 애플리케이션에서 Request 객체를 읽어옴
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String resIp = request.getRemoteAddr();
		
		Object returnObj =  joinPoint.proceed();
		
		ExcelFileVo excelFileVo = null;
		
		
		logger.debug("=============================================== ModelAndView Model  Find Object ExcelFileVo ");
		
		if(returnObj instanceof ModelAndView) {
			ModelAndView mav = (ModelAndView) returnObj;
			Map<String, Object> modelMap = mav.getModel();
			
			excelFileVo = (ExcelFileVo)modelMap.get("excelFile");
		}
		if(excelFileVo == null) return returnObj;
		logger.debug("=============================================== Start FileDownload LOG INSERT  ");
		String executeTime = String.valueOf(System.currentTimeMillis()-currentTime);
		/*사용자 로그인 정보 설정 ------------------------------------------------------------------*/
		String regId = "guest";
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null  && !"anonymousUser".equals(auth.getPrincipal())) {
			regId = ((CustomUser)auth.getPrincipal()).getMemberCd();
			
		}
		/*-------------------------------------------------------------------------------------*/
		
		/*실행프로그램 확인 -----------------------------------------------------------------------*/
		MenuSession acessMenu = (MenuSession)request.getAttribute("acessMenu");
		
	    /*메뉴테이블에 존재하는 프로그램 실행 이고 상세로그 생성여부가 'Y' 일경우에만 DtlCommand에 값설정*/
		if(acessMenu != null && StringUtils.isNotEmpty(acessMenu.getWebPage()) && StringUtils.isNotEmpty(acessMenu.getMenuId())){
			acessMenu.getMenuId();  // 실행메뉴아이디 
			acessMenu.getWebPage(); // 접근 WEB PAGE URL 설정
			acessMenu.getTitle();
		
		//실행프로그램명[실행프로그램 아이디]
		attachFileVo.setDwldCmd(acessMenu.getTitleLocale()+"|"+acessMenu.getTitle()+"["+acessMenu.getMenuId()+"]"); 
		
		}
        /*-------------------------------------------------------------------------------------*/
	      
        /*로그정보 설정  -------------------------------------------------------------------------*/
		attachFileVo.setFileKindCd("EXCEL");
		attachFileVo.setFileInfo(conUrl + acessMenu.getWebPage());	//엑셀파일 다운로드 호출한 위치 
		attachFileVo.setExecuteTime(executeTime);					//FileDownLoadController 실행시간
		attachFileVo.setResIp(resIp);								//접근아이피
		attachFileVo.setWorkId(regId);								//접근자 고유번호
		 /*-------------------------------------------------------------------------------------*/
		
		 accessCommonService.insertFileDownloadHist(attachFileVo);	  //다운로드 파일로그 INSERT
		 
		 logger.debug("=============================================== End FileDownload LOG INSERT  ");
		
		return returnObj;
	}
	
}
