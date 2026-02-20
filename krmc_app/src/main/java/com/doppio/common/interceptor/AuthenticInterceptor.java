package com.doppio.common.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.MenuMap;
import com.doppio.common.model.MenuSession;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.service.AccessCommonService;

import org.springframework.tronic.util.StringUtil;




/**
 * 
 * @Class : AuthenticInterceptor.java
 * @Description : 인터셉터
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2022. 1. 19.                  최초 생성
 *
 * </pre>
 */
public class AuthenticInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticInterceptor.class);	
	
	
	@Autowired
	AccessCommonService accessCommonService;
	
	@Autowired
	CustomUserService customUserService;
	
	@Value("#{config['Common.Language.Type']}")
	public String languageType;

	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	@Value("#{config['security.deviceAuth.cookiename']}")
	public String devAuthCookieName;

	@Value("#{config['security.deviceAuth.checkYn']}")
	public String devAuthCheckYn;

	/**
	 * Controller 호출전
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param handler
	 * @throws ServletException
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String url 			= request.getRequestURI();
		String contextPath	= request.getContextPath();

		logger.debug("-----[START | AuthenticInterceptor.preHandle]------------------------------------");
		logger.debug("-----[URI | ACCEPT]" + url+ " ---|--- "+request.getHeader("Accept"));

		CustomUser customUser 				= new CustomUser();		//로그인 사용자 정보
		List<GrantedAuthority> authorities  = null;					//로그인 사용자 ROLL
		boolean permissionProgram 			= false;    			//접근 메뉴 또는 실행 이벤트 권한 확인

		
		// 접속디바이스 변경여부 체크 ---------------------------------------------------------------*/
        boolean deviceEqualsYn = true;
        deviceEqualsYn = this.checkDeviceEquals(request);
        String deviceGbn = StringUtil.nullConvert((String)request.getSession().getAttribute("loginDevice"));	// 현재 접속중인 디바이스구분     
        /*-------------------------------------------------------------------------------------*/

        
		/*사용자 로그인 정보 설정 ------------------------------------------------------------------*/
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
        	 customUser 	= (CustomUser)auth.getPrincipal();
             authorities 	= (List<GrantedAuthority>)auth.getAuthorities();
             
             /*사용자 기기인증 여부 확인  -----------------------------------------------------------*/
             if("Y".equals(devAuthCheckYn)) {
            	 String deviceAuthChk = StringUtil.nullConvert((String)request.getSession().getAttribute("deviceAuthChk"));
                 //쿠키인증정보가 없으면서 security 페이지가 아닐 경우, 기기인증 정보 체크
                 if(!"Y".equals(deviceAuthChk) && !url.contains("/security/")) {
                	 Result rsltDeviceChk = accessCommonService.selectFuncUserDevicePermission(url, customUser, request);
                	 String rsltDeviceChkMsgCd = rsltDeviceChk.getMsgCd();
                	 
                	 //기기인증 정보가 없을 경우, page redirect
                	 if(!"success".equals(rsltDeviceChkMsgCd)) {
                		 response.sendRedirect(request.getContextPath() + rsltDeviceChk.getRtnValue01());	 
                		 return false;
                	 }
                 }
             }
             /*-------------------------------------------------------------------------------*/
             
             if("Y".equals(StringUtils.defaultString(customUser.getPassChgYn()))&&!"".equals(StringUtils.defaultString(customUser.getForgotPwdCode())) ) {
      			//임시비밀번호 발급 or 비밀번호 찾기 진행시 , 비밀번호 변경이 진행되지 않았으면 변경화면으로 이동
      			response.sendRedirect(request.getContextPath() +"/security/pwEsnReset");
      			return false;
      		}
        }
       /*-------------------------------------------------------------------------------------*/

        /*동기방식 Request일경우에만 메뉴구성 정의 함 -------------------------------------------------*/
        if(!request.getHeader("Accept").contains("application/json") || !request.getHeader("Accept").contains("application/pdf") ){
			// 접근 메뉴 및 버튼 권한 정의
	        getCurrentMenuInfo(customUser,request,deviceEqualsYn);
		}
        /*------------------------------------------------------------------------------------*/



        /*접근 메뉴 또는 실행 이벤트 권한 확인  -------------------------------------------------------*/
        MenuSession menuSession = (MenuSession)request.getAttribute("acessMenu");		//실행프로그램

        if(StringUtils.isNotEmpty(customUser.getMemberCd())){
            permissionProgram = accessCommonService.selectFuncPermission(customUser.getMemberCd(), url, menuSession);
            if(!permissionProgram) {
            	throw new AccessDeniedException(this.message.getMessage("error.msg.custom.access.permission.denied"));
            }
        }
        /*------------------------------------------------------------------------------------*/

        
        /*접근아이피 체크 -------------------------------------------------------------------------*/
        String ip = request.getRemoteAddr();
        logger.debug("-----[STRT | IP CHECK-SelectFuncIpPermission ]-------------------");
 	   	permissionProgram = accessCommonService.selectFuncIpPermission(ip, url, menuSession);
 	   	if(!permissionProgram) {
 	   		throw new AccessDeniedException(this.message.getMessage("error.msg.custom.unaccessible.ip"));
 	   	}
 	   	logger.debug("-----[END  | IP CHECK-SelectFuncIpPermission ]-------------------");
 	   	/*------------------------------------------------------------------------------------*/
 	   

		logger.debug("-----[END | AuthenticInterceptor.preHandle]------------------------------------");

		return true;
	}

	
	/**
	 * Controller 실행 후
	 * @param request
	 * @param response
	 * @param handler
	 * @throws ServletException
	 */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model, Authentication auth) throws Exception {
    }

    
    /**
	 * View jsp 최종 생성후 실행
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param handler
	 * @param Exception
	 * @throws ServletException
	 */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    
	/**
	 * 실행메뉴 및 프로그램 권한 설정
	 *
	 * @param ManagerSession
	 * @param request
	 * @return
	 */
	public void getCurrentMenuInfo(CustomUser customUser, HttpServletRequest request, Boolean deviceEqualsYn) throws Exception {
		
		List<MenuSession> authMenuList = null;
		String menuSession_key = MenuSession.MANAGER_MENU_SESSION_KEY+"_"+request.getSession().getId();
		
		// 현재접속한 디바이스정보
		Device device = DeviceUtils.getCurrentDevice(request);
		String deviceGbn = "W";	// 디폴트 PC
		if(device != null) {
			if(device.isMobile() ) {
				deviceGbn = "M";	// 모바일 
			}else {
				deviceGbn = "W";	// PC 또는 타블릿
			}
		}	
		
		//신규가입자 여부
		String isNewMember = StringUtil.nullConvert(request.getSession().getAttribute("isNewMember"));
		
		//기기가 변경되었거나 신규가입하여 즉시 로그인된 멤버일 경우, 메뉴 권한 재조회
		if(!deviceEqualsYn || "Y".equals(isNewMember)){
			customUser.setExcTypeCd(deviceGbn);
			// 사용자 권한별 메뉴목록 조회
			authMenuList = accessCommonService.selectGrantMenuList(customUser);
			request.getSession().setAttribute(menuSession_key, authMenuList);	// 전체메뉴 tree
			request.getSession().setAttribute("loginDevice", deviceGbn);		// 요청 디바이스구분
		}else{
			// 기셋팅된 메뉴리스트 데이터가 존재하는지 체크
			authMenuList = (List<MenuSession>)request.getSession().getAttribute(menuSession_key);
			// 메뉴리스트 데이터가 존재하지않으면 재조회하여 세션에 저장
			if(authMenuList == null || authMenuList.isEmpty()) {
				customUser.setExcTypeCd(deviceGbn);

				authMenuList = accessCommonService.selectGrantMenuList(customUser);
				request.getSession().setAttribute(menuSession_key, authMenuList); 	// 전체메뉴 tree
				request.getSession().setAttribute("loginDevice", deviceGbn);		// 요청 디바이스구분
			}
		}
		
		// 조회결과 권한을 가진 메뉴가 없는경우
		if(authMenuList == null || authMenuList.size() <= 0) {
			request.setAttribute("resetMainYn", (deviceEqualsYn == true) ? "N" : "Y");
			return;
		}
		
		// 메뉴아이디 설정
		String menuId = request.getParameter("_code") == null ? "" : request.getParameter("_code"); // 선택된 메뉴아이디	
		
		if(!StringUtils.isEmpty(menuId)){
			request.getSession().setAttribute("_codeSesson", menuId);
		}else{
			// 넘어온 메뉴코드가 없을경우 먼저 URL값을통해 menu table을 참조한다
			String path = request.getRequestURI().substring(request.getContextPath().length());
			
			if(request.getRequestURI().lastIndexOf("?") > -1){
				path = path.substring(0, path.lastIndexOf('?'));
			}
			
			menuId = accessCommonService.selectMenuidValidation(path);

			// 메뉴테이블에 없을경우 session에 담겨있는 메뉴아이디를 사용한다.
			if(StringUtils.isEmpty(menuId)){
				menuId = (String)request.getSession().getAttribute("_codeSesson");
			}else{
				request.getSession().setAttribute("_codeSesson", menuId);
			}
		}

		// 메뉴구성 start
		List<MenuSession>   myList   	= new ArrayList<MenuSession>();		// MY     MENU
		List<MenuSession>   topList   	= new ArrayList<MenuSession>();		// TOP    MENU
		List<MenuSession>  	leftList  	= new ArrayList<MenuSession>();		// LEFT   MENU
		List<MenuMap>  		mapList  	= new ArrayList<MenuMap>();			// MAP    MENU

		MenuSession			my			= null;
		MenuSession 		top	 		= null;
		MenuSession 		menuNext 	= null;
		MenuMap 			menuMap 	= null;
		MenuSession 		pgmMenu   	= null;								// ACCESS PROGRAM
		boolean stop = false;												// 상위메뉴 목록에서 링크되는 첫번째 메뉴설정 여부체크용

		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();

		// TOP 메뉴 구성 설정 및 선택된 메뉴아이디의 속성정의
		for(MenuSession menu : authMenuList){

			// LOCALE ko 가 아닌경우 확장 메뉴 locale set 설정 
			if("ko".equals(language) || "ko_KR".equals(language)){
				menu.setTitleLocale(menu.getTitle());
			}else if("en".equals(language) || "en_EN".equals(language)){
				menu.setTitleLocale(menu.getTitleSub());
			}else{
				menu.setTitleLocale(menu.getTitleExtend());
			}

			// Level == 1 && 단말인 아닌경우 대메뉴로 인식한다.
			// 상위메뉴구성
			if(menu.getLevel() == 1 && menu.getLeaf() == 0 && !"Y".equals(menu.getHideYn())){
				top = new MenuSession();
				top.setMenuId(menu.getMenuId());
				top.setTitle(menu.getTitle());
				top.setTitleLocale(menu.getTitleLocale());
				top.setHideYn(menu.getHideYn());
				top.setUseYn(menu.getUseYn());
				top.setSysCode(menu.getSysCode());			// 최상위 대메뉴 구분
				top.setExtraInfo(menu.getExtraInfo());
				top.setMenuIcon(menu.getMenuIcon());
				stop = true;
			}
			
			if(stop && menu.getLeaf() > 0){
				// 단말자식이 있는지 확인한다.
				// 첫번째 단말 자식의 링크 주소가 있는 경우, 숨김메뉴가 아닌경우만 추가한다
				if(!StringUtils.isEmpty(menu.getWebPage()) && !"Y".equals(menu.getHideYn())){
					top.setChildId(menu.getMenuId());
					top.setChildTitle(menu.getTitle());
					top.setChildTitleLocale(menu.getTitleLocale());
					top.setWebPage(menu.getWebPage());
					top.setExtraInfo(menu.getExtraInfo());
					topList.add(top);							// 대메뉴 추가한다.
					
					stop = false;
				}
			}

			// 실행프로그램  구성 설정
			if(menu.getMenuId().equals(menuId)){
				pgmMenu = new MenuSession();

				pgmMenu.setReadAuth(menu.getReadAuth());		// 조회권한
				pgmMenu.setWriteAuth(menu.getWriteAuth());		// 등록권한
				pgmMenu.setEditAuth(menu.getEditAuth());		// 수정권한
				pgmMenu.setDeleteAuth(menu.getDeleteAuth());	// 삭제권한
				pgmMenu.setPrintAuth(menu.getPrintAuth());		// 출력(Excel)
				pgmMenu.setAllAuth(menu.getAllAuth());			// 전체권한여부

				pgmMenu.setTitle(menu.getTitle());				// 실행프로그램 제목
				pgmMenu.setTitleLocale(menu.getTitleLocale());	// LOCALE 적용 제목
				pgmMenu.setExtraInfo(menu.getExtraInfo());		// 부가설명
				pgmMenu.setSort(menu.getSort());				// 메뉴정렬 순번
				pgmMenu.setMenuId(menu.getMenuId());			// 프로그램 메뉴아이디(PK)
				pgmMenu.setWebPage(menu.getWebPage()); 			// 프로그램 실행 URL
				pgmMenu.setVdiCd(menu.getVdiCd()); 				// 아이피접근제어정책(9050)
				pgmMenu.setVdiIps(menu.getVdiIps()); 			// 접근가능 아이피내역 
				pgmMenu.setDtlLogYn(menu.getDtlLogYn());        // 상세로그 생성여부(Y/N)
				pgmMenu.setParentId(menu.getParentId());		// 상위메뉴
				pgmMenu.setSysCode(menu.getSysCode());			// 최상위 대메뉴 구분
				pgmMenu.setMenuIcon(menu.getMenuIcon()); 		// 메뉴아이콘 
			}
		}

		// 바로가기 버튼 및 상위메뉴 타이틀 설정
		if(pgmMenu != null){
			String[] sorts = pgmMenu.getSort().split(",");

			for(int i = 0; i < sorts.length; i++){

				if(StringUtils.isEmpty(sorts[1])){
					continue;
				}

				for(MenuSession menu : authMenuList){
					if(sorts[i].equals(menu.getMenuId())){

						menuMap = new MenuMap();
						
						menuMap.setTitle(menu.getTitle());
						menuMap.setTitleLocale(menu.getTitleLocale());
						menuMap.setWebPage(menu.getWebPage());
						menuMap.setMenuId(menu.getMenuId());
						menuMap.setLeaf(menu.getLeaf());
						menuMap.setSysCode(menu.getSysCode());
						mapList.add(menuMap);

						if(menu.getLevel() == 1 && menu.getLeaf() == 0){
							pgmMenu.setTopMenuTitleLocale(menu.getTitleLocale());
							pgmMenu.setTopMenuId(menu.getMenuId());
						}

						break;
					}
				}
			}

			pgmMenu.setMenuList(mapList);
		}

		// LEFT 메뉴 구성 설정
		if(pgmMenu != null){
			for(MenuSession menu : authMenuList){
				// 참조할 대메뉴를 이용하여 하위메뉴리스트를 생성한다.
				if(menu.getSort().indexOf(pgmMenu.getTopMenuId()) > -1){
					//if(menu.getLevel() != 1 && menu.getLeaf() != 0) {
					if(menu.getLevel() != 1 ) {
						menuNext = new MenuSession();

						menuNext.setTitle(menu.getTitle());
						menuNext.setTitleSub(menu.getTitleSub());
						menuNext.setTitleLocale(menu.getTitleLocale());
						menuNext.setMenuId(menu.getMenuId());
						menuNext.setParentId(menu.getParentId());
						menuNext.setWebPage(menu.getWebPage());
						menuNext.setLeaf(menu.getLeaf());
						menuNext.setHideYn(menu.getHideYn());
						menuNext.setUseYn(menu.getUseYn());
						menuNext.setLevel(menu.getLevel());
						menuNext.setMenuIcon(menu.getMenuIcon());
						menuNext.setSysCode(menu.getSysCode());
						
						leftList.add(menuNext);
					}
				}
			}
		}

		// MY(메뉴 오버시 내려오는 레이아웃에 구성되는 데이터)
		for(MenuSession menu : authMenuList){
			my = new MenuSession();
			
			// LOCALE ko 가 아닌경우 확장 메뉴 locale set 설정
			if("ko".equals(language) || "ko_KR".equals(language)){
				my.setTitleLocale(menu.getTitle());
			}else if("en".equals(language) || "en_EN".equals(language)){
				my.setTitleLocale(menu.getTitleSub());
			}else{
				my.setTitleLocale(menu.getTitleExtend());
			}
			
			my.setMenuId(menu.getMenuId());
			my.setWebPage(menu.getWebPage());
			my.setHideYn(menu.getHideYn());
			my.setTitle(menu.getTitle());
			my.setParentId(menu.getParentId());
			my.setLeaf(menu.getLeaf());
			my.setHideYn(menu.getHideYn());
			my.setUseYn(menu.getUseYn());
			my.setLevel(menu.getLevel());
			my.setSysCode(menu.getSysCode());
			my.setMenuIcon(menu.getMenuIcon());
			myList.add(my);
		}
		
		request.setAttribute("myMenuList"	,myList);			// MY   MENU
		request.setAttribute("topMenuList"	,topList);			// TOP  MENU   생성
		request.setAttribute("leftMenuList"	,leftList);			// LEFT MENU   생성
		request.setAttribute("acessMenu"	,pgmMenu);			// 실행프로그램 속성정의 생성
		request.setAttribute("authMenuList"	,authMenuList);		// 권한 전체메뉴 tree
	}


	/**
	 * 
	 * @Method : checkDeviceEquals
	 * @Description : 접속디바이스 타입 변경여부
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private boolean checkDeviceEquals(HttpServletRequest request) throws Exception {
		
		boolean deviceEqualsYn = true;
		// 현재접속한 디바이스정보
		Device device = DeviceUtils.getCurrentDevice(request);
		String deviceGbn = "W";	// 디폴트 PC
		if(device != null) {
			if(device.isMobile() || device.isTablet()) {
				deviceGbn = "M";	// 모바일 또는 타블릿
			}else {
				deviceGbn = "W";	// PC
			}
		}
		
		// 이전접속디바이스(M:모바일, W:PC & 타블릿, 정보없음:"")
		String originDeviceGbn = StringUtil.nullConvert((String)request.getSession().getAttribute("loginDevice"));
		if(!originDeviceGbn.equals("")) {
			if(originDeviceGbn.equals(deviceGbn)) {
				deviceEqualsYn = true;
			}else {
				deviceEqualsYn = false;
			}
		}

		request.getSession().setAttribute("loginDevice", deviceGbn);		// 요청 디바이스구분
		
		return deviceEqualsYn;
	}
	
}