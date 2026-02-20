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
import com.doppio.workplace.as.service.SalSalesYearOnYearDtlListService;
import com.doppio.workplace.as.service.SalSalesYearOnYearDtlListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 전년대비매출처별매출현황(상세)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesYearOnYearDtlListController")
public class SalSalesYearOnYearDtlListController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesYearOnYearDtlListController.class);
	
	@Autowired
	SalSalesYearOnYearDtlListService salSalesYearOnYearDtlListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesYearOnYearDtlList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesYearOnYearDtlList";
 	}

	/**
	 * 매출처월이익현황 조회 Event
	 * @param SalSalesYearOnYearDtlListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salSalesYearOnYearDtlList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesYearOnYearDtlList(SalSalesYearOnYearDtlListVo param, HttpServletRequest request) throws Exception {
		return salSalesYearOnYearDtlListService.selectSalSalesYearOnYearDtlList(param);
	}
	

	/**
	 * 매출처월이익현황 다운로드
	 * @param SalSalesYearOnYearDtlListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salSalesYearOnYearDtlList_selExcelDown")
	public ModelAndView selectExcelDownSalesYearOnYearDtl(SalSalesYearOnYearDtlListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesYearOnYearDtlListService.selectSalSalesYearOnYearDtlListExcelDown(paramVo);

		//file name
		String fileName = "전년대비매출처별매출현황(상세)";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "년도", "구분", "매출처명", "01월", "02월", "03월", "04월", "05월", "06월", "07월", "08월", "09월", "10월", "11월", "12월", "합계", "영업사원"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "YEAR_NM", "SALES_CLASS_NM", "SALES_NM", "MON_1", "MON_2", "MON_3", "MON_4", "MON_5", "MON_6", "MON_7", "MON_8", "MON_9", "MON_10", "MON_11", "MON_12", "MON_TOT", "SALES_PR_NM"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
