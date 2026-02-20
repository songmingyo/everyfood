package com.doppio.common.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.FileManagerService;

import ch.qos.logback.classic.Logger;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

@Service("fileManagerService")
public class FileManagerServiceImpl implements FileManagerService {

	@Autowired
	FileManagerMapper fileManagerMapper;
	
	
	/**
     * AttachFile 파일 Data 조회
     */
	@Override
	public AttachFileVo fileDownLoadData(AttachFileVo AttachFileVo, HttpServletRequest request)	throws Exception{
		return fileManagerMapper.selectAttachFileDataList(AttachFileVo);
	}
	
	
	
	/**
	 * 동일한 atchFileId 의 파일목록 조회
	 * @param String
	 * @return  List<AttachFileVo>
	 */
	@Override
	public List<AttachFileVo> selectAttachFileList(String atchFileId) throws Exception {
		return fileManagerMapper.selectAttachFileList(atchFileId);
	}
	
	

	/**
     * 업로드파일 db정보 등록
     */
    @Override
    public String insertFileData(List<AttachFileVo> fileList) throws Exception {
    
    	AttachFileVo attachFileVO = (AttachFileVo)fileList.get(0);
		String atchFileId = "";
		
		if(!StringUtils.isEmpty(attachFileVO.getAtchFileId()))
			atchFileId = attachFileVO.getAtchFileId();
		else atchFileId = DateUtil.getTimeStamp();

		Iterator<AttachFileVo> iter = fileList.iterator();
		while (iter.hasNext()) {
			attachFileVO = (AttachFileVo)iter.next();
			attachFileVO.setAtchFileId(atchFileId);
			fileManagerMapper.insertFileData(attachFileVO);
		}
		
		return atchFileId;
    }
	
    
    /**
     * AttachFileId 파일 갯수
     */
    @Override
    public int selectAttachFileListCount(String atchFileId) throws Exception{
    	return fileManagerMapper.selectAttachFileListCount(atchFileId);
    }
	
    
    /**
     * 파일 선택또는전체  삭제(MERGE INTO)
     */
    @Override
    public void deleteUploadFile(AttachFileVo AttachFileVo) throws Exception {
    	fileManagerMapper.deleteFileData(AttachFileVo);
    }
 
    
    /**
     * 시퀀스 자동 증가되면서 등록
     */
    @Override
    public List<AttachFileVo> insertFileRaiseSeq(List<AttachFileVo> list, String atchFileId, int limitCount) throws Exception {

    	List<AttachFileVo> dataList = new ArrayList<AttachFileVo>();
    	int existCount = fileManagerMapper.selectAttachFileListCount(atchFileId);
    	int exceptLimit = limitCount - existCount;
    	if(exceptLimit < 0) exceptLimit = 0;
    	String seq ="";
		for(AttachFileVo vo:list){
			vo.setAtchFileId(atchFileId);
			if(exceptLimit > 0){
				//파일사이즈가 0인경우 insert 안함
				if(!vo.getFileSize().equals("0")) {
					seq = Integer.toString(fileManagerMapper.insertFileRaiseSeq(vo));
					if("".equals(seq)) {
						AttachFileVo seqVo = fileManagerMapper.selectFileSeq(vo);
						vo.setSeq(StringUtils.defaultString(seqVo.getSeq()));
					}
					
					
				}else {
					vo.setError("");
				}
			}else{
				vo.setError("제한된 첨부파일수를 초과하였습니다.");
			}
			dataList.add(vo);
			exceptLimit--;
		}
		return dataList;
    
    }
    
    /**
     * atchFileIds로 조회
     */
    @Override
    public List<AttachFileVo> selectAtchFileIdsFileList(AttachFileVo paramVo) throws Exception{
    	return fileManagerMapper.selectAtchFileIdsFileList(paramVo);
    }
    
    /**
     * file path update
     */
    @Override
    public Result updateFilePath(AttachFileVo fileInfo) throws Exception{
    	Result result = new Result();
    	result.setMsgCd(Result.FAIL);
    	int cnt =0;
    	cnt = fileManagerMapper.updateFilePath(fileInfo);
    	
    	if(cnt>0) {
    		result.setMsgCd(Result.SUCCESS);
    	}else {
    		result.setMessage("filePath update fail");
    	}
    			
    	return result;
    }
    
  
}
