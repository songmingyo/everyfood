package com.doppio.workplace.common.service.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.exception.BusinessException;
import org.springframework.tronic.util.StringUtil;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.security.service.SecurityUtil;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.bm.service.DlvrMasterVo;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.common.model.UserInfoVo;
import com.doppio.workplace.common.service.BizCommonService;
import com.doppio.workplace.cs.service.ClsDayStkListVo;
import com.doppio.workplace.cs.service.impl.ClsDayStkListMapper;
import com.doppio.workplace.bm.service.SalesMgmtVo;
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.bm.service.BuyMgmtVo;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;
import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;


/**
 * 
 * @Class : PlsCommonServiceImpl.java
 * @Description : 업무 공통
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 0. 0.                       최초 생성
 *
 * </pre>
 */
@Service("bizCommonService")
public class BizCommonServiceImpl implements BizCommonService{

	@Autowired 
	BizCommonMapper bizCommonMapper;
	
	@Autowired
	ClsDayStkListMapper clsDayStkListMapper;
	
	@Autowired
	CustomUserService customUserService;
	

	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	
	/**
	 * @Method : selectBuyMasterList
	 * @Description : 매입처 정보조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> selectBuyMasterList(BuyMgmtVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<BuyMgmtVo> 		resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = bizCommonMapper.selectBuyMasterListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  bizCommonMapper.selectBuyMasterList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	/**
	 * @Method : selectSalesMasterList
	 * @Description : 매출처 정보조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectSalesMasterList(SalesMgmtVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<SalesMgmtVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  bizCommonMapper.selectSalesMasterList(paramVo);
		
		int totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);		
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	/**
	 * @Method : selectSalesMasterData
	 * @Description : 매출처 정보조회(SALES_CD 한건 조회 ) 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public SalesMgmtVo selectSalesMasterData(SalesMgmtVo paramVo) throws Exception {
		SalesMgmtVo  salesMgmtVo = new SalesMgmtVo();
		
		if( StringUtils.isEmpty(paramVo.getFind_salesCd()) ) return salesMgmtVo;
		
		paramVo.setStartRowNo(1);
		paramVo.setEndRowNo(1);
		List<SalesMgmtVo> resultList =  bizCommonMapper.selectSalesMasterList(paramVo);
		
		if(resultList != null && resultList.size() == 1) salesMgmtVo =  resultList.get(0);
	
		return salesMgmtVo;
	}
	
	
	
	/**
	 * @Method : selectSalesMasterList
	 * @Description : 매출처 정보조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectPrdtMasterList(PrdtMgmtVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<PrdtMgmtVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = bizCommonMapper.selectPrdtMasterListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  bizCommonMapper.selectPrdtMasterList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	/**
	 * @Method : selectDlvrMasterList
	 * @Description : 차량배송기사 조회 
	 * @param DlvrMasterVo  paramVo
	 * @return Map<String,Object> 
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectDlvrMasterList(DlvrMasterVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<DlvrMasterVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = bizCommonMapper.selectDlvrMasterListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {			      
			resultList =  bizCommonMapper.selectDlvrMasterList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	

	/**
	 * @Method : selectSalesPrMasterList
	 * @Description : 영업사원 조회 
	 * @param EmpMasterVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectSalesPrMasterList(EmpMasterVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<EmpMasterVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = bizCommonMapper.selectSalesPrMasterListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {			      
			resultList =  bizCommonMapper.selectSalesPrMasterList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	/**
	 * @Method : selectEstNumList
	 * @Description : 영업사원 조회 
	 * @param CusSalesEstRegVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectEstNumList(CusSalesEstRegVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<CusSalesEstRegVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = bizCommonMapper.selectEstNumListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {			      
			resultList =  bizCommonMapper.selectEstNumList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	
	
	/**
	 * @Method : selectSalesOrderClose
	 * @Description : 매출처 발주마감 검증
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 * HashMap
	 * 		SALES_CD			매출처코드
	 * 		ORD_DDLN_TM			마감시간 (1700)
	 * 		ORD_DDLN_CLOSE_YN	마감유무 
	 */
	public HashMap<String, String> selectSalesOrderClose(HashMap<String, String> paramMap) throws Exception{
		
		return bizCommonMapper.selectSalesOrderCloseCheck(paramMap);
		 
	}
	

	/**
	 * @Method : selectOrderSlipClose
	 * @Description : 전표별 발주마감 여부 검증
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 * HashMap
	 * 		SALES_SLIP_NO		매출처코드
	 * 		CLOSE_FLAG			발주마감유무(Y/N)
	 * 		REG_ID				마감처리 사용자 아이디 
	 *	    REG_USER_NM			마감처리 사용자명 
	 *	    REG_DT				처리일자('%Y-%m-%d %T')
	 */
	public HashMap<String, String> selectOrderSlipClose(HashMap<String, String> paramMap) throws Exception{
		HashMap<String, String> responseMap = null;
		responseMap =  bizCommonMapper.selectOrderSlipCloseCheck(paramMap);
		
		if(responseMap == null ) {
			responseMap = new HashMap<String, String>();
			responseMap.put("SALES_SLIP_NO", (String)paramMap.get("SALES_SLIP_NO"));
		}
		return responseMap;
	}
	
	/**
	 * @Method : selectSalesPrdtMaster
	 * @Description : 매출처별 상품 찾기
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String,Object> selectSalesPrdtMaster(SalesPrdtMgmtVo paramVo) throws Exception{
		
		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<SalesPrdtMgmtVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  bizCommonMapper.selectSalesPrdtList(paramVo);
		
		int totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	/**
	 * @Method : selectCurOrdRegSalesPrdtMaster
	 * @Description : 매출처 발주화면의 매출처별 상품 찾기
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String,Object> selectCurOrdRegSalesPrdtMaster(SalesPrdtMgmtVo paramVo) throws Exception{
		
		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<SalesPrdtMgmtVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		paramVo.setFind_userType(customUser.getUserType());
		
		resultList =  bizCommonMapper.selectCurOrdRegSalesPrdtMaster(paramVo);
		
		int totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	/**
	 * @Method : selectPrdtStkQty
	 * @Description : 품목 재고 조회
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public PrdtMgmtVo selectPrdtStkQty(PrdtMgmtVo paramVo) throws Exception {
//		return  bizCommonMapper.selectPrdtStkQty(paramVo);
		
		/*********** 당월, 전월, 전전월 출고량 조회 ***********/
		ClsDayStkListVo paramVo1	=	new ClsDayStkListVo();
		paramVo1.setPrdtCd(paramVo.getPrdtCd());
		
		ClsDayStkListVo rslt1	=	clsDayStkListMapper.selPrdtQtyMonth(paramVo1);
		/*********************************************/
		
		
		/*********** 하남, 여주 재고 조회 **************/
		PrdtMgmtVo rslt2	=	bizCommonMapper.selectPrdtStkQty(paramVo);
		/*****************************************/
		
		rslt2.setBoxQtyComp1(rslt1.getBoxQtyComp1());
		rslt2.setBoxQtyComp2(rslt1.getBoxQtyComp2());
		rslt2.setBoxQtyComp3(rslt1.getBoxQtyComp3());
		
		return rslt2;
	}
	
	
	/**
	 * @Method : selectSalesAddData
	 * @Description :매출처 추가 데이터(여신한도, 전월미수, 당월매출 등등)
	 * @param SalesMgmtVo paramVo
	 * @return SalesMgmtVo
	 */
	public SalesMgmtVo selectSalesAddData(SalesMgmtVo paramVo) throws Exception {
		return bizCommonMapper.selectSalesAddData(paramVo);
	}
	
	/**
	 * @Method : selectCurSampleRegPrdtMaster
	 * @Description : 샘플 화면의 매출처별 상품 찾기
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String,Object> selectCurSampleRegPrdtMaster(SalesPrdtMgmtVo paramVo) throws Exception{
		
		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<SalesPrdtMgmtVo> 	resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  bizCommonMapper.selectCurSampleRegPrdtMaster(paramVo);
		
		int totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	
	
	
	
	
	
	/**
	 * 사용자 정보 조회
	 */
	@Override
	public UserInfoVo selectUserInfo(UserInfoVo paramVo)throws Exception{
		UserInfoVo userVo = null;
		userVo = bizCommonMapper.selectPlsUserInfo(paramVo); //pls 계정정보 조회
		
		
		if(userVo == null) {
			userVo = new UserInfoVo();
		}
		
		return userVo;
	}
	
	/**
	 * 비밀번호 변경(초기화) 저장  
	 */
	@Override
	public Result updatePwdInfo(UserInfoVo paramVo, HttpServletRequest request)throws Exception{

		Result result = new Result();
		result.setMsgCd(Result.FAIL);

		String memberCd = StringUtils.defaultString(paramVo.getMemberCd()); //사용자 고유번호
		String userPw = StringUtils.defaultString(paramVo.getUserPw());		//비밀번호
		String newPw = "";
		String genDivnCd =""; //사용자 생성타입[9007]
		
		String email = StringUtils.defaultString(paramVo.getEmail());		//이메일
		String notIncId =""; //pw에 포함되면 안되는문자(아이디)
		
		String accessType = StringUtils.defaultString(paramVo.getAccessType()); //비밀번호 변경 접근 유형
		
		String encryptPassword =""; //암호화된 비밀번호
		
		if(!"MY".equals(accessType)) { 
		
			/*
			if(email.indexOf("@")>-1) {
				notIncId = email.split("@")[0];
			}
			
			if("".equals(email)) {
				throw new BusinessException("noData","사용자 아이디 정보가 없습니다.");
			}
			*/
		}else {
			//나의정보
			newPw = StringUtils.defaultString(paramVo.getNewPw());		//새 비밀번호
		}
		
		if ("".equals(memberCd)) {
			throw new BusinessException("noData","사용자고유번호 정보가 없습니다.");
		}
		
		
		if("".equals(userPw)) {
			throw new BusinessException("noData","비밀번호를 입력하세요.");
		}
		
		//사용자 정보 조회
		UserInfoVo userVo = this.selectUserInfo(paramVo);	
		
		if(userVo!=null) {
			genDivnCd = StringUtils.defaultString(userVo.getGenDivnCd());
			
		
			
			if("N".equals(StringUtils.defaultString(paramVo.getUseYn()))) {
				//탈퇴회원인경우 회원정보 수정 불가
				throw new BusinessException("withDrawFg","탈퇴처리된 회원입니다. 비밀번호를 변경할 수 없습니다.");
			}
			
			if(!"".equals(notIncId) && userPw.indexOf(notIncId)>-1) {
				throw new BusinessException("incId","비밀번호에 아이디는 포함할 수 없습니다.");
			}
			
			if(!"MY".equals(accessType)) {
				//비밀번호 형식 확인
				
				/*
				if(!userPw.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$")) {
					throw new BusinessException("notMatch","비밀번호는 숫자와 영문자,특수문자를 혼용하여 8~25글자를 입력해야 합니다.");
				}
				*/
				encryptPassword = SecurityUtil.getEncryptedPassword(userPw);	//비밀번호 암호화
			}else {
				//새 비밀번호 형식 확인
				/*
				if(!newPw.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$")) {
					throw new BusinessException("notMatch","비밀번호는 숫자와 영문자,특수문자를 혼용하여 8~25글자를 입력해야 합니다.");
				}
				*/
				//현재 비밀번호 검증
				Result pwVerify = selectUserVerify(userPw);
				
				if(!"success".equals(pwVerify.getMsgCd())) {
					throw new BusinessException("verifyFail","현재 비밀번호가 일치하지 않습니다.");
				}
				
				encryptPassword = SecurityUtil.getEncryptedPassword(newPw);	//비밀번호 암호화
			}
			
			userVo.setWorkId(memberCd);
			userVo.setUserPw(encryptPassword);
			userVo.setPassChgYn("N"); //비밀번호 필수변경 여부
			userVo.setPassFailCnt("0");//비밀번호 실패횟수 초기화
			userVo.setLockYn("N");//Lock 여부 N
			userVo.setForgotPwdCode("");
			int cnt = bizCommonMapper.updateUserPwInfo(userVo); //비밀번호, 비밀번호최종변경일자(오늘날짜) update
			
			if(cnt>0) {
				result.setMsgCd(Result.SUCCESS);
				
				if("MY".equals(accessType)) { //나의정보[비밀번호 변경]
					// 로그인한 사용자정보 조회-------
					CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					// 로그인한 사용자정보 조회-------
					
					//세션변경
					customUserService.loadUserByPlsUserChangedSession(customUser.getUserId(), newPw, request);
					
				}else if(!"FG".equals(accessType)&& !"".equals(accessType)) { //임시비밀번호 변경 or 비밀번호 3개월 변경시 로그인처리
					// 로그인한 사용자정보 조회-------
					CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					// 로그인한 사용자정보 조회-------
					
					//세션변경
					customUserService.loadUserByPlsUserChangedSession(customUser.getUserId(), userPw, request);
				}
				
			}
			
			
		}else {
			throw new BusinessException("noData","사용자 정보가 없습니다. 비밀번호 변경이 불가합니다.");
		}
		
		return result;
	}
	
	
	

	/**
	 * 사용자-비밀번호 다음에 변경
	 */
	public Result updatePwdLater(UserInfoVo paramVo)throws Exception{
		
		Result result = new Result();
		result.setMsgCd(Result.SUCCESS);

		String memberCd = StringUtils.defaultString(paramVo.getMemberCd()); //사용자 고유번호
		String email = StringUtils.defaultString(paramVo.getEmail());		//이메일
		String accessType = StringUtils.defaultString(paramVo.getAccessType()); //비밀번호 변경 접근 유형
		String genDivnCd =""; //사용자 생성타입[9007]
		
		if ("".equals(memberCd)) {
			throw new BusinessException("noData","사용자고유번호 정보가 없습니다.");
		}
		
		if("".equals(email)) {
			throw new BusinessException("noData","사용자 아이디 정보가 없습니다.");
		}
	
		if("".equals(accessType)) {
			throw new BusinessException("noData","페이지 접근 정보가 없습니다.");
		}
		
		
		//사용자 정보 조회
		UserInfoVo userVo = this.selectUserInfo(paramVo);
		
		if(userVo!=null) {
			genDivnCd = StringUtils.defaultString(userVo.getGenDivnCd());
			
			
			if("N".equals(StringUtils.defaultString(paramVo.getUseYn()))) {
				//탈퇴회원인경우 회원정보 수정 불가
				throw new BusinessException("withDrawFg","탈퇴처리된 회원입니다.");
			}
			userVo.setWorkId(memberCd);
			userVo.setPassChgYn("N"); //비밀번호 필수변경 여부
			userVo.setPassFailCnt("0");//비밀번호 실패횟수 초기화
			userVo.setLockYn("N");//Lock 여부 N
			
			int cnt = bizCommonMapper.updateUserPwLater(userVo); //비밀번호최종변경일자(오늘날짜+90일) update
			
			if(cnt>0) {
				result.setMsgCd(Result.SUCCESS);
			}
			
			
		}else {
			throw new BusinessException("noData","사용자 정보가 없습니다. 비밀번호 변경이 불가합니다.");
		}
		
		
		return result;
		
	}
	
	
	
	/**
	 * 사용자 비밀번호 검증
	 */
	private Result selectUserVerify(String pw) throws Exception{
		Result result = new Result();
		result.setMsgCd(Result.FAIL);
		
		/*작업자 아이디  생성---------------------------------------*/
			CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userPw = StringUtils.defaultString(customUser.getPassword());
			String userId = StringUtils.defaultString(customUser.getUserId());
			String genDivnCd = StringUtils.defaultString(customUser.getGenDivnCd());
		/*-------------------------------------------------------*/
			String encryptPassword ="";
			String params = "";
			String userEncPw = "";
			String userEncId ="";
		
			encryptPassword = SecurityUtil.getEncryptedPassword(pw); //입력받은 pw 암호화
			
			if("".equals(encryptPassword)) {
				result.setMsgCd("ERR-01");
				result.setMessage("로그인 사용자 비밀번호 정보 없음");
				return result;
			}
			
			if(!encryptPassword.equals(userPw)) {
				result.setMsgCd("ERR-01");
				result.setMessage("사용자 비밀번호 불일치");
				return result;
			}
		
			
			result.setMsgCd(Result.SUCCESS);
			return result;	
	}

	
	
}