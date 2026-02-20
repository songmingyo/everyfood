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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlCreditSalesListVo;
import com.doppio.workplace.as.service.AnlSalesLdgrDtListService;
import com.doppio.workplace.as.service.AnlSalesLdgrDtListVo;


/**
 * @author j10000
 * @Description : 매출처원장(일별)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.20 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlSalesLdgrDtListController")
public class AnlSalesLdgrDtListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlSalesLdgrDtListCotroller.class);
	
	@Autowired
	AnlSalesLdgrDtListService anlSalesLdgrDtListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlSalesLdgrDtList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlSalesLdgrDtList";
 	}

	/**
	 * 매출처원장(일별)
	 * @param AnlSalesLdgrDtListVo param
	 * @param HttpServletRequest
	 * @return List<AnlSalesLdgrDtListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlSalesLdgrDtList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlSalesLdgrDtListSearch(AnlSalesLdgrDtListVo param, HttpServletRequest request) throws Exception {
		return anlSalesLdgrDtListService.selectAnlSalesLdgrDtList(param);
	}
 


	/**
	 * 매출처원장(일별) Event 엑셀 다운로드
	 * @param AnlSalesLdgrDtListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlSalesLdgrDtList_selExcelDown")
	public ModelAndView selectAnlCreditSalesListExcelDown(AnlSalesLdgrDtListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlSalesLdgrDtListService.selectAnlSalesLdgrDtListExcelDown(paramVo);

		//file name
		String fileName = "매출처원장(일별)";


		// 엑셀 필드타이틀
		String[] title = {"NO", "일자", "구분", "매출처명", "품명","규격","수량","단가", "공급가","부가세","합계(VAT포함)","현금","어음","장려금","기타","당월현잔액"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "DT_GUBUN", "GUBUN", "SALES_NM", "PRDT_NM", "PRDT_STD", "QTY", "PRICE", "PURE_AMT", "VAT_AMT", "TOT_AMT", "PAID_AMT1", "PAID_AMT2", "PAID_AMT3", "PAID_AMT4", "BAL_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	
	 /**
	  * 매출처원장 (일별) 출력
	  */
	 @RequestMapping(value="/app/as/anlys/anlSalesLdgrDtList_prtList", method = RequestMethod.POST)
	 public void anlSalesLdgrDtListPdfDownload(HttpServletRequest request, HttpServletResponse response, AnlSalesLdgrDtListVo anlSalesLdgrDtListVo) throws Exception{

		 List<AnlSalesLdgrDtListVo> list = anlSalesLdgrDtListService.selectAnlSalesLdgrDtPrintList(anlSalesLdgrDtListVo);
		 String jasperName = "anlSalesLdgrDtListPrt.jasper";
		 String fileName = "매출처원장(일별)";
		 String viewType = "browser";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list); 									//DATA
		 map.put("viewType", viewType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }

	
}
