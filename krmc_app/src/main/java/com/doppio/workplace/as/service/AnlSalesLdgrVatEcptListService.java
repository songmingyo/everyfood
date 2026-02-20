package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlSalesLdgrVatEcptListService.java
 * @Description : 매출처원장(연간)
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

public interface AnlSalesLdgrVatEcptListService {
	
	
	

	/**
	 * 매출처원장(월별) 조회
	 * @param AnlSalesLdgrVatEcptListVo	paramVo
	 * @return List<AnlSalesLdgrVatEcptListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlSalesLdgrVatEcptList(AnlSalesLdgrVatEcptListVo paramVo) throws Exception;
	


	/**
	 * 매출처원장(월별) 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlSalesLdgrVatEcptListExcelDown(AnlSalesLdgrVatEcptListVo paramVo) throws Exception;


}
