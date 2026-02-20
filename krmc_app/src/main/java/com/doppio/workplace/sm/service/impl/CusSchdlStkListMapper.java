package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.doppio.workplace.sm.service.CusSchdlStkListVo;


/**
 *
 * @Class : CusShipRegMapper.java
 * @Description : 출고예정 재고리스트 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 17.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "cusSchdlStkListMapper")
public interface CusSchdlStkListMapper {
	
	
	/**
	 * @Method : selectCusSchdlStkListCount
	 * @Description : 출고예정 재고리스트 PAGE COUNT 조회 
	 * @param CusSchdlStkListVo param
	 * @return INT
	 */
	public int selectCusSchdlStkListCount(CusSchdlStkListVo paramVo);
	
	/**
	 * @Method : selectCusSchdlStkList
	 * @Description : 출고예정 재고리스트 조회 
	 * @param CusSchdlStkListVo param
	 * @return List<CusSchdlStklListVo>
	 */
	public List<CusSchdlStkListVo> selectCusSchdlStkList(CusSchdlStkListVo paramVo);
	

	/**
	 * @Method : selectCusSchdlStkListExcelDown
	 * @Description : 엑셀 다운로드 
	 * @param CusSchdlStkListVo param
	 * @return List<CusSchdlStklListVo>
	 */                     
	public List<HashMap<String, String>> selectCusSchdlStkListExcelDown(CusSchdlStkListVo paramVo) throws SQLException;

}
