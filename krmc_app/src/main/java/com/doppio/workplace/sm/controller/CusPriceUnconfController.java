package com.doppio.workplace.sm.controller;

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
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.sm.service.CusOrdListVo;
import com.doppio.workplace.sm.service.CusPriceUnconfService;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;


/**
 * @author j10000
 * @Description : 매출단가 미확정건
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusPriceUnconfController")
public class CusPriceUnconfController {

	private static final Logger logger = LoggerFactory.getLogger(CusPriceUnconfController.class);
	
	@Autowired
	CusPriceUnconfService cusPriceUnconfService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusPriceUnconf")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusPriceUnconf";
 	}

	/**
	 * 매출단가 미확정건
	 * @param CusPriceUnconfVo param
	 * @param HttpServletRequest
	 * @return List<CusPriceUnconfVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusPriceUnconf_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusPriceUnconfSearch(CusPriceUnconfVo param, HttpServletRequest request) throws Exception {
		return cusPriceUnconfService.selectCusPriceUnconfList(param);
	}
 



	/**
	 * 매출단가 미확정건 Event 엑셀 다운로드
	 * @param CusPriceUnconfVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusPriceUnconfList_selExcelDown")
	public ModelAndView selectCusPriceUnconfListExcelDown(CusPriceUnconfVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusPriceUnconfService.selectCusPriceUnconfListExcelDown(paramVo);

		//file name
		String fileName = "매출단가미확정건";


		// 엑셀 필드타이틀
		String[] title = {"NO","매출일자", "품목명", "규격","판매수량","매입단가","매출단가","전표번호","매출처명","영업담당자","등록자","등록시간","수정자","수정시간"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "RIGHT/#,##0.00", "RIGHT/#,##0", "RIGHT/#,##0", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_DT","PRDT_NM", "PRDT_STD", "SALES_QTY", "COST", "PRICE",  "SALES_SLIP_NO","SALES_NM", "SALES_PR_NM", "REG_ID","REG_DT","MOD_ID","MOD_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	 /**
	  * 매출단가 미확정건 출력
	  */
	 @RequestMapping(value="/app/sm/custsales/cusPriceListPrint", method = RequestMethod.POST)
	 public void cusPriceListPdfDownload(HttpServletRequest request, HttpServletResponse response, CusPriceUnconfVo cusPriceUnconfVo) throws Exception{

		 List<CusPriceUnconfVo> list = cusPriceUnconfService.selectCusPricePrintList(cusPriceUnconfVo);
		 String jasperName = "cusPriceListPrt.jasper";
		 String fileName = "매출단가미확정건";
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
	 	

	
}
