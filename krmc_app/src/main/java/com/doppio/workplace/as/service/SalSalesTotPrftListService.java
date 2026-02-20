package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;



/**
 * @author Song
 * @Description : 매출처 월별이익현황 Service interface
 * @Class : SalSalesTotPrftListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesTotPrftListService {

	/**
	 * 통합매출처이익률(전월대비) 조회
	 * @param SalSalesTotPrftListVo	paramVo
	 * @return List<SalSalesTotPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesTotPrftList(SalSalesTotPrftListVo paramVo) throws Exception;
	
	
	/**
	 * 통합매출처이익률(전월대비) Footer 
	 * @param SalSalesTotPrftListVo	paramVo
	 * @return SalSalesTotPrftListVo
	 * @throws Exception
	 */
	SalSalesTotPrftListVo selectSalesTotPrftFooter(SalSalesTotPrftListVo paramVo) throws Exception;
	
	/**
	 * 통합매출처이익률(전월대비) 엑셀 다운로드
	 * @param selectSalesTotPrftPrintList	paramVo
	 * @return List<SalSalesTotPrftListVo>
	 * @throws Exception
	 */
	public List<SalSalesTotPrftListVo> selectSalesTotPrftPrintList(SalSalesTotPrftListVo paramVo) throws Exception;
	
	/**
	 * 통합매출처이익률(전월대비) 엑셀 다운로드
	 * @param SalSalesTotPrftListVo	paramVo
	 * @return List<SalSalesTotPrftListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesTotPrftListExcelDown(SalSalesTotPrftListVo paramVo) throws Exception;
	

}

