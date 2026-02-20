package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.workplace.bm.service.PrdtMgmtVo;


/**
 * @author Song
 * @Description : 차량별 배송현황표 Service interface
 * @Class : SalSalesCarShipListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface SalSalesCarShipListService {

	/**
	 * 차량별 배송현황표 조회
	 * @param SalSalesGoalListVo	paramVo
	 * @return List<SalSalesCarShipListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectSalSalesCarShipList(SalSalesCarShipListVo paramVo) throws Exception;
	
		
	/**
	 * 차량별 배송현황표 엑셀 다운로드
	 * @param SalSalesCarShipListVo	paramVo
	 * @return List<SalSalesCarShipListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectSalSalesCarShipListExcelDown(SalSalesCarShipListVo paramVo) throws Exception;
	
	/**
	 * 차량별 배송현황표 Footer 
	 * @param SalSalesCarShipListVo	paramVo
	 * @return SalSalesCarShipListVo
	 * @throws Exception
	 */
	SalSalesCarShipListVo selectSalSalesCarShipFooter(SalSalesCarShipListVo paramVo) throws Exception;
}

