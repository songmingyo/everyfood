package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.PrdtMgmtVo;

/**
 * @author j10000
 * @Description : 매입처입고현황 관리 Service interface
 * @Class : BuyConfirmService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.04.04 j10000
 * </pre>
 * @version : 1.0
 */

public interface BuyInspectListService {

	/**
	 * 매입검수확인서 조회
	 * @param BuyInspectListVo	paramVo
	 * @return List<BuyConfirmVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyConfirmList(BuyInspectListVo paramVo) throws Exception;
	

	/**
	 * 매입검수확인서 출력
	 * @param BuyInspectListVo	paramVo
	 * @return List<BuyInspectListVo>
	 * @throws Exception
	 */
	public List<BuyInspectListVo> selectBuyConfirmPrt(BuyInspectListVo paramVo) throws Exception;

	/**
	 * 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyInspectListExcelDown(BuyInspectListVo paramVo) throws Exception;
	
}
