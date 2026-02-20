package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyRcptListVo;
import com.doppio.workplace.sm.service.CusOrdListVo;


/**
 *
 * @Class : CusOrdListMapper.java
 * @Description : 매출처발주 현황
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

@Repository(value = "cusOrdListMapper")
public interface CusOrdListMapper {
	
	/**
	 * @Method : selectCusOrdList
	 * @Description : 매출처발주 조회 
	 * @param CusOrdListVo param
	 * @return List<CusOrdListVo>
	 */
	public List<CusOrdListVo> selectCusOrdList(CusOrdListVo paramVo);
	
	/**
	 * @Method : selectBuyOrdListExcelDown
	 * @Description :  매출처발주 엑셀 다운로드 
	 * @param CusOrdListVo param
	 * @return List<CusOrdListVo>
	 */                     
	public List<HashMap<String, String>> selectCusOrdListExcelDown(CusOrdListVo paramVo) throws SQLException;

	
	/**
	 * @Method : selectCusOrdPrintList
	 * @Description : 매출처발주 내역 출력 조회 
	 * @param BuyRcptListVo param
	 * @return List<CusOrdListVo>
	 */
	public List<CusOrdListVo> selectCusOrdPrintList(CusOrdListVo paramVo);
}
