package com.doppio.workplace.as.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.cs.service.LogWhMvRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;
import com.doppio.workplace.as.service.AccSalesDepRegService;
import com.doppio.workplace.as.service.AccSalesDepRegVo;
import com.doppio.workplace.bm.service.EmpMasterVo;


/**
 * @author j10000
 * @Description : 매출처입금관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.14 j10000
 * </pre>
 * @version :
 */
@Controller(value = "accSalesDepRegController")
public class AccSalesDepRegController {

	private static final Logger logger = LoggerFactory.getLogger(AccSalesDepRegController.class);
	
	@Autowired
	AccSalesDepRegService accSalesDepRegService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/account/accSalesDepReg")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/accSalesDepReg";
 	}

	/**
	 * 매출처입금관리
	 * @param AccSalesDepRegVO param
	 * @param HttpServletRequest
	 * @return List<AccSalesDepRegVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/account/accSalesDepReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAccSalesDepRegSearch(AccSalesDepRegVo param, HttpServletRequest request) throws Exception {
		return accSalesDepRegService.selectAccSalesDepReg(param);
	}
 

	
	/**
	 * 매출처입금관리 : 매출처입금관리 상세  조회  Event
	 * @param AccSalesDepRegVo param
	 * @param HttpServletRequest
	 * @return List<AccSalesDepRegVo>
	 * @throws Exception
	 */
	
	@RequestMapping(value= {"/app/as/account/accSalesDepReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<AccSalesDepRegVo> selectCusShipRegDetailSearch(AccSalesDepRegVo param, HttpServletRequest request) throws Exception {
		return accSalesDepRegService.selectAccSalesDepRegDetail(param);
	}
	/**
	 * 매출처입금관리  저장 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/app/as/account/accSalesDepReg_insInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result insertAccSalesDepReg (HttpServletRequest request,  @RequestBody AccSalesDepRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = accSalesDepRegService.insertAccSalesDepRegInfo(param);
		
		return result;
	}


	
}
