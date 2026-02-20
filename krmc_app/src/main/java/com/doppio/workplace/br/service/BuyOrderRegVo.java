package com.doppio.workplace.br.service;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;
import java.util.List;

/**
 * @author Song
 * @Description : 매입처 발주 관리  vo
 * @Class : BuyOrderRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class BuyOrderRegVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 전표번호 */
	private String buySlipNo;
	/** 발주일자 */
	private String ordDt;
	/** 매입처 */
	private String buyCd;
	/** 상품코드 */
	private String prdtCd;
	/** 창고코드 */
	private String whCd;
	/** 입고일자 */
	private String buyDt;
	/** 순번 */
	private String buySeq;
	
	/** 비고 */
	private String remarks;
	/** 매입처명 */
	private String buyNm;
	/** 랙번호 */
	private String lackNo1;
	/** 상품명 */
	private String prdtNm;
	/** 상품규격 */
	private String prdtStd;
	/** 주문단위 */
	private String ordUnit;
	/** 지난달출고금액 */
	private String prevSalesQty;
	/** 이번달출고금액 */
	private String curSalesQty;
	/** 추간출고금액 */
	private String weekSalesQty;
	/** 하남재고 */
	private String haStkQty;
	/** 여주재고 */
	private String yeoStkQty;
	/** 재고일수 */
	private String stkDay;
	/** 박스 환산 수량 */
	private String boxQty;
	/** 부가세유무 */
	private String vatYn;
	//원 발주 수량
	private String ordQtyOrg;

	/** 단가 */
	private String cost;
	/** 발주일자 */
	private String ordQty;
	/** 공급가 */
	private String pureAmt;
	/** 부가세 */
	private String vatAmt;
	/** 총금액 */
	private String totAmt;
	
	/** 발주 비고 */
	private String ordNote;
	
	/** 데이터 상태 */
	private String gridFlag;
	/** 작업자 */
	private String workId;
	/** 발주유무 */
	private String ordYn;
	/** 박스당 수량 */
	private String qtyBox;
	/** 규격유무 */
	private String stdYn;
	
	/** 회사명 */
	private String companyNm;
	/** 회사주소 */
	private String companyAddr;
	/** 회사주소 서브 */
	private String companyAddrSub;
	/** 회사전화번호 */
	private String companyTelNo;
	/** 회사 팩스번호 */
	private String companyFaxNo;
	
	/** 입고시 알림사항1 */
	private String buyComment1;
	/** 입고시 알림사항2 */
	private String buyComment2;
	/** 입고시 알림사항3 */
	private String buyComment3;
	
	/** 매입처 전화번호 */
	private String telNo;
	/** 매입처 팩스번호 */
	private String faxNo;
	/** 담당자및 연락처 */
	private String salesPrHp;
	/** 결제방법 */
	private String setlCon;
	/** 행번호 */
	private String rowNum;
	
	
	
	/** 단가 */
	private double prtCost;
	/** 발주일자 */
	private double prtOrdQty;
	/** 공급가 */
	private double prtPureAmt;
	/** 부가세 */
	private double prtVatAmt;
	/** 총금액 */
	private double prtTotAmt;
	
	/** 공급가 합계 */
	private double prtPureSumAmt;
	/** 부가세 합계 */
	private double prtVatSumAmt;
	/** 총금액 합계 */
	private double prtTotSumAmt;
	//출력 컬럼수
	private double columnCnt;
	
	
	
	/** 임시전표번호 */
	private String buySlipNoTemp;
	/** 조회용 발주일자 */
	private String searchOrdDt;
	/** 조회용 입고일자 */
	private String searchBuyDt;
	private String searchOrdYn;
	
	/** 그리드 저장 데이터 */
	private List<BuyOrderRegVo> buyOrderRegList;
	
	/** 사용여부 */
	private String useYn;
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSearchOrdYn() {
		return searchOrdYn;
	}
	public void setSearchOrdYn(String searchOrdYn) {
		this.searchOrdYn = searchOrdYn;
	}
	public double getColumnCnt() {
		return columnCnt;
	}
	public void setColumnCnt(double columnCnt) {
		this.columnCnt = columnCnt;
	}
	public double getPrtCost() {
		return prtCost;
	}
	public void setPrtCost(double prtCost) {
		this.prtCost = prtCost;
	}
	
	public double getPrtOrdQty() {
		return prtOrdQty;
	}
	public void setPrtOrdQty(double prtOrdQty) {
		this.prtOrdQty = prtOrdQty;
	}
	public double getPrtPureAmt() {
		return prtPureAmt;
	}
	public void setPrtPureAmt(double prtPureAmt) {
		this.prtPureAmt = prtPureAmt;
	}
	public double getPrtVatAmt() {
		return prtVatAmt;
	}
	public void setPrtVatAmt(double prtVatAmt) {
		this.prtVatAmt = prtVatAmt;
	}
	public double getPrtTotAmt() {
		return prtTotAmt;
	}
	public void setPrtTotAmt(double prtTotAmt) {
		this.prtTotAmt = prtTotAmt;
	}
	public double getPrtPureSumAmt() {
		return prtPureSumAmt;
	}
	public void setPrtPureSumAmt(double prtPureSumAmt) {
		this.prtPureSumAmt = prtPureSumAmt;
	}
	public double getPrtVatSumAmt() {
		return prtVatSumAmt;
	}
	public void setPrtVatSumAmt(double prtVatSumAmt) {
		this.prtVatSumAmt = prtVatSumAmt;
	}
	public double getPrtTotSumAmt() {
		return prtTotSumAmt;
	}
	public void setPrtTotSumAmt(double prtTotSumAmt) {
		this.prtTotSumAmt = prtTotSumAmt;
	}
	public String getOrdQtyOrg() {
		return ordQtyOrg;
	}
	public void setOrdQtyOrg(String ordQtyOrg) {
		this.ordQtyOrg = ordQtyOrg;
	}
	public List<BuyOrderRegVo> getBuyOrderRegList() {
		return buyOrderRegList;
	}
	public void setBuyOrderRegList(List<BuyOrderRegVo> buyOrderRegList) {
		this.buyOrderRegList = buyOrderRegList;
	}
	public String getSearchOrdDt() {
		return searchOrdDt;
	}
	public void setSearchOrdDt(String searchOrdDt) {
		this.searchOrdDt = searchOrdDt;
	}
	public String getSearchBuyDt() {
		return searchBuyDt;
	}
	public void setSearchBuyDt(String searchBuyDt) {
		this.searchBuyDt = searchBuyDt;
	}
	public String getBuySlipNoTemp() {
		return buySlipNoTemp;
	}
	public void setBuySlipNoTemp(String buySlipNoTemp) {
		this.buySlipNoTemp = buySlipNoTemp;
	}
	public String getBuySlipNo() {
		return buySlipNo;
	}
	public void setBuySlipNo(String buySlipNo) {
		this.buySlipNo = buySlipNo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getSalesPrHp() {
		return salesPrHp;
	}
	public void setSalesPrHp(String salesPrHp) {
		this.salesPrHp = salesPrHp;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getBuyComment1() {
		return buyComment1;
	}
	public void setBuyComment1(String buyComment1) {
		this.buyComment1 = buyComment1;
	}
	public String getBuyComment2() {
		return buyComment2;
	}
	public void setBuyComment2(String buyComment2) {
		this.buyComment2 = buyComment2;
	}
	public String getBuyComment3() {
		return buyComment3;
	}
	public void setBuyComment3(String buyComment3) {
		this.buyComment3 = buyComment3;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getCompanyAddrSub() {
		return companyAddrSub;
	}
	public void setCompanyAddrSub(String companyAddrSub) {
		this.companyAddrSub = companyAddrSub;
	}
	public String getCompanyTelNo() {
		return companyTelNo;
	}
	public void setCompanyTelNo(String companyTelNo) {
		this.companyTelNo = companyTelNo;
	}
	public String getCompanyFaxNo() {
		return companyFaxNo;
	}
	public void setCompanyFaxNo(String companyFaxNo) {
		this.companyFaxNo = companyFaxNo;
	}
	public String getOrdNote() {
		return ordNote;
	}
	public void setOrdNote(String ordNote) {
		this.ordNote = ordNote;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
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
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
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
	public String getPrevSalesQty() {
		return prevSalesQty;
	}
	public void setPrevSalesQty(String prevSalesQty) {
		this.prevSalesQty = prevSalesQty;
	}
	public String getCurSalesQty() {
		return curSalesQty;
	}
	public void setCurSalesQty(String curSalesQty) {
		this.curSalesQty = curSalesQty;
	}
	public String getWeekSalesQty() {
		return weekSalesQty;
	}
	public void setWeekSalesQty(String weekSalesQty) {
		this.weekSalesQty = weekSalesQty;
	}
	public String getHaStkQty() {
		return haStkQty;
	}
	public void setHaStkQty(String haStkQty) {
		this.haStkQty = haStkQty;
	}
	public String getYeoStkQty() {
		return yeoStkQty;
	}
	public void setYeoStkQty(String yeoStkQty) {
		this.yeoStkQty = yeoStkQty;
	}
	public String getStkDay() {
		return stkDay;
	}
	public void setStkDay(String stkDay) {
		this.stkDay = stkDay;
	}
	public String getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
	}
	public String getOrdYn() {
		return ordYn;
	}
	public void setOrdYn(String ordYn) {
		this.ordYn = ordYn;
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
	public String getBuyCd() {
		return buyCd;
	}
	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
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
	
	public String getBuySeq() {
		return buySeq;
	}
	public void setBuySeq(String buySeq) {
		this.buySeq = buySeq;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
