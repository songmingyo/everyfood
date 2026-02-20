package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 센터이동 등록 관리 Service interface
 * @Class : LogWhMvRegVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface LogWhMvRegService {

	/**
	 * 센터이동 등록 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<LogWhMvRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectLogWhMvRegList(LogWhMvRegVo paramVo) throws Exception;
	
	/**
	 * 센터이동 등록 단일 품목 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<LogWhMvRegVo>
	 * @throws Exception
	 */
	HashMap<String,Object>  selectLogWhMvRegPrdtAddList(LogWhMvRegVo paramVo) throws Exception;
	
	/**
	 * 매입처 발주 정보 저장
	 * @param SalSalesGoalListVo	paramVo
	 * @return LogWhMvRegVo
	 * @throws Exception
	 */
	Result insertLogWhMvReg(LogWhMvRegVo paramVo) throws Exception;
	
}

