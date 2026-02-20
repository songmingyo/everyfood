package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalPrdtPrftListVo;


/**
 *
 * @Class : SalPrdtPrftListMapper.java
 * @Description : 품목별이익현황
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

@Repository(value = "SalPrdtPrftListMapper")
public interface SalPrdtPrftListMapper {

	
	/**
	 * @Method : selectSalPrdtPrftList
	 * @Description : 품목별이익현황 조회 
	 * @param SalPrdtPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalPrdtPrftListVo> selectSalPrdtPrftList(SalPrdtPrftListVo paramVo);
	
	/**
	 * @Method : selectSalSalesCarShipFooter
	 * @Description : 품목별이익현황 조회 
	 * @param SalPrdtPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public SalPrdtPrftListVo selectSalPrdtPrftFooter(SalPrdtPrftListVo paramVo);
	
	/**
	 * @Method : selectSalPrdtPrftListListExcelDown
	 * @Description : 품목별이익현황 엑셀 다운로드
	 * @param SalPrdtPrftListVo param
	 * @return List<SalPrdtPrftListVo>
	 */                     
	public List<HashMap<String, String>> selectSalPrdtPrftListExcelDown(SalPrdtPrftListVo paramVo) throws SQLException;
	
}
