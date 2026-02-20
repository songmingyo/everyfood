package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 *
 * @Class : ClsDsplLossRegService.java
 * @Description : 폐기/로스등록 관리 
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

public interface ClsDsplLossRegService {
	
	
	

	/**
	 * @Method : selectClsDsplLossRegList
	 * @Description : 폐기/로스등록 대상품목 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<ClsDsplLossRegVo>
	 */
	public List<ClsDsplLossRegVo> clsDsplLossRegPrdtList(ClsDsplLossRegVo paramVo);
	
	
	/**
	 * @Method : selectClsDsplLossRegDetailList
	 * @Description : 폐기/로스등록 내역 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<ClsDsplLossRegVo>
	 */
	public List<ClsDsplLossRegVo> selectClsDsplLossRegDetailList(ClsDsplLossRegVo paramVo);
	
	/**
	 * 폐기/로스등록 하나의 품목만 조회 
	 * @param SalSalesGoalRegVo	paramVo
	 * @return 
	 * @throws Exception
	 */
	HashMap<String,Object>  selectClsDsplLossRegPrdtAdd(ClsDsplLossRegVo paramVo) throws Exception;
	
	/**
	 * 폐기/로스등록 정보 저장
	 * @param SalSalesGoalRegVo	paramVo
	 * @return ClsDsplLossRegVo
	 * @throws Exception
	 */
	Result insertClsDsplLossReg(ClsDsplLossRegVo paramVo) throws Exception;

}
