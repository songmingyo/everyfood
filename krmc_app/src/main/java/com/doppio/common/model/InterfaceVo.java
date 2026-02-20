package com.doppio.common.model;

import java.io.Serializable;

/**
 * @author KIM YOUNG HAG
 * @Description :Interface Vo
 * @Class : InterfaceVo
 * 
 * <pre>
 * << 개정이력(Modification Information) >> 
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 
 * </pre>
 * @version : 1.0
 */
public class InterfaceVo implements Serializable {

	private static final long serialVersionUID = -8061637100107203440L;
	
	private String applCd;
	private String extent01;

	public String getApplCd() {
		return applCd;
	}

	public void setApplCd(String applCd) {
		this.applCd = applCd;
	}

	public String getExtent01() {
		return extent01;
	}

	public void setExtent01(String extent01) {
		this.extent01 = extent01;
	}
}
