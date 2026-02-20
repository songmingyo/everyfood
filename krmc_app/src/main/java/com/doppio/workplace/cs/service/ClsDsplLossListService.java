package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Song
 * @Description : 폐기로스 조회 Service interface
 * @Class : ClsDsplLossListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface ClsDsplLossListService {

	/**
	 * 폐기로스 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsDsplLossListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClsDsplLossList(ClsDsplLossListVo paramVo) throws Exception;
	

	/**
	 * 폐기로스 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsDsplLossListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectClsDsplLossListExcel(ClsDsplLossListVo paramVo) throws Exception;
}

