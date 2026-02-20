package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입처입고조회  vo
 * @Class : BuyStoreVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.31 j10000
 * </pre>
 * @version : 1.0
 */
public class CusShipDtlListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;


	/**일자*/
	private String dt;
	/**매출처 구분명*/
	private String salesClassNm;
	/**창고명*/
	private String whNm;
	/**랙번호*/
	private String lackNo1;
	/**저장방법*/
	private String saveKindNm;
	/**판매자명*/
	private String salesNm;
	/**전표번호*/
	private String salesSlipNo;
	/**제품코드*/
	private String prdtCd;
	/**제품명*/
	private String prdtNm;
	/**규격*/
	private String prdtStd;
	/**단위*/
	private String ordUnitNm;
	/**가격*/
	private String price;
	/**수량*/
	private String qty;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	/**구분*/
	private String gubun;
	/**차량명*/
	private String carNm;
	/*비고*/
	private String remarks1;
	/**차량명*/
	private String qtyTot;
	/**차량명*/
	private String pureAmtTot;
	/**차량명*/
	private String vatAmtTot;
	/**차량명*/
	private String totAmtTot;
	
	
	/**시작일*/
	private String buyStartDt;
	/**종료일*/
	private String buyEndDt;
	/**영업사원코드*/
	private String salesPrCd;
	/**판매자코드*/
	private String salesCd;
	/**창고코드*/
	private String whCd;
	/**검색구분*/
	private String taxYn;
	/**매출처구분*/
	private String salesClass;
	/**대구분*/
	private String lCd;
	/**중구분*/
	private String mCd;
	/**출력매출처명*/
	private String salesNmSearch;
	/**출력품명*/
	private String prdtNmSearch;
	/**본사구분*/
	private String hqClass;
	
	/**본사구분*/
	private String salesCdExcel;
	/**본사구분*/
	private String hqClassExcel;
	
	/**가격*/
	private double prtPrice;
	/**수량*/
	private double prtQty;
	/**공급가*/
	private double prtPureAmt;
	/**부가세*/
	private double prtVatAmt;
	/**합계금액*/
	private double prtTotAmt;
	
	
	
	public String getQtyTot() {
		return qtyTot;
	}
	public void setQtyTot(String qtyTot) {
		this.qtyTot = qtyTot;
	}
	public String getPureAmtTot() {
		return pureAmtTot;
	}
	public void setPureAmtTot(String pureAmtTot) {
		this.pureAmtTot = pureAmtTot;
	}
	public String getVatAmtTot() {
		return vatAmtTot;
	}
	public void setVatAmtTot(String vatAmtTot) {
		this.vatAmtTot = vatAmtTot;
	}
	public String getTotAmtTot() {
		return totAmtTot;
	}
	public void setTotAmtTot(String totAmtTot) {
		this.totAmtTot = totAmtTot;
	}
	public double getPrtPrice() {
		return prtPrice;
	}
	public void setPrtPrice(double prtPrice) {
		this.prtPrice = prtPrice;
	}
	public double getPrtQty() {
		return prtQty;
	}
	public void setPrtQty(double prtQty) {
		this.prtQty = prtQty;
	}
	public double getPrtPureAmt() {
		return prtPureAmt;
	}
	public void setPrtPureAmt(double prtPureAmt) {
		this.prtPureAmt = prtPureAmt;
	}
	public double getPrtVatAmt() {
		return prtVatAmt;
	}
	public void setPrtVatAmt(double prtVatAmt) {
		this.prtVatAmt = prtVatAmt;
	}
	public double getPrtTotAmt() {
		return prtTotAmt;
	}
	public void setPrtTotAmt(double prtTotAmt) {
		this.prtTotAmt = prtTotAmt;
	}
	public String getSalesCdExcel() {
		return salesCdExcel;
	}
	public void setSalesCdExcel(String salesCdExcel) {
		this.salesCdExcel = salesCdExcel;
	}
	public String getHqClassExcel() {
		return hqClassExcel;
	}
	public void setHqClassExcel(String hqClassExcel) {
		this.hqClassExcel = hqClassExcel;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	public String getSalesNmSearch() {
		return salesNmSearch;
	}
	public void setSalesNmSearch(String salesNmSearch) {
		this.salesNmSearch = salesNmSearch;
	}
	public String getPrdtNmSearch() {
		return prdtNmSearch;
	}
	public void setPrdtNmSearch(String prdtNmSearch) {
		this.prdtNmSearch = prdtNmSearch;
	}
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getCarNm() {
		return carNm;
	}
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	
	public String getSaveKindNm() {
		return saveKindNm;
	}
	public void setSaveKindNm(String saveKindNm) {
		this.saveKindNm = saveKindNm;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getSalesSlipNo() {
		return salesSlipNo;
	}
	public void setSalesSlipNo(String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
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
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public String getBuyStartDt() {
		return buyStartDt;
	}
	public void setBuyStartDt(String buyStartDt) {
		this.buyStartDt = buyStartDt;
	}
	public String getBuyEndDt() {
		return buyEndDt;
	}
	public void setBuyEndDt(String buyEndDt) {
		this.buyEndDt = buyEndDt;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}
	public String getTaxYn() {
		return taxYn;
	}
	public void setTaxYn(String taxYn) {
		this.taxYn = taxYn;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getlCd() {
		return lCd;
	}
	public void setlCd(String lCd) {
		this.lCd = lCd;
	}
	public String getmCd() {
		return mCd;
	}
	public void setmCd(String mCd) {
		this.mCd = mCd;
	}

	

}
