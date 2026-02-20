package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlBuyLdgrMonListService.java
 * @Description : 매입처원장(년별) 
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

public interface AnlBuyLdgrMonListService {
	
	
	

	/**
	 * 매입처원장(년별)  조회
	 * @param AnlBuyLdgrMonListVo	paramVo
	 * @return List<AnlBuyLdgrMonListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlBuyLdgrMonList(AnlBuyLdgrMonListVo paramVo) throws Exception;
	
	/**
	 * 매입처원장(년별)  출력 
	 * @param AnlBuyLdgrMonListVo	paramVo
	 * @return List<AnlBuyLdgrMonListVo>
	 * @throws Exception
	 */
	List<AnlBuyLdgrMonListVo> selectBuyLdgrMonListPrint(AnlBuyLdgrMonListVo paramVo) throws Exception;
	
	/**
	 * 매입처원장(년별) 엑셀 다운로드
	 * @param AnlBuyLdgrMonListVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlBuyLdgrMonListExcelDown(AnlBuyLdgrMonListVo paramVo) throws Exception;

}
