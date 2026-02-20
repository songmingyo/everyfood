package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처별입금현황  vo
 * @Class : BuyStoreVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.14 j10000
 * </pre>
 * @version : 1.0
 */
public class AccBuyWdrlListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;



	/**입금일자*/
	private String wdrlDt;
	/**매출처코드*/
	private String buyCd;
	/**매출처명*/
	private String buyNm;
	/**계좌이체 금액*/
	private String accountAmt;
	/**현금*/
	private String cashAmt;
	/**카드 금액*/
	private String cardAmt;
	/**어음 금액*/
	private String noteAmt;
	/**수입 금액*/
	private String incomeAmt;
	/**수수료 금액*/
	private String vatAmt;
	/**장려금 금액*/
	private String subsidyAmt;
	/**기타 금액*/
	private String etcAmt;
	/**합계금액*/
	private String totAmt;
	/**지급구분*/
	private String setlCon;

	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	
	
	public String getWdrlDt() {
		return wdrlDt;
	}
	public void setWdrlDt(String wdrlDt) {
		this.wdrlDt = wdrlDt;
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
	public String getAccountAmt() {
		return accountAmt;
	}
	public void setAccountAmt(String accountAmt) {
		this.accountAmt = accountAmt;
	}
	public String getCashAmt() {
		return cashAmt;
	}
	public void setCashAmt(String cashAmt) {
		this.cashAmt = cashAmt;
	}
	public String getCardAmt() {
		return cardAmt;
	}
	public void setCardAmt(String cardAmt) {
		this.cardAmt = cardAmt;
	}
	public String getNoteAmt() {
		return noteAmt;
	}
	public void setNoteAmt(String noteAmt) {
		this.noteAmt = noteAmt;
	}
	public String getIncomeAmt() {
		return incomeAmt;
	}
	public void setIncomeAmt(String incomeAmt) {
		this.incomeAmt = incomeAmt;
	}
	public String getVatAmt() {
		return vatAmt;
	}
	public void setVatAmt(String vatAmt) {
		this.vatAmt = vatAmt;
	}
	public String getSubsidyAmt() {
		return subsidyAmt;
	}
	public void setSubsidyAmt(String subsidyAmt) {
		this.subsidyAmt = subsidyAmt;
	}
	public String getEtcAmt() {
		return etcAmt;
	}
	public void setEtcAmt(String etcAmt) {
		this.etcAmt = etcAmt;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
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

	
}
