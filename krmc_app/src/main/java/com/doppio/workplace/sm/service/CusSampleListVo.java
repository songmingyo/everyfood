package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 샘플출고현황  vo
 * @Class : CusSampleListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.05 j10000
 * </pre>
 * @version : 1.0
 */
public class CusSampleListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;



	/**샘플일자*/
	private String freeDt;
	/**매출처코드*/
	private String salesCd;
	/**매출처명*/
	private String salesNm;
	/**품목코드*/
	private String prdtCd;
	/**품목명*/
	private String prdtNm;
	/**품목규격*/
	private String prdtStd;
	/**단가*/
	private String cost;
	/**수량*/
	private String freeQty;
	/**합계금액*/
	private String totAmt;
	/**샘플구분*/
	private String freeClassNm;
	/**비고*/
	private String remarks;
	/**비고*/
	private String salesPrNm;
	/**창고명*/
	private String whNm;
	
	
	/* 조회조건 */
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	/**창고 코드 */
	private String whCd;
	/**구분 코드 */
	private String freeClass;
	/**대분류*/
	private String lCd;
	/**중분류*/
	private String mCd;
	
	
	public String getFreeDt() {
		return freeDt;
	}
	public void setFreeDt(String freeDt) {
		this.freeDt = freeDt;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
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
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(String freeQty) {
		this.freeQty = freeQty;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getFreeClassNm() {
		return freeClassNm;
	}
	public void setFreeClassNm(String freeClassNm) {
		this.freeClassNm = freeClassNm;
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
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
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}
	
	public String getFreeClass() {
		return freeClass;
	}
	public void setFreeClass(String freeClass) {
		this.freeClass = freeClass;
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
