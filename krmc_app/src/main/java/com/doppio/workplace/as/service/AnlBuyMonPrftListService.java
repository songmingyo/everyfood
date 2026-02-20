package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlBuyMonPrftListService.java
 * @Description : 매입처월별이익현황
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

public interface AnlBuyMonPrftListService {

	/**
	 * 매입처월별이익현황  조회
	 * @param AnlBuyMonPrftListVo	paramVo
	 * @return List<AnlBuyMonPrftListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlBuyMonPrftList(AnlBuyMonPrftListVo paramVo) throws Exception;
	

	/**
	 * 매입처월별이익현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlBuyMonPrftListExcelDown(AnlBuyMonPrftListVo paramVo) throws Exception;
	
}
