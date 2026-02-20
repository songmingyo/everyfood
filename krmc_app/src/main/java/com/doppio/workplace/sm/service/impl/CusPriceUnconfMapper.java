package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusOrdListVo;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;


/**
 *
 * @Class : CusShipRegMapper.java
 * @Description : 매출단가미확정건
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

@Repository(value = "cusPriceUnconfMapper")
public interface CusPriceUnconfMapper {
	
	
	/**
	 * @Method : selectCusPriceUnconfCount
	 * @Description : 매출단가미확정건 PAGE COUNT 조회 
	 * @param CusPriceUnconfVo param
	 * @return INT
	 */
	public int selectCusPriceUnconfCount(CusPriceUnconfVo paramVo);
	
	/**
	 * @Method : selectCusPriceUnconfList
	 * @Description : 매출단가미확정건 조회 
	 * @param CusPriceUnconfVo param
	 * @return List<CusPriceUnconfVo>
	 */
	public List<CusPriceUnconfVo> selectCusPriceUnconfList(CusPriceUnconfVo paramVo);
	
	/**
	 * @Method : selectCusPriceUnconfData
	 * @Description : 매출단가미확정건 상세조회 
	 * @param CusPriceUnconfVo param
	 * @return CusPriceUnconfVo
	 */
	public CusPriceUnconfVo selectCusPriceUnconfData(CusPriceUnconfVo paramVo);

	/**
	 * @Method : selectCusPriceUnconfListExcelDown
	 * @Description :  매출단가미확정 엑셀 다운로드 
	 * @param CusPriceUnconfVo param
	 * @return List<CusPriceUnconfVo>
	 */                     
	public List<HashMap<String, String>> selectCusPriceUnconfListExcelDown(CusPriceUnconfVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectCusPricePrintList
	 * @Description : 매출단가미확정 내역 출력 조회 
	 * @param CusPriceUnconfVo param
	 * @return List<CusPriceUnconfVo>
	 */
	public List<CusPriceUnconfVo> selectCusPricePrintList(CusPriceUnconfVo paramVo);
}
