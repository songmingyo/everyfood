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
import com.doppio.workplace.as.service.SalSalesGoalRegService;
import com.doppio.workplace.as.service.SalSalesGoalRegVo;
import com.doppio.workplace.sm.service.CusSampleRegVo;

/**
 * @author Song
 * @Description : 영업목표 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salSalesGoalRegController")
public class SalSalesGoalRegController {

	private static final Logger logger = LoggerFactory.getLogger(SalSalesGoalRegController.class);
	
	@Autowired
	SalSalesGoalRegService salSalesGoalRegService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/salesmgmt/salSalesGoalReg")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/salSalesGoalReg";
 	}

	/**
	 * 영업목표 조회 Event
	 * @param SalSalesGoalRegVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */                      
	@RequestMapping(value= {"/app/as/sal/salSalesGoalReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<SalSalesGoalRegVo> selectSalSalesGoalRegList(SalSalesGoalRegVo param, HttpServletRequest request) throws Exception {
		return salSalesGoalRegService.selectSalSalesGoalRegList(param);
	}

	/**
	 * 영업목표 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/as/sal/salSalesGoalReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salSalesGoalReg (HttpServletRequest request,  @RequestBody SalSalesGoalRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = salSalesGoalRegService.insertSalSalesGoalReg(param);
		
		return result;
	}
	 
	/**
	 * 영업목표 다운로드
	 * @param SalSalesGoalRegVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/sal/salSalesGoalReg_selExcelDown")
	public ModelAndView selectExcelDownSalesPrdtPrft(SalSalesGoalRegVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salSalesGoalRegService.selectSalSalesGoalRegExcelDown(paramVo);

		//file name
		String fileName = "영업목표";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "사원명", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월", "합계"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_PR_NM", "GOAL_AMT01", "GOAL_AMT02", "GOAL_AMT03", "GOAL_AMT04", "GOAL_AMT05", "GOAL_AMT06", "GOAL_AMT07", "GOAL_AMT08", "GOAL_AMT09", "GOAL_AMT10", "GOAL_AMT11", "GOAL_AMT12", "GOAL_TOT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
