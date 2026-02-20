package com.doppio.workplace.cs.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author song
 * @Description : 일재고등록  vo
 * @Class : ClsDayStkRegVo 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.04.12 song
 * </pre>
 * @version : 1.0
 */
public class ClsDayStkRegVo extends Page  implements Serializable {
	

	private static final long serialVersionUID = -8797484923598645834L;

	
	private List<ClsDayStkRegVo> clsDayStkRegList;
	
	/** 랙번호 */
	private String lackNo1 		;
	/** 품목코드 */
	private String prdtCd  		;
	/** 품목명*/
	private String prdtNm  		;
	/** 규격*/
	private String prdtStd 		;
	/** 박스당수량*/
	private String qtyBox  		;
	
	/** 전일재고*/
	private String prevStkQty  		;
	
	/** 입고*/
	private String buyQty  		;
	/** 반품*/
	private String buyRtnQty  		;
	/** 입고계*/
	private String buyTotQty  		;
	
	/** 출고*/
	private String dlvQty  		;
	/** 반품*/
	private String salesRtnQty  		;
	/** 출고계*/
	private String dlvTotQty  		;
	
	/** 수불수량*/
	private String lStkQty  		;
	/** 수불금액*/
	private String lStkAmt  		;
	
	/** 실재고A+B*/
	private String area1Qty  		;
	/** 실재고C+D*/
	private String area2Qty  		;
	/** 합계수량*/
	private String rStkQty  		;
	/** 합계금액*/
	private String rStkAmt  		;
	
	/** 재고차*/
	private String stkDiffQty  		;
	/** 재고금액*/
	private String stkDiffAmt  		;
	
	/** 단가*/
	private String cost  		;
	
	/** 재고일자*/
	private String stkDt  		;
	/** 창고*/
	private String whCd  		;
	
	
	/**재고일자*/
	private String searchStkDt ;
	/**품목코드*/
	private String searchPrdtCd ;
	/** 저장형태*/
	private String searchStrgType ;
	/** 창고코드*/
	private String searchWhCd 	;
	/** 창고코드*/
	private String searchLCd 	;
	/** 엑셀 일자*/
	private String excelStkDt 	;
	
	
	
	/* 그리드 상태 */
	private String gridFlag;
	/* 작업자 */
	private String workId;
	/* 사용유무 */
	private String useYn;
	
	//출력 재고 수량
	private double prtStkQty;
	//출력 순서
	private double rowNum;
	//저장형태명
	private String strgTypeNm;
	//단위
	private String ordUnitNm;
	
	/** 박스당수량*/
	private String qtyBox2;
	
	/** 재고조사표 출력 */
	private String ntm;
	
	/** 박스당 환산 수량, 박스당환산수량을 제외한 남은(단위) 수량, 박스당 환산수량 표기(ex 2Box/3EA) */
	private String qtyBox3;
	private String qtyUnit;
	private String qtyBox4;
		
	public String getQtyBox3() {
		return qtyBox3;
	}
	public void setQtyBox3(String qtyBox3) {
		this.qtyBox3 = qtyBox3;
	}
	public String getQtyUnit() {
		return qtyUnit;
	}
	public void setQtyUnit(String qtyUnit) {
		this.qtyUnit = qtyUnit;
	}
	public String getQtyBox4() {
		return qtyBox4;
	}
	public void setQtyBox4(String qtyBox4) {
		this.qtyBox4 = qtyBox4;
	}
	public String getNtm() {
		return ntm;
	}
	public void setNtm(String ntm) {
		this.ntm = ntm;
	}
	public String getQtyBox2() {
		return qtyBox2;
	}
	public void setQtyBox2(String qtyBox2) {
		this.qtyBox2 = qtyBox2;
	}
	public String getExcelStkDt() {
		return excelStkDt;
	}
	public void setExcelStkDt(String excelStkDt) {
		this.excelStkDt = excelStkDt;
	}
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getStrgTypeNm() {
		return strgTypeNm;
	}
	public void setStrgTypeNm(String strgTypeNm) {
		this.strgTypeNm = strgTypeNm;
	}
	public double getRowNum() {
		return rowNum;
	}
	public void setRowNum(double rowNum) {
		this.rowNum = rowNum;
	}
	public double getPrtStkQty() {
		return prtStkQty;
	}
	public void setPrtStkQty(double prtStkQty) {
		this.prtStkQty = prtStkQty;
	}
	public String getSearchLCd() {
		return searchLCd;
	}
	public void setSearchLCd(String searchLCd) {
		this.searchLCd = searchLCd;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getStkDt() {
		return stkDt;
	}
	public void setStkDt(String stkDt) {
		this.stkDt = stkDt;
	}
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public List<ClsDayStkRegVo> getClsDayStkRegList() {
		return clsDayStkRegList;
	}
	public void setClsDayStkRegList(List<ClsDayStkRegVo> clsDayStkRegList) {
		this.clsDayStkRegList = clsDayStkRegList;
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
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}
	public String getPrevStkQty() {
		return prevStkQty;
	}
	public void setPrevStkQty(String prevStkQty) {
		this.prevStkQty = prevStkQty;
	}
	public String getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(String buyQty) {
		this.buyQty = buyQty;
	}
	public String getBuyRtnQty() {
		return buyRtnQty;
	}
	public void setBuyRtnQty(String buyRtnQty) {
		this.buyRtnQty = buyRtnQty;
	}
	public String getBuyTotQty() {
		return buyTotQty;
	}
	public void setBuyTotQty(String buyTotQty) {
		this.buyTotQty = buyTotQty;
	}
	
	public String getDlvQty() {
		return dlvQty;
	}
	public void setDlvQty(String dlvQty) {
		this.dlvQty = dlvQty;
	}
	public String getSalesRtnQty() {
		return salesRtnQty;
	}
	public void setSalesRtnQty(String salesRtnQty) {
		this.salesRtnQty = salesRtnQty;
	}
	public String getDlvTotQty() {
		return dlvTotQty;
	}
	public void setDlvTotQty(String dlvTotQty) {
		this.dlvTotQty = dlvTotQty;
	}
	public String getlStkQty() {
		return lStkQty;
	}
	public void setlStkQty(String lStkQty) {
		this.lStkQty = lStkQty;
	}
	public String getlStkAmt() {
		return lStkAmt;
	}
	public void setlStkAmt(String lStkAmt) {
		this.lStkAmt = lStkAmt;
	}
	public String getArea1Qty() {
		return area1Qty;
	}
	public void setArea1Qty(String area1Qty) {
		this.area1Qty = area1Qty;
	}
	public String getArea2Qty() {
		return area2Qty;
	}
	public void setArea2Qty(String area2Qty) {
		this.area2Qty = area2Qty;
	}
	public String getrStkQty() {
		return rStkQty;
	}
	public void setrStkQty(String rStkQty) {
		this.rStkQty = rStkQty;
	}
	public String getrStkAmt() {
		return rStkAmt;
	}
	public void setrStkAmt(String rStkAmt) {
		this.rStkAmt = rStkAmt;
	}
	public String getStkDiffQty() {
		return stkDiffQty;
	}
	public void setStkDiffQty(String stkDiffQty) {
		this.stkDiffQty = stkDiffQty;
	}
	public String getStkDiffAmt() {
		return stkDiffAmt;
	}
	public void setStkDiffAmt(String stkDiffAmt) {
		this.stkDiffAmt = stkDiffAmt;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	public String getSearchStkDt() {
		return searchStkDt;
	}
	public void setSearchStkDt(String searchStkDt) {
		this.searchStkDt = searchStkDt;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
	}
	public String getSearchStrgType() {
		return searchStrgType;
	}
	public void setSearchStrgType(String searchStrgType) {
		this.searchStrgType = searchStrgType;
	}
	public String getSearchWhCd() {
		return searchWhCd;
	}
	public void setSearchWhCd(String searchWhCd) {
		this.searchWhCd = searchWhCd;
	}
	 
	
	

}
