package com.doppio.management.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrAuthVo;
import com.doppio.management.service.MgrGroupVo;
import com.doppio.management.service.MgrMemberVo;

@Repository(value = "mgrGroupMapper")
public interface MgrGroupMapper {

	
	/**
	 * 그룹정보 LIST  
	 * @param MgrGroupVo
	 * @return List<MgrGroupVo>
	 */
	public List<MgrGroupVo> selectGroupList(MgrGroupVo paramVo);
	
	/**
	 * 그룹상세정보 LIST  
	 * @param MgrGroupVo
	 * @return MgrGroupVo
	 */
	public MgrGroupVo selectGroupData(MgrGroupVo paramVo);
	
	
	/**
	 * 그룹아이디 중복체크
	 * @param String
	 * @return Integer
	 */
	public int selectGroupIdValidation(String GroupId);
	
	
	/**
	 * 그룹정보 저장
	 * @param MgrGroupVo
	 * @return Integer
	 */
	public int mergeGroupData(MgrGroupVo paramVo);
	
	
	/**
	 * 그룹정보 삭제
	 * @param MgrGroupVo
	 * @return Integer
	 */
	public int deleteGroup(MgrGroupVo paramVo);
	
	
	/**
	 * 그룹정보 권한 삭제
	 * @param MgrGroupVo
	 * @return Integer
	 */
	public int deleteGroupAuth(MgrGroupVo paramVo);
	
	
	/**
	 * 사용자 그룹 메핑정보 삭제
	 * @param MgrGroupVo
	 * @return Integer
	 */
	public int deleteMemberAuth(MgrGroupVo paramVo);
	
	
	/**
	 * 그룹에 매핑된 사용자 List 조회
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 */
	public List<MgrMemberVo> selectGroupAuthMemberList(MgrMemberVo paramVo);
	
	
	/**
	 * 매핑된 그룹외 사용자 조회
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 */
	public List<MgrMemberVo> selectGroupMemberList(MgrMemberVo paramVo);

	
	/**
	 * 사용자 그룹권한 등록
	 * @param MgrAuthVo
	 * @return Integer
	 */
	public int insertGroupMemberAuth(MgrAuthVo paramVo);
	
	
	/**
	 * 사용자 그룹권한 제거
	 * @param MgrAuthVo
	 * @return Integer
	 */
	public int deleteGroupMemberAuth(MgrAuthVo paramVo);
	
	
	/**
	 * 그룹별 메뉴권한 리스트 조회
	 * @param MgrAuthVo
	 * @return List<MgrAuthVo>
	 */
	public List<MgrAuthVo> selectAuthMenuList(MgrAuthVo paramVo);
	
	
	/**
	 * 그룹메뉴권한 INSERT
	 * @param MgrAuthVo
	 * @return Integer
	 */
	public int insertAuthMenu(MgrAuthVo paramVo);
	
	
	/**
	 * 그룹메뉴권한 UPDATE
	 * @param MgrAuthVo
	 * @return Integer
	 */
	public int updateAuthMenu(MgrAuthVo paramVo);
	
	
	/**
	 * 그룹메뉴권한 DELETE
	 * @param MgrAuthVo
	 * @return Integer
	 */
	public int deleteAuthMenu(MgrAuthVo paramVo);
	
	
}
