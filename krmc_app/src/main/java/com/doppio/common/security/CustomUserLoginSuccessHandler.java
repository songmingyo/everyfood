package com.doppio.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.service.AccessCommonService;


public class CustomUserLoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserLoginSuccessHandler.class);
	
	@Autowired
    private CustomUserService userService;
	
	@Autowired
	AccessCommonService accessCommonService;
	
	@Value("#{config['security.deviceAuth.cookiename']}")
	public String devAuthCookieName;
	
	@Value("#{config['security.deviceAuth.checkYn']}")
	public String devAuthCheckYn;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		logger.info("로그인 성공 핸들러 시작------------------------------------------------------------------");
		
		String url 			= request.getRequestURI();
		
		// 로그인한 사용자정보 조회
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		/*사용자 기기인증 여부 확인  -----------------------------------------------------------*/
		/*
		if("Y".equals(devAuthCheckYn)) {
			Result rsltDeviceChk = accessCommonService.selectFuncUserDevicePermission(url, customUser, request);
			String rsltDeviceChkMsgCd = rsltDeviceChk.getMsgCd();
	   	 
			//기기인증 정보가 없을 경우, page redirect
			if(!"success".equals(rsltDeviceChkMsgCd)) {
				response.sendRedirect(request.getContextPath() + rsltDeviceChk.getRtnValue01());	 
				return;
			}
		}*/
		/*-------------------------------------------------------------------------------*/
		
		//if(("Y".equals(StringUtils.defaultString(customUser.getPassChgYn()))&&!"".equals(StringUtils.defaultString(customUser.getForgotPwdCode())) )) {
		if(("Y".equals(StringUtils.defaultString(customUser.getPassChgYn())) )) {
 			//임시비밀번호 발급 or 비밀번호 찾기 진행시 , 비밀번호 변경이 진행되지 않았으면 변경화면으로 이동
			response.sendRedirect(request.getContextPath() +"/security/pwEsnReset");
			
		}else if("Y".equals(StringUtils.defaultString(customUser.getPw3mFlag()))){
  			//최종비밀번호 변경후 3개월지난시점 일때: pw변경(초기화)페이지로 이동
			response.sendRedirect(request.getContextPath() +"/security/pwEsnReset");
			
		}else {
			//메인화면으로 이동
     		response.sendRedirect(request.getContextPath() + "/main/mainDashboard");
		}
		
	}
	
}