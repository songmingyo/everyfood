package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 매입처 입고 관리 Service interface
 * @Class : BuyRcptRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface BuyRcptRegService {

	/**
	 * 매입처 입고 목록 조회
	 * @param BuyRcptRegVo	paramVo
	 * @return List<BuyRcptRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyRcptRegList(BuyRcptRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 입고 상세 조회
	 * @param BuyRcptRegVo	paramVo
	 * @return List<BuyRcptRegVo>
	 * @throws Exception
	 */
	List<BuyRcptRegVo>  selectBuyRcptDetailList(BuyRcptRegVo paramVo) throws Exception;
	
	/**
	 * 매입주 발주 단일 품목 조회
	 * @param BuyRcptRegVo	paramVo
	 * @return List<BuyRcptRegVo>
	 * @throws Exception
	 */
	HashMap<String,Object>  selectBuyRcptRegPrdtAddList(BuyRcptRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 발주 정보 저장
	 * @param BuyRcptRegVo	paramVo
	 * @return BuyRcptRegVo
	 * @throws Exception
	 */
	Result insertBuyRcptReg(BuyRcptRegVo paramVo) throws Exception;
	

}
