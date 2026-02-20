package com.doppio.workplace.bm.controller;

import java.util.HashMap;
import java.util.List;

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
import com.doppio.common.model.Result;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.bm.service.SalesMgmtService;
import com.doppio.workplace.bm.service.SalesMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;

/**
 * @author Song
 * @Description : 매출처관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "salesMgmtController")
public class SalesMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(SalesMgmtController.class);
	
	@Autowired
	SalesMgmtService salesMgmtService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/salesMgmt")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basSalesMgmt";
 	}

	/**
	 * 매출처 마스터 조회 Event
	 * @param SalesMgmtVo param
	 * @param HttpServletRequest
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/salesMgmt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalesMgmtSearch(SalesMgmtVo param, HttpServletRequest request) throws Exception {
		return salesMgmtService.selectSalesMgmtList(param);
	}
 

	/**
	 * 매출처 마스터 상세 조회 Event
	 * @param SalesMgmtVo param
	 * @param HttpServletRequest
	 * @return SalesMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesMgmt_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalesMgmtVo selectSalesMgmtDetail (HttpServletRequest request,  @RequestBody SalesMgmtVo param) throws Exception {
		return salesMgmtService.selectSalesMgmtData(param);
	}

	/**
	 * 매출처 마스터 저장 Event
	 * @param SalesMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/salesMgmt_insInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result insertSalesMgmtSave (HttpServletRequest request,  @RequestBody SalesMgmtVo param, Result result) throws Exception {
		return salesMgmtService.insertSalesMgmt(request, param);
	}
	
	/**
	 * 메출처 품목별이익현황 다운로드
	 * @param SalSalesPrdtPrftListVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/bm/baseinfo/salesMgmt_selExcelDown")
	public ModelAndView selectExcelDownSalesMgmt(SalesMgmtVo paramVo) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist = salesMgmtService.selectSalesMgmtListExcelDown(paramVo);

		//file name
		String fileName = "메출처 마스터 현황";
		
		// 엑셀 필드타이틀
		String[] title = {"NO", "매출처코드", "매출처 명", "매출처 약어", "본사/지사구분", "본사/지사명", "사업자코드","법인번호","업태","업종","대표자명","전화번호","FAX번호","구매팀 담당자","구매팀 담당자 휴대폰","마감 담당자","마감 담당자 휴대폰","센터 담당자","센터 담당자 휴대폰","센터 담당자 전화번호","센터 팩스","우편코드","주소1","주소2","배송지 우편코드","배송지 주소1","배송지 주소2","이메일","거래시작일","거래종료일","사용유무","지역코드","RMC 영업사원코드","배송차량코드","세금계산서 발급 유무","결제조건","결제일자","여신한도","외상매출 유무","발주마감시간","발주마감 체크유무","비고","거래처 매출 코드", "매출처 구분", "가상계좌번호","이익율 구분","매출일자 구분","장려금 비율", "미수잔액표기유무","단가표시유무"};
		
		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {"CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER","CENTER", "CENTER"};

		// cell width
		int[] cellWidth = {0,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20, 20};
		
		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {"NO", "SALES_CD","SALES_NM","SALES_S_NM","HQ_CLASS_NM","HQ_CD_NM","BSNS_NUM","CORP_NUM","BUSI_CON","BUSI_TYPE","BOSS_NM","TEL_NO","FAX_NO","PUR_PR_NM","PUR_PR_HP","CLOSE_PR_NM","CLOSE_HP","CENTER_PR_NM","CENTER_PR_HP","CENTER_PR_TEL","CENTER_FAX","ZIP_CD","ADDR_1","ADDR_2","DLV_ZIP","DLV_ADDR_1","DLV_ADDR_2","EMAIL","START_DT","END_DT","USE_YN","AREA_CLASS","SALES_PR_CD","CAR_CD","TAX_YN","SETL_CON","SETL_DT","CRE_LIM","CR_SALES_YN","ORD_DDLN_TM","ORD_DDLN_YN","REMARKS","CLT_SALES_CD", "SALES_CLASS",  "VR_ACCT_NO","PROFIT_CLASS","DLV_DT_CLASS","SUBSIDY_RATE","BAL_DISPLAY_YN", "PRICE_DISPLAY_YN"};

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}

}
