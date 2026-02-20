package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlBuyLdgrDtListVo;
import com.doppio.workplace.as.service.AnlBuyLdgrMonListVo;


/**
 *
 * @Class : AnlBuyLdgrMonListMapper.java
 * @Description :매입처원장(일별) 현황
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

@Repository(value = "anlBuyLdgrMonListMapper")
public interface AnlBuyLdgrMonListMapper {
	
	
	/**
	 * @Method : selectAnlBuyLdgrMonList
	 * @Description : 매입처원장(년간) 조회 
	 * @param AnlBuyLdgrMonListVo param
	 * @return List<AnlBuyLdgrMonListVo>
	 */
	public List<AnlBuyLdgrMonListVo> selectAnlBuyLdgrMonList(AnlBuyLdgrMonListVo paramVo);
	
	/**
	 * @Method : selectBuyLdgrMonListPrint
	 * @Description : 매입처원장(년간) 출력
	 * @param AnlBuyLdgrMonListVo param
	 * @return List<AnlBuyLdgrMonListVo>
	 */
	public List<AnlBuyLdgrMonListVo> selectBuyLdgrMonListPrint(AnlBuyLdgrMonListVo paramVo);
	
	/**
	 * @Method : selectAnlBuyLdgrMonListExcelDown
	 * @Description :  매입처원장(년간) 엑셀 다운로드 
	 * @param AnlBuyLdgrMonListVo param
	 * @return List<AnlBuyLdgrMonListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlBuyLdgrMonListExcelDown(AnlBuyLdgrMonListVo paramVo) throws SQLException;
	

}
