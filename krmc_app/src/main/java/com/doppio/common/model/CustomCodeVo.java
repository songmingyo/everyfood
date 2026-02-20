package com.doppio.common.model;

import java.io.Serializable;


/**
 * @author khs
 * @Description : 
 * @Class : CustomCodeVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2012.06.07		khs		최초등록
 * </pre>
 * @version : 1.0
 */
public class CustomCodeVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7659065087080344690L;
	
	
	/* 키값 */
	private String keyValue;
	/* Parent 코드 */
	private String parentCd;
	/* 년도 */
	private String type;
	/* Text */
	private String text;
	/* Value */
	private String value;
	/* Sub value */
	private String subValue;
	private String subValue2;
	
	/* 회사코드 */
	private String compCd;
	private String masterId;
	
	/* 키값 */
	private String defaultValue;
	private String tmpType;
	private String subCode;
	private String subCode2;
	
	/* 서브코드 조회용 master parentCd */
	private String masterParentCd;
	/* 서브코드 조회용 master dtlCd */
	private String masterDtlCd;
	
	
	
	
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSubCode2() {
		return subCode2;
	}
	public void setSubCode2(String subCode2) {
		this.subCode2 = subCode2;
	}
	public String getSubValue() {
		return subValue;
	}
	public void setSubValue(String subValue) {
		this.subValue = subValue;
	}
	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public String getParentCd() {
		return parentCd;
	}
	public void setParentCd(String parentCd) {
		this.parentCd = parentCd;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getTmpType() {
		return tmpType;
	}
	public void setTmpType(String tmpType) {
		this.tmpType = tmpType;
	}
	public String getSubValue2() {
		return subValue2;
	}
	public void setSubValue2(String subValue2) {
		this.subValue2 = subValue2;
	}
	public String getMasterParentCd() {
		return masterParentCd;
	}
	public void setMasterParentCd(String masterParentCd) {
		this.masterParentCd = masterParentCd;
	}
	public String getMasterDtlCd() {
		return masterDtlCd;
	}
	public void setMasterDtlCd(String masterDtlCd) {
		this.masterDtlCd = masterDtlCd;
	}
	
}
