package com.doppio.workplace.br.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyInspectListVo;
import com.doppio.workplace.br.service.BuyOrdListService;
import com.doppio.workplace.br.service.BuyOrdListVo;


/**
 * @author j10000
 * @Description : 매입처관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "buyOrdListController")
public class BuyOrdListController {

	private static final Logger logger = LoggerFactory.getLogger(BuyOrdListController.class);
	
	@Autowired
	BuyOrdListService buyOrdListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyOrdList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/br/purBuyOrdList";
 	}

	/**
	 * 매입처 마스터 조회 Event
	 * @param BuyOrdListVo param
	 * @param HttpServletRequest
	 * @return List<BuyOrderVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyOrdList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalesOrderSearch(BuyOrdListVo param, HttpServletRequest request) throws Exception {
		return buyOrdListService.selectBuyOrderList(param);
	}
 

	/**
	 * 매입처 검수현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/br/buy/buyOrdList_selExcelDown")
	public ModelAndView selectBuyOrdListExcelDown(BuyOrdListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = buyOrdListService.selectBuyOrdListExcelDown(paramVo);

		//file name
		String fileName = "매입발주현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "매입처명", "품목코드","품목명","규격","입고예정일","단가","수량","공급가","부가세","합계금액","비고"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = { "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "ORD_DT",  "BUY_NM", "PRDT_CD", "PRDT_NM", "PRDT_STD", "BUY_DT", "COST", "ORD_QTY", "PURE_AMT", "VAT_AMT", "TOT_AMT", "REMARKS"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	

}
