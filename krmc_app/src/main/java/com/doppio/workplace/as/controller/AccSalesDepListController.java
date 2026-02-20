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
import com.doppio.workplace.as.service.AccSalesDepListService;
import com.doppio.workplace.as.service.AccSalesDepListVo;


/**
 * @author j10000
 * @Description : 매출처별입금현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.14 j10000
 * </pre>
 * @version :
 */
@Controller(value = "accSalesDepListController")
public class AccSalesDepListController {

	private static final Logger logger = LoggerFactory.getLogger(AccSalesDepListController.class);
	
	@Autowired
	AccSalesDepListService accSalesDepListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/account/accSalesDepList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/accSalesDepList";
 	}

	/**
	 * 매출처별입금현황
	 * @param AccSalesDepListVO param
	 * @param HttpServletRequest
	 * @return List<AccSalesDepListVO>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/account/accSalesDepList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAccSalesDepListSearch(AccSalesDepListVo param, HttpServletRequest request) throws Exception {
		return accSalesDepListService.selectAccSalesDepList(param);
	}
 


	/**
	 * 매출처별입금현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/account/accSalesDepList_selExcelDown")
	public ModelAndView selectAccSalesDepListExcelDown(AccSalesDepListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = accSalesDepListService.selectAccSalesDepListExcelDown(paramVo);

		//file name
		String fileName = "매출처별입금현황";

		// 엑셀 필드타이틀
		String[] title = {"NO","입금일자", "매출처명", "계좌입금","현금","카드","어음", "장려금","수수료","수입","기타","합계","수금조건","적요"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "DEP_DT",  "SALES_NM", "ACCOUNT_AMT", "CASH_AMT", "CARD_AMT", "NOTE_AMT", "SUBSIDY_AMT",  "VAT_AMT", "INCOME_AMT", "ETC_AMT", "TOT_AMT", "SETL_CON", "REMARKS"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
