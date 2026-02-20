package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 *
 * @Class : CusSalesEstRegService.java
 * @Description : 매출 견적서 관리
 * @author : song 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.            	  
 *
 * </pre>
 */

public interface CusSalesEstRegService {
	

	/**
	 * @Method : selectCusSalesEstRegPrdtList
	 * @Description : 매출 견적서 품목 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	public HashMap<String, Object> selectCusSalesEstRegPrdtList(CusSalesEstRegVo paramVo) throws Exception;
	
	/**
	 * @Method : selectCusSalesEstRegList
	 * @Description : 매출 견적서 Head 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	public CusSalesEstRegVo selectCusSalesEstRegHeadList(CusSalesEstRegVo paramVo) throws Exception;
	
	
	/**
	 * @Method : selectCusSalesEstRegList
	 * @Description : 매출 견적서 Item 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	public HashMap<String, Object> selectCusSalesEstRegItemList(CusSalesEstRegVo paramVo) throws Exception;
	
	/**
	 *매출 견적서 정보 저장
	 * @param CusSalesEstRegVo	paramVo
	 * @return CusSalesEstRegVo
	 * @throws Exception
	 */
	Result insertCusSalesEstReg(CusSalesEstRegVo paramVo) throws Exception;
	
	/**
	 * 매출 견적서 정보 출력
	 * @param CusSalesEstRegVo	paramVo
	 * @return CusSalesEstRegVo
	 * @throws Exception
	 */
	public List<CusSalesEstRegVo> selectCurSalesEstPrintList(CusSalesEstRegVo paramVo) throws Exception;

}
