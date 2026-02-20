package com.doppio.workplace.sm.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * @author j10000
 * @Description : 출고예정재고  vo
 * @Class : BuyStoreVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.31 j10000
 * </pre>
 * @version : 1.0
 */
public class CusSchdlStkListVo extends Page  implements Serializable {


	private static final long serialVersionUID = -6630600692804042954L;

	/**품목명*/
	private String prdtNm;
	/**규격*/
	private String prdtStd;
	/**단뒤*/
	private String ordUnitNm;
	/**전월출고량*/
	private String preSalesQty;
	/**재고수량*/
	private String stkQty;
	/**출고수량*/
	private String salesQty;
	/**잔여수량*/
	private String remStkQty;
	/**재고일수*/
	private String stkDt;
	
	
	/**조회일자*/
	private String salesDt;


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


	public String getPreSalesQty() {
		return preSalesQty;
	}


	public void setPreSalesQty(String preSalesQty) {
		this.preSalesQty = preSalesQty;
	}


	public String getStkQty() {
		return stkQty;
	}


	public void setStkQty(String stkQty) {
		this.stkQty = stkQty;
	}


	public String getSalesQty() {
		return salesQty;
	}


	public void setSalesQty(String salesQty) {
		this.salesQty = salesQty;
	}


	public String getRemStkQty() {
		return remStkQty;
	}


	public void setRemStkQty(String remStkQty) {
		this.remStkQty = remStkQty;
	}


	public String getStkDt() {
		return stkDt;
	}


	public void setStkDt(String stkDt) {
		this.stkDt = stkDt;
	}


	public String getSalesDt() {
		return salesDt;
	}


	public void setSalesDt(String salesDt) {
		this.salesDt = salesDt;
	}
	
	
	
	

}
