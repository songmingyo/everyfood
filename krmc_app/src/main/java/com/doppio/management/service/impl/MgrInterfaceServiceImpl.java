package com.doppio.management.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doppio.management.service.MgrInterfaceService;
import com.doppio.management.service.MgrInterfaceVo;

@Service("mgrInterfaceService")
public class MgrInterfaceServiceImpl implements MgrInterfaceService {

	private static final Logger logger = LoggerFactory.getLogger(MgrInterfaceServiceImpl.class);
	
	@Autowired
	MgrInterfaceMapper mgrInterfaceMapper;
	
	/**
	 * 작업 내역 조회
	 */
	@Override
	public List<MgrInterfaceVo> selectIfMasterList (MgrInterfaceVo paramVo) throws Exception {
		return mgrInterfaceMapper.selectIfMasterList(paramVo);
	}
	
	/**
	 * 수작업 리스트 조회
	 */
	@Override
	public List<MgrInterfaceVo> selectIfSubList (MgrInterfaceVo paramVo) throws Exception {
		return mgrInterfaceMapper.selectIfSubList(paramVo);
	}
}
