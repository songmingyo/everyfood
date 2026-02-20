package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.bm.service.BuyMgmtVo;
import com.doppio.workplace.br.service.BuyInspectListVo;
import com.doppio.workplace.br.service.BuyOrdListVo;


/**
 *
 * @Class : BuyMgmtMapper.java
 * @Description : 매입처마스터 관리 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 19.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "BuyOrdListMapper")
public interface BuyOrdListMapper {


	/**
	 * @Method : selectBuyMgmtListCount
	 * @Description : 매입처관리 PAGE COUNT 조회 
	 * @param BuyOrdListVo param
	 * @return INT
	 */
	public int selectBuyOrderListCount(BuyOrdListVo paramVo);
	
	/**
	 * @Method : selectBuyMgmtList
	 * @Description : 매입처관리 조회 
	 * @param BuyOrdListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<BuyOrdListVo> selectBuyOrderList(BuyOrdListVo paramVo);
	
	/**
	 * @Method : selectBuyMgmtList
	 * @Description : 매입처 상세조회 
	 * @param BuyOrdListVo param
	 * @return BuyMgmtVo
	 */
	public BuyOrdListVo selectBuyOrderData(BuyOrdListVo paramVo);
	/**
	 * @Method : selectBuyOrdListExcelDown
	 * @Description :  매입처 엑셀 다운로드 
	 * @param BuyOrdListVo param
	 * @return List<BuyOrdListVo>
	 */                     
	public List<HashMap<String, String>> selectBuyOrdListExcelDown(BuyOrdListVo paramVo) throws SQLException;
}
