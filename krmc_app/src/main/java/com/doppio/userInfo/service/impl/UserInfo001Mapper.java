package com.doppio.userInfo.service.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.workplace.common.model.UserInfoVo;

@Repository(value = "userInfo001Mapper")
public interface UserInfo001Mapper {

	/**
	 * 사용자 정보 조회
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public UserInfoVo selectUserData (UserInfoVo paramVo) throws SQLException;
	
	/**
	 * 회사 정보 조회
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public CommonCompInfoVo selectCompData (CommonCompInfoVo paramVo) throws SQLException;
}
