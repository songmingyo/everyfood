package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 매출 단가 일괄 수정 Service interface
 * @Class : CusPriceUptVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface CusPriceUptService {

	/**
	 * 매출주 발주 조회
	 * @param CusPriceUptVo	paramVo
	 * @return List<CusPriceUptVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectCusPriceUptList(CusPriceUptVo paramVo) throws Exception;
	
	/**
	 * 매출처 발주 정보 저장
	 * @param CusPriceUptVo	paramVo
	 * @return CusPriceUptVo
	 * @throws Exception
	 */
	Result updateCusPriceUpt(CusPriceUptVo paramVo) throws Exception;
	
}

