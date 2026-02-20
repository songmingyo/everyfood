package com.doppio.management.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.CommonSearchVo;

/**
 * @Class : MgrTermsVo
 * @Description : 동의정보 Vo
 * @author : 김동호
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *  수정일				수정자			 수정내용
 *  ----------------	------------	---------------------------
 *  2022. 4. 13.		김동호			최초 생성
 *
 * </pre>
 */
public class MgrTermsVo extends CommonSearchVo implements Serializable {

	private static final long serialVersionUID = -2938963406066101898L;
	
	/**동의인증고유번호*/
	private String agreeRegNo;
	
	/**정보동의구분코드*/
	private String termsDivnCd;
	private List<String> termsDivnCds;
	
	/**순번*/
	private String seq;
	
	/**사용자고유번호*/
	private String termsPclNo;
	
	/**정보동의자식별값*/
	private String termsPclIdx;
	
	/**동의인증여부*/
	private String agreeYn;
	
	/**시스템명*/
	private String sysNm;
	
	/**정보동의버전정보*/
	private String termsVersionInfo;
	
	/**정보동의서*/
	private String termsContent;
	
	/**등록일시*/
	private String regDt;
	
	/**등록자아이디*/
	private String regId;
	
	/**약관명*/
	private String codeNm;

	/**동의한 약관 정보*/
	private String joinTermsMapp;
	
	/**동의한 약관 정보*/
	private String[] policyAgree;
	
	/**회원가입 구분*/
	private String joinType;
	
	private String agreeAllYn;
	
	private String userId;
	
	private String userPw;
	
	private String masterId;
	
	/** 인증유효시간(초) */
	private String validSec;
	
	/** 약관구분코드[D: 기기인증, T:약관동의] */
	private String gbnType;
	
	/** 확장코드 */
	private String extent01;
	private String extent02;
	private String extent03;
	
	private List<String> extentArr01;
	private List<String> extentArr02;
	private List<String> extentArr03;
	
	
	public String getTermsDivnCd() {
		return termsDivnCd;
	}

	public void setTermsDivnCd(String termsDivnCd) {
		this.termsDivnCd = termsDivnCd;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getTermsVersionInfo() {
		return termsVersionInfo;
	}

	public void setTermsVersionInfo(String termsVersionInfo) {
		this.termsVersionInfo = termsVersionInfo;
	}

	public String getTermsContent() {
		return termsContent;
	}

	public void setTermsContent(String termsContent) {
		this.termsContent = termsContent;
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
	
	public String regDtFmt() {
		return DateUtil.convertDateLocale(regDt,"yyyyMMdd","yyyy/MM/dd");
	}

	public String getCodeNm() {
		return codeNm;
	}

	public void setCodeNm(String codeNm) {
		this.codeNm = codeNm;
	}

	public String getJoinTermsMapp() {
		return joinTermsMapp;
	}

	public void setJoinTermsMapp(String joinTermsMapp) {
		this.joinTermsMapp = joinTermsMapp;
	}

	public String getJoinType() {
		return joinType;
	}

	public void setJoinType(String joinType) {
		this.joinType = joinType;
	}

	public String[] getPolicyAgree() {
		return policyAgree;
	}

	public void setPolicyAgree(String[] policyAgree) {
		this.policyAgree = policyAgree;
	}

	public String getAgreeRegNo() {
		return agreeRegNo;
	}

	public void setAgreeRegNo(String agreeRegNo) {
		this.agreeRegNo = agreeRegNo;
	}

	public String getTermsPclNo() {
		return termsPclNo;
	}

	public void setTermsPclNo(String termsPclNo) {
		this.termsPclNo = termsPclNo;
	}

	public String getTermsPclIdx() {
		return termsPclIdx;
	}

	public void setTermsPclIdx(String termsPclIdx) {
		this.termsPclIdx = termsPclIdx;
	}

	public String getAgreeYn() {
		return agreeYn;
	}

	public void setAgreeYn(String agreeYn) {
		this.agreeYn = agreeYn;
	}

	public String getSysNm() {
		return sysNm;
	}

	public void setSysNm(String sysNm) {
		this.sysNm = sysNm;
	}

	public String getAgreeAllYn() {
		return agreeAllYn;
	}

	public void setAgreeAllYn(String agreeAllYn) {
		this.agreeAllYn = agreeAllYn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getValidSec() {
		return validSec;
	}

	public void setValidSec(String validSec) {
		this.validSec = validSec;
	}

	public String getGbnType() {
		return gbnType;
	}

	public void setGbnType(String gbnType) {
		this.gbnType = gbnType;
	}

	public List<String> getTermsDivnCds() {
		return termsDivnCds;
	}

	public void setTermsDivnCds(List<String> termsDivnCds) {
		this.termsDivnCds = termsDivnCds;
	}

	public String getExtent01() {
		return extent01;
	}

	public void setExtent01(String extent01) {
		this.extent01 = extent01;
	}

	public String getExtent02() {
		return extent02;
	}

	public void setExtent02(String extent02) {
		this.extent02 = extent02;
	}

	public String getExtent03() {
		return extent03;
	}

	public void setExtent03(String extent03) {
		this.extent03 = extent03;
	}

	public List<String> getExtentArr01() {
		return extentArr01;
	}

	public void setExtentArr01(List<String> extentArr01) {
		this.extentArr01 = extentArr01;
	}

	public List<String> getExtentArr02() {
		return extentArr02;
	}

	public void setExtentArr02(List<String> extentArr02) {
		this.extentArr02 = extentArr02;
	}

	public List<String> getExtentArr03() {
		return extentArr03;
	}

	public void setExtentArr03(List<String> extentArr03) {
		this.extentArr03 = extentArr03;
	}
	
}