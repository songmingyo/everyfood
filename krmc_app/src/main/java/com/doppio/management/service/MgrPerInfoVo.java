package com.doppio.management.service;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.CommonSearchVo;
/**
 * 
 * @Class : MgrPerInfoVo.java
 * @Description : 개인정보 및 파일이력 VO
 * @author : JS
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 1. 18.            최 진성        	최초 생성
 *
 * </pre>
 */
public class MgrPerInfoVo extends CommonSearchVo implements Serializable {

	private static final long serialVersionUID = -8078353605041014033L;

	/** 검색조건 조회시작일 */
	private String srchToDy;
	/** 검색조건 조회종료일 */
	private String srchFrDy;
	/** 검색조건 개인정보열람사유코드 */
	private String srchRendDivnCd;
	/** 검색조건 개인정보열람구분코드 */
	private String srchPersBizsCd;
	/**검색조건 파일종류*/
	private String srchfileKindCd;

	/** 이력번호 */
	private String logSeq;
	/** 정보조회일시 */
    private String accessDy;
	/** 열람업무구분 */
	private String persBizsCdNm;
	/** 조회사용자 식별정보 */
	private String persPclInfo;
	/** 개인정보 조회내용 */
	private String persInfo;
	/** 개인정보 열람 사유 */
	private String rendDivnCdNm;
	/** 담당자 */
	private String userNm;
	/** 생성일자 */
	private String regDt;
	/** 개인정보열람사유코드 */
	private String rendDivnCd;
	/** 정보조회 업무구분코드 */
	private String persBizsCd;
	
	/**파일종류*/
	private String fileKindCdNm;
	/**다운로드 내용*/
	private String dwldCmd;
	/**다운로드 파일*/
	private String fileInfo;
	/**실행시간MS*/
	private String executeTime;
	/**접근 아이피*/
	private String resIp;
	/**등록자 아이디*/
	private String regId;
	/**등록일시*/
	private String fileKindCd;
	
	
	public String getSrchfileKindCd() {
		return srchfileKindCd;
	}
	
	public void setSrchfileKindCd(String srchfileKindCd) {
		this.srchfileKindCd = srchfileKindCd;
	}
	public String getFileKindCdNm() {
		return fileKindCdNm;
	}

	public void setFileKindCdNm(String fileKindCdNm) {
		this.fileKindCdNm = fileKindCdNm;
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

	public String getResIp() {
		return resIp;
	}

	public void setResIp(String resIp) {
		this.resIp = resIp;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getFileKindCd() {
		return fileKindCd;
	}

	public void setFileKindCd(String fileKindCd) {
		this.fileKindCd = fileKindCd;
	}


	public String getRegDt() {
		return regDt;
	}
	
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRendDivnCdNm() {
		return rendDivnCdNm;
	}

	public void setRendDivnCdNm(String rendDivnCdNm) {
		this.rendDivnCdNm = rendDivnCdNm;
	}

	public String getSrchToDy() {
		return srchToDy;
	}

	public void setSrchToDy(String srchToDy) {
		this.srchToDy = srchToDy;
	}

	public String getSrchFrDy() {
		return srchFrDy;
	}

	public void setSrchFrDy(String srchFrDy) {
		this.srchFrDy = srchFrDy;
	}

	public String getSrchRendDivnCd() {
		return srchRendDivnCd;
	}

	public void setSrchRendDivnCd(String srchRendDivnCd) {
		this.srchRendDivnCd = srchRendDivnCd;
	}

	public String getSrchPersBizsCd() {
		return srchPersBizsCd;
	}

	public void setSrchPersBizsCd(String srchPersBizsCd) {
		this.srchPersBizsCd = srchPersBizsCd;
	}

	public String getLogSeq() {
		return logSeq;
	}

	public void setLogSeq(String logSeq) {
		this.logSeq = logSeq;
	}
	public String getAccessDy() {
		return accessDy; 
	} 
	public void setAccessDy(String accessDy) {
		this.accessDy = accessDy; 
	}
	 
	public String getPersBizsCdNm() {
		return persBizsCdNm;
	}

	public void setPersBizsCdNm(String persBizsCdNm) {
		this.persBizsCdNm = persBizsCdNm;
	}

	public String getPersPclInfo() {
		return persPclInfo;
	}

	public void setPersPclInfo(String persPclInfo) {
		this.persPclInfo = persPclInfo;
	}

	public String getPersInfo() {
		return persInfo;
	}

	public void setPersInfo(String persInfo) {
		this.persInfo = persInfo;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getRendDivnCd() {
		return rendDivnCd;
	}

	public void setRendDivnCd(String rendDivnCd) {
		this.rendDivnCd = rendDivnCd;
	}

	public String getPersBizsCd() {
		return persBizsCd;
	}

	public void setPersBizsCd(String persBizsCd) {
		this.persBizsCd = persBizsCd;
	}
	public String getaccessDyFmt() {
		String rtnVal = this.accessDy;
		if(!StringUtils.isEmpty(rtnVal))
			rtnVal = DateUtil.convertDate(rtnVal, "yyyyMMdd", "yyyy-MM-dd");
		return rtnVal;
	}
	
	public String getRegDateFmt() {
		String rtnVal = this.regDt;
		if(!StringUtils.isEmpty(rtnVal))
			rtnVal = DateUtil.convertDate(rtnVal, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
		return rtnVal;
	}
	
}
