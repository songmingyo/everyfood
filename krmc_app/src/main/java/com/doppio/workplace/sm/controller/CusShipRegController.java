package com.doppio.workplace.sm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.tronic.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.service.JasperDownloadService;
import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sample.service.SampleJasperVo;
import com.doppio.workplace.sm.service.CusShipRegService;
import com.doppio.workplace.sm.service.CusPeriodListVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;

import java.util.*;

/**
 * @Class : CusShipRegController.java
 * @author dada
 * @Description : 매출처출고등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04. 08.     DADA
 * </pre>
 * @version :
 */
@Controller(value = "cusShipRegController")
public class CusShipRegController {

	private static final Logger logger = LoggerFactory.getLogger(CusShipRegController.class);
	
	@Autowired
	CusShipRegService  cusShipRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusShipReg")
 	public String cusShipRegInit() throws Exception{
 		return "app/sm/cusShipReg";
 	}
 	
 	
 	/**
	 * 매출처출고등록 : 매출처출고등록 마스터 조회  Event
	 * @param CusSalesDlvVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesDlvVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusShipReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusSalesDlvVo> selectCusShipRegSearch(CusSalesDlvVo param, HttpServletRequest request) throws Exception {
		return cusShipRegService.selectCusShipRegList(param);
	}
 	
	
	/**
	 * 매출처출고등록 : 매출처출고등록 상품 상세  조회  Event
	 * @param CusSalesDlvVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesDlvVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusShipReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusSalesDlvVo> selectCusShipRegDetailSearch(CusSalesDlvVo param, HttpServletRequest request) throws Exception {
		return cusShipRegService.selectCusShipRegDetailList(param);
	}
	
	/**
	 * 매출처출고등록 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusShipReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusShipReg (HttpServletRequest request,  @RequestBody CusSalesDlvVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusShipRegService.insertCusShipReg(param);
		
		return result;
	}
	
	/**
	 * 매출처출고 엑셀 업로드 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusShipReg_insExcelUploadList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusShipExcelUploadReg (HttpServletRequest request,  @RequestBody CusSalesDlvVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusShipRegService.insertCusShipExcelUploadReg(param);
		
		return result;
	}
	
	/**
	 * 매출처출고 엑셀 확정 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusShipExcelReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusShipExcelReg (HttpServletRequest request,  @RequestBody CusSalesDlvVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusShipRegService.insertCusShipExcelReg(param);
		
		return result;
	}
	
	/**
	 * 매출처출고 엑셀 업로드 조회 Event
	 * @param CusSalesDlvVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesDlvVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusShipReg_selExcelUploadList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusSalesDlvVo> selectCusShipRegExcelUploadSearch(CusSalesDlvVo param, HttpServletRequest request) throws Exception {
		return cusShipRegService.selectCusShipRegExcelUploadList(param);
	}
	
	/**
	 * 매출처출고 엑셀 업로드 에러 조회 Event
	 * @param CusSalesDlvVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesDlvVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusShipReg_selExcelUploadErrorList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusSalesDlvVo> selectCusShipRegExcelUploadErrorSearch(CusSalesDlvVo param, HttpServletRequest request) throws Exception {
		return cusShipRegService.selectCusShipRegExcelUploadErrorList(param);
	}
	
	
	//비교하는 로직
	public class salesCdComparator implements Comparator{
		@Override
		public int compare(Object o1, Object o2){
			String en1 = ((CusSalesDlvVo)o1).getSalesCds();
			String en2 = ((CusSalesDlvVo)o2).getSalesCds();
			return en1.compareTo(en2);
		}
	}
	
	
	 /**
	  * 매출 거래명세 출력 
	  * @param CusSalesDlvVo param
	 * @param HttpServletRequest
	 * @return void
	 * @throws Exception
	 */
	 @RequestMapping(value="/app/sm/custsales/cusShipReg_prtTran", method = RequestMethod.POST)
	 public void cusPrtTranPdfDownload(HttpServletRequest request, HttpServletResponse response, CusSalesDlvVo cusSalesDlvVo) throws Exception{

		 String jasperName_1	= "curShipTranPrt_1.jasper";
		 String jasperName_2	= "curShipTranPrt_2.jasper";
		 String fileName 		= "거래명세표";
		 
		 List<CusSalesDlvVo> list = null ;					// 대상 데이터 List ( Report 바인딩 데이터)
		 List<Map<String, Object>> contFileMapList = null;  // JASPER 파일 리스트 ( 복합 그리드 사용 인경우 List 레포트 종류 설정 가능)
		 
		 List<Map<String, Object>> jasperList = null;		// 병합 처리 할 레포트 리스트 
		
		 
		 Map<String, Object> contFileMap = null;			// 레포트 유형(Design)
		 Map<String, Object> map = null;					// 레포트 대상 마스터 Map
		 
		 jasperList = new ArrayList<>();
		 
		 List<CusSalesDlvVo> salesCdList = cusSalesDlvVo.getItem();
		 
		 Collections.sort(salesCdList, new salesCdComparator());
		 
		 //Comparator<CusSalesDlvVo> salesCd = Comparator.comparing(CusSalesDlvVo::getSalesCd);
		 //Collections.sort(cusSalesDlvVo, salesCd);
		 
		 //cusSalesDlvVo.sort()
		 
		 for(CusSalesDlvVo vo : salesCdList) {
			 list = cusShipRegService.selectTranPrintList(vo);
			 
			 cusShipRegService.insertCusShipPrtYnReg(vo);
			 			 
			 //공급받는자용 출력
			 map = new HashMap<String, Object>();
			 
			 contFileMapList = new ArrayList<>();
			 contFileMap = new HashMap<String, Object>();
			 
			 contFileMap.put("jasperName1", jasperName_1);
			 contFileMapList.add(contFileMap);
				 
			 map.put("list", list); 									//DATA
			 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
			 map.put("fileName", fileName);								//파일 이름
				 
			 jasperList.add(map);
			 
			 //공급자용 출력
			 if("A".equals(vo.getPrtClass())){
				 map = new HashMap<String, Object>();
				 
				 contFileMapList = new ArrayList<>();
				 contFileMap = new HashMap<String, Object>();
				 
				 contFileMap.put("jasperName1", jasperName_2);
				 contFileMapList.add(contFileMap);
				
				 map.put("list", list); 									//DATA
				 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
				 map.put("fileName", fileName);								//파일 이름
					 
				 jasperList.add(map);
			 }
		 }
		 
		 jasperDownloadService.jasperDocumentDownloadMulit(jasperList,request, response);

	 }

	 /**
	  * 매출처 출력 기타 출력물 
	  * @param CusSalesDlvVo param
	 * @param HttpServletRequest
	 * @return void
	 * @throws Exception
	 */
	 @RequestMapping(value="/app/sm/custsales/cusShipReg_prtOther", method = RequestMethod.POST)
	 public void cusprtCustPdfDownload(HttpServletRequest request, HttpServletResponse response, CusSalesDlvVo cusSalesDlvVo) throws Exception{
		 
		 String jasperName_1 = "";
		 String fileName = "";
		 
		 if("B".equals(cusSalesDlvVo.getItem().get(0).getPrtClass())){
			 jasperName_1	= "curShipCustPrt.jasper";
			 fileName 		= "고객사별집계표";
		 }else if("C".equals(cusSalesDlvVo.getItem().get(0).getPrtClass())){
			 jasperName_1	= "curShipCarPrt.jasper";
			 fileName 		= "차량별집계표";
		 }else if("D".equals(cusSalesDlvVo.getItem().get(0).getPrtClass())){
			 jasperName_1	= "curShipStkPrt.jasper";
			 fileName 		= "재고집계표";
		 }
		 
		 List<CusSalesDlvVo> list = null ;					// 대상 데이터 List ( Report 바인딩 데이터)
		 List<Map<String, Object>> contFileMapList = null;  // JASPER 파일 리스트 ( 복합 그리드 사용 인경우 List 레포트 종류 설정 가능)
		 
		 List<Map<String, Object>> jasperList = null;		// 병합 처리 할 레포트 리스트		
		 
		 Map<String, Object> contFileMap = null;			// 레포트 유형(Design)
		 Map<String, Object> map = null;					// 레포트 대상 마스터 Map
		 
		 jasperList = new ArrayList<>();
		 
		 ArrayList<String> slipNo = new ArrayList<>();
		 
		 for(CusSalesDlvVo vo : cusSalesDlvVo.getItem()) {
			 
			 slipNo.add(vo.getSalesSlipNos());
			 
			 cusShipRegService.insertCusShipPrtYnReg(vo);			 
		 }
		 
		 cusSalesDlvVo.setSlipNoList(slipNo);
		 
		 if("B".equals(cusSalesDlvVo.getItem().get(0).getPrtClass())) {
			 list = cusShipRegService.selectSalesCustPrintList(cusSalesDlvVo);
		 }else if("C".equals(cusSalesDlvVo.getItem().get(0).getPrtClass())) {
			 list = cusShipRegService.selectSalesCarPrintList(cusSalesDlvVo);
		 }else if("D".equals(cusSalesDlvVo.getItem().get(0).getPrtClass())) {
			 list = cusShipRegService.selectSalesStkPrintList(cusSalesDlvVo);
		 }
		 
		 map = new HashMap<String, Object>();
		 
		 contFileMapList = new ArrayList<>();
		 contFileMap = new HashMap<String, Object>();
		 
		 contFileMap.put("jasperName1", jasperName_1);
		 contFileMapList.add(contFileMap);
			 
		 map.put("list", list); 									//DATA
		 map.put("contFileMapList", contFileMapList);				//사용할 jasper 양식
		 map.put("fileName", fileName);								//파일 이름
			 
		 jasperList.add(map);
		 
		 jasperDownloadService.jasperDocumentDownloadMulit(jasperList,request, response);
	 }
	
}