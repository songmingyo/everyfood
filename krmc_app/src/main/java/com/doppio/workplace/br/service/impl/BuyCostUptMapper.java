package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyCostUptVo;


/**
 *
 * @Class : BuyCostUptMapper.java
 * @Description : 매입 단가 일괄 수정 관리 
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

@Repository(value = "BuyCostUptMapper")
public interface BuyCostUptMapper {


	/**
	 * @Method : selectBuyCostUptListOrdCount
	 * @Description : 매입 단가 일괄 수정 PAGE COUNT 조회 
	 * @param CusPriceUptVo param
	 * @return INT
	 */
	public int selectBuyCostUptListCount(BuyCostUptVo paramVo);
	
	
	/**
	 * @Method : selectBuyCostUptList
	 * @Description : 매입 단가 일괄 수정 조회 
	 * @param CusPriceUptVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyCostUptVo> selectBuyCostUptList(BuyCostUptVo paramVo);
	
	/**
	 * @Method : updateBuyCostUptData
	 * @Description : 매입 단가 일괄 수정 수정
	 * @param CusPriceUptVo param
	 * @return INT
	 */
	public int updateBuyCostUptData(BuyCostUptVo paramVo) throws SQLException;
	
	
	
}
