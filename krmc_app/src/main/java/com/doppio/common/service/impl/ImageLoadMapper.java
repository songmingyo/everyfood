package com.doppio.common.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.AttachFileVo;

@Repository(value = "imageLoadMapper")
public interface ImageLoadMapper {

	
	/**
	 * 파일 정보 조회 공통 
	 * @param TecDocSign
	 * @return AttachFileVo
	 */
	public AttachFileVo selectCommonFileInfo(AttachFileVo paramVo);
	
	/**
	 * 파일 정보 조회 공통 List 
	 * @param TecDocSign
	 * @return List<AttachFileVo>
	 */
	public List<AttachFileVo> selectCommonFileInfoList(AttachFileVo paramVo);
		
	
}
