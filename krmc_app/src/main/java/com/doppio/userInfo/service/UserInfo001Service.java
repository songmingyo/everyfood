package com.doppio.userInfo.service;

import java.sql.SQLException;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.workplace.common.model.UserInfoVo;

public interface UserInfo001Service {

	/**
	 * 사용자 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public UserInfoVo selectUserData (UserInfoVo param) throws Exception;
	
	/**
	 * 회사 정보 조회
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public CommonCompInfoVo selectCompData (CommonCompInfoVo param) throws Exception;
	
	
}
