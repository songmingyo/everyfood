package com.doppio.management.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrGroupVo;
import com.doppio.management.service.MgrMemberVo;

@Repository(value = "mgrMemberMapper")
public interface MgrMemberMapper {

	/**
	 * 사용자 정보 조회  
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 */
	public List<MgrMemberVo> selectMemberList(MgrMemberVo paramVo);
	
			
	/**
	 * 사용자 상세 조회
	 * @param MgrMemberVo
	 * @return MgrMemberVo
	 */
	public MgrMemberVo selectMemberData(MgrMemberVo paramVo);
	
	/**
	 * 사용자 그룹권한 조회
	 * @param String
	 * @return List<MgrGroupVo>
	 */
	public List<MgrGroupVo> selectMemberGroupList(String memberCd);
	
	
	
	/**
	 * 사용자 그룹삭제
	 * @param String
	 * @return Integer
	 */
	public int deleteMemberAuth(String memberCd);
	
	
	
	/**
	 * 사용자 등록/수정
	 * @param MgrMemberVo
	 * @return Integer
	 */
	public int updateMergeMemberMergeData(MgrMemberVo paramVo);
	
	
	
	/**
	 * 사용자그룹등록
	 * @param String MgrMemberVo
	 * @return Integer
	 */
	public int insertMemberAuth(MgrMemberVo paramVo);
	

	/**
	 * 사용자 접근아이피 목록 조회
	 * @param MgrMemberVo
	 * @return List<MgrMemberVo>
	 * @throws SQLException
	 */
	public List<MgrMemberVo> selectAccessIpList(MgrMemberVo searchVo);
	
	/**
	 * 
	 * @Method : updateMgrPassWord
	 * @Description : 사용자 정보- 비밀번호 변경
	 * @param paramVo
	 * @return
	 */
	public int updateMgrPassWord(MgrMemberVo paramVo);
}
