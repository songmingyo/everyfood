package com.doppio.workplace.br.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.CommonService;
import com.doppio.workplace.br.service.BuyRtnRegService;
import com.doppio.workplace.br.service.BuyRtnRegVo;

/**
 * @author Song
 * @Description : 매입처 반품 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "buyRtnRegController")
public class BuyRtnRegController {

	private static final Logger logger = LoggerFactory.getLogger(BuyRtnRegController.class);
	
	@Autowired
	BuyRtnRegService buyRtnRegService;
	
	@Autowired
	CommonService commonService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyRtnReg")
 	public String buyRtnRegInit(Model model) throws Exception{
 		
 		return "app/br/purBuyRtnReg";
 	}

	/**
	 * 매입처 반품 목록 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRtnReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyRtnRegList(BuyRtnRegVo param, HttpServletRequest request) throws Exception {
		return buyRtnRegService.selectBuyRtnRegList(param);
	}
	
	/**
	 * 매입처 반품 목록 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRtnReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<BuyRtnRegVo> selectBuyRtnDetailList(BuyRtnRegVo param, HttpServletRequest request) throws Exception {
		return buyRtnRegService.selectBuyRtnDetailList(param);
	}
	
	/**
	 * 매입처 반품 추가 품목 하나 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRtnReg_selPrdtAddList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyRtnRegPrdtAddList(@RequestBody BuyRtnRegVo param, HttpServletRequest request) throws Exception {
		return buyRtnRegService.selectBuyRtnRegPrdtAddList(param);
	}
 

	/**
	 * 매입처 반품 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/br/buy/buyRtnReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result buyRtnReg (HttpServletRequest request,  @RequestBody BuyRtnRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = buyRtnRegService.insertBuyRtnReg(param);
		
		return result;		
	}	
	
	/**
    * 매입처 반품현황 Form page init... add by song min kyo 2025.08.26
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyRtnList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/br/buyRtnList";
 	}
 	
 	/**
     * 매입처 반품현황 조회 add by song min kyo 2025.08.26
  	* @param
  	* @return
     * @throws Exception
  	*/
 	@RequestMapping(value= {"/app/br/buy/buyRtnList_2_SelList.json"}, method=RequestMethod.POST) 	
	public @ResponseBody HashMap<String, Object> buyRtnList_2_SelList(BuyRtnRegVo param, HttpServletRequest request) throws Exception {
		return buyRtnRegService.selectBuyRtnList_2(param);
	}
 	
 	/**
     * 매입처 반품현황 Excel add by song min kyo 2025.08.26
  	* @param
  	* @return
     * @throws Exception
  	*/ 	
 	@RequestMapping(value="/app/br/buy/buyRtnList_2_SelExcelDwon")
	public ModelAndView selectBuyRtnListExcelDown(BuyRtnRegVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = buyRtnRegService.selectBuyRtnListExcelDown(paramVo);

		//file name
		String fileName = "매입처반품현황";
	
		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "매입전표번호", "매입처명","품목코드","품목명","규격","반품구분","단가","수량","공급가","부가세","합계금액","비고","최초등록자","최초등록시간"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "LEFT", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGTH/#,##0.00", "RIGTH/#,##0", "RIGTH/#,##0", "RIGTH/#,##0", "LEFT", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 50, 20, 40, 30, 20, 20, 20, 20, 20, 20, 50, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "RTN_DT",  "BUY_SLIP_NO", "BUY_NM", "PRDT_CD", "PRDT_NM",  "PRDT_STD", "RTN_CLASS_NM", "COST", "RTN_QTY", "PURE_AMT", "VAT_AMT",  "TOT_AMT", "REMARKS", "REG_ID", "REG_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
}
