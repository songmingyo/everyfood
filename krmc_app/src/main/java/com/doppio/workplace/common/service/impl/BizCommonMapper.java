package com.doppio.workplace.common.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.doppio.workplace.bm.service.BuyMgmtVo;
import com.doppio.workplace.bm.service.SalesMgmtVo;
import com.doppio.workplace.bm.service.DlvrMasterVo;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;

import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;


/**
 * 
 * @Class : BizCommonMapper.java
 * @Description :업무 공통
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
@Repository(value="bizCommonMapper")
public interface BizCommonMapper {


	/**
	 * @Method : selectBuyMasterListCount
	 * @Description : 매입처 조회 PAGE COUNT 조회 
	 * @param BuyMstVo paramVo
	 * @return INT
	 */
	public int selectBuyMasterListCount(BuyMgmtVo paramVo);
	
	/**
	 * @Method : selectBuyMasterList
	 * @Description : 매입처 조회(TFM_BUY_MST)
	 * @param BuyMstVo paramVo
	 * @return List<BuyMstVo>
	 */
	public List<BuyMgmtVo> selectBuyMasterList(BuyMgmtVo paramVo);
	
	/**
	 * @Method : selectSalesMasterListCount
	 * @Description : 매출처 조회 PAGE COUNT 조회 
	 * @param SalesMgmtVo paramVo
	 * @return INT
	 */
	public int selectSalesMasterListCount(SalesMgmtVo paramVo);
	
	/**
	 * @Method : selectSalesMasterList
	 * @Description : 매출처 조회(TFM_BUY_MST)
	 * @param SalesMgmtVo paramVo
	 * @return List<BuyMstVo>
	 */
	public List<SalesMgmtVo> selectSalesMasterList(SalesMgmtVo paramVo);
	
	/**
	 * @Method : selectPrdtMasterListCount
	 * @Description : 상품 조회 PAGE COUNT 조회 
	 * @param PrdtMgmtVo paramVo
	 * @return INT
	 */
	public int selectPrdtMasterListCount(PrdtMgmtVo paramVo);
	
	/**
	 * @Method : selectPrdtMasterList
	 * @Description : 상품 조회(TFM_PRDT_MST)
	 * @param PrdtMgmtVo paramVo
	 * @return List<PrdtMgmtVo>
	 */
	public List<PrdtMgmtVo> selectPrdtMasterList(PrdtMgmtVo paramVo);

	
	
	/**
	 * @Method : selectDlvrMasterListCount
	 * @Description : 배송차량 조회(TFM_DLVR_MST) PAGE COUNT 조회 
	 * @param DlvrMasterVo paramVo
	 * @return INT
	 */
	public int selectDlvrMasterListCount(DlvrMasterVo paramVo);
	
	/**
	 * @Method : selectDlvrMasterList
	 * @Description : 배송차량 조회(TFM_DLVR_MST)
	 * @param DlvrMasterVo paramVo
	 * @return List<DlvrMasterVo>
	 */
	public List<DlvrMasterVo> selectDlvrMasterList(DlvrMasterVo paramVo);
	

	/**
	 * @Method : selectSalesPrMasterListCount
	 * @Description : 영업사원 조회(TFM_SALES_PR_MST) PAGE COUNT 조회 
	 * @param EmpMasterVo paramVo
	 * @return INT
	 */
	public int selectSalesPrMasterListCount(EmpMasterVo paramVo);
	
	/**
	 * @Method : selectSalesPrMasterList
	 * @Description : 영업사원 조회(TFM_SALES_PR_MST)
	 * @param EmpMasterVo paramVo
	 * @return List<EmpMasterVo>
	 */
	public List<EmpMasterVo> selectSalesPrMasterList(EmpMasterVo paramVo);
	
	/**
	 * @Method : selectEstNumListCount
	 * @Description : 견적서 번호 COUNT 조회 
	 * @param CusSalesEstRegVo paramVo
	 * @return INT
	 */
	public int selectEstNumListCount(CusSalesEstRegVo paramVo);
	
	/**
	 * @Method : selectEstNumList
	 * @Description : 견적서 번호 조회
	 * @param CusSalesEstRegVo paramVo
	 * @return List<CusSalesEstRegVo>
	 */
	public List<CusSalesEstRegVo> selectEstNumList(CusSalesEstRegVo paramVo);
	
	
	/**
	 * @Method : selectSalesOrderCloseCheck
	 * @Description : 매출처 발주마감 검증
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectSalesOrderCloseCheck(HashMap<String, String> paramMap);
	

	/**
	 * @Method : selectOrderSlipCloseCheck
	 * @Description : 전표별 발주마감 여부 검증
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectOrderSlipCloseCheck(HashMap<String, String> paramMap);
	
	/**
	 * @Method : selectSalesPrdtListCount
	 * @Description : 매출처별 상품 PAGE COUNT 조회 
	 * @param EmpMasterVo paramVo
	 * @return INT
	 */
	public int selectSalesPrdtListCount(SalesPrdtMgmtVo paramVo);
	
	/**
	 * @Method : selectSalesPrdtList
	 * @Description : 매출처별 상품 조회
	 * @param SalesPrdtMgmtVo paramVo
	 * @return List<SalesPrdtMgmtVo>
	 */
	public List<SalesPrdtMgmtVo> selectSalesPrdtList(SalesPrdtMgmtVo paramVo);
	
	/**
	 * @Method : selectCurOrdRegSalesPrdtMaster
	 * @Description : 매출처 발주화면의 매출처별 상품 조회
	 * @param SalesPrdtMgmtVo paramVo
	 * @return List<SalesPrdtMgmtVo>
	 */
	public List<SalesPrdtMgmtVo> selectCurOrdRegSalesPrdtMaster(SalesPrdtMgmtVo paramVo);
	
	
	/**
	 * @Method : selectPrdtStkQty
	 * @Description : 품목 재고 조회
	 * @param HashMap<String, String> paramVo
	 * @return HashMap<String, String>
	 */
	public PrdtMgmtVo selectPrdtStkQty(PrdtMgmtVo paramMap);
	
	/**
	 * @Method : selectPrdtStkQty
	 * @Description : 매출처 추가 데이터(여신한도, 전월미수, 당월매출 등등)
	 * @param SalesMgmtVo paramVo
	 * @return SalesMgmtVo
	 */
	public SalesMgmtVo selectSalesAddData(SalesMgmtVo paramMap);
	
	/**
	 * @Method : selectCurSampleRegPrdtMaster
	 * @Description : 매출처 발주화면의 매출처별 상품 조회
	 * @param SalesPrdtMgmtVo paramVo
	 * @return List<SalesPrdtMgmtVo>
	 */
	public List<SalesPrdtMgmtVo> selectCurSampleRegPrdtMaster(SalesPrdtMgmtVo paramVo);
	
	
	/**
	 * 
	 * @Method : updateUserPwLater
	 * @Description : 비밀번호 다음에 변경
	 * @param paramVo
	 * @return int
	 */
	public int updateUserPwLater(UserInfoVo paramVo);
	
	
	
	
	
	
	/**
	 * 
	 * @Method : selectPlsUserInfo
	 * @Description : 사용자 정보 조회(PLS 계정)
	 * @param paramVo
	 * @return UserInfoVo
	 */
	public UserInfoVo selectPlsUserInfo(UserInfoVo paramVo)throws SQLException;
	
	

	/**
	 * 
	 * @Method : updateUserPwInfo
	 * @Description : 비밀번호 update
	 * @param paramVo
	 * @return int
	 */
	public int updateUserPwInfo(UserInfoVo paramVo)throws SQLException;
	
	
}
