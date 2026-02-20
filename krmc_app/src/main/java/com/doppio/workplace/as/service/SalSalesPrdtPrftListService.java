package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 매출처 품목별이익현황 Service interface
 * @Class : SalSalesPrdtPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesPrdtPrftListService {

	/**
	 * 매출처 품목별이익현황 조회
	 * @param SalSalesPrdtPrftListVo	paramVo
	 * @return List<SalSalesPrdtPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesPrdtPrftList(SalSalesPrdtPrftListVo paramVo) throws Exception;
	

	/**
	 * 매출처 품목별이익현황 엑셀 다운로드
	 * @param SalSalesPrdtPrftListVo	paramVo
	 * @return List<SalSalesPrdtPrftListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesPrdtPrftListExcelDown(SalSalesPrdtPrftListVo paramVo) throws Exception;
	
	/**
	 * 매출처 품목별이익현황 Footer 
	 * @param SalSalesPrdtPrftListVo	paramVo
	 * @return SalSalesPrdtPrftListVo
	 * @throws Exception
	 */
	SalSalesPrdtPrftListVo selectSalSalesPrdtPrftFooter(SalSalesPrdtPrftListVo paramVo) throws Exception;
}

