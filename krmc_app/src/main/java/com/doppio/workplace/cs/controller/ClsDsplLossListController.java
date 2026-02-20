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
import com.doppio.workplace.cs.service.ClsDsplLossListService;
import com.doppio.workplace.cs.service.ClsDsplLossListVo;
import com.doppio.workplace.cs.service.ClsDayStkRegVo;
import com.doppio.common.service.JasperDownloadService;

/**
 * @author Song
 * @Description : 폐기로스 조회
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "clsDsplLossListController")
public class ClsDsplLossListController {

	private static final Logger logger = LoggerFactory.getLogger(ClsDsplLossListController.class);
	
	@Autowired
	ClsDsplLossListService clsDsplLossListService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/cs/closing/clsDsplLossList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/cs/clsDsplLossList";
 	}

	/**
	 * 폐기로스 조회 Event
	 * @param ClsDsplLossListVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/cs/clsDsplLossList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClsDsplLossList(ClsDsplLossListVo param, HttpServletRequest request) throws Exception {
		return clsDsplLossListService.selectClsDsplLossList(param);
	}
	
 	 
	/**
	 * 폐기로스 엑셀 다운로드
	 * @param ClsDsplLossListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/cs/clsDsplLossList_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(ClsDsplLossListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = clsDsplLossListService.selectClsDsplLossListExcel(paramVo);

		//file name
		String fileName = "폐기로스조회";


		// 엑셀 필드타이틀
		String[] title = {"NO", "일자", "창고명", "품목코드", "품명", "규격", "폐기로스구분", "수량", "단가", "금액", "비고", "등록자", "등록시간" };		

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "DSP_DT", "WH_NM", "PRDT_CD", "PRDT_NM", "PRDT_STD", "DSP_CLASS_NM", "DSP_QTY", "COST", "DSP_AMT", "REMARKS", "REG_NM", "REG_DT" };
		
		ExcelFileVo = FileUtil.toExcelNew(fileName, datalist, title, "CENTER", 20, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	
	
}
