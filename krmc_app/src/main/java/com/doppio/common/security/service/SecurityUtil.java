package com.doppio.common.security.service;


import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtil implements Serializable  {

	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	private static final long serialVersionUID = -755388126289148696L;

	public static String getEncryptedPassword(String param){
		return getEncryptedPassword(param, "");
	}
	
	public static String getEncryptedPassword(String param, String salt){
		try{
        	MessageDigest md = MessageDigest.getInstance("SHA-256");
        	md.update((param+salt).getBytes());
        	return Base64.getEncoder().encodeToString(md.digest());
        }catch(Exception e){
        	logger.error("Ecrypt Exception Occured");
        }
		return "";
	}

}