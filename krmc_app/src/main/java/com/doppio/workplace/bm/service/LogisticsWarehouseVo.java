package com.doppio.workplace.bm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author Song
 * @Description : 물류  vo
 * @Class : MgrGroupVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class LogisticsWarehouseVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**창고코드*/
	private String whCd;
	/**창고명*/
	private String whNm;
	/**우편번호*/
	private String zipCd;
	/**주소*/
	private String zipAddr;
	/**주소상세*/
	private String dtlAddr;
	/**사용유무*/
	private String useYn;
	/**등록자*/
	private String regNm;
	/**등록자*/
	private String regId;
	/*등록일시*/
	private String regDt;
	/**수정자*/
	private String modNm;
	/**수정자*/
	private String modId;
	/**수정일시*/
	private String modDt;
	
	/** 조회 창고명 **/
	private String searchWhNm;
	
	
	
	
	public String getSearchWhNm() {
		return searchWhNm;
	}
	public void setSearchWhNm(String searchWhNm) {
		this.searchWhNm = searchWhNm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
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
	public String getZipCd() {
		return zipCd;
	}
	public void setZipCd(String zipCd) {
		this.zipCd = zipCd;
	}
	public String getZipAddr() {
		return zipAddr;
	}
	public void setZipAddr(String zipAddr) {
		this.zipAddr = zipAddr;
	}
	public String getDtlAddr() {
		return dtlAddr;
	}
	public void setDtlAddr(String dtlAddr) {
		this.dtlAddr = dtlAddr;
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
	
	
	
	
	
	

}
