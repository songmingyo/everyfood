package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Song
 * @Description : 소비기한관리 관리 Service interface
 * @Class : ClsTermValListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface ClsTermValListService {

	/**
	 * 소비기한관리 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsTermValListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClsTermValList(ClsTermValListVo paramVo) throws Exception;
	
	
	/**
	 * 소비기한관리 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsTermValListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectClsTermValListExcel(ClsTermValListVo paramVo) throws Exception;
}

