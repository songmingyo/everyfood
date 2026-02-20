package com.doppio.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.doppio.common.model.MenuSession;
import com.doppio.common.security.service.CustomUser;

public class CustomeLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		if (authentication != null) {
			CustomUser loginUser = (CustomUser)authentication.getPrincipal();
            try {
        		//일반적인 로그아웃
        		if(loginUser.getOldAuth() == null) {
        			//세션 삭제
        			session.invalidate();
        			
        		}else { //관리자가 사용자 전환해서 로그아웃 시
        			Authentication oldAuth = loginUser.getOldAuth();
        			
        			//관리자 정보(oldAuth)를 Auth에 넣어주고 oldAuth 삭제
        			SecurityContextHolder.getContext().setAuthentication(oldAuth);
        			loginUser.setOldAuth(null);
        			
        			//전환 사용자의 menuSession 삭제
        			String menuSession_key = MenuSession.MANAGER_MENU_SESSION_KEY+"_"+session.getId();
            		session.removeAttribute(menuSession_key);
        		}
            } catch (Exception e) {
            	logger.debug(e.getMessage());
            }
	     }
    }
}
