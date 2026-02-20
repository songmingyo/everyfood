package com.doppio.common.util.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.doppio.common.security.service.CustomUser;
import com.doppio.management.controller.MgrCodeController;

public class CustomUtil {

	private CustomUser customUser = null;
	private static final Logger logger = LoggerFactory.getLogger(MgrCodeController.class);

	public CustomUtil() {
		try{
		/* User Session(사용자정보 설정  ) -----------------------------------------  */
		final Authentication auth =SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
			customUser 	= (CustomUser)auth.getPrincipal();
	    }
		/* ---------------------------------------------------------------------- */
		}catch (Exception e) {
			logger.error(e.toString());
		}
	}


	public CustomUser customUser() {
		return this.customUser;
	}
	
	public CustomUser getCustomUser() {
		if(customUser != null ) return this.customUser;
		return null;
	}


	public String getMemberCd() {
		if(customUser != null ) return customUser.getMemberCd();
		return null;
	}

	public String getUsername() {
		if(customUser != null )  return customUser.getUsername();
		return null;
	}

	public String getUserId() {
		if(customUser != null ) return customUser.getUserId();
		return null;
	}

	public String getUserType() {
		if(customUser != null ) return customUser.getUserType();
		return null;
	}

	public String getMasterId() {
		if(customUser != null ) return customUser.getMasterId();
		return null;
	}

	public String getCompNm() {
		if(customUser != null ) return customUser.getCompNm();
		return null;
	}
	
	public String getPositionNm() {
		if(customUser != null ) return customUser.getPositionNm();
		return null;
	}
	
	public String getClassNm() {
		if(customUser != null ) return customUser.getClassNm();
		return null;
	}
	
}
