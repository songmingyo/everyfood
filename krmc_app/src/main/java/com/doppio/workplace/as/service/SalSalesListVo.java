package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song min kyo
 * @Description : 영업신규현황
 * @Class : SalSalesGoalListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalSalesListVo extends Page  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4590976389986764151L;
	
	private String srchFrDt;		//검색조건 시작일
	private String srchToDt;		//검색조건 종료일
	private String srchSalesPrCd;	//검색조건 영업사원 코드
	private String srchSalesClass;	//검색조건 매출처구분
	
	private String salesCd;  		//매출처 코드
	private String startDt;         //거래시작일 
	private String salesNm;         //매출처 명 
	private String salesClass;      //매출구분 코드 
	private String salesClassNm;    //매출구분 코드 명 
	private String cnt;             //거래품목수 
	private String busiCon;		    //업태 
	private String busiType;        //업종
	private String storeCnt;        //매장수 
	private String setlCon;         //결제조건 
	private String expSalesAmt;     //에상매출액 
	private String subsidyRate;     //장려금율 
	private String warrAmt;         //보증금 
	private String creLim;          //여신한도
	private String carCd;           //배송차량 코드 
	private String carNm;           //배송차량 코드 명 
	private String salesPrCd;       //담당영업사원 코드 
	private String salesPrCdNm;     //담당영업사원 명 
	private String endDt;           //거래 종료일 
	private String newSalesPrCdNm;	 //개설영업사원 이름
	private String srchNewSalesPrCd; //검색조건_개설영업사원
	
	/** 25.12.24 add by song min kyo */
	private String pureAmt;			/* 공급가(출고금액) */    
	private String vatAmt;			/* 부가세 */          
	private String totAmt;			/* 총금액 */          
	private String profitAmt3;		/* 매출이익 */         
	private String marRate3;		/* 마진율 */
	
	public String getProfitAmt3() {
		return profitAmt3;
	}
	public void setProfitAmt3(String profitAmt3) {
		this.profitAmt3 = profitAmt3;
	}
	public String getPureAmt() {
		return pureAmt;
	}
	public void setPureAmt(String pureAmt) {
		this.pureAmt = pureAmt;
	}
	public String getVatAmt() {
		return vatAmt;
	}
	public void setVatAmt(String vatAmt) {
		this.vatAmt = vatAmt;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}	
	public String getMarRate3() {
		return marRate3;
	}
	public void setMarRate3(String marRate3) {
		this.marRate3 = marRate3;
	}
	public String getSrchNewSalesPrCd() {
		return srchNewSalesPrCd;
	}
	public void setSrchNewSalesPrCd(String srchNewSalesPrCd) {
		this.srchNewSalesPrCd = srchNewSalesPrCd;
	}
	public String getNewSalesPrCdNm() {
		return newSalesPrCdNm;
	}
	public void setNewSalesPrCdNm(String newSalesPrCdNm) {
		this.newSalesPrCdNm = newSalesPrCdNm;
	}
	public String getSrchSalesClass() {
		return srchSalesClass;
	}
	public void setSrchSalesClass(String srchSalesClass) {
		this.srchSalesClass = srchSalesClass;
	}
	public String getSrchFrDt() {
		return srchFrDt;
	}
	public void setSrchFrDt(String srchFrDt) {
		this.srchFrDt = srchFrDt;
	}
	public String getSrchToDt() {
		return srchToDt;
	}
	public void setSrchToDt(String srchToDt) {
		this.srchToDt = srchToDt;
	}
	public String getSrchSalesPrCd() {
		return srchSalesPrCd;
	}
	public void setSrchSalesPrCd(String srchSalesPrCd) {
		this.srchSalesPrCd = srchSalesPrCd;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
	public String getBusiCon() {
		return busiCon;
	}
	public void setBusiCon(String busiCon) {
		this.busiCon = busiCon;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getStoreCnt() {
		return storeCnt;
	}
	public void setStoreCnt(String storeCnt) {
		this.storeCnt = storeCnt;
	}
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getExpSalesAmt() {
		return expSalesAmt;
	}
	public void setExpSalesAmt(String expSalesAmt) {
		this.expSalesAmt = expSalesAmt;
	}
	public String getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public String getWarrAmt() {
		return warrAmt;
	}
	public void setWarrAmt(String warrAmt) {
		this.warrAmt = warrAmt;
	}
	public String getCreLim() {
		return creLim;
	}
	public void setCreLim(String creLim) {
		this.creLim = creLim;
	}
	public String getCarCd() {
		return carCd;
	}
	public void setCarCd(String carCd) {
		this.carCd = carCd;
	}
	public String getCarNm() {
		return carNm;
	}
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getSalesPrCdNm() {
		return salesPrCdNm;
	}
	public void setSalesPrCdNm(String salesPrCdNm) {
		this.salesPrCdNm = salesPrCdNm;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	
	
}
