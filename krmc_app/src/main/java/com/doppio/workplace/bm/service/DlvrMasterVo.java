package com.doppio.workplace.bm.service;

import java.io.Serializable;

import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Page;

/**
 * @author dada
 * @Description :차량관리  vo
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
public class DlvrMasterVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**배송차코드*/
	private String carCd;
	/**배송차명*/
	private String carNm;
	/**배송차번호*/
	private String carNum;
	/**배송기사*/
	private String drvSnm;
	/**휴대폰번호*/
	private String mtelNo;
	/**차량유형*/
	private String carType;
	/**시작일자(운영)*/
	private String startDt;
	/**종료일자(운영)*/
	private String endDt;
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
	
	
	/*매출처코드*/
	private String salesCd;
	/*매출처명 */
	private String salesNm;
	
	/*차량검색 조건 (공통)*/
	/*차량코드*/
	private String find_carCd;
	/*차량명 */
	private String find_carNm;
	/*차량번호(호차)  */
	private String find_carNum;
	/*배송기사 */
	private String find_drvSnm;
	/*매출처명*/
	private String find_salesNm;
	/*매출처코드*/
	private String find_salesCd;
	
	
	
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
	public String getCarNum() {
		return carNum;
	}
	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}
	public String getDrvSnm() {
		return drvSnm;
	}
	public void setDrvSnm(String drvSnm) {
		this.drvSnm = drvSnm;
	}
	public String getMtelNo() {
		return mtelNo;
	}
	public void setMtelNo(String mtelNo) {
		this.mtelNo = mtelNo;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
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
	public String getModUserNm() {
		return modUserNm;
	}
	public void setModUserNm(String modUserNm) {
		this.modUserNm = modUserNm;
	}
	
	
	public String getRegUserId() {
		return regUserId;
	}
	public void setRegUserId(String regUserId) {
		this.regUserId = regUserId;
	}
	public String getModUserId() {
		return modUserId;
	}
	public void setModUserId(String modUserId) {
		this.modUserId = modUserId;
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
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	

	public String getFind_carCd() {
		return find_carCd;
	}
	public void setFind_carCd(String find_carCd) {
		this.find_carCd = find_carCd;
	}
	public String getFind_carNm() {
		return find_carNm;
	}
	public void setFind_carNm(String find_carNm) {
		this.find_carNm = find_carNm;
	}
	
	public String getFind_carNum() {
		return find_carNum;
	}
	public void setFind_carNum(String find_carNum) {
		this.find_carNum = find_carNum;
	}
	public String getFind_drvSnm() {
		return find_drvSnm;
	}
	public void setFind_drvSnm(String find_drvSnm) {
		this.find_drvSnm = find_drvSnm;
	}
	public String getFind_salesNm() {
		return find_salesNm;
	}
	public void setFind_salesNm(String find_salesNm) {
		this.find_salesNm = find_salesNm;
	}
	public String getFind_salesCd() {
		return find_salesCd;
	}
	public void setFind_salesCd(String find_salesCd) {
		this.find_salesCd = find_salesCd;
	}
	/*등록자 이름(개정코드) */
	public String getRegUserInfo() {
		
		String  regUserInfo =  "";
		
		if(StringUtil.isNotEmpty(this.regUserNm) && StringUtil.isNotEmpty(this.regUserNm)) {
			  regUserInfo =   this.regUserNm+"("+this.regId+")";
		}else regUserInfo =    StringUtil.nvl(this.regUserNm,"")+StringUtil.nvl(this.regId,""); 
		
		return regUserInfo;
	}
	/*수정자 이름(개정코드) */
	public String getModUserInfo() {
		String modUserInfo = "";
		
		if(StringUtil.isNotEmpty(this.modUserNm) && StringUtil.isNotEmpty(this.modUserNm)) {
			  modUserInfo =   this.modUserNm+"("+this.regId+")";
		}else modUserInfo =    StringUtil.nvl(this.modUserNm,"")+StringUtil.nvl(getModId(),""); 
		
		return modUserInfo;
	}

	public String getStartDtFmt() {
		return DateUtil.convertDateFmt(this.startDt, "", "");
	}
	
	
	public String getEndDtFmt() {
		return DateUtil.convertDateFmt(this.endDt, "", "");
	}
	
	
}
