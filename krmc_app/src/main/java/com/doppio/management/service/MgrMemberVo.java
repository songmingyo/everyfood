package com.doppio.management.service;

import java.io.Serializable;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author dada
 * @Description :그룹멤버관리 vo
 * @Class : MgrMemberVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 * </pre>
 * @version : 1.0
 */
public class MgrMemberVo extends Page implements Serializable {
	private static final Logger logger = LoggerFactory.getLogger(MgrMemberVo.class);

	private static final long serialVersionUID = 2529142476006288357L;
	
	/**사용자고유번호*/
	private String memberCd			;
	/**담당자ID*/
	private String userId			;
	/**성명*/
	private String userNm			;
	/**비밀번호*/
	private String userPw			;
	/**전화번호*/
	private String telNo			;
	/**핸드폰번호*/
	private String hpNo				;
	/**이메일*/
	private String email			;
	/**소속회사코드*/
	private String compCd			;
	private String masterId			;
	/**소속회사명*/
	private String compNm			;
	/**직책명*/
	private String positionNm		;
	/**직급명*/
	private String classNm			;
	/**사용자타입*/
	private String userType			;
	/**사용자타입*/
	private String userTypeName		;
	/**비밀번호필수변경유무*/
	private String passChgYn		;
	/**비밀번호최종변경일자*/
	private String passLastDy  		;
	/**사용유무*/
	private String useYn			;
	/**등록일시*/
	private String regDt			;
	/**등록자아이디*/
	private String regId			;
	/**수정일시*/
	private String modDt			;
	/**수정자아이디*/
	private String modId			;
	/**금일접속아이피갯수*/
	private String ipCnt;
	/**선택한그룹 List*/
	private String[] groupIds		;
	/**패스워드확인 */
	private String userPwCheck		;
	/**기사용ID */
	private String userIdOld		;
	/**패스워드 실패 횟수 */
	private String passFailCnt 		;
	/**계정 장금 */
	private String lockYn			;
	/**기존 패스워드 */
	private String hiddenPasswd		;
	
	/**작업자 아이디*/
	private String workId;
	
	/**접속자일자 */
	private String accessDate ;
	/**접속아이피*/
	private String accessIp;
	/**접속아이피 조회 시작일자*/
	private String accessDateFr;
	/**접속아이피 조회 끝일자*/
	private String accessDateTo;
	/**접속자아이피조회 member cd*/
	private String access_memberCd;
	/**사용자고유번호*/
	private String memberCds[]		;
	/**사번*/
	private String empNo;
	
	
	/**매핑그룹 여부*/
	private String groupAuthYn;
	/**그룹아이디*/
	private String groupId  ;
	
	
	/**작업구분(U/I)*/
	private String workType  ;
	
	/**검색조건*/
	private String search_userNm;
	private String search_userType;
	private String search_userId;
	private String search_useYn;
	private String search_compNm;
	private String search_ipCnt;	// 금일접속아이피갯수
	private String search_genDivnCd;
	/**시큐리티 USER_ROLES*/
	private String userRoles;
	
	/**사업자등록번호*/
	private String bmanNo;
	
	/** 인터페이스 구분[9007]*/
	private String genDivnCd		;
	/**사용자 생성명*/
	private String genDivnCdName	;
	
	
	
	/**비밀번호 찾기 질문 코드[9015]*/
	private String findPwdCd;
	/**비밀번호 찾기 질문 답변*/
	private String findPwdAnswer;
	
	/**탈퇴처리여부*/
	private String withDrawFg;
	/**탈퇴처리일자*/
	private String withDrawDt;
	
	/**업체 정회원 승인여부*/
	private String regularApprYn;
	
	/** 사용자구분코드(S:내부사용자, F:SCM 협력사, L:PLS 협력사) */
	private String memGbnCd;
	
	public String getSearch_ipCnt() {
		return search_ipCnt;
	}
	public void setSearch_ipCnt(String search_ipCnt) {
		this.search_ipCnt = search_ipCnt;
	}
	
	
	public String getSearch_genDivnCd() {
		return search_genDivnCd;
	}
	public void setSearch_genDivnCd(String search_genDivnCd) {
		this.search_genDivnCd = search_genDivnCd;
	}
	public String getAccessDateFr() {
		return accessDateFr;
	}
	public void setAccessDateFr(String accessDateFr) {
		this.accessDateFr = accessDateFr;
	}
	public String getAccessDateTo() {
		return accessDateTo;
	}
	public void setAccessDateTo(String accessDateTo) {
		this.accessDateTo = accessDateTo;
	}
	public String getAccess_memberCd() {
		return access_memberCd;
	}
	public void setAccess_memberCd(String access_memberCd) {
		this.access_memberCd = access_memberCd;
	}
	public String getAccessIp() {
		return accessIp;
	}
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public String getAccessDate() {
		return accessDate;
	}
	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}
	public String getIpCnt() {
		return ipCnt;
	}
	public void setIpCnt(String ipCnt) {
		this.ipCnt = ipCnt;
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
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
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
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
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
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
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
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	public String[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}
	public String getUserPwCheck() {
		return userPwCheck;
	}
	public void setUserPwCheck(String userPwCheck) {
		this.userPwCheck = userPwCheck;
	}
	
	public String getUserIdOld() {
		return userIdOld;
	}
	public void setUserIdOld(String userIdOld) {
		this.userIdOld = userIdOld;
	}
	
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getGroupAuthYn() {
		return groupAuthYn;
	}
	public void setGroupAuthYn(String groupAuthYn) {
		this.groupAuthYn = groupAuthYn;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String[] getMemberCds() {
		return memberCds;
	}
	public void setMemberCds(String[] memberCds) {
		this.memberCds = memberCds;
	}
	
	
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getSearch_userNm() {
		return search_userNm;
	}
	public void setSearch_userNm(String search_userNm) {
		this.search_userNm = search_userNm;
	}
	public String getSearch_userType() {
		return search_userType;
	}
	public void setSearch_userType(String search_userType) {
		this.search_userType = search_userType;
	}
	public String getSearch_useYn() {
		return search_useYn;
	}
	public void setSearch_useYn(String search_useYn) {
		this.search_useYn = search_useYn;
	}
	public String getSearch_compNm() {
		return search_compNm;
	}
	public void setSearch_compNm(String search_compNm) {
		this.search_compNm = search_compNm;
	}
	public String getSearch_userId() {
		return search_userId;
	}
	public void setSearch_userId(String search_userId) {
		this.search_userId = search_userId;
	}
	public String getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(String userRoles) {
		this.userRoles = userRoles;
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
	public String getBmanNo() {
		return bmanNo;
	}
	public void setBmanNo(String bmanNo) {
		this.bmanNo = bmanNo;
	}
	
	public String getGenDivnCd() {
		return genDivnCd;
	}
	public void setGenDivnCd(String genDivnCd) {
		this.genDivnCd = genDivnCd;
	}
	public String getGenDivnCdName() {
		return genDivnCdName;
	}
	public void setGenDivnCdName(String genDivnCdName) {
		this.genDivnCdName = genDivnCdName;
	}
	public String getSearchAccessDateFrLocale() {
		String accessDateFrLocale = "";
		try{
			accessDateFrLocale = DateUtil.convertDateLocale(accessDateFr);
		}catch(Exception ex){logger.debug(ex.getMessage()); accessDateFrLocale = ""; }
		return accessDateFrLocale;
	}
	public String getSearchAccessDateToLocale() {
		String accessDateToLocale = "";
		try{
			accessDateToLocale = DateUtil.convertDateLocale(accessDateTo);
		}catch(Exception ex){logger.debug(ex.getMessage()); accessDateToLocale = ""; }
		return accessDateToLocale;
	}
	public String getAccessDateLocale(){
		String accessDateLocale = "";
		try{
			accessDateLocale = DateUtil.convertDate(accessDate, "", "yyyyMMddHHmmss"); 
		}catch(Exception ex){logger.debug(ex.getMessage()); accessDateLocale = ""; }
		return accessDateLocale;
	}
	public String getPassLastDyLocale() {
		return DateUtil.convertDateLocale(passLastDy);
	}
	
	public String getPassLastDyFmt() {
		return DateUtil.convertDateLocale(passLastDy, "", ""); 
	}
	
	public String getPassLastDtLocale() {
		String fmt = "dd/MM/yyyy";
    	Locale locale = LocaleContextHolder.getLocale();
        if(Locale.ENGLISH.equals(locale) ) fmt = "dd/MM/yyyy";
        if(Locale.KOREAN.equals(locale) ) fmt = "yyyy/MM/dd";
    	return DateUtil.convertDate(passLastDy, "yyyyMMdd", fmt);
	}
	
	public String getFindPwdCd() {
		return findPwdCd;
	}
	public void setFindPwdCd(String findPwdCd) {
		this.findPwdCd = findPwdCd;
	}
	public String getFindPwdAnswer() {
		return findPwdAnswer;
	}
	public void setFindPwdAnswer(String findPwdAnswer) {
		this.findPwdAnswer = findPwdAnswer;
	}
	public String getWithDrawFg() {
		return withDrawFg;
	}
	public void setWithDrawFg(String withDrawFg) {
		this.withDrawFg = withDrawFg;
	}
	public String getWithDrawDt() {
		return withDrawDt;
	}
	public void setWithDrawDt(String withDrawDt) {
		this.withDrawDt = withDrawDt;
	}
	
	public String getRegularApprYn() {
		return regularApprYn;
	}
	
	public void setRegularApprYn(String regularApprYn) {
		this.regularApprYn = regularApprYn;
	}
	public String getMemGbnCd() {
		return memGbnCd;
	}
	public void setMemGbnCd(String memGbnCd) {
		this.memGbnCd = memGbnCd;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
}
