package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;



/**
 *
 * @Class : CusShipRegService.java
 * @Description : 기간별출고현황 
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

public interface CusPeriodListService {
	
	
	

	/**
	 * 기간별출고현황 조회
	 * @param CusPeriodListVo	paramVo
	 * @return List<CusPeriodListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusPeriodList(CusPeriodListVo paramVo) throws Exception;
	


	/**
	 * 기간별출고현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusPeriodListExcelDown(CusPeriodListVo paramVo) throws Exception;
	
	/**
	 * 기간별출고현황 출력
	 * @param CusPriceUnconfVo	paramVo
	 * @return List<CusPriceUnconfVo>
	 * @throws Exception
	 */
	public List<CusPeriodListVo> selectCusPeriodPrintList(CusPeriodListVo paramVo) throws Exception;



}
