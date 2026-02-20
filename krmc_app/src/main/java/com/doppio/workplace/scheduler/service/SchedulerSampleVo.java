package com.doppio.workplace.scheduler.service;

import java.io.Serializable;

public class SchedulerSampleVo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1375441725918038917L;
	
	
	/**사용자고유번호*/
	private String memberCd			;
	/**담당자ID*/
	private String userId			;
	/**성명*/
	private String userNm			;
	
	/**사용자타입*/
	private String userType			;
	
	/**패스워드 실패 횟수 */
	private String passFailCnt 		;
	/**계정 장금 */
	private String lockYn			;
	/**기존 패스워드 */
	private String hiddenPasswd		;
	
	
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getHiddenPasswd() {
		return hiddenPasswd;
	}
	public void setHiddenPasswd(String hiddenPasswd) {
		this.hiddenPasswd = hiddenPasswd;
	}
	
	
	
	
	
	
}
