package com.doppio.workplace.sample.service;

import java.io.Serializable;


/**
 * @author 김영학
 * @Description : 샘플 점포마스터 vo
 * @Class : ShopMstVo
 * 
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 
 * </pre>
 * @version : 1.0
 */

public class SampleStoreMstVo implements Serializable {

	private static final long serialVersionUID = 4168995649574359244L;
	
	private String kunnr;		//고객 번호 1
	private String vtweg;		//유통경로(직가맹구분)
	private String zsname;		//[SD] 상호명 
	private String zfdat;		//[SD] 영업개점일 
	private String ztdat;		//[SD] 영업종료일 
	private String ztarea;		//[SD] 상권
	private String zloca;		//[SD] 지역 
	private String zstype;		//[SD] 점포형태
	private String zsvno;		//[SD] 사원번호
	private String vkbur;		//소속지점(상위)
	private String spart;		//사업부 2바이트
	private String stcd2;		//사업자번호
	private String fulladdr;	//점포주소
	private String zeName;		//SV명
	private String ort01;   	//City
	private String stras;   	//House number and street
	private String telf1;   	//First telephone number 
	
	private String regdt;		//등록일시
	private String regid;		//등록자ID
	private String moddt;		//수정일시
	private String modid;		//수정자ID
	
	private String srchSpart;	// 검색 사업부
	private String srchVtweg;	// 검색 직가맹 구분
	private String srchTxt;		// 검색 위치코드/위치명
	private String vtwegNm;		// 유통경로명
	private String stTelNo;		// 점포전화번호
	private String mgrNm;		// SV명
	private String addr;		// 점포주소
	
	
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getStTelNo() {
		return stTelNo;
	}
	public void setStTelNo(String stTelNo) {
		this.stTelNo = stTelNo;
	}
	public String getMgrNm() {
		return mgrNm;
	}
	public void setMgrNm(String mgrNm) {
		this.mgrNm = mgrNm;
	}
	public String getVtwegNm() {
		return vtwegNm;
	}
	public void setVtwegNm(String vtwegNm) {
		this.vtwegNm = vtwegNm;
	}
	public String getZeName() {
		return zeName;
	}
	public void setZeName(String zeName) {
		this.zeName = zeName;
	}
	public String getFulladdr() {
		return fulladdr;
	}
	public void setFulladdr(String fulladdr) {
		this.fulladdr = fulladdr;
	}
	public String getStcd2() {
		return stcd2;
	}
	public void setStcd2(String stcd2) {
		this.stcd2 = stcd2;
	}
	public String getSpart() {
		return spart;
	}
	public void setSpart(String spart) {
		this.spart = spart;
	}
	public String getVkbur() {
		return vkbur;
	}
	public void setVkbur(String vkbur) {
		this.vkbur = vkbur;
	}
	public String getKunnr() {
		return kunnr;
	}
	public void setKunnr(String kunnr) {
		this.kunnr = kunnr;
	}
	public String getVtweg() {
		return vtweg;
	}
	public void setVtweg(String vtweg) {
		this.vtweg = vtweg;
	}
	public String getZsname() {
		return zsname;
	}
	public void setZsname(String zsname) {
		this.zsname = zsname;
	}
	public String getZfdat() {
		return zfdat;
	}
	public void setZfdat(String zfdat) {
		this.zfdat = zfdat;
	}
	public String getZtdat() {
		return ztdat;
	}
	public void setZtdat(String ztdat) {
		this.ztdat = ztdat;
	}
	public String getZtarea() {
		return ztarea;
	}
	public void setZtarea(String ztarea) {
		this.ztarea = ztarea;
	}
	public String getZloca() {
		return zloca;
	}
	public void setZloca(String zloca) {
		this.zloca = zloca;
	}
	public String getZstype() {
		return zstype;
	}
	public void setZstype(String zstype) {
		this.zstype = zstype;
	}
	public String getZsvno() {
		return zsvno;
	}
	public void setZsvno(String zsvno) {
		this.zsvno = zsvno;
	}
	public String getRegdt() {
		return regdt;
	}
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getModdt() {
		return moddt;
	}
	public void setModdt(String moddt) {
		this.moddt = moddt;
	}
	public String getModid() {
		return modid;
	}
	public void setModid(String modid) {
		this.modid = modid;
	}
	
	
	public String getSrchSpart() {
		return srchSpart;
	}
	public void setSrchSpart(String srchSpart) {
		this.srchSpart = srchSpart;
	}
	public String getSrchVtweg() {
		return srchVtweg;
	}
	public void setSrchVtweg(String srchVtweg) {
		this.srchVtweg = srchVtweg;
	}
	public String getSrchTxt() {
		return srchTxt;
	}
	public void setSrchTxt(String srchTxt) {
		this.srchTxt = srchTxt;
	}
	public String getOrt01() {
		return ort01;
	}
	public void setOrt01(String ort01) {
		this.ort01 = ort01;
	}
	public String getStras() {
		return stras;
	}
	public void setStras(String stras) {
		this.stras = stras;
	}
	public String getTelf1() {
		return telf1;
	}
	public void setTelf1(String telf1) {
		this.telf1 = telf1;
	}
	

	/* Fomatting ----------------------------------------- 
	//[SD] 영업개점일 (formatting)
	public String getZfdatFmt() {
		String rtnVal = this.zfdat;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDateLocale(rtnVal,"yyyyMMdd","");
		return rtnVal;
	}
	
	//[SD] 영업종료일 (formatting)
	public String getZtdatFmt() {
		String rtnVal = this.ztdat;
		if(!StringUtils.isEmpty(rtnVal)) 
			rtnVal = DateUtil.convertDateLocale(rtnVal,"yyyyMMdd","");
		return rtnVal;
	}
	*/
}