package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyRcptRegVo;

/**
 *
 * @Class : CusSampleRegService.java
 * @Description : 매출처샘플등록 관리 
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

public interface CusSampleRegService {
	
	
	

	/**
	 * @Method : selectCusSampleRegList
	 * @Description : 매출처샘플등록 대상품목 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<CusSampleRegVo>
	 */
	public List<CusSampleRegVo> selectCusSampleRegList(CusSampleRegVo paramVo);
	
	
	/**
	 * @Method : selectCusSampleRegDetailList
	 * @Description : 매출처샘플등록 내역 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<CusSampleRegVo>
	 */
	public List<CusSampleRegVo> selectCusSampleRegDetailList(CusSampleRegVo paramVo);
	
	/**
	 * 매출처샘플등록 하나의 품목만 조회 
	 * @param SalSalesGoalRegVo	paramVo
	 * @return 
	 * @throws Exception
	 */
	HashMap<String,Object>  selectCusSampleRegPrdtAdd(CusSampleRegVo paramVo) throws Exception;
	
	/**
	 * 매출처샘플등록 정보 저장
	 * @param SalSalesGoalRegVo	paramVo
	 * @return CusSampleRegVo
	 * @throws Exception
	 */
	Result insertCusSampleReg(CusSampleRegVo paramVo) throws Exception;

}
