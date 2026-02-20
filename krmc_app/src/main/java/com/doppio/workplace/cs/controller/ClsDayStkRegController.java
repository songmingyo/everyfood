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
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.cs.service.ClsDayStkRegService;
import com.doppio.workplace.cs.service.ClsDayStkRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 일재고 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "clsDayStkRegController")
public class ClsDayStkRegController {

	private static final Logger logger = LoggerFactory.getLogger(ClsDayStkRegController.class);
	
	@Autowired
	ClsDayStkRegService clsDayStkRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/clsDayStkReg")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/clsDayStkReg";
 	}

	/**
	 * 일재고 조회 Event
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/clsDayStkReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClsDayStkRegList(ClsDayStkRegVo param, HttpServletRequest request) throws Exception {
		return clsDayStkRegService.selectClsDayStkRegList(param);
	}
	
	
	/**
	 * 일재고 Footer 조회 Event
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return ClsDayStkRegVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/cs/clsDayStkRegFooter_selList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ClsDayStkRegVo selectDayStkRegFooterList (HttpServletRequest request,  @RequestBody ClsDayStkRegVo param) throws Exception {
		return clsDayStkRegService.selectDayStkRegFooter(param);
	}
 

	/**
	 * 일재고 저장 Event
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/cs/clsDayStkReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result buyOrdReg (HttpServletRequest request,  @RequestBody ClsDayStkRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = clsDayStkRegService.insertClsDayStkReg(param);
		
		return result;
	}

	 
	/**
	 * 일재고 엑셀 다운로드
	 * @param ClsDayStkRegVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/cs/clsDayStkReg_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(ClsDayStkRegVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = clsDayStkRegService.selectClsDayStkRegListExcel(paramVo);

		//file name
		String fileName = "일재고등록";


		// 엑셀 필드타이틀
		String[] title = {"NO", "품목코드", "랙번호", "품명", "규격", "박스당수량", "전일재고", "입고", "반품", "입고계", "출고", "반품", "출고계", "수불수량", "수불금액", "실재고A+B동", "실재고C+D동", "실재고합계수량", "실재고합계금액", "재고차수량", "재고차금액", "단가" };		

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "PRDT_CD", "LACK_NO_1", "PRDT_NM", "PRDT_STD", "QTY_BOX", "PREV_STK_QTY", "BUY_QTY", "BUY_RTN_QTY", "BUY_TOT_QTY", "DLV_QTY", "SALES_RTN_QTY", "DLV_TOT_QTY", "L_STK_QTY", "L_STK_AMT", "AREA_1_QTY", "AREA_2_QTY", "R_STK_QTY", "R_STK_AMT", "STK_DIFF_QTY", "STK_DIFF_AMT", "COST"};

		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT"};
		
		// cell width
		int[] cellWidth = {0,  20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

	
	 /**
	  * 재고조사표 출력
	  */
	 @RequestMapping(value="/app/cs/clsDayStkReg_prtInspList", method = RequestMethod.POST)
	 public void dayStkInspListPdfDownload(HttpServletRequest request, HttpServletResponse response, ClsDayStkRegVo clsDayStkRegVo) throws Exception{

		 List<ClsDayStkRegVo> list = clsDayStkRegService.selectStkInspPrintList(clsDayStkRegVo);
		 String jasperName = "clsDayStkInspListPrt.jasper";
		 String fileName = "재고조사표";
		 String viewType = "browser";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list); 									//DATA
		 map.put("viewType", viewType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }
	 
	 
	 /**
	  * 재고 수불장 출력
	  */
	 @RequestMapping(value="/app/cs/clsDayStkReg_prtList", method = RequestMethod.POST)
	 public void dayStkListPdfDownload(HttpServletRequest request, HttpServletResponse response, ClsDayStkRegVo clsDayStkRegVo) throws Exception{

		 HashMap<String, Object> resultMap = clsDayStkRegService.selectClsDayStkRegList(clsDayStkRegVo);
			
		 String jasperName = "clsDayStkListPrt.jasper";
		 String fileName = "재고수불장";
		 String viewType = "browser";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", resultMap.get("list")); 									//DATA
		 map.put("viewType", viewType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }
}

