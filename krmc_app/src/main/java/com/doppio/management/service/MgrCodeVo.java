package com.doppio.management.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.tronic.util.DateUtil;

/**
 * @author dada
 * @Description :코드관리 vo
 * @Class : MgrCodeVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 * </pre>
 * @version : 1.0
 */
public class MgrCodeVo  implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7419646043267161195L;
	
	/**마스터코드*/
	private String comCd	  ;
	/**마스터코드*/
	private String comCdOld	  ;
	/**마스터코드명칭*/
	private String comNm	  ;
	/**세부코드*/
	private String dtlCd      ;
	/**세부코드*/
	private String dtlCdOld      ;
	/**세부코드명칭*/
	private String dtlNm      ;
	/**세부코드추가명칭(영문)*/
	private String dtlSubNm	  ;
	/**세부코드추가명칭(VN)*/
	private String dtlExtendNm;
	/**사용여부*/
	private String useYn    ; 
	/**등록일자*/
	private String regDt      ; 
	/**변경일자*/
	private String modDt   ; 
	/**변경자 사번*/
	private String modId; 
	/**예비속성1*/
	private String extent01   ; 
	/**예비속성2*/
	private String extent02   ;
	/**작업자 아이디*/
	private String workId     ;
	/**기타구분사용(여러가지 구분코드)*/
	private String etcType     ;
	/**소트순번*/
	private String sortNo     ;
	/**서브코드명 추가속성명*/
	private String comSubNm ; 
	/**분류추가명칭(VN)*/
	private String comExtendNm;
	
	/** 코드의 조회 리스트 LANGUAGE TYPE (KO/EN)*/
	private String languageType     ;
	
	/** 디테일코드 배열 */
	private ArrayList<String> dtlCdArr;
	
	
	public ArrayList<String> getDtlCdArr() {
		return dtlCdArr;
	}
	public void setDtlCdArr(ArrayList<String> dtlCdArr) {
		this.dtlCdArr = dtlCdArr;
	}
	public String getDtlExtendNm() {
		return dtlExtendNm;
	}
	public void setDtlExtendNm(String dtlExtendNm) {
		this.dtlExtendNm = dtlExtendNm;
	}
	public String getComExtendNm() {
		return comExtendNm;
	}
	public void setComExtendNm(String comExtendNm) {
		this.comExtendNm = comExtendNm;
	}
	public String getComCd() {
		return comCd;
	}
	public void setComCd(String comCd) {
		this.comCd = comCd;
	}
	public String getComNm() {
		return comNm;
	}
	public void setComNm(String comNm) {
		this.comNm = comNm;
	}
	public String getDtlCd() {
		return dtlCd;
	}
	public void setDtlCd(String dtlCd) {
		this.dtlCd = dtlCd;
	}
	public String getDtlNm() {
		return dtlNm;
	}
	public void setDtlNm(String dtlNm) {
		this.dtlNm = dtlNm;
	}
	public String getDtlSubNm() {
		return dtlSubNm;
	}
	public void setDtlSubNm(String dtlSubNm) {
		this.dtlSubNm = dtlSubNm;
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
	public String getExtent01() {
		return extent01;
	}
	public void setExtent01(String extent01) {
		this.extent01 = extent01;
	}
	public String getExtent02() {
		return extent02;
	}
	public void setExtent02(String extent02) {
		this.extent02 = extent02;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getComCdOld() {
		return comCdOld;
	}
	public void setComCdOld(String comCdOld) {
		this.comCdOld = comCdOld;
	}
	public String getDtlCdOld() {
		return dtlCdOld;
	}
	public void setDtlCdOld(String dtlCdOld) {
		this.dtlCdOld = dtlCdOld;
	}
	public String getEtcType() {
		return etcType;
	}
	public void setEtcType(String etcType) {
		this.etcType = etcType;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getComSubNm() {
		return comSubNm;
	}
	public void setComSubNm(String comSubNm) {
		this.comSubNm = comSubNm;
	}
	public String getLanguageType() {
		return languageType;
	}
	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
	
	public String getComLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.comNm;
		} else return this.comSubNm;
	}
	
	public String getDtlLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		
		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.dtlNm;
		} else return this.dtlSubNm;
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

}
