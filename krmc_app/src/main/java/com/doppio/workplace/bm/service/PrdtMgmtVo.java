package com.doppio.workplace.bm.service;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 상품관리  vo
 * @Class : PrdtMgmtVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class PrdtMgmtVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**상품코드*/
	private String prdtCd;
	/**상품명*/
	private String prdtNm;
	/**대분류*/
	private String lCd;
	/**중분류*/
	private String mCd;
	/**규격*/
	private String prdtStd;
	/**주문단위*/
	private String ordUnit;
	/**주문단위*/
	private String ordUnitNm;
	/**원산지*/
	private String originNm;
	/**박스당수량*/
	private String qtyBox;
	/**부가세유무*/
	private String vatYn;
	/**저장형태*/
	private String strgType;
	/**선반번호1*/
	private String lackNo1;
	/**선반번호2*/
	private String lackNo2;
	/**세금계산서발급유무*/
	private String taxYn;
	/**가로*/
	private String caseW;
	/**세로*/
	private String caseL;
	/**높이*/
	private String caseH;
	/**중량*/
	private String caseWt;
	/**제조사*/
	private String mnfct;
	/**규격유무*/
	private String stdYn;
	/**유통기한*/
	private String exprtDt;
	/**상품담당자*/
	private String prdtMgrPr;
	/**발주리드타임*/
	private String ordLdTm;
	/**기준원가*/
	private String baseCost;
	/**매입처상품코드*/
	private String buyPrdtCd;
	/**비고*/
	private String remarks;
	/**견적가*/
	private String estPrice;
	/**사용유무*/
	private String useYn;
	/**등록자*/
	private String regNm;
	/**등록ID*/
	private String regId;
	/**등록시간*/
	private String regDt;
	/**수정자*/
	private String modNm;
	/**수정ID*/
	private String modId;
	/**수정시간*/
	private String modDt;
	
	/**단가*/
	private String cost;
	/**단가시작일*/
	private String costStartDt;
	/**단가종료일*/
	private String costEndDt;
	/**매입처*/
	private String buyCd;
	/**이전매입처*/
	private String prevBuyCd;
	/**매입처 시작일*/
	private String buyStartDt;
	/**매입처 종료일*/
	private String buyEndDt;
	/**작업자*/
	private String workId;
	/**기한임박*/
	private String timeLimit;
	/**츨고부진*/
	private String issSluggish;
	/**츨고부진*/
	private String prdtCdNm;
	/**매출처*/
	private String salesNm;
	/**매출일자*/
	private String salesDt;
	/**창고명*/
	private String whNm;
	/**매출수량*/
	private String salesQty;
	/**판매가*/
	private String price;
	/**공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt;
	/**합계금액*/
	private String totAmt;
	/**입고일자*/
	private String buyDt;
	/**소비기한*/
	private String termVal;
	/**매입수량*/
	private String buyQty;
	/**매출구분*/
	private String salesClass;
	/**창고코드*/
	private String whCd;	
	/**하남재고*/
	private String haStkQty;
	/**여주재고*/
	private String yeoStkQty;
	/**매입처명*/
	private String buyNm;
	/**이전매입처명*/
	private String prevBuyNm;
	/**당월실적*/
	private String thisSalesQty;
	/**전월실적*/
	private String lastSalesQty;
	/**전전월실적*/
	private String twoMnSalesQty;
	
	
	/**상품코드 검색용*/
	private String find_prdtCd;
	/**상품명 검색용*/
	private String find_prdtNm;
	/**매입처 검색용*/
	private String find_buyCd;
	
	private String salesPrCd;		//영업사원 코드
	private String salesPrNm;		//영업사원 이름
	private String salesCd;			//매출처 코드
	
	private String boxQtyComp1;		// 당월출고량
	private String boxQtyComp2;		// 전월출고량
	private String boxQtyComp3;		// 전전월출고량
	
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
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getThisSalesQty() {
		return thisSalesQty;
	}
	public void setThisSalesQty(String thisSalesQty) {
		this.thisSalesQty = thisSalesQty;
	}
	public String getSalesDt() {
		return salesDt;
	}
	public void setSalesDt(String salesDt) {
		this.salesDt = salesDt;
	}
	public String getBuyDt() {
		return buyDt;
	}
	public void setBuyDt(String buyDt) {
		this.buyDt = buyDt;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	public void setHaStkQty(String haStkQty) {
		this.haStkQty = haStkQty;
	}
	public void setYeoStkQty(String yeoStkQty) {
		this.yeoStkQty = yeoStkQty;
	}
	public String getLastSalesQty() {
		return lastSalesQty;
	}
	public void setLastSalesQty(String lastSalesQty) {
		this.lastSalesQty = lastSalesQty;
	}
	public String getTwoMnSalesQty() {
		return twoMnSalesQty;
	}
	public void setTwoMnSalesQty(String twoMnSalesQty) {
		this.twoMnSalesQty = twoMnSalesQty;
	}
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getFind_buyCd() {
		return find_buyCd;
	}
	public void setFind_buyCd(String find_buyCd) {
		this.find_buyCd = find_buyCd;
	}
	public String getFind_prdtCd() {
		return find_prdtCd;
	}
	public void setFind_prdtCd(String find_prdtCd) {
		this.find_prdtCd = find_prdtCd;
	}
	public String getFind_prdtNm() {
		return find_prdtNm;
	}
	public void setFind_prdtNm(String find_prdtNm) {
		this.find_prdtNm = find_prdtNm;
	}
	public String getPrevBuyNm() {
		return prevBuyNm;
	}
	public void setPrevBuyNm(String prevBuyNm) {
		this.prevBuyNm = prevBuyNm;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getbuyDtFmt() {
		String rtnVal = this.buyDt;
		if(!StringUtils.isEmpty(rtnVal))
			rtnVal = DateUtil.convertDate(rtnVal, "yyyyMMdd", "yyyy-MM-dd");
		return rtnVal;
	}
	public String getHaStkQty() {
		return haStkQty;
	}
	public String getYeoStkQty() {
		return yeoStkQty;
	}
	public String getWhCd() {
		return whCd;
	}
	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}
	public String getsalesDtFmt() {
		String rtnVal = this.salesDt;
		if(!StringUtils.isEmpty(rtnVal))
			rtnVal = DateUtil.convertDate(rtnVal, "yyyyMMdd", "yyyy-MM-dd");
		return rtnVal;
	}
	public String getWhNm() {
		return whNm;
	}
	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}
	public String getSalesQty() {
		return salesQty;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}
	public String getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(String buyQty) {
		this.buyQty = buyQty;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getPrdtCdNm() {
		return prdtCdNm;
	}
	public void setPrdtCdNm(String prdtCdNm) {
		this.prdtCdNm = prdtCdNm;
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
	public String getlCd() {
		return lCd;
	}
	public void setlCd(String lCd) {
		this.lCd = lCd;
	}
	public String getmCd() {
		return mCd;
	}
	public void setmCd(String mCd) {
		this.mCd = mCd;
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
	public String getOriginNm() {
		return originNm;
	}
	public void setOriginNm(String originNm) {
		this.originNm = originNm;
	}
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	public String getStrgType() {
		return strgType;
	}
	public void setStrgType(String strgType) {
		this.strgType = strgType;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getLackNo2() {
		return lackNo2;
	}
	public void setLackNo2(String lackNo2) {
		this.lackNo2 = lackNo2;
	}
	public String getTaxYn() {
		return taxYn;
	}
	public void setTaxYn(String taxYn) {
		this.taxYn = taxYn;
	}
	public String getCaseW() {
		return caseW;
	}
	public void setCaseW(String caseW) {
		this.caseW = caseW;
	}
	public String getCaseL() {
		return caseL;
	}
	public void setCaseL(String caseL) {
		this.caseL = caseL;
	}
	public String getCaseH() {
		return caseH;
	}
	public void setCaseH(String caseH) {
		this.caseH = caseH;
	}
	public String getCaseWt() {
		return caseWt;
	}
	public void setCaseWt(String caseWt) {
		this.caseWt = caseWt;
	}
	public String getMnfct() {
		return mnfct;
	}
	public void setMnfct(String mnfct) {
		this.mnfct = mnfct;
	}
	public String getStdYn() {
		return stdYn;
	}
	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}
	public String getExprtDt() {
		return exprtDt;
	}
	public void setExprtDt(String exprtDt) {
		this.exprtDt = exprtDt;
	}
	public String getPrdtMgrPr() {
		return prdtMgrPr;
	}
	public void setPrdtMgrPr(String prdtMgrPr) {
		this.prdtMgrPr = prdtMgrPr;
	}
	public String getOrdLdTm() {
		return ordLdTm;
	}
	public void setOrdLdTm(String ordLdTm) {
		this.ordLdTm = ordLdTm;
	}
	public String getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(String baseCost) {
		this.baseCost = baseCost;
	}
	public String getBuyPrdtCd() {
		return buyPrdtCd;
	}
	public void setBuyPrdtCd(String buyPrdtCd) {
		this.buyPrdtCd = buyPrdtCd;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getEstPrice() {
		return estPrice;
	}
	public void setEstPrice(String estPrice) {
		this.estPrice = estPrice;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
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
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getCostStartDt() {
		return costStartDt;
	}
	public void setCostStartDt(String costStartDt) {
		this.costStartDt = costStartDt;
	}
	public String getCostEndDt() {
		return costEndDt;
	}
	public void setCostEndDt(String costEndDt) {
		this.costEndDt = costEndDt;
	}
	public String getBuyCd() {
		return buyCd;
	}
	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}
	public String getPrevBuyCd() {
		return prevBuyCd;
	}
	public void setPrevBuyCd(String prevBuyCd) {
		this.prevBuyCd = prevBuyCd;
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
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getIssSluggish() {
		return issSluggish;
	}
	public void setIssSluggish(String issSluggish) {
		this.issSluggish = issSluggish;
	}
	
	
	
	
	

}
