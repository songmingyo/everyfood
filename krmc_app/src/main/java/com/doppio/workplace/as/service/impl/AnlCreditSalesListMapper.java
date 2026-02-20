package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.sm.service.CusShipAgrclListVo;
import com.doppio.workplace.as.service.AnlCreditSalesListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description : 외상매출금(상세) 현황
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

@Repository(value = "anlCreditSalesListMapper")
public interface AnlCreditSalesListMapper {
	
	
	/**
	 * @Method : selectAnlCreditSalesListCount
	 * @Description : 외상매출금(상세) PAGE COUNT 조회 
	 * @param AnlCreditSalesListVo param
	 * @return INT
	 */
	public int selectAnlCreditSalesListCount(AnlCreditSalesListVo paramVo);
	
	/**
	 * @Method : selectAnlCreditSalesList
	 * @Description : 외상매출금(상세) 조회 
	 * @param AnlCreditSalesListVo param
	 * @return List<AnlCreditSalesListVo>
	 */
	public List<AnlCreditSalesListVo> selectAnlCreditSalesList(AnlCreditSalesListVo paramVo);
	
	/**
	 * @Method : selectAnlCreditSalesList
	 * @Description :  외상매출금(상세) 엑셀 다운로드 
	 * @param AnlCreditSalesListVo param
	 * @return List<AnlCreditSalesListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlCreditSalesListExcelDown(AnlCreditSalesListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectAnlCreditPrintList
	 * @Description : 외상매출금(상세) 내역 출력 조회 
	 * @param CusShipAgrclListVo param
	 * @return List<AnlCreditSalesListVo>
	 */
	public List<AnlCreditSalesListVo> selectAnlCreditPrintList(AnlCreditSalesListVo paramVo);

}
