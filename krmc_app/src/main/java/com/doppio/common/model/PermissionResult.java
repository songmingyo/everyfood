package com.doppio.common.model;

import java.io.Serializable;
/**
 * @author yjCho
 * @Description : 접속자 메뉴 권한 실행 조회 결과 
 * @Class : PermissionResult
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2013.05.28	yjCho
 *  2014.01.11  DADA	
 *  2017.08.19  DADA		MenuSession extends
 * </pre>
 * @version : 1.1
 */

public class PermissionResult  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7835587683950314124L;
	
	/** 접근권한 */
	private String authorityLevel;
	/** 읽기권한 */
	private String readAuth;
	/** 쓰기권한 */
	private String writeAuth;
	/** 수정권한 */
	private String editAuth;
	/** 삭제권한 */
	private String deleteAuth;
	/** 출력(엑셀)권한 */
	private String printAuth;
	/** 모든권한 */
	private String allAuth;
	/** 모든 권한없음 */
	private String deny;
	/** 메뉴목록없음 T/F */
	private String programNvl;
	/**접급자 코드*/
	private String memberCd ;
	/**접근프로그램아이디(url)*/
	private String programId;
	/**이벤트*/
	private String programEvent;
	 
	
	 
	 
	public String getAuthorityLevel() {
		return authorityLevel;
	}
	public void setAuthorityLevel(String authorityLevel) {
		this.authorityLevel = authorityLevel;
	}
	public String getReadAuth() {
		return readAuth;
	}
	public void setReadAuth(String readAuth) {
		this.readAuth = readAuth;
	}
	public String getWriteAuth() {
		return writeAuth;
	}
	public void setWriteAuth(String writeAuth) {
		this.writeAuth = writeAuth;
	}
	public String getEditAuth() {
		return editAuth;
	}
	public void setEditAuth(String editAuth) {
		this.editAuth = editAuth;
	}
	public String getDeleteAuth() {
		return deleteAuth;
	}
	public void setDeleteAuth(String deleteAuth) {
		this.deleteAuth = deleteAuth;
	}
	public String getPrintAuth() {
		return printAuth;
	}
	public void setPrintAuth(String printAuth) {
		this.printAuth = printAuth;
	}
	public String getAllAuth() {
		return allAuth;
	}
	public void setAllAuth(String allAuth) {
		this.allAuth = allAuth;
	}
	public String getDeny() {
		return deny;
	}
	public void setDeny(String deny) {
		this.deny = deny;
	}
	
	
	public String getProgramNvl() {
		return programNvl;
	}
	public void setProgramNvl(String programNvl) {
		this.programNvl = programNvl;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramEvent() {
		return programEvent;
	}
	public void setProgramEvent(String programEvent) {
		this.programEvent = programEvent;
	}
	
	 
	
}
