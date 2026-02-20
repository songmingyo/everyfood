package com.doppio.userInfo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.userInfo.service.UserInfo001Service;
import com.doppio.workplace.common.model.UserInfoVo;

@Service("userInfo001Service")
public class UserInfo001ServiceImpl implements UserInfo001Service {

	private static final Logger logger = LoggerFactory.getLogger(UserInfo001ServiceImpl.class);
	
	@Autowired
	private UserInfo001Mapper userInfo001Mapper;
	
	/**
	 * 사용자 정보 조회
	 */
	public UserInfoVo selectUserData (UserInfoVo paramVo) throws Exception {
		return userInfo001Mapper.selectUserData(paramVo);
	}
	
	/**
	 * 회사 정보 조회
	 */
	public CommonCompInfoVo selectCompData (CommonCompInfoVo paramVo) throws Exception {
		return userInfo001Mapper.selectCompData(paramVo);
	}
	
}
