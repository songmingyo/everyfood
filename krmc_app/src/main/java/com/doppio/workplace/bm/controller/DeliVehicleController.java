package com.doppio.workplace.bm.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.DeliVehicleService;
import com.doppio.workplace.bm.service.DlvrMasterVo;
import com.doppio.workplace.common.model.UserInfoVo;

/**
 * @author dada
 * @Description : 배송차량관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03. 09.     DADA
 * </pre>
 * @version :
 */
@Controller(value = "deliVehicleController")
public class DeliVehicleController {

	private static final Logger logger = LoggerFactory.getLogger(DeliVehicleController.class);
	
	@Autowired
	DeliVehicleService  deliVehicleService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/empDeliveryVehicle")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basDeliVehicle";
 	}

	/**
	 * 차량정보 조회  조회 Event
	 * @param DlvrMasterVo param
	 * @param HttpServletRequest
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/empDeliveryVehicle_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectDelivehicleSearch(DlvrMasterVo param, HttpServletRequest request) throws Exception {
		return deliVehicleService.selectDelivehicleList(param);
	}
 

	/**
	 * 차량정보 상세 조회 Event
	 * @param DlvrMasterVo param
	 * @param HttpServletRequest
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/empDeliveryVehicle_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody DlvrMasterVo selectDelivehicleDetail (HttpServletRequest request,  @RequestBody DlvrMasterVo param) throws Exception {
		return deliVehicleService.selectDelivehicleData(param);
	}

	/**
	 * 차량정보 저장 Event
	 * @param DlvrMasterVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/empDeliveryVehicle_insData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result selectDelivehicleSave (HttpServletRequest request,  @RequestBody DlvrMasterVo param, Result result) throws Exception {
		return deliVehicleService.insertDelivehicleData(param);
	}
	
	/**
	 * 차량정보 저장 Event
	 * @param DlvrMasterVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/empDeliveryVehicle_delData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result deleteDelivehicle (HttpServletRequest request,  @RequestBody DlvrMasterVo param, Result result) throws Exception {
		return deliVehicleService.deleteDelivehicleData(param);
	}
	
	
 	

}
