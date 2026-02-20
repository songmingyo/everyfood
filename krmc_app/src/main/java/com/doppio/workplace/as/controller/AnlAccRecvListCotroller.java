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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.workplace.as.service.AnlAccRecvListService;
import com.doppio.workplace.as.service.AnlAccRecvListVo;
import com.doppio.common.service.JasperDownloadService;


/**
 * @author j10000
 * @Description : 외상매입금
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "anlAccRecvListController")
public class AnlAccRecvListCotroller {

	private static final Logger logger = LoggerFactory.getLogger(AnlAccRecvListCotroller.class);
	
	@Autowired
	AnlAccRecvListService anlAccRecvListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/as/anlys/anlAccRecvList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/as/anlAccRecvList";
 	}

	/**
	 * 외상매입금
	 * @param AnlAccRecvListVo param
	 * @param HttpServletRequest
	 * @return List<AnlAccRecvListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/as/anlys/anlAccRecvList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectAnlAccRecvListSearch(AnlAccRecvListVo param, HttpServletRequest request) throws Exception {
		return anlAccRecvListService.selectAnlAccRecvList(param);
	}
 

	/**
	  * 외상매입금 출력
	  */
	 @RequestMapping(value="/app/as/salesmgmt/anlAccRecvList_prtList.json", method = RequestMethod.POST)
	 public void accRecvListPdfDownload(HttpServletRequest request, HttpServletResponse response, AnlAccRecvListVo anlAccRecvListVo) throws Exception{

		 List<AnlAccRecvListVo> list = anlAccRecvListService.selectAccRecvPrintList(anlAccRecvListVo);
		 String jasperName = "accRecvPrt.jasper";
		 String fileName = "외상매입금";
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
	 * 외상매입금 Event 엑셀 다운로드
	 * @param AnlAccRecvListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/as/anlys/anlAccRecvList_selExcelDown")
	public ModelAndView selectAnlAccRecvListExcelDown(AnlAccRecvListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = anlAccRecvListService.selectAnlAccRecvListExcelDown(paramVo);

		//file name
		String fileName = "외상매입금";


		// 엑셀 필드타이틀
		String[] title = {"NO","매입처명", "잔액", "매입","지급","잔액","매입", "지급","잔액","결제조건","은행명","계좌번호","예금주","대표자번호","사업자번호"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "BUY_NM",  "BAL_AMT1", "IN_AMT2", "PAY_AMT2", "BAL_AMT2", "BUY_AMT3", "PAY_AMT3",  "BAL_AMT3", "SETL_CON", "BANK_NM", "BANK_ACC_NUM", "BANK_DEP", "TEL_NO", "BSNS_NUM"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

	
}
