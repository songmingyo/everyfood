package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;


/**
 *
 * @Class : CusAgrclListService.java
 * @Description : 매출처출고현황(농산물) 
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

public interface CusShipAgrclListService {
	
	
	

	/**
	 * 매출처출고현황(농산물) 조회
	 * @param CusShipAgrclListVo	paramVo
	 * @return List<CusShipAgrclListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusShipAgrclList(CusShipAgrclListVo paramVo) throws Exception;
	


	/**
	 * 매출처출고현황(농산물) 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusShipAgrclListExcelDown(CusShipAgrclListVo paramVo) throws Exception;


	
	/**
	 * 매출처출고현황(농산물) 출력
	 * @param CusShipAgrclListVo	paramVo
	 * @return List<CusShipAgrclListVo>
	 * @throws Exception
	 */
	public List<CusShipAgrclListVo> selectCusShipAgrcPrintList(CusShipAgrclListVo paramVo) throws Exception;

}
