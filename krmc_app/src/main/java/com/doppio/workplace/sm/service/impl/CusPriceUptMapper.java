package com.doppio.workplace.sm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusPriceUptVo;


/**
 *
 * @Class : CusPriceUptMapper.java
 * @Description : 매출 단가 일괄 수정 관리 
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      song        	  
 *
 * </pre>
 */

@Repository(value = "CusPriceUptMapper")
public interface CusPriceUptMapper {


	/**
	 * @Method : selectCusPriceUptListOrdCount
	 * @Description : 매출 단가 일괄 수정 PAGE COUNT 조회 
	 * @param CusPriceUptVo param
	 * @return INT
	 */
	public int selectCusPriceUptListCount(CusPriceUptVo paramVo);
	
	
	/**
	 * @Method : selectCusPriceUptList
	 * @Description : 매출 단가 일괄 수정 조회 
	 * @param CusPriceUptVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<CusPriceUptVo> selectCusPriceUptList(CusPriceUptVo paramVo);
	
	/**
	 * @Method : updateCusPriceUptData
	 * @Description : 매출 단가 일괄 수정 수정
	 * @param CusPriceUptVo param
	 * @return INT
	 */
	public int updateCusPriceUptData(CusPriceUptVo paramVo) throws SQLException;
	
	
	
}
