package com.doppio.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;

public interface FileManagerService {
	
	/**
	 *  파일목록 조회 
	 * @param AttachFileVo
	 * @return AttachFileVo
	 * @throws Exception
	 */
	 AttachFileVo fileDownLoadData(AttachFileVo AttachFileVo, HttpServletRequest request) throws Exception;

	 
    /**
	 * AttachFile 파일 List 조회 
	 * @param  String 
	 * @return List<AttachFileVo>
	 * @throws Exception
	 */
	 List<AttachFileVo> selectAttachFileList(String atchFileId) throws Exception;

	 
	/**
	 *  업로드파일 db정보 등록
	 * @param List<AttachFileVo>
	 * @return String
	 * @throws Exception
	 */
	 String insertFileData(List<AttachFileVo> fileList) throws Exception;
	 
	 
	/**
	 * AttachFileId 파일 갯수
	 * @param String
	 * @return INTEGER
	 * @throws Exception
     */
	 int selectAttachFileListCount(String atchFileId) throws Exception;
	 
	 
	 
	/**
	 * 파일 선택또는전체  삭제(MERGE INTO)
	 * 
	 * @param AttachFileVO
	 * @return
	 * @throws Exception
	 */
	 void deleteUploadFile(AttachFileVo AttachFileVo) throws Exception;
	 
	
	 
	/**
	 * 시퀀스 자동 증가되면서 등록
	 * @param List<AttachFileVo>
	 * @param atchFileId
	 * @param INTEGER
	 * @return List<AttachFileVo>
	 * @throws Exception
	 */
	List<AttachFileVo> insertFileRaiseSeq(List<AttachFileVo> list, String atchFileId, int limitCount) throws Exception;
	
	 /**
	  * 
	  * @Method : selectAtchFileIdsFileList
	  * @Description : atchFileIds로 조회
	  * @param paramVo
	  * @return
	  * @throws Exception
	  */
	List<AttachFileVo> selectAtchFileIdsFileList(AttachFileVo paramVo) throws Exception;
	
	/**
	 * 
	 * @Method : updateFilePath
	 * @Description : 파일 위치 update
	 * @param fileInfo
	 * @return result
	 * @throws Exception
	 */
	Result updateFilePath(AttachFileVo fileInfo) throws Exception;
}
