package com.doppio.workplace.bm.service;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 매출처 상품관리  vo
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
public class SalesPrdtMgmtVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/** 매출처 */
	private String salesCd;
	/** 본사유무 */
	private String hqClass;
	/** 품목코드 */
	private String prdtCd;
	/** 매출처 상품코드1 */
	private String salesPrdtCd1;
	/** 매출처 상품코드2 */
	private String salesPrdtCd2;
	/** 매출처 상품코드1 */
	private String salesPrdtCd1Org;
	/** 매출처 상품코드2 */
	private String salesPrdtCd2Org;
	/** 시작일자 */
	private String startDt;
	/** 종료일자 */
	private String endDt;
	/** 판매가 */
	private String price;
	/** 이전판매가 */
	private String prevPrice;
	/** 판매가 */
	private String priceOrg;
	/** 사용유무 */
	private String useYn;
	/** 등록자 */
	private String regId;
	/** 등록자 */
	private String regNm;
	/** 등록시간 */
	private String regDt;
	/** 수정자 */
	private String modId;
	/** 수정자 */
	private String modNm;
	/** 수정시간 */
	private String modDt;
	/** 작업자 */
	private String workId;
	/** 매출처명 */
	private String salesNm;
	/** 상품명 */
	private String prdtNm;
	/** 상품규격 */
	private String prdtStd;
	/** 주문단위 */
	private String ordUnit;
	/** 주문단위명 */
	private String ordUnitNm;
	/** 단가 */
	private String cost;
	/** 부가세유무 */
	private String vatYn;
	/** 매입처 */
	private String buyNm;
	/** 재고 */
	private String stkQty;
	/** 박스당수량 */
	private String qtyBox;
	/** 규격유무 */
	private String stdYn;
	
	/** 데이터 상태 */
	private String gridFlag;
	
	/** 이전 상품코드 */
	private String prdtCdOld;
	/** 신규 상품코드 */
	private String prdtCdNew;
	
	/** 이전 상품코드 */
	private String salesCdOrg;
	/** 신규 상품코드 */
	private String salesCdNew;
	
	/** 품목일괄적용 매출처 */
	private String salesCdAll;
	/** 품목일괄적용 상품코드 */
	private String prdtCdAll;
	/** 품목일괄적용 본사구분코드 */
	private String hqClassAll;
	/** 품목일괄적용 본사구분코드 */
	private String priceAll;
	/** 매출처품목1 */
	private String salesPrdtCd1All;
	/** 매출처품목2 */
	private String salesPrdtCd2All;
	/** 과면세구분 */
	private String vatYnAll;
	//일괄판매가수정 대상품목
	private String prdtCdPriceUpt;
	//일괄판매가수정 대상품목
	private String priceUpt;
	
	private String salesCdAllSch;
	private String hqclassAllSch;
	private String prdtCdAllSch;
	private String priceAllSch;
	private String salesPrdtCd1AllSch;
	private String salesPrdtCd2AllSch;
	
	
	/** 조회용 매출처 */
	private String find_prdtSalesCd;
	/** 조회용 품목명 */
	private String find_prdtNm;
	/** 조회용 품목명 */
	private String find_prdtNmSales;
	/** 조회용 사용유무 */
	private String find_useYn;
	
	
	/** 그리드 저장 데이터 */
	private SalesPrdtMgmtListVo salesPrdtMasterData;
	/** 품목 작업 데이터 */
	private SalesPrdtMgmtListVo prdtJobList;
	
	/** 로그인 유저 타입 */
	private String find_userType;
	
	/** 여주재고 */
	private String stkQty1;
	
	public String getStkQty1() {
		return stkQty1;
	}
	public void setStkQty1(String stkQty1) {
		this.stkQty1 = stkQty1;
	}
	public String getFind_userType() {
		return find_userType;
	}
	public void setFind_userType(String find_userType) {
		this.find_userType = find_userType;
	}
	public String getFind_useYn() {
		return find_useYn;
	}
	public void setFind_useYn(String find_useYn) {
		this.find_useYn = find_useYn;
	}
	public String getHqclassAllSch() {
		return hqclassAllSch;
	}
	public void setHqclassAllSch(String hqclassAllSch) {
		this.hqclassAllSch = hqclassAllSch;
	}
	public String getFind_prdtNmSales() {
		return find_prdtNmSales;
	}
	public void setFind_prdtNmSales(String find_prdtNmSales) {
		this.find_prdtNmSales = find_prdtNmSales;
	}
	public String getSalesCdAllSch() {
		return salesCdAllSch;
	}
	public void setSalesCdAllSch(String salesCdAllSch) {
		this.salesCdAllSch = salesCdAllSch;
	}
	public String getPrdtCdAllSch() {
		return prdtCdAllSch;
	}
	public void setPrdtCdAllSch(String prdtCdAllSch) {
		this.prdtCdAllSch = prdtCdAllSch;
	}
	public String getPriceAllSch() {
		return priceAllSch;
	}
	public void setPriceAllSch(String priceAllSch) {
		this.priceAllSch = priceAllSch;
	}
	public String getSalesPrdtCd1AllSch() {
		return salesPrdtCd1AllSch;
	}
	public void setSalesPrdtCd1AllSch(String salesPrdtCd1AllSch) {
		this.salesPrdtCd1AllSch = salesPrdtCd1AllSch;
	}
	public String getSalesPrdtCd2AllSch() {
		return salesPrdtCd2AllSch;
	}
	public void setSalesPrdtCd2AllSch(String salesPrdtCd2AllSch) {
		this.salesPrdtCd2AllSch = salesPrdtCd2AllSch;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	public String getPriceOrg() {
		return priceOrg;
	}
	public void setPriceOrg(String priceOrg) {
		this.priceOrg = priceOrg;
	}
	public String getSalesPrdtCd1Org() {
		return salesPrdtCd1Org;
	}
	public void setSalesPrdtCd1Org(String salesPrdtCd1Org) {
		this.salesPrdtCd1Org = salesPrdtCd1Org;
	}
	public String getSalesPrdtCd2Org() {
		return salesPrdtCd2Org;
	}
	public void setSalesPrdtCd2Org(String salesPrdtCd2Org) {
		this.salesPrdtCd2Org = salesPrdtCd2Org;
	}
	public String getPrdtCdPriceUpt() {
		return prdtCdPriceUpt;
	}
	public void setPrdtCdPriceUpt(String prdtCdPriceUpt) {
		this.prdtCdPriceUpt = prdtCdPriceUpt;
	}
	public String getPriceUpt() {
		return priceUpt;
	}
	public void setPriceUpt(String priceUpt) {
		this.priceUpt = priceUpt;
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
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}
	public String getStkQty() {
		return stkQty;
	}
	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}
	public String getFind_prdtSalesCd() {
		return find_prdtSalesCd;
	}
	public void setFind_prdtSalesCd(String find_prdtSalesCd) {
		this.find_prdtSalesCd = find_prdtSalesCd;
	}
	public String getFind_prdtNm() {
		return find_prdtNm;
	}
	public void setFind_prdtNm(String find_prdtNm) {
		this.find_prdtNm = find_prdtNm;
	}
	public String getHqClassAll() {
		return hqClassAll;
	}
	public void setHqClassAll(String hqClassAll) {
		this.hqClassAll = hqClassAll;
	}
	public String getPriceAll() {
		return priceAll;
	}
	public void setPriceAll(String priceAll) {
		this.priceAll = priceAll;
	}
	public String getSalesPrdtCd1All() {
		return salesPrdtCd1All;
	}
	public void setSalesPrdtCd1All(String salesPrdtCd1All) {
		this.salesPrdtCd1All = salesPrdtCd1All;
	}
	public String getSalesPrdtCd2All() {
		return salesPrdtCd2All;
	}
	public void setSalesPrdtCd2All(String salesPrdtCd2All) {
		this.salesPrdtCd2All = salesPrdtCd2All;
	}
	public String getVatYnAll() {
		return vatYnAll;
	}
	public void setVatYnAll(String vatYnAll) {
		this.vatYnAll = vatYnAll;
	}
	public String getSalesCdAll() {
		return salesCdAll;
	}
	public void setSalesCdAll(String salesCdAll) {
		this.salesCdAll = salesCdAll;
	}
	public String getPrdtCdAll() {
		return prdtCdAll;
	}
	public void setPrdtCdAll(String prdtCdAll) {
		this.prdtCdAll = prdtCdAll;
	}
	public String getSalesCdOrg() {
		return salesCdOrg;
	}
	public void setSalesCdOrg(String salesCdOrg) {
		this.salesCdOrg = salesCdOrg;
	}
	public String getSalesCdNew() {
		return salesCdNew;
	}
	public void setSalesCdNew(String salesCdNew) {
		this.salesCdNew = salesCdNew;
	}
	public String getPrdtCdOld() {
		return prdtCdOld;
	}
	public void setPrdtCdOld(String prdtCdOld) {
		this.prdtCdOld = prdtCdOld;
	}
	public String getPrdtCdNew() {
		return prdtCdNew;
	}
	public void setPrdtCdNew(String prdtCdNew) {
		this.prdtCdNew = prdtCdNew;
	}
	public SalesPrdtMgmtListVo getPrdtJobList() {
		return prdtJobList;
	}
	public void setPrdtJobList(SalesPrdtMgmtListVo prdtJobList) {
		this.prdtJobList = prdtJobList;
	}
	public String getRegNm() {
		return regNm;
	}
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}
	public String getModNm() {
		return modNm;
	}
	public void setModNm(String modNm) {
		this.modNm = modNm;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	public SalesPrdtMgmtListVo getSalesPrdtMasterData() {
		return salesPrdtMasterData;
	}
	public void setSalesPrdtMasterData(SalesPrdtMgmtListVo salesPrdtMasterData) {
		this.salesPrdtMasterData = salesPrdtMasterData;
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
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
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
	public String getSalesPrdtCd1() {
		return salesPrdtCd1;
	}
	public void setSalesPrdtCd1(String salesPrdtCd1) {
		this.salesPrdtCd1 = salesPrdtCd1;
	}
	public String getSalesPrdtCd2() {
		return salesPrdtCd2;
	}
	public void setSalesPrdtCd2(String salesPrdtCd2) {
		this.salesPrdtCd2 = salesPrdtCd2;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrevPrice() {
		return prevPrice;
	}
	public void setPrevPrice(String prevPrice) {
		this.prevPrice = prevPrice;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
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
	

	
	

}
