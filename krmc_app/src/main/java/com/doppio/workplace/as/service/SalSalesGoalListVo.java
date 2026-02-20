package com.doppio.workplace.as.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 엽업목표현황
 * @Class : SalSalesGoalListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class SalSalesGoalListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 영업사원 */
	private String salesPrNm;
	/** 구분 */
	private String gubun;
	/** 1월 */
	private String mon1;
	/** 2월 */
	private String mon2;
	/** 3월 */
	private String mon3;
	/** 4월 */
	private String mon4;
	/** 5월 */
	private String mon5;
	/** 6월 */
	private String mon6;
	/** 7월 */
	private String mon7;
	/** 8월 */
	private String mon8;
	/** 9월 */
	private String mon9;
	/** 10월 */
	private String mon10;
	/** 11월 */
	private String mon11;
	/** 12월 */
	private String mon12;
	/** 합계 */
	private String totAmt;
	
	/** 조회조건 년도 */
	private String goalYear;
	
	
	
	public String getGoalYear() {
		return goalYear;
	}
	public void setGoalYear(String goalYear) {
		this.goalYear = goalYear;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
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
	

	
	

}
