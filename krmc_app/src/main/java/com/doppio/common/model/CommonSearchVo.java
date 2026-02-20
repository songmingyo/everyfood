package com.doppio.common.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

/**
 * 
 * @Class : CommonSearchVo.java
 * @Description : [공통] 검색조건 Vo
 * @author : 윤지아
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2022. 12. 29.            윤지아        	최초 생성
 *
 * </pre>
 */
public class CommonSearchVo extends PageN implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 95844058490812990L;
	
	/** 작업자 정보 */
	private String regId;		//등록자
	private String regDt;		//등록일자
	private String regNm;		//등록자명
	private String regFullId;	//등록자(아이디)
	private String modId;		//수정자
	private String modDt;		//수정일자
	private String modNm;		//수정자명
	private String modeFullId;	//수정자(아이디)
	private String workId;		//작업자
	
	
	/** 검색 조건 */
	private String srchCompCd;	//업체코드
	private String srchLvl;		//상품분류계층
	private String srchSaleChnnCd; //판매채널
	private String srchMainProdCatCd;//대표상품카테고리
	private String srchMainProdlCatCd;
	private String srchMainProdmCatCd;
	private String srchLCatNmKr;	// 대분류명
	private String srchMCatNmKr;	// 중분류명
	private String srchKsicLarCatCd;	// 표준산업분류 대분류코드
	private String srchKsicLarCatCdNm;	// 표준산업분류 대분류코드명
	private String srchKsicMedCatCd;	// 표준산업분류 중분류코드
	private String srchKsicMedCatCdNm;	// 표준산업분류 중분류코드명
	private String srchAppliNo;			// 입점제안 고유번호
	
	private String srchAppliStDy;		//제안신청시작일
	private String srchAppliEdDy;		//제안신청종료일
	private String srchAppliStatCd;		//제안진행상태
	private String srchMainProdNm;		//대표상품명
	
	private String srchProdSltCmitYn;	// 상선위대상 여부
	private String srchTeamCd;			// 관리팀코드
	private String srchTeamNm;			// 관리팀명
	
	private List<String> srchAppliNos;	//제안번호 list
	private String srchMemberCd;		//사용자고유번호
	
	private String srchTeamDivnCd;		//팀유형구분코드[5014]
	private String srchWorkType;		//업무유형(custom)
	private String srchUserType;		//사용자유형[9001]
	private String srchCompNmKr;		//업체명(kr)
	private String srchAcceptMemberNm;	//접수담당자명
	private String srchCompFileDivnCd;	//회사 업로드 파일구분[3003]
	private String srchGenDivnCd;
	
	private String srchUserNm;			//사용자명
	private String srchUserId;			//사용자아이디
	private String srchCatNmKr;			//카테고리명(대분류/중분류 구분x)
	
	private String srchUseYn;			//사용여부
	
	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRegDtFmt() {
		return DateUtil.convertDateFmt(regDt, "", "");
	}
	
	public String getRegDtLocale() {
		return DateUtil.convertDateLocale(regDt);
	}
	
	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getModDtFmt() {
		return DateUtil.convertDateFmt(modDt, "", "");
	}
	
	public String getModDtLocale() {
		return DateUtil.convertDateLocale(modDt);
	}
	
	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getSrchCompCd() {
		return srchCompCd;
	}

	public void setSrchCompCd(String srchCompCd) {
		this.srchCompCd = srchCompCd;
	}

	public String getSrchLvl() {
		return srchLvl;
	}

	public void setSrchLvl(String srchLvl) {
		this.srchLvl = srchLvl;
	}

	public String getSrchSaleChnnCd() {
		return srchSaleChnnCd;
	}

	public void setSrchSaleChnnCd(String srchSaleChnnCd) {
		this.srchSaleChnnCd = srchSaleChnnCd;
	}

	public String getSrchMainProdCatCd() {
		return srchMainProdCatCd;
	}

	public void setSrchMainProdCatCd(String srchMainProdCatCd) {
		this.srchMainProdCatCd = srchMainProdCatCd;
	}

	public String getSrchMainProdlCatCd() {
		return srchMainProdlCatCd;
	}

	public void setSrchMainProdlCatCd(String srchMainProdlCatCd) {
		this.srchMainProdlCatCd = srchMainProdlCatCd;
	}

	public String getSrchMainProdmCatCd() {
		return srchMainProdmCatCd;
	}

	public void setSrchMainProdmCatCd(String srchMainProdmCatCd) {
		this.srchMainProdmCatCd = srchMainProdmCatCd;
	}

	public String getSrchLCatNmKr() {
		return srchLCatNmKr;
	}

	public void setSrchLCatNmKr(String srchLCatNmKr) {
		this.srchLCatNmKr = srchLCatNmKr;
	}

	public String getSrchMCatNmKr() {
		return srchMCatNmKr;
	}

	public void setSrchMCatNmKr(String srchMCatNmKr) {
		this.srchMCatNmKr = srchMCatNmKr;
	}

	public String getSrchKsicLarCatCd() {
		return srchKsicLarCatCd;
	}

	public void setSrchKsicLarCatCd(String srchKsicLarCatCd) {
		this.srchKsicLarCatCd = srchKsicLarCatCd;
	}

	public String getSrchKsicLarCatCdNm() {
		return srchKsicLarCatCdNm;
	}

	public void setSrchKsicLarCatCdNm(String srchKsicLarCatCdNm) {
		this.srchKsicLarCatCdNm = srchKsicLarCatCdNm;
	}

	public String getSrchKsicMedCatCd() {
		return srchKsicMedCatCd;
	}

	public void setSrchKsicMedCatCd(String srchKsicMedCatCd) {
		this.srchKsicMedCatCd = srchKsicMedCatCd;
	}

	public String getSrchKsicMedCatCdNm() {
		return srchKsicMedCatCdNm;
	}

	public void setSrchKsicMedCatCdNm(String srchKsicMedCatCdNm) {
		this.srchKsicMedCatCdNm = srchKsicMedCatCdNm;
	}

	public String getSrchAppliNo() {
		return srchAppliNo;
	}

	public void setSrchAppliNo(String srchAppliNo) {
		this.srchAppliNo = srchAppliNo;
	}

	public String getSrchAppliStDy() {
		return srchAppliStDy;
	}

	public String getSrchAppliEdDy() {
		return srchAppliEdDy;
	}

	public String getSrchAppliStatCd() {
		return srchAppliStatCd;
	}

	public String getSrchMainProdNm() {
		return srchMainProdNm;
	}

	public void setSrchAppliStDy(String srchAppliStDy) {
		this.srchAppliStDy = srchAppliStDy;
	}

	public void setSrchAppliEdDy(String srchAppliEdDy) {
		this.srchAppliEdDy = srchAppliEdDy;
	}

	public void setSrchAppliStatCd(String srchAppliStatCd) {
		this.srchAppliStatCd = srchAppliStatCd;
	}

	public void setSrchMainProdNm(String srchMainProdNm) {
		this.srchMainProdNm = srchMainProdNm;
	}

	public String getSrchProdSltCmitYn() {
		return srchProdSltCmitYn;
	}

	public void setSrchProdSltCmitYn(String srchProdSltCmitYn) {
		this.srchProdSltCmitYn = srchProdSltCmitYn;
	}

	public String getSrchTeamCd() {
		return srchTeamCd;
	}

	public void setSrchTeamCd(String srchTeamCd) {
		this.srchTeamCd = srchTeamCd;
	}

	public List<String> getSrchAppliNos() {
		return srchAppliNos;
	}

	public void setSrchAppliNos(List<String> srchAppliNos) {
		this.srchAppliNos = srchAppliNos;
	}

	public String getSrchMemberCd() {
		return srchMemberCd;
	}

	public void setSrchMemberCd(String srchMemberCd) {
		this.srchMemberCd = srchMemberCd;
	}

	public String getSrchTeamDivnCd() {
		return srchTeamDivnCd;
	}

	public void setSrchTeamDivnCd(String srchTeamDivnCd) {
		this.srchTeamDivnCd = srchTeamDivnCd;
	}

	public String getSrchWorkType() {
		return srchWorkType;
	}

	public void setSrchWorkType(String srchWorkType) {
		this.srchWorkType = srchWorkType;
	}

	public String getSrchUserType() {
		return srchUserType;
	}

	public void setSrchUserType(String srchUserType) {
		this.srchUserType = srchUserType;
	}

	public String getSrchCompNmKr() {
		return srchCompNmKr;
	}

	public void setSrchCompNmKr(String srchCompNmKr) {
		this.srchCompNmKr = srchCompNmKr;
	}

	public String getSrchAcceptMemberNm() {
		return srchAcceptMemberNm;
	}

	public void setSrchAcceptMemberNm(String srchAcceptMemberNm) {
		this.srchAcceptMemberNm = srchAcceptMemberNm;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getRegFullId() {
		return regFullId;
	}

	public void setRegFullId(String regFullId) {
		this.regFullId = regFullId;
	}

	public String getModNm() {
		return modNm;
	}

	public void setModNm(String modNm) {
		this.modNm = modNm;
	}

	public String getModeFullId() {
		return modeFullId;
	}

	public void setModeFullId(String modeFullId) {
		this.modeFullId = modeFullId;
	}
	
	public String getSrchCompFileDivnCd() {
		return srchCompFileDivnCd;
	}

	public void setSrchCompFileDivnCd(String srchCompFileDivnCd) {
		this.srchCompFileDivnCd = srchCompFileDivnCd;
	}

	public String getSrchGenDivnCd() {
		return srchGenDivnCd;
	}

	public void setSrchGenDivnCd(String srchGenDivnCd) {
		this.srchGenDivnCd = srchGenDivnCd;
	}
	
	public String getSrchUserNm() {
		return srchUserNm;
	}

	public void setSrchUserNm(String srchUserNm) {
		this.srchUserNm = srchUserNm;
	}

	public String getSrchUserId() {
		return srchUserId;
	}

	public void setSrchUserId(String srchUserId) {
		this.srchUserId = srchUserId;
	}

	public String getSrchTeamNm() {
		return srchTeamNm;
	}

	public void setSrchTeamNm(String srchTeamNm) {
		this.srchTeamNm = srchTeamNm;
	}

	public String getSrchCatNmKr() {
		return srchCatNmKr;
	}

	public void setSrchCatNmKr(String srchCatNmKr) {
		this.srchCatNmKr = srchCatNmKr;
	}

	public String getSrchUseYn() {
		return srchUseYn;
	}

	public void setSrchUseYn(String srchUseYn) {
		this.srchUseYn = srchUseYn;
	}
	
}
