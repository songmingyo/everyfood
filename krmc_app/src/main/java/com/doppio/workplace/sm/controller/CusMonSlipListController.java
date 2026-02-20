package com.doppio.workplace.sm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.doppio.workplace.sm.service.CusMonSlipListService;
import com.doppio.workplace.sm.service.CusMonSlipListVo;


/**
 * @author j10000
 * @Description : 매출처월별출고현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.05 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusMonSlipListController")
public class CusMonSlipListController {

	private static final Logger logger = LoggerFactory.getLogger(CusMonSlipListController.class);
	
	@Autowired
	CusMonSlipListService cusMonSlipListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusMonSlipList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusMonSlipList";
 	}

	/**
	 * 매출처월별출고현황
	 * @param CusMonSlipListVo param
	 * @param HttpServletRequest
	 * @return List<CusMonSlipListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusMonSlipList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusOrdListSearch(CusMonSlipListVo param, HttpServletRequest request) throws Exception {
		return cusMonSlipListService.selectCusMonSlipList(param);
	}
 


	/**
	 * 매출처월별출고현황 Event 엑셀 다운로드
	 * @param CusMonSlipListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusMonSlipList_selExcelDown")
	public ModelAndView selectCusOrdListExcelDown(CusMonSlipListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusMonSlipListService.selectCusMonSlipListExcelDown(paramVo);

		//file name
		String fileName = "매출처월별출고현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","구분", "고객사명", "창고명","과세","부가세","과세합계","면세","합계"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CLASS_NM", "SALES_NM", "WH_NM", "PURE_AMT", "VAT_AMT", "PURE_TOT_AMT", "FREE_AMT", "TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
