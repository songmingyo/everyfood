package com.doppio.workplace.br.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 매입처 반품 관리  vo
 * @Class : BuyRtnRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class BuyRtnRegVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 매입처 */
	private String buyCd;
	/** 상품코드 */
	private String prdtCd;
	/** 창고코드 */
	private String whCd;
	/** 창고명 */
	private String whNm;
	/** 반품일자 */
	private String rtnDt;
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
	/** 소비기한 */
	private String termVal;
	/** 전표번호 */
	private String buySlipNo;
	/** 순번 */
	private String buySeq;
	/** 반품유형 */
	private String rtnClass;
	
	/** 데이터 상태 */
	private String gridFlag;
	/** 작업자 */
	private String workId;
	/** 박스당수량 */
	private String qtyBox;
	/** 규격유무 */
	private String stdYn;
	
	
	/** 단가 */
	private Double cost;
	/** 반품수량 */
	private Double rtnQty;
	/** 공급가 */
	private Double pureAmt;
	/** 부가세 */
	private Double vatAmt;
	/** 총금액 */
	private Double totAmt;
	
	/** 반품수량 */
	private Double rtnQtyOrg;
	/** 공급가 */
	private Double pureAmtOrg;
	/** 부가세 */
	private Double vatAmtOrg;
	
	
	/** 그리드 저장 데이터 */
	private List<BuyRtnRegVo> buyRtnRegList;
	
	private String useYn;               /* 사용유무 */       
	private String modId;               /* 수정자 id*/           
	private String rtnClassNm;         	/* 반품구분 코드 명 */       
	private String regId;               /* 등록자 이름 */     
	private String srchRtnClass;		/* 검색조건_반품구분 */
	private String srchPrdt;			/* 검색조건_품목코드 */
	private String srchFrDt;			/* 검색조건_시작일자 */
	private String srchToDt;	 		/* 검색조건_종료일자 */
	private String srchBuyCd;			/* 검색조건_매입처 */
				
	public String getSrchBuyCd() {
		return srchBuyCd;
	}
	public void setSrchBuyCd(String srchBuyCd) {
		this.srchBuyCd = srchBuyCd;
	}
	public String getSrchRtnClass() {
		return srchRtnClass;
	}
	public void setSrchRtnClass(String srchRtnClass) {
		this.srchRtnClass = srchRtnClass;
	}
	public String getSrchPrdt() {
		return srchPrdt;
	}
	public void setSrchPrdt(String srchPrdt) {
		this.srchPrdt = srchPrdt;
	}
	public String getSrchFrDt() {
		return srchFrDt;
	}
	public void setSrchFrDt(String srchFrDt) {
		this.srchFrDt = srchFrDt;
	}
	public String getSrchToDt() {
		return srchToDt;
	}
	public void setSrchToDt(String srchToDt) {
		this.srchToDt = srchToDt;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getRtnClassNm() {
		return rtnClassNm;
	}
	public void setRtnClassNm(String rtnClassNm) {
		this.rtnClassNm = rtnClassNm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public Double getRtnQtyOrg() {
		return rtnQtyOrg;
	}
	public void setRtnQtyOrg(Double rtnQtyOrg) {
		this.rtnQtyOrg = rtnQtyOrg;
	}
	public Double getPureAmtOrg() {
		return pureAmtOrg;
	}
	public void setPureAmtOrg(Double pureAmtOrg) {
		this.pureAmtOrg = pureAmtOrg;
	}
	public Double getVatAmtOrg() {
		return vatAmtOrg;
	}
	public void setVatAmtOrg(Double vatAmtOrg) {
		this.vatAmtOrg = vatAmtOrg;
	}
	public String getRtnClass() {
		return rtnClass;
	}
	public void setRtnClass(String rtnClass) {
		this.rtnClass = rtnClass;
	}
	public String getBuyCd() {
		return buyCd;
	}
	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}
	public String getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
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
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
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
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
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
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
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
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}	
	public Double getRtnQty() {
		return rtnQty;
	}
	public void setRtnQty(Double rtnQty) {
		this.rtnQty = rtnQty;
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
	public List<BuyRtnRegVo> getBuyRtnRegList() {
		return buyRtnRegList;
	}
	public void setBuyRtnRegList(List<BuyRtnRegVo> buyRtnRegList) {
		this.buyRtnRegList = buyRtnRegList;
	}
	public String getRtnDt() {
		return rtnDt;
	}
	public void setRtnDt(String rtnDt) {
		this.rtnDt = rtnDt;
	}
	
	public String getrtnDtFmt() {
		String rtnVal = this.rtnDt;
		if(!StringUtils.isEmpty(rtnVal))
			rtnVal = DateUtil.convertDate(rtnVal, "yyyyMMdd", "yyyy-MM-dd");
		return rtnVal;
	}
	
}
