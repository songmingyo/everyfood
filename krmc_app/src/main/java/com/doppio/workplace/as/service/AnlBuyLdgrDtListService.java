package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlBuyLdgrDtListService.java
 * @Description : 매입처원장(일별) 
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

public interface AnlBuyLdgrDtListService {
	

	/**
	 * 매입처원장(일별)  조회
	 * @param AnlBuyLdgrDtListVo	paramVo
	 * @return List<AnlBuyLdgrDtListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlBuyLdgrDtList(AnlBuyLdgrDtListVo paramVo) throws Exception;
	
	/**
	 * 매입처원장(일별)  출력
	 * @param selectbuyLdgrListPrint	paramVo
	 * @return List<AnlBuyLdgrDtListVo>
	 * @throws Exception
	 */
	List<AnlBuyLdgrDtListVo> selectbuyLdgrListPrint(AnlBuyLdgrDtListVo paramVo) throws Exception;
	
	/**
	 * 매입처원장(일별) 엑셀 다운로드
	 * @param AnlBuyLdgrDtListVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlBuyLdgrDtListExcelDown(AnlBuyLdgrDtListVo paramVo) throws Exception;
	

}
