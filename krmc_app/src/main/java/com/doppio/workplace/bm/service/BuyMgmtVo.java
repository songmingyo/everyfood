package com.doppio.workplace.bm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 매입처관리  vo
 * @Class : BuyMgmtVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.19 j10000
 * </pre>
 * @version : 1.0
 */
public class BuyMgmtVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**매입처코드*/
	private String buyCd;
	/**매입처명*/
	private String buyNm;
	/**매입처명약어*/
	private String buySNm;
	/**사업자번호*/
	private String bsnsNum;
	/**대표자명*/
	private String bossNm;
	/**업태*/
	private String busiCon;
	/**업종*/
	private String busiType;
	/**회사대표전화번호*/
	private String telNo;
	/**FAX번호*/
	private String faxNo;
	/**법인번호*/
	private String corpNum;
	/**영업담당자*/
	private String salesPrNm;
	/**영업담당자휴대폰*/
	private String salesPrHp;
	/**마감담당자*/
	private String closePrNm;
	/**마감담당자휴대폰*/
	private String closePrHp;
	/**물류센터담당자휴대폰*/
	private String centerPrHp;

	/**물류센터팩스*/
	private String centerFax;
	/**우편번호*/
	private String zipCd;
	/**주소1*/
	private String addr1;
	/**주소2*/
	private String addr2;
	
	/**센터우편코드*/
	private String centerZipCd;
	/**센터주소1*/
	private String centerZipAddr;
	/**센터주소2*/
	private String centerDtlAddr;
	/**이메일*/
	private String email;
	/**이메일(세금계산서용)*/
	private String emailTax;
	/**결제조건*/
	private String setlCon;
	/**결제일자*/
	private String setlDt;
	/**은행예금주*/
	private String bankDep;
	/**은행명*/
	private String bankNm;
	/**은행계좌번호*/
	private String bankAccNum;
	/**장려금유무*/
	private String subsidyYn;
	/**송금수수료구븐*/
	private String remFeeClass;
	/**외상매입여부*/
	private String crPurYn;
	/**거래시작일*/
	private String startDt;
	/**비고1*/
	private String remakrs1;
	/**비고2*/
	private String remakrs2;
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
	
	
	/*매입처코드*/
	private String   find_buyCd		;	
	/*매입처명*/
	private String   find_buyNm		;	
	
	/*사업자번호*/
	private String   find_bsnsNum	;	
	/*대표자명*/
	private String   find_bossNm	;	
	/*업태*/
	private String   find_busiCon	;	
	/*업종*/
	private String   find_busiType	;	
	/*영업담당자*/
	private String   find_salesPrNm	;	
	
	
	
	public String getEmailTax() {
		return emailTax;
	}

	public void setEmailTax(String emailTax) {
		this.emailTax = emailTax;
	}

	public void setRemakrs1(String remakrs1) {
		this.remakrs1 = remakrs1;
	}

	public String getFind_buyNm() {
		return find_buyNm;
	}

	public void setFind_buyNm(String find_buyNm) {
		this.find_buyNm = find_buyNm;
	}

	public String getFind_bsnsNum() {
		return find_bsnsNum;
	}

	public void setFind_bsnsNum(String find_bsnsNum) {
		this.find_bsnsNum = find_bsnsNum;
	}

	public String getFind_bossNm() {
		return find_bossNm;
	}

	public void setFind_bossNm(String find_bossNm) {
		this.find_bossNm = find_bossNm;
	}

	public String getFind_busiCon() {
		return find_busiCon;
	}

	public void setFind_busiCon(String find_busiCon) {
		this.find_busiCon = find_busiCon;
	}

	public String getFind_busiType() {
		return find_busiType;
	}

	public void setFind_busiType(String find_busiType) {
		this.find_busiType = find_busiType;
	}

	public String getFind_salesPrNm() {
		return find_salesPrNm;
	}

	public void setFind_salesPrNm(String find_salesPrNm) {
		this.find_salesPrNm = find_salesPrNm;
	}

	public String getFind_buyCd() {
		return find_buyCd;
	}

	public void setFind_buyCd(String find_buyCd) {
		this.find_buyCd = find_buyCd;
	}

	public String getBuyCd() {
		return buyCd;
	}

	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}

	public String getBuyNm() {
		return buyNm;
	}

	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
	}

	public String getBuySNm() {
		return buySNm;
	}

	public void setBuySNm(String buySNm) {
		this.buySNm = buySNm;
	}

	public String getBsnsNum() {
		return bsnsNum;
	}

	public void setBsnsNum(String bsnsNum) {
		this.bsnsNum = bsnsNum;
	}

	public String getBossNm() {
		return bossNm;
	}

	public void setBossNm(String bossNm) {
		this.bossNm = bossNm;
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

	public String getCorpNum() {
		return corpNum;
	}

	public void setCorpNum(String corpNum) {
		this.corpNum = corpNum;
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

	public String getClosePrNm() {
		return closePrNm;
	}

	public void setClosePrNm(String closePrNm) {
		this.closePrNm = closePrNm;
	}

	public String getClosePrHp() {
		return closePrHp;
	}

	public void setClosePrHp(String closePrHp) {
		this.closePrHp = closePrHp;
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

	public String getCenterZipCd() {
		return centerZipCd;
	}

	public void setCenterZipCd(String centerZipCd) {
		this.centerZipCd = centerZipCd;
	}

	public String getCenterZipAddr() {
		return centerZipAddr;
	}

	public void setCenterZipAddr(String centerZipAddr) {
		this.centerZipAddr = centerZipAddr;
	}

	public String getCenterDtlAddr() {
		return centerDtlAddr;
	}

	public void setCenterDtlAddr(String centerDtlAddr) {
		this.centerDtlAddr = centerDtlAddr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSetlCon() {
		return setlCon;
	}

	public void setSetlCon(String setlCon) {
		this.setlCon = setlCon;
	}

	public String getSetlDt() {
		return setlDt;
	}

	public void setSetlDt(String setlDt) {
		this.setlDt = setlDt;
	}

	public String getBankDep() {
		return bankDep;
	}

	public void setBankDep(String bankDep) {
		this.bankDep = bankDep;
	}

	public String getBankNm() {
		return bankNm;
	}

	public void setBankNm(String bankNm) {
		this.bankNm = bankNm;
	}

	public String getBankAccNum() {
		return bankAccNum;
	}

	public void setBankAccNum(String bankAccNum) {
		this.bankAccNum = bankAccNum;
	}

	public String getSubsidyYn() {
		return subsidyYn;
	}

	public void setSubsidyYn(String subsidyYn) {
		this.subsidyYn = subsidyYn;
	}

	public String getRemFeeClass() {
		return remFeeClass;
	}

	public void setRemFeeClass(String remFeeClass) {
		this.remFeeClass = remFeeClass;
	}

	public String getCrPurYn() {
		return crPurYn;
	}

	public void setCrPurYn(String crPurYn) {
		this.crPurYn = crPurYn;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getRemakrs1() {
		return remakrs1;
	}

	public void setRemark1(String remakrs1) {
		this.remakrs1 = remakrs1;
	}

	public String getRemakrs2() {
		return remakrs2;
	}

	public void setRemakrs2(String remakrs2) {
		this.remakrs2 = remakrs2;
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
	public String getCenterPrHp() {
		return centerPrHp;
	}

	public void setCenterPrHp(String centerPrHp) {
		this.centerPrHp = centerPrHp;
	}




	
	

}
