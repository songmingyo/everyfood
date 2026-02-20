package com.doppio.workplace.cs.service;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;
import java.util.List;

/**
 * @author Song
 * @Description : 매입처 발주 관리  vo
 * @Class : BuyOrderRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class LogWhMvRegVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;
	
	private List<LogWhMvRegVo>  logWhMvRegList;

	/** 이동일자 */
	private String mvDt;
	/** 품목코드 */
	private String prdtCd;
	/** 품목코드 */
	private String prdtNm;
	/** 품목코드 */
	private String prdtStd;
	/** 품목코드 */
	private String ordUnitNm;
	/** 출고창고 */
	private String outWhCd;
	/** 출고창고명 */
	private String outWhNm;
	/** 입고창고 */
	private String inWhCd;
	/** 입고창고명 */
	private String inWhNm;
	/** 이동수량 */
	private String mvQty;
	/** 이동수량 */
	private String mvQtyOrg;
	/** 확정유무 */
	private String mvConfYn;
	/** 소비기한 */
	private String termVal;
	/** 이고사유 */
	private String remarks;
	/** 사용유무 */
	private String useYn;
	
	
	/** 이동일자 */
	private String searchMvDt;
	/** 품목 대분류 */
	private String searchLCd;
	/** 품목 중분류 */
	private String searchMCd;
	/** 품목코드 */
	private String searchPrdtCd;
	/** 품목명 */
	private String searchPrdtNm;
	/** 출고창고 */
	private String searchOutWhCd;
	/** 입고창고 */
	private String searchInWhCd;
	
	
	/** 그리드 상태 */
	private String gridFlag;
	/** 작업자 */
	private String workId;
	
	
	
	public String getMvQtyOrg() {
		return mvQtyOrg;
	}
	public void setMvQtyOrg(String mvQtyOrg) {
		this.mvQtyOrg = mvQtyOrg;
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
	public String getOrdUnitNm() {
		return ordUnitNm;
	}
	public void setOrdUnitNm(String ordUnitNm) {
		this.ordUnitNm = ordUnitNm;
	}
	public String getOutWhNm() {
		return outWhNm;
	}
	public void setOutWhNm(String outWhNm) {
		this.outWhNm = outWhNm;
	}
	public String getInWhNm() {
		return inWhNm;
	}
	public void setInWhNm(String inWhNm) {
		this.inWhNm = inWhNm;
	}
	public String getSearchPrdtNm() {
		return searchPrdtNm;
	}
	public void setSearchPrdtNm(String searchPrdtNm) {
		this.searchPrdtNm = searchPrdtNm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSearchOutWhCd() {
		return searchOutWhCd;
	}
	public void setSearchOutWhCd(String searchOutWhCd) {
		this.searchOutWhCd = searchOutWhCd;
	}
	public String getSearchInWhCd() {
		return searchInWhCd;
	}
	public void setSearchInWhCd(String searchInWhCd) {
		this.searchInWhCd = searchInWhCd;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getGridFlag() {
		return gridFlag;
	}
	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}
	public List<LogWhMvRegVo> getLogWhMvRegList() {
		return logWhMvRegList;
	}
	public void setLogWhMvRegList(List<LogWhMvRegVo> logWhMvRegList) {
		this.logWhMvRegList = logWhMvRegList;
	}
	public String getMvDt() {
		return mvDt;
	}
	public void setMvDt(String mvDt) {
		this.mvDt = mvDt;
	}
	public String getPrdtCd() {
		return prdtCd;
	}
	public void setPrdtCd(String prdtCd) {
		this.prdtCd = prdtCd;
	}
	public String getOutWhCd() {
		return outWhCd;
	}
	public void setOutWhCd(String outWhCd) {
		this.outWhCd = outWhCd;
	}
	public String getInWhCd() {
		return inWhCd;
	}
	public void setInWhCd(String inWhCd) {
		this.inWhCd = inWhCd;
	}
	public String getMvQty() {
		return mvQty;
	}
	public void setMvQty(String mvQty) {
		this.mvQty = mvQty;
	}
	public String getMvConfYn() {
		return mvConfYn;
	}
	public void setMvConfYn(String mvConfYn) {
		this.mvConfYn = mvConfYn;
	}
	public String getTermVal() {
		return termVal;
	}
	public void setTermVal(String termVal) {
		this.termVal = termVal;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSearchMvDt() {
		return searchMvDt;
	}
	public void setSearchMvDt(String searchMvDt) {
		this.searchMvDt = searchMvDt;
	}
	public String getSearchLCd() {
		return searchLCd;
	}
	public void setSearchLCd(String searchLCd) {
		this.searchLCd = searchLCd;
	}
	public String getSearchMCd() {
		return searchMCd;
	}
	public void setSearchMCd(String searchMCd) {
		this.searchMCd = searchMCd;
	}
	public String getSearchPrdtCd() {
		return searchPrdtCd;
	}
	public void setSearchPrdtCd(String searchPrdtCd) {
		this.searchPrdtCd = searchPrdtCd;
	}
	
	
	
}
