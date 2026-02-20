package com.doppio.common.model;

import java.io.Serializable;
/**
 * @author hdh
 * @Description :
 * @Class : Result
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version : 1.0
 */
public class CommonMemberInfoVo extends PageN implements Serializable {

	private static final long serialVersionUID = 8743755707371684259L;

	/** 사용자고유번호*/
	private String memberCd   ;
	/** 담당자ID*/
	private String userId     ;
	/** 신청인성명*/
	private String userNm     ;
	/** 전화번호*/
	private String telNo      ;
	/** 핸드폰번호*/
	private String hpNo       ;
	/** 이메일*/
	private String email      ;
	/** 소속회사코드*/
	private String compCd     ;
	/** 소속회사코드*/
	private String masterId	  ;
	/** 소속회사명칭*/
	private String compNm     ;
	/** 계열사유무(계열사:Y/협력사:N 구분)*/
	private String affiliateYn;
	/** 직책명*/
	private String positionNm ;
	/** 직급명 */
	private String classNm    ;
	/** 사용자타입 코드[9001]*/
	private String userType   ;
	/** 사용자타입명 */
	private String userTypeNm ;
	/** 사용유무*/
	private String useYn      ;

	/** 조회조건 */
	private String findMember_userNm		;
	private String findMember_userId		;
	private String findMember_compNm		;
	private String findMember_examiner		;


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
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	public String getAffiliateYn() {
		return affiliateYn;
	}
	public void setAffiliateYn(String affiliateYn) {
		this.affiliateYn = affiliateYn;
	}
	public String getPositionNm() {
		return positionNm;
	}
	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}
	public String getClassNm() {
		return classNm;
	}
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserTypeNm() {
		return userTypeNm;
	}
	public void setUserTypeNm(String userTypeNm) {
		this.userTypeNm = userTypeNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getFindMember_userNm() {
		return findMember_userNm;
	}
	public void setFindMember_userNm(String findMember_userNm) {
		this.findMember_userNm = findMember_userNm;
	}
	public String getFindMember_userId() {
		return findMember_userId;
	}
	public void setFindMember_userId(String findMember_userId) {
		this.findMember_userId = findMember_userId;
	}
	public String getFindMember_compNm() {
		return findMember_compNm;
	}
	public void setFindMember_compNm(String findMember_compNm) {
		this.findMember_compNm = findMember_compNm;
	}
	public String getFindMember_examiner() {
		return findMember_examiner;
	}
	public void setFindMember_examiner(String findMember_examiner) {
		this.findMember_examiner = findMember_examiner;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

}
