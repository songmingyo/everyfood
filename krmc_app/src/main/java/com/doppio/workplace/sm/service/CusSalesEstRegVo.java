package com.doppio.workplace.sm.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.doppio.common.model.Page;

/**
 * @author song
 * @Description : 매출 견적서 관리  vo
 * @Class : CusSalesEstRegVo 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.04.12 
 * </pre>
 * @version : 1.0
 */
public class CusSalesEstRegVo extends Page  implements Serializable {
	
	private static final long serialVersionUID = -8797484923598645834L;

	private List<CusSalesEstRegVo> cusSalesEstRegList;	
	
	/** 품목코드 */
	private String prdtCd;
	/** 품목명*/
	private String prdtNm;
	/** 규격*/
	private String prdtStd;
	/** 주문단위*/
	private String ordUnitNm;
	/** 단가*/
	private String cost;
	/** 기본단가*/
	private String baseCost;
	/** 견적가*/
	private String estPrice;
	/** 저장형태*/
	private String strgTypeNm;
	/** 원산지*/
	private String originNm;
	/** 유통기한*/
	private String exprtDt;
	/** 매입처*/
	private String buyNm;
	/** 영업담당자*/
	private String salesPrNm;
	/** 영업담당연락처*/
	private String salesPrHp;
	/** 결제조건*/
	private String setlCon;
	//부가세유무
	private String vatYn;
	 
	/** 견적번호*/
	private String estNum;
	/** 견적차수*/
	private String estSeq;
	/** 매출처코드*/
	private String salesCd;
	/** 매출처명*/
	private String salesNm;
	/** 매출처전화번호*/
	private String salesTelNo;
	/** 견적일*/
	private String estDt;
	/** 유효일*/
	private String effDt;
	/** 핸드폰 */
	private String mTelNo;
	/** 견적조건*/
	private String cnstrRemarks;
	/** 특이사항*/
	private String spclRemarks;
	/** 매출시직예정일*/
	private String salesStDt;
	/** 최초입고예정일*/
	private String buyStDt;
	/** 매출의뢰담당자*/
	private String reqSalesPrNm;
	/** 매출처의뢰담당자연락처*/
	private String reqSalesTelNo;
	/** 입고가*/
	private String buyCost;
	/** 부가세*/
	private String vatAmt;
	/** 마진*/
	private String marginRate;
	/** 월예상판매량*/
	private String monSalesQty;
	/** 보관방법*/
	private String saveKindNm;
	/** 최초입고수량*/
	private String buyStQty;
	/** 매입처담당자*/
	private String buyPrNm;
	/** 매입처연락처*/
	private String buyTelNo;
	/** 입고방법*/
	private String buyHow;
	/** 확정유무*/
	private String confirmYn;
	/** 삭제구분*/
	private String useYn;
	/** 삭제구분명칭*/
	private String useYnNm;
	//매출 이익율 구분
	private String profitClass;
	//매출 이익율 구분
	private String subsidyRate;
	//등록일
	private String regDt;
	//등록자
	private String regNm;
		
	/** 팝업 견적번호 */
	private String find_estNum;
	/** 팝업 매출처 */
	private String find_salesNm;
	
	/*작업자 아이디 */
	private String workId;
	/* 그리드 상태 */
	private String gridFlag;
	
	/* 조회용 견적번호 */
	private String searchEstNum;
	/* 조회용 견적차수 */
	private String searchEstSeq;
	/* 출력 구분 */
	private String searchPrtClass;
	
	
	
	public String getSearchPrtClass() {
		return searchPrtClass;
	}
	public void setSearchPrtClass(String searchPrtClass) {
		this.searchPrtClass = searchPrtClass;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	public String getProfitClass() {
		return profitClass;
	}
	public void setProfitClass(String profitClass) {
		this.profitClass = profitClass;
	}
	public String getUseYnNm() {
		return useYnNm;
	}
	public void setUseYnNm(String useYnNm) {
		this.useYnNm = useYnNm;
	}
	public String getSearchEstNum() {
		return searchEstNum;
	}
	public void setSearchEstNum(String searchEstNum) {
		this.searchEstNum = searchEstNum;
	}
	public String getSearchEstSeq() {
		return searchEstSeq;
	}
	public void setSearchEstSeq(String searchEstSeq) {
		this.searchEstSeq = searchEstSeq;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	public String getFind_estNum() {
		return find_estNum;
	}
	public void setFind_estNum(String find_estNum) {
		this.find_estNum = find_estNum;
	}
	public String getFind_salesNm() {
		return find_salesNm;
	}
	public void setFind_salesNm(String find_salesNm) {
		this.find_salesNm = find_salesNm;
	}
	public String getBaseCost() {
		return baseCost;
	}
	public void setBaseCost(String baseCost) {
		this.baseCost = baseCost;
	}
	public String getBuyCost() {
		return buyCost;
	}
	public void setBuyCost(String buyCost) {
		this.buyCost = buyCost;
	}
	public List<CusSalesEstRegVo> getCusSalesEstRegList() {
		return cusSalesEstRegList;
	}
	public void setCusSalesEstRegList(List<CusSalesEstRegVo> cusSalesEstRegList) {
		this.cusSalesEstRegList = cusSalesEstRegList;
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
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getEstPrice() {
		return estPrice;
	}
	public void setEstPrice(String estPrice) {
		this.estPrice = estPrice;
	}
	public String getStrgTypeNm() {
		return strgTypeNm;
	}
	public void setStrgTypeNm(String strgTypeNm) {
		this.strgTypeNm = strgTypeNm;
	}
	public String getOriginNm() {
		return originNm;
	}
	public void setOriginNm(String originNm) {
		this.originNm = originNm;
	}
	public String getExprtDt() {
		return exprtDt;
	}
	public void setExprtDt(String exprtDt) {
		this.exprtDt = exprtDt;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getSalesPrHp() {
		return salesPrHp;
	}
	public void setSalesPrHp(String salesPrHp) {
		this.salesPrHp = salesPrHp;
	}
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getEstNum() {
		return estNum;
	}
	public void setEstNum(String estNum) {
		this.estNum = estNum;
	}
	
	
	public String getEstSeq() {
		return estSeq;
	}
	public void setEstSeq(String estSeq) {
		this.estSeq = estSeq;
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
	public String getSalesTelNo() {
		return salesTelNo;
	}
	public void setSalesTelNo(String salesTelNo) {
		this.salesTelNo = salesTelNo;
	}
	public String getEstDt() {
		return estDt;
	}
	public void setEstDt(String estDt) {
		this.estDt = estDt;
	}
	public String getEffDt() {
		return effDt;
	}
	public void setEffDt(String effDt) {
		this.effDt = effDt;
	}
	public String getmTelNo() {
		return mTelNo;
	}
	public void setmTelNo(String mTelNo) {
		this.mTelNo = mTelNo;
	}
	public String getCnstrRemarks() {
		return cnstrRemarks;
	}
	public void setCnstrRemarks(String cnstrRemarks) {
		this.cnstrRemarks = cnstrRemarks;
	}
	public String getSpclRemarks() {
		return spclRemarks;
	}
	public void setSpclRemarks(String spclRemarks) {
		this.spclRemarks = spclRemarks;
	}
	public String getSalesStDt() {
		return salesStDt;
	}
	public void setSalesStDt(String salesStDt) {
		this.salesStDt = salesStDt;
	}
	public String getBuyStDt() {
		return buyStDt;
	}
	public void setBuyStDt(String buyStDt) {
		this.buyStDt = buyStDt;
	}
	public String getReqSalesPrNm() {
		return reqSalesPrNm;
	}
	public void setReqSalesPrNm(String reqSalesPrNm) {
		this.reqSalesPrNm = reqSalesPrNm;
	}
	public String getReqSalesTelNo() {
		return reqSalesTelNo;
	}
	public void setReqSalesTelNo(String reqSalesTelNo) {
		this.reqSalesTelNo = reqSalesTelNo;
	}
	public String getVatAmt() {
		return vatAmt;
	}
	public void setVatAmt(String vatAmt) {
		this.vatAmt = vatAmt;
	}
	public String getMarginRate() {
		return marginRate;
	}
	public void setMarginRate(String marginRate) {
		this.marginRate = marginRate;
	}
	public String getMonSalesQty() {
		return monSalesQty;
	}
	public void setMonSalesQty(String monSalesQty) {
		this.monSalesQty = monSalesQty;
	}
	public String getSaveKindNm() {
		return saveKindNm;
	}
	public void setSaveKindNm(String saveKindNm) {
		this.saveKindNm = saveKindNm;
	}
	public String getBuyStQty() {
		return buyStQty;
	}
	public void setBuyStQty(String buyStQty) {
		this.buyStQty = buyStQty;
	}
	public String getBuyPrNm() {
		return buyPrNm;
	}
	public void setBuyPrNm(String buyPrNm) {
		this.buyPrNm = buyPrNm;
	}
	public String getBuyTelNo() {
		return buyTelNo;
	}
	public void setBuyTelNo(String buyTelNo) {
		this.buyTelNo = buyTelNo;
	}
	public String getBuyHow() {
		return buyHow;
	}
	public void setBuyHow(String buyHow) {
		this.buyHow = buyHow;
	}
	public String getConfirmYn() {
		return confirmYn;
	}
	public void setConfirmYn(String confirmYn) {
		this.confirmYn = confirmYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	
	 

}
