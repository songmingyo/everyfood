package com.doppio.common.model;

import java.io.Serializable;
import java.util.List;
/**
 * @author hdh
 * @Description :
 * @Class : Result
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2021. 12. 07	추용호		(추가) 파일부가정보
 * </pre>
 * @version : 1.0
 */
public class AttachFileVo implements Serializable {

	private static final long serialVersionUID = 7971677840665759895L;
	
	/**파일아이디*/
	private String atchFileId		;
	/**파일순번*/
	private String seq				;
	/**원본파일명칭*/
	private String orgFileNm		;
	/**저장파일명칭*/
	private String saveFileNm	  	;
	/**파일종류코드*/
	private String fileKindCd	  	;
	/**파일사이즈 byte*/
	private String fileSize			;
	/**파일사이즈 KB*/
	private String fileSizeKb		;
	/**파일경로명*/
	private String filePathNm	  	;
	/**파일확장자명*/
	private String fileTypeNm	  	;
	/** 파일부가정보 */
	private String anxInfoNm;
	/**등록일시*/
	private String regDt			;
	/**등록자아이디*/
	private String regId			;
	/**수정일시*/
	private String modDt			;
	/**수정자아이디*/
	private String modId			;
	/**작업자아이디*/
	private String workId			;

	/** 추가 변수 */

	/** 파일업로드, 다운로드시 에러 */
	private String error;

	/** 파일업로드, 다운로드시 에러 코드 */
	private String errorCode;

	/** DOC_SEQ(TEC_DOCINFO) */
	private String docSeq;

	/** FILE_SEQ(TEC_DOCFILE) */
	private String fileSeq;

	/** useYn */
	private String useYn;
	
	/** seq List */
	private List<Integer> seqList;
	
	
	/** 다운로드 로그 정보 */
	
	/** 로그순번*/
	private String logSeq			;
	/**다운로드내용*/
	private String dwldCmd			;
	/**다운로드파일정보*/
	private String fileInfo			;
	/**EXECUTE_TIME*/
	private String executeTime		;
	/**접근일자*/
	private String accessDy			;
	/**RES_IP*/
	private String resIp			;

	
	private String fileDesc;
	
	private List<String> atchFileIds;
	
	public List<Integer> getSeqList() {
		return seqList;
	}
	public void setSeqList(List<Integer> seqList) {
		this.seqList = seqList;
	}
	public String getDocSeq() {
		return docSeq;
	}
	public void setDocSeq(String docSeq) {
		this.docSeq = docSeq;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getOrgFileNm() {
		return orgFileNm;
	}
	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}
	public String getSaveFileNm() {
		return saveFileNm;
	}
	public void setSaveFileNm(String saveFileNm) {
		this.saveFileNm = saveFileNm;
	}
	public String getFileKindCd() {
		return fileKindCd;
	}
	public void setFileKindCd(String fileKindCd) {
		this.fileKindCd = fileKindCd;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileSizeKb() {
		return fileSizeKb;
	}
	public void setFileSizeKb(String fileSizeKb) {
		this.fileSizeKb = fileSizeKb;
	}
	public String getFilePathNm() {
		return filePathNm;
	}
	public void setFilePathNm(String filePathNm) {
		this.filePathNm = filePathNm;
	}
	public String getFileTypeNm() {
		return fileTypeNm;
	}
	public void setFileTypeNm(String fileTypeNm) {
		this.fileTypeNm = fileTypeNm;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getError() {
		if(error == null) error = "";
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(String fileSeq) {
		this.fileSeq = fileSeq;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getAnxInfoNm() {
		return anxInfoNm;
	}
	public void setAnxInfoNm(String anxInfoNm) {
		this.anxInfoNm = anxInfoNm;
	}
	public String getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}
	
	public String getLogSeq() {
		return logSeq;
	}
	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}
	public String getDwldCmd() {
		return dwldCmd;
	}
	public void setDwldCmd(String dwldCmd) {
		this.dwldCmd = dwldCmd;
	}
	public String getFileInfo() {
		return fileInfo;
	}
	public void setFileInfo(String fileInfo) {
		this.fileInfo = fileInfo;
	}
	public String getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}
	public String getAccessDy() {
		return accessDy;
	}
	public void setAccessDy(String accessDy) {
		this.accessDy = accessDy;
	}
	public String getResIp() {
		return resIp;
	}
	public void setResIp(String resIp) {
		this.resIp = resIp;
	}
	public List<String> getAtchFileIds() {
		return atchFileIds;
	}
	public void setAtchFileIds(List<String> atchFileIds) {
		this.atchFileIds = atchFileIds;
	}
	
	
}
