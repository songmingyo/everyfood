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
import com.doppio.workplace.as.service.SalPrdtPrftListService;
import com.doppio.workplace.as.service.SalPrdtPrftListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 품목별이익현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salPrdtPrftListController")
public class SalPrdtPrftListController {

	private static final Logger logger = LoggerFactory.getLogger(SalPrdtPrftListController.class);
	
	@Autowired
	SalPrdtPrftListService salPrdtPrftListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salPrdtPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salPrdtPrftList";
 	}

	/**
	 * 품목별이익현황 조회 Event
	 * @param SalPrdtPrftListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salPrdtPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalPrdtPrftList(SalPrdtPrftListVo param, HttpServletRequest request) throws Exception {
		return salPrdtPrftListService.selectSalPrdtPrftList(param);
	}
	
	/**
	 * 품목별이익현황 Footer 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/sal/salPrdtPrftFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalPrdtPrftListVo selectPrdtPrftFooterList (HttpServletRequest request,  @RequestBody SalPrdtPrftListVo param) throws Exception {
		return salPrdtPrftListService.selectSalPrdtPrftFooter(param);
	}
	 
	/**
	 * 품목별이익현황 다운로드
	 * @param SalPrdtPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salPrdtPrftList_selExcelDown")
	public ModelAndView selectExcelDownPrdtPrft(SalPrdtPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salPrdtPrftListService.selectSalPrdtPrftListExcelDown(paramVo);

		//file name
		String fileName = "품목별이익현황";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "품목코드","품목명","규격","출고수량","입고단가","입고금액","출고단가","출고금액","차액","마진율(%)"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0.00"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "PRDT_CD", "PRDT_NM", "PRDT_STD", "SALES_QTY", "COST", "RCV_AMT", "PRICE", "PURE_AMT", "DIFF_AMT", "MAR_RATE"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
