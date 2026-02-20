package com.doppio.workplace.cs.controller;

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
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.doppio.common.model.Result;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.cs.service.ClsBadStkListService;
import com.doppio.workplace.cs.service.ClsBadStkListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 악성재고조회
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "clsBadStkListController")
public class ClsBadStkListController {

	private static final Logger logger = LoggerFactory.getLogger(ClsBadStkListController.class);
	
	@Autowired
	ClsBadStkListService clsBadStkListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/clsBadStkList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/clsBadStkList";
 	}

	/**
	 * 악성재고조회 조회 Event
	 * @param ClsBadStkListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/clsBadStkList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClsBadStkListList(ClsBadStkListVo param, HttpServletRequest request) throws Exception {
		return clsBadStkListService.selectClsBadStkList(param);
	}
	

	 
	/**
	 * 악성재고조회 엑셀 다운로드
	 * @param ClsBadStkListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/cs/clsBadStkList_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(ClsBadStkListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = clsBadStkListService.selectClsBadStkListExcel(paramVo);

		//file name
		String fileName = "악성재고조회";


		// 엑셀 필드타이틀
		String[] title = {"NO", "매입처명", "랙번호", "품목코드", "품목명", "규격", "단위", "현재고", "출고수량(평균)", "재고일수", "단가", "재고금액", "유통기한", "매출처", "영업사원", "최종입고일", "최종출고일", "최종입고소비기한", "최종출고매출처"};		

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "BUY_NM", "LACK_NO_1", "PRDT_CD", "PRDT_NM", "PRDT_STD", "ORD_UNIT_NM", "STK_QTY", "SALES_QTY_AVG", "STK_DAY", "BUY_COST", "STK_AMT", "EXPRT_DT", "SALES_NM", "SALES_PR_NM", "BUY_DT_LAST", "SALES_DT_LAST", "TERM_VAL_LAST", "SALES_NM_LAST"};
		
		ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

	
	
}
