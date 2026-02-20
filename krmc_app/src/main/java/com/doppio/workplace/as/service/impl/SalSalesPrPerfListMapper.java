package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesPrPerfListVo;


/**
 *
 * @Class : SalSalesPrPerfListMapper.java
 * @Description : 영업 실적 현황
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

@Repository(value = "SalSalesPrPerfListMapper")
public interface SalSalesPrPerfListMapper {

	/**
	 * @Method : selectSalSalesPrPerfList
	 * @Description : 영업 실적 현황 조회 
	 * @param SalSalesPrPerfListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesPrPerfListVo> selectSalSalesPrPerfList(SalSalesPrPerfListVo paramVo);
	
	/**
	 * @Method : selectSalSalesPrPerfListListExcelDown
	 * @Description : 영업 실적 현황 엑셀 다운로드
	 * @param SalSalesPrPerfListVo param
	 * @return List<SalSalesPrPerfListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesPrPerfListExcelDown(SalSalesPrPerfListVo paramVo) throws SQLException;
	
}
