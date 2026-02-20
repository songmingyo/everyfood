package com.doppio.workplace.common.model;


import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.CommonSearchVo;

/**
 * 
 * @Class : UserInfoVo.java
 * @Description : 사용자 정보 Vo
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   
 *
 * </pre>
 */
/**
 * @Class : UserInfoVo.java
 * @Description : 
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------

 *
 * </pre>
 */
public class UserInfoVo extends CommonSearchVo implements Serializable {

	private static final long serialVersionUID = -6640443052942912350L;
	
	/** 사용자고유번호 */
	private String memberCd;
	/** 사용자 ID */
	private String userId;
	/** 성명 */
	private String userNm;
	/** 비밀번호 */
	private String userPw;
	/** 전화번호 */
	private String telNo;
	/** 핸드폰번호 */
	private String hpNo;
	/** 이메일 */
	private String email;
	/** EMS 저장용 to 아이디 */
	private String toId;
	/** 소속회사코드 */
	private String compCd;
	private String masterId;
	/** 소속회사명 */
	private String compNm;
	/** 소속회사 사업자번호 */
	private String bmanNo;
	/** 인터페이스 여부 */
	private String ifYn;
	/** 사용자아이디 유형 */
	private String idType;
	/** 사용자유형명 */
	private String idTypeNm;
	/** 업체등록 여부 */
	private String regYn;

	private String userType;
	private String useYn;
	private String psnInfoYn;
	private String userTypeNm;
	private String sendTarget;
	
	/** 인증번호 */
	private String certNum;
	
	/** 검색조건 */
	private String search_userId;		//사용자아이디
	private String search_userNm;		//사용자명
	private String search_idType;		//아이디타입
	private String search_compNm;		//업체명
	private String search_bmanNo;       //사업자 등록번호
	private String srchWorkDivnCd;		//작업구분코드[5013]
	private String srchTeamCd;			//팀관리코드
	private String srchMasterId;		//회사마스터아이디
	private String srchMemberCd;		//사용자고유번호
	private String srchTeamDivnCd;		//팀유형구분코드[5014]
	private String srchBmanNo;			//사업자 등록번호
	
	
	/** 작업자정보 */
	private String workId;		//작업자아이디
	private String regDt;		//등록일시
	private String modDt;		//수정일시
	private String regNm;		//등록자명
	
	

	/**개인정보동의 만료 예정일*/
	private String indInfoAgreeExpireDyFmt;
	// teamList
	private String teamList;
	
	/**협력사 담당자명*/
	private String entpManNm;
	/**협력사 담당자번호*/
	private String entpManTel;
	/**협력사 담당자이메일*/
	private String entpManEmail;
	
	/**비밀번호 변경화면 접근키 */
	private String accessCode;
	
	/**forgot password url code(랜덤키)*/
	private String forgotPwdCode;
	
	/**forgot password expiration date*/
	private String forgotPwdLstDy;
	
	/**비밀번호 필수변경 유무*/
	private String passChgYn;
	
	/**사용자 생성타입[9007]*/
	private String genDivnCd;
	
	/**사용자 생성타입명[9007]*/
	private String genDivnCdNm;
	
	/** 비밀번호 실패횟수*/
	private String passFailCnt;
	
	/** lock 유무*/
	private String lockYn;
	
	/** 비밀번호 변경(초기화) 구분*/
	//(FG:비밀번호 찾기, FR:임시비밀번호 발급(운영자가 생성)에 따른 접근, 3M: 비밀번호 3개월 변경,MY:나의 정보)
	private String accessType;
	
	/**연동된 기간계 계정 고유번호*/
	private String scmMemberCd;
	
	/**연동된  기간 계정 아이디*/
	private String scmUserId;
	
	/**연동된 scm 계정 사용자명*/
	private String scmUserNm;
	
	/**대표자명*/
	private String ceoNm;
	
	/**상세주소*/
	private String dtlAddr;
	
	/* 팀관리 ----------------- */
	private String teamCd;			//관리팀 코드
	private String teamCdOld;		//관리팀 코드 old
	private String teamNm;			//팀명
	private String buildinYn;		//빌드인 여부
	private String workDivnCd;		//작업구분코드[5013]
	private String workDivnCdNm;	//작업구분코드명
	private String workDivnCdOld;	//작업구분코드 old
	private String teamDivnCd;		//팀유형코드[5014]
	private String teamDivnCdNm;	//팀유형코드명
	
	//사용자리스트
	private List<UserInfoVo> userInfoList;
	
	//데이터 저장 유형 (I:신규등록, U:수정)
	private String saveMode;
	
	//수정가능여부
	private String modYn;
	
	//SCM 계정 연동여부
	private String lnkgFlag;
	
	//SCM 연동일시
	private String lnkgDt;
	
	//연동대상 memberCd
	private String lnkgMemberCd;
	
	//새 비밀번호
	private String newPw;
	
	/**회원탈퇴시 제안건 카운트**/
	private String totCnt;
	private String complCnt; //완료건수
	private String progCnt;  //진행중인건
	
	
	public String getTeamList() {
		return teamList;
	}

	public void setTeamList(String teamList) {
		this.teamList = teamList;
	}

	public String getSendTarget() {
		return sendTarget;
	}

	public void setSendTarget(String sendTarget) {
		this.sendTarget = sendTarget;
	}

	public String getMemberCd() {
		return memberCd;
	}

	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserIdMsk() {
		return StringUtil.prsnInfoMsk("userId", userId); 
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	
	public String getUserNmMsk() {
		return StringUtil.prsnInfoMsk("name", userNm); 
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	public String getTelNoMsk() {
		return StringUtil.prsnInfoMsk("telNo", telNo); 
	}

	public String getHpNo() {
		return hpNo;
	}

	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	
	public String getHpNoMsk() {
		return StringUtil.prsnInfoMsk("hpNo", hpNo); 
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToId() {
		return toId;
	}
	
	public String getEmailMsk() {
		return StringUtil.prsnInfoMsk("email",email);
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getCompCd() {
		return compCd;
	}

	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getCompNm() {
		return compNm;
	}

	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	
	public String getBmanNo() {
		return bmanNo;
	}

	public void setBmanNo(String bmanNo) {
		this.bmanNo = bmanNo;
	}

	public String getIfYn() {
		return ifYn;
	}

	public void setIfYn(String ifYn) {
		this.ifYn = ifYn;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdTypeNm() {
		return idTypeNm;
	}

	public void setIdTypeNm(String idTypeNm) {
		this.idTypeNm = idTypeNm;
	}

	public String getRegYn() {
		return regYn;
	}

	public void setRegYn(String regYn) {
		this.regYn = regYn;
	}

	public String getSearch_userId() {
		return search_userId;
	}

	public void setSearch_userId(String search_userId) {
		this.search_userId = search_userId;
	}

	public String getSearch_userNm() {
		return search_userNm;
	}

	public void setSearch_userNm(String search_userNm) {
		this.search_userNm = search_userNm;
	}

	public String getSearch_idType() {
		return search_idType;
	}

	public void setSearch_idType(String search_idType) {
		this.search_idType = search_idType;
	}

	public String getSearch_compNm() {
		return search_compNm;
	}

	public void setSearch_compNm(String search_compNm) {
		this.search_compNm = search_compNm;
	}
	public String getSearch_bmanNo() {
		return search_bmanNo;
	}
	
	public void setSearch_bmanNo(String search_bmanNo) {
		this.search_bmanNo = search_bmanNo;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	public String getIndInfoAgreeExpireDyFmt() {
		return indInfoAgreeExpireDyFmt;
	}

	public void setIndInfoAgreeExpireDyFmt(String indInfoAgreeExpireDyFmt) {
		this.indInfoAgreeExpireDyFmt = indInfoAgreeExpireDyFmt;
	}

	public String getPsnInfoYn() {
		return psnInfoYn;
	}

	public void setPsnInfoYn(String psnInfoYn) {
		this.psnInfoYn = psnInfoYn;
	}



	public String getUserTypeNm() {
		return userTypeNm;
	}

	public void setUserTypeNm(String userTypeNm) {
		this.userTypeNm = userTypeNm;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}



	public static final class UserInfoVoBuilder {
		private String memberCd;
		private String userId;
		private String userNm;
		private String userPw;
		private String telNo;
		private String hpNo;
		private String email;
		private String toId;
		private String compCd;
		private String masterId;
		private String compNm;
		private String ifYn;
		private String idType;
		private String idTypeNm;
		private String search_userId;
		private String search_userNm;
		private String search_idType;
		private String search_compNm;
		private String workId;

		private UserInfoVoBuilder() {
		}

		public static UserInfoVoBuilder anUserInfoVo() {
			return new UserInfoVoBuilder();
		}

		public UserInfoVoBuilder setMemberCd(String memberCd) {
			this.memberCd = memberCd;
			return this;
		}

		public UserInfoVoBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public UserInfoVoBuilder setUserNm(String userNm) {
			this.userNm = userNm;
			return this;
		}

		public UserInfoVoBuilder setUserPw(String userPw) {
			this.userPw = userPw;
			return this;
		}

		public UserInfoVoBuilder setTelNo(String telNo) {
			this.telNo = telNo;
			return this;
		}

		public UserInfoVoBuilder setHpNo(String hpNo) {
			this.hpNo = hpNo;
			return this;
		}

		public UserInfoVoBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public UserInfoVoBuilder setToId(String toId) {
			this.toId = toId;
			return this;
		}

		public UserInfoVoBuilder setCompCd(String compCd) {
			this.compCd = compCd;
			return this;
		}

		public UserInfoVoBuilder setMasterId(String masterId) {
			this.masterId = masterId;
			return this;
		}
		
		public UserInfoVoBuilder setCompNm(String compNm) {
			this.compNm = compNm;
			return this;
		}

		public UserInfoVoBuilder setIfYn(String ifYn) {
			this.ifYn = ifYn;
			return this;
		}

		public UserInfoVoBuilder setIdType(String idType) {
			this.idType = idType;
			return this;
		}

		public UserInfoVoBuilder setIdTypeNm(String idTypeNm) {
			this.idTypeNm = idTypeNm;
			return this;
		}

		public UserInfoVoBuilder setSearch_userId(String search_userId) {
			this.search_userId = search_userId;
			return this;
		}

		public UserInfoVoBuilder setSearch_userNm(String search_userNm) {
			this.search_userNm = search_userNm;
			return this;
		}

		public UserInfoVoBuilder setSearch_idType(String search_idType) {
			this.search_idType = search_idType;
			return this;
		}

		public UserInfoVoBuilder setSearch_compNm(String search_compNm) {
			this.search_compNm = search_compNm;
			return this;
		}

		public UserInfoVoBuilder setWorkId(String workId) {
			this.workId = workId;
			return this;
		}

		public UserInfoVo build() {
			UserInfoVo userInfoVo = new UserInfoVo();
			userInfoVo.setMemberCd(memberCd);
			userInfoVo.setUserId(userId);
			userInfoVo.setUserNm(userNm);
			userInfoVo.setUserPw(userPw);
			userInfoVo.setTelNo(telNo);
			userInfoVo.setHpNo(hpNo);
			userInfoVo.setEmail(email);
			userInfoVo.setToId(toId);
			userInfoVo.setCompCd(compCd);
			userInfoVo.setMasterId(masterId);
			userInfoVo.setCompNm(compNm);
			userInfoVo.setIfYn(ifYn);
			userInfoVo.setIdType(idType);
			userInfoVo.setIdTypeNm(idTypeNm);
			userInfoVo.setSearch_userId(search_userId);
			userInfoVo.setSearch_userNm(search_userNm);
			userInfoVo.setSearch_idType(search_idType);
			userInfoVo.setSearch_compNm(search_compNm);
			userInfoVo.setWorkId(workId);
			return userInfoVo;
		}
	}



	public String getEntpManNm() {
		return entpManNm;
	}

	public void setEntpManNm(String entpManNm) {
		this.entpManNm = entpManNm;
	}

	public String getEntpManTel() {
		return entpManTel;
	}

	public void setEntpManTel(String entpManTel) {
		this.entpManTel = entpManTel;
	}

	public String getEntpManEmail() {
		return entpManEmail;
	}

	public void setEntpManEmail(String entpManEmail) {
		this.entpManEmail = entpManEmail;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getForgotPwdCode() {
		return forgotPwdCode;
	}

	public void setForgotPwdCode(String forgotPwdCode) {
		this.forgotPwdCode = forgotPwdCode;
	}

	public String getForgotPwdLstDy() {
		return forgotPwdLstDy;
	}

	public void setForgotPwdLstDy(String forgotPwdLstDy) {
		this.forgotPwdLstDy = forgotPwdLstDy;
	}

	public String getForgotPwdLstDyFmt() {
		return DateUtil.convertDateFmt(forgotPwdLstDy, "", "");
	}
	
	public String getPassChgYn() {
		return passChgYn;
	}

	public void setPassChgYn(String passChgYn) {
		this.passChgYn = passChgYn;
	}

	public String getGenDivnCd() {
		return genDivnCd;
	}

	public void setGenDivnCd(String genDivnCd) {
		this.genDivnCd = genDivnCd;
	}

	
	public String getGenDivnCdNm() {
		return genDivnCdNm;
	}

	public void setGenDivnCdNm(String genDivnCdNm) {
		this.genDivnCdNm = genDivnCdNm;
	}

	public String getPassFailCnt() {
		return passFailCnt;
	}

	public void setPassFailCnt(String passFailCnt) {
		this.passFailCnt = passFailCnt;
	}

	public String getLockYn() {
		return lockYn;
	}

	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}
	
	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getAccessType() {
		return accessType;
	}

	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public String getScmMemberCd() {
		return scmMemberCd;
	}

	public void setScmMemberCd(String scmMemberCd) {
		this.scmMemberCd = scmMemberCd;
	}

	public String getScmUserId() {
		return scmUserId;
	}

	public void setScmUserId(String scmUserId) {
		this.scmUserId = scmUserId;
	}
	
	public String getScmUserIdMsk() {
		return StringUtil.prsnInfoMsk("userId", scmUserId); 
	}

	public String getScmUserNm() {
		return scmUserNm;
	}

	public void setScmUserNm(String scmUserNm) {
		this.scmUserNm = scmUserNm;
	}

	public String getCeoNm() {
		return ceoNm;
	}

	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}

	public String getDtlAddr() {
		return dtlAddr;
	}

	public void setDtlAddr(String dtlAddr) {
		this.dtlAddr = dtlAddr;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	
	public String getRegDtFmt(){
		String rtnVal = this.regDt;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDate(rtnVal,"yyyyMMddHHmmss","");
		return rtnVal;
	}
	
	public String getModDtFmt(){
		String rtnVal = this.modDt;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDate(rtnVal,"yyyyMMddHHmmss","");
		return rtnVal;
	}
	
	public String getTeamCd() {
		return teamCd;
	}

	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}

	public String getTeamNm() {
		return teamNm;
	}

	public void setTeamNm(String teamNm) {
		this.teamNm = teamNm;
	}

	public String getBuildinYn() {
		return buildinYn;
	}

	public void setBuildinYn(String buildinYn) {
		this.buildinYn = buildinYn;
	}

	public String getWorkDivnCd() {
		return workDivnCd;
	}

	public void setWorkDivnCd(String workDivnCd) {
		this.workDivnCd = workDivnCd;
	}

	public String getWorkDivnCdNm() {
		return workDivnCdNm;
	}

	public void setWorkDivnCdNm(String workDivnCdNm) {
		this.workDivnCdNm = workDivnCdNm;
	}

	public String getSrchWorkDivnCd() {
		return srchWorkDivnCd;
	}

	public void setSrchWorkDivnCd(String srchWorkDivnCd) {
		this.srchWorkDivnCd = srchWorkDivnCd;
	}

	public String getSrchTeamCd() {
		return srchTeamCd;
	}

	public void setSrchTeamCd(String srchTeamCd) {
		this.srchTeamCd = srchTeamCd;
	}

	public List<UserInfoVo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfoVo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public String getWorkDivnCdOld() {
		return workDivnCdOld;
	}

	public void setWorkDivnCdOld(String workDivnCdOld) {
		this.workDivnCdOld = workDivnCdOld;
	}

	public String getSrchMasterId() {
		return srchMasterId;
	}

	public void setSrchMasterId(String srchMasterId) {
		this.srchMasterId = srchMasterId;
	}

	public String getSaveMode() {
		return saveMode;
	}

	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getTeamCdOld() {
		return teamCdOld;
	}

	public void setTeamCdOld(String teamCdOld) {
		this.teamCdOld = teamCdOld;
	}
	public String getTeamDivnCd() {
		return teamDivnCd;
	}

	public void setTeamDivnCd(String teamDivnCd) {
		this.teamDivnCd = teamDivnCd;
	}

	public String getTeamDivnCdNm() {
		return teamDivnCdNm;
	}

	public void setTeamDivnCdNm(String teamDivnCdNm) {
		this.teamDivnCdNm = teamDivnCdNm;
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
	
	
	public String getSrchBmanNo() {
		return srchBmanNo;
	}

	public void setSrchBmanNo(String srchBmanNo) {
		this.srchBmanNo = srchBmanNo;
	}

	public String getModYn() {
		return modYn;
	}

	public void setModYn(String modYn) {
		this.modYn = modYn;
	}

	public String getLnkgFlag() {
		return lnkgFlag;
	}

	public void setLnkgFlag(String lnkgFlag) {
		this.lnkgFlag = lnkgFlag;
	}

	public String getLnkgDt() {
		return lnkgDt;
	}


	public void setLnkgDt(String lnkgDt) {
		this.lnkgDt = lnkgDt;
	}
	
	public String getLnkgDtFmt(){
		String rtnVal = this.lnkgDt;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDate(rtnVal,"yyyyMMddHHmmss","");
		return rtnVal;
	}

	public String getLnkgMemberCd() {
		return lnkgMemberCd;
	}

	public void setLnkgMemberCd(String lnkgMemberCd) {
		this.lnkgMemberCd = lnkgMemberCd;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getTotCnt() {
		return totCnt;
	}

	public String getComplCnt() {
		return complCnt;
	}

	public String getProgCnt() {
		return progCnt;
	}

	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}

	public void setComplCnt(String complCnt) {
		this.complCnt = complCnt;
	}

	public void setProgCnt(String progCnt) {
		this.progCnt = progCnt;
	}
	
}