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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver_bak;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyRcptListVo;
import com.doppio.workplace.sm.service.CusOrdListService;
import com.doppio.workplace.sm.service.CusOrdListVo;


/**
 * @author j10000
 * @Description : 매출처 발주현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusOrdListController")
public class CusOrdListController {

	private static final Logger logger = LoggerFactory.getLogger(CusOrdListController.class);
	
	@Autowired
	CusOrdListService cusOrdListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusOrdList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusOrdList";
 	}

	/**
	 * 매출처 발주현황
	 * @param AccSalesDepListVo param
	 * @param HttpServletRequest
	 * @return List<CusOrdListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusOrdList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusOrdListSearch(CusOrdListVo param, HttpServletRequest request) throws Exception {
		return cusOrdListService.selectCusOrdList(param);
	}
 


	/**
	 * 매출처 발주현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusOrdList_selExcelDown")
	public ModelAndView selectCusOrdListExcelDown(CusOrdListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusOrdListService.selectCusOrdListExcelDown(paramVo);

		//file name
		String fileName = "매출처발주현황";

		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "전표번호", "매출처명","품목코드","품목명","규격","출고예정일","단가","수량","공급가","부가세","합계금액","비고","최초등록자","최초등록시간"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "ORD_DT", "SALES_SLIP_NO", "SALES_NM", "PRDT_CD", "PRDT_NM", "PRDT_STD", "DLV_DT",  "PRICE","ORD_QTY", "PURE_AMT", "VAT_AMT","TOT_AMT","REMARKS_1","USER_NM","REG_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
//		modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver_bak", "excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
}
