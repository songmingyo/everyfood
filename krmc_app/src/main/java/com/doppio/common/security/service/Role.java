package com.doppio.common.security.service;


import org.springframework.security.core.GrantedAuthority;
 

public class Role implements GrantedAuthority{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6614239305665335057L;
	private String name;

	 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    
    @Override
    public String getAuthority() {
        return this.name;
    }
    
}
