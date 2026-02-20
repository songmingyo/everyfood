package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;


/**
 * @author Song
 * @Description : 영업 실적 현황 Service interface
 * @Class : SalSalesPrPerfListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesPrPerfListService {

	/**
	 * 영업 실적현황 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<SalSalesPrPerfListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesPrPerfList(SalSalesPrPerfListVo paramVo) throws Exception;
	
		
	/**
	 * 영업 실적현황 엑셀 다운로드
	 * @param SalSalesPrPerfListVo	paramVo
	 * @return List<SalSalesPrPerfListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesPrPerfListExcelDown(SalSalesPrPerfListVo paramVo) throws Exception;
}

