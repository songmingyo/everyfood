package com.doppio.common.service.impl;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.service.ImageLoadService;

@Service("imageLoadService")
public class ImageLoadServiceImpl implements ImageLoadService {

	@Autowired
	private ImageLoadMapper imageLoadMapper;

	
	/**
	 * AttachFile 파일 Data 조회 : 공통
	 */
	@Override
	public AttachFileVo selectCommonFileInfo(AttachFileVo attachFileVO, HttpServletRequest request)
			throws DataAccessException {
		return imageLoadMapper.selectCommonFileInfo(attachFileVO); 
	}
	
  
	
	/**
	 * AttachFile 파일 Data List 조회 : 공통
	 */
	@Override
	public List<AttachFileVo> selectCommonFileInfoList(AttachFileVo attachFileVO, HttpServletRequest request)
			throws DataAccessException {
		return imageLoadMapper.selectCommonFileInfoList(attachFileVO); 
	}
	
	
	
}
