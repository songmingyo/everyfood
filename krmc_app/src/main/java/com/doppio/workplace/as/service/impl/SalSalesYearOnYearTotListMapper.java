package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesYearOnYearTotListVo;


/**
 *
 * @Class : SalSalesYearOnYearTotListMapper.java
 * @Description : 전년대비매출처별매출현황(합계)
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

@Repository(value = "SalSalesYearOnYearTotListMapper")
public interface SalSalesYearOnYearTotListMapper {

	
	/**
	 * @Method : selectSalSalesYearOnYearTotList
	 * @Description : 전년대비매출처별매출현황(합계) 조회 
	 * @param SalSalesYearOnYearTotListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesYearOnYearTotListVo> selectSalSalesYearOnYearTotList(SalSalesYearOnYearTotListVo paramVo);
	

	
	/**
	 * @Method : selectSalSalesYearOnYearTotListListExcelDown
	 * @Description : 전년대비매출처별매출현황(합계) 엑셀 다운로드
	 * @param SalSalesYearOnYearTotListVo param
	 * @return List<SalSalesYearOnYearTotListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesYearOnYearTotListExcelDown(SalSalesYearOnYearTotListVo paramVo) throws SQLException;
	
}
