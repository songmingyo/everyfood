package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;


/**
 * @author Song min kyo
 * @Description : 영업 신규현황 Service interface
 * @Class : SalSalesListService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesListService {

	/**
	 * 영업 신규현황 조회
	 * @param SalSalesListVo	paramVo
	 * @return List<SalSalesListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesMstList(SalSalesListVo paramVo) throws Exception;
	
		
	/**
	 * 영업 목표현황 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> salSalesMstListSelExcelDown(SalSalesListVo paramVo) throws Exception;
}

