package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doppio.common.model.Result;

public interface CusOrdRegService {
	
	
	
	
	
	
	/**
	 * 매출처발주상품 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	List<CusOrdRegVo> selectCusOrdProdList(CusOrdRegVo paramVo) throws Exception;
	
	

	/**
	 * 매출처발주상품 저장
	 * @param ClsDayStkRegVo	paramVo
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	Result updateCusOrdProdList(CusOrdRegVo paramVo) throws Exception;
	
	
	
	/**
	 * 매출처발주상품 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return CusOrdRegVo
	 * @throws Exception
	 */
	CusOrdRegVo selectCusOrdProdData(CusOrdRegVo paramVo) throws Exception;
	
	
	/**
	 * 매출처발주 정보 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return  Map<String,List<CusOrdRegVo> >
	 * @throws Exception
	 */
	HashMap<String,Object> selectOrdSlipList(CusOrdRegVo paramVo) throws Exception ;
	

	/**
	*  매출처발주등록 상세  현황 
	* @param ClsDayStkRegVo paramVo
	* @return List<CusOrdRegVo>
	* @throws Exception
	*/
	List<CusOrdRegVo> selectOrdProdList(CusOrdRegVo paramVo) throws Exception;
	
	
	/**
	 * 매출처발주 내역 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	List<CusOrdRegVo> selectOrdPrdtList(CusOrdRegVo paramVo) throws Exception;
	
	/**
	 * 매출처발주 내역 조회 (PC)
	 * @param ClsDayStkRegVo	paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	List<CusOrdRegVo> selectOrdList(CusOrdRegVo paramVo) throws Exception;
	
	/**
	 * 매출처발주등록 하나의 품목만 조회  (PC)
	 * @param ClsDayStkRegVo	paramVo
	 * @return 
	 * @throws Exception
	 */
	HashMap<String,Object>  selectCusOrdRegPrdtAddSearch(CusOrdRegVo paramVo) throws Exception;
	
	/**
	 * 매출처발주상품 저장 (PC)
	 * @param ClsDayStkRegVo	paramVo
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	Result updateCusOrdList(CusOrdRegVo paramVo) throws Exception;
	
}
