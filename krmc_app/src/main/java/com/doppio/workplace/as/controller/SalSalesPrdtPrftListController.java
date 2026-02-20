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
import com.doppio.workplace.as.service.SalSalesPrdtPrftListService;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 메출처 품목별이익현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesPrdtPrftListController")
public class SalSalesPrdtPrftListController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesPrdtPrftListController.class);
	
	@Autowired
	SalSalesPrdtPrftListService salSalesPrdtPrftListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesPrdtPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesPrdtPrftList";
 	}

	/**
	 * 메출처 품목별이익현황 조회 Event
	 * @param SalSalesPrdtPrftListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salSalesPrdtPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesPrdtPrftList(SalSalesPrdtPrftListVo param, HttpServletRequest request) throws Exception {
		return salSalesPrdtPrftListService.selectSalSalesPrdtPrftList(param);
	}
	
	/**
	 * 메출처 품목별이익현황 Footer 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/sal/salSalesPrdtPrftFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalSalesPrdtPrftListVo selectSalesPrdtPrftFooterList (HttpServletRequest request,  @RequestBody SalSalesPrdtPrftListVo param) throws Exception {
		return salSalesPrdtPrftListService.selectSalSalesPrdtPrftFooter(param);
	}
	 
	/**
	 * 메출처 품목별이익현황 다운로드
	 * @param SalSalesPrdtPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salSalesPrdtPrftList_selExcelDown")
	public ModelAndView selectExcelDownSalesPrdtPrft(SalSalesPrdtPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesPrdtPrftListService.selectSalSalesPrdtPrftListExcelDown(paramVo);

		//file name
		String fileName = "메출처 품목별이익현황";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "구분", "매출처명", "품목코드", "품목명", "규격", "입고단가", "입고금액", "출고단가", "출고금액", "매출이익", "이익율(%)", "단가기준", "출고수량", "영업사원", "장려비율"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "CENTER", "RIGHT/#,##0.00", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS", "SALES_NM", "PRDT_CD", "PRDT_NM", "PRDT_STD", "COST", "BUY_AMT", "PRICE", "PURE_AMT", "SALES_PROFIT", "MAR_RATE", "COST_ClASS", "SALES_QTY", "SALES_PR_NM", "SUBSIDY_RATE"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
