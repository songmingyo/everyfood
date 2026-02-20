package com.doppio.common.model;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

/**
 * 
 * @Class : CommonPersInfoVo.java
 * @Description : 개인정보조회이력Vo
 * @author : 안석진
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 1. 12.            안석진           최초 생성
 *
 * </pre>
 */
public class CommonPersInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7653210559500855285L;

	/**seq*/
	private String seq;
	
	/**개인정보 열람사유코드[9016]*/
	private String rendDivnCd;
	
	/**개인정보 열람사유코드명[9016]*/
	private String rendDivnCdNm;
	
	/**열람사유내용*/
	private String rendCmt;
	
	/**개인정보 조회내용*/
	private String persInfo;
	
	/**개인정보별번호(사용자 고유번호)*/
	private String persPclNo;
	
	/**정보조회일시*/
	private String accessDy;
	
	/**정보조회 업무 구분코드[9017]*/
	private String persBizsCd;
	
	/**정보조회 업무 구분코드명[9017]*/
	private String persBizsCdNm;
	
	/**접근아이피*/
	private String resIp;
	
	/**등록자 아이디*/
	private String regId;
	
	/**등록일시*/
	private String regDt;

	/**사용자 검증용 패스워드*/
	private String pw;
	
	/**업무구분*/
	private String workGbn;
	
	/**작업자 아이디*/
	private String workId;
	
	/**열람대상 계정생성구분[9007]*/
	private String persGenDivnCd;
	
	/**제안 고유번호*/
	private String appliNo;
	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getRendDivnCd() {
		return rendDivnCd;
	}

	public void setRendDivnCd(String rendDivnCd) {
		this.rendDivnCd = rendDivnCd;
	}

	public String getRendDivnCdNm() {
		return rendDivnCdNm;
	}

	public void setRendDivnCdNm(String rendDivnCdNm) {
		this.rendDivnCdNm = rendDivnCdNm;
	}

	public String getRendCmt() {
		return rendCmt;
	}

	public void setRendCmt(String rendCmt) {
		this.rendCmt = rendCmt;
	}

	public String getPersInfo() {
		return persInfo;
	}

	public void setPersInfo(String persInfo) {
		this.persInfo = persInfo;
	}

	public String getPersPclNo() {
		return persPclNo;
	}

	public void setPersPclNo(String persPclNo) {
		this.persPclNo = persPclNo;
	}

	public String getAccessDy() {
		return accessDy;
	}

	public void setAccessDy(String accessDy) {
		this.accessDy = accessDy;
	}
	
	public String getAccessDyFmt() {
		return DateUtil.convertDateFmt(accessDy, "", "");
	}

	public String getAccessDyLocale() {
		return DateUtil.convertDateLocale(accessDy);
	}

	public String getPersBizsCd() {
		return persBizsCd;
	}

	public void setPersBizsCd(String persBizsCd) {
		this.persBizsCd = persBizsCd;
	}

	public String getPersBizsCdNm() {
		return persBizsCdNm;
	}

	public void setPersBizsCdNm(String persBizsCdNm) {
		this.persBizsCdNm = persBizsCdNm;
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

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public String getRegDtFmt(){
		String rtnVal = this.regDt;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDate(rtnVal,"yyyyMMddHHmmss","");
		return rtnVal;
	}
	
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getWorkGbn() {
		return workGbn;
	}

	public void setWorkGbn(String workGbn) {
		this.workGbn = workGbn;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getPersGenDivnCd() {
		return persGenDivnCd;
	}

	public void setPersGenDivnCd(String persGenDivnCd) {
		this.persGenDivnCd = persGenDivnCd;
	}

	public String getAppliNo() {
		return appliNo;
	}

	public void setAppliNo(String appliNo) {
		this.appliNo = appliNo;
	}
	
}
