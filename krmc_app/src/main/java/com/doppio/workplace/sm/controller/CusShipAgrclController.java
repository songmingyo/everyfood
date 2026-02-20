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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;
import com.doppio.workplace.sm.service.CusShipAgrclListService;
import com.doppio.workplace.sm.service.CusShipAgrclListVo;


/**
 * @author j10000
 * @Description : 매출처출고현황(농산물)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.03 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusShipAgrclController")
public class CusShipAgrclController {

	private static final Logger logger = LoggerFactory.getLogger(CusShipAgrclController.class);
	
	@Autowired
	CusShipAgrclListService cusShipAgrclListService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusShipAgrclList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusShipAgrclList";
 	}

	/**
	 * 매출처출고현황(농산물)
	 * @param CusShipAgrclListVo param
	 * @param HttpServletRequest
	 * @return List<CusShipAgrclListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusShipAgrclList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusShipAgrclListSearch(CusShipAgrclListVo param, HttpServletRequest request) throws Exception {
		return cusShipAgrclListService.selectCusShipAgrclList(param);
	}
 


	/**
	 * 매출처출고현황(농산물) Event 엑셀 다운로드
	 * @param CusShipAgrclListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusShipAgrclList_selExcelDown")
	public ModelAndView selectCusShipAgrclListExcelDown(CusShipAgrclListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = cusShipAgrclListService.selectCusShipAgrclListExcelDown(paramVo);

		//file name
		String fileName = "매출처출고현황(농산물)";

		// 엑셀 필드타이틀
		String[] title = {"NO", "일자","매출처분류","창고명","랙번호","보관방법","호차","매출처명","전표번호","품목코드","품목명","규격","단위","수량","구분"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "RIGTH/#,##0.00", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "DT", "SALES_CLASS_NM", "WH_NM", "LACK_NO_1", "SAVE_KIND", "CAR_NM", "SALES_NM", "SALES_SLIP_NO", "PRDT_CD", "PRDT_NM", "PRDT_STD", "ORD_UNIT_NM", "QTY", "GUBUN"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	 /**
	  * 매출처출고현황(농산물) 출력
	  */
	 @RequestMapping(value="/app/sm/custsales/cusShipAgrcListPrint", method = RequestMethod.POST)
	 public void cusShipAGrcListPdfDownload(HttpServletRequest request, HttpServletResponse response, CusShipAgrclListVo cusShipAgrclListVo) throws Exception{

		 List<CusShipAgrclListVo> list = cusShipAgrclListService.selectCusShipAgrcPrintList(cusShipAgrclListVo);
		 String jasperName = "cusShipAgrcListPrt.jasper";
		 String fileName = "매출처출고현황(농산물)";
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
