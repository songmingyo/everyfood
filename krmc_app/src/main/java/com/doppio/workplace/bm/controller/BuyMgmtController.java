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
import com.doppio.workplace.as.service.AccSalesDepListVo;
import com.doppio.workplace.bm.service.BuyMgmtService;
import com.doppio.workplace.bm.service. BuyMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;

/**
 * @author Song
 * @Description : 매입처관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03.17 j10000
 * </pre>
 * @version :
 */
@Controller(value = "buyMgmtController")
public class BuyMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(BuyMgmtController.class);
	
	@Autowired
	BuyMgmtService buyMgmtService;
	
	/**
     *  Init Action
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/buyMgmt")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basBuyMgmt";
 	}

	/**
	 * 매입처 마스터 조회 Event
	 * @param BuyOrderVo param
	 * @param HttpServletRequest
	 * @return List<BuyMgmtVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/bm/baseinfo/buyMgmt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectSalesMgmtSearch(BuyMgmtVo param, HttpServletRequest request) throws Exception {
		return buyMgmtService.selectBuyMgmtList(param);
	}
 

	/**
	 * 매입처 마스터 상세 조회 Event
	 * @param BuyOrderVo param
	 * @param HttpServletRequest
	 * @return BuyMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/buyMgmt_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody BuyMgmtVo selectBuyMgmtDetail (HttpServletRequest request,  @RequestBody BuyMgmtVo param) throws Exception {
		return buyMgmtService.selectBuyMgmtData(param);
	}

	/**
	 * 매입처 마스터 저장 Event
	 * @param BuyOrderVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/buyMgmt_insInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result insertBuyMgmtSave (HttpServletRequest request,  @RequestBody BuyMgmtVo param, Result result) throws Exception {
		return buyMgmtService.insertBuyMgmt(request, param);
	}
	
	
	/**
	 * 매입처 관리 Excel Down
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(value="/app/bm/baseinfo/buyMgmList_selExcelDown")
	public ModelAndView selectbuyMgmListExcelDown(BuyMgmtVo param) throws Exception {
		
		// Excel파일 정보/dataList
		ExcelFileVo ExcelFileVo = new ExcelFileVo();

		// 엑셀에 출력할 데이터들을 조회
		List<HashMap<String, String>> datalist	=	buyMgmtService.selectBuyMgmtListExcelDown(param);

		//file name
		String fileName = "매입처 관리";

		// 엑셀 필드타이틀
		String[] title = {	"NO","매입처명", "전화번호", "여부","약명","사업자번호","대표자명", "업태","종목","팩스번호","법인","영업 담당자","영업담당자 휴대폰 번호","마감 담당자", "마감 담당자 휴대폰 번호", "센터 담당자 휴대폰 번호"
						,	"센터 팩스번호", "본사 우편번호", "본사 주소1", "본사 주소2", "센터 우펀번호", "센터 주소1", "센터 주소2", "이메일1", "이메일2", "결제조건", "결제일자", "예금주", "은행명"
						,	"계좌번호", "장려금 유무", "송금 수수료 공제", "장려금 처리", "외상매입 여부", "거래 시작일", "거래 종료일", "비고1", "비고2", "등록자", "등록시간", "수정자", "수정시간"
						 };

		// cell style(정렬위치, 포맷 - 데이터가 숫자인경우)
		String[] dataStyle = {	"CENTER", "LEFT", "CENTER", "CENTER", "LEFT", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"
						,		"CENTER", "CENTER", "LEFT", "LEFT", "CENTER", "LEFT", "LEFT", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER"
						,		"CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "CENTER", "LEFT", "LEFT", "CENTER", "CENTER", "CENTER", "CENTER"
							 };

		// cell width
		int[] cellWidth = {	10, 30, 20, 10, 30, 20, 15, 15, 15, 20, 15, 20, 15, 20, 20, 20
						,	20, 10, 30, 20, 10, 30, 20, 20, 20, 15, 15, 15, 20
						,	20, 10, 10, 10, 10, 20, 20, 30, 30, 20, 25, 20, 25
						  };

		// Keyset(DB에서 조회한 필드명)
		String[] keyset = {	"RNUM", "BUY_NM",  "TEL_NO", "USE_YN", "BUY_S_NM", "BSNS_NUM", "BOSS_NM", "BUSI_CON",  "BUSI_TYPE", "FAX_NO", "CORP_NUM", "SALES_PR_NM", "SALES_PR_HP", "CLOSE_PR_NM", "CLOSE_PR_HP", "CENTER_PR_HP"
						,	"CENTER_FAX", "ZIP_CD", "ADDR_1", "ADDR_2", "CENTER_ZIP_CD", "CENTER_ZIP_ADDR", "CENTER_DTL_ADDR", "EMAIL", "EMAIL_TAX", "SETL_CON", "SETL_DT", "BANK_DEP", "BANK_NM"
						,	"BANK_ACC_NUM", "SUBSIDY_YN", "REM_FEE_CLASS", "SUBSIDY_PRCS_YN", "CR_PUR_YN", "START_DT", "END_DT", "REMARK1", "REMARK2", "REG_NM", "REG_DT", "MOD_NM", "MOD_DT"
						  };

		ExcelFileVo = FileUtil.toExcel(fileName, datalist, title, dataStyle, cellWidth, keyset);
		
		//ModelAndView modelAndView = new ModelAndView("jxlExcelFileDownLoadViewResolver", "excelFile", ExcelFileVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(new JxlExcelFileDownLoadViewResolverNew());
		modelAndView.addObject("excelFile", ExcelFileVo);
		
		return modelAndView;

	}
}
