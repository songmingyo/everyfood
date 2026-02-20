package com.doppio.workplace.cs.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/*
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
public class ClsDayStkListVo extends Page  implements Serializable {
	

	private static final long serialVersionUID = -8797484923598645834L;

	
	/* 재고일자 */
	private String stkDt 		;
	/* 품목코드 */
	private String prdtCd  		;
	/* 랙번호 */
	private String lackNo1 		;
	/* 품목명*/
	private String prdtNm  		;
	/* 규격*/
	private String prdtStd 		;
	/* 박스당수량*/
	private String qtyBox  		;
	
	/* 실재고*/
	private String realStkQty  		;	
	/* 전일재고*/
	private String prevStkQty  		;
	
	/* 입고*/
	private String buyQty  		;
	/* 창고이동입고 */
	private String inWhQty  		;
	/* 반품*/
	private String buyRtnQty  		;
	/* 입고계*/
	private String buyTotQty  		;
	
	/* 출고*/
	private String dlvQty  		;
	/* 칭고이동출고*/
	private String outWhQty  		;
	/* 반품*/
	private String dlvRtnQty  		;
	/* 출고계*/
	private String dlvTotQty  		;
	
	/* 재고수량*/
	private String stkQty  		;
	/* 재고금액*/
	private String stkAmt  		;	
	/* 단가*/
	private String cost  		;
	/* 박스수량 */
	private String boxQtyComp 		;
	
	
	/*재고일자*/
	private String searchStkDt ;
	/*품목코드*/
	private String searchPrdtCd ;
	/*품목명*/
	private String searchPrdtNm ;
	/* 저장형태*/
	private String searchStrgType ;
	/* 창고코드*/
	private String searchWhCd 	;
	/* 대분류*/
	private String searchLCd 	;
	/* 중분류*/
	private String searchMCd 	;
	/** 엑셀 일자*/
	private String excelStkDt 	;
	
	/** 당월 출고량 */
	private String salesQty1;
	/** 전월 출고량 */
	private String salesQty2;
	/** 전전월 출고량 */
	private String salesQty3;
	
	/** 최종입고 소비기한*/
	private String termVal;
	
	/** 당월, 전월, 전전월 출고수량 */
	private String boxQtyComp1;		
	private String boxQtyComp2;
	private String boxQtyComp3;
	
	public String getBoxQtyComp1() {
		return boxQtyComp1;
	}
	public void setBoxQtyComp1(String boxQtyComp1) {
		this.boxQtyComp1 = boxQtyComp1;
	}
	public String getBoxQtyComp2() {
		return boxQtyComp2;
	}
	public void setBoxQtyComp2(String boxQtyComp2) {
		this.boxQtyComp2 = boxQtyComp2;
	}
	public String getBoxQtyComp3() {
		return boxQtyComp3;
	}
	public void setBoxQtyComp3(String boxQtyComp3) {
		this.boxQtyComp3 = boxQtyComp3;
	}
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}
	public String getSalesQty1() {
		return salesQty1;
	}
	public void setSalesQty1(String salesQty1) {
		this.salesQty1 = salesQty1;
	}
	public String getSalesQty2() {
		return salesQty2;
	}
	public void setSalesQty2(String salesQty2) {
		this.salesQty2 = salesQty2;
	}
	public String getSalesQty3() {
		return salesQty3;
	}
	public void setSalesQty3(String salesQty3) {
		this.salesQty3 = salesQty3;
	}
	public String getBoxQtyComp() {
		return boxQtyComp;
	}
	public void setBoxQtyComp(String boxQtyComp) {
		this.boxQtyComp = boxQtyComp;
	}
	public String getRealStkQty() {
		return realStkQty;
	}
	public void setRealStkQty(String realStkQty) {
		this.realStkQty = realStkQty;
	}
	public String getExcelStkDt() {
		return excelStkDt;
	}
	public void setExcelStkDt(String excelStkDt) {
		this.excelStkDt = excelStkDt;
	}
	public String getSearchPrdtNm() {
		return searchPrdtNm;
	}
	public void setSearchPrdtNm(String searchPrdtNm) {
		this.searchPrdtNm = searchPrdtNm;
	}
	public String getStkDt() {
		return stkDt;
	}
	public void setStkDt(String stkDt) {
		this.stkDt = stkDt;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
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
	
	
	public String getInWhQty() {
		return inWhQty;
	}
	public void setInWhQty(String inWhQty) {
		this.inWhQty = inWhQty;
	}
	public String getOutWhQty() {
		return outWhQty;
	}
	public void setOutWhQty(String outWhQty) {
		this.outWhQty = outWhQty;
	}
	public String getDlvRtnQty() {
		return dlvRtnQty;
	}
	public void setDlvRtnQty(String dlvRtnQty) {
		this.dlvRtnQty = dlvRtnQty;
	}
	public String getDlvTotQty() {
		return dlvTotQty;
	}
	public void setDlvTotQty(String dlvTotQty) {
		this.dlvTotQty = dlvTotQty;
	}
	
	public String getStkQty() {
		return stkQty;
	}
	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}
	public String getStkAmt() {
		return stkAmt;
	}
	public void setStkAmt(String stkAmt) {
		this.stkAmt = stkAmt;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
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
	public String getSearchLCd() {
		return searchLCd;
	}
	public void setSearchLCd(String searchLCd) {
		this.searchLCd = searchLCd;
	}
	public String getSearchMCd() {
		return searchMCd;
	}
	public void setSearchMCd(String searchMCd) {
		this.searchMCd = searchMCd;
	}
	

	

}
