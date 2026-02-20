package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;



/**
 *
 * @Class : CusSampleListService.java
 * @Description : 샘플출고현황 
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

public interface CusSampleListService {
	
	
	

	/**
	 * 샘플출고현황 조회
	 * @param CusSampleListVo	paramVo
	 * @return List<CusSampleListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusSampleList(CusSampleListVo paramVo) throws Exception;
	


	/**
	 * 샘플출고현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusSampleListExcelDown(CusSampleListVo paramVo) throws Exception;


}
