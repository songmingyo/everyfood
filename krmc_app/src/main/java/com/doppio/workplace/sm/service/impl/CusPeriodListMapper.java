package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusPeriodListVo;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;


/**
 *
 * @Class : CusPeriodListMapper.java
 * @Description : 기간별출고현황  현황
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

@Repository(value = "cusPeriodListMapper")
public interface CusPeriodListMapper {
	
	
	/**
	 * @Method : selectCusPeriodListCount
	 * @Description : 기간별출고현황  PAGE COUNT 조회 
	 * @param CusPeriodListVo param
	 * @return INT
	 */
	public int selectCusPeriodListCount(CusPeriodListVo paramVo);
	
	/**
	 * @Method : selectCusPeriodList
	 * @Description : 기간별출고현황 조회 
	 * @param CusPeriodListVo param
	 * @return List<CusPeriodListVo>
	 */
	public List<CusPeriodListVo> selectCusPeriodList(CusPeriodListVo paramVo);
	
	/**
	 * @Method : selectCusPeriodListExcelDown
	 * @Description :  기간별출고현황 엑셀 다운로드 
	 * @param CusPeriodListVo param
	 * @return List<CusPeriodListVo>
	 */                     
	public List<HashMap<String, String>> selectCusPeriodListExcelDown(CusPeriodListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectCusPeriodPrintList
	 * @Description : 기간별출고현황 내역 출력 조회 
	 * @param CusPeriodListVo param
	 * @return List<CusPeriodListVo>
	 */
	public List<CusPeriodListVo> selectCusPeriodPrintList(CusPeriodListVo paramVo);
}
