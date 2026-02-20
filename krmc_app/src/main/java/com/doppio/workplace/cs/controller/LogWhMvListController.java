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
import com.doppio.workplace.cs.service.LogWhMvListService;
import com.doppio.workplace.cs.service.LogWhMvListVo;
import com.doppio.workplace.cs.service.ClsDayStkListVo;
import com.doppio.workplace.cs.service.ClsDayStkRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 센터이동 조회
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "logWhMvListController")
public class LogWhMvListController {

	private static final Logger logger = LoggerFactory.getLogger(LogWhMvListController.class);
	
	@Autowired
	LogWhMvListService logWhMvListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/logWhMvList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/logWhMvList";
 	}

	/**
	 * 센터이동 조회 Event
	 * @param LogWhMvListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/logWhMvList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectLogWhMvList(LogWhMvListVo param, HttpServletRequest request) throws Exception {
		return logWhMvListService.selectLogWhMvList(param);
	}
	
 	 
	/**
	 * 센터이동 엑셀 다운로드
	 * @param LogWhMvListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/cs/logWhMvList_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(LogWhMvListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = logWhMvListService.selectLogWhMvListExcel(paramVo);

		//file name
		String fileName = "센터이동조회";


		// 엑셀 필드타이틀
		String[] title = {"NO", "이동일자", "출고창고", "출고창고명", "입고창고", "입고창고명", "품목코드", "품명", "규격", "단위", "수량", "소비기한", "비고", "등록자", "등록시간" };		

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "MV_DT", "OUT_WH_CD", "OUT_WH_NM", "IN_WH_CD", "IN_WH_NM", "PRDT_CD", "PRDT_NM", "PRDT_STD", "ORD_UNIT_NM", "MV_QTY", "TERM_VAL", "REMARKS", "REG_NM", "REG_DT" };
		
		ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;
	}
	
	 /**
	  * 센터이동 출력
	  */
	 @RequestMapping(value="/app/cs/logWhMvList_prtList", method = RequestMethod.POST)
	 public void logWhMvListPdfDownload(HttpServletRequest request, HttpServletResponse response, LogWhMvListVo logWhMvListVo) throws Exception{

		 List<LogWhMvListVo> list = logWhMvListService.selectLogWhMvListPrint(logWhMvListVo);
		 String jasperName = "logWhMvPrt.jasper";
		 String fileName = "센터이동집계표";
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
	
	
	
}
