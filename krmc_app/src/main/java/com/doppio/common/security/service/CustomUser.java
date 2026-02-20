package com.doppio.common.security.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;

public class CustomUser implements UserDetails  {

	private static final long serialVersionUID = 4264590646163836341L;

	public static final String MANAGER_LOGIN_SESSION_KEY = "com.doppio.user";

	/*로그인 사용자 계정명*/
	private String username;

	/*로그인 계정 기본정보 */
	private String userId;
	private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String fullName;
    private String hpNo;
    private String telNo;
    
    /* Spring Security related fields*/
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    /* extend user info */
    private String memberCd;
    private String masterId;
    private String compCd;
    private String compNm;
    private String tradeCompCd;
    private String deptCd;
    private String deptNm;
    private String userType;
    private String userTypeNm;
    private String userRoles;
    private String lockYn;
    private String useYn;
    private String passFailCnt;
    private String maxPassFailCnt;
    private String positionNm;
    private String classNm;
    private String strCd;
    private String workngSeCd;    
    private String divcd;
    private String postnBrnchCd;
    private String empNo;
    
    private String idTypeCd;
    private String sysCode;
    private String expSysCode;
    private String excTypeCd;
    
    /* 비밀번호 변경기간 체크 */
    private String passChgYn;
    private String passLastDy;
    private String passChgNeedYn;		// 비밀번호 변경필요여부(비밀번호 변경대상이며 변경일로부터 90일이 오버된 사용자)    

    /* 사용자 탈퇴 여부 */
    private String withDrawFg;
    
    /* 관리자 로그인세션 정보 */
    private Authentication oldAuth;

    /** 승인구분코드[9068] */
    private String aprvDivnCd;
    
    /** 인터페이스 여부  */
    private String ifYn;
    
    /** insert group parameter */
    private String groupParam;
    
    private String userNm;   
    
    /** 로그인 사용자 유형 [S,F]*/
    private String loginUserType;
    
    /**사용자 생성유형 [9007]*/
    private String genDivnCd;
    
    /**중복 접속 여부(최종사용자 로그인시도 일시) */
	private String doublieAccessDt;
	
	/**회사담당자명 */
	private String entpManNm;
	
	/**회사담당자번호 */
	private String entpManTel;
	
	/**회사담당자메일 */
	private String entpManEmail;

	/**사업자 등록번호*/
	private String bmanNo;
	
	/**비밀번호 변경 후 3개월(90) 여부*/
	private String pw3mFlag;
    
	/**forgot password url code(랜덤키 or 임시비밀번호)*/
	private String forgotPwdCode;
	
	/**forgot password expiration date*/
	private String forgotPwdLstDy;
	
	private String hqClass;
    
	
	public String getHqClass() {
		return hqClass;
	}

	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmailMsk() {
		return StringUtil.prsnInfoMsk("email",email);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getHpNo() {
		return hpNo;
	}

	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public List<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getMemberCd() {
		return memberCd;
	}

	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
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
	
	public String getTradeCompCd() {
		return tradeCompCd;
	}

	public void setTradeCompCd(String tradeCompCd) {
		this.tradeCompCd = tradeCompCd;
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

	public String getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
	}

	public String getLockYn() {
		return lockYn;
	}

	public void setLockYn(String lockYn) {
		this.lockYn = lockYn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getPassFailCnt() {
		return passFailCnt;
	}

	public void setPassFailCnt(String passFailCnt) {
		this.passFailCnt = passFailCnt;
	}

	public String getMaxPassFailCnt() {
		return maxPassFailCnt;
	}

	public void setMaxPassFailCnt(String maxPassFailCnt) {
		this.maxPassFailCnt = maxPassFailCnt;
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

	public String getStrCd() {
		return strCd;
	}

	public void setStrCd(String strCd) {
		this.strCd = strCd;
	}

	public String getWorkngSeCd() {
		return workngSeCd;
	}

	public void setWorkngSeCd(String workngSeCd) {
		this.workngSeCd = workngSeCd;
	}

	public String getIdTypeCd() {
		return idTypeCd;
	}

	public void setIdTypeCd(String idTypeCd) {
		this.idTypeCd = idTypeCd;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getExpSysCode() {
		return expSysCode;
	}

	public void setExpSysCode(String expSysCode) {
		this.expSysCode = expSysCode;
	}

	public String getExcTypeCd() {
		return excTypeCd;
	}

	public void setExcTypeCd(String excTypeCd) {
		this.excTypeCd = excTypeCd;
	}

	public String getPassChgYn() {
		return passChgYn;
	}

	public void setPassChgYn(String passChgYn) {
		this.passChgYn = passChgYn;
	}

	public String getPassLastDy() {
		return passLastDy;
	}

	public void setPassLastDy(String passLastDy) {
		this.passLastDy = passLastDy;
	}

	public String getPassLastDyFmt() {
		return DateUtil.convertDateFmt(passLastDy, "", "");
	}
	
	public String getPassChgNeedYn() {
		return passChgNeedYn;
	}

	public void setPassChgNeedYn(String passChgNeedYn) {
		this.passChgNeedYn = passChgNeedYn;
	}

	public String getWithDrawFg() {
		return withDrawFg;
	}

	public void setWithDrawFg(String withDrawFg) {
		this.withDrawFg = withDrawFg;
	}

	public Authentication getOldAuth() {
		return oldAuth;
	}

	public void setOldAuth(Authentication oldAuth) {
		this.oldAuth = oldAuth;
	}

	public String getAprvDivnCd() {
		return aprvDivnCd;
	}

	public void setAprvDivnCd(String aprvDivnCd) {
		this.aprvDivnCd = aprvDivnCd;
	}

	public String getIfYn() {
		return ifYn;
	}

	public void setIfYn(String ifYn) {
		this.ifYn = ifYn;
	}

	public String getGroupParam() {
		return groupParam;
	}

	public void setGroupParam(String groupParam) {
		this.groupParam = groupParam;
	}

	public String getDivcd() {
		return divcd;
	}

	public void setDivcd(String divcd) {
		this.divcd = divcd;
	}

	public String getPostnBrnchCd() {
		return postnBrnchCd;
	}

	public void setPostnBrnchCd(String postnBrnchCd) {
		this.postnBrnchCd = postnBrnchCd;
	}
	
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getLoginUserType() {
		return loginUserType;
	}

	public void setLoginUserType(String loginUserType) {
		this.loginUserType = loginUserType;
	}

	public String getGenDivnCd() {
		return genDivnCd;
	}

	public void setGenDivnCd(String genDivnCd) {
		this.genDivnCd = genDivnCd;
	}

	public String getDoublieAccessDt() {
		return doublieAccessDt;
	}

	public void setDoublieAccessDt(String doublieAccessDt) {
		this.doublieAccessDt = doublieAccessDt;
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

	public String getCompCd() {
		return compCd;
	}

	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	public String getBmanNo() {
		return bmanNo;
	}

	public void setBmanNo(String bmanNo) {
		this.bmanNo = bmanNo;
	}

	public String getPw3mFlag() {
		return pw3mFlag;
	}

	public void setPw3mFlag(String pw3mFlag) {
		this.pw3mFlag = pw3mFlag;
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
	
}