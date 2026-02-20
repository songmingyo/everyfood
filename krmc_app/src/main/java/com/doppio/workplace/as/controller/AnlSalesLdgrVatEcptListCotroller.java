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
import com.doppio.workplace.as.service.AnlSalesLdgrVatEcptListService;
import com.doppio.workplace.as.service.AnlSalesLdgrVatEcptListVo;


/**
 * @author j10000
 * @Description : 매출처원장부가세제외
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.20 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlSalesLdgrVatEcptListController")
public class AnlSalesLdgrVatEcptListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlSalesLdgrVatEcptListCotroller.class);
	
	@Autowired
	AnlSalesLdgrVatEcptListService anlSalesLdgrVatEcptListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlSalesLdgrVatEcptList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlSalesLdgrVatEcptList";
 	}

	/**
	 * 매출처원장부가세제외
	 * @param AnlSalesLdgrVatEcptListVo param
	 * @param HttpServletRequest
	 * @return List<AnlSalesLdgrVatEcptListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlSalesLdgrVatEcptList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlSalesLdgrVatEcptListSearch(AnlSalesLdgrVatEcptListVo param, HttpServletRequest request) throws Exception {
		return anlSalesLdgrVatEcptListService.selectAnlSalesLdgrVatEcptList(param);
	}
 


	/**
	 * 매출처원장부가세제외 Event 엑셀 다운로드
	 * @param AnlSalesLdgrVatEcptListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlSalesLdgrVatEcptList_selExcelDown")
	public ModelAndView selectAnlSalesLdgrVatEcptListExcelDown(AnlSalesLdgrVatEcptListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlSalesLdgrVatEcptListService.selectAnlSalesLdgrVatEcptListExcelDown(paramVo);

		//file name
		String fileName = "매출처원장(연간)";


		// 엑셀 필드타이틀
		String[] title = {"NO","매출처명", "1월", "2월","3월","4월","5월", "6월","7월","8월","9월","10월","11월","12월","합게","영없사원"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_NM", "MON_1", "MON_2", "MON_3", "MON_4", "MON_5", "MON_6", "MON_7", "MON_8", "MON_9", "MON_10", "MON_11", "MON_12", "TOT_AMT", "SALES_PR_NM"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
