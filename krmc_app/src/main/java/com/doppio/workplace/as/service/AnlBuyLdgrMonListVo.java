package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
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
public class AnlBuyLdgrMonListVo extends Page  implements Serializable {

	private static final long serialVersionUID = -6630600692804042954L;
	
	/**매입처코드*/
	private String buyCd;
	/**매입처명*/
	private String buyNm;
	/**구분*/
	private String classNm;
	/**1월*/
	private String mon1;
	/**2월*/
	private String mon2;
	/**3월*/
	private String mon3;
	/**4월*/
	private String mon4;
	/**5월*/
	private String mon5;
	/**6월*/
	private String mon6;
	/**7월 */
	private String mon7;
	/**8월 */
	private String mon8;
	/**9월 */
	private String mon9;
	/**10월 */
	private String mon10;
	/**11월 */
	private String mon11;
	/**12월 */
	private String mon12;
	/**총합계 */
	private String totAmt;
	
	
	/**1월*/
	private double prtMon1;
	/**2월*/
	private double prtMon2;
	/**3월*/
	private double prtMon3;
	/**4월*/
	private double prtMon4;
	/**5월*/
	private double prtMon5;
	/**6월*/
	private double prtMon6;
	/**7월 */
	private double prtMon7;
	/**8월 */
	private double prtMon8;
	/**9월 */
	private double prtMon9;
	/**10월 */
	private double prtMon10;
	/**11월 */
	private double prtMon11;
	/**12월 */
	private double prtMon12;
	/**총합계 */
	private double prtTotAmt;
	//시작월
	private String startDt;
	/**종료월*/
	private String endDt;
	
	//출력일자
	private String printDt;
	

	/*검색 시작일*/
	private String searchYearMnSt;
	/*검색 종료일*/
	private String searchYearMnEt;
	/*검색 종료일*/
	private String searchBuyCd;
	/*검색 종료일*/
	private String searchBuyNm;
	
	
	
	public String getPrintDt() {
		return printDt;
	}
	public void setPrintDt(String printDt) {
		this.printDt = printDt;
	}
	
	public String getSearchBuyNm() {
		return searchBuyNm;
	}
	public void setSearchBuyNm(String searchBuyNm) {
		this.searchBuyNm = searchBuyNm;
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
	public double getPrtMon1() {
		return prtMon1;
	}
	public void setPrtMon1(double prtMon1) {
		this.prtMon1 = prtMon1;
	}
	public double getPrtMon2() {
		return prtMon2;
	}
	public void setPrtMon2(double prtMon2) {
		this.prtMon2 = prtMon2;
	}
	public double getPrtMon3() {
		return prtMon3;
	}
	public void setPrtMon3(double prtMon3) {
		this.prtMon3 = prtMon3;
	}
	public double getPrtMon4() {
		return prtMon4;
	}
	public void setPrtMon4(double prtMon4) {
		this.prtMon4 = prtMon4;
	}
	public double getPrtMon5() {
		return prtMon5;
	}
	public void setPrtMon5(double prtMon5) {
		this.prtMon5 = prtMon5;
	}
	public double getPrtMon6() {
		return prtMon6;
	}
	public void setPrtMon6(double prtMon6) {
		this.prtMon6 = prtMon6;
	}
	public double getPrtMon7() {
		return prtMon7;
	}
	public void setPrtMon7(double prtMon7) {
		this.prtMon7 = prtMon7;
	}
	public double getPrtMon8() {
		return prtMon8;
	}
	public void setPrtMon8(double prtMon8) {
		this.prtMon8 = prtMon8;
	}
	public double getPrtMon9() {
		return prtMon9;
	}
	public void setPrtMon9(double prtMon9) {
		this.prtMon9 = prtMon9;
	}
	public double getPrtMon10() {
		return prtMon10;
	}
	public void setPrtMon10(double prtMon10) {
		this.prtMon10 = prtMon10;
	}
	public double getPrtMon11() {
		return prtMon11;
	}
	public void setPrtMon11(double prtMon11) {
		this.prtMon11 = prtMon11;
	}
	public double getPrtMon12() {
		return prtMon12;
	}
	public void setPrtMon12(double prtMon12) {
		this.prtMon12 = prtMon12;
	}
	public double getPrtTotAmt() {
		return prtTotAmt;
	}
	public void setPrtTotAmt(double prtTotAmt) {
		this.prtTotAmt = prtTotAmt;
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
	public String getClassNm() {
		return classNm;
	}
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	public String getMon1() {
		return mon1;
	}
	public void setMon1(String mon1) {
		this.mon1 = mon1;
	}
	public String getMon2() {
		return mon2;
	}
	public void setMon2(String mon2) {
		this.mon2 = mon2;
	}
	public String getMon3() {
		return mon3;
	}
	public void setMon3(String mon3) {
		this.mon3 = mon3;
	}
	public String getMon4() {
		return mon4;
	}
	public void setMon4(String mon4) {
		this.mon4 = mon4;
	}
	public String getMon5() {
		return mon5;
	}
	public void setMon5(String mon5) {
		this.mon5 = mon5;
	}
	public String getMon6() {
		return mon6;
	}
	public void setMon6(String mon6) {
		this.mon6 = mon6;
	}
	public String getMon7() {
		return mon7;
	}
	public void setMon7(String mon7) {
		this.mon7 = mon7;
	}
	public String getMon8() {
		return mon8;
	}
	public void setMon8(String mon8) {
		this.mon8 = mon8;
	}
	public String getMon9() {
		return mon9;
	}
	public void setMon9(String mon9) {
		this.mon9 = mon9;
	}
	public String getMon10() {
		return mon10;
	}
	public void setMon10(String mon10) {
		this.mon10 = mon10;
	}
	public String getMon11() {
		return mon11;
	}
	public void setMon11(String mon11) {
		this.mon11 = mon11;
	}
	public String getMon12() {
		return mon12;
	}
	public void setMon12(String mon12) {
		this.mon12 = mon12;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	
	public String getSearchYearMnSt() {
		return searchYearMnSt;
	}
	public void setSearchYearMnSt(String searchYearMnSt) {
		this.searchYearMnSt = searchYearMnSt;
	}
	public String getSearchYearMnEt() {
		return searchYearMnEt;
	}
	public void setSearchYearMnEt(String searchYearMnEt) {
		this.searchYearMnEt = searchYearMnEt;
	}
	public String getSearchBuyCd() {
		return searchBuyCd;
	}
	public void setSearchBuyCd(String searchBuyCd) {
		this.searchBuyCd = searchBuyCd;
	}
	
	
	
	

}
