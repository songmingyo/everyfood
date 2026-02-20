package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;


/**
 *
 * @Class : CusMonSlipListService.java
 * @Description : 매출처월별출고현황 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 05.      j10000        	  
 *
 * </pre>
 */

public interface CusMonSlipListService {
	
	
	

	/**
	 * 매출처월별출고현황 조회
	 * @param CusMonSlipListVo	paramVo
	 * @return List<CusOrdListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusMonSlipList(CusMonSlipListVo paramVo) throws Exception;
	


	/**
	 * 매출처월별출고현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusMonSlipListExcelDown(CusMonSlipListVo paramVo) throws Exception;


}
