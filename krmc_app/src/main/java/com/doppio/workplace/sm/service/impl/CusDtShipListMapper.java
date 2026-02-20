package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusDtShipListVo;


/**
 *
 * @Class : CusPeriodListMapper.java
 * @Description : 매출처일자별출고현황  현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 6.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "cusDtShipListMapper")
public interface CusDtShipListMapper {
	
	
	/**
	 * @Method : selectCusDtShipList
	 * @Description : 매출처일자별출고현황 조회 
	 * @param CusPeriodListVo param
	 * @return List<CusPeriodListVo>
	 */
	public List<CusDtShipListVo> selectCusDtShipList(CusDtShipListVo paramVo);
	
	/**
	 * @Method : selectCusDtShipListExcelDown
	 * @Description :  매출처일자별출고현황 엑셀 다운로드 
	 * @param CusDtShipListVo param
	 * @return List<CusDtShipListVo>
	 */                     
	public List<HashMap<String, String>> selectCusDtShipListExcelDown(CusDtShipListVo paramVo) throws SQLException;

}
