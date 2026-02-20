package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 차량별 배송현황표
 * @Class : SalSalesPrPerfListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalSalesCarShipListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 차량명 */
	private String carNm;
    /** 차량번호 */
	private String carNum;
	/** 배송기사 */
	private String drvSnm;
	/** 매출처 */
	private String salesNm;
	/** 매출처구분 */
	private String salesClass;
	/** 매출이익율구분 */
	private String profitClass;
	/** 공급금액 */
	private String pureAmt;
	/** 총금액 */
	private String totAmt;
	/** 매출이익 */
	private String salesProfit;
	/** 이익율(%) */
	private String marRate;
	/** 중량 */
	private String weight;
	/** 입고금액 */
	private String buyAmt;
	
	/** Footer Sum 마진 */
	private String marRateSum;
	/** Footer Sum 공급금액 */
	private String pureAmtTot;
	/** Footer Sum총금액 */
	private String totAmtTot;
	/** Footer Sum 매출이익 */
	private String salesProfitTot;
	
	/** 조회시작일 */
	private String searchFromDt;
	/** 조회종료일 */
	private String searchToDt;
	/** 배송차량 */
	private String searchCarCd;
	/** 매출처구분 */
	private String searchSalesClass;
	/** 영업사원 */
	private String searchSalesPrCd;
	/** 작업자 */
	private String workId;
	/** 작업자 */
	private String userType;
	
	
	
	public String getPureAmtTot() {
		return pureAmtTot;
	}
	public void setPureAmtTot(String pureAmtTot) {
		this.pureAmtTot = pureAmtTot;
	}
	public String getTotAmtTot() {
		return totAmtTot;
	}
	public void setTotAmtTot(String totAmtTot) {
		this.totAmtTot = totAmtTot;
	}
	public String getSalesProfitTot() {
		return salesProfitTot;
	}
	public void setSalesProfitTot(String salesProfitTot) {
		this.salesProfitTot = salesProfitTot;
	}
	public String getMarRateSum() {
		return marRateSum;
	}
	public void setMarRateSum(String marRateSum) {
		this.marRateSum = marRateSum;
	}
	public String getBuyAmt() {
		return buyAmt;
	}
	public void setBuyAmt(String buyAmt) {
		this.buyAmt = buyAmt;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCarNm() {
		return carNm;
	}
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getDrvSnm() {
		return drvSnm;
	}
	public void setDrvSnm(String drvSnm) {
		this.drvSnm = drvSnm;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	
	public String getProfitClass() {
		return profitClass;
	}
	public void setProfitClass(String profitClass) {
		this.profitClass = profitClass;
	}
	public String getPureAmt() {
		return pureAmt;
	}
	public void setPureAmt(String pureAmt) {
		this.pureAmt = pureAmt;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getSalesProfit() {
		return salesProfit;
	}
	public void setSalesProfit(String salesProfit) {
		this.salesProfit = salesProfit;
	}
	public String getMarRate() {
		return marRate;
	}
	public void setMarRate(String marRate) {
		this.marRate = marRate;
	}
	public String getSearchFromDt() {
		return searchFromDt;
	}
	public void setSearchFromDt(String searchFromDt) {
		this.searchFromDt = searchFromDt;
	}
	public String getSearchToDt() {
		return searchToDt;
	}
	public void setSearchToDt(String searchToDt) {
		this.searchToDt = searchToDt;
	}
	public String getSearchCarCd() {
		return searchCarCd;
	}
	public void setSearchCarCd(String searchCarCd) {
		this.searchCarCd = searchCarCd;
	}
	public String getSearchSalesClass() {
		return searchSalesClass;
	}
	public void setSearchSalesClass(String searchSalesClass) {
		this.searchSalesClass = searchSalesClass;
	}
	public String getSearchSalesPrCd() {
		return searchSalesPrCd;
	}
	public void setSearchSalesPrCd(String searchSalesPrCd) {
		this.searchSalesPrCd = searchSalesPrCd;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	

	
	
}
