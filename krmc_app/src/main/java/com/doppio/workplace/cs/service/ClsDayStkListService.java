package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.workplace.bm.service.PrdtMgmtVo;

/**
 * @author Song
 * @Description : 일재고 조회 Service interface
 * @Class : ClsDayStkListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface ClsDayStkListService {

	/**
	 * 일재고 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsDayStkListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClsDayStkList(ClsDayStkListVo paramVo) throws Exception;
	

	/**
	 * 일재고 출력
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsDayStkListVo>
	 * @throws Exception
	 */
	public List<ClsDayStkListVo> selectStkListPrint(ClsDayStkListVo paramVo) throws Exception;

	
	/**
	 * 일재고 엑셀 다운로드
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsDayStkListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectClsDayStkListExcel(ClsDayStkListVo paramVo) throws Exception;
	
	/**
	 * 품목별 당월, 전월, 전전월 출고량
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public ClsDayStkListVo selPrdtQtyMonth(ClsDayStkListVo paramVo) throws Exception;
}

