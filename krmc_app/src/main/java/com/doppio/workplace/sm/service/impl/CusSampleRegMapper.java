package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusSampleRegVo;

/**
 *
 * @Class : CusSampleRegMapper.java
 * @Description : 매출처샘플등록 관리 
 * @author : song 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.      song        	  
 *
 * </pre>
 */

@Repository(value = "cusSampleRegMapper")
public interface CusSampleRegMapper {
	
	
	/**
	 * @Method : selectCusSampleRegList
	 * @Description : 매출처샘플등록 대상자재 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<CusSampleRegVo>
	 */
	public List<CusSampleRegVo> selectCusSamplePrdtList(CusSampleRegVo paramVo);
	
	/**
	 * @Method : selectCusSampleRegDlvDetailList
	 * @Description : 매출처샘플등록 내역 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<CusSampleRegVo>
	 */
	public List<CusSampleRegVo> selectCusSampleRegList(CusSampleRegVo paramVo);
	
	/**
	 * @Method : selectCusSampleRegPrdtAdd
	 * @Description : 매출처샘플등록 하나의 품목만 조회
	 * @param SalSalesGoalRegVo param
	 * @return 
	 */
	public CusSampleRegVo selectCusSampleRegPrdtAdd(CusSampleRegVo paramVo);
	
	/** 
	 * @Method : insertSalesSlipNo
	 * 무조건 신규 전표 생성 
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertSampleSlipNoNew(CusSampleRegVo paramVo) throws SQLException;
	
	/** 
	 * @Method : insertSalesSlipNo
	 * 전포번호 생성 / 조회 
	 * (25개이상인 경우 신규 전표번호 생성 하고 Insert/전표번호 반환,  25이하인경우 전표번호 INSERT 무시, 조회된 전표번호 반환  )  
	  [전표번호 생성 Sequence 및 규칙 적용 필요 ]		
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertSampleSlipNo(CusSampleRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처샘플등록 관리 MERGE(INSERT )
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 */
	public int insertSamepleDlv(CusSampleRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처샘플등록 관리 MERGE(UPDATE)
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 * @return List<CustSalesDlvVo>
	 */
	public int updateSampleDlv(CusSampleRegVo paramVo) throws SQLException;
	

	
	/**
	 * @Method : selectSalesRemarksListCount
	 * @Description : 매출처 전표별 비고 Count
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 */
	public int selectSampleRemarksListCount(CusSampleRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesRemarks
	 * @Description : 매출처 전표별 비고 수정
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 */
	public int updateSampleRemarks(CusSampleRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesRemarks
	 * @Description : 매출처 전표별 비고 입력
	 * @param SalSalesGoalRegVo param
	 * @return Int
	 */	
	public int insertSampleRemarks(CusSampleRegVo paramVo) throws SQLException;


}
