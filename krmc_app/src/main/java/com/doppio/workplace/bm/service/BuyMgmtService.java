package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;

/**
 * @author j10000
 * @Description : 매입처코드 관리 Service interface
 * @Class : BuyMgmtService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.03.17 j10000
 * </pre>
 * @version : 1.0
 */

public interface BuyMgmtService {

	/**
	 * 매입처정보 조회
	 * @param SalesMgmtVo	paramVo
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectBuyMgmtList(BuyMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 매입처정보 정보 저장
	 * @param BuyOrderVo	paramVo
	 * @return BuyMgmtVo
	 * @throws Exception
	 */
	public Result insertBuyMgmt(HttpServletRequest request, BuyMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 매입처정보 상세조회 
	 * @param BuyOrderVo	paramVo
	 * @return BuyMgmtVo
	 * @throws Exception
	 */
	BuyMgmtVo selectBuyMgmtData(BuyMgmtVo paramVo) throws Exception;
	
	/**
	 * 매출처별입금현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectBuyMgmtListExcelDown(BuyMgmtVo paramVo) throws Exception;
}
