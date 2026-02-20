package com.doppio.common.security.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
 
public interface CustomUserService   {

   CustomUser 		loadUserByLoginUsername(Authentication authentication) throws UsernameNotFoundException;
   CustomUser 		loadUserByRememberMe(String username) throws UsernameNotFoundException ;
   public boolean loadUserByPlsUserChangedSession(String userId, String userPw, HttpServletRequest request) throws UsernameNotFoundException;
}