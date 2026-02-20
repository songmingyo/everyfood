package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlSalesLdgrDtListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description : 매출처원장(일별) 현황
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

@Repository(value = "anlSalesLdgrDtListMapper")
public interface AnlSalesLdgrDtListMapper {
	
	
	/**
	 * @Method : selectAnlSalesLdgrDtListCount
	 * @Description : 매출처원장(일별) PAGE COUNT 조회 
	 * @param AnlSalesLdgrDtListVo param
	 * @return INT
	 */
	public int selectAnlSalesLdgrDtListCount(AnlSalesLdgrDtListVo paramVo);
	
	/**
	 * @Method : selectAnlSalesLdgrDtList
	 * @Description : 매출처원장(일별) 조회 
	 * @param AnlSalesLdgrDtListVo param
	 * @return List<AnlSalesLdgrDtListVo>
	 */
	public List<AnlSalesLdgrDtListVo> selectAnlSalesLdgrDtList(AnlSalesLdgrDtListVo paramVo);
	
	/**
	 * @Method : selectAnlSalesLdgrDtListExcelDown
	 * @Description :  매출처원장(일별) 엑셀 다운로드 
	 * @param AnlSalesLdgrDtListVo param
	 * @return List<AnlSalesLdgrDtListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlSalesLdgrDtListExcelDown(AnlSalesLdgrDtListVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectAnlSalesLdgrDtPrintList
	 * @Description : 매출처원장(일별) 출력
	 * @param AnlSalesLdgrDtListVo param
	 * @return List<AnlSalesLdgrDtListVo>
	 */
	public List<AnlSalesLdgrDtListVo> selectAnlSalesLdgrDtPrintList(AnlSalesLdgrDtListVo paramVo);

}
