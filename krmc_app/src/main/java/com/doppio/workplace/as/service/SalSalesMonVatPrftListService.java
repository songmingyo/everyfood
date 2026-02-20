package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 매출처 월별이익현황 Service interface
 * @Class : SalSalesMonVatPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesMonVatPrftListService {

	/**
	 * 매출처 월별이익현황 조회
	 * @param SalSalesMonVatPrftListVo	paramVo
	 * @return List<SalSalesMonVatPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesMonVatPrftList(SalSalesMonVatPrftListVo paramVo) throws Exception;
	
	
	/**
	 * 매출처 월별이익현황 footer
	 * @param SalSalesMonVatPrftListVo	paramVo
	 * @return SalSalesMonVatPrftListVo
	 * @throws Exception
	 */
	SalSalesMonVatPrftListVo selectSalSalesMonVatPrftFooter(SalSalesMonVatPrftListVo paramVo) throws Exception;

	/**
	 * 매출처 월별이익현황 엑셀 다운로드
	 * @param SalSalesMonVatPrftListVo	paramVo
	 * @return List<SalSalesMonVatPrftListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesMonVatPrftListExcelDown(SalSalesMonVatPrftListVo paramVo) throws Exception;
	

}

