package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.ClsDayStkListVo;


/**
 *
 * @Class : ClsDayStkListMapper.java
 * @Description : 일재고 조회
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

@Repository(value = "ClsDayStkListMapper")
public interface ClsDayStkListMapper {


	/**
	 * @Method : selectClsDayStkListOrdList
	 * @Description : 일재고 조회 
	 * @param SalSalesGoalListVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<ClsDayStkListVo> selectClsDayStkList(ClsDayStkListVo paramVo);
	

	
	/**
	 * @Method : selectClsDayStkListOrdPrtList
	 * @Description : 매입처 발주 발주내역 출력 조회 
	 * @param SalSalesGoalListVo param
	 * @return List<ClsDayStkListVo>
	 */
	public List<ClsDayStkListVo> selectStkListPrint(ClsDayStkListVo paramVo);
	
	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 매입처 발주 엑셀 다운로드
	 * @param SalSalesGoalListVo param
	 * @return List<ClsDayStkListVo>
	 */                     
	public List<HashMap<String, String>> selectClsDayStkListExcel(ClsDayStkListVo paramVo) throws SQLException;
	
	/**
	 * 품목별 당월, 전월, 전전월 출고량 조회 add by song min kyo 2024.10.11
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public ClsDayStkListVo selPrdtQtyMonth(ClsDayStkListVo paramVo) throws SQLException;

}
