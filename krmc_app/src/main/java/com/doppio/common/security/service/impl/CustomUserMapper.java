package com.doppio.common.security.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.doppio.common.security.service.CustomUser;

/**
 * 
 * @Class : CustomUserMapper.java
 * @Description : 사용자 정보 Mapper
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 0. 0.	                  최초 생성
 *
 * </pre>
 */
@Repository(value = "customUserMapper")
public interface CustomUserMapper {

	/**
	 * 
	 * @Method : selectSecurityData
	 * @Description : 사용자 정보 조회 - USER LOGIN, REMEMBER-ME 
	 * @param params
	 * @return CustomUser
	 */
	public CustomUser selectSecurityData(HashMap<String,String> params);


	/**
	 * 
	 * @Method : updateSecurityPassFailCntAndLock
	 * @Description : 사용자 로그인 오류 카운트 및 lock 처리
	 * @param customUser
	 * @return int
	 */
	public int updateSecurityPassFailCntAndLock(CustomUser customUser);

	
	/**
	 * 
	 * @Method : updateSecurityPassFailCntClear
	 * @Description : 사용자 로그인 오류 카운트 해지처리
	 * @param customUser
	 * @return int
	 */
	public int updateSecurityPassFailCntClear(CustomUser customUser);


	/**
	 * 사용자 그룹 체크
	 * @param CustomUser
	 * @return List<HashMap<String, Object>>
	 */
	public List<HashMap<String, Object>> selectMemberGroupAuth(CustomUser customUser);

	
	/**
	 * 
	 * @Method : insertMemberAuth
	 * @Description : 디폴트 그룹정보등록
	 * @param customUser
	 * @return int
	 */
	public int insertMemberAuth(CustomUser customUser);

	
	/**
	 * 내부사용자 TCM_MEMBER COUNT
	 * @param String
	 * @return int
	 */
	public int selectInternalUserTcmMemberCount(String username);

	/**
	 * 
	 * @Method : selectChkUserDeviceAuthCookieValidYn
	 * @Description : 사용자 기기인증 쿠키 정보 유효성 확인
	 * @param param
	 * @return String
	 */
	public String selectChkUserDeviceAuthCookieValidYn(HashMap<String,String> param);
}