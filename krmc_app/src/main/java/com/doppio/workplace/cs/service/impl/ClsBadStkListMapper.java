package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.ClsBadStkListVo;


/**
 *
 * @Class : ClsBadStkListMapper.java
 * @Description : 악성재고조회
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

@Repository(value = "ClsBadStkListMapper")
public interface ClsBadStkListMapper {


	/**
	 * @Method : selectClsBadStkListOrdList
	 * @Description : 악성재고조회
	 * @param SalSalesGoalListVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<ClsBadStkListVo> selectClsBadStkList(ClsBadStkListVo paramVo);
	
	

	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 악성재고 엑셀 다운로드
	 * @param SalSalesGoalListVo param
	 * @return List<ClsBadStkListVo>
	 */                     
	public List<HashMap<String, String>> selectClsBadStkListExcel(ClsBadStkListVo paramVo) throws SQLException;
	
}
