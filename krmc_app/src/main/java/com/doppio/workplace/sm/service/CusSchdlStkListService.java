package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;


/**
 *
 * @Class : CusSchdlStklListService.java
 * @Description : 출고예정 재고리스트 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 23.      j10000        	  
 *
 * </pre>
 */

public interface CusSchdlStkListService {
	
	
	

	/**
	 * 출고예정 재고리스트  조회
	 * @param CusSchdlStkListVo	paramVo
	 * @return List<CusSchdlStkListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusSchdlStkList(CusSchdlStkListVo paramVo) throws Exception;
	

	/**
	 * 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusSchdlStkListExcelDown(CusSchdlStkListVo paramVo) throws Exception;
}
