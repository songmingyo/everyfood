package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusPeriodListVo;
import com.doppio.workplace.sm.service.CusShipDtlListVo;


/**
 *
 * @Class : CusPeriodListMapper.java
 * @Description : 매출처출고현황(상세)  현황
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

@Repository(value = "cusShipDtlListMapper")
public interface CusShipDtlListMapper {
	
	/**
	 * @Method : selectCusShipDtlListExcelDownSum
	 * @Description : 매출처출고현황(상세) 조회 합계
	 * @param CusShipDtlListVo param
	 * @return List<CusShipDtlListVo>
	 */
	public List<HashMap<String, String>> selectCusShipDtlListExcelDownSum(CusShipDtlListVo paramVo);	
	
	
	/**
	 * @Method : selectCusShipDtlListCount
	 * @Description : 매출처출고현황(상세)  PAGE COUNT 조회 
	 * @param CusShipDtlListVo param
	 * @return INT
	 */
	public int selectCusShipDtlListCount(CusShipDtlListVo paramVo);
	
	/**
	 * @Method : selectCusShipDtlList
	 * @Description : 매출처출고현황(상세) 조회 
	 * @param CusShipDtlListVo param
	 * @return List<CusShipDtlListVo>
	 */
	public List<CusShipDtlListVo> selectCusShipDtlList(CusShipDtlListVo paramVo);
	
	/**
	 * @Method : selectCusShipDtlListExcelDown
	 * @Description :  매출처출고현황(상세) 엑셀 다운로드 
	 * @param CusShipDtlListVo param
	 * @return List<CusShipDtlListVo>
	 */                     
	public List<HashMap<String, String>> selectCusShipDtlListExcelDown(CusShipDtlListVo paramVo) throws SQLException;

	
	
	/**
	 * @Method : selectCusShipDtlPrintList
	 * @Description : 매출처출고현황(상세) 내역 출력 조회 
	 * @param CusShipDtlListVo param
	 * @return List<CusShipDtlListVo>
	 */
	public List<CusShipDtlListVo> selectCusShipDtlPrintList(CusShipDtlListVo paramVo);
}
