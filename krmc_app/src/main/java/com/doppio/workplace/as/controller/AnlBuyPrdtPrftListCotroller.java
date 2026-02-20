package com.doppio.workplace.as.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AccSalesDepListVo;
import com.doppio.workplace.as.service.AnlBuyPrdtPrftListService;
import com.doppio.workplace.as.service.AnlBuyPrdtPrftListVo;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;


/**
 * @author j10000
 * @Description : 매입처품목별이익현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlBuyPrdtPrftListController")
public class AnlBuyPrdtPrftListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlBuyPrdtPrftListCotroller.class);
	
	@Autowired
	AnlBuyPrdtPrftListService anlBuyPrdtPrftListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlBuyPrdtPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlBuyPrdtPrftList";
 	}

	/**
	 * 매입처품목별이익현황
	 * @param AnlBuyPrdtPrftListVo param
	 * @param HttpServletRequest
	 * @return List<AnlBuyPrdtPrftListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlBuyPrdtPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlBuyPrdtPrftListSearch(AnlBuyPrdtPrftListVo param, HttpServletRequest request) throws Exception {
		return anlBuyPrdtPrftListService.selectAnlBuyPrdtPrftList(param);
	}
 
	/**
	 * 매입처품목별이익현황 Footer 조회 Event
	 * @param AnlBuyPrdtPrftListVo param
	 * @param HttpServletRequest
	 * @return AnlBuyPrdtPrftListVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/anlys/anlBuyPrdtPrftFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody AnlBuyPrdtPrftListVo selectAnlBuyPrdtPrftFooter (HttpServletRequest request,  @RequestBody AnlBuyPrdtPrftListVo param) throws Exception {
		return anlBuyPrdtPrftListService.selectAnlBuyPrdtPrftFooter(param);
	}
	
	/**
	 * 매입처품목별이익현황 Event 엑셀 다운로드
	 * @param AnlBuyPrdtPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlBuyPrdtPrftList_selExcelDown")
	public ModelAndView selectAnlBuyPrdtPrftListExcelDown(AnlBuyPrdtPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlBuyPrdtPrftListService.selectAnlBuyPrdtPrftListExcelDown(paramVo);

		//file name
		String fileName = "매입처품목별이익현황";

		// 엑셀 필드타이틀
		String[] title = {"NO","매입처명", "품목코드", "품목명","규격","입고단가","입고금액", "출고단가","출고금액","매출이익","마진율(%)","출고수량"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "BUY_NM",  "PRDT_CD", "PRDT_NM", "PRDT_STD", "COST", "BUY_AMT", "PRICE",  "PURE_AMT", "SALES_PRFT", "MAR_RATE", "SALES_QTY"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

	
}
