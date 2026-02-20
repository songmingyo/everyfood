package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;


/**
 *
 * @Class : SalSalesPrdtPrftListMapper.java
 * @Description : 매출처 품목별이익현황
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      song        	  
 *
 * </pre>
 */

@Repository(value = "SalSalesPrdtPrftListMapper")
public interface SalSalesPrdtPrftListMapper {

	
	/**
	 * @Method : selectSalSalesPrdtPrftList
	 * @Description : 매출처 품목별이익현황 조회 
	 * @param SalSalesPrdtPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesPrdtPrftListVo> selectSalSalesPrdtPrftList(SalSalesPrdtPrftListVo paramVo);
	
	/**
	 * @Method : selectSalSalesCarShipFooter
	 * @Description : 매출처 품목별이익현황 조회 
	 * @param SalSalesPrdtPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public SalSalesPrdtPrftListVo selectSalSalesPrdtPrftFooter(SalSalesPrdtPrftListVo paramVo);
	
	/**
	 * @Method : selectSalSalesPrdtPrftListListExcelDown
	 * @Description : 매출처 품목별이익현황 엑셀 다운로드
	 * @param SalSalesPrdtPrftListVo param
	 * @return List<SalSalesPrdtPrftListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesPrdtPrftListExcelDown(SalSalesPrdtPrftListVo paramVo) throws SQLException;
	
}
