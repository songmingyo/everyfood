package com.doppio.common.security;


import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
 

@Component(value = "customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider, UserDetailsService{
 
	 
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private CustomUserService userService;
    
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         
         if(!authentication.isAuthenticated()) {
        	CustomUser user = userService.loadUserByLoginUsername( authentication);
        	Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        	return new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
        } else return authentication;
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("UserDetails loadUserByRememberMe ======================= : " + username);
		
		//throw new UsernameNotFoundException("");
		
		CustomUser user = userService.loadUserByRememberMe(username);
		return user;
	} 
	
}