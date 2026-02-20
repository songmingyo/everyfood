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
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.doppio.common.model.Result;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.as.service.SalSalesMonPrftListService;
import com.doppio.workplace.as.service.SalSalesMonPrftListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 매출처월이익현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesMonPrftListController")
public class SalSalesMonPrftListController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesMonPrftListController.class);
	
	@Autowired
	SalSalesMonPrftListService salSalesMonPrftListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesMonPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesMonPrftList";
 	}

	/**
	 * 매출처월이익현황 조회 Event
	 * @param SalSalesMonPrftListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salSalesMonPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesMonPrftList(SalSalesMonPrftListVo param, HttpServletRequest request) throws Exception {
		return salSalesMonPrftListService.selectSalSalesMonPrftList(param);
	}
	

	/**
	 * 매출처월이익현황 다운로드
	 * @param SalSalesMonPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salSalesMonPrftList_selExcelDown")
	public ModelAndView selectExcelDownSalesMonPrft(SalSalesMonPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesMonPrftListService.selectSalSalesMonPrftListExcelDown(paramVo);

		//file name
		String fileName = "매출처월이익현황";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "구분", "매출처명", "항목", "01월", "02월", "03월", "04월", "05월", "06월", "07월", "08월", "09월", "10월", "11월", "12월", "합계", "영업사원"};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS_NM", "SALES_NM", "CLASS_NM", "MON_1", "MON_2", "MON_3", "MON_4", "MON_5", "MON_6", "MON_7", "MON_8", "MON_9", "MON_10", "MON_11", "MON_12", "MON_TOT", "SALES_PR_NM"};

	    ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
