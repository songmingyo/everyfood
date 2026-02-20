package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;


/**
 * @author Song
 * @Description : 영업 목표현황 Service interface
 * @Class : SalSalesGoalListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesGoalListService {

	/**
	 * 영업 목표현황 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesGoalList(SalSalesGoalListVo paramVo) throws Exception;
	
		
	/**
	 * 영업 목표현황 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesGoalListExcelDown(SalSalesGoalListVo paramVo) throws Exception;
}

