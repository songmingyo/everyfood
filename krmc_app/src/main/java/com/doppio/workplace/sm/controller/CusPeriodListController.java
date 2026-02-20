package com.doppio.workplace.sm.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.sm.service.CusPeriodListService;
import com.doppio.workplace.sm.service.CusPeriodListVo;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;


/**
 * @author j10000
 * @Description : 기간별출고현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.06 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusPeriodListController")
public class CusPeriodListController {

	private static final Logger logger = LoggerFactory.getLogger(CusPeriodListController.class);
	
	@Autowired
	CusPeriodListService cusPeriodListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusPeriodList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusPeriodList";
 	}

	/**
	 * 기간별출고현황 
	 * @param CusPeriodListVo param
	 * @param HttpServletRequest
	 * @return List<CusPeriodListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusPeriodList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusPeriodListSearch(CusPeriodListVo param, HttpServletRequest request) throws Exception {
		return cusPeriodListService.selectCusPeriodList(param);
	}
 


	/**
	 * 기간별출고현황  Event 엑셀 다운로드
	 * @param CusPeriodListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusPeriodList_selExcelDown")
	public ModelAndView selectCusPeriodListExcelDown(CusPeriodListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusPeriodListService.selectCusPeriodListExcelDown(paramVo);

		//file name
		String fileName = "기간별출고현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","구분", "매출처명", "창고명","매입처명","랙번호", "품목코드","품목명","규격","출고수량","박스수량","입고단가","출고단가","공급가","부가세","합계금액"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS_NM", "SALES_NM", "WH_NM", "BUY_NM", "LACK_NO_1", "PRDT_CD", "PRDT_NM", "PRDT_STD", "SALES_QTY" ,"BOX_QTY", "COST", "PRICE","PURE_AMT","VAT_AMT","TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	 /**
	  * 기간별출고현황 출력
	  */
	 @RequestMapping(value="/app/sm/custsales/cusPeriodListPrint", method = RequestMethod.POST)
	 public void cusPriceListPdfDownload(HttpServletRequest request, HttpServletResponse response, CusPeriodListVo cusPeriodListVo) throws Exception{

		 List<CusPeriodListVo> list = cusPeriodListService.selectCusPeriodPrintList(cusPeriodListVo);
		 String jasperName = "cusPeriodListPrt.jasper";
		 String fileName = "기간별출고현황";
		 String docType = "pdf";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list); 									//DATA
		 map.put("docType", docType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }
	
}
