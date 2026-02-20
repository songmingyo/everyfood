package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처출고현황(농산물)   vo
 * @Class : CusShipAgrclListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.05 j10000
 * </pre>
 * @version : 1.0
 */
public class CusShipAgrclListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;



	/**전표번호*/
	private String salesSlipNo;
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
	/**입고일자*/
	private String dt;
	/**벤더구분*/
	private String dtlNm;
	/**수량*/
	private String qty;
	/**단위*/
	private String ordUnitNm;
	/**창고명*/
	private String whNm;
	/**배송차량명*/
	private String carNm;
	/**랙번호*/
	private String lackNo1;
	/**저장방법*/
	private String saveKind;
	/**구분*/
	private String gubun;
	//매출처구분
	private String salesClassNm;
	
	
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	/**조회용 매출처명*/
	private String salesNmSearch;
	/**조회용 품목명*/
	private String prdtNmSearch;
	
	
	
	public String getSalesClassNm() {
		return salesClassNm;
	}
	public void setSalesClassNm(String salesClassNm) {
		this.salesClassNm = salesClassNm;
	}
	public String getSalesSlipNo() {
		return salesSlipNo;
	}
	public void setSalesSlipNo(String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
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
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getDtlNm() {
		return dtlNm;
	}
	public void setDtlNm(String dtlNm) {
		this.dtlNm = dtlNm;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getCarNm() {
		return carNm;
	}
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getSaveKind() {
		return saveKind;
	}
	public void setSaveKind(String saveKind) {
		this.saveKind = saveKind;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
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
	public String getSalesNmSearch() {
		return salesNmSearch;
	}
	public void setSalesNmSearch(String salesNmSearch) {
		this.salesNmSearch = salesNmSearch;
	}
	public String getPrdtNmSearch() {
		return prdtNmSearch;
	}
	public void setPrdtNmSearch(String prdtNmSearch) {
		this.prdtNmSearch = prdtNmSearch;
	}
	
	
	
	
}
