package com.doppio.workplace.as.service;

import java.io.Serializable;
import java.util.List;

import com.doppio.common.model.Page;

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
public class AccSalesFirmbankingListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;
	
	private List<AccSalesFirmbankingListVo>  accSalesFirmbankingRegList;


	/**입금자명*/
	private String custName;
	/**입금일자*/
	private String dealDate;
	/**입금시간*/
	private String dealTime;
	/**가상계좌*/
	private String vrAcctNo;
	/**입금금액*/
	private String totalAmt;
	/**작업자*/
	private String workId;
	/**적용여부*/
	private String procFlag;
	/**적용여부*/
	private String procFlagNm;
	
	/**시작일*/
	private String startDt;
	/**종료일*/
	private String endDt;
	
	
	
	public String getProcFlagNm() {
		return procFlagNm;
	}
	public void setProcFlagNm(String procFlagNm) {
		this.procFlagNm = procFlagNm;
	}
	public String getProcFlag() {
		return procFlag;
	}
	public void setProcFlag(String procFlag) {
		this.procFlag = procFlag;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public List<AccSalesFirmbankingListVo> getAccSalesFirmbankingRegList() {
		return accSalesFirmbankingRegList;
	}
	public void setAccSalesFirmbankingRegList(List<AccSalesFirmbankingListVo> accSalesFirmbankingRegList) {
		this.accSalesFirmbankingRegList = accSalesFirmbankingRegList;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
	public String getDealTime() {
		return dealTime;
	}
	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}
	public String getVrAcctNo() {
		return vrAcctNo;
	}
	public void setVrAcctNo(String vrAcctNo) {
		this.vrAcctNo = vrAcctNo;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
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
	
	
		
	
}
