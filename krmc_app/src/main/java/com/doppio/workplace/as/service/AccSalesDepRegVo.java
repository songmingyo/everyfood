package com.doppio.workplace.as.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Page;
import com.doppio.workplace.cs.service.ClsDsplLossRegVo;

/**
 * @author j10000
 * @Description : 매출처별입금현황  vo
 * @Class : BuyStoreVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.05.14 j10000
 * </pre>
 * @version : 1.0
 */
public class AccSalesDepRegVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;
	
	private List<AccSalesDepRegVo>  accSalesDepRegList;

	/**입금일자*/
	private String depDt;
	/**매출처코드*/
	private String salesCd;
	/**매출처명*/
	private String salesNm;
	/**지급구분*/
	private String setlClass;
	/**지급구분명*/
	private String setlClassNm;
	/**지급금액*/
	private String setlAmt;
	/**어음발행일*/
	private String promDt; 
	/**어음발행일*/
	private String remarks; 
	/**어음만기일자*/
	private String promExpDt;
	/**사용유무*/
	private String useYn;
	/**등록자*/
	private String regNm; 
	/**수정자*/
	private String modNm;
	/**등록일자*/
	private String regDt;
	/**수정일자*/
	private String modDt;
	
	/**작업자*/
	private String workId;
	/**그리드상태*/
	private String gridFlag;
	/**건수*/
	private String rowCount;
	
	
	
	public String getRowCount() {
		return rowCount;
	}

	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}

	public String getGridFlag() {
		return gridFlag;
	}

	public void setGridFlag(String gridFlag) {
		this.gridFlag = gridFlag;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public List<AccSalesDepRegVo> getAccSalesDepRegList() {
		return accSalesDepRegList;
	}

	public void setAccSalesDepRegList(List<AccSalesDepRegVo> accSalesDepRegList) {
		this.accSalesDepRegList = accSalesDepRegList;
	}

	public String getDepDt() {
		return depDt;
	}

	public void setDepDt(String depDt) {
		this.depDt = depDt;
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

	public String getSetlClass() {
		return setlClass;
	}

	public void setSetlClass(String setlClass) {
		this.setlClass = setlClass;
	}

	public String getSetlClassNm() {
		return setlClassNm;
	}

	public void setSetlClassNm(String setlClassNm) {
		this.setlClassNm = setlClassNm;
	}

	public String getSetlAmt() {
		return setlAmt;
	}

	public void setSetlAmt(String setlAmt) {
		this.setlAmt = setlAmt;
	}

	public String getPromDt() {
		return promDt;
	}

	public void setPromDt(String promDt) {
		this.promDt = promDt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPromExpDt() {
		return promExpDt;
	}

	public void setPromExpDt(String promExpDt) {
		this.promExpDt = promExpDt;
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

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

}
