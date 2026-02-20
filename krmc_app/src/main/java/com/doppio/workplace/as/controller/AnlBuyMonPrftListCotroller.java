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
import com.doppio.workplace.as.service.AnlBuyMonPrftListService;
import com.doppio.workplace.as.service.AnlBuyMonPrftListVo;
import com.doppio.workplace.as.service.AnlCreditSalesListVo;


/**
 * @author j10000
 * @Description : 매입처월별이익현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.06.24 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlBuyMonPrftListController")
public class AnlBuyMonPrftListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlBuyMonPrftListCotroller.class);
	
	@Autowired
	AnlBuyMonPrftListService anlBuyMonPrftListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlBuyMonPrftList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlBuyMonPrftList";
 	}

	/**
	 * 매입처월별이익현황
	 * @param AnlBuyMonPrftListVo param
	 * @param HttpServletRequest
	 * @return List<AnlBuyMonPrftListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlBuyMonPrftList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlBuyMonPrftListSearch(AnlBuyMonPrftListVo param, HttpServletRequest request) throws Exception {
		return anlBuyMonPrftListService.selectAnlBuyMonPrftList(param);
	}
 
	/**
	 * 매입처월별이익현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlBuyMonPrftList_selExcelDown")
	public ModelAndView selectAnlBuyMonPrftListExcelDown(AnlBuyMonPrftListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlBuyMonPrftListService.selectAnlBuyMonPrftListExcelDown(paramVo);

		//file name
		String fileName = "매입처월별이익현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","매입처", "항목","01월","02월","03월","04월","05월", "06월","07월","08월","09월","10월","11월","12월", "합계"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "BUY_NM", "CLASS_NM", "MON_1", "MON_2", "MON_3", "MON_4",  "MON_5", "MON_6", "MON_7", "MON_8", "MON_9", "MON_10", "MON_11", "MON_12", "TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
}
