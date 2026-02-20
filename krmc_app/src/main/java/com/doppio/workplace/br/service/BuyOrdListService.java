package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;

/**
 * @author j10000
 * @Description : 매입처빌주현황 관리 Service interface
 * @Class : BuyOrderService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.03.25 j10000
 * </pre>
 * @version : 1.0
 */

public interface BuyOrdListService {

	/**
	 * 매입처발주현황 조회
	 * @param BuyOrdListVo	paramVo
	 * @return List<BuyOrderVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyOrderList(BuyOrdListVo paramVo) throws Exception;
	

	/**
	 * 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyOrdListExcelDown(BuyOrdListVo paramVo) throws Exception;
	
}
