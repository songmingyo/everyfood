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
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.as.service.SalSalesTotPrftListService;
import com.doppio.workplace.as.service.SalSalesTotPrftListVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 통합매출처이익률(전월대비)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesTotPrftListController")
public class SalSalesTotPrftListController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesTotPrftListController.class);
	
	@Autowired
	SalSalesTotPrftListService salSalesTotPrftListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesTotPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesTotPrftList";
 	}

	/**
	 * 통합매출처이익률(전월대비) 조회 Event
	 * @param SalSalesTotPrftListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/salesmgmt/salSalesTotPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesTotPrftList(SalSalesTotPrftListVo param, HttpServletRequest request) throws Exception {
		return salSalesTotPrftListService.selectSalSalesTotPrftList(param);
	}
	
	/**
	 * 통합매출처이익률(전월대비) Footer 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/salesmgmt/salSalesTotPrftFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalSalesTotPrftListVo selectSalesTotPrftFooter (HttpServletRequest request,  @RequestBody SalSalesTotPrftListVo param) throws Exception {
		return salSalesTotPrftListService.selectSalesTotPrftFooter(param);
	}
	
	
	 /**
	  * 통합매출처이익률(전월대비) 출력
	  */
	 @RequestMapping(value="/app/as/salesmgmt/salSalesTotPrftList_prtList.json", method = RequestMethod.POST)
	 public void salesTotPrftListPdfDownload(HttpServletRequest request, HttpServletResponse response, SalSalesTotPrftListVo salSalesTotPrftListVo) throws Exception{

		 List<SalSalesTotPrftListVo> list = salSalesTotPrftListService.selectSalesTotPrftPrintList(salSalesTotPrftListVo);
		 String jasperName = "salesTotPrftPrt.jasper";
		 String fileName = "통합매출처이익율(부가세별도)";
		 String viewType = "browser";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);
			
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list); 										//DATA
		 map.put("viewType", viewType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름
			
		 jasperDownloadService.jasperDocumentDownload(map, request, response);
	 }
	 
	
	/**
	 * 통합매출처이익률(전월대비) 다운로드
	 * @param SalSalesTotPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/salesmgmt/salSalesTotPrftList_selExcelDown")
	public ModelAndView selectExcelDownSalesTotPrft(SalSalesTotPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesTotPrftListService.selectSalSalesTotPrftListExcelDown(paramVo);

		//file name
		String fileName = "통합매출처이익률(전월대비)";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "구분", "매출처명", "전전월 매출", "전전월 이익", "전전월 마진율", "전월 매출", "전월 이익", "전월 마진율", "당월 매입", "당월 매출", "당월 이익", "당월 마진율", "영업사원","장려비율"};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS_NM", "SALES_NM", "SALES_AMT_1", "PROFIT_AMT_1", "MAR_RATE_1", "SALES_AMT_2", "PROFIT_AMT_2", "MAR_RATE_2", "BUY_AMT_3", "SALES_AMT_3", "PROFIT_AMT_3", "MAR_RATE_3", "SALES_PR_NM", "SUBSIDY_RATE"};

	    ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
