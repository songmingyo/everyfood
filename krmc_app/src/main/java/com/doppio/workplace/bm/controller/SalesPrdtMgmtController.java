package com.doppio.workplace.bm.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.ui.Model;

import com.doppio.common.model.CustomCodeVo;
import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.CommonService;
import com.doppio.workplace.bm.service.SalesPrdtMgmtService;
import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;

/**
 * @author Song
 * @Description : 매출처별 상품관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------

 * </pre>
 * @version :
 */
@Controller(value = "salesPrdtMgmtController")
public class SalesPrdtMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(SalesPrdtMgmtController.class);
	
	@Autowired
	SalesPrdtMgmtService salesPrdtMgmtService;
	
	@Autowired
	CommonService commonService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/salesPrdtMgmt")
 	public String mgrBoardManagerInit(Model model) throws Exception{
 		
 		CustomCodeVo codeVo = new CustomCodeVo();
		codeVo.setParentCd("M021");
		String codeList_confirmYn = commonService.selectGridCodeList(codeVo);
		model.addAttribute("codeList_vatYn", codeList_confirmYn);
		
 		return "app/bm/basSalesPrdtMgmt";
 	}

	/**
	 * 매출처벌 상품관리  조회 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/salesPrdtMgmt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectPrdtMgmtSearch(SalesPrdtMgmtVo param, HttpServletRequest request) throws Exception {
		return salesPrdtMgmtService.selectSalesPrdtMgmtList(param);
	}
 

	/**
	 * 상품 저장 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesPrdtMgmt_insList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salesPrdtMgmtSave (HttpServletRequest request,  @RequestBody SalesPrdtMgmtVo param, Result result) throws Exception {
		return salesPrdtMgmtService.insertSalesPrdtMaster(param);
	}
	
	/**
	 * 선택 상품 삭제 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesPrdt_delSelectRow.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salesPrdtMgmtSelectRowDelete (HttpServletRequest request,  @RequestBody SalesPrdtMgmtVo param, Result result) throws Exception {
		return salesPrdtMgmtService.updateSalesPrdtMasterDataUseFlag(param);
	}
	
	/**
	 * 품목코드 일괄 변경 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesPrdt_insPrdtOldNewChg.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salesPrdtMgmtPrdtOldNewChg (HttpServletRequest request,  @RequestBody SalesPrdtMgmtVo param, Result result) throws Exception {
		return salesPrdtMgmtService.updateSalesPrdtMaterPrdtOldNewChg(param);
	}
	
	/**
	 * 매출처 품목에 대한 매출처로 일괄 입력 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesPrdt_insSalesCdOrgNewInsert.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salesPrdtMgmtSalesCdOrgNewInsert (HttpServletRequest request,  @RequestBody SalesPrdtMgmtVo param, Result result) throws Exception {
		return salesPrdtMgmtService.updateSalesPrdtMaterSalesCdOrgNewInsert(param);
	}
	
	/**
	 * 품목일괄적용 조회 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/salesPrdtMgmtAdd_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectPrdtMgmtAddSearch(@RequestBody SalesPrdtMgmtVo param, HttpServletRequest request) throws Exception {
		return salesPrdtMgmtService.selectSalesPrdtMgmtAddList(param);
	}

	/**
	 * 품목일괄적용 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesPrdt_insSalesPrdtAllInsert.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salesPrdtMgmtSalesPrdtAllInsert (HttpServletRequest request,  @RequestBody SalesPrdtMgmtVo param, Result result) throws Exception {
		return salesPrdtMgmtService.insertSalesPrdtMaterSalesPrdtAllInsert(param);
	}
	
	
	/**
	 * 일괄판매가수정 Event
	 * @param BuyOrderRegVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesPrdt_insSalesPriceUpt.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result salesPrdtMgmtSalesPriceUpt (HttpServletRequest request,  @RequestBody SalesPrdtMgmtVo param, Result result) throws Exception {
		return salesPrdtMgmtService.updateSalesPrdtMaterSalesPriceUpt(param);
	}
	
	
	/**
	 * 매출처 품목 엑셀 다운로드
	 * @param CusPriceUnconfVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/bm/baseinfo/salesPrdtList_selExcelDown")
	public ModelAndView selectSalesPrdtListListExcelDown(SalesPrdtMgmtVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salesPrdtMgmtService.selectSalesPrdtListExcelDown(paramVo);

		//file name
		String fileName = "매출처 품목 현황";


		// 엑셀 필드타이틀
		String[] title = {"NO","품목코드", "품목명", "규격","단위","원가","매출처","매출처품목(BOH)","매출처품목(FOH)","매출단가","이전단가","과면세","적용일","등록자","등록시간","수정자","수정시간"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "PRDT_CD","PRDT_NM", "PRDT_STD", "ORD_UNIT", "COST", "SALES_NM",  "SALES_PRDT_CD_1","SALES_PRDT_CD_2", "PRICE", "PREV_PRICE", "VAT_YN", "START_DT", "REG_NM","REG_DT","MOD_NM","MOD_DT"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
