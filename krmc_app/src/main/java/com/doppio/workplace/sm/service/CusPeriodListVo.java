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
public class CusPeriodListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**매출처구분*/
	private String salesClassNm;
	/**매출처명*/
	private String salesNm;
	/**창고명 */
	private String whNm;
	/**매입처명*/
	private String buyNm;
	/**품목명*/
	private String prdtNm;
	/**품목규격*/
	private String prdtStd;
	/**수량*/
	private String salesQty;
	/**박스 수량*/
	private String boxQty;
	/**입고단가*/
	private String cost;
	/**출고단가*/
	private String price;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	
	/**수량*/
	private String salesQtyTot;
	/**공급가*/
	private String pureAmtTot;
	/**부가세*/
	private String vatAmtTot;
	/**합계금액*/
	private String totAmtTot;
	
	
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	/**매출처코드*/
	private String salesCd;
	/**품목*/
	private String prdtCd;
	/**과세여부 코드 */
	private String taxYn;
	/**매출처 구분 */
	private String salesClass;
	/**창고 코드 */
	private String whCd;
	/**대분류*/
	private String lCd;
	/**중분류*/
	private String mCd;
	//박스당수량
	private String qtyBox;
	//주문단위
	private String ordUnit;
	//규격유무
	private String stdYn;
	//본사구분
	private String hqClass;
	
	private String srchSalesPrCd;
	
	private String lackNo1;
	private String lackNo2;
	
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getLackNo2() {
		return lackNo2;
	}
	public void setLackNo2(String lackNo2) {
		this.lackNo2 = lackNo2;
	}
	public String getSrchSalesPrCd() {
		return srchSalesPrCd;
	}
	public void setSrchSalesPrCd(String srchSalesPrCd) {
		this.srchSalesPrCd = srchSalesPrCd;
	}
	public String getSalesQtyTot() {
		return salesQtyTot;
	}
	public void setSalesQtyTot(String salesQtyTot) {
		this.salesQtyTot = salesQtyTot;
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
	public String getPureAmtTot() {
		return pureAmtTot;
	}
	public void setPureAmtTot(String pureAmtTot) {
		this.pureAmtTot = pureAmtTot;
	}
	public String getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
	}
	public String getOrdUnit() {
		return ordUnit;
	}
	public void setOrdUnit(String ordUnit) {
		this.ordUnit = ordUnit;
	}
	public String getStdYn() {
		return stdYn;
	}
	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
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
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
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
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
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
