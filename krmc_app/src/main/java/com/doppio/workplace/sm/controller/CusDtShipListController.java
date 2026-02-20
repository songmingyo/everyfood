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
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.sm.service.CusDtShipListService;
import com.doppio.workplace.sm.service.CusDtShipListVo;


/**
 * @author j10000
 * @Description : 매출처일자별출고현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.06 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusDtShipListController")
public class CusDtShipListController {

	private static final Logger logger = LoggerFactory.getLogger(CusDtShipListController.class);
	
	@Autowired
	CusDtShipListService cusDtShipListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusDtShipList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusDtShipList";
 	}

	/**
	 * 매출처일자별출고현황
	 * @param CusDtShipListVo param
	 * @param HttpServletRequest
	 * @return List<CusDtShipListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusDtShipList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusDtShipListSearch(CusDtShipListVo param, HttpServletRequest request) throws Exception {
		return cusDtShipListService.selectCusDtShipList(param);
	}
 


	/**
	 * 매출처일자별출고현황 Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusDtShipList_selExcelDown")
	public ModelAndView selectCusDtShipListExcelDown(CusDtShipListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusDtShipListService.selectCusDtShipListExcelDown(paramVo);

		//file name
		String fileName = "매출처일자별출고현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","매출처명", "일자", "창고명","출고금액","부가세","합계금액"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_NM",  "SALES_DT", "WH_NM", "PURE_AMT", "VAT_AMT", "TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
