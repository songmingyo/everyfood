package com.doppio.workplace.br.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;

/**
 * @author Song
 * @Description : 매입 단가 일괄수정 vo
 * @Class : BuyCostUptVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class BuyCostUptVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	private List<BuyCostUptVo> buyCostUptList;
	
	/** 전표번호 */
	private String buySlipNo;
	/** 전표순번 */
	private String buySeq;
	/** 매입처 */
	private String buyCd;
	/** 상품코드 */
	private String prdtCd;
	/** 입고일자 */
	private String buyDt;
	/** 매입처명 */
	private String buyNm;
	/** 상품명 */
	private String prdtNm;
	/** 상품규격 */
	private String prdtStd;
	/** 주문단위 */
	private String ordUnit;
	/** 부가세유무 */
	private String vatYn;
	/** 단가 */
	private Double cost;
	/** 입고수량 */
	private Double buyQty;
	/** 공급가 */
	private Double pureAmt;
	/** 부가세 */
	private Double vatAmt;
	/** 총금액 */
	private Double totAmt;
	
	/** 조회용 시작일자 */
	private String buyStartDt;
	/** 조회용 종료일자 */
	private String buyEndDt;
	
	/** 작업자 */
	private String workId;
	/** 그리드 상태 */
	private String gridFlag;
	
	
	
	public String getBuySlipNo() {
		return buySlipNo;
	}
	public void setBuySlipNo(String buySlipNo) {
		this.buySlipNo = buySlipNo;
	}
	public String getBuySeq() {
		return buySeq;
	}
	public void setBuySeq(String buySeq) {
		this.buySeq = buySeq;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public List<BuyCostUptVo> getBuyCostUptList() {
		return buyCostUptList;
	}
	public void setBuyCostUptList(List<BuyCostUptVo> buyCostUptList) {
		this.buyCostUptList = buyCostUptList;
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
	public String getBuyCd() {
		return buyCd;
	}
	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getBuyDt() {
		return buyDt;
	}
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public Double getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(Double buyQty) {
		this.buyQty = buyQty;
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
	
	
}
