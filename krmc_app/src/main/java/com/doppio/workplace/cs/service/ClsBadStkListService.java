package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Song
 * @Description : 악성재고조회 Service interface
 * @Class : ClsBadStkListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface ClsBadStkListService {

	/**
	 * 악성재고조회 조회
	 * @param ClsBadStkListVo	paramVo
	 * @return List<ClsBadStkListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClsBadStkList(ClsBadStkListVo paramVo) throws Exception;
	
	
	/**
	 * 악성재고조회 엑셀 다운로드
	 * @param ClsBadStkListVo	paramVo
	 * @return List<ClsBadStkListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectClsBadStkListExcel(ClsBadStkListVo paramVo) throws Exception;
}

