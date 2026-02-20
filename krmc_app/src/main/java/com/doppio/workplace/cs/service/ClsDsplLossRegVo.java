package com.doppio.workplace.cs.service;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;

import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;
import com.doppio.common.model.Page;


/**
 * @author song
 * @Description :폐기/로스등록
 * @Class : ClsDsplLossRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  23.04.08   song       
 * </pre>
 * @version : 1.0
 */

public class ClsDsplLossRegVo  extends Page  implements Serializable {

	private static final long serialVersionUID = 5746453328068624800L;
	
	private List<ClsDsplLossRegVo>  clsDsplLossRegList;
	
	/** 폐기 일자 */
	private String dspDt	 ;
	/** 전표번호(pk) */
	private String buySlipNo    ;
	/** 전표순번(pk) */
	private String buySeq    ;
	/** 상품코드(pk) */
	private String prdtCd	 ;
	
	/**상품명 */
	private String prdtNm	 ;
	
	/** 폐기 구분 */
	private String dspClass;
	/**샘플 구분명 */
	private String dspClassNm;
	
	/**규격*/
	private String prdtStd		;
	/**주문단위*/
	private String ordUnit      ; 
	/**폐기수량*/
	private String dspQty		;
	/**창고코드*/
	private String whCd			; 
	/**장고명칭*/
	private String whNm 		;  
	
	/**단가*/
	private String cost		;
	/**폐기금액*/
	private String dspAmt	;
	/**원폐기금액*/
	private String dspOrgAmt	;
	
	
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
	/*비고 */
	private String remarks;
	/*비고 */
	private String orgRemarks;
	
	/*작업자 아이디 */
	private String workId;
	
	/* 박스 Qty */
	private String boxQty;
	/* 저장형태 */
	private String strgType;
	
	/** 그리드 플래그 */
	private String gridFlag;
	
	
	/* 조회 품목코드 */
	private String searchPrdtCd;
	/* 조회 일자 */
	private String searchDspDt;
	/* 조회 구분코드 */
	private String searchDspClass;
	/* 조회 수량  */
	private String searchDspQty;
	
	
	
	
	
	public String getOrgRemarks() {
		return orgRemarks;
	}
	public void setOrgRemarks(String orgRemarks) {
		this.orgRemarks = orgRemarks;
	}
	public String getDspOrgAmt() {
		return dspOrgAmt;
	}
	public void setDspOrgAmt(String dspOrgAmt) {
		this.dspOrgAmt = dspOrgAmt;
	}
	public String getSearchDspQty() {
		return searchDspQty;
	}
	public void setSearchDspQty(String searchDspQty) {
		this.searchDspQty = searchDspQty;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	public List<ClsDsplLossRegVo> getClsDsplLossRegList() {
		return clsDsplLossRegList;
	}
	public void setClsDsplLossRegList(List<ClsDsplLossRegVo> clsDsplLossRegList) {
		this.clsDsplLossRegList = clsDsplLossRegList;
	}
	public String getDspDt() {
		return dspDt;
	}
	public void setDspDt(String dspDt) {
		this.dspDt = dspDt;
	}
	public String getBuySlipNo() {
		return buySlipNo;
	}
	public void setBuySlipNo(String buySlipNo) {
		this.buySlipNo = buySlipNo;
	}
	public String getBuySeq() {
		return buySeq;
	}
	public void setBuySeq(String buySeq) {
		this.buySeq = buySeq;
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
	public String getDspClass() {
		return dspClass;
	}
	public void setDspClass(String dspClass) {
		this.dspClass = dspClass;
	}
	public String getDspClassNm() {
		return dspClassNm;
	}
	public void setDspClassNm(String dspClassNm) {
		this.dspClassNm = dspClassNm;
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
	public String getDspQty() {
		return dspQty;
	}
	public void setDspQty(String dspQty) {
		this.dspQty = dspQty;
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
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getDspAmt() {
		return dspAmt;
	}
	public void setDspAmt(String dspAmt) {
		this.dspAmt = dspAmt;
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
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getBoxQty() {
		return boxQty;
	}
	public void setBoxQty(String boxQty) {
		this.boxQty = boxQty;
	}
	public String getStrgType() {
		return strgType;
	}
	public void setStrgType(String strgType) {
		this.strgType = strgType;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
	}
	public String getSearchDspDt() {
		return searchDspDt;
	}
	public void setSearchDspDt(String searchDspDt) {
		this.searchDspDt = searchDspDt;
	}
	public String getSearchDspClass() {
		return searchDspClass;
	}
	public void setSearchDspClass(String searchDspClass) {
		this.searchDspClass = searchDspClass;
	}
	
	
	

}
