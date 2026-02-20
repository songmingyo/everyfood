package com.doppio.workplace.bm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description :영업사원관리  vo
 * @Class : EmpMasterVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class EmpMasterVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**영업사원코드*/
	private String salesPrCd;
	/**영업사원명*/
	private String salesPrNm;
	/**직급*/
	private String position;
	/**휴대폰번호*/
	private String mtelNo;
	/**사용유무*/
	private String useYn;
	/**등록자명*/
	private String regNm;
	/**등록자*/
	private String regId;
	/*등록일시*/
	private String regDt;
	/**수정자명*/
	private String modNm;
	/**수정자*/
	private String modId;
	/**수정일시*/
	private String modDt;
	
	/** 조회 영업사원명 **/
	private String workId;
	
	
	/**영업사원코드*/
	private String find_salesPrCd;
	/**영업사원명*/
	private String find_salesPrNm;
	/**직급*/
	private String find_position;


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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMtelNo() {
		return mtelNo;
	}
	public void setMtelNo(String mtelNo) {
		this.mtelNo = mtelNo;
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
	public String getFind_salesPrCd() {
		return find_salesPrCd;
	}
	public void setFind_salesPrCd(String find_salesPrCd) {
		this.find_salesPrCd = find_salesPrCd;
	}
	public String getFind_salesPrNm() {
		return find_salesPrNm;
	}
	public void setFind_salesPrNm(String find_salesPrNm) {
		this.find_salesPrNm = find_salesPrNm;
	}
	public String getFind_position() {
		return find_position;
	}
	public void setFind_position(String find_position) {
		this.find_position = find_position;
	}
	
	

}
