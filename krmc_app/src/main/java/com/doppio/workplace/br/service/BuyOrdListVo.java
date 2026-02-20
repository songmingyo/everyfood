package com.doppio.workplace.br.service;

import java.io.Serializable;

import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입처발주현황  
 * @Class : BuyOrderVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.25 j10000
 * </pre>
 * @version : 1.0
 */
public class BuyOrdListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;



	/**발주일자*/
	private String ordDt;
	/**매입처명*/
	private String buyNm;
	/**자재코드*/
	private String prdtCd;
	/**자재명*/
	private String prdtNm;
	/**규격*/
	private String prdtStd;
	/**입고요청일자*/
	private String buyDt;
	/**단가*/
	private String cost;
	/**낱개 수량*/
	private String ordQty;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	/**비고*/
	private String remarks;

	/**대분류*/
	private String lCd;
	/**중분류*/
	private String mCd;
	/**매입처 시작일*/
	private String buyStartDt;
	/**매입처 종료일*/
	private String buyEndDt;
	/**매입처코드*/
	private String buyCd;
	
	

	
	public String getBuyCd() {
		return buyCd;
	}

	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
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



	public String getOrdDt() {
		return ordDt;
	}

	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}

	public String getBuyNm() {
		return buyNm;
	}

	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
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

	public String getBuyDt() {
		return buyDt;
	}

	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWorkId() {
		return workId;
	}

	/**작업자*/
	private String workId;

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	
	

}
