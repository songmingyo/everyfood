package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.doppio.workplace.sm.service.CusMonSlipListVo;


/**
 *
 * @Class : CusMonSlipListMapper.java
 * @Description : 매출처월별출고현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 05.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "cusMonSlipListMapper")
public interface CusMonSlipListMapper {
	
	
	/**
	 * @Method : selectCusMonSlipListCount
	 * @Description : 매출처월별출고현황 PAGE COUNT 조회 
	 * @param CusMonSlipListVo param
	 * @return INT
	 */
	public int selectCusMonSlipListCount(CusMonSlipListVo paramVo);
	
	/**
	 * @Method : selectCusMonSlipList
	 * @Description : 매출처월별출고현황
	 * @param CusMonSlipListVo param
	 * @return List<CusMonSlipListVo>
	 */
	public List<CusMonSlipListVo> selectCusMonSlipList(CusMonSlipListVo paramVo);
	
	/**
	 * @Method : selectCusMonSlipListExcelDown
	 * @Description :  매출처월별출고현황 엑셀 다운로드 
	 * @param CusOrdListVo param
	 * @return List<CusOrdListVo>
	 */                     
	public List<HashMap<String, String>> selectCusMonSlipListExcelDown(CusMonSlipListVo paramVo) throws SQLException;

}
