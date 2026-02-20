package com.doppio.workplace.as.controller;

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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.doppio.common.model.Result;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.as.service.SalSalesCarShipListService;
import com.doppio.workplace.as.service.SalSalesCarShipListVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 차량별 배송현황표 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesCarShipListController")
public class SalCarShipListController {

	private static final Logger logger = LoggerFactory.getLogger(SalCarShipListController.class);
	
	@Autowired
	SalSalesCarShipListService salSalesCarShipListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesCarShipList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesCarShipList";
 	}

	/**
	 * 차량별 배송현황표 조회 Event
	 * @param SalSalesCarShipListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salSalesCarShipList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalSalesCarShipList(SalSalesCarShipListVo param, HttpServletRequest request) throws Exception {
		return salSalesCarShipListService.selectSalSalesCarShipList(param);
	}
	
	/**
	 * 차량별 배송현황표 Footer 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/sal/salSalesCarShipFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalSalesCarShipListVo selectSalSalesCarShipFooterList (HttpServletRequest request,  @RequestBody SalSalesCarShipListVo param) throws Exception {
		return salSalesCarShipListService.selectSalSalesCarShipFooter(param);
	}
	 
	/**
	 * 차량별 배송현황표 다운로드
	 * @param SalSalesCarShipListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salSalesCarShipList_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(SalSalesCarShipListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesCarShipListService.selectSalSalesCarShipListExcelDown(paramVo);
		//file name
		String fileName = "차량별 배송현황표";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "호차","차량변호","배송기사명","매출처구분","매출처명","매출금액(부가세제외)","매출금액","매출이익","이익율(%)","중량(kg)"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0.00", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "CAR_NM", "CAR_NUM", "DRV_SNM", "SALES_CLASS", "SALES_NM", "PURE_AMT", "TOT_AMT", "SALES_PROFIT", "MAR_RATE", "WEIGHT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
