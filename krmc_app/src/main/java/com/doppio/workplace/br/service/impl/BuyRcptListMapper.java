package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptListVo;


/**
 *
 * @Class : BuyStoreMapper.java
 * @Description : 매입처입고조회
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 31.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "BuyRcptListMapper")
public interface BuyRcptListMapper {


	/**
	 * @Method : selectBuyStoreListCount
	 * @Description : 매입처관리 PAGE COUNT 조회 
	 * @param BuyOrderVo param
	 * @return INT
	 */
	public int selectBuyRcptListCount(BuyRcptListVo paramVo);
	
	/**
	 * @Method : selectBuyStoreList
	 * @Description : 매입처관리 조회 
	 * @param CusOrdListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<BuyRcptListVo> selectBuyRcptList(BuyRcptListVo paramVo);
	
	/**
	 * @Method : selectBuyStoreData
	 * @Description : 매입처 상세조회 
	 * @param CusOrdListVo param
	 * @return BuyStoreVo
	 */
	public BuyRcptListVo selectBuyRcptListData(BuyRcptListVo paramVo);

	/**
	 * @Method : selectBuyRcptListFooter
	 * @Description : 매입처 조회 
	 * @param selectBuyRcptListFooter param
	 * @return List<BuyRcptListVo>
	 */
	public BuyRcptListVo selectBuyRcptListFooter(BuyRcptListVo paramVo);
	
	/**
	 * @Method : selectBuyRcptListExcelDown
	 * @Description :  매입처 엑셀 다운로드 
	 * @param BuyRcptListVo param
	 * @return List<BuyRcptListVo>
	 */                     
	public List<HashMap<String, String>> selectBuyRcptListExcelDown(BuyRcptListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuyRcptPrtList
	 * @Description : 매입처 입고내역 출력 조회 
	 * @param BuyRcptListVo param
	 * @return List<BuyRcptListVo>
	 */
	public List<BuyRcptListVo> selectBuyRcptPrintList(BuyRcptListVo paramVo);
}
