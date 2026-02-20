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
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolverNew2;
import org.springframework.tronic.util.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.workplace.sm.service.CusShipDtlListService;
import com.doppio.workplace.sm.service.CusShipDtlListVo;


/**
 * @author j10000
 * @Description : 매출처출고현황(상세)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05.06 j10000
 * </pre>
 * @version :
 */
@Controller(value = "cusShipDtlListController")
public class CusShipDtlListController {

	private static final Logger logger = LoggerFactory.getLogger(CusShipDtlListController.class);
	
	@Autowired
	CusShipDtlListService cusShipDtlListService;
	
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusShipDtlList")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/sm/cusShipDtlList";
 	}

	/**
	 * 매출처출고현황(상세)
	 * @param CusShipDtlListVo param
	 * @param HttpServletRequest
	 * @return List<CusShipDtlListVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusShipDtlList_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusShipDtlListSearch(CusShipDtlListVo param, HttpServletRequest request) throws Exception {
		return cusShipDtlListService.selectCusShipDtlList(param);
	}
 


	/**
	 * 매출처출고현황(상세) Event 엑셀 다운로드
	 * @param CusShipDtlListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/sm/custsales/cusShipDtlList_selExcelDown")
	public ModelAndView selectCusShipDtlListExcelDown(CusShipDtlListVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalistSum 	= cusShipDtlListService.selectCusShipDtlListExcelDownSum(paramVo);		
		List<HashMap<String, String>> datalist 		= cusShipDtlListService.selectCusShipDtlListExcelDown(paramVo);

		//file name
		String fileName = "매출처출고현황(상세)";

		// 엑셀 필드타이틀
		String[] title = {"NO","일자", "구분", "창고명","랙번호","보관방법","호차", "매출처명","사업자등록번호", "전표번호","품목코드","품목명", "규격","단위"
				           ,"단가","수량", "공급가","부가세","합계금액","구분","비고"};
		
		// 엑셀 필드타이틀
		String[] titleSum = {"NO", "일자",	"수량", "공급가","부가세","합계금액"};

		
		String[] dataStyle = {"CENTER","CENTER/##","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER", "CENTER","CENTER","CENTER","CENTER","CENTER"
				              ,"RIGHT/#,##0","RIGHT/#,##0.00","RIGHT/#,##0","RIGHT/#,##0","RIGHT/#,##0","CENTER","CENTER"};
		
		String[] dataStyleSum = {"CENTER", "CENTER", "RIGHT/#,##0.00","RIGHT/#,##0","RIGHT/#,##0","RIGHT/#,##0"};
		

		int[] cellWidth = {0,20,20,20,20,20,20,20,20,20,20,20,20,20
			               ,20,20,20,20,20,20,20};
		
		int[] cellWidthSum = {0, 20,20,20, 20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "DT", "SALES_CLASS_NM", "WH_NM", "LACK_NO_1", "SAVE_KIND_NM", "CAR_NM",  "SALES_NM", "BSNS_NUM", "SALES_SLIP_NO", "PRDT_CD", "PRDT_NM", "PRDT_STD", "ORD_UNIT_NM", "PRICE", "QTY", "PURE_AMT", "VAT_AMT", "TOT_AMT", "GUBUN","REMARKS_1"};
		
		String[] keysetSum = {"NO", "DT", "QTY", "PURE_AMT", "VAT_AMT", "TOT_AMT"};

//		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		ExcelFileVo	=	FileUtil.toExcelWithSum(fileName, datalist, title, dataStyle, cellWidth, keyset, datalistSum, titleSum, dataStyleSum, cellWidthSum, keysetSum);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew2());
//		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	
	 /**
	  * 매출처출고현황(상세) 출력
	  */
	 @RequestMapping(value="/app/sm/custsales/cusShipDtlListPrint.json", method = RequestMethod.POST)
	 public void cusShipDtlListPdfDownload(HttpServletRequest request, HttpServletResponse response, CusShipDtlListVo cusShipDtlListVo) throws Exception{

		 List<CusShipDtlListVo> list = cusShipDtlListService.selectCusShipDtlPrintList(cusShipDtlListVo);
		 String jasperName = "cusShipDtlListPrt.jasper";
		 String fileName = "매출처출고현황(상세)";
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
