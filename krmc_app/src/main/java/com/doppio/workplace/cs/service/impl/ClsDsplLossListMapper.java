package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.ClsDsplLossListVo;


/**
 *
 * @Class : ClsDsplLossListMapper.java
 * @Description : 폐기로스 조회
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

@Repository(value = "ClsDsplLossListMapper")
public interface ClsDsplLossListMapper {


	/**
	 * @Method : selectClsDsplLossListOrdList
	 * @Description : 폐기로스 조회 
	 * @param SalSalesGoalListVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<ClsDsplLossListVo> selectClsDsplLossList(ClsDsplLossListVo paramVo);
	

	
	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 매입처 발주 엑셀 다운로드
	 * @param SalSalesGoalListVo param
	 * @return List<ClsDsplLossListVo>
	 */                     
	public List<HashMap<String, String>> selectClsDsplLossListExcel(ClsDsplLossListVo paramVo) throws SQLException;
	
}
