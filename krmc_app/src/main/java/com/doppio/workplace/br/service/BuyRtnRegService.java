package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;
import com.doppio.workplace.sm.service.CusRtnListVo;

/**
 * @author Song
 * @Description : 매입처 반품 관리 Service interface
 * @Class : BuyRtnRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface BuyRtnRegService {

	/**
	 * 매입처 입고 목록 조회
	 * @param BuyRtnRegVo	paramVo
	 * @return List<BuyRtnRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyRtnRegList(BuyRtnRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 입고 상세 조회
	 * @param BuyRtnRegVo	paramVo
	 * @return List<BuyRtnRegVo>
	 * @throws Exception
	 */
	List<BuyRtnRegVo>  selectBuyRtnDetailList(BuyRtnRegVo paramVo) throws Exception;
	
	/**
	 * 매입주 발주 단일 품목 조회
	 * @param BuyRtnRegVo	paramVo
	 * @return List<BuyRtnRegVo>
	 * @throws Exception
	 */
	HashMap<String,Object>  selectBuyRtnRegPrdtAddList(BuyRtnRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 발주 정보 저장
	 * @param BuyRtnRegVo	paramVo
	 * @return BuyRtnRegVo
	 * @throws Exception
	 */
	Result insertBuyRtnReg(BuyRtnRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 반품현황 add by song min kyo 2025.08.26
	 * @param BuyRtnRegVo	paramVo
	 * @return BuyRtnRegVo
	 * @throws Exception
	 */
	HashMap<String, Object>  selectBuyRtnList_2(BuyRtnRegVo paramVo) throws Exception;
	
	
	/**
	 * 매입처 반품현황 excel add by song min kyo 2025.08.26
	 * @param BuyRtnRegVo	paramVo
	 * @return BuyRtnRegVo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyRtnListExcelDown(BuyRtnRegVo paramVo) throws Exception;
}
