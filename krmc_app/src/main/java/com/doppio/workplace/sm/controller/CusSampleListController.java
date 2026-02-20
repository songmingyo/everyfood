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
import com.doppio.workplace.sm.service.CusSampleListService;
import com.doppio.workplace.sm.service.CusSampleListVo;


/**
 * @author j10000
 * @Description : 샘플출고현황
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.05 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusSampleListController")
public class CusSampleListController {

	private static final Logger logger = LoggerFactory.getLogger(CusSampleListController.class);
	
	@Autowired
	CusSampleListService cusSampleListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusSampleList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusSampleList";
 	}

	/**
	 * 샘플출고현황
	 * @param CusSampleListVo param
	 * @param HttpServletRequest
	 * @return List<CusSampleListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSampleList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusSampleListSearch(CusSampleListVo param, HttpServletRequest request) throws Exception {
		return cusSampleListService.selectCusSampleList(param);
	}
 


	/**
	 * 샘플출고현황 Event 엑셀 다운로드
	 * @param CusSampleListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusSampleList_selExcelDown")
	public ModelAndView selectCusSampleListExcelDown(CusSampleListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusSampleListService.selectCusSampleListExcelDown(paramVo);

		//file name
		String fileName = "샘플출고현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "창고명", "매출처명","품목코드","품목명","규격","구분","수량","단가","공급가","영업담당자","비고"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGTH/#,##0.00", "RIGTH/#,##0", "RIGTH/#,##0", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "FREE_DT", "WH_NM", "SALES_NM", "PRDT_CD", "PRDT_NM", "PRDT_STD", "FREE_CLASS_NM", "FREE_QTY", "COST", "TOT_AMT", "SALES_PR_NM", "REMARKS"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
