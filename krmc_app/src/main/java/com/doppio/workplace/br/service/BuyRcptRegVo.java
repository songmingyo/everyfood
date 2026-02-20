package com.doppio.workplace.br.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 매입처 입고 vo
 * @Class : BuyRcptRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class BuyRcptRegVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 발주일자 */
	private String ordDt;
	/** 매입처 */
	private String buyCd;
	/** 상품코드 */
	private String prdtCd;
	/** 창고코드 */
	private String whCd;
	/** 창고명 */
	private String whNm;
	/** 입고일자 */
	private String buyDt;
	/** 비고 */
	private String remarks;
	/** 매입처명 */
	private String buyNm;
	/** 상품명 */
	private String prdtNm;
	/** 상품규격 */
	private String prdtStd;
	/** 주문단위 */
	private String ordUnit;
	/** 박스 환산 수량 */
	private String boxQty;
	/** 부가세유무 */
	private String vatYn;
	/** 등록자 */
	private String regNm;
	/** 등록시간 */
	private String regDt;
	/** 수정자 */
	private String modNm;
	/** 수정시간 */
	private String modDt;
	/** 유통기한 */
	private String exprtDt;
	/** 소비기한 */
	private String termVal;
	/** 전표번호 */
	private String buySlipNo;
	/** 순번 */
	private String buySeq;
	
	/** 데이터 상태 */
	private String gridFlag;
	/** 작업자 */
	private String workId;
	/** 입고유무 */
	private String buyConfYn;
	/** 소비기한입력유무 */
	private String termValYn;
	/** 박스당 수량 */
	private String qtyBox;
	/** 규격유무 */
	private String stdYn;
	
	
	
	/** 단가 */
	private String cost;
	/** 발주일자 */
	private String ordQty;
	/** 입고일자 */
	private String buyQty;
	/** 공급가 */
	private String pureAmt;
	/** 부가세 */
	private String vatAmt;
	/** 총금액 */
	private String totAmt;
	
	/** 매입일자 시작일 */
	private String buyStartDt;
	/** 매입일자 종료일 */
	private String buyEndDt;
	
	/** 입고수량 원 */
	private String buyQtyOrg;
	/** 공급가 원 */
	private String pureAmtOrg;
	/** 부가세 원 */
	private String vatAmtOrg;
	
	
	/** 그리드 저장 데이터 */
	private List<BuyRcptRegVo> buyRcptRegList;
	

	
	
	public String getVatAmtOrg() {
		return vatAmtOrg;
	}
	public void setVatAmtOrg(String vatAmtOrg) {
		this.vatAmtOrg = vatAmtOrg;
	}
	public String getBuyQtyOrg() {
		return buyQtyOrg;
	}
	public void setBuyQtyOrg(String buyQtyOrg) {
		this.buyQtyOrg = buyQtyOrg;
	}
	public String getPureAmtOrg() {
		return pureAmtOrg;
	}
	public void setPureAmtOrg(String pureAmtOrg) {
		this.pureAmtOrg = pureAmtOrg;
	}
	public List<BuyRcptRegVo> getBuyRcptRegList() {
		return buyRcptRegList;
	}
	public void setBuyRcptRegList(List<BuyRcptRegVo> buyRcptRegList) {
		this.buyRcptRegList = buyRcptRegList;
	}
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
	public String getExprtDt() {
		return exprtDt;
	}
	public void setExprtDt(String exprtDt) {
		this.exprtDt = exprtDt;
	}
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getModNm() {
		return modNm;
	}
	public void setModNm(String modNm) {
		this.modNm = modNm;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getBuyConfYn() {
		return buyConfYn;
	}
	public void setBuyConfYn(String buyConfYn) {
		this.buyConfYn = buyConfYn;
	}
	public String getTermValYn() {
		return termValYn;
	}
	public void setTermValYn(String termValYn) {
		this.termValYn = termValYn;
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
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
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
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}
	public String getBuyDt() {
		return buyDt;
	}
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public String getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
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
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}
	public String getStdYn() {
		return stdYn;
	}
	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}
	
	
	
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
	}
	public String getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(String buyQty) {
		this.buyQty = buyQty;
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
	public String getBuyDtFmt() {
		return DateUtil.convertDateFmt(this.buyDt, "", "");
	}
	public String getOrdDtFmt() {
		return DateUtil.convertDateFmt(this.ordDt, "", "");
	}
	

}
