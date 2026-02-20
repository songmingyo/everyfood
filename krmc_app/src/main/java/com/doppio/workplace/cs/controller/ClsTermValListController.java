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
import com.doppio.workplace.cs.service.ClsTermValListService;
import com.doppio.workplace.cs.service.ClsTermValListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 소비기한관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "clsTermValListController")
public class ClsTermValListController {

	private static final Logger logger = LoggerFactory.getLogger(ClsTermValListController.class);
	
	@Autowired
	ClsTermValListService clsTermValListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/clsTermValList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/clsTermValList";
 	}

	/**
	 * 소비기한관리 조회 Event
	 * @param ClsTermValListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/clsTermValList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClsTermValListList(ClsTermValListVo param, HttpServletRequest request) throws Exception {
		return clsTermValListService.selectClsTermValList(param);
	}
	

	 
	/**
	 * 소비기한관리 엑셀 다운로드
	 * @param ClsTermValListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/cs/clsTermValList_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(ClsTermValListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = clsTermValListService.selectClsTermValListExcel(paramVo);

		//file name
		String fileName = "소비기한관리";


		// 엑셀 필드타이틀
		String[] title = {"NO", "매입처명", "품명", "랙번호", "규격", "단위", "입고일자", "입고수량", "최종출고일", "전월출고량", "현재고", "매입단가", "매입금액", "소비기한", "유통기한", "매출처", "영업사원", "최근출고거래처(5개)"};		

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "BUY_NM", "PRDT_NM", "LACK_NO_1", "PRDT_STD", "ORD_UNIT_NM", "BUY_DT", "BUY_QTY", "SALES_LAST_DT", "PREV_SALES_QTY", "STK_QTY", "BUY_COST", "PURE_AMT", "TERM_VAL", "EXPRT_DT", "SALES_NM", "SALES_PR_NM", "SALES_CD_LIST"};
		
		ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

	
	
}
