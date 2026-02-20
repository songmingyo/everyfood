package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlBuyMonPrftListVo;


/**
 *
 * @Class : AnlBuyMonPrftListMapper.java
 * @Description :매입처월별이익현황 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 6. 24.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "anlBuyMonPrftListMapper")
public interface AnlBuyMonPrftListMapper {
	
	
	/**
	 * @Method : selectAnlBuyMonPrftList
	 * @Description : 매입처월별이익현황 조회 
	 * @param AnlBuyMonPrftListVo param
	 * @return List<AnlBuyMonPrftListVo>
	 */
	public List<AnlBuyMonPrftListVo> selectAnlBuyMonPrftList(AnlBuyMonPrftListVo paramVo);
	
	
	/**
	 * @Method : selectAnlBuyMonPrftListExcelDown
	 * @Description : 매입처월별이익현황 조회 
	 * @param AnlBuyMonPrftListVo param
	 * @return List<AnlBuyMonPrftListVo>
	 */
	public List<HashMap<String, String>> selectAnlBuyMonPrftListExcelDown(AnlBuyMonPrftListVo paramVo) throws SQLException;
	

}
