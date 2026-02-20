package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 품목별이익현황 Service interface
 * @Class : SalPrdtPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalPrdtPrftListService {

	/**
	 * 품목별이익현황 조회
	 * @param SalPrdtPrftListVo	paramVo
	 * @return List<SalPrdtPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalPrdtPrftList(SalPrdtPrftListVo paramVo) throws Exception;
	
		
	/**
	 * 품목별이익현황 엑셀 다운로드
	 * @param SalPrdtPrftListVo	paramVo
	 * @return List<SalPrdtPrftListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalPrdtPrftListExcelDown(SalPrdtPrftListVo paramVo) throws Exception;
	
	/**
	 * 품목별이익현황 Footer 
	 * @param SalPrdtPrftListVo	paramVo
	 * @return SalPrdtPrftListVo
	 * @throws Exception
	 */
	SalPrdtPrftListVo selectSalPrdtPrftFooter(SalPrdtPrftListVo paramVo) throws Exception;
}

