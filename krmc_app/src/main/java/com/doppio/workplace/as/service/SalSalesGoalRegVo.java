package com.doppio.workplace.as.service;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;

import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;
import com.doppio.common.model.Page;


/**
 * @author dada
 * @Description :매출처출고  vo
 * @Class : CusSalesDlvVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  23.04.08   dada       
 * </pre>
 * @version : 1.0
 */

public class SalSalesGoalRegVo  extends Page  implements Serializable {

	private static final long serialVersionUID = 5746453328068624800L;
	
	private List<SalSalesGoalRegVo>  salSalesGoalRegList;
	
	/** 사원코드 */
	private String salesPrCd	 ;
	/** 사원명 */
	private String salesPrNm	 ;
	/** 1월 */
	private String goalAmt01	 ;
	/** 2월 */
	private String goalAmt02	 ;
	/** 3월 */
	private String goalAmt03	 ;
	/** 4월 */
	private String goalAmt04	 ;
	/** 5월 */
	private String goalAmt05	 ;
	/** 6월 */
	private String goalAmt06	 ;
	/** 7월 */
	private String goalAmt07	 ;
	/** 8월 */
	private String goalAmt08	 ;
	/** 9월 */
	private String goalAmt09	 ;
	/** 10월 */
	private String goalAmt10	 ;
	/** 11월 */
	private String goalAmt11	 ;
	/** 12월 */
	private String goalAmt12	 ;
	/** 합계 */
	private String goalTot       ;
	
	/** 작업자 */
	private String workId  ;
	/** 조회조건 년도 */
	private String goalYear  ;
	/** 월 */
	private String goalMon   ;
	/** 금액 */
	private String goalAmt   ;
	
	
	
	public String getGoalAmt() {
		return goalAmt;
	}
	public void setGoalAmt(String goalAmt) {
		this.goalAmt = goalAmt;
	}
	public String getGoalMon() {
		return goalMon;
	}
	public void setGoalMon(String goalMon) {
		this.goalMon = goalMon;
	}
	public String getGoalYear() {
		return goalYear;
	}
	public void setGoalYear(String goalYear) {
		this.goalYear = goalYear;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public List<SalSalesGoalRegVo> getSalSalesGoalRegList() {
		return salSalesGoalRegList;
	}
	public void setSalSalesGoalRegList(List<SalSalesGoalRegVo> salSalesGoalRegList) {
		this.salSalesGoalRegList = salSalesGoalRegList;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getGoalAmt01() {
		return goalAmt01;
	}
	public void setGoalAmt01(String goalAmt01) {
		this.goalAmt01 = goalAmt01;
	}
	public String getGoalAmt02() {
		return goalAmt02;
	}
	public void setGoalAmt02(String goalAmt02) {
		this.goalAmt02 = goalAmt02;
	}
	public String getGoalAmt03() {
		return goalAmt03;
	}
	public void setGoalAmt03(String goalAmt03) {
		this.goalAmt03 = goalAmt03;
	}
	public String getGoalAmt04() {
		return goalAmt04;
	}
	public void setGoalAmt04(String goalAmt04) {
		this.goalAmt04 = goalAmt04;
	}
	public String getGoalAmt05() {
		return goalAmt05;
	}
	public void setGoalAmt05(String goalAmt05) {
		this.goalAmt05 = goalAmt05;
	}
	public String getGoalAmt06() {
		return goalAmt06;
	}
	public void setGoalAmt06(String goalAmt06) {
		this.goalAmt06 = goalAmt06;
	}
	public String getGoalAmt07() {
		return goalAmt07;
	}
	public void setGoalAmt07(String goalAmt07) {
		this.goalAmt07 = goalAmt07;
	}
	public String getGoalAmt08() {
		return goalAmt08;
	}
	public void setGoalAmt08(String goalAmt08) {
		this.goalAmt08 = goalAmt08;
	}
	public String getGoalAmt09() {
		return goalAmt09;
	}
	public void setGoalAmt09(String goalAmt09) {
		this.goalAmt09 = goalAmt09;
	}
	public String getGoalAmt10() {
		return goalAmt10;
	}
	public void setGoalAmt10(String goalAmt10) {
		this.goalAmt10 = goalAmt10;
	}
	public String getGoalAmt11() {
		return goalAmt11;
	}
	public void setGoalAmt11(String goalAmt11) {
		this.goalAmt11 = goalAmt11;
	}
	public String getGoalAmt12() {
		return goalAmt12;
	}
	public void setGoalAmt12(String goalAmt12) {
		this.goalAmt12 = goalAmt12;
	}
	public String getGoalTot() {
		return goalTot;
	}
	public void setGoalTot(String goalTot) {
		this.goalTot = goalTot;
	}
	
	
	
	

}
