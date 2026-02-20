package com.doppio.management.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.doppio.common.model.Result;

/**
 * 
 * @Class : MgrMemberService.java
 * @Description : 시스템관리 -사용자 정보 Service
 * @author : 안석진
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2022. 12. 22.            안석진           최초 생성 (사용자 정보 -비밀번호 변경 추가)
 *
 * </pre>
 */

public interface MgrMemberService {
	
	/**
	 * 사용자 정보 조회
	 * @param MgrMemberVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	Map<String,Object> memberSearchGridList(MgrMemberVo paramVo) throws Exception;
	
	
	/**
	 * 사용자 상세 조회
	 * @param MgrMemberVo
	 * @return MgrMemberVo
	 * @throws Exception
	 */
	MgrMemberVo selectMemberData(MgrMemberVo paramVo) throws Exception;
	
	/**
	 * 사용자 권한목록 조회
	 * @param String
	 * @return List<MgrGroupVo>
	 * @throws Exception
	 */
	List<MgrGroupVo> selectGroupList(String memberCd) throws Exception;

	
	
	/**
	 * 사용자 정보저장
	 * @param MgrMemberVo
	 * @return String
	 * @throws Exception
	 */
	String updateMemberData(MgrMemberVo paramVo) throws Exception;
	
	/**
	 * 사용자 접근아이피 목록 조회
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 * @throws SQLException
	 */
	public List<MgrMemberVo> selectAccessIpList(MgrMemberVo paramVo) throws Exception;
	
	/**
	 * 
	 * @Method : updateMgrPassWord
	 * @Description : 사용자 정보- 비밀번호 변경
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public Result updateMgrPassWord(MgrMemberVo paramVo) throws Exception;

	
}
