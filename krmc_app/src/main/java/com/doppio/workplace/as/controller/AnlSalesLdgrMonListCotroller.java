package com.doppio.workplace.as.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AnlSalesLdgrMonListService;
import com.doppio.workplace.as.service.AnlSalesLdgrMonListVo;


/**
 * @author j10000
 * @Description : 매출처원장(월별)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.20 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlSalesLdgrMonListController")
public class AnlSalesLdgrMonListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlSalesLdgrMonListCotroller.class);
	
	@Autowired
	AnlSalesLdgrMonListService anlSalesLdgrMonListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlSalesLdgrMonList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlSalesLdgrMonList";
 	}

	/**
	 * 매출처원장(월별)
	 * @param AnlSalesLdgrMonListVo param
	 * @param HttpServletRequest
	 * @return List<AnlSalesLdgrMonListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlSalesLdgrMonList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlSalesLdgrMonListSearch(AnlSalesLdgrMonListVo param, HttpServletRequest request) throws Exception {
		return anlSalesLdgrMonListService.selectAnlSalesLdgrMonList(param);
	}
 


	/**
	 * 매출처원장(월별) Event 엑셀 다운로드
	 * @param AnlSalesLdgrMonListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlSalesLdgrMonList_selExcelDown")
	public ModelAndView selectAnlCreditSalesListExcelDown(AnlSalesLdgrMonListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlSalesLdgrMonListService.selectAnlSalesLdgrMonListExcelDown(paramVo);

		//file name
		String fileName = "매출처원장(일별)";


		// 엑셀 필드타이틀
		String[] title = {"NO", "월", "매출처명", "구분", "공급가", "부가세", "합계(VAT포함)", "현금", "어음", "장려금", "기타", "당월현잔액"};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "YEAR_MON", "SALES_NM", "GUBUN", "PURE_AMT", "VAT_AMT", "TOT_AMT", "PAID_AMT1",  "PAID_AMT2", "PAID_AMT3", "PAID_AMT4", "BAL_AMT"};

		ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
