package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 매출처 품목별이익현황
 * @Class : SalSalesPrdtPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalSalesTotPrftListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 매출구분 */
	private String salesClassNm;
	/** 매출명 */
	private String salesNm;
	/** 2달전 매출 */
	private String salesAmt1;
	/** 1달전 매출 */
	private String salesAmt2;
	/** 해당월 매출 */
	private String salesAmt3;
	/** 2달전 매입 */
	private String buyAmt1;
	/** 1달전 매입 */
	private String buyAmt2;
	/** 해당월 매입 */
	private String buyAmt3;
	/** 2달전 이익 */
	private String profitAmt1;
	/** 1달전 이익 */
	private String profitAmt2;
	/** 해당월 이익 */
	private String profitAmt3;
	/** 2달전 마진율 */
	private String marRate1;
	/** 1달전 마진율 */
	private String marRate2;
	/** 해당월 마진율 */
	private String marRate3;
	/** 장려비율 */
	private String subsidyRate;
	/** 영업사원 */
	private String salesPrNm;
	
	/** Footer Sum */
	private String marRateSum1;
	private String marRateSum2;
	private String marRateSum3;
	
	/** 조회시작일 */
	private String searchFromDt;
	/** 조회종료일 */
	private String searchToDt;
	/** 영업사원 */
	private String searchSalesPrCd;
	/** 매출구분 */
	private String searchSalesClass;
	/** 본사구분 */
	private String searchHqClass;
	/** 매출처코드 */
	private String searchSalesCd;
	
	//출력 전월 매출
	private double prtSalesAmt2;
	//출력 당월 매출
	private double prtSalesAmt3;
	//출력 당월 이익
	private double prtProfitAmt3;
	//출력 당월 마진
	private double prtMarRate3;
	//출력 타이틀
	private String title;
	//출력 전월 매출 타이틀
	private String subTitle1;
	//출력 당월 매출 타이틀
	private String subTitle2;
	//출력 당월이익 타이틀
	private String subTitle3;
	//출력 시작일자
	private String startDt;
	//출력 종료일자
	private String endDt;
	//출력 컬럼수
	private double columnCnt;
	private String hqCdNm;
			
	public String getHqCdNm() {
		return hqCdNm;
	}
	public void setHqCdNm(String hqCdNm) {
		this.hqCdNm = hqCdNm;
	}
	public String getSearchSalesCd() {
		return searchSalesCd;
	}
	public void setSearchSalesCd(String searchSalesCd) {
		this.searchSalesCd = searchSalesCd;
	}
	public String getSearchHqClass() {
		return searchHqClass;
	}
	public void setSearchHqClass(String searchHqClass) {
		this.searchHqClass = searchHqClass;
	}
	public double getColumnCnt() {
		return columnCnt;
	}
	public void setColumnCnt(double columnCnt) {
		this.columnCnt = columnCnt;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSubTitle1() {
		return subTitle1;
	}
	public void setSubTitle1(String subTitle1) {
		this.subTitle1 = subTitle1;
	}
	public String getSubTitle2() {
		return subTitle2;
	}
	public void setSubTitle2(String subTitle2) {
		this.subTitle2 = subTitle2;
	}
	public String getSubTitle3() {
		return subTitle3;
	}
	public void setSubTitle3(String subTitle3) {
		this.subTitle3 = subTitle3;
	}
	public double getPrtSalesAmt2() {
		return prtSalesAmt2;
	}
	public void setPrtSalesAmt2(double prtSalesAmt2) {
		this.prtSalesAmt2 = prtSalesAmt2;
	}
	public double getPrtSalesAmt3() {
		return prtSalesAmt3;
	}
	public void setPrtSalesAmt3(double prtSalesAmt3) {
		this.prtSalesAmt3 = prtSalesAmt3;
	}
	public double getPrtProfitAmt3() {
		return prtProfitAmt3;
	}
	public void setPrtProfitAmt3(double prtProfitAmt3) {
		this.prtProfitAmt3 = prtProfitAmt3;
	}
	public double getPrtMarRate3() {
		return prtMarRate3;
	}
	public void setPrtMarRate3(double prtMarRate3) {
		this.prtMarRate3 = prtMarRate3;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getSalesAmt1() {
		return salesAmt1;
	}
	public void setSalesAmt1(String salesAmt1) {
		this.salesAmt1 = salesAmt1;
	}
	public String getSalesAmt2() {
		return salesAmt2;
	}
	public void setSalesAmt2(String salesAmt2) {
		this.salesAmt2 = salesAmt2;
	}
	public String getSalesAmt3() {
		return salesAmt3;
	}
	public void setSalesAmt3(String salesAmt3) {
		this.salesAmt3 = salesAmt3;
	}
	public String getBuyAmt1() {
		return buyAmt1;
	}
	public void setBuyAmt1(String buyAmt1) {
		this.buyAmt1 = buyAmt1;
	}
	public String getBuyAmt2() {
		return buyAmt2;
	}
	public void setBuyAmt2(String buyAmt2) {
		this.buyAmt2 = buyAmt2;
	}
	public String getBuyAmt3() {
		return buyAmt3;
	}
	public void setBuyAmt3(String buyAmt3) {
		this.buyAmt3 = buyAmt3;
	}
	public String getProfitAmt1() {
		return profitAmt1;
	}
	public void setProfitAmt1(String profitAmt1) {
		this.profitAmt1 = profitAmt1;
	}
	public String getProfitAmt2() {
		return profitAmt2;
	}
	public void setProfitAmt2(String profitAmt2) {
		this.profitAmt2 = profitAmt2;
	}
	public String getProfitAmt3() {
		return profitAmt3;
	}
	public void setProfitAmt3(String profitAmt3) {
		this.profitAmt3 = profitAmt3;
	}
	public String getMarRate1() {
		return marRate1;
	}
	public void setMarRate1(String marRate1) {
		this.marRate1 = marRate1;
	}
	public String getMarRate2() {
		return marRate2;
	}
	public void setMarRate2(String marRate2) {
		this.marRate2 = marRate2;
	}
	public String getMarRate3() {
		return marRate3;
	}
	public void setMarRate3(String marRate3) {
		this.marRate3 = marRate3;
	}
	public String getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	
	public String getMarRateSum1() {
		return marRateSum1;
	}
	public void setMarRateSum1(String marRateSum1) {
		this.marRateSum1 = marRateSum1;
	}
	public String getMarRateSum2() {
		return marRateSum2;
	}
	public void setMarRateSum2(String marRateSum2) {
		this.marRateSum2 = marRateSum2;
	}
	public String getMarRateSum3() {
		return marRateSum3;
	}
	public void setMarRateSum3(String marRateSum3) {
		this.marRateSum3 = marRateSum3;
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
	public String getSearchSalesPrCd() {
		return searchSalesPrCd;
	}
	public void setSearchSalesPrCd(String searchSalesPrCd) {
		this.searchSalesPrCd = searchSalesPrCd;
	}
	public String getSearchSalesClass() {
		return searchSalesClass;
	}
	public void setSearchSalesClass(String searchSalesClass) {
		this.searchSalesClass = searchSalesClass;
	}
	
		
	
		
	
}
