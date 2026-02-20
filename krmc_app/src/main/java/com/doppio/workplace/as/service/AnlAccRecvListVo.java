package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/*
 * @author j10000
 * @Description : 매출처상세현황  vo
 * @Class : AnlCreditSalesListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.20 j10000
 * </pre>
 * @version : 1.0
 */
public class AnlAccRecvListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;


	/*매입처명*/
	private String buyNm;
	/*전전월잔액*/
	private String balAmt1;
	/*전월매입*/
	private String buyAmt2;
	/*전월지급*/
	private String payAmt2;
	/*전월잔액*/
	private String balAmt2;
	/*당월매입*/
	private String buyAmt3;
	/*당월지급*/
	private String payAmt3;
	/*당월잔액*/
	private String balAmt3;
	/*결제조건*/
	private String setlCon;
	/*은행명*/
	private String bankNm;
	/*은행예금주*/
	private String bankDep;
	/*은행계좌번호*/
	private String bankAccNum;
	/*대표전화번호*/
	private String telNo;
	/*사업주번호*/
	private String bsnsNum;
	
	/*필드 타이틀*/
	private String balAmt1Title;
	/*필드 타이틀*/
	private String buyAmt2Title;
	/*필드 타이틀*/
	private String payAmt2Title;
	/*필드 타이틀*/
	private String balAmt2Title;
	/*필드 타이틀*/
	private String buyAmt3Title;
	/*필드 타이틀*/
	private String payAmt3Title;
	/*필드 타이틀*/
	private String balAmt3Title;
	
	/*전전월잔액*/
	private double prtBalAmt1;
	/*전월매입*/
	private double prtBuyAmt2;
	/*전월지급*/
	private double prtPayAmt2;
	/*전월잔액*/
	private double prtBalAmt2;
	/*당월매입*/
	private double prtBuyAmt3;
	/*당월지급*/
	private double prtPayAmt3;
	/*당월잔액*/
	private double prtBalAmt3;
	//컬럼수
	private int columnCnt;
	
	
	/*시작일*/
	private String searchStartDt;
	/*종료일 */
	private String searchEndDt;
	/*매입처 */
	private String searchBuyCd;
	
	
	
	public int getColumnCnt() {
		return columnCnt;
	}
	public void setColumnCnt(int columnCnt) {
		this.columnCnt = columnCnt;
	}
	public double getPrtBalAmt1() {
		return prtBalAmt1;
	}
	public void setPrtBalAmt1(double prtBalAmt1) {
		this.prtBalAmt1 = prtBalAmt1;
	}
	public double getPrtBuyAmt2() {
		return prtBuyAmt2;
	}
	public void setPrtBuyAmt2(double prtBuyAmt2) {
		this.prtBuyAmt2 = prtBuyAmt2;
	}
	public double getPrtPayAmt2() {
		return prtPayAmt2;
	}
	public void setPrtPayAmt2(double prtPayAmt2) {
		this.prtPayAmt2 = prtPayAmt2;
	}
	public double getPrtBalAmt2() {
		return prtBalAmt2;
	}
	public void setPrtBalAmt2(double prtBalAmt2) {
		this.prtBalAmt2 = prtBalAmt2;
	}
	public double getPrtBuyAmt3() {
		return prtBuyAmt3;
	}
	public void setPrtBuyAmt3(double prtBuyAmt3) {
		this.prtBuyAmt3 = prtBuyAmt3;
	}
	public double getPrtPayAmt3() {
		return prtPayAmt3;
	}
	public void setPrtPayAmt3(double prtPayAmt3) {
		this.prtPayAmt3 = prtPayAmt3;
	}
	public double getPrtBalAmt3() {
		return prtBalAmt3;
	}
	public void setPrtBalAmt3(double prtBalAmt3) {
		this.prtBalAmt3 = prtBalAmt3;
	}
	public String getBalAmt1Title() {
		return balAmt1Title;
	}
	public void setBalAmt1Title(String balAmt1Title) {
		this.balAmt1Title = balAmt1Title;
	}
	public String getBuyAmt2Title() {
		return buyAmt2Title;
	}
	public void setBuyAmt2Title(String buyAmt2Title) {
		this.buyAmt2Title = buyAmt2Title;
	}
	public String getPayAmt2Title() {
		return payAmt2Title;
	}
	public void setPayAmt2Title(String payAmt2Title) {
		this.payAmt2Title = payAmt2Title;
	}
	public String getBalAmt2Title() {
		return balAmt2Title;
	}
	public void setBalAmt2Title(String balAmt2Title) {
		this.balAmt2Title = balAmt2Title;
	}
	public String getBuyAmt3Title() {
		return buyAmt3Title;
	}
	public void setBuyAmt3Title(String buyAmt3Title) {
		this.buyAmt3Title = buyAmt3Title;
	}
	public String getPayAmt3Title() {
		return payAmt3Title;
	}
	public void setPayAmt3Title(String payAmt3Title) {
		this.payAmt3Title = payAmt3Title;
	}
	public String getBalAmt3Title() {
		return balAmt3Title;
	}
	public void setBalAmt3Title(String balAmt3Title) {
		this.balAmt3Title = balAmt3Title;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getBalAmt1() {
		return balAmt1;
	}
	public void setBalAmt1(String balAmt1) {
		this.balAmt1 = balAmt1;
	}
	public String getBuyAmt2() {
		return buyAmt2;
	}
	public void setBuyAmt2(String buyAmt2) {
		this.buyAmt2 = buyAmt2;
	}
	public String getPayAmt2() {
		return payAmt2;
	}
	public void setPayAmt2(String payAmt2) {
		this.payAmt2 = payAmt2;
	}
	public String getBalAmt2() {
		return balAmt2;
	}
	public void setBalAmt2(String balAmt2) {
		this.balAmt2 = balAmt2;
	}
	public String getBuyAmt3() {
		return buyAmt3;
	}
	public void setBuyAmt3(String buyAmt3) {
		this.buyAmt3 = buyAmt3;
	}
	public String getPayAmt3() {
		return payAmt3;
	}
	public void setPayAmt3(String payAmt3) {
		this.payAmt3 = payAmt3;
	}
	public String getBalAmt3() {
		return balAmt3;
	}
	public void setBalAmt3(String balAmt3) {
		this.balAmt3 = balAmt3;
	}
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getBankNm() {
		return bankNm;
	}
	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}
	public String getBankDep() {
		return bankDep;
	}
	public void setBankDep(String bankDep) {
		this.bankDep = bankDep;
	}
	public String getBankAccNum() {
		return bankAccNum;
	}
	public void setBankAccNum(String bankAccNum) {
		this.bankAccNum = bankAccNum;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getBsnsNum() {
		return bsnsNum;
	}
	public void setBsnsNum(String bsnsNum) {
		this.bsnsNum = bsnsNum;
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
	public String getSearchBuyCd() {
		return searchBuyCd;
	}
	public void setSearchBuyCd(String searchBuyCd) {
		this.searchBuyCd = searchBuyCd;
	}
	
	
	
	
}
