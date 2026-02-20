package com.doppio.workplace.br.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입 검수 확인  vo
 * @Class : BuyConfirmVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.04.04  j10000
 * </pre>
 * @version : 1.0
 */
public class BuyInspectListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;



	/**매입처명*/
	private String buyNm;
	/**매입처코드*/
	private String buyCd;
	/**사용유무*/
	private String buyConfYn;
	/**랙코드 명*/
	private String lackNo1;
	/**품목명*/
	private String prdtNm;
	/**품목규격*/
	private String prdtStd;
	/**박스당수량*/
	private String qtyBox;
	/**수량*/
	private String ordQty;
	/**공급가*/
	private String termVal;
	/**발주일자*/
	private String ordDt;
	/**합계금액*/
	private String stdYn;
	/**유통기한*/
	private String limitTm;
	/**발주기간*/
	private String timeLimit;
	/**창고명*/
	private String whNm;
	/**중분류*/
	private String lCd;
	/**중분류*/
	private String mCd;
	/**매입처 시작일*/
	private String buyStartDt;
	/**매입처 종료일*/
	private String buyEndDt;
	/**창고 코드 */
	private String whCd;
	/**규격*/
	private String ordUnit;
	/**창고 코드 */
	private String bIssQty;
	/**창고 코드 */
	private String dIssQty;
	/**창고 코드 */
	private String wIssQty;
	/**발주량 */
	private String stkQty;
	/**등록자명*/
	private String regNm;
	/**등록자 아이디 */
	private String regId;
	/**등록 시간 */
	private String regDt;
	//박수 수량
	private String boxQty;
	
	
	
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

	public String getbIssQty() {
		return bIssQty;
	}

	public void setbIssQty(String bIssQty) {
		this.bIssQty = bIssQty;
	}

	public String getdIssQty() {
		return dIssQty;
	}

	public void setdIssQty(String dIssQty) {
		this.dIssQty = dIssQty;
	}

	public String getwIssQty() {
		return wIssQty;
	}

	public void setwIssQty(String wIssQty) {
		this.wIssQty = wIssQty;
	}

	

	public String getStkQty() {
		return stkQty;
	}

	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}

	public String getRegNm() {
		return regNm;
	}

	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getBuyConfYn() {
		return buyConfYn;
	}

	public void setBuyConfYn(String buyConfYn) {
		this.buyConfYn = buyConfYn;
	}



	public String getBuyCd() {
		return buyCd;
	}

	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}

	public String getBuyNm() {
		return buyNm;
	}

	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}

	public String getLackNo1() {
		return lackNo1;
	}

	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
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

	public String getQtyBox() {
		return qtyBox;
	}

	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}

	public String getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
	}

	public String getTermVal() {
		return termVal;
	}

	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}

	public String getOrdDt() {
		return ordDt;
	}

	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}

	public String getStdYn() {
		return stdYn;
	}

	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}

	public String getLimitTm() {
		return limitTm;
	}

	public void setLimitTm(String limitTm) {
		this.limitTm = limitTm;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getWhNm() {
		return whNm;
	}

	public void setWhNm(String whNm) {
		this.whNm = whNm;
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

	public String getWhCd() {
		return whCd;
	}

	public void setWhCd(String whCd) {
		this.whCd = whCd;
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
