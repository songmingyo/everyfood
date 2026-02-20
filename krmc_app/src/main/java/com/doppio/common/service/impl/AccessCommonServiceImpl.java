package com.doppio.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.interceptor.AuthenticInterceptor;
import com.doppio.common.model.AccessLog;
import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.MenuSession;
import com.doppio.common.model.PermissionResult;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.service.AccessCommonService;

/**
 * @author DADA
 * @Description : 접근 정보 관리 공통
 * @Class : AccessCommonServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      		수정자          		수정내용
 *  -------    		--------    		---------------------------
 *  2017.04.09  	DADA       			 최초 생성
 *  2017.09.03		DADA				 MenuSession 통합처리  
 * </pre>
 * @version : 
 */

@Service("accessCommonService")
public class AccessCommonServiceImpl implements AccessCommonService {
	
	
	@Autowired
	private AccessCommonMapper  accessCommonMapper;
	
	@Autowired
	CustomUserService customUserService;
	
	@Value("#{config['security.deviceAuth.cookiename']}")
	public String devAuthCookieName;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticInterceptor.class);
	
	
	/**
	 * 메뉴정보 조회
	 * @param memberCd
	 * @return List
	 */
	@Override
	public List<MenuSession> selectGrantMenuList(CustomUser CustomUser) {
		
		List<MenuSession> menuList = new ArrayList<MenuSession>();
		
		if(CustomUser != null && !"".equals(StringUtils.defaultString(CustomUser.getMemberCd()))) {
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("memberCd", CustomUser.getMemberCd());
			paramMap.put("sysCode", CustomUser.getSysCode());
			paramMap.put("excTypeCd", CustomUser.getExcTypeCd());		//실행구분(디바이스구분)
			
			menuList = accessCommonMapper.selectMenuListById(paramMap);
		}
		else {
			//Guest menu
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("excTypeCd",CustomUser.getExcTypeCd());		//실행구분(디바이스구분)
			
			menuList = accessCommonMapper.selectMenuListByGuest();
		}

		return menuList;
	}
	
	/**
	 * 요청url 정보를 참조하여 메뉴아이디를 조회 
	 * @param webPage
	 * @return String
	 */
	@Override
	public String selectMenuidValidation(String webPage) {
		return accessCommonMapper.selectMenuidValidation(webPage);
	}
	

	/**
	 * 해당 메뉴 실행 권한 조회 MenuSession is null at select database...
	 */
	public boolean selectFuncPermission(String memberCd, String url, MenuSession acessMenu)  {
		
		/* 로그인 사용자코드가 없으면 접근이불가능(session 이 null 이 아닌경우에만 체크함. 2. 요청 프로그램 아이디(url)이 없으면 오류임.*/
		if (StringUtils.isEmpty(memberCd) || StringUtils.isEmpty(url)) return false;

		String programId 	= url;
		String programSubId = "";
		String programEvent = "";
		
		/* [ INS, UPD, DEL, SEL, PRT ]
		 * /app/manager/MgrCode 까지가 프로그램 단위 아이디 (메뉴등록 여부 판단) 
		 * 접근 url를 통해 프로그램 아이디 셋팅 프로그램 아이디 체계 /app/manager/MgrCode_selList, /app/manager/MgrCode_ins ... 
		 */
		int inx = url.lastIndexOf("_");
		if (inx > 0) {
			programId 	 = url.substring(0, inx);
			programSubId = url.substring(programId.length());

			if(programSubId.length() > 3) {
				programEvent = programSubId.substring(1,4).toUpperCase();
			} 
		}
		
		/* Attribute("acessMenu") 참조하여 database 사용하지 않을수 있도록 함
		 * [acessMenu 을 강제로 null로 호출 사용 할 수있음.]
		 *  */
		if(acessMenu == null) {
			/* 접근프로그램, 사용자정보를 통해 권한 여부를 조회함.[TCM_MEMBER_AUTH,TCM_GROUP_AUTH] */
			PermissionResult paramVo = new MenuSession();
			paramVo.setMemberCd(memberCd);
			paramVo.setProgramId(programId);
			paramVo.setProgramEvent(programEvent);
			acessMenu = accessCommonMapper.selectFuncPermission(paramVo);
		} else {
			/*메뉴목록에 미등록 여부(F:등록 T:미등록) */
			if(acessMenu.getWebPage() != null && acessMenu.getWebPage().contains(programId)) acessMenu.setProgramNvl("F");				
			else acessMenu.setProgramNvl("T");
		}

		logger.debug("===========[programEvent ]================");
		logger.debug(" Event : " + programEvent);
		logger.debug(" Program : " + programId);
		logger.debug(" programSub : " + programSubId);
		logger.debug("-------------------------------------");
		logger.debug(" SEL : " + acessMenu.getReadAuth());
		logger.debug(" INS : " + acessMenu.getWriteAuth());
		logger.debug(" UPD : " + acessMenu.getEditAuth());
		logger.debug(" DEL : " + acessMenu.getDeleteAuth());
		logger.debug(" PRT : " + acessMenu.getPrintAuth());
		logger.debug(" NVL : " + acessMenu.getProgramNvl());
		logger.debug("===========[end programEvent]==============");

		/* 메뉴목록에 등록되지 않은 프로그램은 권한체크 제외 한다. T:프로그램 목록에 없음, F:프로그램 목록에 있음. */
		if ("T".equals(acessMenu.getProgramNvl())) {
			return true;
		} else {
			/* 메뉴목록에 등록된 프로그램이고 접근한 사용자가 모든권한에 메핑되지 않은경우 */
			if ("N".equals(acessMenu.getReadAuth()) && "N".equals(acessMenu.getWriteAuth()) && "N".equals(acessMenu.getEditAuth()) &&
				"N".equals(acessMenu.getDeleteAuth()) && "N".equals(acessMenu.getPrintAuth())) {
				return false;
			}
		}

		/* 접근권한 있으며, programEvent 값이 없을경우(init page...등 ) */
		if (StringUtils.isEmpty(programEvent)) return true;

		/* 접근권한이 있는 프로그램이며, 실행이벤트 값이 있을경우 
		 * 이벤트 권한 체크 [ INS, UPD, DEL, SEL, PRT ] */
		if("INS".equals(programEvent) && "Y".equals(acessMenu.getWriteAuth())) 	return true;	// 등록
		if("UPD".equals(programEvent) && "Y".equals(acessMenu.getEditAuth()))  	return true;	// 수정
		if("DEL".equals(programEvent) && "Y".equals(acessMenu.getDeleteAuth())) return true;	// 삭제
		if("SEL".equals(programEvent) && "Y".equals(acessMenu.getReadAuth())) 	return true;	// 조회
		if("PRT".equals(programEvent) && "Y".equals(acessMenu.getPrintAuth())) 	return true;	// 출력

		return false;
	}

	/**
	 * 접근로그 저장
	 */
	public void insertAccessLog(AccessLog accessLog) {
		
		if(accessLog == null || StringUtils.isEmpty(accessLog.getSessionId()) || StringUtils.isEmpty(accessLog.getResFullUrl())){
			return;
		}
		
		try{
			accessCommonMapper.insertAccessLogSp(accessLog);	
		}catch (Exception e) {
			logger.error("접근로그저장 프로시져 오류 : " + e.getMessage());
		}
	}

	/**
	 * 시스템 코드에 따른 사용자 권한별 최상단 노출 메뉴조회 (상단에 '입점상담시스템|전자계약 시스템' 으로 메뉴 노출) 
	 */
	@Override
	public List<MenuSession> selectGrantAuthSysMenuList(CustomUser customUser) {
		
		List<MenuSession> sysMenuAuthList = new ArrayList<MenuSession>();
		
		if(customUser.getMemberCd() != null) {
			sysMenuAuthList = accessCommonMapper.selectGrantAuthSysMenuList(customUser);
		} else {
			// 게스트가 로그인시에는 상단 대메뉴를 고정시킬것임
			MenuSession sysMenuVo = new MenuSession();
			sysMenuAuthList.add(sysMenuVo);
		}
		
		return sysMenuAuthList;
	}

	/**
	 * 시스템 코드에 따라 메뉴 대분류코드 유효성 체크 (상단에 '입점상담시스템|전자계약 시스템' 으로 메뉴 노출되고 각 시스템별 메뉴 조회)
	 */
	@Override
	public int selectValidSysMenu(HashMap<String, String> paramMap) {
		return accessCommonMapper.selectValidSysMenu(paramMap);
	}

	
	/**
	 * 모바일 최근 접속일시 조회
	 */
	@Override
	public Result selectAccessDt(HashMap<String, String> paramMap) throws Exception {
		Result result = new Result(); 
		result.setRtnValue01(StringUtil.nullConvert(accessCommonMapper.selectAccessDt(paramMap)));

		return result; 
	}
	

	
	/**
	 * 접근허용아이피 정보 조회
	 */
	public boolean selectFuncIpPermission(String ipAddress, String url, MenuSession acessMenu){

		logger.debug(" IP ADDRESS :  "+ipAddress);
		logger.debug(" ACCESS URL :  "+url);
		   
		if(StringUtils.isEmpty(ipAddress) || StringUtils.isEmpty(url) || acessMenu == null) return true;
		
		logger.debug(" VDI CODE :  "+acessMenu.getVdiCd());
		
		if(    StringUtils.isEmpty(acessMenu.getVdiCd())
			|| StringUtils.isEmpty(acessMenu.getWebPage())
			|| StringUtils.isEmpty(acessMenu.getVdiIps())
		 ) return true;
		
		String programId 	= url;
		int inx = url.lastIndexOf("_");
		if (inx > 0)  programId 	 = url.substring(0, inx);

		logger.debug(" PROGRM ID :  "+programId);
		
		
		/*아이피체크 페이지여부,   등록된 웹페이지 URL (프로그램 아이디)가 아닌경우 TRUE */
		if(!acessMenu.getWebPage().contains(programId) ) return true;
		
		/* IP 목록 (acessMenu.vdiIps : 120.0.0.1,120.0.0.2 )*/
		String permitIp = acessMenu.getVdiIps();
		String[] permitIpArr = permitIp.split(",");

		for( String ip : permitIpArr ) {
			if(ip.trim().equals(ipAddress)) return true;
		}
		return false;
	}
	
	public boolean selectFuncIpPermission_back(String ipAddress, String url, MenuSession acessMenu){

		if(StringUtils.isEmpty(ipAddress) || StringUtils.isEmpty(url) || acessMenu == null) return true;
		
		String vdi = acessMenu.getVdiCd() == null ? "N" :acessMenu.getVdiCd() ;
		
		if("N".equals(vdi) || StringUtils.isEmpty(acessMenu.getWebPage())) return true;
		
		String programId 	= url;
		int inx = url.lastIndexOf("_");
		if (inx > 0)  programId 	 = url.substring(0, inx);
		
		/*아이피체크 페이지여부,   등록된 웹페이지 URL (프로그램 아이디)가 아닌경우 TRUE */
		if(!acessMenu.getWebPage().contains(programId) ) return true;
		
		/*등록아이피 와 일치하는 REQUEST IP 여부 확인 ---------------------------------------------*/
		
		List<HashMap<String, String>> list = accessCommonMapper.selectAccessIpList();
		if(list == null || list.size() <= 0) return true;
		for ( HashMap<String,String> ipMap : list ) {
			
			String permitIp = (String)ipMap.get("PERMIT_IP");
			String[] permitIpArr = permitIp.split(",");
			
			for( String ip : permitIpArr ) {
				if(ip.equals(ipAddress)) return true;
			}
			
			
		}
		
		/*-------------------------------------------------------------------------------*/

		return false;
	}
	
	/**
	 * 기기인증정보 확인
	 */
	public Result selectFuncUserDevicePermission(String url, CustomUser customUser, HttpServletRequest request){
	
		Result result = new Result();
		result.setMsgCd(Result.FAIL);
		
		String moveToUrl = "";
		
		//사용자 기기인증정보 조회
		String memberCd = StringUtil.nullConvert(customUser.getMemberCd());
		String deviceAuthCookie_name = devAuthCookieName + "_" + memberCd;	// 쿠키명
		
		Cookie[] cookies = request.getCookies();	//전체 쿠키
		
		//쿠키가 존재할 경우,
		if(cookies != null) {
			String cookieNm = "";
			String cookieVal = "";
			boolean cookieFlag = false;
			
			//쿠키 탐색
			for(Cookie c : cookies) {
				 cookieNm = c.getName();
				 cookieVal = c.getValue();
			
				 //기기인증 쿠키일 경우,
				 if(cookieNm.equals(deviceAuthCookie_name)) {
					 //쿠키 정보가 유효한 정보인지 확인
					 if(this.selectFuncUserDeviceValid(cookieVal)) {
						 //유효한 쿠키일 경우, flag 변경 및 탐색 종료
						 cookieFlag = true;
						 break;
					 }
				 }
			}
			
			//유효한 기기인증정보 쿠키가 있을 경우,
			if(cookieFlag) {
				//세션에 쿠키인증여부 등록
				request.getSession().setAttribute("deviceAuthChk", "Y");
				
				//결과값 return
				result.setMsgCd(Result.SUCCESS);
				return result;
			}
		}
		
		//기기인증정보 쿠키가 없거나 유효하지 않은 쿠키이면서,
		//guest가 접근 가능한 page 접근 시, 로그아웃(세션삭제) 처리
		if(url.contains("/web/") || "/".equals(url) || url.contains("/main/")) {
			moveToUrl = "/security/securitySignout";
		}else {
		//guest가 접근 불가능한 page 접근 시, 기기인증 페이지로 이동
			moveToUrl = "/security/securityCertDevice";
		}
		
		result.setRtnValue01(moveToUrl);	//redirect url 셋팅
		return result;
	}
	
	/**
	 * 
	 * @Method : selectFuncUserDeviceValid
	 * @Description : 기기인증 쿠키 유효성 검사
	 * @param cookieVal
	 * @return boolean
	 */
	private boolean selectFuncUserDeviceValid(String cookieVal) {
		//암호화된 value
		String encCookieVal = StringUtils.defaultString(cookieVal);
		if("".equals(encCookieVal)) return false;
		
		//원본 value
		String orgCookieVal = StringUtils.defaultString(StringUtil.getDecrypt(encCookieVal));
		
		if(!"".equals(orgCookieVal)) {
			String[] cookieValues = orgCookieVal.split("_");
			if(cookieValues.length == 3) {
				String validYn = "";
				
				HashMap<String,String> paramMap = new HashMap<String,String>();
				paramMap.put("memberCd", StringUtils.defaultString(cookieValues[0]));
				paramMap.put("termsAgreeDt", StringUtils.defaultString(cookieValues[1]));
				paramMap.put("agreeRegNo", StringUtils.defaultString(cookieValues[2]));
				
				validYn = accessCommonMapper.selectChkUserDeviceAuthCookieValidYn(paramMap);
				
				if("Y".equals(validYn)) return true;
				
			}
		}
		return false;
	}
	
	
	  /**
     * 파일다운로드 이력 등록 
     */
    @Override
    public int insertFileDownloadHist(AttachFileVo attachFileVo) throws Exception {
    	return accessCommonMapper.insertFileDownloadHist(attachFileVo);
    }
    
}
