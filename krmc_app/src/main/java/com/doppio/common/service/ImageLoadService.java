package com.doppio.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;

import com.doppio.common.model.AttachFileVo;

public interface ImageLoadService {
	
	/**
	 *   파일 정보 조회 공통
	 * @param TecDocSign
	 * @return AttachFileVo
	 * @throws DataAccessException
	 */
	 AttachFileVo  selectCommonFileInfo(AttachFileVo attachFileVO, HttpServletRequest request) throws DataAccessException;

	 
	 /**
	 *   파일 정보 조회 List 공통
	 * @param TecDocSign
	 * @return List<AttachFileVo>
	 * @throws DataAccessException
	 */
	 List<AttachFileVo>  selectCommonFileInfoList(AttachFileVo attachFileVO, HttpServletRequest request) throws DataAccessException;
}
