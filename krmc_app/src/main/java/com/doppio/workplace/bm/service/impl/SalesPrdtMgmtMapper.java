package com.doppio.workplace.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;


/**
 *
 * @Class : SalesPrdtMgmtMapper.java
 * @Description : 매출처 상품마스터 관리 
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      song        	  
 *
 * </pre>
 */

@Repository(value = "SalesPrdtMgmtMapper")
public interface SalesPrdtMgmtMapper {

	
	/**
	 * @Method : selectSalesPrdtMgmtList
	 * @Description : 매출처 상품관리 조회 
	 * @param SalesPrdtMgmtVo param
	 * @return List<SalesPrdtMgmtVo>
	 */
	public List<SalesPrdtMgmtVo> selectSalesPrdtMgmtList(SalesPrdtMgmtVo paramVo);
	
	
	/**
	 * @Method : insertSalesPrdtMasterData
	 * @Description : 매출처 상품 입력
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int insertSalesPrdtMasterData(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesPrdtMasterData
	 * @Description : 매출처 상품 수정
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int updateSalesPrdtMasterData(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesPrdtMasterDataUseFlag
	 * @Description : 매출처 상품 삭제 플래그
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int updateSalesPrdtMasterDataUseFlag(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesPrdtMaterPrdtOldNewChg
	 * @Description : 매출처 상품 코드 일괄 변경
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int updateSalesPrdtMaterPrdtOldNewChg(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesPrdtMaterSalesCdOrgNewInsert
	 * @Description : 품목 코드 일괄 수정
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int updateSalesPrdtMaterSalesCdOrgNewInsert(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectSalesPrdtMgmtAddList
	 * @Description : 품목일괄적용 대상 조회 
	 * @param SalesPrdtMgmtVo param
	 * @return List<SalesPrdtMgmtVo>
	 */
	public SalesPrdtMgmtVo selectSalesPrdtMgmtAddList(SalesPrdtMgmtVo paramVo);
	
	/**
	 * @Method : updateSalesPrdtMaterSalesPrdtAllInsert
	 * @Description : 품목일괄적용
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int updateSalesPrdtMaterSalesPrdtAllInsert(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesPrdtMaterSalesPriceUpt
	 * @Description : 품목일괄적용
	 * @param SalesPrdtMgmtVo param
	 * @return INT
	 */
	public int updateSalesPrdtMaterSalesPriceUpt(SalesPrdtMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectSalesPrdtListExcelDown
	 * @Description :  매출품목 엑셀 다운로드 
	 * @param CusPriceUnconfVo param
	 * @return List<CusPriceUnconfVo>
	 */                     
	public List<HashMap<String, String>> selectSalesPrdtListExcelDown(SalesPrdtMgmtVo paramVo) throws SQLException;
	
}
