package com.doppio.management.service;

import java.util.List;

public interface MgrInterfaceService {

	/**
	 * 작업 내역 조회
	 * @param paramVo
	 * @return
	 * @throws Exception 
	 */
	List<MgrInterfaceVo> selectIfMasterList(MgrInterfaceVo paramVo) throws Exception;
	
	/**
	 * 수작업 리스트 조회
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	List<MgrInterfaceVo> selectIfSubList(MgrInterfaceVo paramVo) throws Exception;
}
