package com.doppio.workplace.sm.service;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;

public class CusSalesStkVo extends Page {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2914981057653930736L;
	
	/** 재고일자		*/
	private String stkDt 		;	
	/** 상품코드		*/
  	private String prdtCd 		;	
  	/** 상품명 		*/
  	private String prdtNm		;	
  	/** 창고코드		*/
  	private String whCd 		;	
  	/** 창고명 		*/
  	private String whNm 		;	
  	/** 1구역 재고	 	*/
  	private String areaQty1		;	
  	/** 2구역 재고	 	*/
  	private String areaQty2		;	
  	/** 합계 재고		*/
  	private String carryOvQty	;	
  	/** 단가 			*/
  	private String cost			;	
  	/** 규격			*/
  	private String prdtStd		;	
  	/** 유통기한		*/
  	private String exprtDt		;	
  	/** 대분류 코드	*/
  	private String lCd 			;	
  	/** 대분류 명칭	*/
	private String lNm 			;	
	/** 중분류 코드 	*/
	private String mCd 			;	
	/** 중분류 명칭 	*/
	private String mNm   		;	
	/** 매입가 		*/
	private String buyPrice		;	
	/** 매출가		*/
	private String salPrice 	;
	/*박스당수량 */
	private String qtyBox		; 
	/*단가 시작일 */
	private String costStartDt	; 
	
	/*저장형태 (M001)*/
	private String strgType		;
	private String[] strgTypes  ; 
	/*매입처코드 */
	private String buyCd		;
   	
	
	/** 재고일자 From 		*/
	private String stkDtFrDy 		;	
	/** 재고일자 To 		*/
	private String stkDtToDy 		;	
	
	/** 재고수량  From 		*/
	private String carryOvQtyFr;
	
	/** 재고수량  To 		*/
	private String carryOvQtyTo;
	
 	/** 저장형태명칭  */
	private String strgTypeNm 	;
   	/** 매입처코드  */
	/** 매입처명칭 */
	private String buyNm 		;	
	
	/*주문단위 코드 */
	private String ordUnit ;
	/*주문단위 명칭 */
	private String  ordUnitNm ;
   	
	
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
	
	/*하남 재고 */
	private String haStkQty;
	/*여주 재고 */
	private String yeoStkQty;
	
	
	
	public String getHaStkQty() {
		return haStkQty;
	}
	public void setHaStkQty(String haStkQty) {
		this.haStkQty = haStkQty;
	}
	public String getYeoStkQty() {
		return yeoStkQty;
	}
	public void setYeoStkQty(String yeoStkQty) {
		this.yeoStkQty = yeoStkQty;
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
	public String getPrdtNm() {
		return prdtNm;
	}
	public void setPrdtNm(String prdtNm) {
		this.prdtNm = prdtNm;
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
	public String getAreaQty1() {
		return areaQty1;
	}
	public void setAreaQty1(String areaQty1) {
		this.areaQty1 = areaQty1;
	}
	public String getAreaQty2() {
		return areaQty2;
	}
	public void setAreaQty2(String areaQty2) {
		this.areaQty2 = areaQty2;
	}
	public String getCarryOvQty() {
		return carryOvQty;
	}
	public void setCarryOvQty(String carryOvQty) {
		this.carryOvQty = carryOvQty;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getPrdtStd() {
		return prdtStd;
	}
	public void setPrdtStd(String prdtStd) {
		this.prdtStd = prdtStd;
	}
	public String getExprtDt() {
		return exprtDt;
	}
	public void setExprtDt(String exprtDt) {
		this.exprtDt = exprtDt;
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
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getSalPrice() {
		return salPrice;
	}
	public void setSalPrice(String salPrice) {
		this.salPrice = salPrice;
	}
	
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
	}
	public String getCostStartDt() {
		return costStartDt;
	}
	public void setCostStartDt(String costStartDt) {
		this.costStartDt = costStartDt;
	}
	public String getStrgType() {
		return strgType;
	}
	public void setStrgType(String strgType) {
		this.strgType = strgType;
	}
	
	public String[] getStrgTypes() {
		return strgTypes;
	}
	public void setStrgTypes(String[] strgTypes) {
		this.strgTypes = strgTypes;
	}
	public String getBuyCd() {
		return buyCd;
	}
	public void setBuyCd(String buyCd) {
		this.buyCd = buyCd;
	}
	public String getStkDtFrDy() {
		return stkDtFrDy;
	}
	public void setStkDtFrDy(String stkDtFrDy) {
		this.stkDtFrDy = stkDtFrDy;
	}
	public String getStkDtToDy() {
		return stkDtToDy;
	}
	public void setStkDtToDy(String stkDtToDy) {
		this.stkDtToDy = stkDtToDy;
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
	
	
	public String getCarryOvQtyFr() {
		return carryOvQtyFr;
	}
	public void setCarryOvQtyFr(String carryOvQtyFr) {
		this.carryOvQtyFr = carryOvQtyFr;
	}
	public String getCarryOvQtyTo() {
		return carryOvQtyTo;
	}
	public void setCarryOvQtyTo(String carryOvQtyTo) {
		this.carryOvQtyTo = carryOvQtyTo;
	}
	public String getStkDtLocale() {
		return DateUtil.convertDateLocale(stkDt,"yyyyMMdd","yyyy-MM-dd");
	}
	
	public String getCostStartDtLocale() {
		return DateUtil.convertDateLocale(costStartDt,"yyyyMMdd","yy-MM-dd");
	}
	public String getStrgTypeNm() {
		return strgTypeNm;
	}
	public void setStrgTypeNm(String strgTypeNm) {
		this.strgTypeNm = strgTypeNm;
	}
	public String getBuyNm() {
		return buyNm;
	}
	public void setBuyNm(String buyNm) {
		this.buyNm = buyNm;
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
	
	 	
	

}
