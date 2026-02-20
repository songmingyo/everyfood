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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.doppio.common.model.Result;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.cs.service.LogWhMvRegService;
import com.doppio.workplace.cs.service.LogWhMvRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 센터이동 등록 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "logWhMvRegController")
public class LogWhMvRegController {

	private static final Logger logger = LoggerFactory.getLogger(LogWhMvRegController.class);
	
	@Autowired
	LogWhMvRegService logWhMvRegService;

	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/logistics/logWhMvReg")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/logWhMvReg";
 	}

	/**
	 * 센터이동 등록 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/logistics/logWhMvReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectLogWhMvRegList(LogWhMvRegVo param, HttpServletRequest request) throws Exception {
		return logWhMvRegService.selectLogWhMvRegList(param);
	}
	
	/**
	 * 센터이동 등록 추가 품목 하나 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/logistics/logWhMvReg_selPrdtAddList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectLogWhMvRegPrdtAddList(@RequestBody LogWhMvRegVo param, HttpServletRequest request) throws Exception {
		return logWhMvRegService.selectLogWhMvRegPrdtAddList(param);
	}
 

	/**
	 * 센터이동 등록 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/cs/logistics/logWhMvReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result buyOrdReg (HttpServletRequest request,  @RequestBody LogWhMvRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = logWhMvRegService.insertLogWhMvReg(param);
		
		return result;
	}



}
