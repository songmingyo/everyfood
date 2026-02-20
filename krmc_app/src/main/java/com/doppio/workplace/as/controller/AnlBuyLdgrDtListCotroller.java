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
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.as.service.AnlAccRecvListVo;
import com.doppio.workplace.as.service.AnlBuyLdgrDtListService;
import com.doppio.workplace.as.service.AnlBuyLdgrDtListVo;


/**
 * @author j10000
 * @Description : 매입처원장(일별)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlBuyLdgrDtListController")
public class AnlBuyLdgrDtListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlBuyLdgrDtListCotroller.class);
	
	@Autowired
	AnlBuyLdgrDtListService anlBuyLdgrDtListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlBuyLdgrDtList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlBuyLdgrDtList";
 	}

	/**
	 * 매입처원장(일별)
	 * @param AccSalesDepListVO param
	 * @param HttpServletRequest
	 * @return List<AccSalesDepListVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlBuyLdgrDtList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlBuyLdgrDtListSearch(AnlBuyLdgrDtListVo param, HttpServletRequest request) throws Exception {
		return anlBuyLdgrDtListService.selectAnlBuyLdgrDtList(param);
	}

	/**
	  * 매입처원장(일별) 출력
	  */
	 @RequestMapping(value="/app/as/anlys/buyLdgrListPrint", method = RequestMethod.POST)
	 public void buyLdgrListPrintPdfDownload(HttpServletRequest request, HttpServletResponse response, AnlBuyLdgrDtListVo anlBuyLdgrDtListVo) throws Exception{

		List<AnlBuyLdgrDtListVo> list = anlBuyLdgrDtListService.selectbuyLdgrListPrint(anlBuyLdgrDtListVo);
		String jasperName = "buyLdgrDtPrt.jasper";
		String fileName = "매입처원장(일별)";
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
	 * 매입처원장(일별) Event 엑셀 다운로드
	 * @param AnlAccRecvListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlBuyLdgrDtList_selExcelDown")
	public ModelAndView selectAnlBuyLdgrDtListExcelDown(AnlBuyLdgrDtListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlBuyLdgrDtListService.selectAnlBuyLdgrDtListExcelDown(paramVo);

		//file name
		String fileName = "매입처원장(일별)";


		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "매입처명", "구분","품명","규격","수량", "단가","공급가","부가세","합계(VAT포함)","현금","어음","장려금","기타","당월현잔액"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "DT_CLASS", "BUY_NM", "SUB_CLASS", "PRDT_NM", "PRDT_STD", "QTY",  "COST", "PURE_AMT", "VAT_AMT", "TOT_AMT", "PAID_AMT1", "PAID_AMT2", "PAID_AMT3","PAID_AMT4","BAL_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	 
}
