package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlAccRecvListVo;
import com.doppio.workplace.as.service.AnlBuyLdgrDtListVo;


/**
 *
 * @Class : AnlBuyLdgrDtListMapper.java
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

@Repository(value = "anlBuyLdgrDtListMapper")
public interface AnlBuyLdgrDtListMapper {
	

	/**
	 * @Method : selectAnlBuyLdgrDtList
	 * @Description : 매입처원장(일별) 조회 
	 * @param AnlBuyLdgrDtListVo param
	 * @return List<AnlBuyLdgrDtListVo>
	 */
	public List<AnlBuyLdgrDtListVo> selectAnlBuyLdgrDtList(AnlBuyLdgrDtListVo paramVo);
	
	/**
	 * @Method : selectAnlBuyLdgrDtList
	 * @Description : 매입처원장(일별) 출력
	 * @param AnlBuyLdgrDtListVo param
	 * @return List<AnlBuyLdgrDtListVo>
	 */
	public List<AnlBuyLdgrDtListVo> selectbuyLdgrListPrint(AnlBuyLdgrDtListVo paramVo);
	
	
	/**
	 * @Method : selectAnlBuyLdgrDtListExcelDown
	 * @Description :  매입처원장(일별) 엑셀 다운로드 
	 * @param AnlBuyLdgrDtListVo param
	 * @return List<AnlBuyLdgrDtListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlBuyLdgrDtListExcelDown(AnlBuyLdgrDtListVo paramVo) throws SQLException;
}
