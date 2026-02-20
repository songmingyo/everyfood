package com.doppio.workplace.as.controller;

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

import com.doppio.common.model.Result;
import com.doppio.workplace.as.service.AccBuyWdrlRegService;
import com.doppio.workplace.as.service.AccBuyWdrlRegVo;


/**
 * @author j10000
 * @Description : 매입처지급관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.14 j10000
 * </pre>
 * @version :
 */
@Controller(value = "accBuyWdrlRegController")
public class AccBuyWdrlRegController {

	private static final Logger logger = LoggerFactory.getLogger(AccBuyWdrlRegController.class);
	
	@Autowired
	AccBuyWdrlRegService accBuyWdrlRegService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/account/accBuyWdrlReg")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/accBuyWdrlReg";
 	}

	/**
	 * 매입처지급관리
	 * @param AccBuyWdrlRegVO param
	 * @param HttpServletRequest
	 * @return List<AccBuyWdrlRegVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/account/accBuyWdrlReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAccBuyWdrlRegSearch(AccBuyWdrlRegVo param, HttpServletRequest request) throws Exception {
		return accBuyWdrlRegService.selectAccBuyWdrlReg(param);
	}
 

	
	/**
	 * 매입처지급관리 : 매입처지급관리 상세  조회  Event
	 * @param AccBuyWdrlRegVo param
	 * @param HttpServletRequest
	 * @return List<AccBuyWdrlRegVo>
	 * @throws Exception
	 */
	
	@RequestMapping(value= {"/app/as/account/accBuyWdrlReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<AccBuyWdrlRegVo> selectCusShipRegDetailSearch(AccBuyWdrlRegVo param, HttpServletRequest request) throws Exception {
		return accBuyWdrlRegService.selectAccBuyWdrlRegDetail(param);
	}
	/**
	 * 매입처지급관리  저장 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/app/as/account/accBuyWdrlReg_insInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result insertAccBuyWdrlReg (HttpServletRequest request,  @RequestBody AccBuyWdrlRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = accBuyWdrlRegService.insertAccBuyWdrlRegInfo(param);
		
		return result;
	}


	
}
