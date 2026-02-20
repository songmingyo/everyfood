package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.workplace.sm.service.CusPriceUnconfVo;


/**
 *
 * @Class : CusPriceUnconfService.java
 * @Description : 매출단가미확정건 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 30.      j10000        	  
 *
 * </pre>
 */

public interface CusPriceUnconfService {
	
	
	

	/**
	 * 매출단가미확정건  조회
	 * @param CusPriceUnconfVo	paramVo
	 * @return List<CusPriceUnconfVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusPriceUnconfList(CusPriceUnconfVo paramVo) throws Exception;
	


	/**
	 * 매출단가미확정건 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCusPriceUnconfListExcelDown(CusPriceUnconfVo paramVo) throws Exception;

	/**
	 * 매출단가미확정건 출력
	 * @param CusPriceUnconfVo	paramVo
	 * @return List<CusPriceUnconfVo>
	 * @throws Exception
	 */
	public List<CusPriceUnconfVo> selectCusPricePrintList(CusPriceUnconfVo paramVo) throws Exception;


}
