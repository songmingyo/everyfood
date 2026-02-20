package com.doppio.workplace.sm.service.impl;

import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;

@Repository(value = "cusSalesEstRegMapper")
public interface CusSalesEstRegMapper {

	
	/**
	 * @Method : selectCusSalesEstPrdtListCount
	 * @Description : 매출 견적서 품목 COUNT 조회 
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int selectCusSalesEstPrdtListCount(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstList
	 * @Description : 매출 견적서 품목 목록 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	public List<CusSalesEstRegVo> selectCusSalesEstPrdtList(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstHeadListCount
	 * @Description : 매출 견적서 Head 목록 COUNT 조회 
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int selectCusSalesEstHeadListCount(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstHeadList
	 * @Description : 매출 견적서 Head 목록 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	CusSalesEstRegVo selectCusSalesEstHeadList(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstItemListCount
	 * @Description : 매출 견적서 Item 목록 COUNT 조회 
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int selectCusSalesEstItemListCount(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstItemList
	 * @Description : 매출 견적서 Item 목록 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	public List<CusSalesEstRegVo> selectCusSalesEstItemList(CusSalesEstRegVo paramVo);
	
	
	
	/**
	 * @Method : selectCusSalesEstItemList
	 * @Description : 매출 견적서 Head 저장 
	 * @param CusSalesEstRegVo param
	 * @return int
	 */
	public int insertEstHead(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstItemList
	 * @Description : 매출 견적서 Head 수정
	 * @param CusSalesEstRegVo param
	 * @return int
	 */
	public int updateEstHead(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstItemList
	 * @Description : 매출 견적서 Item 저장 
	 * @param CusSalesEstRegVo param
	 * @return int
	 */
	public int insertEstItem(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectCusSalesEstItemList
	 * @Description : 매출 견적서 Item 수정
	 * @param CusSalesEstRegVo param
	 * @return int
	 */
	public int updateEstItem(CusSalesEstRegVo paramVo);
	
	
	/**
	 * @Method : selectCurSalesEstPrintList
	 * @Description : 내부견적서 출력 조회 
	 * @param CusSalesEstRegVo param
	 * @return List<CusSalesEstRegVo>
	 */
	public List<CusSalesEstRegVo> selectCurSalesEstPrintList(CusSalesEstRegVo paramVo);
	
}
