package com.doppio.workplace.bm.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.tronic.resolver.JxlExcelFileDownLoadViewResolver;
import org.springframework.tronic.util.FileUtil;

import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.PrdtMgmtService;
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;
import com.doppio.workplace.sample.service.SampleExcelVo;

/**
 * @author Song
 * @Description : 상품관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03. 09.     DADA
 * </pre>
 * @version :
 */
@Controller(value = "prdtMgmtController")
public class PrdtMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(PrdtMgmtController.class);
	
	@Autowired
	PrdtMgmtService prdtMgmtService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/prdtMgmt")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basPrdtMgmt";
 	}
 	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/prdtMgmtList")
 	public String prdtMgmtListInit() throws Exception{
 		return "app/bm/basPrdtMgmtList";
 	}

	/**
	 * 상품관리  조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return List<PrdtMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/prdtMgmt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectPrdtMgmtSearch(PrdtMgmtVo param, HttpServletRequest request) throws Exception {
		return prdtMgmtService.selectPrdtMgmtList(param);
	}
 

	/**
	 * 상품 상세 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/prdtMgmt_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody PrdtMgmtVo selectPrdtMgmtDetail (HttpServletRequest request,  @RequestBody PrdtMgmtVo param) throws Exception {
		return prdtMgmtService.selectPrdtMgmtData(param);
	}
	
	/**
	 * 상품 최근입고 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/prdtBuy_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectPrdtBuySearch(PrdtMgmtVo param, HttpServletRequest request) throws Exception {
		return prdtMgmtService.selectPrdtBuyList(param);
	}

	
	/**
	 * 상품 최근출고 조회 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/prdtSales_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectPrdtSalesSearch(PrdtMgmtVo param, HttpServletRequest request) throws Exception {
		return prdtMgmtService.selectPrdtSalesList(param);
	}

	/**
	 * 상품 저장 Event
	 * @param PrdtMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/prdtMgmt_insData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result selectPrdtMgmtSave (HttpServletRequest request,  @RequestBody PrdtMgmtVo param, Result result) throws Exception {
		return prdtMgmtService.insertPrdtMgmt(param);
	}
	
	/**
	 * 품목현황 엑셀 다운로드
	 * @param PrdtMgmtVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/bm/baseinfo/prdtMgmt_selExcelDown")
	public ModelAndView selectExcelDownPrdtMgmt(PrdtMgmtVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = prdtMgmtService.selectPrdtMgmtListExcelDown(paramVo);

		//file name
		String fileName = "품목현황";


		// 엑셀 필드타이틀
		String[] title = {"NO", "품목코드","품목명","대분류","중분류","규격","주문단위","원산지","박스당수량","부가세유무","저장형태","선반번호1","선반번호2","입고단가","시작일자","매입처","시작일자","이전매입처","세금계산서발급유무","가로크기","세로크기","높이","중량","제조사","규격유무","유통기한","상품담당자","발주리드타임","기준원가","매입처상품코드","비고","견적가","기한임박","출고부진","사용유무"};

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO",  "PRDT_CD", "PRDT_NM", "L_NM", "M_NM", "PRDT_STD", "ORD_UNIT", "ORIGIN_NM", "QTY_BOX", "VAT_YN", "STRG_TYPE", "LACK_NO_1", "LACK_NO_2", "COST", "COST_START_DT", "BUY_CD", "BUY_START_DT", "PREV_BUY_CD", "TAX_YN", "CASE_W", "CASE_L", "CASE_H", "CASE_WT", "MNFCT", "STD_YN", "EXPRT_DT", "PRDT_MGR_PR", "ORD_LD_TM", "BASE_COST", "BUY_PRDT_CD", "REMARKS", "EST_PRICE", "TIME_LIMIT", "ISS_SLUGGISH", "USE_YN"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolver());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
	

}
