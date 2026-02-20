package com.doppio.workplace.sm.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;

/**
 * @author Song
 * @Description : 매출 단가 일괄수정 vo
 * @Class : CusPriceUptVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class CusPriceUptVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	private List<CusPriceUptVo> cusPriceUptList;
	
	/** 매출처 */
	private String salesSlipNo;
	/** 매출처 */
	private String salesSeq;
	/** 매출처 */
	private String salesCd;
	/** 상품코드 */
	private String prdtCd;
	/** 입고일자 */
	private String salesDt;
	/** 매출처명 */
	private String salesNm;
	/** 상품명 */
	private String prdtNm;
	/** 상품규격 */
	private String prdtStd;
	/** 주문단위 */
	private String ordUnit;
	/** 부가세유무 */
	private String vatYn;
	/** 단가 */
	private Double price;
	/** 입고수량 */
	private Double salesQty;
	/** 공급가 */
	private Double pureAmt;
	/** 부가세 */
	private Double vatAmt;
	/** 총금액 */
	private Double totAmt;
	
	/** 조회용 시작일자 */
	private String salesStartDt;
	/** 조회용 종료일자 */
	private String salesEndDt;
	/** 본사구분 */
	private String hqClass;
	/** 본사 코드 */
	private String hqCd;
	
	/** 작업자 */
	private String workId;
	/** 그리드 상태 */
	private String gridFlag;
	
	
	
	public String getSalesSlipNo() {
		return salesSlipNo;
	}
	public void setSalesSlipNo(String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}
	public String getSalesSeq() {
		return salesSeq;
	}
	public void setSalesSeq(String salesSeq) {
		this.salesSeq = salesSeq;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	public String getHqCd() {
		return hqCd;
	}
	public void setHqCd(String hqCd) {
		this.hqCd = hqCd;
	}
	public List<CusPriceUptVo> getCusPriceUptList() {
		return cusPriceUptList;
	}
	public void setCusPriceUptList(List<CusPriceUptVo> cusPriceUptList) {
		this.cusPriceUptList = cusPriceUptList;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getSalesDt() {
		return salesDt;
	}
	public void setSalesDt(String salesDt) {
		this.salesDt = salesDt;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
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
	public String getOrdUnit() {
		return ordUnit;
	}
	public void setOrdUnit(String ordUnit) {
		this.ordUnit = ordUnit;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(Double salesQty) {
		this.salesQty = salesQty;
	}
	public Double getPureAmt() {
		return pureAmt;
	}
	public void setPureAmt(Double pureAmt) {
		this.pureAmt = pureAmt;
	}
	public Double getVatAmt() {
		return vatAmt;
	}
	public void setVatAmt(Double vatAmt) {
		this.vatAmt = vatAmt;
	}
	public Double getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(Double totAmt) {
		this.totAmt = totAmt;
	}
	public String getSalesStartDt() {
		return salesStartDt;
	}
	public void setSalesStartDt(String salesStartDt) {
		this.salesStartDt = salesStartDt;
	}
	public String getSalesEndDt() {
		return salesEndDt;
	}
	public void setSalesEndDt(String salesEndDt) {
		this.salesEndDt = salesEndDt;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	
	
	
	
	
}
