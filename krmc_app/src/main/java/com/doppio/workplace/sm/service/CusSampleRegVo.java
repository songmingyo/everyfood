package com.doppio.workplace.sm.service;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;

import org.springframework.tronic.util.DateUtil;
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

public class CusSampleRegVo  extends Page  implements Serializable {

	private static final long serialVersionUID = 5746453328068624800L;
	
	private List<CusSampleRegVo>  cusSampleRegList;
	
	/** 샘플일자(pk) */
	private String freeDt	 ;
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
	
	/**샘플 구분 */
	private String freeClass;
	/**샘플 구분명 */
	private String freeClassNm;
	
	/**규격*/
	private String prdtStd		;
	/**주문단위*/
	private String ordUnit      ; 
	/**샘플수량*/
	private String freeQty		;
	/**박스수량*/
	private String qtyBox		; 
	/**창고코드*/
	private String whCd			; 
	/**장고명칭*/
	private String whNm 		;  
	/**전표 헤더 비고*/
	private String remarks1		;
	/**전표 아이템 비고*/
	private String remarks2		;
	
	/**부가세유무*/
	private String vatYnNm		;
	/**단가*/
	private String cost		;
	
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
	private String regNm;
	/*수정자명 */
	private String modNm;
	
	/*작업자 아이디 */
	private String workId;
	
	/* 박스 Qty */
	private String boxQty;
	/* 부가세유무 */
	private String vatYn;
	/* 저장형태 */
	private String strgType;
	/* 규격유무 */
	private String stdYn;
	
	
	/* 조회 매출처코드 */
	private String searchSalesCd;	
	/* 조회 매출처명 */
	private String searchSalesNm;
	/* 조회 품목코드 */
	private String searchPrdtCd;
	/* 조회 품목명 */
	private String searchPrdtNm;
	/* 조회 매출처코드 */
	private String searchFreeDt;
	/* 조회 매출처코드 */
	private String searchFreeClass;
	
	
	
	public String getSearchSalesNm() {
		return searchSalesNm;
	}
	public void setSearchSalesNm(String searchSalesNm) {
		this.searchSalesNm = searchSalesNm;
	}
	public String getSearchPrdtNm() {
		return searchPrdtNm;
	}
	public void setSearchPrdtNm(String searchPrdtNm) {
		this.searchPrdtNm = searchPrdtNm;
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
	public String getFreeClassNm() {
		return freeClassNm;
	}
	public void setFreeClassNm(String freeClassNm) {
		this.freeClassNm = freeClassNm;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	public String getVatYnNm() {
		return vatYnNm;
	}
	public void setVatYnNm(String vatYnNm) {
		this.vatYnNm = vatYnNm;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSearchSalesCd() {
		return searchSalesCd;
	}
	public void setSearchSalesCd(String searchSalesCd) {
		this.searchSalesCd = searchSalesCd;
	}
	
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
	}
	public String getSearchFreeDt() {
		return searchFreeDt;
	}
	public void setSearchFreeDt(String searchFreeDt) {
		this.searchFreeDt = searchFreeDt;
	}
	public String getSearchFreeClass() {
		return searchFreeClass;
	}
	public void setSearchFreeClass(String searchFreeClass) {
		this.searchFreeClass = searchFreeClass;
	}
	public String getStrgType() {
		return strgType;
	}
	public void setStrgType(String strgType) {
		this.strgType = strgType;
	}
	
	public List<CusSampleRegVo> getCusSampleRegList() {
		return cusSampleRegList;
	}
	public void setCusSampleRegList(List<CusSampleRegVo> cusSampleRegList) {
		this.cusSampleRegList = cusSampleRegList;
	}
	public String getFreeDt() {
		return freeDt;
	}
	public void setFreeDt(String freeDt) {
		this.freeDt = freeDt;
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
	public String getFreeClass() {
		return freeClass;
	}
	public void setFreeClass(String freeClass) {
		this.freeClass = freeClass;
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
	public String getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(String freeQty) {
		this.freeQty = freeQty;
	}
	public String getQtyBox() {
		return qtyBox;
	}
	public void setQtyBox(String qtyBox) {
		this.qtyBox = qtyBox;
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

	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	

}
