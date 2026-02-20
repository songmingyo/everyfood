package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 *
 * @Class : AccSalesFirmbankingListService.java
 * @Description : 펌뱅킹입금현황 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 14.      j10000        	  
 *
 * </pre>
 */

public interface AccSalesFirmbankingListService {

	/**
	 * 펌뱅킹입금현황 조회
	 * @param AccSalesFirmbankingListVo	paramVo
	 * @return List<AccSalesDepListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAccSalesFirmbankingList(AccSalesFirmbankingListVo paramVo) throws Exception;
	


	/**
	 * 펌뱅킹입금현황 저장
	 * @param AccSalesFirmbankingListVo	paramVo
	 * @return List<AccSalesFirmbankingListVo>
	 * @throws Exception
	 */
	public Result insertAccSalesFirmbankingRegInfo(AccSalesFirmbankingListVo paramVo) throws Exception;


}
