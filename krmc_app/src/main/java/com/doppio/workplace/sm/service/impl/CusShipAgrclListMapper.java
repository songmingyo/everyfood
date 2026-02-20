package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusPriceUnconfVo;
import com.doppio.workplace.sm.service.CusShipAgrclListVo;


/**
 *
 * @Class : CusShipRegMapper.java
 * @Description : 매출처출고현황(농산물)
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023.05.03.     j10000        	  
 *
 * </pre>
 */

@Repository(value = "cusShipAgrclListMapper")
public interface CusShipAgrclListMapper {
	
	
	/**
	 * @Method : selectCusOrdListCount
	 * @Description : 매출처출고현황(농산물) PAGE COUNT 조회 
	 * @param CusOrdListVo param
	 * @return INT
	 */
	public int selectCusShipAgrclListCount(CusShipAgrclListVo paramVo);
	
	/**
	 * @Method : selectCusShipAgrclList
	 * @Description : 매출처출고현황(농산물) 조회 
	 * @param CusShipAgrclListVo param
	 * @return List<CusShipAgrclListVo>
	 */
	public List<CusShipAgrclListVo> selectCusShipAgrclList(CusShipAgrclListVo paramVo);
	
	/**
	 * @Method : selectCusShipAgrclListExcelDown
	 * @Description :  매출처출고현황(농산물) 엑셀 다운로드 
	 * @param CusShipAgrclListVo param
	 * @return List<CusShipAgrclListVo>
	 */                     
	public List<HashMap<String, String>> selectCusShipAgrclListExcelDown(CusShipAgrclListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectCusShipAgrcPrintList
	 * @Description : 매출처출고현황(농산물) 내역 출력 조회 
	 * @param CusShipAgrclListVo param
	 * @return List<CusShipAgrclListVo>
	 */
	public List<CusShipAgrclListVo> selectCusShipAgrcPrintList(CusShipAgrclListVo paramVo);

}
