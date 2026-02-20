package com.doppio.workplace.sm.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

/**
 * @author dada
 * @Description : 매입처 발주관리  vo
 * @Class : CusOrdRegVo 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.04.12 dada
 * </pre>
 * @version : 1.0
 */
public class CusOrdRegVo extends Page  implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8797484923598645834L;

	
//	private List<Map<String, String>> orderRegList;	
	
	private List<CusOrdRegVo> orderRegList;	
	
	/** 품목코드 */
	private String prdtCd  		;
	/** 품목명*/
	private String prdtNm  		;
	/** 대분류 코드*/
	private String lCd 			;
	/** 대분류 명칭*/
	private String lNm 			;
	/** 중분류 코드 */
	private String mCd 			;
	/** 중분류 명칭 */
	private String mNm   		;	
	/** 규격*/
	private String prdtStd 		;
	/** 주문단위코드 */
	private String ordUnit 		;
	/** 주문단위명칭 */
	private String ordUnitNm   ; 
	/** 박스당수량*/
	private String qtyBox  		;
	/**원산지명 */
	private String originNm ; 
	/** 시작일자*/
	private String startDt  	;	
	/** 종료일자*/
	private String endDt	  	;	
	/** 부가세 여부*/
	private String vatYn	  	;
	/** 부가세 여부*/
	private String vatYnNm	  	;
	/** 판매단가*/
	private String price	  	;	
	/** 매출처 코드*/
	private String salesCd 		;
	/** 매출처 명*/
	private String salesNm 		;
	/** 규격유무*/
	private String stdYn 		;
	/** 저장형태*/
	private String strgTypeNm	;
	/** 매입전표번호*/
	private String salesSlipNo ;
	/** 순번*/
	private String salesSeq		;
	/** 발주낱개수량*/
	private String ordQty		;	
	/** 발주낱개수량 BEFORE*/
	private String ordQtyBef	;	
	/** 발주캐스당수량*/
	private String boxQty		;
	/** 공급가*/
	private String pureAmt 		;
	/** 부가세*/
	private String vatAmt  	;
	/** 총금액*/
	private String totAmt 	;
	/** 창고코드*/
	private String whCd 	;
	/** 창고명*/
	private String whNm 	;
	
	/**발주일자*/
	private String ordDt ; 
	/**납품일자 */
	private String dlvDt ;
	
	/*발주 아이템 수 */
	private String ordItemCount;
	

	/**사용유무*/
	private String useYn;
	/**등록자*/
	private String regId;
	/*등록일시*/
	private String regDt;
	/**수정자*/
	private String modId;
	/**수정일시*/
	private String modDt;
	/*등록자명 */
	private String regUserNm;
	/*등록자 아이디 */
	private String regUserId;
	/*수정자명 */
	private String modUserNm;
	/*수정자 아이디 */
	private String modUserId;
	
	/*작업자 아이디 */
	private String workId;
	
	/*발주일자 From*/
	private String ordDtFrDy;
	/*발주일자 To*/
	private String ordDtToDy;
	/*납품일자 From*/
	private String dlvDtFrDy;
	/*납품일자 to*/
	private String dlvDtToDy;
	
	/*비고 1*/
	private String remarks1;
	/*비고 2*/
	private String remarks2;
	
	
	
	
	public String getWhNm() {
		return whNm;
	}

	public void setWhNm(String whNm) {
		this.whNm = whNm;
	}

	public String getWhCd() {
		return whCd;
	}

	public void setWhCd(String whCd) {
		this.whCd = whCd;
	}

	public String getStrgTypeNm() {
		return strgTypeNm;
	}

	public void setStrgTypeNm(String strgTypeNm) {
		this.strgTypeNm = strgTypeNm;
	}

	public String getVatYnNm() {
		return vatYnNm;
	}

	public void setVatYnNm(String vatYnNm) {
		this.vatYnNm = vatYnNm;
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

	public String getStdYn() {
		return stdYn;
	}

	public void setStdYn(String stdYn) {
		this.stdYn = stdYn;
	}

	public String getOrdItemCount() {
		return ordItemCount;
	}

	public void setOrdItemCount(String ordItemCount) {
		this.ordItemCount = ordItemCount;
	}


	/*마감유무 */
	private String closeYn;
	
	
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

	public String getlNm() {
		return lNm;
	}

	public void setlNm(String lNm) {
		this.lNm = lNm;
	}

	public String getmCd() {
		return mCd;
	}

	public void setmCd(String mCd) {
		this.mCd = mCd;
	}

	public String getmNm() {
		return mNm;
	}

	public void setmNm(String mNm) {
		this.mNm = mNm;
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
	
	public String getOrdUnitNm() {
		return ordUnitNm;
	}

	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}

	public String getQtyBox() {
		return qtyBox;
	}

	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}
	
	public String getOriginNm() {
		return originNm;
	}

	public void setOriginNm(String originNm) {
		this.originNm = originNm;
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

	public String getVatYn() {
		return vatYn;
	}

	public void setVatYn(String vatYn) {
		this.vatYn = vatYn;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getOrdQty() {
		return ordQty;
	}

	public String getOrdQtyBef() {
		return ordQtyBef;
	}

	public void setOrdQtyBef(String ordQtyBef) {
		this.ordQtyBef = ordQtyBef;
	}

	public void setOrdQty(String ordQty) {
		this.ordQty = ordQty;
	}

	

	public String getBoxQty() {
		return boxQty;
	}

	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
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
	
	public String getOrdDt() {
		return ordDt;
	}

	public void setOrdDt(String ordDt) {
		this.ordDt = ordDt;
	}
	
	public String getDlvDt() {
		return dlvDt;
	}

	public void setDlvDt(String dlvDt) {
		this.dlvDt = dlvDt;
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

	public String getRegUserNm() {
		return regUserNm;
	}

	public void setRegUserNm(String regUserNm) {
		this.regUserNm = regUserNm;
	}

	public String getRegUserId() {
		return regUserId;
	}

	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}

	public String getModUserNm() {
		return modUserNm;
	}

	public void setModUserNm(String modUserNm) {
		this.modUserNm = modUserNm;
	}

	public String getModUserId() {
		return modUserId;
	}

	public void setModUserId(String modUserId) {
		this.modUserId = modUserId;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public List<CusOrdRegVo> getOrderRegList() {
		return orderRegList;
	}

	public void setOrderRegList(List<CusOrdRegVo> orderRegList) {
		this.orderRegList = orderRegList;
	}

	public String getOrdDtFrDy() {
		return ordDtFrDy;
	}

	public void setOrdDtFrDy(String ordDtFrDy) {
		this.ordDtFrDy = ordDtFrDy;
	}

	public String getOrdDtToDy() {
		return ordDtToDy;
	}

	public void setOrdDtToDy(String ordDtToDy) {
		this.ordDtToDy = ordDtToDy;
	}

	public String getDlvDtFrDy() {
		return dlvDtFrDy;
	}

	public void setDlvDtFrDy(String dlvDtFrDy) {
		this.dlvDtFrDy = dlvDtFrDy;
	}

	public String getDlvDtToDy() {
		return dlvDtToDy;
	}

	public void setDlvDtToDy(String dlvDtToDy) {
		this.dlvDtToDy = dlvDtToDy;
	}

	public String getCloseYn() {
		return closeYn;
	}

	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}
	
	
	public String getOrdDtLocale(){
		return DateUtil.convertDateLocale(ordDt,"yyyyMMdd","yyyy-MM-dd");
	}
	
	
	public String getDlvDtLocale(){
		return DateUtil.convertDateLocale(dlvDt,"yyyyMMdd","yyyy-MM-dd");
	}
	
	
	
	
	
	
	 

}
