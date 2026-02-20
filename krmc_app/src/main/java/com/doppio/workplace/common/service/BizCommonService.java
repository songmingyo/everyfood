package com.doppio.workplace.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.BuyMgmtVo;
import com.doppio.workplace.bm.service.SalesMgmtVo;
import com.doppio.workplace.bm.service.DlvrMasterVo;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;



/**
 * @author dada
 * @Class : BizCommonService.java
 * @Description : 업무 공통
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 03. 21.       DADA              최초 생성
 *
 * </pre>
 */
public interface BizCommonService {

	
	/**
	 * @Method : selectBuyMasterList
	 * @Description : 매입처조회
	 * @param BuyMstVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public Map<String,Object> selectBuyMasterList(BuyMgmtVo paramVo) throws Exception;
	
	/**
	 * @Method : selectSalesMasterList
	 * @Description : 매출처조회
	 * @param SalesMgmtVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public HashMap<String,Object> selectSalesMasterList(SalesMgmtVo paramVo) throws Exception;
	

	/**
	 * @Method : selectSalesMasterData
	 * @Description : 매출처조회 SALES_CD 한건 조회 
	 * @param SalesMgmtVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public SalesMgmtVo selectSalesMasterData(SalesMgmtVo paramVo) throws Exception ;
	
	/**
	 * @Method : selectPrdtMasterList
	 * @Description : 상품 조회
	 * @param PrdtMgmtVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public HashMap<String,Object> selectPrdtMasterList(PrdtMgmtVo paramVo) throws Exception;
	
	
	/**
	 * @Method : selectDlvrMasterList
	 * @Description : 차량배송기사 조회 
	 * @param DlvrMasterVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public HashMap<String,Object> selectDlvrMasterList(DlvrMasterVo paramVo) throws Exception;
	
	/**
	 * @Method : selectSalesPrMasterList
	 * @Description : 영업사원 조회 
	 * @param EmpMasterVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public HashMap<String,Object> selectSalesPrMasterList(EmpMasterVo paramVo) throws Exception;
	
	/**
	 * @Method : selectEstNumSearch
	 * @Description : 견적번호 조회 
	 * @param CusSalesEstRegVo paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	public HashMap<String,Object> selectEstNumList(CusSalesEstRegVo paramVo) throws Exception;
	
	
	
	/**
	 * @Method : selectSalesOrderClose
	 * @Description : 매출처 발주마감 검증
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectSalesOrderClose(HashMap<String, String> paramMap) throws Exception;

	/**
	 * @Method : selectOrderSlipClose
	 * @Description : 전표별 발주마감 여부 검증
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectOrderSlipClose(HashMap<String, String> paramMap) throws Exception;

	/**
	 * @Method : selectSalesPrdtMaster
	 * @Description : 매출처 품목 조회
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, Object> selectSalesPrdtMaster(SalesPrdtMgmtVo paramMap) throws Exception;
	
	/**
	 * @Method : selectCurOrdRegSalesPrdtMaster
	 * @Description : 매출처 발주 화면의 매출처 품목 조회
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, Object> selectCurOrdRegSalesPrdtMaster(SalesPrdtMgmtVo paramMap) throws Exception;

	/**
	 * @Method : selectPrdtStkQty
	 * @Description : 품목 재고 조회
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 */
	public PrdtMgmtVo selectPrdtStkQty(PrdtMgmtVo paramMap) throws Exception;
	
	/**
	 * @Method : selectSalesAddData
	 * @Description : 매출처 추가 데이터(여신한도, 전월미수, 당월매출 등등)
	 * @param SalesMgmtVo paramVo
	 * @return SalesMgmtVo
	 */
	public SalesMgmtVo selectSalesAddData(SalesMgmtVo paramMap) throws Exception;
	
	/**
	 * @Method : selectCurSampleRegPrdtMaster
	 * @Description : 샘플 화면의 매출처 품목 조회
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, Object> selectCurSampleRegPrdtMaster(SalesPrdtMgmtVo paramMap) throws Exception;
	
	
	
	/**
	 * 
	 * @Method : selectUserInfo
	 * @Description : 사용자 정보 조회
	 * @param paramVo
	 * @return UserInfoVo
	 * @throws Exception
	 */
	public UserInfoVo selectUserInfo(UserInfoVo paramVo)throws Exception;
	
	/**
	 * 
	 * @Method : updatePwdInfo
	 * @Description :  비밀번호 변경(초기화) 저장  
	 * @param paramVo
	 * @return result
	 * @throws Exception
	 */
	public Result updatePwdInfo(UserInfoVo paramVo,HttpServletRequest request)throws Exception;
	
	
	/**
	 * 
	 * @Method : updatePwdInfo
	 * @Description :  비밀번호 다음에 변경하기 
	 * @param paramVo
	 * @return result
	 * @throws Exception
	 */
	public Result updatePwdLater(UserInfoVo paramVo)throws Exception;
	

}
