package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 매출처월이익현황 Service interface
 * @Class : SalSalesMonPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesMonPrftListService {

	/**
	 * 매출처월이익현황 조회
	 * @param SalSalesMonPrftListVo	paramVo
	 * @return List<SalSalesMonPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesMonPrftList(SalSalesMonPrftListVo paramVo) throws Exception;
	

	/**
	 * 매출처월이익현황 엑셀 다운로드
	 * @param SalSalesMonPrftListVo	paramVo
	 * @return List<SalSalesMonPrftListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesMonPrftListExcelDown(SalSalesMonPrftListVo paramVo) throws Exception;
	

}

