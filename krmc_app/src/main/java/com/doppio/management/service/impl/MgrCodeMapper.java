package com.doppio.management.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrCodeVo;

@Repository(value = "mgrCodeMapper")
public interface MgrCodeMapper {

	
	/**
	 * COMMON CODE MASTER LIST  
	 * @param MgrCodeVo
	 * @return List<MgrCodeVo>
	 */
	public List<MgrCodeVo> selectCodeMasterList(MgrCodeVo paramVo);
	 
	/**
	 * COMMON CODE SUB LIST  
	 * @param MgrCodeVo
	 * @return List<MgrCodeVo>
	 */
	public List<MgrCodeVo> selectCodeSubList(MgrCodeVo paramVo);
	 
	/**
	 * 코드 중복체크
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public String selectMasterCodeCount(MgrCodeVo paramVo);
	
	/**
	 * 분류코드가 변경되었을경우 세부코드의 분류코드 전체 UPDATE
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int upateCodeSubAll(MgrCodeVo paramVo);
	
	/**
	 * 분류 코드 수정	
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int updateCodeMaster(MgrCodeVo paramVo);
				
	/**
	 * 분류 코드 저장(신규등록)	
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int insertCodeMaster(MgrCodeVo paramVo);
	
	/**
	 * 마스터코드삭제
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int deleteCodeMaster(MgrCodeVo paramVo);
	
	/**
	 * 마스터코드의 세부코드 전체삭제
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int deleteCodeSub(MgrCodeVo paramVo);
	
	/**
	 * 서브코드 중복체크
	 * @param MgrCodeVo
	 * @return String
	 */
	public String selectSubCodeCount(MgrCodeVo paramVo);
			
	/**
	 * 서브 코드 수정
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int updateCodeSub(MgrCodeVo paramVo);
	
	/**
	 * 서브 코드 INERT
	 * @param MgrCodeVo
	 * @return Integer
	 */
	public int insertCodeSub(MgrCodeVo paramVo);
			

}
