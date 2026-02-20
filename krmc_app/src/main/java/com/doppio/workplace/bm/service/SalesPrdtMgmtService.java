package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.sm.service.CusRtnListVo;

/**
 * @author Song
 * @Description : 매출처별 상품코드 관리 Service interface
 * @Class : SalesPrdtMgmtVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalesPrdtMgmtService {

	/**
	 * 매출처 상품정보 조회
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalesPrdtMgmtList(SalesPrdtMgmtVo paramVo) throws Exception;
	
	/**
	 * 매출처 상품 정보 저장
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	Result insertSalesPrdtMaster(SalesPrdtMgmtVo paramVo) throws Exception;
	
	/**
	 * 선택된 매출처 품목 삭제
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	Result updateSalesPrdtMasterDataUseFlag(SalesPrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 품목코드 일괄 적용
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	Result updateSalesPrdtMaterPrdtOldNewChg(SalesPrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 매출처 품목 일괄 신규 매출처 복사
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	Result updateSalesPrdtMaterSalesCdOrgNewInsert(SalesPrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * 품목일괄 대상 조회
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalesPrdtMgmtAddList(SalesPrdtMgmtVo paramVo) throws Exception;
	
	/**
	 * 품목일괄 적용
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	Result insertSalesPrdtMaterSalesPrdtAllInsert(SalesPrdtMgmtVo paramVo) throws Exception;
	
	/**
	 * 일괄판매가 수정
	 * @param SalesPrdtMgmtVo	paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	Result updateSalesPrdtMaterSalesPriceUpt(SalesPrdtMgmtVo paramVo) throws Exception;
	
	/**
	 * 매출처반품현황 엑셀 다운로드
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalesPrdtListExcelDown(SalesPrdtMgmtVo paramVo) throws Exception;
	
	
}
