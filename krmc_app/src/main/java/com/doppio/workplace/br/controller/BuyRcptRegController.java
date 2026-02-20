package com.doppio.workplace.br.controller;

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

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.model.CustomCodeVo;
import com.doppio.workplace.br.service.BuyRcptRegService;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.common.service.CommonService;

/**
 * @author Song
 * @Description : 매입처 입고 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "buyRcptRegController")
public class BuyRcptRegController {

	private static final Logger logger = LoggerFactory.getLogger(BuyRcptRegController.class);
	
	@Autowired
	BuyRcptRegService buyRcptRegService;
	
	@Autowired
	CommonService commonService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyRcptReg")
 	public String buyRcptRegInit(Model model) throws Exception{
 		
 		//jqGrid selectbox 생성용 코드 리스트 조회 >> 이물회수[FS04]
		CustomCodeVo codeVo = new CustomCodeVo();
		codeVo.setParentCd("M009");
		String codeList_buyConYn = commonService.selectGridCodeList(codeVo);
		model.addAttribute("codeList_buyConYn", codeList_buyConYn);
 		
 		return "app/br/purBuyRcptReg";
 	}

	/**
	 * 매입처 입고 목록 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRcptReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyRcptRegList(BuyRcptRegVo param, HttpServletRequest request) throws Exception {
		return buyRcptRegService.selectBuyRcptRegList(param);
	}
	
	/**
	 * 매입처 입고 상세 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRcptReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<BuyRcptRegVo> selectBuyRcptDetailList(BuyRcptRegVo param, HttpServletRequest request) throws Exception {
		return buyRcptRegService.selectBuyRcptDetailList(param);
	}
	
	/**
	 * 매입처 입고 추가 품목 하나 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRcptReg_selPrdtAddList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyRcptRegPrdtAddList(@RequestBody BuyRcptRegVo param, HttpServletRequest request) throws Exception {
		return buyRcptRegService.selectBuyRcptRegPrdtAddList(param);
	}
 

	/**
	 * 매입처 입고 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/br/buy/buyRcptReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result buyOrdReg (HttpServletRequest request,  @RequestBody BuyRcptRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = buyRcptRegService.insertBuyRcptReg(param);
		
		return result;
	}
	


}
