package com.doppio.workplace.sample.service;

import java.io.Serializable;

public class SampleJasperVo implements Serializable  {

	private static final long serialVersionUID = 3886942828227201183L;
	
	/*MEMBER CD*/
	private String memberCd;
	/* ID */
	private String userId;
	
	private String userType;
	/* 이름 */
	private String userNm;
	/* 전화번호 */
	private String telNo;
	/* 핸드폰번호 */
	private String hpNo;
	/* 이메일 */
	private String email;
	/* 소속Master_ID	 */
	private String compCd;
	private String masterId;
	private String compNm;
	/*바코드 테스트*/
	private String barcode; 
	
	
	
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
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	

	
	
	
	
	
	
	
}
