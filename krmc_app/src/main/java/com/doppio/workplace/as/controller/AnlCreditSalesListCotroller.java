package com.doppio.workplace.as.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.doppio.workplace.sm.service.CusShipAgrclListVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.doppio.workplace.as.service.AnlCreditSalesListService;
import com.doppio.workplace.as.service.AnlCreditSalesListVo;


/**
 * @author j10000
 * @Description : 외상매출금(상세)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlCreditSalesListController")
public class AnlCreditSalesListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlCreditSalesListCotroller.class);
	
	@Autowired
	AnlCreditSalesListService anlCreditSalesListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlCreditSalesList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlCreditSalesList";
 	}

	/**
	 * 외상매출금(상세)
	 * @param AccSalesDepListVO param
	 * @param HttpServletRequest
	 * @return List<AccSalesDepListVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlCreditSalesList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlCreditSalesListSearch(AnlCreditSalesListVo param, HttpServletRequest request) throws Exception {
		return anlCreditSalesListService.selectAnlCreditSalesList(param);
	}
 


	/**
	 * 외상매출금(상세) Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlCreditSalesList_selExcelDown")
	public ModelAndView selectAnlCreditSalesListExcelDown(AnlCreditSalesListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlCreditSalesListService.selectAnlCreditSalesListExcelDown(paramVo);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate currentDate = LocalDate.parse(paramVo.getStartDt(), formatter);
		
		// 현재 월과 전월을 가져옵니다.
        LocalDate currentMonth = currentDate.withDayOfMonth(1);
        
        LocalDate lastMonth1 = currentMonth.minusMonths(0);
        LocalDate lastMonth2 = currentMonth.minusMonths(1);
        LocalDate lastMonth3 = currentMonth.minusMonths(2);
        
        // 형식을 지정하여 출력합니다.
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        DateTimeFormatter formatterYear = DateTimeFormatter.ofPattern("yy");
        DateTimeFormatter formatterMon = DateTimeFormatter.ofPattern("MM");
        
        String lastYear1 = lastMonth1.format(formatterYear);
        String lastMon1 = lastMonth1.format(formatterMon);
        String lastYear2 = lastMonth2.format(formatterYear);
        String lastMon2 = lastMonth2.format(formatterMon);
        String lastYear3 = lastMonth3.format(formatterYear);
        String lastMon3 = lastMonth3.format(formatterMon);
        
        String headText1 = lastYear3.concat("년 ").concat(lastMon3).concat("월 잔액");
        String headText2 = lastYear2.concat("년 ").concat(lastMon2).concat("월 매출");
        String headText3 = lastYear2.concat("년 ").concat(lastMon2).concat("월 입금");
        String headText4 = lastYear2.concat("년 ").concat(lastMon2).concat("월 잔액");
        String headText5 = lastYear1.concat("년 ").concat(lastMon1).concat("월 매출");
        String headText6 = lastYear1.concat("년 ").concat(lastMon1).concat("월 입금");
        String headText7 = lastYear1.concat("년 ").concat(lastMon1).concat("월 잔액");
		
		//file name
		String fileName = "외상매출금(상세)";

		// 엑셀 필드타이틀
		String[] title = {"NO","매출처구분", "매출처","매출처명",headText1,headText2,headText3, headText4,headText5,headText6,headText7,"결제조건","영업사원","여신한도", "사업자 등록번호"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "RIGHT/#,##0", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS_NM", "SALES_CD", "SALES_NM", "BAL_AMT1", "SALES_AMT2", "DEP_AMT2",  "BAL_AMT2", "SALES_AMT3", "DEP_AMT3", "BAL_AMT3", "SETL_CON", "SALES_PR_NM", "CRE_LIM", "BSNS_NUM"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	 /**
	  * 외상매출금(상세) 출력
	  */
	 @RequestMapping(value="/app/as/anlys/anlCreditSalesListPrint", method = RequestMethod.POST)
	 public void cusShipAGrcListPdfDownload(HttpServletRequest request, HttpServletResponse response, AnlCreditSalesListVo anlCreditSalesListVo) throws Exception{

		 String jsonData = request.getParameter("gridDataInput");
		 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 List<AnlCreditSalesListVo> gridDataList = mapper.readValue(jsonData, mapper.getTypeFactory().constructCollectionType(List.class, AnlCreditSalesListVo.class));
		 
		 //List<AnlCreditSalesListVo> list = anlCreditSalesListService.selectAnlCreditPrintList(anlCreditSalesListVo);
		 
		 //System.out.println(anlCreditSalesListVo.getGridDataInput());
		 
		 String jasperName = "anlCreditSalesListPrt.jasper";
		 String fileName = "외상매출금(상세)";
		 String viewType = "browser";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();
		 
		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", gridDataList); 									//DATA
		 map.put("viewType", viewType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }
	
}
