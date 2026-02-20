package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.PrdtMgmtVo;

/**
 * @author Song
 * @Description : 매입주 발주 관리 Service interface
 * @Class : BuyOrderRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface BuyOrderRegService {

	/**
	 * 매입주 발주 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyOrderRegList(BuyOrderRegVo paramVo) throws Exception;
	
	/**
	 * 매입주 발주 단일 품목 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	HashMap<String,Object>  selectBuyOrderRegPrdtAddList(BuyOrderRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 발주 정보 저장
	 * @param SalSalesGoalListVo	paramVo
	 * @return BuyOrderRegVo
	 * @throws Exception
	 */
	Result insertBuyOrderReg(BuyOrderRegVo paramVo) throws Exception;
	
	
	/**
	 * 매입처 발주 비고 정보 저장
	 * @param SalSalesGoalListVo	paramVo
	 * @return BuyOrderRegVo
	 * @throws Exception
	 */
	Result insertBuyOrdNoteReg(BuyOrderRegVo paramVo) throws Exception;
	
	/**
	 * 매입주 발주서 출력 건수
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	Result selectBuyOrderListPrintCnt(BuyOrderRegVo paramVo) throws Exception;
	
	/**
	 * 매입주 발주 발주서 출력
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	public List<BuyOrderRegVo> selectOrdPrintList(BuyOrderRegVo paramVo) throws Exception;

	
	/**
	 * 매입주 발주 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyOrdRegListExcelDown(BuyOrderRegVo paramVo) throws Exception;
	
	/**
	 * 매입주 발주 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<BuyOrderRegVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyOrdRegNotListExcelDown(BuyOrderRegVo paramVo) throws Exception;
}

