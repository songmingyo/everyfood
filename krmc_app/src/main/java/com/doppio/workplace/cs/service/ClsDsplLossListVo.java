package com.doppio.workplace.cs.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/*
 * @author song
 * @Description : 로스폐기  vo
 * @Class : ClsDayStkRegVo 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.04.12 song
 * </pre>
 * @version : 1.0
 */
public class ClsDsplLossListVo extends Page  implements Serializable {
	

	private static final long serialVersionUID = -8797484923598645834L;

	
	/* 일자 */
	private String dspDt 		;
	/* 창고명 */
	private String whNm 		;
	/* 품목코드 */
	private String prdtCd  		;
	/* 품목명*/
	private String prdtNm  		;
	/* 규격*/
	private String prdtStd 		;
	/* 폐기로스구분*/
	private String dspClassNm	;
	/* 수량 */
	private String dspQty	;
	/* 단가 */
	private String cost	;
	/* 금액 */
	private String dspAmt	;
	/* 비고 */
	private String remarks	;
	/* 등록자 */
	private String regNm	;
	/* 등록일 */
	private String regDt	;
	
	
	
	/*일자*/
	private String searchStartDt ;
	/*일자*/
	private String searchEndDt ;
	/*품목코드*/
	private String searchPrdtCd ;
	/* 구분 */
	private String searchDspClass ;
	/* 창고코드*/
	private String searchWhCd 	;
	/* 대분류*/
	private String searchLCd 	;
	/* 중분류*/
	private String searchMCd 	;
	
	
	
	public String getDspDt() {
		return dspDt;
	}
	public void setDspDt(String dspDt) {
		this.dspDt = dspDt;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getPrdtNm() {
		return prdtNm;
	}
	public void setPrdtNm(String prdtNm) {
		this.prdtNm = prdtNm;
	}
	public String getPrdtStd() {
		return prdtStd;
	}
	public void setPrdtStd(String prdtStd) {
		this.prdtStd = prdtStd;
	}
	public String getDspClassNm() {
		return dspClassNm;
	}
	public void setDspClassNm(String dspClassNm) {
		this.dspClassNm = dspClassNm;
	}
	public String getDspQty() {
		return dspQty;
	}
	public void setDspQty(String dspQty) {
		this.dspQty = dspQty;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getDspAmt() {
		return dspAmt;
	}
	public void setDspAmt(String dspAmt) {
		this.dspAmt = dspAmt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getSearchStartDt() {
		return searchStartDt;
	}
	public void setSearchStartDt(String searchStartDt) {
		this.searchStartDt = searchStartDt;
	}
	public String getSearchEndDt() {
		return searchEndDt;
	}
	public void setSearchEndDt(String searchEndDt) {
		this.searchEndDt = searchEndDt;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
	}
	public String getSearchDspClass() {
		return searchDspClass;
	}
	public void setSearchDspClass(String searchDspClass) {
		this.searchDspClass = searchDspClass;
	}
	public String getSearchWhCd() {
		return searchWhCd;
	}
	public void setSearchWhCd(String searchWhCd) {
		this.searchWhCd = searchWhCd;
	}
	public String getSearchLCd() {
		return searchLCd;
	}
	public void setSearchLCd(String searchLCd) {
		this.searchLCd = searchLCd;
	}
	public String getSearchMCd() {
		return searchMCd;
	}
	public void setSearchMCd(String searchMCd) {
		this.searchMCd = searchMCd;
	}
	
	
	
	
}
