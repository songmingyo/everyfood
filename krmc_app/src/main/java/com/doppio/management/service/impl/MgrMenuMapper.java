package com.doppio.management.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrMenuVo;

@Repository(value = "mgrMenuMapper")
public interface MgrMenuMapper {


	/**
	 * 메뉴 정보 조회  
	 * @param MgrMenuVo
	 * @return List<MgrMenuVo>
	 */
	public List<MgrMenuVo> selectMenuList(MgrMenuVo paramVo);
	
	
	/**
	 * 대분류 메뉴 저장(MERGE) 
	 * @param MgrMenuVo
	 * @return Integer
	 */
	public int updateMergeMenuLagData(MgrMenuVo paramVo);
	
	
	/**
	 * 중,소분류 메뉴 저장(MERGE)  
	 * @param MgrMenuVo
	 * @return Integer
	 */
	public int updateMergeMenuMidData(MgrMenuVo paramVo);
	
	
	/**
	 * 메뉴 삭제
	 * @param MgrMenuVo
	 * @return Integer
	 */
	public int deleteMenuData(MgrMenuVo paramVo);
	
	
	/**
	 * 메뉴권한 삭제
	 * @param MgrMenuVo
	 * @return Integer
	 */
	public int deleteMenuAuthData(MgrMenuVo paramVo);
	
}
