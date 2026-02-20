package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.workplace.sm.service.CusShipAgrclListVo;

/**
 *
 * @Class : AnlCreditSalesListService.java
 * @Description : 외상매출금(상세) 
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

public interface AnlCreditSalesListService {
	
	
	

	/**
	 * 외상매출금(상세) 조회
	 * @param AnlCreditSalesListVo	paramVo
	 * @return List<AnlCreditSalesListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlCreditSalesList(AnlCreditSalesListVo paramVo) throws Exception;
	


	/**
	 * 외상매출금(상세) 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlCreditSalesListExcelDown(AnlCreditSalesListVo paramVo) throws Exception;

	
	/**
	 * 외상매출금(상세) 출력
	 * @param AnlCreditSalesListVo	paramVo
	 * @return List<AnlCreditSalesListVo>
	 * @throws Exception
	 */
	public List<AnlCreditSalesListVo> selectAnlCreditPrintList(AnlCreditSalesListVo paramVo) throws Exception;

}
