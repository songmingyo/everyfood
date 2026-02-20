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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.Result;
import com.doppio.workplace.sm.service.CusPriceUptService;
import com.doppio.workplace.sm.service.CusPriceUptVo;

/**
 * @author Song
 * @Description : 매출 단가 일괄 수정
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "cusPriceUptController")
public class CusPriceUptController {

	private static final Logger logger = LoggerFactory.getLogger(CusPriceUptController.class);
	
	@Autowired
	CusPriceUptService cusPriceUptService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusPriceUpt")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusPriceUpt";
 	}

	/**
	 * 매출 일괄 단가 조회 Event
	 * @param CusPriceUptVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusPriceUpt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusPriceUptList(CusPriceUptVo param, HttpServletRequest request) throws Exception {
		return cusPriceUptService.selectCusPriceUptList(param);
	}
	

	/**
	 * 매출 일괄 단가 저장 Event
	 * @param CusPriceUptVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusPriceUpt_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusPriceUpt (HttpServletRequest request,  @RequestBody CusPriceUptVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusPriceUptService.updateCusPriceUpt(param);
		
		return result;	
	}
	

}
