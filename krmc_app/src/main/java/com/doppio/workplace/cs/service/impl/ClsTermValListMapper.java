package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.ClsTermValListVo;


/**
 *
 * @Class : ClsTermValListMapper.java
 * @Description : 소비기한 관리 
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

@Repository(value = "ClsTermValListMapper")
public interface ClsTermValListMapper {


	/**
	 * @Method : selectClsTermValListOrdList
	 * @Description : 소비기한 조회 
	 * @param SalSalesGoalListVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<ClsTermValListVo> selectClsTermValList(ClsTermValListVo paramVo);
	
	

	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 소비기한 엑셀 다운로드
	 * @param SalSalesGoalListVo param
	 * @return List<ClsTermValListVo>
	 */                     
	public List<HashMap<String, String>> selectClsTermValListExcel(ClsTermValListVo paramVo) throws SQLException;
	
}
