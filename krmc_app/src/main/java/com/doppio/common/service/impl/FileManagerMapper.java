package com.doppio.common.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.AttachFileVo;

@Repository(value = "fileManagerMapper")
public interface FileManagerMapper {

	
	/**
	 * 파일 정보 조회 공통 
	 * @param TecDocSign
	 * @return AttachFileVo
	 */
	AttachFileVo selectAttachFileDataList(AttachFileVo paramVo);
	
	
	/**
	 * 동일한 atchFileId 의 파일목록 조회
	 * @param String
	 * @return  List<AttachFileVo>
	 */
	List<AttachFileVo> selectAttachFileList(String atchFileId);
	
	
	/**
	 * 업로드 파일 db정보  등록 
	 * @param TecDocSign
	 * @return Integer
	 */
	int insertFileData(AttachFileVo paramVo);
	
	
	
	/**
	  * 업로드 파일 db정보  삭제 
	  * @param String
	  * @return
	  */
	void deleteAttachFileData(String atchFileId);


	
	/**
	  * 업로드 파일 db정보  삭제 
	  * @param TecDocSign
	  * @return
	  */
	void deleteFileData(AttachFileVo paramVo);
	
	
	
	/**
	 * 동일한 atchFileId 의 파일목록 갯수 조회
	 * @param String
	 * @return INTEGER
	 */
	int selectAttachFileListCount(String atchFileId);
	
	
	/**
	 * 파일정보 등록시에 SEQ 자동 증감
	 * @param AttachFileVo
	 * @return String
	 */
	int insertFileRaiseSeq(AttachFileVo AttachFileVo);
	

	/**
	 * seq 조회
	 * @param AttachFileVo
	 * @return String
	 */
	AttachFileVo selectFileSeq(AttachFileVo AttachFileVo);
	
	
	/**
	 * 삭제 파일 조회
	 * @param AttachFileVo
	 * @return List<AttachFileVo>
	 */
	List<AttachFileVo> selectDeleteAttachFileDataList(AttachFileVo paramVo);
	
	/**
	 * 
	 * @Method : selectAtchFileIdsFileList
	 * @Description : atchFileId 여러개로 파일 조회
	 * @param paramVo
	 * @return
	 */
	List<AttachFileVo> selectAtchFileIdsFileList(AttachFileVo paramVo);
	
	/**
	 * 
	 * @Method : updateFilePath
	 * @Description : filePath update 
	 * @param paramVo
	 * @return int
	 */
	int updateFilePath(AttachFileVo paramVo);
}
