package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;



/**
 *
 * @Class : CusShipDtlListService.java
 * @Description : 매출처출고현황(상세) 
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

public interface CusShipDtlListService {
	
	
	/**
	 * 매출처출고현황(상세) 합계 엑셀 다운로드 append by song min kyo 2025.04.25 
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusShipDtlListExcelDownSum(CusShipDtlListVo paramVo) throws Exception;

	/**
	 * 매출처출고현황(상세) 조회
	 * @param CusShipDtlListVo	paramVo
	 * @return List<CusShipDtlListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusShipDtlList(CusShipDtlListVo paramVo) throws Exception;
	


	/**
	 * 매출처출고현황(상세) 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusShipDtlListExcelDown(CusShipDtlListVo paramVo) throws Exception;

	/**
	 * 매출처출고현황(상세) 출력
	 * @param CusShipDtlListVo	paramVo
	 * @return List<CusShipDtlListVo>
	 * @throws Exception
	 */
	public List<CusShipDtlListVo> selectCusShipDtlPrintList(CusShipDtlListVo paramVo) throws Exception;


}
