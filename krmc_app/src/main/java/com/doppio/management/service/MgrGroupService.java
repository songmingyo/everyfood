package com.doppio.management.service;

import java.util.List;

public interface MgrGroupService {
	

	/**
	 * 그룹정보 조회
	 * @param MgrGroupVo
	 * @return List<MgrGroupVo>
	 * @throws Exception
	 */
	List<MgrGroupVo> selectGroupList(MgrGroupVo paramVo) throws Exception;
	
	
	/**
	 * 그룹 상세 조회
	 * @param MgrGroupVo
	 * @return List<MgrGroupVo>
	 * @throws Exception
	 */
	MgrGroupVo selectGroupData(MgrGroupVo paramVo) throws Exception;

	
	 
	/**
	 * 그룹 정보 저장
	 * @param MgrGroupVo
	 * @return String
	 * @throws Exception
	 */ 
	String updateGroupData(MgrGroupVo paramVo) throws Exception;
	
	
	/**
	 * 그룹 정보 삭제
	 * @param MgrGroupVo
	 * @return String
	 * @throws Exception
	 */ 
	String deleteGroupData(MgrGroupVo paramVo) throws Exception;
	
	
	/**
	 * 그룹에 매핑된 사용자 List 조회
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 * @throws Exception
	 */
	List<MgrMemberVo> selectGroupAuthMemberList(MgrMemberVo paramVo) throws Exception;
	
	
	/**
	 * 매핑된 그룹외 사용자 조회
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 * @throws Exception
	 */
	List<MgrMemberVo> selectGroupMemberList(MgrMemberVo paramVo) throws Exception;
	
	
	/**
	 * 사용자 그룹매핑 추가/삭제 
	 * @param MgrAuthVo
	 * @return String
	 * @throws Exception 
	 */
	String updateGroupAuthSave(MgrAuthVo paramVo) throws Exception; 
	
	
	/**
	 * 그룹별 메뉴권한 리스트 조회
	 * @param MgrAuthVo
	 * @return List<MgrAuthVo>
	 * @throws Exception
	 */
	List<MgrAuthVo> selectAuthMenuList(MgrAuthVo paramVo) throws Exception;
	

	
	/**
	 * 그룹권한 저장
	 * @param MgrAuthProcessVo
	 * @return String
	 * @throws Exception
	 */ 
	String updateAuthInfoList(MgrAuthProcessVo paramVo) throws Exception;
	
	
	
}
