package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 전년대비매출처별매출현황(합계) Service interface
 * @Class : SalSalesYearOnYearTotListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesYearOnYearTotListService {

	/**
	 * 전년대비매출처별매출현황(합계) 조회
	 * @param SalSalesYearOnYearTotListVo	paramVo
	 * @return List<SalSalesYearOnYearTotListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesYearOnYearTotList(SalSalesYearOnYearTotListVo paramVo) throws Exception;
	

	/**
	 * 전년대비매출처별매출현황(합계) 엑셀 다운로드
	 * @param SalSalesYearOnYearTotListVo	paramVo
	 * @return List<SalSalesYearOnYearTotListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesYearOnYearTotListExcelDown(SalSalesYearOnYearTotListVo paramVo) throws Exception;
	

}

