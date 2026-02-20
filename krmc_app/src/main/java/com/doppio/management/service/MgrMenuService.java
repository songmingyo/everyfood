package com.doppio.management.service;

import java.util.List;

public interface MgrMenuService {
	
	/**
	 * 메뉴 조회
	 * @param MgrMenuVo
	 * @return List<MgrMenuVo>
	 * @throws Exception
	 */
	List<MgrMenuVo> selectMenuList(MgrMenuVo paramVo) throws Exception;
	
	
	/**
	 * 메뉴 등록
	 * @param MgrMenuVo
	 * @return String
	 * @throws Exception
	 */
	String updateMenuData(MgrMenuVo paramVo) throws Exception;
	
	
	/**
	 * 메뉴 등록
	 * @param MgrMenuVo
	 * @return String
	 * @throws Exception
	 */
	String deleteMenuData(MgrMenuVo paramVo) throws Exception;
}
