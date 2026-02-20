package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 상품코드 관리 Service interface
 * @Class : PrdtMgmtService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface PrdtMgmtService {

	/**
	 * 상품정보 조회
	 * @param PrdtMgmtVo	paramVo
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectPrdtMgmtList(PrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 상품코드 상세조회 
	 * @param PrdtMgmtVo	paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	PrdtMgmtVo selectPrdtMgmtData(PrdtMgmtVo paramVo) throws Exception;

	
	/**
	 * 상품최근입고 조회 
	 * @param PrdtMgmtVo	paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	HashMap<String, Object> selectPrdtBuyList(PrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 상품최근출고 조회 
	 * @param PrdtMgmtVo	paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	HashMap<String, Object> selectPrdtSalesList(PrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 상품 정보 저장
	 * @param PrdtMgmtVo	paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	Result insertPrdtMgmt(PrdtMgmtVo paramVo) throws Exception;
	
	/**
	 * 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectPrdtMgmtListExcelDown(PrdtMgmtVo paramVo) throws Exception;
	
	
}
