package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusRtnRegVo;

/**
 *
 * @Class : CusRtnRegMapper.java
 * @Description : 매출처반품등록 관리 
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

@Repository(value = "cusRtnRegMapper")
public interface CusRtnRegMapper {
	
	
	/**
	 * @Method : selectCusRtnRegList
	 * @Description : 매출처반품등록 목록 조회 
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	public List<CusRtnRegVo> selectCusRtnRegList(CusRtnRegVo paramVo);
	
	/**
	 * @Method : selectCusRtnRegDlvDetailList
	 * @Description : 매출처반품등록 내역 조회 
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	public List<CusRtnRegVo> selectCusRtnRegDetailList(CusRtnRegVo paramVo);
	
	/**
	 * @Method : selectCusRtnRegPrdtAdd
	 * @Description : 매출처반품등록 하나의 품목만 조회
	 * @param CusRtnRegVo param
	 * @return 
	 */
	public CusRtnRegVo selectCusRtnRegPrdtAdd(CusRtnRegVo paramVo);
	
	/** 
	 * @Method : insertSalesSlipNo
	 * 무조건 신규 전표 생성 
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertRtnSlipNoNew(CusRtnRegVo paramVo) throws SQLException;
	
	/** 
	 * @Method : insertSalesSlipNo
	 * 전포번호 생성 / 조회 
	 * (25개이상인 경우 신규 전표번호 생성 하고 Insert/전표번호 반환,  25이하인경우 전표번호 INSERT 무시, 조회된 전표번호 반환  )  
	  [전표번호 생성 Sequence 및 규칙 적용 필요 ]		
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertRtnSlipNo(CusRtnRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처반품등록 관리 MERGE(INSERT )
	 * @param CusRtnRegVo param
	 * @return Int
	 */
	public int insertSalesRtnReg(CusRtnRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처반품등록 관리 MERGE(UPDATE)
	 * @param CusRtnRegVo param
	 * @return Int
	 * @return List<CustSalesDlvVo>
	 */
	public int updateSalesRtnReg(CusRtnRegVo paramVo) throws SQLException;
	

	
	/**
	 * @Method : selectSalesRemarksListCount
	 * @Description : 매출처 전표별 비고 Count
	 * @param CusRtnRegVo param
	 * @return Int
	 */
	public int selectSalesRemarksListCount(CusRtnRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesRemarks
	 * @Description : 매출처 전표별 비고 수정
	 * @param CusRtnRegVo param
	 * @return Int
	 */
	public int updateSalesRemarks(CusRtnRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesRemarks
	 * @Description : 매출처 전표별 비고 입력
	 * @param CusRtnRegVo param
	 * @return Int
	 */	
	public int insertSalesRemarks(CusRtnRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectRtnPrintList
	 * @Description : 매출처반품등록 거래명세표 출력
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	public List<CusRtnRegVo> selectRtnPrintList(CusRtnRegVo paramVo);


}
