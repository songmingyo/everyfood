package com.doppio.workplace.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.sample.service.SampleExcelVo;


/**
 *
 * @Class : PrdtMgmtMapper.java
 * @Description : 상품마스터 관리 
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      DADA        	  
 *
 * </pre>
 */

@Repository(value = "PrdtMgmtMapper")
public interface PrdtMgmtMapper {


	/**
	 * @Method : selectPrdtMgmtListCount
	 * @Description : 상품관리 PAGE COUNT 조회 
	 * @param PrdtMgmtVo param
	 * @return INT
	 */
	public int selectPrdtMgmtListCount(PrdtMgmtVo paramVo);
	
	/**
	 * @Method : selectPrdtMgmtList
	 * @Description : 상품관리 조회 
	 * @param PrdtMgmtVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<PrdtMgmtVo> selectPrdtMgmtList(PrdtMgmtVo paramVo);
	
	/**
	 * @Method : selectPrdtMgmtData
	 * @Description : 상품 상세조회 
	 * @param DlvrMasterVo param
	 * @return DlvrMasterVo
	 */
	public PrdtMgmtVo selectPrdtMgmtData(PrdtMgmtVo paramVo);
	
	
	/**
	 * @Method : selectPrdtBuyData
	 * @Description : 상품 최근입고 조회 
	 * @param PrdtMgmtVo param
	 * @return PrdtMgmtVo
	 */
	public List<PrdtMgmtVo> selectPrdtBuyList(PrdtMgmtVo paramVo);
	
	/**
	 * @Method : selectPrdtSalesData
	 * @Description : 상품 최근출고 조회 
	 * @param PrdtMgmtVo param
	 * @return PrdtMgmtVo
	 */
	public List<PrdtMgmtVo> selectPrdtSalesList(PrdtMgmtVo paramVo);
	
	/**
	 * @Method : insertPrdtMgmt
	 * @Description : 상품 생성
	 * @param PrdtMgmtVo param
	 * @return INT
	 */
	public int insertPrdtMgmt(PrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updatePrdtMgmtInfo
	 * @Description : 상품 수정
	 * @param DlvrMasterVo param
	 * @return INT
	 */
	public int updatePrdtMgmt(PrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectPrdtMgmtList
	 * @Description : 상품관리 엑셀 다운로드
	 * @param PrdtMgmtVo param
	 * @return List<DlvrMasterVo>
	 */                     
	public List<HashMap<String, String>> selectPrdtMgmtListExcelDown(PrdtMgmtVo paramVo) throws SQLException;
}
