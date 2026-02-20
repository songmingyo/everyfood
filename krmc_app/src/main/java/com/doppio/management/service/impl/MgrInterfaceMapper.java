package com.doppio.management.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrInterfaceVo;

@Repository(value = "mgrInterfaceMapper")
public interface MgrInterfaceMapper {

	/**
	 * 작업 내역 조회
	 * @param mgrInterfaceVo
	 * @return
	 * @throws Exception
	 */
	public List<MgrInterfaceVo> selectIfMasterList (MgrInterfaceVo mgrInterfaceVo) throws Exception;
	
	/**
	 * 수작업 리스트 조회
	 * @param mgrInterfaceVo
	 * @return
	 * @throws Exception
	 */
	public List<MgrInterfaceVo> selectIfSubList (MgrInterfaceVo mgrInterfaceVo) throws Exception;
}
