package com.doppio.workplace.as.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.as.service.AccSalesDepRegVo;
import com.doppio.workplace.as.service.AccSalesFirmbankingListService;
import com.doppio.workplace.as.service.AccSalesFirmbankingListVo;

import com.doppio.workplace.scheduler.service.BatchCommVo;
import com.doppio.workplace.scheduler.service.SchedulerSampleVo;
import com.doppio.workplace.scheduler.service.SchedulerService;


/**
 * @author j10000
 * @Description : 펌뱅킹입금현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.14 j10000
 * </pre>
 * @version :
 */
@Controller(value = "accSalesFirmbankingListController")
public class AccSalesFirmbankingListController {

	private static final Logger logger = LoggerFactory.getLogger(AccSalesFirmbankingListController.class);
	
	@Autowired
	AccSalesFirmbankingListService accSalesFirmbankingListService;
	
	SchedulerService schedulerService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/account/accSalesFirmbankingList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/accSalesFirmbankingList";
 	}

	/**
	 * 펌뱅킹입금현황
	 * @param accSalesFirmbankingListVO param
	 * @param HttpServletRequest
	 * @return List<accSalesFirmbankingListVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/account/accSalesFirmbankingList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectaccSalesFirmbankingListSearch(AccSalesFirmbankingListVo param, HttpServletRequest request) throws Exception {
		return accSalesFirmbankingListService.selectAccSalesFirmbankingList(param);
	}


	/**
	 * 펌뱅킹입금  저장 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/app/as/account/accSalesFirmbankingReg_insInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result insertAccSalesDepReg (HttpServletRequest request,  @RequestBody AccSalesFirmbankingListVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = accSalesFirmbankingListService.insertAccSalesFirmbankingRegInfo(param);
		
		return result;
	}
	

	
}
