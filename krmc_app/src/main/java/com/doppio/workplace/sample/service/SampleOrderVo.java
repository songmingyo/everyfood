package com.doppio.workplace.sample.service;

import java.io.Serializable;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

public class SampleOrderVo extends Page implements Serializable  {

	private static final long serialVersionUID = -4536270354615148755L;
	
	/*업체코드*/
	private String	venCd;
	/*점포코드*/
	private String	strCd;
	/*업체명*/
	private String	venNm;
	/*사업자명*/
	private String	bmanNo;
	private String	btyp;
	private String	bkind;
	private String	addr1;
	private String	addr2;
	private String	reptelNo;
	private String	presidNm;
	private String	trdTypFg;
	private String	usplypenaltyFg;
	private String	email;
	private String	promoAmtRt;
	private String	trdStDy;
	private String	trdEndDy;
	private String	regDt;
	private String	lstChgDt;
	private String	chgFg;
	private String	splyCycle;
	private String	splyCond;
	/*발주전표번호*/
	private String	ordSlipNo;
	/*발주일자*/
	private String	ordDy;
	private String	ordVseq;
	private String	prodPatFg;
	/*발주구분코드*/
	private String	ordFg;
	private String	routeFg;
	/*발주총금액*/
	private String	totPrc;
	/*발주총수량*/
	private String	totQty;
	/*납품일자*/
	private String	splyDy;
	/*납품일자(시간)*/
	private String	splyTm;
	/*납품일자(합)*/
	private String  splyDyTm;
	private String	ctrArrDy;
	private String	logiCatNm;
	private String	insDate;
	private String	lstCfmFg;
	private String	userHitDt;
	private String	userInsDt;
	private String	mdInsDt;
	private String	smsFg;
	private String	pdcOrdSlipNo;
	private String	ctrCd;
	private String	supplyCd;
	private String	supplyNm;
	/*발주가능수량*/
	private String	ansQty;
	/*발주가능금액*/
	private String	ansPrc;
	private String	prodCd;
	private String	srcmkCd;
	private String	prodNm;
	private String	regVseq;
	private String	logiBcd;
	private String	evtCd;
	private String	ordIpsu;
	private String	ordUnit;
	private String	prodStd;
	private String	ordQty;
	private String	splyAbleQty;
	private String	buyDan;
	private String	buyPrc;
	private String	btInFg;
	private String	ordTypFg;
	private String	convRt;
	private String	negoBuyPrc;
	private String	homeNm;
	private String	taxFg;
	private String	protectTagFg;
	private String	insDt;
	private String	producer;
	private String	sorterFg;
	private String	splyAblePrc;

	private String sBmanNo;	
	private String sRepTelNo;
	private String sVenNm;	
	private String sPresidNm;	
	private String rBmanNo;
	private String rStrCd;		
	private String rVenNm;		
	private String rPresidNm;	
	private String ordFgNm;	
	private String taxFgNm;		
	private String[] ordSlipNoList;
	private String sVenCd;
	private String splyAmt;
	
	private String sumSplyAmt;
	private String addTaxAmt;
	private String totalSum;
	private String sumOrdQty;
	
	private String logo;
	private String ordSlipNoBarcode;
	
	public String getVenCd() {
		return venCd;
	}
	public void setVenCd(String venCd) {
		this.venCd = venCd;
	}
	public String getStrCd() {
		return strCd;
	}
	public void setStrCd(String strCd) {
		this.strCd = strCd;
	}
	public String getVenNm() {
		return venNm;
	}
	public void setVenNm(String venNm) {
		this.venNm = venNm;
	}
	public String getBmanNo() {
		return bmanNo;
	}
	public void setBmanNo(String bmanNo) {
		this.bmanNo = bmanNo;
	}
	public String getBtyp() {
		return btyp;
	}
	public void setBtyp(String btyp) {
		this.btyp = btyp;
	}
	public String getBkind() {
		return bkind;
	}
	public void setBkind(String bkind) {
		this.bkind = bkind;
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
	public String getReptelNo() {
		return reptelNo;
	}
	public void setReptelNo(String reptelNo) {
		this.reptelNo = reptelNo;
	}
	public String getPresidNm() {
		return presidNm;
	}
	public void setPresidNm(String presidNm) {
		this.presidNm = presidNm;
	}
	public String getTrdTypFg() {
		return trdTypFg;
	}
	public void setTrdTypFg(String trdTypFg) {
		this.trdTypFg = trdTypFg;
	}
	public String getUsplypenaltyFg() {
		return usplypenaltyFg;
	}
	public void setUsplypenaltyFg(String usplypenaltyFg) {
		this.usplypenaltyFg = usplypenaltyFg;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPromoAmtRt() {
		return promoAmtRt;
	}
	public void setPromoAmtRt(String promoAmtRt) {
		this.promoAmtRt = promoAmtRt;
	}
	public String getTrdStDy() {
		return trdStDy;
	}
	public void setTrdStDy(String trdStDy) {
		this.trdStDy = trdStDy;
	}
	public String getTrdEndDy() {
		return trdEndDy;
	}
	public void setTrdEndDy(String trdEndDy) {
		this.trdEndDy = trdEndDy;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getLstChgDt() {
		return lstChgDt;
	}
	public void setLstChgDt(String lstChgDt) {
		this.lstChgDt = lstChgDt;
	}
	public String getChgFg() {
		return chgFg;
	}
	public void setChgFg(String chgFg) {
		this.chgFg = chgFg;
	}
	public String getSplyCycle() {
		return splyCycle;
	}
	public void setSplyCycle(String splyCycle) {
		this.splyCycle = splyCycle;
	}
	public String getSplyCond() {
		return splyCond;
	}
	public void setSplyCond(String splyCond) {
		this.splyCond = splyCond;
	}
	public String getOrdSlipNo() {
		return ordSlipNo;
	}
	public void setOrdSlipNo(String ordSlipNo) {
		this.ordSlipNo = ordSlipNo;
	}
	public String getOrdDy() {
		return ordDy;
	}
	public void setOrdDy(String ordDy) {
		this.ordDy = ordDy;
	}
	public String getOrdVseq() {
		return ordVseq;
	}
	public void setOrdVseq(String ordVseq) {
		this.ordVseq = ordVseq;
	}
	public String getProdPatFg() {
		return prodPatFg;
	}
	public void setProdPatFg(String prodPatFg) {
		this.prodPatFg = prodPatFg;
	}
	public String getOrdFg() {
		return ordFg;
	}
	public void setOrdFg(String ordFg) {
		this.ordFg = ordFg;
	}
	public String getRouteFg() {
		return routeFg;
	}
	public void setRouteFg(String routeFg) {
		this.routeFg = routeFg;
	}
	public String getTotPrc() {
		return totPrc;
	}
	public void setTotPrc(String totPrc) {
		this.totPrc = totPrc;
	}
	public String getTotQty() {
		return totQty;
	}
	public void setTotQty(String totQty) {
		this.totQty = totQty;
	}
	public String getSplyDy() {
		return splyDy;
	}
	public void setSplyDy(String splyDy) {
		this.splyDy = splyDy;
	}
	public String getSplyTm() {
		return splyTm;
	}
	public void setSplyTm(String splyTm) {
		this.splyTm = splyTm;
	}
	public String getCtrArrDy() {
		return ctrArrDy;
	}
	public void setCtrArrDy(String ctrArrDy) {
		this.ctrArrDy = ctrArrDy;
	}
	public String getLogiCatNm() {
		return logiCatNm;
	}
	public void setLogiCatNm(String logiCatNm) {
		this.logiCatNm = logiCatNm;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getLstCfmFg() {
		return lstCfmFg;
	}
	public void setLstCfmFg(String lstCfmFg) {
		this.lstCfmFg = lstCfmFg;
	}
	public String getUserHitDt() {
		return userHitDt;
	}
	public void setUserHitDt(String userHitDt) {
		this.userHitDt = userHitDt;
	}
	public String getUserInsDt() {
		return userInsDt;
	}
	public void setUserInsDt(String userInsDt) {
		this.userInsDt = userInsDt;
	}
	public String getMdInsDt() {
		return mdInsDt;
	}
	public void setMdInsDt(String mdInsDt) {
		this.mdInsDt = mdInsDt;
	}
	public String getSmsFg() {
		return smsFg;
	}
	public void setSmsFg(String smsFg) {
		this.smsFg = smsFg;
	}
	public String getPdcOrdSlipNo() {
		return pdcOrdSlipNo;
	}
	public void setPdcOrdSlipNo(String pdcOrdSlipNo) {
		this.pdcOrdSlipNo = pdcOrdSlipNo;
	}
	public String getCtrCd() {
		return ctrCd;
	}
	public void setCtrCd(String ctrCd) {
		this.ctrCd = ctrCd;
	}
	public String getSupplyCd() {
		return supplyCd;
	}
	public void setSupplyCd(String supplyCd) {
		this.supplyCd = supplyCd;
	}
	public String getSupplyNm() {
		return supplyNm;
	}
	public void setSupplyNm(String supplyNm) {
		this.supplyNm = supplyNm;
	}
	public String getAnsQty() {
		return ansQty;
	}
	public void setAnsQty(String ansQty) {
		this.ansQty = ansQty;
	}
	public String getAnsPrc() {
		return ansPrc;
	}
	public void setAnsPrc(String ansPrc) {
		this.ansPrc = ansPrc;
	}
	public String getProdCd() {
		return prodCd;
	}
	public void setProdCd(String prodCd) {
		this.prodCd = prodCd;
	}
	public String getSrcmkCd() {
		return srcmkCd;
	}
	public void setSrcmkCd(String srcmkCd) {
		this.srcmkCd = srcmkCd;
	}
	public String getProdNm() {
		return prodNm;
	}
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	public String getRegVseq() {
		return regVseq;
	}
	public void setRegVseq(String regVseq) {
		this.regVseq = regVseq;
	}
	public String getLogiBcd() {
		return logiBcd;
	}
	public void setLogiBcd(String logiBcd) {
		this.logiBcd = logiBcd;
	}
	public String getEvtCd() {
		return evtCd;
	}
	public void setEvtCd(String evtCd) {
		this.evtCd = evtCd;
	}
	public String getOrdIpsu() {
		return ordIpsu;
	}
	public void setOrdIpsu(String ordIpsu) {
		this.ordIpsu = ordIpsu;
	}
	public String getOrdUnit() {
		return ordUnit;
	}
	public void setOrdUnit(String ordUnit) {
		this.ordUnit = ordUnit;
	}
	public String getProdStd() {
		return prodStd;
	}
	public void setProdStd(String prodStd) {
		this.prodStd = prodStd;
	}
	public String getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
	}
	public String getSplyAbleQty() {
		return splyAbleQty;
	}
	public void setSplyAbleQty(String splyAbleQty) {
		this.splyAbleQty = splyAbleQty;
	}
	public String getBuyDan() {
		return buyDan;
	}
	public void setBuyDan(String buyDan) {
		this.buyDan = buyDan;
	}
	public String getBuyPrc() {
		return buyPrc;
	}
	public void setBuyPrc(String buyPrc) {
		this.buyPrc = buyPrc;
	}
	public String getBtInFg() {
		return btInFg;
	}
	public void setBtInFg(String btInFg) {
		this.btInFg = btInFg;
	}
	public String getOrdTypFg() {
		return ordTypFg;
	}
	public void setOrdTypFg(String ordTypFg) {
		this.ordTypFg = ordTypFg;
	}
	public String getConvRt() {
		return convRt;
	}
	public void setConvRt(String convRt) {
		this.convRt = convRt;
	}
	public String getNegoBuyPrc() {
		return negoBuyPrc;
	}
	public void setNegoBuyPrc(String negoBuyPrc) {
		this.negoBuyPrc = negoBuyPrc;
	}
	public String getHomeNm() {
		return homeNm;
	}
	public void setHomeNm(String homeNm) {
		this.homeNm = homeNm;
	}
	public String getTaxFg() {
		return taxFg;
	}
	public void setTaxFg(String taxFg) {
		this.taxFg = taxFg;
	}
	public String getProtectTagFg() {
		return protectTagFg;
	}
	public void setProtectTagFg(String protectTagFg) {
		this.protectTagFg = protectTagFg;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getSorterFg() {
		return sorterFg;
	}
	public void setSorterFg(String sorterFg) {
		this.sorterFg = sorterFg;
	}
	public String getSplyAblePrc() {
		return splyAblePrc;
	}
	public void setSplyAblePrc(String splyAblePrc) {
		this.splyAblePrc = splyAblePrc;
	}
	public String getSplyDyTm() {
		return splyDyTm;
	}
	public void setSplyDyTm(String splyDyTm) {
		this.splyDyTm = splyDyTm;
	}
	
	public String getOrdDyLocale(){
		return DateUtil.convertDateLocale(ordDy,"","");
	}
	
	public String getSplyDyTmLocale(){
		return DateUtil.convertDateLocale(splyDy,"","") + " " + DateUtil.convertDateLocale(splyTm,"HHmm","HH:mm");
	}
	public String getsBmanNo() {
		return sBmanNo;
	}
	public void setsBmanNo(String sBmanNo) {
		this.sBmanNo = sBmanNo;
	}
	public String getsRepTelNo() {
		return sRepTelNo;
	}
	public void setsRepTelNo(String sRepTelNo) {
		this.sRepTelNo = sRepTelNo;
	}
	public String getsVenNm() {
		return sVenNm;
	}
	public void setsVenNm(String sVenNm) {
		this.sVenNm = sVenNm;
	}
	public String getsPresidNm() {
		return sPresidNm;
	}
	public void setsPresidNm(String sPresidNm) {
		this.sPresidNm = sPresidNm;
	}
	public String getrBmanNo() {
		return rBmanNo;
	}
	public void setrBmanNo(String rBmanNo) {
		this.rBmanNo = rBmanNo;
	}
	public String getrStrCd() {
		return rStrCd;
	}
	public void setrStrCd(String rStrCd) {
		this.rStrCd = rStrCd;
	}
	public String getrVenNm() {
		return rVenNm;
	}
	public void setrVenNm(String rVenNm) {
		this.rVenNm = rVenNm;
	}
	public String getrPresidNm() {
		return rPresidNm;
	}
	public void setrPresidNm(String rPresidNm) {
		this.rPresidNm = rPresidNm;
	}
	public String getOrdFgNm() {
		return ordFgNm;
	}
	public void setOrdFgNm(String ordFgNm) {
		this.ordFgNm = ordFgNm;
	}
	public String getTaxFgNm() {
		return taxFgNm;
	}
	public void setTaxFgNm(String taxFgNm) {
		this.taxFgNm = taxFgNm;
	}
	public String[] getOrdSlipNoList() {
		return ordSlipNoList;
	}
	public void setOrdSlipNoList(String[] ordSlipNoList) {
		this.ordSlipNoList = ordSlipNoList;
	}
	public String getsVenCd() {
		return sVenCd;
	}
	public void setsVenCd(String sVenCd) {
		this.sVenCd = sVenCd;
	}
	public String getSplyAmt() {
		return splyAmt;
	}
	public void setSplyAmt(String splyAmt) {
		this.splyAmt = splyAmt;
	}
	
	public String getOrdDyFmt(){
		return DateUtil.convertDateFmt(ordDy, "", "");
	}
	
	public String getSplyDtFmt(){
		return DateUtil.convertDateFmt(splyDy, "", "");
	}
	
	public String getSplyTmFmt(){
		return DateUtil.convertDateLocale(splyTm,"HHmm","HH:mm");
	}
	
	public String getAddTaxAmt() {
		return addTaxAmt;
	}
	public void setAddTaxAmt(String addTaxAmt) {
		this.addTaxAmt = addTaxAmt;
	}
	public String getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
	}
	public String getSumOrdQty() {
		return sumOrdQty;
	}
	public void setSumOrdQty(String sumOrdQty) {
		this.sumOrdQty = sumOrdQty;
	}
	public String getSumSplyAmt() {
		return sumSplyAmt;
	}
	public void setSumSplyAmt(String sumSplyAmt) {
		this.sumSplyAmt = sumSplyAmt;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getOrdSlipNoBarcode() {
		return ordSlipNoBarcode;
	}
	public void setOrdSlipNoBarcode(String ordSlipNoBarcode) {
		this.ordSlipNoBarcode = ordSlipNoBarcode;
	}
	
	

}
