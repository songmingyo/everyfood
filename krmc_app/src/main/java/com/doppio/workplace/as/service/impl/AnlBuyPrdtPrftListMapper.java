package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AccSalesDepListVo;
import com.doppio.workplace.as.service.AnlBuyPrdtPrftListVo;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;


/**
 *
 * @Class : AnlBuyPrdtPrftListMapper.java
 * @Description :매입처품목별이익현황
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

@Repository(value = "anlBuyPrdtPrftListMapper")
public interface AnlBuyPrdtPrftListMapper {
	
	
	/**
	 * @Method : selectAnlBuyPrdtPrftList
	 * @Description : 매입처품목별이익현황
	 * @param AnlBuyPrdtPrftListVo param
	 * @return List<AnlBuyPrdtPrftListVo>
	 */
	public List<AnlBuyPrdtPrftListVo> selectAnlBuyPrdtPrftList(AnlBuyPrdtPrftListVo paramVo);
	
	/**
	 * @Method : selectAnlBuyPrdtPrftFooter
	 * @Description : 매입처품목별이익현황
	 * @param AnlBuyPrdtPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public AnlBuyPrdtPrftListVo selectAnlBuyPrdtPrftFooter(AnlBuyPrdtPrftListVo paramVo);
	
	/**
	 * @Method : selectAnlBuyPrdtPrftListExcelDown
	 * @Description :  매입처품목별이익현황 엑셀 다운로드 
	 * @param AccSalesDepListVo param
	 * @return List<AccSalesDepListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlBuyPrdtPrftListExcelDown(AnlBuyPrdtPrftListVo paramVo) throws SQLException;

}
