package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlSalesLdgrMonListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description : 매출처원장(월별) 현황
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

@Repository(value = "anlSalesLdgrMonListMapper")
public interface AnlSalesLdgrMonListMapper {
	
	
	/**
	 * @Method : selectAnlSalesLdgrMonListCount
	 * @Description : 매출처원장(월별) PAGE COUNT 조회 
	 * @param AnlSalesLdgrMonListVo param
	 * @return INT
	 */
	public int selectAnlSalesLdgrMonListCount(AnlSalesLdgrMonListVo paramVo);
	
	/**
	 * @Method : selectAnlSalesLdgrMonList
	 * @Description : 매출처원장(월별) 조회 
	 * @param AnlSalesLdgrMonListVo param
	 * @return List<AnlSalesLdgrMonListVo>
	 */
	public List<AnlSalesLdgrMonListVo> selectAnlSalesLdgrMonList(AnlSalesLdgrMonListVo paramVo);
	
	/**
	 * @Method : selectAnlSalesLdgrMonListExcelDown
	 * @Description :  매출처원장(월별) 엑셀 다운로드 
	 * @param AnlSalesLdgrMonListVo param
	 * @return List<AnlSalesLdgrMonListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlSalesLdgrMonListExcelDown(AnlSalesLdgrMonListVo paramVo) throws SQLException;

}
