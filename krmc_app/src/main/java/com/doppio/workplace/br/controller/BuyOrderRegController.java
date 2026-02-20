package com.doppio.workplace.br.controller;

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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
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
import com.doppio.workplace.br.service.BuyOrderRegService;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 매입처 발주 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "buyOrderRegController")
public class BuyOrderRegController {

	private static final Logger logger = LoggerFactory.getLogger(BuyOrderRegController.class);
	
	@Autowired
	BuyOrderRegService buyOrderRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/br/buy/buyOrderReg")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/br/purBuyOrderReg";
 	}

	/**
	 * 매입처 발주 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyOrderReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyOrderRegList(BuyOrderRegVo param, HttpServletRequest request) throws Exception {
		return buyOrderRegService.selectBuyOrderRegList(param);
	}
	
	/**
	 * 매입처 발주 추가 품목 하나 조회 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/br/buy/buyOrderReg_selPrdtAddList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectBuyOrderRegPrdtAddList(@RequestBody BuyOrderRegVo param, HttpServletRequest request) throws Exception {
		return buyOrderRegService.selectBuyOrderRegPrdtAddList(param);
	}
 

	/**
	 * 매입처 발주 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/br/buy/buyOrderReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result buyOrdReg (HttpServletRequest request,  @RequestBody BuyOrderRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = buyOrderRegService.insertBuyOrderReg(param);
		
		return result;
	}
	
	/**
	 * 매입처 발주 노트 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/br/buy/buyOrderReg_insOrdNote.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result buyOrdNoteReg (HttpServletRequest request,  @RequestBody BuyOrderRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = buyOrderRegService.insertBuyOrdNoteReg(param);
		
		return result;
	}
	
	/**
	 * 매입처 발주서 출력 건수 확인
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	
	@RequestMapping(value= "/app/br/buy/ordListPrint_selCnt.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result selectBuyOrderListPrintCnt(@RequestBody BuyOrderRegVo param, HttpServletRequest request) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = buyOrderRegService.selectBuyOrderListPrintCnt(param);
		
		return result;
	}
	
	 /**
	  * 발주내역 발주서 출력
	  */
	 @RequestMapping(value="/app/br/buy/ordListPrint", method = RequestMethod.POST)
	 public void ordListPdfDownload(HttpServletRequest request, HttpServletResponse response, BuyOrderRegVo buyOrderRegVo) throws Exception{

		List<BuyOrderRegVo> list = buyOrderRegService.selectOrdPrintList(buyOrderRegVo);
		String jasperName = "ordListPrt.jasper";
		String fileName = "발주서 내역";
		String viewType = "browser";

		List<Map<String, Object>> contFileMapList = new ArrayList<>();
		Map<String, Object> contFileMap = new HashMap<String, Object>();

		contFileMap.put("jasperName1", jasperName);
		contFileMapList.add(contFileMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list); 										//DATA
		map.put("viewType", viewType);								//pdf, excel, ppt
		map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		map.put("fileName", fileName);								//파일 이름
		
		jasperDownloadService.jasperDocumentDownload(map, request, response);
	 }
	 
	/**
	 * 매입처 발주 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/br/buy/buyOrderReg_selExcelDown")
	public ModelAndView selectBuyOrderReg_selExcelDown(BuyOrderRegVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();
		
		List<HashMap<String, String>> datalist = buyOrderRegService.selectBuyOrdRegListExcelDown(paramVo);
		

		//file name
		String fileName = "매입처 발주품목 발주내역";


		// 엑셀 필드타이틀
		String[] title = {"NO", "품목코드","매입처명","랙번호","품명","규격","단위","전월출고량","당월출고량","주간출고량","하남재고","여주재고","재고일수","발주량","BOX수량","입고요청일","단가","공급가","부가세","합계"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "CENTER", "RIGHT/#,##0.00", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "PRDT_CD", "BUY_NM", "LACK_NO_1", "PRDT_NM", "PRDT_STD", "ORD_UNIT", "PREV_SALES_QTY", "CURR_SALES_QTY", "WEEK_SALES_QTY", "HA_STK_QTY", "YEO_STK_QTY", "STK_DAY", "ORD_QTY", "BOX_QTY", "BUY_DT", "COST", "PURE_AMT", "VAT_AMT", "TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	
	/**
	 * 매입처 발주 전체 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/br/buy/buyOrderNotReg_selExcelDown")
	public ModelAndView selectBuyOrderNotReg(BuyOrderRegVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();
		
		List<HashMap<String, String>> datalist = buyOrderRegService.selectBuyOrdRegNotListExcelDown(paramVo);
		

		//file name
		String fileName = "매입처 전체 품목 발주내역";


		// 엑셀 필드타이틀
		String[] title = {"NO", "품목코드","매입처명","랙번호","품명","규격","단위","전월출고량","당월출고량","주간출고량","하남재고","여주재고","재고일수","발주량","BOX수량","입고요청일","단가","공급가","부가세","합계"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "RIGHT/#,##0.00", "CENTER", "RIGHT/#,##0.00", "CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "PRDT_CD", "BUY_NM", "LACK_NO_1", "PRDT_NM", "PRDT_STD", "ORD_UNIT", "PREV_SALES_QTY", "CURR_SALES_QTY", "WEEK_SALES_QTY", "HA_STK_QTY", "YEO_STK_QTY", "STK_DAY", "ORD_QTY", "BOX_QTY", "BUY_DT", "COST", "PURE_AMT", "VAT_AMT", "TOT_AMT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
