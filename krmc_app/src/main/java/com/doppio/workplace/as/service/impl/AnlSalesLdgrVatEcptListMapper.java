package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlSalesLdgrVatEcptListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description : 매출처원장(연간) 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 20.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "anlSalesLdgrVatEcptListMapper")
public interface AnlSalesLdgrVatEcptListMapper {
	
	
	/**
	 * @Method : selectAnlSalesLdgrVatEcptListCount
	 * @Description : 매출처원장(연간) PAGE COUNT 조회 
	 * @param AnlSalesLdgrVatEcptListVo param
	 * @return INT
	 */
	public int selectAnlSalesLdgrVatEcptListCount(AnlSalesLdgrVatEcptListVo paramVo);
	
	/**
	 * @Method : selectAnlSalesLdgrVatEcptList
	 * @Description : 매출처원장(연간) 조회 
	 * @param AnlSalesLdgrVatEcptListVo param
	 * @return List<AnlSalesLdgrVatEcptListVo>
	 */
	public List<AnlSalesLdgrVatEcptListVo> selectAnlSalesLdgrVatEcptList(AnlSalesLdgrVatEcptListVo paramVo);
	
	/**
	 * @Method : selectAnlSalesLdgrVatEcptListExcelDown
	 * @Description :  매출처원장(연간) 엑셀 다운로드 
	 * @param AnlSalesLdgrVatEcptListVo param
	 * @return List<AnlSalesLdgrVatEcptListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlSalesLdgrVatEcptListExcelDown(AnlSalesLdgrVatEcptListVo paramVo) throws SQLException;

}
