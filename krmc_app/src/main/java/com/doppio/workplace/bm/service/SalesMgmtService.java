package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;

/**
 * @author j10000
 * @Description : 매출처코드 관리 Service interface
 * @Class : SalesMgmtService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.03.15 j10000
 * </pre>
 * @version : 1.0
 */

public interface SalesMgmtService {

	/**
	 * 매출처정보 조회
	 * @param SalesMgmtVo	paramVo
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalesMgmtList(SalesMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 매출처정보 상세조회 
	 * @param SalesMgmtVo	paramVo
	 * @return SalesMgmtVo
	 * @throws Exception
	 */
	SalesMgmtVo selectSalesMgmtData(SalesMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 매출처정보 정보 저장
	 * @param SalesMgmtVo	paramVo
	 * @return SalesMgmtVo
	 * @throws Exception
	 */
	public Result insertSalesMgmt(HttpServletRequest request, SalesMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 매출처정보 엑셀 다운로드
	 * @param SalesMgmtVo	paramVo
	 * @return List<SalesMgmtVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalesMgmtListExcelDown(SalesMgmtVo paramVo) throws Exception;


}
