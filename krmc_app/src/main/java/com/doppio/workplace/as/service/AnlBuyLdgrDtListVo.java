package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입처원장(일별)  vo
 * @Class : AnlBuyLdgrDtListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.06.22 j10000
 * </pre>
 * @version : 1.0
 */
public class AnlBuyLdgrDtListVo extends Page  implements Serializable {

	private static final long serialVersionUID = -6630600692804042954L;

	/**일자*/
	private String dtClass;
	/**매입처*/
	private String buyCd;
	/**매입처명*/
	private String buyNm;
	/**구분*/
	private String subClass;
	/**제품명*/
	private String prdtNm;
	/**제품규격*/
	private String prdtStd;
	/**수량*/
	private String qty;
	/**단가*/
	private String cost;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**총금액*/
	private String totAmt;
	/**현금*/
	private String paidAmt1;
	/**어음*/
	private String paidAmt2;
	/**장려금*/
	private String paidAmt3;
	/**기타 */
	private String paidAmt4;
	/**잔액 */
	private String balAmt;
	/**정렬 */
	private String sort;
   
   
	/*검색 시작일*/
	private String searchStartDt;
	/**검색 종료일*/
	private String searchEndDt;
	/**검색 매입처*/
	private String searchBuyCd;
	
	//타이틀
	private String title;
	//시작일
	private String startDt;
	//종료일
	private String endDt;
	//결제조건
	private String setlCon;
	//계좌번호
	private String bankAccNum;
	//출력일자
	private String printDt;
	/**수량*/
	private int prtQty;
	/**단가*/
	private int prtCost;
	/**공급가*/
	private int prtPureAmt;
	/**부가세*/
	private int prtVatAmt;
	/**총금액*/
	private int prtTotAmt;
	/**현금*/
	private int prtPaidAmt1;
	/**어음*/
	private int prtPaidAmt2;
	/**장려금*/
	private int prtPaidAmt3;
	/**기타 */
	private int prtPaidAmt4;
	/**잔액 */
	private int prtBalAmt;
	
	
	
	
	public int getPrtQty() {
		return prtQty;
	}
	public void setPrtQty(int prtQty) {
		this.prtQty = prtQty;
	}
	public int getPrtCost() {
		return prtCost;
	}
	public void setPrtCost(int prtCost) {
		this.prtCost = prtCost;
	}
	public int getPrtPureAmt() {
		return prtPureAmt;
	}
	public void setPrtPureAmt(int prtPureAmt) {
		this.prtPureAmt = prtPureAmt;
	}
	public int getPrtVatAmt() {
		return prtVatAmt;
	}
	public void setPrtVatAmt(int prtVatAmt) {
		this.prtVatAmt = prtVatAmt;
	}
	public int getPrtTotAmt() {
		return prtTotAmt;
	}
	public void setPrtTotAmt(int prtTotAmt) {
		this.prtTotAmt = prtTotAmt;
	}
	public int getPrtPaidAmt1() {
		return prtPaidAmt1;
	}
	public void setPrtPaidAmt1(int prtPaidAmt1) {
		this.prtPaidAmt1 = prtPaidAmt1;
	}
	public int getPrtPaidAmt2() {
		return prtPaidAmt2;
	}
	public void setPrtPaidAmt2(int prtPaidAmt2) {
		this.prtPaidAmt2 = prtPaidAmt2;
	}
	public int getPrtPaidAmt3() {
		return prtPaidAmt3;
	}
	public void setPrtPaidAmt3(int prtPaidAmt3) {
		this.prtPaidAmt3 = prtPaidAmt3;
	}
	public int getPrtPaidAmt4() {
		return prtPaidAmt4;
	}
	public void setPrtPaidAmt4(int prtPaidAmt4) {
		this.prtPaidAmt4 = prtPaidAmt4;
	}
	public int getPrtBalAmt() {
		return prtBalAmt;
	}
	public void setPrtBalAmt(int prtBalAmt) {
		this.prtBalAmt = prtBalAmt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getBankAccNum() {
		return bankAccNum;
	}
	public void setBankAccNum(String bankAccNum) {
		this.bankAccNum = bankAccNum;
	}
	public String getPrintDt() {
		return printDt;
	}
	public void setPrintDt(String printDt) {
		this.printDt = printDt;
	}
	public String getSearchBuyCd() {
		return searchBuyCd;
	}
	public void setSearchBuyCd(String searchBuyCd) {
		this.searchBuyCd = searchBuyCd;
	}
	public String getBuyCd() {
		return buyCd;
	}
	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}
	public String getDtClass() {
		return dtClass;
	}
	public void setDtClass(String dtClass) {
		this.dtClass = dtClass;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getSubClass() {
		return subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
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
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
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
	public String getPaidAmt1() {
		return paidAmt1;
	}
	public void setPaidAmt1(String paidAmt1) {
		this.paidAmt1 = paidAmt1;
	}
	public String getPaidAmt2() {
		return paidAmt2;
	}
	public void setPaidAmt2(String paidAmt2) {
		this.paidAmt2 = paidAmt2;
	}
	public String getPaidAmt3() {
		return paidAmt3;
	}
	public void setPaidAmt3(String paidAmt3) {
		this.paidAmt3 = paidAmt3;
	}
	public String getPaidAmt4() {
		return paidAmt4;
	}
	public void setPaidAmt4(String paidAmt4) {
		this.paidAmt4 = paidAmt4;
	}
	public String getBalAmt() {
		return balAmt;
	}
	public void setBalAmt(String balAmt) {
		this.balAmt = balAmt;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
	
	

}
