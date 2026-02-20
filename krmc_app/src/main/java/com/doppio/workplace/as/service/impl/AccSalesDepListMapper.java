package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AccSalesDepListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description : 매출처별입금현황 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 14.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "accSalesDepListMapper")
public interface AccSalesDepListMapper {
	
	
	/**
	 * @Method : selectAccSalesDepListCount
	 * @Description : 매출처별입금현황 PAGE COUNT 조회 
	 * @param AccSalesDepListVo param
	 * @return INT
	 */
	public int selectAccSalesDepListCount(AccSalesDepListVo paramVo);
	
	/**
	 * @Method : selectAccSalesDepList
	 * @Description : 매출처별입금현황 조회 
	 * @param AccSalesDepListVo param
	 * @return List<AccSalesDepListVo>
	 */
	public List<AccSalesDepListVo> selectAccSalesDepList(AccSalesDepListVo paramVo);
	
	/**
	 * @Method : selectAccSalesDepListExcelDown
	 * @Description :  매출처별입금현황 엑셀 다운로드 
	 * @param AccSalesDepListVo param
	 * @return List<AccSalesDepListVo>
	 */                     
	public List<HashMap<String, String>> selectAccSalesDepListExcelDown(AccSalesDepListVo paramVo) throws SQLException;

}
