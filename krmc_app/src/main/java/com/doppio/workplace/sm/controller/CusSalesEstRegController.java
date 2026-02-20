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
import org.springframework.tronic.util.FileUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.CustomCodeVo;
import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.sm.service.CusSalesEstRegService;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;
import com.doppio.common.service.JasperDownloadService;
import com.doppio.common.service.CommonService;

/**
 * @author Song
 * @Description : 매출 견적서 등록
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "cusSalesEstRegController")
public class CusSalesEstRegController {

	private static final Logger logger = LoggerFactory.getLogger(CusSalesEstRegController.class);
	
	@Autowired
	CusSalesEstRegService cusSalesEstRegService;
	
	@Autowired
	private JasperDownloadService jasperDownloadService;
	
	@Autowired
	CommonService commonService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusSalesEstReg")
 	public String mgrBoardManagerInit(Model model) throws Exception{
 		//jqGrid selectbox 생성용 코드 리스트 조회 >> 이물회수[FS04]
		CustomCodeVo codeVo = new CustomCodeVo();
		codeVo.setParentCd("M009");
		String codeList_confirmYn = commonService.selectGridCodeList(codeVo);
		model.addAttribute("codeList_confirmYn", codeList_confirmYn);
 		
 		return "app/sm/cusSalesEstReg";
 	}

	/**
	 * 매출 견적서 품목 조회 Event
	 * @param CusSalesEstRegVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesEstRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSalesEstReg_selPrdtList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusSalesEstRegPrdtList(CusSalesEstRegVo param, HttpServletRequest request) throws Exception {
		return cusSalesEstRegService.selectCusSalesEstRegPrdtList(param);
	}
	
	/**
	 * 매출 견적서 Head 조회 Event
	 * @param CusSalesEstRegVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesEstRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSalesEstReg_selHeadList.json"}, method=RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CusSalesEstRegVo selectCusSalesEstRegHeadList(@RequestBody CusSalesEstRegVo param, HttpServletRequest request) throws Exception {
		return cusSalesEstRegService.selectCusSalesEstRegHeadList(param);
	}
	
	/**
	 * 매출 견적서 Item 조회 Event
	 * @param CusSalesEstRegVo param
	 * @param HttpServletRequest
	 * @return List<CusSalesEstRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusSalesEstReg_selItemList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusSalesEstRegItemList(CusSalesEstRegVo param, HttpServletRequest request) throws Exception {
		return cusSalesEstRegService.selectCusSalesEstRegItemList(param);
	}
	

	/**
	 * 매출 견적서 저장 Event
	 * @param CusSalesEstRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/sm/custsales/cusSalesEstReg_insData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result cusSalesEstSave (HttpServletRequest request,  @RequestBody CusSalesEstRegVo param, Result result) throws Exception {
		return cusSalesEstRegService.insertCusSalesEstReg(param);
	}
	
	
	/**
	 * 내부 견적서 출력
	 */
	@RequestMapping(value="/app/sm/custsales/curSalesEstInPrint", method = RequestMethod.POST)
	public void salesEstInPdfDownload(HttpServletRequest request, HttpServletResponse response, CusSalesEstRegVo cusSalesEstRegVo) throws Exception{

		List<CusSalesEstRegVo> list = cusSalesEstRegService.selectCurSalesEstPrintList(cusSalesEstRegVo);
		String jasperName = "curSalesEstInPrt.jasper";
		String fileName = "내부견적서";
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
	 * 외부 견적서 출력
	 */
	@RequestMapping(value="/app/sm/custsales/curSalesEstOutPrint", method = RequestMethod.POST)
	public void salesEstOutPdfDownload(HttpServletRequest request, HttpServletResponse response, CusSalesEstRegVo cusSalesEstRegVo) throws Exception{

		List<CusSalesEstRegVo> list = cusSalesEstRegService.selectCurSalesEstPrintList(cusSalesEstRegVo);
		String jasperName = "curSalesEstOutPrt.jasper";
		String fileName = "외부견적서";
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
	 * 신규품목입점의뢰서 출력
	 */
	@RequestMapping(value="/app/sm/custsales/curSalesEstNewPrdtPrint", method = RequestMethod.POST)
	public void salesEstNewPrdtPdfDownload(HttpServletRequest request, HttpServletResponse response, CusSalesEstRegVo cusSalesEstRegVo) throws Exception{

		List<CusSalesEstRegVo> list = cusSalesEstRegService.selectCurSalesEstPrintList(cusSalesEstRegVo);
		String jasperName = "curSalesEstNewPrdtPrt.jasper";
		String fileName = "신규품목입점의뢰서";
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

}
