package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;

/**
 * @author j10000
 * @Description : 기간별 매입현황 Service interface
 * @Class : BuyPeriodListService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.03.31 j10000
 * </pre>
 * @version : 1.0
 */

public interface BuyPeriodListService {

	/**
	 * 기간별 매입현황
	 * @param CusOrdListVo	paramVo
	 * @return List<BuyPeriodListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyPeriodList(BuyPeriodListVo paramVo) throws Exception;
	

	/**
	 * 기간별 매입현황 상세조회 
	 * @param CusOrdListVo	paramVo
	 * @return BuyPeriodListVo
	 * @throws Exception
	 */
	BuyPeriodListVo selectBuyPeriodListData(BuyPeriodListVo paramVo) throws Exception;

	/**
	 * 기간별 매입현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyPeriodListExcelDown(BuyPeriodListVo paramVo) throws Exception;
	
	/**
	 * 기간별 매입현황 발주서 출력
	 * @param BuyPeriodListVo	paramVo
	 * @return List<BuyPeriodListVo>
	 * @throws Exception
	 */
	public List<BuyPeriodListVo> selectPeriodPrintList(BuyPeriodListVo paramVo) throws Exception;

	
	
}
