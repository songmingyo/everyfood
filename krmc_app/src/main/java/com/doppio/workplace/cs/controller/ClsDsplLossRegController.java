package com.doppio.workplace.cs.controller;

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
import com.doppio.workplace.cs.service.ClsDsplLossRegService;
import com.doppio.workplace.cs.service.ClsDsplLossRegVo;

/**
 * @Class : ClsDsplLossRegController.java
 * @author dada
 * @Description : 폐기/로스등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04. 08.     song
 * </pre>
 * @version :
 */
@Controller(value = "clsDsplLossRegController")
public class ClsDsplLossRegController {

	private static final Logger logger = LoggerFactory.getLogger(ClsDsplLossRegController.class);
	
	@Autowired
	ClsDsplLossRegService  clsDsplLossRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/clsDsplLossReg")
 	public String clsDsplLossRegInit() throws Exception{
 		return "app/cs/clsDsplLossReg";
 	}
 	
 	
 	/**
	 * 폐기/로스등록 대상 품목 조회  Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<ClsDsplLossRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/closing/clsDsplLossRegPrdt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<ClsDsplLossRegVo> clsDsplLossRegPrdtList(ClsDsplLossRegVo param, HttpServletRequest request) throws Exception {
		return clsDsplLossRegService.clsDsplLossRegPrdtList(param);
	}
 	
	
	/**
	 * 폐기/로스등록 내역 조회  Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<ClsDsplLossRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/closing/clsDsplLossReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<ClsDsplLossRegVo> selectClsDsplLossRegDetailSearch(ClsDsplLossRegVo param, HttpServletRequest request) throws Exception {
		return clsDsplLossRegService.selectClsDsplLossRegDetailList(param);
	}
	
 	/**
	 * 폐기/로스등록 하나의 품목만 조회  Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<ClsDsplLossRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/closing/clsDsplLossReg_selPrdtAdd.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClsDsplLossRegPrdtAddSearch(@RequestBody ClsDsplLossRegVo param, HttpServletRequest request) throws Exception {
		return clsDsplLossRegService.selectClsDsplLossRegPrdtAdd(param);
	}
	
	
	/**
	 * 폐기/로스등록 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/cs/closing/clsDsplLossReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result clsDsplLossReg (HttpServletRequest request,  @RequestBody ClsDsplLossRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = clsDsplLossRegService.insertClsDsplLossReg(param);
		
		return result;
	}
 	
}
