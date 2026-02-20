package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 물류창고 Service interface
 * @Class : LogisticsWarehouseService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface LogisticsWarehouseService {

	
	

	/**
	 * 물류창고 정보 조회
	 * @param LogisticsWarehouseVo	paramVo
	 * @return List<LogisticsWarehouseVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectLogisticsWarehouseList(LogisticsWarehouseVo paramVo) throws Exception;

	
	/**
	 * 물류창고 정보 저장
	 * @param LogisticsWarehouseVo	paramVo
	 * @return List<LogisticsWarehouseVo>
	 * @throws Exception
	 */
	public Result insertLogisticsWarehouseInfo(HttpServletRequest request, LogisticsWarehouseVo paramVo) throws Exception;
	
	
}
