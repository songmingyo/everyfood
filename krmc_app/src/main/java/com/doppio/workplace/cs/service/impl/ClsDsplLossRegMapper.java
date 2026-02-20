package com.doppio.workplace.cs.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.ClsDsplLossRegVo;

/**
 *
 * @Class : ClsDsplLossRegMapper.java
 * @Description : 폐기/로스등록 관리 
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

@Repository(value = "clsDsplLossRegMapper")
public interface ClsDsplLossRegMapper {
	
	
	/**
	 * @Method : clsDsplLossRegPrdtList
	 * @Description : 폐기/로스등록 대상자재 조회 
	 * @param ClsDsplLossRegVo param
	 * @return List<ClsDsplLossRegVo>
	 */
	public List<ClsDsplLossRegVo> clsDsplLossRegPrdtList(ClsDsplLossRegVo paramVo);
	
	/**
	 * @Method : selectClsDsplLossRegDetailList
	 * @Description : 폐기/로스등록 상세 내역 조회 
	 * @param ClsDsplLossRegVo param
	 * @return List<ClsDsplLossRegVo>
	 */
	public List<ClsDsplLossRegVo> selectClsDsplLossRegDetailList(ClsDsplLossRegVo paramVo);
	
	/**
	 * @Method : selectClsDsplLossRegPrdtAdd
	 * @Description : 폐기/로스등록 하나의 품목만 조회
	 * @param SalSalesGoalRegVo param
	 * @return 
	 */
	public ClsDsplLossRegVo selectClsDsplLossRegPrdtAdd(ClsDsplLossRegVo paramVo);
	
	/**
	 * @Method : selectClsDsplLossRegSlipNo
	 * @Description : 폐기/로스등록 전표번호 확인
	 * @param ClsDsplLossRegVo param
	 * @return String
	 */
	public String selectClsDsplLossRegSlipNo(ClsDsplLossRegVo paramVo) throws SQLException;
	
	/** 
	 * @Method : insertDsplLossSlipNo
	 * 전포번호 생성 / 조회
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertDsplLossSlipNo(ClsDsplLossRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertDsplLossDlv
	 * @Description : 폐기/로스등록 관리 MERGE(INSERT )
	 * @param ClsDsplLossRegVo param
	 * @return Int
	 */
	public int insertDsplLossDlv(ClsDsplLossRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateDsplLossDlv
	 * @Description : 폐기/로스등록 관리 MERGE(UPDATE)
	 * @param ClsDsplLossRegVo param
	 * @return Int
	 * @return List<CustSalesDlvVo>
	 */
	public int updateDsplLossDlv(ClsDsplLossRegVo paramVo) throws SQLException;
	


}
