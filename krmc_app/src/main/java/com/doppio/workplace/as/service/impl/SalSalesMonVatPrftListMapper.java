package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesMonVatPrftListVo;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;


/**
 *
 * @Class : SalSalesMonVatPrftListMapper.java
 * @Description : 매출처 월별이익현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "SalSalesMonVatPrftListMapper")
public interface SalSalesMonVatPrftListMapper {

	
	/**
	 * @Method : selectSalSalesMonVatPrftList
	 * @Description : 매출처 월별이익현황 조회 
	 * @param SalSalesMonVatPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesMonVatPrftListVo> selectSalSalesMonVatPrftList(SalSalesMonVatPrftListVo paramVo);
	
	/**
	 * @Method : selectSalSalesMonVatPrftFooter
	 * @Description : 매출처 월별이익현황 footer
	 * @param SalSalesMonVatPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public SalSalesMonVatPrftListVo selectSalSalesMonVatPrftFooter(SalSalesMonVatPrftListVo paramVo);
	
	/**
	 * @Method : selectSalSalesMonVatPrftListListExcelDown
	 * @Description : 매출처 월별이익현황 엑셀 다운로드
	 * @param SalSalesMonVatPrftListVo param
	 * @return List<SalSalesMonVatPrftListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesMonVatPrftListExcelDown(SalSalesMonVatPrftListVo paramVo) throws SQLException;
	
}
