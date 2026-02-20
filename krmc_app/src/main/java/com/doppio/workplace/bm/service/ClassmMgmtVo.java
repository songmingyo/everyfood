package com.doppio.workplace.bm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description :영업사원관리  vo
 * @Class : ClassmMgmtVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class ClassmMgmtVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**중분류코드*/
	private String mCd;
	/**중분류명*/
	private String mNm;

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

}
