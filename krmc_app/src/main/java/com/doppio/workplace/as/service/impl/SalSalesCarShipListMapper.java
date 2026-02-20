package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesCarShipListVo;


/**
 *
 * @Class : SalSalesCarShipListMapper.java
 * @Description : 차량별 배송현황표
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      song        	  
 *
 * </pre>
 */

@Repository(value = "SalSalesCarShipListMapper")
public interface SalSalesCarShipListMapper {

	
	/**
	 * @Method : selectSalSalesCarShipList
	 * @Description : 차량별 배송현황표 조회 
	 * @param SalSalesCarShipListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesCarShipListVo> selectSalSalesCarShipList(SalSalesCarShipListVo paramVo);
	
	/**
	 * @Method : selectSalSalesCarShipFooter
	 * @Description : 차량별 배송현황표 조회 
	 * @param SalSalesCarShipListVo param
	 * @return List<BuyMgmtVo>
	 */
	public SalSalesCarShipListVo selectSalSalesCarShipFooter(SalSalesCarShipListVo paramVo);
	
	/**
	 * @Method : selectSalSalesCarShipListListExcelDown
	 * @Description : 차량별 배송현황표 엑셀 다운로드
	 * @param SalSalesCarShipListVo param
	 * @return List<SalSalesCarShipListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesCarShipListExcelDown(SalSalesCarShipListVo paramVo) throws SQLException;
	
}
