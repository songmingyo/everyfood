package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlBuyPrdtPrftListService.java
 * @Description : 매입처품목별이익현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 18.      j10000        	  
 *
 * </pre>
 */

public interface AnlBuyPrdtPrftListService {
	

	/**
	 * 매입처품목별이익현황  조회
	 * @param AnlBuyPrdtPrftListVo	paramVo
	 * @return List<AnlBuyPrdtPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlBuyPrdtPrftList(AnlBuyPrdtPrftListVo paramVo) throws Exception;

	
	/**
	 * 매입처품목별이익현황 Footer 
	 * @param AnlBuyPrdtPrftListVo	paramVo
	 * @return AnlBuyPrdtPrftListVo
	 * @throws Exception
	 */
	AnlBuyPrdtPrftListVo selectAnlBuyPrdtPrftFooter(AnlBuyPrdtPrftListVo paramVo) throws Exception;
	
	/**
	 * 매입처품목별이익현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlBuyPrdtPrftListExcelDown(AnlBuyPrdtPrftListVo paramVo) throws Exception;
}
