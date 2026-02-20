package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesYearOnYearDtlListVo;


/**
 *
 * @Class : SalSalesYearOnYearDtlListMapper.java
 * @Description : 전년대비매출처별매출현황(상세)
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

@Repository(value = "SalSalesYearOnYearDtlListMapper")
public interface SalSalesYearOnYearDtlListMapper {

	
	/**
	 * @Method : selectSalSalesYearOnYearDtlList
	 * @Description : 전년대비매출처별매출현황(상세) 조회 
	 * @param SalSalesYearOnYearDtlListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesYearOnYearDtlListVo> selectSalSalesYearOnYearDtlList(SalSalesYearOnYearDtlListVo paramVo);
	

	
	/**
	 * @Method : selectSalSalesYearOnYearDtlListListExcelDown
	 * @Description : 전년대비매출처별매출현황(상세) 엑셀 다운로드
	 * @param SalSalesYearOnYearDtlListVo param
	 * @return List<SalSalesYearOnYearDtlListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesYearOnYearDtlListExcelDown(SalSalesYearOnYearDtlListVo paramVo) throws SQLException;
	
}
