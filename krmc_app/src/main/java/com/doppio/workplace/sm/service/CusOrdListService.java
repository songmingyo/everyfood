package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.workplace.br.service.BuyInspectListVo;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyRcptListVo;


/**
 *
 * @Class : CusShipRegService.java
 * @Description : 매출처발주현황 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 17.      j10000        	  
 *
 * </pre>
 */

public interface CusOrdListService {
	
	
	

	/**
	 * 매출처발주현황 조회
	 * @param AccSalesDepListVo	paramVo
	 * @return List<CusOrdListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusOrdList(CusOrdListVo paramVo) throws Exception;
	


	/**
	 * 매출처발주현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusOrdListExcelDown(CusOrdListVo paramVo) throws Exception;

	/**
	 * 매출처발주현황 출력
	 * @param CusOrdListVo	paramVo
	 * @return List<CusOrdListVo>
	 * @throws Exception
	 */
	public List<CusOrdListVo> selectCusOrdPrintList(CusOrdListVo paramVo) throws Exception;


}
