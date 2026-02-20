package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyPeriodListVo;


/**
 *
 * @Class : BuyPeriodListMapper.java
 * @Description : 기간별 매입 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "BuyPeriodListMapper")
public interface BuyPeriodListMapper {


	/**
	 * @Method : selectBuyPeriodListCount
	 * @Description : 기간별 매입 PAGE COUNT 조회 
	 * @param BuyOrderVo param
	 * @return INT
	 */
	public int selectBuyPeriodListCount(BuyPeriodListVo paramVo);
	
	/**
	 * @Method : selectBuyPeriodList
	 * @Description : 기간별 매입 조회 
	 * @param BuyStoreVo param
	 * @return List<BuyPeriodListVo>
	 */
	public List<BuyPeriodListVo> selectBuyPeriodList(BuyPeriodListVo paramVo);
	
	/**
	 * @Method : selectBuyPeriodListData
	 * @Description : 기간별 매입 상세조회 
	 * @param CusOrdListVo param
	 * @return BuyPeriodListVo
	 */
	public BuyPeriodListVo selectBuyPeriodListData(BuyPeriodListVo paramVo);
	/**
	 * @Method : selectBuyOrdListExcelDown
	 * @Description :  기간별 매입 엑셀 다운로드 
	 * @param BuyOrdListVo param
	 * @return List<BuyOrdListVo>
	 */                     
	public List<HashMap<String, String>> selectBuyPeriodListExcelDown(BuyPeriodListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuyPeriodPrtList
	 * @Description : 기간별 매입 발주내역 출력 조회 
	 * @param BuyPeriodListVo param
	 * @return List<BuyPeriodListVo>
	 */
	public List<BuyPeriodListVo> selectBuyPeriodPrtList(BuyPeriodListVo paramVo);
	
}
