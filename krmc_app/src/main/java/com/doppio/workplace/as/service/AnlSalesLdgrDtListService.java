package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlCreditSalesListService.java
 * @Description : 매출처원장(일별)
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

public interface AnlSalesLdgrDtListService {
	
	
	

	/**
	 * 매출처원장(일별) 조회
	 * @param AnlSalesLdgrDtListVo	paramVo
	 * @return List<AnlSalesLdgrDtListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlSalesLdgrDtList(AnlSalesLdgrDtListVo paramVo) throws Exception;
	


	/**
	 * 매출처원장(일별) 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlSalesLdgrDtListExcelDown(AnlSalesLdgrDtListVo paramVo) throws Exception;
	
	
	/**
	 * 매출처원장(일별) 출력
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<AnlSalesLdgrDtListVo> selectAnlSalesLdgrDtPrintList(AnlSalesLdgrDtListVo paramVo) throws Exception;


}
