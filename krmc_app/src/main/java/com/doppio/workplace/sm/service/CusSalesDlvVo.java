package com.doppio.workplace.sm.service;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

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

public class CusSalesDlvVo  extends Page  implements Serializable {

	private static final long serialVersionUID = 5746453328068624800L;
	
	private List<CusSalesDlvVo>  cusSalesDlvList;
	private List<HashMap<String, String>>  cusSalesDlvExcelUploadList;
	
	/** 납품일자(pk) */
	private String salesDt	 ;
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
	
	/**마감유무*/
	private String closeYn	 ;
	/**거래명세표출력유무		*/
	private String trnscPrtYn ;    
	/**집계표출력유무*/ 
	private String smrPrtYn	 ;	
	/**차량별집계표출력유무*/
	private String carPrtYn	 ;
	/** 재고집계표출력유무*/
	private String stkPrtYn	 ;
	/**거래명세표출력유무		*/
	private String trnscPrtYnNm ;    
	/**집계표출력유무*/ 
	private String smrPrtYnNm	 ;	
	/**차량별집계표출력유무*/
	private String carPrtYnNm	 ;
	/** 재고집계표출력유무*/
	private String stkPrtYnNm	 ;
	/**거래명세표출력담당자*/
	private String trnscPrtId ;	
	/**거래명세표출력담당자*/
	private String trnscPrtIdNm ;
	/**거래명세표출력시간*/
	private String trnscPrtDt  ;
	/**배송차량코드*/
	private String carCd;
	/**배송차량명칭*/
	private String carNm;
	/**미수잔액표기유무 */
	private String accRcvYn;
	/**매출구분 */
	private String freeClass;
	/**매출구분명 */
	private String freeClassNm;
	/**영업사원코드  */
	private String salesPrCd;
	/**영업사원명*/
	private String salesPrNm;
	/**발주일자*/
	private String ordDt;
	/**매출처구분 */
	private String salesClass;
	
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
	/**발주수량*/
	private String ordQty		;
	/**매출수량*/
	private String salesQty		;
	/**박스수량*/
	private String qtyBox		; 
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
	/**마진율 */
	private String marRate;
	
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
	
	/* 박스 Qty */
	private String boxQty;
	/* 부가세유무 */
	private String vatYn;
	/* 규격유무 */
	private String stdYn;
	/* 그리드상태 */
	private String gridFlag;
	
	/* 본사구분  */
	private String hqClass;
	
	
	/* 주방홀구분 */
	private String fbClass;
	/* 거래처 매출처코드 */
	private String cltSalesCd;
	/* 거래처 상품코드 */
	private String salesPrdtCd;
	/* 발주금액 */
	private String ordAmt;
	//주방홀구분을 위한 임시 변수
	private String slipNoChg;
	
	/* 전표번호들 */
	private String salesSlipNos;
	/* 매출처들 */
	private String salesCds;
	// 매출일자들
	private String salesDts;
	// 출력구분
	private String prtClass;
	// 샘플수량
	private String freeQty;
	// 원 금액
	private String orgAmt;
	// 원 수량
	private String orgQty;
	
	
	/* 조회조건 출고예정일 */
	private String searchSalesDt;
	
	
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
	//미수금
	private double balAmt;
	//일매출합계
	private double daySalesAmt;
	//단가
	private double prtPrice;
	//매출수량*/
	private double prtSalesQty;
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
	/* 박스 Qty */
	private String boxQtyComp;
	
	//금액 표기 구분
	private String amtNone;
		
	//전표번호 배열 선언
	
	private ArrayList<String> slipNoList; 
	
		
	private List<CusSalesDlvVo> item;
	
	//재고수량 add by song min kyo
	private String stkQty;
	
	public String getStkQty() {
		return stkQty;
	}
	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}
	public String getOrgQty() {
		return orgQty;
	}
	public void setOrgQty(String orgQty) {
		this.orgQty = orgQty;
	}
	public String getOrgAmt() {
		return orgAmt;
	}
	public void setOrgAmt(String orgAmt) {
		this.orgAmt = orgAmt;
	}
	public String getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(String freeQty) {
		this.freeQty = freeQty;
	}
	public String getMarRate() {
		return marRate;
	}
	public void setMarRate(String marRate) {
		this.marRate = marRate;
	}
	public String getAmtNone() {
		return amtNone;
	}
	public void setAmtNone(String amtNone) {
		this.amtNone = amtNone;
	}
	public String getHqClass() {
		return hqClass;
	}
	public void setHqClass(String hqClass) {
		this.hqClass = hqClass;
	}
	public String getSlipNoChg() {
		return slipNoChg;
	}
	public void setSlipNoChg(String slipNoChg) {
		this.slipNoChg = slipNoChg;
	}
	public String getBoxQtyComp() {
		return boxQtyComp;
	}
	public void setBoxQtyComp(String boxQtyComp) {
		this.boxQtyComp = boxQtyComp;
	}
	public double getPrtStkQty() {
		return prtStkQty;
	}
	public void setPrtStkQty(double prtStkQty) {
		this.prtStkQty = prtStkQty;
	}
	public ArrayList<String> getSlipNoList() {
		return slipNoList;
	}
	public void setSlipNoList(ArrayList<String> slipNoList) {
		this.slipNoList = slipNoList;
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
	public String getTrnscPrtYnNm() {
		return trnscPrtYnNm;
	}
	public void setTrnscPrtYnNm(String trnscPrtYnNm) {
		this.trnscPrtYnNm = trnscPrtYnNm;
	}
	public String getSmrPrtYnNm() {
		return smrPrtYnNm;
	}
	public void setSmrPrtYnNm(String smrPrtYnNm) {
		this.smrPrtYnNm = smrPrtYnNm;
	}
	public String getCarPrtYnNm() {
		return carPrtYnNm;
	}
	public void setCarPrtYnNm(String carPrtYnNm) {
		this.carPrtYnNm = carPrtYnNm;
	}
	public String getStkPrtYnNm() {
		return stkPrtYnNm;
	}
	public void setStkPrtYnNm(String stkPrtYnNm) {
		this.stkPrtYnNm = stkPrtYnNm;
	}
	public String getPrtClass() {
		return prtClass;
	}
	public void setPrtClass(String prtClass) {
		this.prtClass = prtClass;
	}
	public String getSalesDts() {
		return salesDts;
	}
	public void setSalesDts(String salesDts) {
		this.salesDts = salesDts;
	}
	public double getSumYnTotAmt() {
		return sumYnTotAmt;
	}
	public void setSumYnTotAmt(double sumYnTotAmt) {
		this.sumYnTotAmt = sumYnTotAmt;
	}
	public double getPrtPrice() {
		return prtPrice;
	}
	public void setPrtPrice(double prtPrice) {
		this.prtPrice = prtPrice;
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
	public double getPrtSalesQty() {
		return prtSalesQty;
	}
	public void setPrtSalesQty(double prtSalesQty) {
		this.prtSalesQty = prtSalesQty;
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
	public String getSalesCds() {
		return salesCds;
	}
	public void setSalesCds(String salesCds) {
		this.salesCds = salesCds;
	}
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
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
	
	public String getBsnsNum() {
		return bsnsNum;
	}
	public void setBsnsNum(String bsnsNum) {
		this.bsnsNum = bsnsNum;
	}
	public String getSearchSalesDt() {
		return searchSalesDt;
	}
	public void setSearchSalesDt(String searchSalesDt) {
		this.searchSalesDt = searchSalesDt;
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
	public String getFreeClass() {
		return freeClass;
	}
	public void setFreeClass(String freeClass) {
		this.freeClass = freeClass;
	}
	public String getFreeClassNm() {
		return freeClassNm;
	}
	public void setFreeClassNm(String freeClassNm) {
		this.freeClassNm = freeClassNm;
	}
	public String getFbClass() {
		return fbClass;
	}
	public void setFbClass(String fbClass) {
		this.fbClass = fbClass;
	}
	public String getCltSalesCd() {
		return cltSalesCd;
	}
	public void setCltSalesCd(String cltSalesCd) {
		this.cltSalesCd = cltSalesCd;
	}
	public String getSalesPrdtCd() {
		return salesPrdtCd;
	}
	public void setSalesPrdtCd(String salesPrdtCd) {
		this.salesPrdtCd = salesPrdtCd;
	}
	public String getOrdAmt() {
		return ordAmt;
	}
	public void setOrdAmt(String ordAmt) {
		this.ordAmt = ordAmt;
	}
	public List<HashMap<String, String>> getCusSalesDlvExcelUploadList() {
		return cusSalesDlvExcelUploadList;
	}
	public void setCusSalesDlvExcelUploadList(List<HashMap<String, String>> cusSalesDlvExcelUploadList) {
		this.cusSalesDlvExcelUploadList = cusSalesDlvExcelUploadList;
	}
	public String getOrdDt() {
		return ordDt;
	}
	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}	
	public List<CusSalesDlvVo> getCusSalesDlvList() {
		return cusSalesDlvList;
	}
	public void setCusSalesDlvList(List<CusSalesDlvVo> cusSalesDlvList) {
		this.cusSalesDlvList = cusSalesDlvList;
	}
	public String getStdYn() {
		return stdYn;
	}
	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}
	
	public String getSalesSeq() {
		return salesSeq;
	}
	public void setSalesSeq(String salesSeq) {
		this.salesSeq = salesSeq;
	}
	public String getSalesQty() {
		return salesQty;
	}
	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
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
	public String getTrnscPrtIdNm() {
		return trnscPrtIdNm;
	}
	public void setTrnscPrtIdNm(String trnscPrtIdNm) {
		this.trnscPrtIdNm = trnscPrtIdNm;
	}
	public String getSalesDt() {
		return salesDt;
	}
	public void setSalesDt(String salesDt) {
		this.salesDt = salesDt;
	}
	public String getSalesDtFmt() {
		if(salesDt.isEmpty())  return salesDt;
		else  return StringUtil.isToNumberStr(salesDt);
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
	public String getCloseYn() {
		return closeYn;
	}
	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}
	public String getTrnscPrtYn() {
		return trnscPrtYn;
	}
	public void setTrnscPrtYn(String trnscPrtYn) {
		this.trnscPrtYn = trnscPrtYn;
	}
	public String getSmrPrtYn() {
		return smrPrtYn;
	}
	public void setSmrPrtYn(String smrPrtYn) {
		this.smrPrtYn = smrPrtYn;
	}
	public String getCarPrtYn() {
		return carPrtYn;
	}
	public void setCarPrtYn(String carPrtYn) {
		this.carPrtYn = carPrtYn;
	}
	public String getStkPrtYn() {
		return stkPrtYn;
	}
	public void setStkPrtYn(String stkPrtYn) {
		this.stkPrtYn = stkPrtYn;
	}
	public String getTrnscPrtId() {
		return trnscPrtId;
	}
	public void setTrnscPrtId(String trnscPrtId) {
		this.trnscPrtId = trnscPrtId;
	}
	public String getTrnscPrtDt() {
		return trnscPrtDt;
	}
	public void setTrnscPrtDt(String trnscPrtDt) {
		this.trnscPrtDt = trnscPrtDt;
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
	public String getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
	}
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
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
	public String getCarCd() {
		return carCd;
	}
	public void setCarCd(String carCd) {
		this.carCd = carCd;
	}
	public String getCarNm() {
		return carNm;
	}
	public void setCarNm(String carNm) {
		this.carNm = carNm;
	}
	
	public String getAccRcvYn() {
		return accRcvYn;
	}
	public void setAccRcvYn(String accRcvYn) {
		this.accRcvYn = accRcvYn;
	}
	public String getSalesClass() {
		return salesClass;
	}
	public void setSalesClass(String salesClass) {
		this.salesClass = salesClass;
	}
	public String getSalesPrCd() {
		return salesPrCd;
	}
	public void setSalesPrCd(String salesPrCd) {
		this.salesPrCd = salesPrCd;
	}
	public String getSalesPrNm() {
		return salesPrNm;
	}
	public void setSalesPrNm(String salesPrNm) {
		this.salesPrNm = salesPrNm;
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
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
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
	public String getSalesSlipNos() {
		return salesSlipNos;
	}
	public void setSalesSlipNos(String salesSlipNos) {
		this.salesSlipNos = salesSlipNos;
	}
	public String getTitle() {
		return title;
	}
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public List<CusSalesDlvVo> getItem() {
		return item;
	}
	public void setItem(List<CusSalesDlvVo> item) {
		this.item = item;
	}
	
	
	
	

}
