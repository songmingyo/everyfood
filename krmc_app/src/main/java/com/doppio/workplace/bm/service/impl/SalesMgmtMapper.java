package com.doppio.workplace.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.bm.service.SalesMgmtVo;


/**
 *
 * @Class : SalesMgmtMapper.java
 * @Description : 매출처마스터 관리 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 17.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "SalesMgmtMapper")
public interface SalesMgmtMapper {


	/**
	 * @Method : selectSalesMgmtListCount
	 * @Description : 매출처관리 PAGE COUNT 조회 
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int selectSalesMgmtListCount(SalesMgmtVo paramVo);
	
	/**
	 * @Method : selectSalesMgmtList
	 * @Description : 매출처관리 조회 
	 * @param SalesMgmtVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<SalesMgmtVo> selectSalesMgmtList(SalesMgmtVo paramVo);
	
	/**
	 * @Method : selectSalesMgmtList
	 * @Description : 매출처 상세조회 
	 * @param SalesMgmtVo param
	 * @return SalesMgmtVo
	 */
	public SalesMgmtVo selectSalesMgmtData(SalesMgmtVo paramVo);
	
	
	/**
	 * @Method : insertSalesMgmt
	 * @Description : 매출처 마스터 생성
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int insertSalesMgmt(SalesMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesMgmt
	 * @Description : 매출처 마스터 추가정보 생성
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int insertSalesAddInfo(SalesMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesMgmt
	 * @Description : 매출처 본사유무 생성
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int insertSalesHqClass(SalesMgmtVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateSalesMgmt
	 * @Description : 매출처 마스터 수정
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int updateSalesMgmt(SalesMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesMgmt
	 * @Description : 매출처 마스터 추가정보 수정
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int updateSalesAddInfo(SalesMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesMgmt
	 * @Description : 매출처 마스터 본사유무 수정
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int updateSalesHqClass(SalesMgmtVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : insertSalesMember
	 * @Description : 매출처 로그인 정보 생성
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int insertSalesMember(SalesMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesMember
	 * @Description : 매출처 로그인 권한 생성
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int insertSalesMemberAuth(SalesMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesMember
	 * @Description : 매출처 마스터 로그인 정보
	 * @param SalesMgmtVo param
	 * @return INT
	 */
	public int updateSalesMember(SalesMgmtVo paramVo) throws SQLException;
	

	/**
	 * @Method : selectSalesMgmtListExcelDown
	 * @Description : 매출처 마스터 엑셀 다운로드
	 * @param SalesMgmtVo param
	 * @return List<SalesMgmtVo>
	 */                     
	public List<HashMap<String, String>> selectSalesMgmtListExcelDown(SalesMgmtVo paramVo) throws SQLException;
	
}
