package com.doppio.workplace.bm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.workplace.bm.service.LogisticsWarehouseService;
import com.doppio.workplace.bm.service.LogisticsWarehouseVo;
import com.doppio.common.model.Result;

/**
 * @author dada
 * @Description : 배송차량관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03. 09.     Song
 * </pre>
 * @version :
 */
@Controller(value = "logisticsWarehouseController")
public class LogisticsWarehouseController {

	private static final Logger logger = LoggerFactory.getLogger(LogisticsWarehouseController.class);
	
	@Autowired
	LogisticsWarehouseService  logisticsWarehouseService;
	
	/**
     *  Init Action
 	* @param
 	* @return
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/logisticsWarehouse")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basLogisticsWarehouse";
 	}
 	
 	

	/**
	 * 창고  조회 Event
	 * @param DlvrMasterVo param
	 * @param HttpServletRequest
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */

	@RequestMapping(value= {"/app/bm/baseinfo/logisticsWarehouse_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectLogisticsWarehouseSearch(LogisticsWarehouseVo param, HttpServletRequest request) throws Exception {
		return logisticsWarehouseService.selectLogisticsWarehouseList(param);
	}
 	
	/**
	 * 창고  저장 Event
	 * @param DlvrMasterVo param
	 * @param HttpServletRequest
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/bm/baseinfo/logisticsWarehouse_insInfo.json", method=RequestMethod.POST)
	public Result insertLogisticsWarehouseInfo(HttpServletRequest request, @RequestBody LogisticsWarehouseVo param) throws Exception {
		return logisticsWarehouseService.insertLogisticsWarehouseInfo(request, param);
	}

}
