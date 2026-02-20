package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.as.service.SalPrdtPrftListVo;

/**
 * @author j10000
 * @Description : 매입처입고현황 관리 Service interface
 * @Class : BuyStoreService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.03.31 j10000
 * </pre>
 * @version : 1.0
 */

public interface BuyRcptListService {

	/**
	 * 매입처입고현황 조회
	 * @param BuyRcptListVo	paramVo
	 * @return List<BuyRcptListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyRcptList(BuyRcptListVo paramVo) throws Exception;
	

	/**
	 * 매입처입고현황 상세조회 
	 * @param BuyRcptListVo	paramVo
	 * @return BuyRcptListVo
	 * @throws Exception
	 */
	BuyRcptListVo selectBuyRcptListData(BuyRcptListVo paramVo) throws Exception;

	/**
	 * 품목별이익현황 Footer 
	 * @param BuyRcptListVo	paramVo
	 * @return BuyRcptListVo
	 * @throws Exception
	 */
	BuyRcptListVo selectBuyRcptListFooter(BuyRcptListVo paramVo) throws Exception;
	
	/**
	 * 매입처입고현황  엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyRcptListExcelDown(BuyRcptListVo paramVo) throws Exception;
	
	/**
	 * 매입처입고현황 출력
	 * @param BuyRcptListVo	paramVo
	 * @return List<BuyRcptListVo>
	 * @throws Exception
	 */
	public List<BuyRcptListVo> selectBuyRcptPrintList(BuyRcptListVo paramVo) throws Exception;

}
