package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 *
 * @Class : SalSalesGoalRegService.java
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

public interface SalSalesGoalRegService {
	

	/**
	 * @Method : selectSalSalesGoalRegList
	 * @Description : 영업목표 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<SalSalesGoalRegVo>
	 */
	public List<SalSalesGoalRegVo> selectSalSalesGoalRegList(SalSalesGoalRegVo paramVo);
	

	/**
	 * 영업목표 정보 저장
	 * @param SalSalesGoalRegVo	paramVo
	 * @return SalSalesGoalRegVo
	 * @throws Exception
	 */
	Result insertSalSalesGoalReg(SalSalesGoalRegVo paramVo) throws Exception;

	
	/**
	 * 영업목표 엑셀 다운로드
	 * @param SalSalesGoalRegVo	paramVo
	 * @return List<SalSalesGoalRegVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesGoalRegExcelDown(SalSalesGoalRegVo paramVo) throws Exception;
}
