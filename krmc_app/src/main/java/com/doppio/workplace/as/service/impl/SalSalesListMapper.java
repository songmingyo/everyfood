package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesListVo;


/**
 *
 * @Class : SalSalesListMapper.java
 * @Description : 영업신규현황
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2025. 08. 22.      song        	  
 *
 * </pre>
 */

@Repository(value = "SalSalesListMapper")
public interface SalSalesListMapper {

	/**
	 * @Method : selectSalSalesMstList
	 * @Description : 영업목표 현황 조회 
	 * @param SalSalesListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesListVo> selectSalSalesMstList(SalSalesListVo paramVo);	
	
	/**
	 * @Method : selectSalSalesMstListCnt
	 * @Description : 영업신규 현황 PAGE COUNT 조회 
	 * @param SalSalesListVo param
	 * @return INT
	 */
	public int selectSalSalesMstListCnt(SalSalesListVo paramVo);
	
	
	
	/**
	 * @Method : salSalesMstListSelExcelDown
	 * @Description : 영업 신규개설현황 엑셀 다운로드
	 * @param SalSalesListVo param
	 * @return List<SalSalesListVo>
	 */                     
	public List<HashMap<String, String>> salSalesMstListSelExcelDown(SalSalesListVo paramVo) throws SQLException;
	
}
