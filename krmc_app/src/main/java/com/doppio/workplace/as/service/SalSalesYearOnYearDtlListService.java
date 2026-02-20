package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 전년대비매출처별매출현황(상세) Service interface
 * @Class : SalSalesYearOnYearDtlListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesYearOnYearDtlListService {

	/**
	 * 전년대비매출처별매출현황(상세) 조회
	 * @param SalSalesYearOnYearDtlListVo	paramVo
	 * @return List<SalSalesYearOnYearDtlListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesYearOnYearDtlList(SalSalesYearOnYearDtlListVo paramVo) throws Exception;
	

	/**
	 * 전년대비매출처별매출현황(상세) 엑셀 다운로드
	 * @param SalSalesYearOnYearDtlListVo	paramVo
	 * @return List<SalSalesYearOnYearDtlListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesYearOnYearDtlListExcelDown(SalSalesYearOnYearDtlListVo paramVo) throws Exception;
	

}

