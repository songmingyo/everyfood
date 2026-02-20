package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlSalesLdgrYearListService.java
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

public interface AnlSalesLdgrYearListService {
	
	
	

	/**
	 * 매출처원장(년간) 조회
	 * @param AnlSalesLdgrYearListVo	paramVo
	 * @return List<AnlSalesLdgrYearListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlSalesLdgrYearList(AnlSalesLdgrYearListVo paramVo) throws Exception;
	
	/**
	 * 매출처원장(년간) 엑셀
	 * @param AnlSalesLdgrYearListVo	paramVo
	 * @return List<AnlSalesLdgrYearListVo>
	 * @throws Exception
	 */
	List<HashMap<String, String>> selectAnlSalesLdgrYearListExcelDown(AnlSalesLdgrYearListVo paramVo) throws Exception;
	

}
