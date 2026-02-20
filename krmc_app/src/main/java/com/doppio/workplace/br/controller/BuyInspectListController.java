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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyInspectListService;
import com.doppio.workplace.br.service.BuyInspectListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author j10000
 * @Description : 매입처 검수 확인
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "buyInspectListController")
public class BuyInspectListController {

	private static final Logger logger = LoggerFactory.getLogger(BuyInspectListController.class);
	
	@Autowired
	BuyInspectListService buyInspectListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyInspectList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/br/purBuyInspectList";
 	}

	/**
	 * 매입처 검수현황 Event
	 * @param BuyInspectListVo param
	 * @param HttpServletRequest
	 * @return List<BuyInspectListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyInspectList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalesOrderSearch(BuyInspectListVo param, HttpServletRequest request) throws Exception {
		return buyInspectListService.selectBuyConfirmList(param);
	}
 

	 /**
	  * 매입처 검수 출력
	  */
	 @RequestMapping(value="/app/br/buy/buyInspectListPrint", method = RequestMethod.POST)
	 public void ordListPdfDownload(HttpServletRequest request, HttpServletResponse response, BuyInspectListVo buyInspectListVo) throws Exception{

		 List<BuyInspectListVo> list = buyInspectListService.selectBuyConfirmPrt(buyInspectListVo);
		 String jasperName = "buyConfrmPrt.jasper";
		 String fileName = "매입검수확인서";
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

	/**
	 * 매입처 검수현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/br/buy/purBuyInspectList_selExcelDown")
	public ModelAndView selectBuyInspectListExcelDown(BuyInspectListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = buyInspectListService.selectBuyInspectListExcelDown(paramVo);

		//file name
		String fileName = "매입검수확인서";


		// 엑셀 필드타이틀
		String[] title = {"NO", "매입처명", "랙번호", "창고명", "품목명","규격","단위","유통기한","소비기한","전월출고","주간출고","하남재고","발주량","박스수량","검수자","발주등록자","발주일자"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "BUY_NM", "LACK_NO_1", "WH_NM", "PRDT_NM", "PRDT_STD", "ORD_UNIT_NM", "TIME_LIMIT", "TERM_VAL", "B_ISS_QTY", "W_ISS_QTY", "STK_QTY", "ORD_QTY", "REG_NM", "ORD_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

}
	

	

