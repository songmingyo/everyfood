package com.doppio.workplace.as.controller;

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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.doppio.common.model.Result;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.as.service.SalSalesMonVatPrftListService;
import com.doppio.workplace.as.service.SalSalesMonVatPrftListVo;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 매출처기간 월별 이익현황(부가세포함)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesMonVatPrftListController")
public class SalSalesMonVatPrftListController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesMonVatPrftListController.class);
	
	@Autowired
	SalSalesMonVatPrftListService salSalesMonVatPrftListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesMonVatPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesMonVatPrftList";
 	}

	/**
	 * 매출처기간 월별 이익현황(부가세포함) 조회 Event
	 * @param SalSalesMonVatPrftListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salSalesMonVatPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesMonVatPrftList(SalSalesMonVatPrftListVo param, HttpServletRequest request) throws Exception {
		return salSalesMonVatPrftListService.selectSalSalesMonVatPrftList(param);
	}
	
	
	/**
	 * 매출처기간 월별 이익현황(부가세포함) Footer 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/sal/salSalesMonVatPrftFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalSalesMonVatPrftListVo selectSalesPrdtPrftFooterList (HttpServletRequest request,  @RequestBody SalSalesMonVatPrftListVo param) throws Exception {
		return salSalesMonVatPrftListService.selectSalSalesMonVatPrftFooter(param);
	}
	
	/**
	 * 매출처기간 월별 이익현황(부가세포함) 다운로드
	 * @param SalSalesMonVatPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salSalesMonVatPrftList_selExcelDown")
	public ModelAndView selectExcelDownSalesMonVatPrft(SalSalesMonVatPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesMonVatPrftListService.selectSalSalesMonVatPrftListExcelDown(paramVo);

		//file name
		String fileName = "매출처기간 월별 이익현황(부가세포함)";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "구분", "매출처명", "전월잔액", "입고금액", "출고금액", "매출이익", "마진율(%)", "입금", "잔액", "결제조건", "영업사원", "장려비율" };
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS_NM", "SALES_NM", "PREV_BAL_AMT", "RCV_AMT", "PURE_AMT", "SALES_PROFIT", "MAR_RATE", "THIS_DEP_AMT", "THIS_BAL_AMT", "SETL_CON", "SALES_PR_NM", "SUBSIDY_RATE" };

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
