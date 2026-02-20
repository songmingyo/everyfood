package com.doppio.workplace.sm.service;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.tronic.util.StringUtil;
import com.doppio.common.model.Page;


/**
 * @author dada
 * @Description :매출처출고  vo
 * @Class : CusSalesDlvVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  23.04.08   dada       
 * </pre>
 * @version : 1.0
 */

public class CusRtnRegVo  extends Page  implements Serializable {

	private static final long serialVersionUID = 5746453328068624800L;
	
	private List<CusRtnRegVo>  cusRtnRegList;
	
	/** 반품일자 */
	private String rtnDt	 ;
	/** 매출처코드(pk) */
	private String salesCd	 ;
	/** 전표번호(pk) */
	private String salesSlipNo    ;
	/** 전표순번(pk) */
	private String salesSeq    ;
	/** 상품코드(pk) */
	private String prdtCd	 ;
	
	/**매출처명 */
	private String salesNm	 ;
	/**상품명 TFM_PRDT_MST*/
	private String prdtNm	 ;
	
	/**매출구분 */
	private String RtnClass;
	/**매출구분명 */
	private String RtnClassNm;
	
	/*공급가*/
	private String pureAmt;
	/**부가세*/
	private String vatAmt		;
	/**합계급액*/
	private String totAmt     ; 	
	
	/**규격*/
	private String prdtStd		;
	/**주문단위*/
	private String ordUnit      ; 
	/**매출수량*/
	private String rtnQty		;
	/**판매단가*/
	private String price		; 
	/**창고코드*/
	private String whCd			; 
	/**장고명칭*/
	private String whNm 		;  
	/**전표 헤더 비고*/
	private String remarks1		;
	/**전표 품목 비고*/
	private String remarks2		;
	
	/**사용유무*/
	private String useYn;
	/**등록자*/
	private String regNm;
	/*등록일시*/
	private String regDt;
	/**수정자*/
	private String modNm;
	/**수정일시*/
	private String modDt;
	
	
	/*작업자 아이디 */
	private String workId;
	
	/**박스수량*/
	private String qtyBox; 
	/* 박스 Qty */
	private String boxQty;
	/* 부가세유무 */
	private String vatYn;
	/* 규격유무 */
	private String stdYn;
	
	/* 조회 매출처명 */
	private String searchSalesCd;
	/* 조회 반품일자 */
	private String searchRtnDt;
	/* 조회 반품구분 */
	private String searchRtnClass;
	/* 조회 품목코드 */
	private String searchPrdtCd;
	
	/* 전표번호들 */
	private String salesSlipNos;
	/* 매출처들 */
	private String salesCds;
	// 매출일자들
	private String rtnDts;
	// 출력구분
	private String prtClass;
	
	
	/* 조회조건 출고예정일 */
	private String prtRtnDt;
	
	
	/*출력 */
	private String title;
	/* 사업자번호 */
	private String bsnsNum;
	/*대표자명  */
	private String bossNm;
	/*주소  */
	private String addr;
	/*전화번호  */
	private String telNo;
	/*배송기사  */
	private String drvSnm;
	/*배송기사전화번호  */
	private String drvMTelNo;
	/*영업사원전화번호  */
	private String salesMTelNo;
	/**영업사원명*/
	private String salesPrNm;
	
	/*주문단위 */
	private String ordUnitNm;
	
	/*과세공급가  */
	private double sumPureAmtTaxY;
	/*과세부가세  */
	private double sumVatAmtTaxY;
	/*과세합계  */
	private double sumTotAmtTaxY;
	/*면세공급가  */
	private double sumPureAmtTaxN;
	/*면세부가세  */
	private double sumVatAmtTaxN;
	/*면세합계  */
	private double sumTotAmtTaxN;
	/*총합계  */
	private double sumTotAmt;
	//표기 구분
	private String chTail;
	//미수금
	private double balAmt;
	//일매출합계
	private double daySalesAmt;
	//단가
	private double prtPrice;
	//매출수량*/
	private String salesQty;
	//공급가*/
	private double prtPureAmt;
	//부가세*/
	private double prtVatAmt;
	//합계급액*/
	private double prtTotAmt;
	//과면세합계
	private double sumYnTotAmt;
	//lack번호
	private String lackNo1;
	//lack번호
	private String carNum;
	//배송기사명
	private String drvSnmName;
	//발주수량
	private double prtOrdQty;
	//재고수량
	private double prtStkQty;
	
	
	
	
	private ArrayList<String> slipNoList;	
	
	private List<CusRtnRegVo> item;
	
	
	
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
	}
	public String getSalesSlipNos() {
		return salesSlipNos;
	}
	public void setSalesSlipNos(String salesSlipNos) {
		this.salesSlipNos = salesSlipNos;
	}
	public String getSalesCds() {
		return salesCds;
	}
	public void setSalesCds(String salesCds) {
		this.salesCds = salesCds;
	}
	public String getRtnDts() {
		return rtnDts;
	}
	public void setRtnDts(String rtnDts) {
		this.rtnDts = rtnDts;
	}
	public String getPrtRtnDt() {
		return prtRtnDt;
	}
	public void setPrtRtnDt(String prtRtnDt) {
		this.prtRtnDt = prtRtnDt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getDrvSnm() {
		return drvSnm;
	}
	public void setDrvSnm(String drvSnm) {
		this.drvSnm = drvSnm;
	}
	public String getDrvMTelNo() {
		return drvMTelNo;
	}
	public void setDrvMTelNo(String drvMTelNo) {
		this.drvMTelNo = drvMTelNo;
	}
	public String getSalesMTelNo() {
		return salesMTelNo;
	}
	public void setSalesMTelNo(String salesMTelNo) {
		this.salesMTelNo = salesMTelNo;
	}
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public double getSumPureAmtTaxY() {
		return sumPureAmtTaxY;
	}
	public void setSumPureAmtTaxY(double sumPureAmtTaxY) {
		this.sumPureAmtTaxY = sumPureAmtTaxY;
	}
	public double getSumVatAmtTaxY() {
		return sumVatAmtTaxY;
	}
	public void setSumVatAmtTaxY(double sumVatAmtTaxY) {
		this.sumVatAmtTaxY = sumVatAmtTaxY;
	}
	public double getSumTotAmtTaxY() {
		return sumTotAmtTaxY;
	}
	public void setSumTotAmtTaxY(double sumTotAmtTaxY) {
		this.sumTotAmtTaxY = sumTotAmtTaxY;
	}
	public double getSumPureAmtTaxN() {
		return sumPureAmtTaxN;
	}
	public void setSumPureAmtTaxN(double sumPureAmtTaxN) {
		this.sumPureAmtTaxN = sumPureAmtTaxN;
	}
	public double getSumVatAmtTaxN() {
		return sumVatAmtTaxN;
	}
	public void setSumVatAmtTaxN(double sumVatAmtTaxN) {
		this.sumVatAmtTaxN = sumVatAmtTaxN;
	}
	public double getSumTotAmtTaxN() {
		return sumTotAmtTaxN;
	}
	public void setSumTotAmtTaxN(double sumTotAmtTaxN) {
		this.sumTotAmtTaxN = sumTotAmtTaxN;
	}
	public double getSumTotAmt() {
		return sumTotAmt;
	}
	public void setSumTotAmt(double sumTotAmt) {
		this.sumTotAmt = sumTotAmt;
	}
	public String getChTail() {
		return chTail;
	}
	public void setChTail(String chTail) {
		this.chTail = chTail;
	}
	public double getBalAmt() {
		return balAmt;
	}
	public void setBalAmt(double balAmt) {
		this.balAmt = balAmt;
	}
	public double getDaySalesAmt() {
		return daySalesAmt;
	}
	public void setDaySalesAmt(double daySalesAmt) {
		this.daySalesAmt = daySalesAmt;
	}
	public double getPrtPrice() {
		return prtPrice;
	}
	public void setPrtPrice(double prtPrice) {
		this.prtPrice = prtPrice;
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
	public double getSumYnTotAmt() {
		return sumYnTotAmt;
	}
	public void setSumYnTotAmt(double sumYnTotAmt) {
		this.sumYnTotAmt = sumYnTotAmt;
	}
	public String getLackNo1() {
		return lackNo1;
	}
	public void setLackNo1(String lackNo1) {
		this.lackNo1 = lackNo1;
	}
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getDrvSnmName() {
		return drvSnmName;
	}
	public void setDrvSnmName(String drvSnmName) {
		this.drvSnmName = drvSnmName;
	}
	public double getPrtOrdQty() {
		return prtOrdQty;
	}
	public void setPrtOrdQty(double prtOrdQty) {
		this.prtOrdQty = prtOrdQty;
	}
	public double getPrtStkQty() {
		return prtStkQty;
	}
	public void setPrtStkQty(double prtStkQty) {
		this.prtStkQty = prtStkQty;
	}
	public String getPrtClass() {
		return prtClass;
	}
	public void setPrtClass(String prtClass) {
		this.prtClass = prtClass;
	}
	public ArrayList<String> getSlipNoList() {
		return slipNoList;
	}
	public void setSlipNoList(ArrayList<String> slipNoList) {
		this.slipNoList = slipNoList;
	}
	public List<CusRtnRegVo> getItem() {
		return item;
	}
	public void setItem(List<CusRtnRegVo> item) {
		this.item = item;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
	}
	public String getSearchSalesCd() {
		return searchSalesCd;
	}
	public void setSearchSalesCd(String searchSalesCd) {
		this.searchSalesCd = searchSalesCd;
	}
	public String getSearchRtnDt() {
		return searchRtnDt;
	}
	public void setSearchRtnDt(String searchRtnDt) {
		this.searchRtnDt = searchRtnDt;
	}
	public String getSearchRtnClass() {
		return searchRtnClass;
	}
	public void setSearchRtnClass(String searchRtnClass) {
		this.searchRtnClass = searchRtnClass;
	}
	public List<CusRtnRegVo> getCusRtnRegList() {
		return cusRtnRegList;
	}
	public void setCusRtnRegList(List<CusRtnRegVo> cusRtnRegList) {
		this.cusRtnRegList = cusRtnRegList;
	}
	public String getRtnDt() {
		return rtnDt;
	}
	public void setRtnDt(String rtnDt) {
		this.rtnDt = rtnDt;
	}
	public String getSalesCd() {
		return salesCd;
	}
	public void setSalesCd(String salesCd) {
		this.salesCd = salesCd;
	}
	public String getSalesSlipNo() {
		return salesSlipNo;
	}
	public void setSalesSlipNo(String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}
	public String getSalesSeq() {
		return salesSeq;
	}
	public void setSalesSeq(String salesSeq) {
		this.salesSeq = salesSeq;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getSalesNm() {
		return salesNm;
	}
	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}
	public String getPrdtNm() {
		return prdtNm;
	}
	public void setPrdtNm(String prdtNm) {
		this.prdtNm = prdtNm;
	}
	
	public String getRtnClass() {
		return RtnClass;
	}
	public void setRtnClass(String rtnClass) {
		RtnClass = rtnClass;
	}
	public String getRtnClassNm() {
		return RtnClassNm;
	}
	public void setRtnClassNm(String rtnClassNm) {
		RtnClassNm = rtnClassNm;
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
	public String getRtnQty() {
		return rtnQty;
	}
	public void setRtnQty(String rtnQty) {
		this.rtnQty = rtnQty;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
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
	public String getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
	}
	public String getVatYn() {
		return vatYn;
	}
	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}
	public String getStdYn() {
		return stdYn;
	}
	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}

	

}
