package com.doppio.workplace.bm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매출처관리  vo
 * @Class : SalesMgmtVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.17 j10000
 * </pre>
 * @version : 1.0
 */
public class SalesMgmtVo extends Page  implements Serializable {

	
	private static final long serialVersionUID = -6630600692804042954L;

	/**매출처코드*/
	private String salesCd;
	/**사업자코드*/
	private String BsnsNum;
	/**법인번호*/
	private String corpNum;
	/**업태*/
	private String busiCon;
	/**업종*/
	private String busiType;
	/**대표자명*/
	private String bossNm;
	/**전화번호*/
	private String telNo;
	/**Fax번호*/
	private String faxNo;
	/**구매팀담당자*/
	private String purPrNm;
	/**구매팀담당자휴대폰*/
	private String purPrHp;
	/**마감담당자*/
	private String closePrNm;
	/**센터담당자*/
	private String centerPrNm;
	/**센터담당자휴대폰*/
	private String centerPrHp;
	/**센터담당자전화번호*/
	private String centerPrTel;
	/**마감담당자휴대폰*/
	private String closeHp;
	/**센터팩스*/
	private String centerFax;
	/**우편코드*/
	private String zipCd;
	/**주소1*/
	private String addr1;
	/**주소2*/
	private String addr2;
	/**배송지우편코드*/
	private String dlvZip;
	/**배송지주소1*/
	private String dlvAddr1;
	/**배송지주소2*/
	private String dlvAddr2;
	/**이메일*/
	private String email;
	/**거래시작일*/
	private String startDt;
	/**거래종료일*/
	private String endDt;
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
	
	/**작업자*/
	private String workId;
	
	/**본사구분*/
	private String hqClass;
	/**본사구분명*/
	private String hqClassNm;
	/**본사코드*/
	private String hqCd;
	
	/**매출처명*/
	private String salesNm;
	/**매출처약명*/
	private String salesSNm;
	/**지역*/
	private String areaClass;
	/**영업담당자*/
	private String salesPrCd;
	/**차량번호*/
	private String carCd;
	/**세금계산서발급유무*/
	private String taxYn;
	/**결제조건*/
	private String setlCon;
	/*결제일자*/
	private String setlDt;
	/**여신한도*/
	private String creLim;
	/**외상매출유무*/
	private String crSalesYn;
	/**발주마감시간*/
	private String ordDdlnTm;
	/**발부마감 체크유무*/
	private String ordDdlnYn;
	/**비고*/
	private String remarks;
	/**거래처 매출코드*/
	private String cltSalesCd;
	/**매출처구분*/
	private String salesClass;
	/**이익율구분*/
	private String profitClass;
	/**매출일자구분*/
	private String dlvDtClass;
	/**정려금비율*/
	private String subsidyRate;
	/**미수잔액표기유무*/
	private String balDisplayYn;
	/**단가표기 유무*/
	private String priceDisplayYn;
	/**거래처 가상계좌*/
	private String vrAcctNo;
	
	
	/**조회 매출처코드*/
	private String find_salesCd;
	/**조회 매출처명*/
	private String find_salesNm;
	/**조회 세금계산서유무*/
	private String searchSalesTaxYn;
	/**조회 매출처 본사/실제매출처 구분*/
	private String searchHqClass;
	/**조회 사용유무*/
	private String searchUseYn;
	
		

	/*미수잔액*/
	private String balRec;
	/*전월잔액*/
	private String lastBalAmt;
	/*당월매출*/
	private String salesAmt;
	/*당월입금*/
	private String setlAmt;
	/*미수잔액*/
	private String balRecAmt;
	/**차량명*/
	private String carNm;
	
	/** 보증금액, 보증만기일자 add by song min kyo 2025.03.03 */
	private String warrAmt;
	private String warrExpDt;
	
	/** 매장 수, 에상매출액 add by song min kyo 2025.08.22 */
	private String storeCnt;
	private String expSalesAmt;
	
	/** 개설영업사원 add by song min kyo 2025.10.17 */
	private String newSalesPrCd;

	
	
	public String getVrAcctNo() {
		return vrAcctNo;
	}
	public void setVrAcctNo(String vrAcctNo) {
		this.vrAcctNo = vrAcctNo;
	}
	public String getNewSalesPrCd() {
		return newSalesPrCd;
	}
	public void setNewSalesPrCd(String newSalesPrCd) {
		this.newSalesPrCd = newSalesPrCd;
	}
	public String getStoreCnt() {
		return storeCnt;
	}
	public void setStoreCnt(String storeCnt) {
		this.storeCnt = storeCnt;
	}
	public String getExpSalesAmt() {
		return expSalesAmt;
	}
	public void setExpSalesAmt(String expSalesAmt) {
		this.expSalesAmt = expSalesAmt;
	}
	public String getWarrAmt() {
		return warrAmt;
	}
	public void setWarrAmt(String warrAmt) {
		this.warrAmt = warrAmt;
	}
	public String getWarrExpDt() {
		return warrExpDt;
	}
	public void setWarrExpDt(String warrExpDt) {
		this.warrExpDt = warrExpDt;
	}
	public String getCarNm() {
		return carNm;
	}
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	public String getBalDisplayYn() {
		return balDisplayYn;
	}
	public void setBalDisplayYn(String balDisplayYn) {
		this.balDisplayYn = balDisplayYn;
	}
	public String getPriceDisplayYn() {
		return priceDisplayYn;
	}
	public void setPriceDisplayYn(String priceDisplayYn) {
		this.priceDisplayYn = priceDisplayYn;
	}
	public String getSearchUseYn() {
		return searchUseYn;
	}
	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}
	public String getSearchHqClass() {
		return searchHqClass;
	}
	public void setSearchHqClass(String searchHqClass) {
		this.searchHqClass = searchHqClass;
	}
	public String getSearchSalesTaxYn() {
		return searchSalesTaxYn;
	}
	public void setSearchSalesTaxYn(String searchSalesTaxYn) {
		this.searchSalesTaxYn = searchSalesTaxYn;
	}
	public String getFind_salesCd() {
		return find_salesCd;
	}
	public void setFind_salesCd(String find_salesCd) {
		this.find_salesCd = find_salesCd;
	}
	public String getFind_salesNm() {
		return find_salesNm;
	}
	public void setFind_salesNm(String find_salesNm) {
		this.find_salesNm = find_salesNm;
	}
	public String getSalesSNm() {
		return salesSNm;
	}
	public void setSalesSNm(String salesSNm) {
		this.salesSNm = salesSNm;
	}
	public String getAreaClass() {
		return areaClass;
	}
	public void setAreaClass(String areaClass) {
		this.areaClass = areaClass;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getCarCd() {
		return carCd;
	}
	public void setCarCd(String carCd) {
		this.carCd = carCd;
	}
	public String getTaxYn() {
		return taxYn;
	}
	public void setTaxYn(String taxYn) {
		this.taxYn = taxYn;
	}
	public String getSetlCon() {
		return setlCon;
	}
	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}
	public String getCrSalesYn() {
		return crSalesYn;
	}
	public void setCrSalesYn(String crSalesYn) {
		this.crSalesYn = crSalesYn;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCltSalesCd() {
		return cltSalesCd;
	}
	public void setCltSalesCd(String cltSalesCd) {
		this.cltSalesCd = cltSalesCd;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	
	public String getDlvDtClass() {
		return dlvDtClass;
	}
	public void setDlvDtClass(String dlvDtClass) {
		this.dlvDtClass = dlvDtClass;
	}
	public String getSubsidyRate() {
		return subsidyRate;
	}
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}
	public String getBalRecAmt() {
		return balRecAmt;
	}
	public void setBalRecAmt(String balRecAmt) {
		this.balRecAmt = balRecAmt;
	}
	public String getSetlDt() {
		return setlDt;
	}
	public void setSetlDt(String setlDt) {
		this.setlDt = setlDt;
	}
	public String getLastBalAmt() {
		return lastBalAmt;
	}
	public void setLastBalAmt(String lastBalAmt) {
		this.lastBalAmt = lastBalAmt;
	}
	public String getSalesAmt() {
		return salesAmt;
	}
	public void setSalesAmt(String salesAmt) {
		this.salesAmt = salesAmt;
	}
	public String getSetlAmt() {
		return setlAmt;
	}
	public void setSetlAmt(String setlAmt) {
		this.setlAmt = setlAmt;
	}
	public String getProfitClass() {
		return profitClass;
	}
	public void setProfitClass(String profitClass) {
		this.profitClass = profitClass;
	}
	public String getHqCd() {
		return hqCd;
	}
	public void setHqCd(String hqCd) {
		this.hqCd = hqCd;
	}
	public String getCloseHp() {
		return closeHp;
	}
	public void setCloseHp(String closeHp) {
		this.closeHp = closeHp;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getHqClassNm() {
		return hqClassNm;
	}
	public void setHqClassNm(String hqClassNm) {
		this.hqClassNm = hqClassNm;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getBsnsNum() {
		return BsnsNum;
	}
	public void setBsnsNum(String bsnsNum) {
		BsnsNum = bsnsNum;
	}
	public String getCorpNum() {
		return corpNum;
	}
	public void setCorpNum(String corpNum) {
		this.corpNum = corpNum;
	}
	public String getBusiCon() {
		return busiCon;
	}
	public void setBusiCon(String busiCon) {
		this.busiCon = busiCon;
	}
	public String getBusiType() {
		return busiType;
	}
	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}
	public String getBossNm() {
		return bossNm;
	}
	public void setBossNm(String bossNm) {
		this.bossNm = bossNm;
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
	public String getPurPrNm() {
		return purPrNm;
	}
	public void setPurPrNm(String purPrNm) {
		this.purPrNm = purPrNm;
	}
	public String getPurPrHp() {
		return purPrHp;
	}
	public void setPurPrHp(String purPrHp) {
		this.purPrHp = purPrHp;
	}
	public String getClosePrNm() {
		return closePrNm;
	}
	public void setClosePrNm(String closePrNm) {
		this.closePrNm = closePrNm;
	}
	public String getCenterPrNm() {
		return centerPrNm;
	}
	public void setCenterPrNm(String centerPrNm) {
		this.centerPrNm = centerPrNm;
	}
	public String getCenterPrHp() {
		return centerPrHp;
	}
	public void setCenterPrHp(String centerPrHp) {
		this.centerPrHp = centerPrHp;
	}
	public String getCenterPrTel() {
		return centerPrTel;
	}
	public void setCenterPrTel(String centerPrTel) {
		this.centerPrTel = centerPrTel;
	}
	public String getCenterFax() {
		return centerFax;
	}
	public void setCenterFax(String centerFax) {
		this.centerFax = centerFax;
	}
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getDlvZip() {
		return dlvZip;
	}
	public void setDlvZip(String dlvZip) {
		this.dlvZip = dlvZip;
	}
	public String getDlvAddr1() {
		return dlvAddr1;
	}
	public void setDlvAddr1(String dlvAddr1) {
		this.dlvAddr1 = dlvAddr1;
	}
	public String getDlvAddr2() {
		return dlvAddr2;
	}
	public void setDlvAddr2(String dlvAddr2) {
		this.dlvAddr2 = dlvAddr2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
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
	

	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getCreLim() {
		return creLim;
	}
	public void setCreLim(String creLim) {
		this.creLim = creLim;
	}
	public String getBalRec() {
		return balRec;
	}
	public void setBalRec(String balRec) {
		this.balRec = balRec;
	}
	public String getOrdDdlnTm() {
		return ordDdlnTm;
	}
	public void setOrdDdlnTm(String ordDdlnTm) {
		this.ordDdlnTm = ordDdlnTm;
	}
	public String getOrdDdlnYn() {
		return ordDdlnYn;
	}
	public void setOrdDdlnYn(String ordDdlnYn) {
		this.ordDdlnYn = ordDdlnYn;
	}
	
	
	
	

}
