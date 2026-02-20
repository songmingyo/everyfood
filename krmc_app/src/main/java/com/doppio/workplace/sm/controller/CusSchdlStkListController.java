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
import com.doppio.workplace.br.service.BuyInspectListVo;
import com.doppio.workplace.sm.service.CusSchdlStkListService;
import com.doppio.workplace.sm.service.CusSchdlStkListVo;


/**
 * @author j10000
 * @Description : 출고예정 재고리스트 
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04.23 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusSchdlStkListController")
public class CusSchdlStkListController {

	private static final Logger logger = LoggerFactory.getLogger(CusSchdlStkListController.class);
	
	@Autowired
	CusSchdlStkListService cusSchdlStkListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusSchdlStkList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusSchdlStkList";
 	}

	/**
	 * 출고예정 재고리스트 
	 * @param CusSchdlStkListVo param
	 * @param HttpServletRequest
	 * @return List<CusSchdlStkListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSchdlStkList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusSchdlStkList(CusSchdlStkListVo param, HttpServletRequest request) throws Exception {
		return cusSchdlStkListService.selectCusSchdlStkList(param);
	}
 


	
	/**
	 * 출고예정 재고리스트  Event 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusSchdlStkList_selExcelDown")
	public ModelAndView selectCusSchdlStkListExcelDown(CusSchdlStkListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusSchdlStkListService.selectCusSchdlStkListExcelDown(paramVo);

		//file name
		String fileName = "출고예정재고리스트";

		// 엑셀 필드타이틀
		String[] title = {"NO", "품목명","규격","단위","전월출고량","현재고(조회시점)","출고수량","잔여수량(출고일자기준)","재고일수"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGTH/#,##0.00", "RIGTH/#,##0.00", "RIGTH/#,##0.00", "RIGTH/#,##0.00", "RIGTH/#,##0.00"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "PRDT_NM", "PRDT_STD", "ORD_UNIT_NM", "PRE_SALES_QTY", "STK_QTY", "SALES_QTY", "REM_STK_QTY", "STK_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	
}
