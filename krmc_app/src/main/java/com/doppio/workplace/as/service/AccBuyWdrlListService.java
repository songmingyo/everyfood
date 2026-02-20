package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AccBuyWdrlListService.java
 * @Description : 매입처별입금현황 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 20.      j10000        	  
 *
 * </pre>
 */

public interface AccBuyWdrlListService {
	
	
	

	/**
	 * 매출처별입금현황 조회
	 * @param AccBuyWdrlListVo	paramVo
	 * @return List<AccBuyWdrlListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAccBuyWdrlList(AccBuyWdrlListVo paramVo) throws Exception;
	


	/**
	 * 매출처별입금현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAccBuyWdrlListExcelDown(AccBuyWdrlListVo paramVo) throws Exception;


}
