package com.doppio.workplace.as.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.as.service.SalSalesListService;
import com.doppio.workplace.as.service.SalSalesListVo;

import org.springframework.tronic.util.FileUtil;

/**
 * @author Song min kyo
 * @Description : 영업신규현황 조회
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesListController")
public class SalSalesListController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesListController.class);
	
	@Autowired
	SalSalesListService salSalesListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesMstList")		
 	public String salSalesMstList() throws Exception{
 		return "app/as/salSalesMstList";
 	}

	/**
	 * 영업신규 현황  조회 Event
	 * @param SalSalesListVo param
	 * @param HttpServletRequest
	 * @return List<SalSalesListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/sal/salSalesMstList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesGoalListList(SalSalesListVo param, HttpServletRequest request) throws Exception {
		return salSalesListService.selectSalSalesMstList(param);
	}
	 
	/**
	 * 영업 신규개설 현황 excel 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping(value="/app/as/sal/salSalesMstList_selExcelDown")
	public ModelAndView salSalesMstListSelExcelDown(SalSalesListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesListService.salSalesMstListSelExcelDown(paramVo);

		//file name
		String fileName = "영업 신규개설 현황";
		
		// 엑셀 필드타이틀
//		String[] title = {"NO", "거래시작일", "거래처명", "매출처구분", "업태", "종목", "거래품목수", "매장수", "결재조건", "에상 매출액",  "장려금 비율",  "보증금",  "여신한도", "물류대행사", "개설자",  "담당자", "거래종료일"};
		String[] title = {"NO", "거래시작일", "거래처명", "매출처구분", "업태", "종목", "출고금액", "매출이익", "마진율(%)",  "결재조건", "장려금",  "보증금",  "여신한도", "물류대행사", "개설자",  "담당자", "거래종료일"};
		
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
//		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER", "CENTER"};
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0.00", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
//		int[] cellWidth = {0, 20, 50, 20, 50, 50, 20, 20, 20, 20, 20, 20, 20, 20, 10, 20, 20};
		int[] cellWidth = {0, 20, 50, 20, 50, 50, 30, 30, 20, 20, 20, 20, 20, 20, 10, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
//		String[] keyset = {"NO",  "START_DT", "SALES_NM", "SALES_CLASS_NM", "BUSI_CON", "BUSI_TYPE", "CNT", "STORE_CNT", "SETL_CON", "EXP_SALES_AMT", "SUBSIDY_RATE", "WARR_AMT", "CRE_LIM", "CAR_NM", "NEW_SALES_PR_CD_NM", "SALES_PR_CD_NM", "END_DT"};
		String[] keyset = {"NO",  "START_DT", "SALES_NM", "SALES_CLASS_NM", "BUSI_CON", "BUSI_TYPE", "PURE_AMT", "PROFIT_AMT_3", "MAR_RATE_3", "SETL_CON", "SUBSIDY_RATE", "WARR_AMT", "CRE_LIM", "CAR_NM", "NEW_SALES_PR_CD_NM", "SALES_PR_CD_NM", "END_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
