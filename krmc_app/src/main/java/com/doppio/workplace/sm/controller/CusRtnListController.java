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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.sm.service.CusRtnListService;
import com.doppio.workplace.sm.service.CusRtnListVo;


/**
 * @author j10000
 * @Description : 매출처반품현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.07 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusRtnListController")
public class CusRtnListController {

	private static final Logger logger = LoggerFactory.getLogger(CusRtnListController.class);
	
	@Autowired
	CusRtnListService cusRtnListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusRtnList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusRtnList";
 	}

	/**
	 * 매출처반품현황
	 * @param CusRtnListVo param
	 * @param HttpServletRequest
	 * @return List<CusRtnListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusRtnList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusRtnListSearch(CusRtnListVo param, HttpServletRequest request) throws Exception {
		return cusRtnListService.selectCusRtnList(param);
	}
 


	/**
	 * 매출처반품현황 Event 엑셀 다운로드
	 * @param CusRtnListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusRtnList_selExcelDown")
	public ModelAndView selectCusRtnListExcelDown(CusRtnListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusRtnListService.selectCusRtnListExcelDown(paramVo);

		//file name
		String fileName = "매출처반품현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "전표번호", "창고명","매출처명","품목코드","품목명","규격","반품구분","단가","수량","공급가","부가세","합계금액","비고","최초등록자","최초등록시간"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGTH/#,##0", "RIGTH/#,##0.00", "RIGTH/#,##0", "RIGTH/#,##0", "RIGTH/#,##0", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "RTN_DT",  "SALES_SLIP_NO", "WH_NM", "SALES_NM", "PRDT_CD", "PRDT_NM",  "PRDT_STD", "DTL_NM", "PRICE", "RTN_QTY", "PURE_AMT", "VAT_AMT",  "TOT_AMT", "REMARKS", "REG_ID", "REG_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
