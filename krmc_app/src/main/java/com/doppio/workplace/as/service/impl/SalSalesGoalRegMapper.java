package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesGoalRegVo;

/**
 *
 * @Class : SalSalesGoalRegMapper.java
 * @Description : 영업목표 관리 
 * @author : song 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.      song        	  
 *
 * </pre>
 */

@Repository(value = "salSalesGoalRegMapper")
public interface SalSalesGoalRegMapper {
	
	
	/**
	 * @Method : selectSalSalesGoalRegDlvDetailList
	 * @Description : 영업목표 내역 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<SalSalesGoalRegVo>
	 */
	public List<SalSalesGoalRegVo> selectSalSalesGoalRegList(SalSalesGoalRegVo paramVo);
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 영업목표 저장
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 */
	public int insertSalesGoalReg(SalSalesGoalRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 영업목표 수정
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 * @return List<CustSalesDlvVo>
	 */
	public int updateSalesGoalReg(SalSalesGoalRegVo paramVo) throws SQLException;
	

	/**
	 * @Method : selectSalSalesGoalRegExcelDown
	 * @Description : 영업목표 엑셀 다운로드
	 * @param SalSalesPrdtPrftListVo param
	 * @return List<SalSalesPrdtPrftListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesGoalRegExcelDown(SalSalesGoalRegVo paramVo) throws SQLException;


}
