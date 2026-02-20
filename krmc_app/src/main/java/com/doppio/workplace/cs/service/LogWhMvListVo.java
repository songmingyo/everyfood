package com.doppio.workplace.cs.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/*
 * @author song
 * @Description : 센터이동현황  vo
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
public class LogWhMvListVo extends Page  implements Serializable {
	

	private static final long serialVersionUID = -8797484923598645834L;

	
	/* 일자 */
	private String mvDt 		;
	/* 출고창고 */
	private String outWhCd 		;
	/* 출고창고명 */
	private String outWhNm 		;
	/* 입고창고 */
	private String inWhCd 		;
	/* 압고창고명 */
	private String inWhNm 		;
	/* 품목코드 */
	private String prdtCd  		;
	/* 품목명*/
	private String prdtNm  		;
	/* 규격*/
	private String prdtStd 		;
	/* 단위*/
	private String ordUnitNm	;
	/* 수량 */
	private double mvQty	;
	/* 소비기한 */
	private String termVal	;
	/* 비고 */
	private String remarks	;
	/* 등록자 */
	private String regNm	;
	/* 등록일 */
	private String regDt	;
	/* 랙번호 */
	private String lackNo1	;
	/* 박스수량 */
	private String boxQtyComp	;	
	
	/*시작일자*/
	private String searchStartDt ;
	/*종료일자*/
	private String searchEndDt ;
	/*품목코드*/
	private String searchPrdtCd ;
	/* 출고창고 */
	private String searchWhCd ;
	/* 대분류*/
	private String searchLCd 	;
	/* 중분류*/
	private String searchMCd 	;
	
	
	
	
	public String getBoxQtyComp() {
		return boxQtyComp;
	}
	public void setBoxQtyComp(String boxQtyComp) {
		this.boxQtyComp = boxQtyComp;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getMvDt() {
		return mvDt;
	}
	public void setMvDt(String mvDt) {
		this.mvDt = mvDt;
	}
	public String getOutWhCd() {
		return outWhCd;
	}
	public void setOutWhCd(String outWhCd) {
		this.outWhCd = outWhCd;
	}
	public String getOutWhNm() {
		return outWhNm;
	}
	public void setOutWhNm(String outWhNm) {
		this.outWhNm = outWhNm;
	}
	public String getInWhCd() {
		return inWhCd;
	}
	public void setInWhCd(String inWhCd) {
		this.inWhCd = inWhCd;
	}
	public String getInWhNm() {
		return inWhNm;
	}
	public void setInWhNm(String inWhNm) {
		this.inWhNm = inWhNm;
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
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	
	public double getMvQty() {
		return mvQty;
	}
	public void setMvQty(double mvQty) {
		this.mvQty = mvQty;
	}
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
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
