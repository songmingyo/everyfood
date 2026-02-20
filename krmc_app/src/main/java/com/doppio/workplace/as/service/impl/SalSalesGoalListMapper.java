package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesGoalListVo;


/**
 *
 * @Class : SalSalesGoalListMapper.java
 * @Description : 영업목표 현황
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

@Repository(value = "SalSalesGoalListMapper")
public interface SalSalesGoalListMapper {


	/**
	 * @Method : selectsalSalesGoalListListOrdCount
	 * @Description : 영업목표 현황 PAGE COUNT 조회 
	 * @param SalSalesGoalListVo param
	 * @return INT
	 */
	public int selectSalSalesGoalListCount(SalSalesGoalListVo paramVo);
	
	/**
	 * @Method : selectSalSalesGoalList
	 * @Description : 영업목표 현황 조회 
	 * @param SalSalesGoalListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesGoalListVo> selectSalSalesGoalList(SalSalesGoalListVo paramVo);
	
	/**
	 * @Method : selectSalSalesGoalListListExcelDown
	 * @Description : 영업목표 현황 엑셀 다운로드
	 * @param SalSalesGoalListVo param
	 * @return List<SalSalesGoalListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesGoalListExcelDown(SalSalesGoalListVo paramVo) throws SQLException;
	
}
