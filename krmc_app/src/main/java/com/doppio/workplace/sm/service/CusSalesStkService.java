package com.doppio.workplace.sm.service;

import java.util.HashMap;

/**
 *
 * @Class : CusShipRegService.java
 * @Description : 매출처재고 현황 
 * @author : dada
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 14.      dada        	  
 *
 * </pre>
 */

public interface CusSalesStkService {
	
	/**
	 * @Method : selectSalesStkList
	 * @Description : 매입처 재고  정보조회 
	 * @param CusSalesStkVo  paramVo
	 * @return Map<String,List<CusSalesStkVo> >
	 * @throws Exception
	 */
	HashMap<String,Object> selectSalesStkList(CusSalesStkVo paramVo) throws Exception ;

	
	/**
	 * @Method : selectPrdtSearch
	 * @Description : 상품정보 조회 
	 * @param CusSalesStkVo  paramVo
	 * @return CusSalesStkVo
	 * @throws Exception
	 */
	CusSalesStkVo selectPrdtSearch(CusSalesStkVo paramVo) throws Exception ;
	
	
}
