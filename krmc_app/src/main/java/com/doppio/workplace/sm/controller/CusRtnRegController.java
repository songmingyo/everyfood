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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.service.JasperDownloadService;
import com.doppio.common.model.Result;

import com.doppio.workplace.sm.service.CusRtnRegService;
import com.doppio.workplace.sm.service.CusRtnRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;

/**
 * @Class : CusRtnRegController.java
 * @author dada
 * @Description : 매출처반품등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04. 08.     song
 * </pre>
 * @version :
 */
@Controller(value = "cusRtnRegController")
public class CusRtnRegController {

	private static final Logger logger = LoggerFactory.getLogger(CusRtnRegController.class);
	
	@Autowired
	CusRtnRegService  cusRtnRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusRtnReg")
 	public String cusRtnRegInit() throws Exception{
 		return "app/sm/cusRtnReg";
 	}
 	
 	
 	/**
	 * 매출처반품등록 마스터 조회  Event
	 * @param CusRtnRegVo param
	 * @param HttpServletRequest
	 * @return List<CusRtnRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusRtnReg_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusRtnRegVo> selectCusRtnRegSearch(CusRtnRegVo param, HttpServletRequest request) throws Exception {
		return cusRtnRegService.selectCusRtnRegList(param);
	}
 	
	
	/**
	 * 매출처반품등록 내역 조회  Event
	 * @param CusRtnRegVo param
	 * @param HttpServletRequest
	 * @return List<CusRtnRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusRtnReg_selDetailList.json"}, method=RequestMethod.POST)
	public @ResponseBody List<CusRtnRegVo> selectCusRtnRegDetailSearch(CusRtnRegVo param, HttpServletRequest request) throws Exception {
		return cusRtnRegService.selectCusRtnRegDetailList(param);
	}
	
 	/**
	 * 매출처반품등록 하나의 품목만 조회  Event
	 * @param CusRtnRegVo param
	 * @param HttpServletRequest
	 * @return List<CusRtnRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusRtnReg_selPrdtAdd.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusRtnRegPrdtAddSearch(@RequestBody CusRtnRegVo param, HttpServletRequest request) throws Exception {
		return cusRtnRegService.selectCusRtnRegPrdtAdd(param);
	}
	
	
	/**
	 * 매출처반품등록 저장 Event
	 * @param SalesPrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusRtnReg_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusRtnReg (HttpServletRequest request,  @RequestBody CusRtnRegVo param) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusRtnRegService.insertCusRtnReg(param);
		
		return result;
	}
	
	
	 /**
	  * 반품 거래명세 출력 
	  * @param CusRtnRegVo param
	 * @param HttpServletRequest
	 * @return void
	 * @throws Exception
	 */
	 @RequestMapping(value="/app/sm/custsales/cusRtnReg_prtTran", method = RequestMethod.POST)
	 public void cusPrtTranPdfDownload(HttpServletRequest request, HttpServletResponse response, CusRtnRegVo cusRtnRegVo) throws Exception{
		 
		 String jasperName_1	= "curRtnTranPrt_1.jasper";
		 String jasperName_2	= "curRtnTranPrt_2.jasper";
		 String fileName 		= "거래명세표";
		 
		 List<CusRtnRegVo> list = null ;					// 대상 데이터 List ( Report 바인딩 데이터)
		 List<Map<String, Object>> contFileMapList = null;  // JASPER 파일 리스트 ( 복합 그리드 사용 인경우 List 레포트 종류 설정 가능)
		 
		 List<Map<String, Object>> jasperList = null;		// 병합 처리 할 레포트 리스트 
		
		 
		 Map<String, Object> contFileMap = null;			// 레포트 유형(Design)
		 Map<String, Object> map = null;					// 레포트 대상 마스터 Map
		 
		 jasperList = new ArrayList<>();
		 
		 for(CusRtnRegVo vo : cusRtnRegVo.getItem()) {
			 list = cusRtnRegService.selectRtnPrintList(vo);
			 
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
 	
}
