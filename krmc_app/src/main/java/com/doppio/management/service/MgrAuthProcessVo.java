package com.doppio.management.service;

import java.io.Serializable;

public class MgrAuthProcessVo  implements Serializable {

	/** 
	 * @see 
	 * @Description : 
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 425021648957645585L;
	
	
	/* 권한정보 LIST VO */
	private MgrAuthListVo authInfoList = null;
	
	

	public MgrAuthListVo getAuthInfoList() {
		return authInfoList;
	}

	public void setAuthInfoList(MgrAuthListVo authInfoList) {
		this.authInfoList = authInfoList;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
