package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;



/**
 *
 * @Class : CusDtShipListService.java
 * @Description : 매출처일자별출고현황 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 05. 06.      j10000        	  
 *
 * </pre>
 */

public interface CusDtShipListService {
	
	
	

	/**
	 * 매출처일자별출고현황 조회
	 * @param CusDtShipListVo	paramVo
	 * @return List<CusDtShipListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusDtShipList(CusDtShipListVo paramVo) throws Exception;
	


	/**
	 * 매출처일자별출고현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusDtShipListExcelDown(CusDtShipListVo paramVo) throws Exception;


}
