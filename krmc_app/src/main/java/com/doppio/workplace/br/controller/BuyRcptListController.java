package com.doppio.workplace.br.controller;

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
import com.doppio.common.model.Result;
import com.doppio.workplace.as.service.SalPrdtPrftListVo;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptListService;
import com.doppio.workplace.br.service.BuyRcptListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author j10000
 * @Description : 매입처 입고현황관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03.31 j10000
 * </pre>
 * @version :
 */
@Controller(value = "buyRcpListController")
public class BuyRcptListController {

	private static final Logger logger = LoggerFactory.getLogger(BuyRcptListController.class);
	
	@Autowired
	BuyRcptListService buyRcptListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyRcptList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/br/purBuyRcptList";
 	}

	/**
	 * 매입처입고현황 조회 Event
	 * @param BuyRcptListVo param
	 * @param HttpServletRequest
	 * @return List<BuyRcptListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyRcptList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyRcptListSearch(BuyRcptListVo param, HttpServletRequest request) throws Exception {
		return buyRcptListService.selectBuyRcptList(param);
	}
 

 

	/**
	 * 매입처입고현황 상세 조회 Event
	 * @param BuyRcptListVo param
	 * @param HttpServletRequest
	 * @return BuyRcptListVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/br/buy/buyRcptList_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BuyRcptListVo selectBuyRcptListDetail (HttpServletRequest request,  @RequestBody BuyRcptListVo param) throws Exception {
		return buyRcptListService.selectBuyRcptListData(param);
	}



	
	/**
	 * 매입처입고현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/br/buy/buyRcptList_selExcelDown")
	public ModelAndView selectBuyRcptListExcelDown(BuyRcptListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = buyRcptListService.selectBuyRcptListExcelDown(paramVo);

		//file name
		String fileName = "매입처입고현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","매입처명", "Lack번호", "창고명","품목코드","품목명","입고일자","규격","단가","수량","공급가","부가세","합계금액","발주일자","유통기한","소비기간","비고","매입처상품코드"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = { "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "BUY_NM", "LACK_NM","WH_NM", "PRDT_CD", "PRDT_NM", "BUY_DT", "PRDT_STD", "COST", "BUY_QTY", "PURE_AMT", "VAT_AMT", "TOT_AMT", "ORD_DT","EXPRT_DT", "TERM_VAL", "REMARKS", "BUY_PRDT_CD"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	
	 /**
	  * 매입처입고현황 출력
	  */
	 @RequestMapping(value="/app/br/buy/buyRcptListPrint", method = RequestMethod.POST)
	 public void buyRcptListPdfDownload(HttpServletRequest request, HttpServletResponse response, BuyRcptListVo buyRcptListVo) throws Exception{

		 List<BuyRcptListVo> list = buyRcptListService.selectBuyRcptPrintList(buyRcptListVo);
		 String jasperName = "buyRcptListPrt.jasper";
		 String fileName = "매입처 일별 품목 입고 현황";
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
