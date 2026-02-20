package com.doppio.workplace.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.exception.CustomGenericException;
import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.workplace.bm.service.BuyMgmtVo;
import com.doppio.workplace.bm.service.SalesMgmtVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.workplace.bm.service.DlvrMasterVo;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;


import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;
import com.doppio.workplace.common.service.BizCommonService;



/**
 * @author dada
 * @Class : BizCommonController.java
 * @Description : 업무공통 영역
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 03. 21.         DADA              최초 생성
 *
 * </pre>
 */
@Controller(value = "bizCommonController")
public class BizCommonController {

	@Autowired
	private BizCommonService bizCommonService;
	
	
	
	/**
	 * 매입처 찾기(TFM_BUY_MST) 공통팝업
	 *
	 * @param BuyMstVo
	 * @return List<BuyMstVo>
	 */
	@RequestMapping(value= {"/app/common/findBuyMaster"}, method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>  selectDelivehicleSearch(BuyMgmtVo searchParam) throws Exception {
		return bizCommonService.selectBuyMasterList(searchParam);
	}
	
	/**
	 * 매출처 찾기(TFM_SALES_MST) PC
	 *
	 * @param SalesMgmtVo
	 * @return List<SalesMgmtVo>
	 */
	@RequestMapping(value= {"/app/common/findSalesMaster"}, method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>  findSalesMstSearch(SalesMgmtVo searchParam) throws Exception {
		return bizCommonService.selectSalesMasterList(searchParam);
	}
	
	
	/**
	 * 매출처 찾기(TFM_SALES_MST) 모바일
	 *
	 * @param SalesMgmtVo
	 * @return List<SalesMgmtVo>
	 */
	@RequestMapping(value = "/app/res/common/findSalesMaster", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> findResSalesMstSearch(@RequestBody SalesMgmtVo searchParam) throws Exception {

		return bizCommonService.selectSalesMasterList(searchParam);
	}
	

	/**
	 * 매출처 찾기(TFM_SALES_MST) 
	 * 특정 매출처 상세 조회용 (SALES_CD 필수 조건 )
	 * @param SalesMgmtVo
	 * @return List<SalesMgmtVo>
	 */
	@RequestMapping(value= {"/app/common/findSalesMasterData"}, method=RequestMethod.POST)
	public @ResponseBody SalesMgmtVo findSalesMstData(@RequestBody SalesMgmtVo searchParam) throws Exception {
		return bizCommonService.selectSalesMasterData(searchParam);
	}
	
	
	
	
	/**
	 * 상품 찾기(TFM_SALES_MST) 공통팝업
	 *
	 * @param PrdtMgmtVo
	 * @return List<SalesMgmtVo>
	 */
	@RequestMapping(value = "/app/common/findPrdtMaster", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> findPrdtMstSearch( PrdtMgmtVo searchParam) throws Exception {

		return bizCommonService.selectPrdtMasterList(searchParam);
	}
	
	
	
	/**
	 * 차량배송기사 찾기 
	 * @param DlvrMasterVo searchParam
	 * @return Map<String, List<DlvrMasterVo>> 
	 */
	@RequestMapping(value = "/app/common/findDlvrMaster", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> findDlvrMstSearch(DlvrMasterVo searchParam) throws Exception {
		return bizCommonService.selectDlvrMasterList(searchParam);
	}
	
	/**
	 * 영업사원 찾기 
	 * @param EmpMasterVo searchParam
	 * @return Map<String, List<EmpMasterVo>> 
	 */
	@RequestMapping(value = "/app/common/findSalesPrMaster", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> findSalesPrMstSearch(EmpMasterVo searchParam) throws Exception {
		return bizCommonService.selectSalesPrMasterList(searchParam);
	}
	
	/**
	 * 견적서 찾기 
	 * @param CusSalesEstRegVo searchParam
	 * @return Map<String, List<CusSalesEstRegVo>> 
	 */
	@RequestMapping(value = "/app/common/findEstNum", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> findEstNumSearch( CusSalesEstRegVo searchParam) throws Exception {
		return bizCommonService.selectEstNumList(searchParam);
	}
	
	
	
	/**
	 *  매출처 발주마감 검증 데이터 조회 
	 *  
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/selectSalesOrderClose", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,String> selectSalesOrderClose( HashMap<String, String> searchParam) throws Exception {
		return bizCommonService.selectSalesOrderClose(searchParam);
	}
	
	/**
	 *  전표별 발주마감 여부 검증
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/selectOrderSlipClose", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,String> selectOrderSlipClose( HashMap<String, String> searchParam) throws Exception {
		return bizCommonService.selectOrderSlipClose(searchParam);
	}

	/**
	 *  매출처별 상품 찾기
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/findSalesPrdtMaster", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> selectSalesPrdtMaster( SalesPrdtMgmtVo searchParam) throws Exception {
		return bizCommonService.selectSalesPrdtMaster(searchParam);
	}
	
	/**
	 *  매출처별 상품 찾기
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/findCurOrdRegSalesPrdtMaster", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> selectCurOrdRegSalesPrdtMaster( SalesPrdtMgmtVo searchParam) throws Exception {
		return bizCommonService.selectCurOrdRegSalesPrdtMaster(searchParam);
	}
	
	/**
	 * 품목 재고 조회
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/selectPrdtStkQty", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody PrdtMgmtVo selectPrdtStkQty (HttpServletRequest request,  @RequestBody PrdtMgmtVo param) throws Exception {
		return bizCommonService.selectPrdtStkQty(param);
	}
	
	/**
	 * 매출처 추가 데이터(여신한도, 전월미수, 당월매출 등등)
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/selectSalesAddData", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody SalesMgmtVo selectSalesAddData (HttpServletRequest request,  @RequestBody SalesMgmtVo param) throws Exception {
		return bizCommonService.selectSalesAddData(param);
	}
	
	/**
	 *  샘플 등록 매출처별 상품 찾기
	 * @param HashMap<String, String> searchParam
	 * @return HashMap<String,String>
	 */
	@RequestMapping(value = "/app/common/findCurSampleRegPrdtMaster", method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> selectCurSampleRegPrdtMaster( SalesPrdtMgmtVo searchParam) throws Exception {
		return bizCommonService.selectCurSampleRegPrdtMaster(searchParam);
	}
	
	/**
	 * 비밀번호 변경(초기화) 페이지호출 - 인증코드로 접근시
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/security/pwReset", method = RequestMethod.GET)
	public ModelAndView initPwReset(Model model, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		String accessCode = StringUtils.defaultString(request.getParameter("incKey")); 
		String decAccessCode = ""; //accessCode 암호화 해제

    	accessCode = accessCode.replaceAll("@", "%");
    	decAccessCode = StringUtil.getDecrypt(accessCode);
    	decAccessCode = URLDecoder.decode(decAccessCode, "UTF-8");
    	String[] accessCodeDec = decAccessCode.split("_"); //암호화 해제
    	
    	if(accessCodeDec.length != 2) {	// 비정상적인 parameter 또는 누락된 정보
    		throw new AccessDeniedException("Access Denide Exception"); 
    	}
    	
    	UserInfoVo param = new UserInfoVo();
    	String forgotPwdCode   = accessCodeDec[0]; // 인증코드
    	String forgotPwdLstDy  = DateUtil.getNowDate("yyyyMMdd"); //코드 유효일자

    	// 사용자고유번호로 사용자 검증
    	param.setMemberCd(accessCodeDec[1]);			// 사용자고유번호
    	UserInfoVo userVo = bizCommonService.selectUserInfo(param); 

    	if(userVo == null) {
    		// 사용자 정보 없을경우 커스텀 오류페이지로 이동
    		throw new CustomGenericException("E300", "Not found UserData!");
    	}

    	if(!forgotPwdCode.equals(userVo.getForgotPwdCode()) ||  !forgotPwdLstDy.equals(userVo.getForgotPwdLstDy())) {
    		// 인증코드 불일치 또는 생성일자가 오늘날짜가 아닌경우 커스텀 오류페이지로 이동
    		throw new CustomGenericException("E301", "Fail find password info.");
    	}

		mv.addObject("userVo", userVo);
		mv.addObject("accessType","FG"); //비밀번호 변경(초기화) 구분 (FG:비밀번호 찾기, FR:임시비밀번호 발급(운영자가 생성)에 따른 접근, 3M: 비밀번호 3개월 변경)
		mv.setViewName("/security/pwReset");
		return mv;
	}	
	
	
	/**
	 * 비밀번호 변경(초기화) 페이지호출 - 필수변경유무에 따른 접근
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/security/pwEsnReset", method = RequestMethod.GET)
	public ModelAndView initPwEsnReset(Model model, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		CustomUser customUser = null;
		// 로그인한 사용자정보 조회----------------------------------------------------------------------------------------------
		if(!"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())) {
			customUser= (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}else {
			throw new CustomGenericException("E300", "Not found UserData!");
		}
		// 로그인한 사용자정보 조회----------------------------------------------------------------------------------------------

    	if(customUser == null) {
    		// 사용자 정보 없을경우 커스텀 오류페이지로 이동
    		throw new CustomGenericException("E300", "Not found UserData!");
    	}

		mv.addObject("userVo", customUser);
		
		if("Y".equals(StringUtils.defaultString(customUser.getPw3mFlag()))) {
			mv.addObject("accessType","3M"); //비밀번호 변경(초기화) 구분 (FG:비밀번호 찾기, FR:임시비밀번호 발급(운영자가 생성)에 따른 접근, 3M: 비밀번호 3개월 변경)
		}else {
			mv.addObject("accessType","FR"); //비밀번호 변경(초기화) 구분 (FG:비밀번호 찾기, FR:임시비밀번호 발급(운영자가 생성)에 따른 접근, 3M: 비밀번호 3개월 변경)
		}
		
		mv.setViewName("/security/pwReset");
		return mv;
	}	
	
	
	
	/**
	 * 
	 * @Method : updatePwdInfo
	 * @Description : 사용자-비밀번호 변경(초기화) 저장 
	 * @param paramVo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value= {"/security/pwReset_updatePwdInfo.json","/front/common/updatePwdInfo.json"}, method=RequestMethod.POST)
	public @ResponseBody Result updatePwdInfo(@RequestBody UserInfoVo paramVo,HttpServletRequest request) throws Exception{
		return bizCommonService.updatePwdInfo(paramVo, request);
	}
	
	
	/**
	 * 
	 * @Method : updatePwdLater
	 * @Description : 사용자-비밀번호 다음에 변경 
	 * @param paramVo
	 * @return result
	 * @throws Exception
	 */
	@RequestMapping(value="/security/pwReset_updPwdLater.json", method=RequestMethod.POST)
	public @ResponseBody Result updatePwdLater(@RequestBody UserInfoVo paramVo) throws Exception{
		return bizCommonService.updatePwdLater(paramVo);
	}
	
}