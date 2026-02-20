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
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.cs.service.ClsDayStkListService;
import com.doppio.workplace.cs.service.ClsDayStkListVo;
import com.doppio.workplace.cs.service.ClsDayStkRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 일재고 조회
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "clsDayStkListController")
public class ClsDayStkListController {

	private static final Logger logger = LoggerFactory.getLogger(ClsDayStkListController.class);
	
	@Autowired
	ClsDayStkListService clsDayStkListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/clsDayStkList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/clsDayStkList";
 	}

	/**
	 * 일재고 조회 Event
	 * @param ClsDayStkListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/clsDayStkList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClsDayStkList(ClsDayStkListVo param, HttpServletRequest request) throws Exception {
		return clsDayStkListService.selectClsDayStkList(param);
	}
	
 	 
	/**
	 * 일재고 엑셀 다운로드
	 * @param ClsDayStkListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/cs/clsDayStkList_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(ClsDayStkListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = clsDayStkListService.selectClsDayStkListExcel(paramVo);

		//file name
		String fileName = "일재고조회";


		// 엑셀 필드타이틀
		String[] title = {"NO", "재고일자", "품목코드", "랙번호", "품명", "규격", "박스당수량", "전일재고", "입고", "칭고이동", "반품", "입고계", "출고", "창고이동", "반품", "출고계", "재고수량", "재고금액", "단가", "최종입고 소비기한" };		
		
		String[] dataStyle = {"CENTER",  "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "RIGHT", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "STK_DT", "PRDT_CD", "LACK_NO_1", "PRDT_NM", "PRDT_STD", "QTY_BOX", "PREV_STK_QTY", "BUY_QTY", "IN_WH_QTY", "BUY_RTN_QTY", "BUY_TOT_QTY", "DLV_QTY", "OUT_WH_QTY", "DLV_RTN_QTY", "DLV_TOT_QTY", "STK_QTY", "STK_AMT", "COST", "TERM_VAL"};
		
		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	 /**
	  * 일재고 출력
	  */
	 @RequestMapping(value="/app/cs/stkListPrint", method = RequestMethod.POST)
	 public void ordListPdfDownload(HttpServletRequest request, HttpServletResponse response, ClsDayStkListVo clsDayStkListVo) throws Exception{

		 List<ClsDayStkListVo> list = clsDayStkListService.selectStkListPrint(clsDayStkListVo);
		 String jasperName = "stkListPrt.jasper";
		 String fileName = "재고수불부";
		 String docType = "pdf";

		 List<Map<String, Object>> contFileMapList = new ArrayList<>();
		 Map<String, Object> contFileMap = new HashMap<String, Object>();

		 contFileMap.put("jasperName1", jasperName);
		 contFileMapList.add(contFileMap);

		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("list", list); 									//DATA
		 map.put("docType", docType);								//pdf, excel, ppt
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름

		 jasperDownloadService.jasperDocumentDownload(map, request, response);

	 }
	
	 /**
	  * 품목별 당월, 전월, 전전월 출고량
	  * @param request
	  * @param paramVo
	  * @return
	  * @throws Exception
	  */
	@RequestMapping(value = "/app/cs/selPrdtQtyMonth.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ClsDayStkListVo selPrdtQtyMonth (HttpServletRequest request,  @RequestBody ClsDayStkListVo paramVo) throws Exception {
		return clsDayStkListService.selPrdtQtyMonth(paramVo);
	}
	
}
