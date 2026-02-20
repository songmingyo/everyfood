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
import com.doppio.workplace.as.service.AnlBuyLdgrDtListVo;
import com.doppio.workplace.as.service.AnlBuyLdgrMonListService;
import com.doppio.workplace.as.service.AnlBuyLdgrMonListVo;


/**
 * @author j10000
 * @Description : 매입처원장(월별)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlBuyLdgrMonListController")
public class AnlBuyLdgrMonListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlBuyLdgrMonListCotroller.class);
	
	@Autowired
	AnlBuyLdgrMonListService anlBuyLdgrMonListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlBuyLdgrMonList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlBuyLdgrMonList";
 	}

	/**
	 * 매입처원장(년간)
	 * @param AccSalesDepListVO param
	 * @param HttpServletRequest
	 * @return List<AccSalesDepListVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlBuyLdgrMonList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlBuyLdgrMonListSearch(AnlBuyLdgrMonListVo param, HttpServletRequest request) throws Exception {
		return anlBuyLdgrMonListService.selectAnlBuyLdgrMonList(param);
	}

	/**
	  * 매입처원장(년간) 출력
	  */
	 @RequestMapping(value="/app/as/anlys/buyLdgrMonListPrint", method = RequestMethod.POST)
	 public void buyLdgrMonListPrintPdfDownload(HttpServletRequest request, HttpServletResponse response, AnlBuyLdgrMonListVo anlBuyLdgrMonListVo) throws Exception{

		List<AnlBuyLdgrMonListVo> list = anlBuyLdgrMonListService.selectBuyLdgrMonListPrint(anlBuyLdgrMonListVo);
			
		String jasperName = "anlBuyLdgrMonPrt.jasper";
		String fileName = "매입처원장(년간)";
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
	 * 매입처원장(년간) Event 엑셀 다운로드
	 * @param AnlAccRecvListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlBuyLdgrMonList_selExcelDown")
	public ModelAndView selectAnlBuyLdgrMonListExcelDown(AnlBuyLdgrMonListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlBuyLdgrMonListService.selectAnlBuyLdgrMonListExcelDown(paramVo);

		//file name
		String fileName = "매입처원장(년간)";


		// 엑셀 필드타이틀
		String[] title = {"NO","매입처명", "항목", "01월","02월","03월","04월", "05월","06월","07월","08월","09월","10월","11월","12월","합계"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0	", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "BUY_NM", "CLASS_NM", "MON1", "MON2", "MON3", "MON4", "MON5", "MON6", "MON7", "MON8", "MON9", "MON10", "MON11","MON12","TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
}
