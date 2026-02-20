package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.PrdtMgmtVo;

/**
 * @author Song
 * @Description : 일재고 관리 Service interface
 * @Class : ClsDayStkRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface ClsDayStkRegService {

	/**
	 * 일재고 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return List<ClsDayStkRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClsDayStkRegList(ClsDayStkRegVo paramVo) throws Exception;
	
	
	/**
	 * 일재고 조회 footer
	 * @param selectDayStkRegFooter	paramVo
	 * @return List<ClsDayStkRegVo>
	 * @throws Exception
	 */
	ClsDayStkRegVo selectDayStkRegFooter(ClsDayStkRegVo paramVo) throws Exception;
	
	
	/**
	 * 일재고 정보 저장
	 * @param ClsDayStkRegVo	paramVo
	 * @return ClsDayStkRegVo
	 * @throws Exception
	 */
	Result insertClsDayStkReg(ClsDayStkRegVo paramVo) throws Exception;
	

	
	/**
	 * 일재고 엑셀 다운로드
	 * @param ClsDayStkRegVo	paramVo
	 * @return List<ClsDayStkRegVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectClsDayStkRegListExcel(ClsDayStkRegVo paramVo) throws Exception;
	
	
	/**
	 * 일재고 출력 
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<ClsDayStkRegVo>
	 * @throws Exception
	 */
	public List<ClsDayStkRegVo> selectStkInspPrintList(ClsDayStkRegVo paramVo) throws Exception;
}

