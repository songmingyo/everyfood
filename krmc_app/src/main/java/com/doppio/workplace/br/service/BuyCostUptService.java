package com.doppio.workplace.br.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 매입 단가 일괄 수정 Service interface
 * @Class : BuyCostUptVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface BuyCostUptService {

	/**
	 * 매입주 발주 조회
	 * @param CusPriceUptVo	paramVo
	 * @return List<BuyCostUptVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyCostUptList(BuyCostUptVo paramVo) throws Exception;
	
	/**
	 * 매입처 발주 정보 저장
	 * @param CusPriceUptVo	paramVo
	 * @return BuyCostUptVo
	 * @throws Exception
	 */
	Result updateBuyCostUpt(BuyCostUptVo paramVo) throws Exception;
	
}

