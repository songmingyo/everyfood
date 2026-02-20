package com.doppio.dashboard.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.common.model.UserInfoVo;



/**
 * @Class Name : DashboardMainMapper.java
 * @Description : 메인대시보드 데이터 조회 매퍼 
 * @Modification Information
 *
 *    수정일         수정자   수정내용
 *    -----------  ------- -------------------
 *    2022.10.26.  DADA    최초생성 
 *
 * @author  DADA
 * @since 2022. 10. 26.
 * @version
 * @see
 *
 */
@Repository(value = "dashboardMainMapper")
public interface DashboardMainMapper {

	/**
	 * 로그인 시 담당자 정보 수정
	 * @param userInfoVo
	 * @return Map<String, Object>
	 */
	public int updateCompInfo(UserInfoVo userInfoVo);
	
	
	/**
	 * 메인페이지 접속 시 담당자 정보 검증
	 * @param userInfoVo
	 * @return HashMap<String, Object>
	 */
	public UserInfoVo selectCompInfo(UserInfoVo userInfoVo);
	
	
	
	
}
