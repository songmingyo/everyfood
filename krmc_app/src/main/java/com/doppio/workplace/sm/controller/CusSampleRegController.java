package com.doppio.workplace.sm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.doppio.common.service.JasperDownloadService;
import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusSampleRegService;
import com.doppio.workplace.sm.service.CusSampleRegVo;

/**
 * @Class : CusSampleRegController.java
 * @author dada
 * @Description : 매출처샘플등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04. 08.     song
 * </pre>
 * @version :
 */
@Controller(value = "cusSampleRegController")
public class CusSampleRegController {

	private static final Logger logger = LoggerFactory.getLogger(CusSampleRegController.class);
	
	@Autowired
	CusSampleRegService  cusSampleRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusSampleReg")
 	public String cusSampleRegInit() throws Exception{
 		return "app/sm/cusSampleReg";
 	}
 	
 	
 	/**
	 * 매출처샘플등록 대상 품목 조회  Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<CusSampleRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSampleReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusSampleRegVo> selectCusSampleRegSearch(CusSampleRegVo param, HttpServletRequest request) throws Exception {
		return cusSampleRegService.selectCusSampleRegList(param);
	}
 	
	
	/**
	 * 매출처샘플등록 내역 조회  Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<CusSampleRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSampleReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusSampleRegVo> selectCusSampleRegDetailSearch(CusSampleRegVo param, HttpServletRequest request) throws Exception {
		return cusSampleRegService.selectCusSampleRegDetailList(param);
	}
	
 	/**
	 * 매출처샘플등록 하나의 품목만 조회  Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<CusSampleRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSampleReg_selPrdtAdd.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusSampleRegPrdtAddSearch(@RequestBody CusSampleRegVo param, HttpServletRequest request) throws Exception {
		return cusSampleRegService.selectCusSampleRegPrdtAdd(param);
	}
	
	
	/**
	 * 매출처샘플등록 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusSampleReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusSampleReg (HttpServletRequest request,  @RequestBody CusSampleRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusSampleRegService.insertCusSampleReg(param);
		
		return result;
	}
 	
}
