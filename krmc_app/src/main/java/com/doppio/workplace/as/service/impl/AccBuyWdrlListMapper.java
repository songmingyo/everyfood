package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AccBuyWdrlListVo;


/**
 *
 * @Class : BuyWdrlListMapper.java
 * @Description : 매입처별지급현황 현황
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

@Repository(value = "accBuyWdrlListMapper")
public interface AccBuyWdrlListMapper {
	
	
	/**
	 * @Method : selectAccBuyWdrlListCount
	 * @Description : 매출처별입금현황 PAGE COUNT 조회 
	 * @param AccBuyWdrlListVo param
	 * @return INT
	 */
	public int selectAccBuyWdrlListCount(AccBuyWdrlListVo paramVo);
	
	/**
	 * @Method : selectBuyWdrlList
	 * @Description : 매입처별지급현황 조회 
	 * @param AccBuyWdrlListVo param
	 * @return List<AccBuyWdrlListVo>
	 */
	public List<AccBuyWdrlListVo> selectAccBuyWdrlList(AccBuyWdrlListVo paramVo);
	
	/**
	 * @Method : selectAccBuyWdrlListExcelDown
	 * @Description :  매입처별지급현황 엑셀 다운로드 
	 * @param AccBuyWdrlListVo param
	 * @return List<AccBuyWdrlListVo>
	 */                     
	public List<HashMap<String, String>> selectAccBuyWdrlListExcelDown(AccBuyWdrlListVo paramVo) throws SQLException;

}
