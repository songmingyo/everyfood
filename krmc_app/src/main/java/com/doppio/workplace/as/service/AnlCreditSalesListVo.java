package com.doppio.workplace.as.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

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
public class AnlCreditSalesListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**매출처구분*/
	private String salesClassNm;
	/**매출처코드*/
	private String salesCd;
	/**매출처명*/
	private String salesNm;
	/**전전월잔액*/
	private double balAmt1;
	/**전월매출*/
	private double salesAmt2;
	/**전월입금*/
	private double depAmt2;
	/**전월잔액 */
	private double balAmt2;
	/**당월매출*/
	private double salesAmt3;
	/**당월입금*/
	private double depAmt3;
	/**당월잔액*/
	private double balAmt3;
	/**지급구분*/
	private String setlCon;
	/**여신한도*/
	private double creLim;
	/**영업사원명*/
	private String salesPrNm;
	/**사업자 번호*/
	private String bsnsNum;
	
	
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	/**판매구분*/
	private String salesClass;
	/**본사구분 */
	private String searchHqCd;
	/**영업사원*/
	private String salesPrCd;
	
	/**매출처명조회 */
	private String salesNmSearch;
	/**영업사원조회*/
	private String salesPrNmSearch;
	
	
	private double prtBalAmt1;
	/**전월매출*/
	private double prtSalesAmt2;
	/**전월입금*/
	private double prtDepAmt2;
	/**전월잔액 */
	private double prtBalAmt2;
	/**당월매출*/
	private double prtSalesAmt3;
	/**당월입금*/
	private double prtDepAmt3;
	/**당월잔액*/
	private double prtBalAmt3;
	/**여신한도*/
	private double prtCreLim;
	
	private String title1;
	private String title2;
	private String title3;
	private String title4;
	private String title5;
	private String title6;
	private String title7;
	
	private String gridDataInput;
	private String searchHqCdNm;
	
	public String getSearchHqCdNm() {
		return searchHqCdNm;
	}

	public void setSearchHqCdNm(String searchHqCdNm) {
		this.searchHqCdNm = searchHqCdNm;
	}

	public String getSalesClassNm() {
		return salesClassNm;
	}

	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
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

	public double getBalAmt1() {
		return balAmt1;
	}

	public void setBalAmt1(double balAmt1) {
		this.balAmt1 = balAmt1;
	}

	public double getSalesAmt2() {
		return salesAmt2;
	}

	public void setSalesAmt2(double salesAmt2) {
		this.salesAmt2 = salesAmt2;
	}

	public double getDepAmt2() {
		return depAmt2;
	}

	public void setDepAmt2(double depAmt2) {
		this.depAmt2 = depAmt2;
	}

	public double getBalAmt2() {
		return balAmt2;
	}

	public void setBalAmt2(double balAmt2) {
		this.balAmt2 = balAmt2;
	}

	public double getSalesAmt3() {
		return salesAmt3;
	}

	public void setSalesAmt3(double salesAmt3) {
		this.salesAmt3 = salesAmt3;
	}

	public double getDepAmt3() {
		return depAmt3;
	}

	public void setDepAmt3(double depAmt3) {
		this.depAmt3 = depAmt3;
	}

	public double getBalAmt3() {
		return balAmt3;
	}

	public void setBalAmt3(double balAmt3) {
		this.balAmt3 = balAmt3;
	}

	public String getSetlCon() {
		return setlCon;
	}

	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}

	public double getCreLim() {
		return creLim;
	}

	public void setCreLim(double creLim) {
		this.creLim = creLim;
	}

	public String getSalesPrNm() {
		return salesPrNm;
	}

	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}

	public String getBsnsNum() {
		return bsnsNum;
	}

	public void setBsnsNum(String bsnsNum) {
		this.bsnsNum = bsnsNum;
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

	public String getSalesClass() {
		return salesClass;
	}

	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}

	public String getSearchHqCd() {
		return searchHqCd;
	}

	public void setSearchHqCd(String searchHqCd) {
		this.searchHqCd = searchHqCd;
	}

	public String getSalesPrCd() {
		return salesPrCd;
	}

	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}

	public String getSalesNmSearch() {
		return salesNmSearch;
	}

	public void setSalesNmSearch(String salesNmSearch) {
		this.salesNmSearch = salesNmSearch;
	}

	public String getSalesPrNmSearch() {
		return salesPrNmSearch;
	}

	public void setSalesPrNmSearch(String salesPrNmSearch) {
		this.salesPrNmSearch = salesPrNmSearch;
	}

	public double getPrtBalAmt1() {
		return prtBalAmt1;
	}

	public void setPrtBalAmt1(double prtBalAmt1) {
		this.prtBalAmt1 = prtBalAmt1;
	}

	public double getPrtSalesAmt2() {
		return prtSalesAmt2;
	}

	public void setPrtSalesAmt2(double prtSalesAmt2) {
		this.prtSalesAmt2 = prtSalesAmt2;
	}

	public double getPrtDepAmt2() {
		return prtDepAmt2;
	}

	public void setPrtDepAmt2(double prtDepAmt2) {
		this.prtDepAmt2 = prtDepAmt2;
	}

	public double getPrtBalAmt2() {
		return prtBalAmt2;
	}

	public void setPrtBalAmt2(double prtBalAmt2) {
		this.prtBalAmt2 = prtBalAmt2;
	}

	public double getPrtSalesAmt3() {
		return prtSalesAmt3;
	}

	public void setPrtSalesAmt3(double prtSalesAmt3) {
		this.prtSalesAmt3 = prtSalesAmt3;
	}

	public double getPrtDepAmt3() {
		return prtDepAmt3;
	}

	public void setPrtDepAmt3(double prtDepAmt3) {
		this.prtDepAmt3 = prtDepAmt3;
	}

	public double getPrtBalAmt3() {
		return prtBalAmt3;
	}

	public void setPrtBalAmt3(double prtBalAmt3) {
		this.prtBalAmt3 = prtBalAmt3;
	}

	public double getPrtCreLim() {
		return prtCreLim;
	}

	public void setPrtCreLim(double prtCreLim) {
		this.prtCreLim = prtCreLim;
	}

	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public String getTitle5() {
		return title5;
	}

	public void setTitle5(String title5) {
		this.title5 = title5;
	}

	public String getTitle6() {
		return title6;
	}

	public void setTitle6(String title6) {
		this.title6 = title6;
	}

	public String getTitle7() {
		return title7;
	}

	public void setTitle7(String title7) {
		this.title7 = title7;
	}

	public String getGridDataInput() {
		return gridDataInput;
	}

	public void setGridDataInput(String gridDataInput) {
		this.gridDataInput = gridDataInput;
	}
	
	
	
	
	

}
