package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusSampleRegVo;

/**
*
* @Class : CusOrdRegMapper.java
* @Description : 매출처 발주 
* @author : dada
* <pre>
*  << 개정이력(Modification Information) >>
*
*          수정일          수정자           수정내용
*  ----------------    ------------    ---------------------------
*   2023. 5. 14.      DADA        	  
*
* </pre>
*/

@Repository(value = "cusOrdRegMapper")
public interface CusOrdRegMapper {

	
	/**
	 * @Method : selectCusOrdProdList
	 * @Description : 매출처발주 대상 품목  조회 
	 * @param ClsDayStkRegVo param
	 * @return List<CusOrdRegVo>
	 */
	public List<CusOrdRegVo> selectCusOrdProdList(CusOrdRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectCusOrdProdData
	 * @Description : 매출처발주 대상 품목  조회 
	 * @param ClsDayStkRegVo param
	 * @return CusOrdRegVo
	 */
	public CusOrdRegVo selectCusOrdProdData(CusOrdRegVo paramVo) throws SQLException;
	
	
	/** 
	 * @Method : insertSalesSlipNo
	 * 전포번호 생성 / 조회 
	 * (25개이상인 경우 신규 전표번호 생성 하고 Insert/전표번호 반환,  25이하인경우 전표번호 INSERT 무시, 조회된 전표번호 반환  )  
	  [전표번호 생성 Sequence 및 규칙 적용 필요 ]		
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertSalesSlipNo(CusOrdRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertCusOrdProdList
	 * @Description : 매출처발주 대상 정보 INSERT 
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int insertCusOrdProdList(CusOrdRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateCusOrdProdList
	 * @Description : 매출처발주 대상 정보 UPDATE 
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int updateCusOrdProdList(CusOrdRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : deleteCusOrdProdList
	 * @Description : 매출처발주 대상 정보 DELETE 
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int deleteCusOrdProdList(CusOrdRegVo paramVo) throws SQLException;
	
	
	
	/**
	 * @Method : updateSalesDlv
	 * @Description : 매출처 발주 관리 UPDTE
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int updateSalesDlv(CusOrdRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처 발주 관리 INSERT
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int insertSalesDlv(CusOrdRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlvEtc
	 * @Description : 매출처 판매 기타 정보 INSERT
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int insertSalesDlvEtc(CusOrdRegVo paramVo) throws SQLException;
	
	
	
	
	/**
	 * @Method : selectSalesMasterList
	 * @Description : 매출처 발주 (TSH_SALES_ORDER)
	 * @param ClsDayStkRegVo paramVo
	 * @return INT
	 */
	public int selectCusOrdSlipListCount(CusOrdRegVo paramVo);

	/**
	 * @Method : selectSalesMasterList
	 * @Description : 매출처 발주 조회(TSH_SALES_ORDER)
	 * @param ClsDayStkRegVo paramVo
	 * @return List<CusOrdRegVo>
	 */
	public List<CusOrdRegVo> selectCusOrdSlipList(CusOrdRegVo paramVo);
	
	
	/**
	 * @Method : selectOrdProdList
	 * @Description : 매출처 발주 상세 (TSH_SALES_ORDER)
	 * @param ClsDayStkRegVo paramVo
	 * @return List<CusOrdRegVo>
	 */
	public List<CusOrdRegVo> selectOrdProdList(CusOrdRegVo paramVo);
	
	/**
	 * @Method : selectOrdProdList
	 * @Description : 매출처 발주 상세 (TSH_SALES_ORDER)
	 * @param ClsDayStkRegVo paramVo
	 * @return List<CusOrdRegVo>
	 */
	public List<CusOrdRegVo> selectOrdList(CusOrdRegVo paramVo);
	
	
	/**
	 * @Method : selectCusOrdRemarksListCount
	 * @Description : 매출처 발주 전표별 비고 Count
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int selectCusOrdRemarksListCount(CusOrdRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateCusOrdRemarks
	 * @Description : 매출처 발주 전표별 비고 수정
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int updateCusOrdRemarks(CusOrdRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertCusOrdRemarks
	 * @Description : 매출처 발주 전표별 비고 입력
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */	
	public int insertCusOrdRemarks(CusOrdRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectCusOrdRegPrdtAdd
	 * @Description : 매출처발주등록 하나의 품목만 조회
	 * @param ClsDayStkRegVo param
	 * @return 
	 */
	public CusOrdRegVo selectCusOrdRegPrdtAdd(CusOrdRegVo paramVo);
	
	
	/**
	 * @Method : selectOrdProdList
	 * @Description : 매출처 발주 상세 (TSH_SALES_ORDER)
	 * @param ClsDayStkRegVo paramVo
	 * @return List<CusOrdRegVo>
	 */
	public List<CusOrdRegVo> selectOrdPrdtList(CusOrdRegVo paramVo);
	
	
	/**
	 * @Method : insertCusOrdList
	 * @Description : 매출처발주 대상 정보 UPDATE (PC) 
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int insertCusOrdList(CusOrdRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateCusOrdList
	 * @Description : 매출처발주 대상 정보 UPDATE ( PC ) 
	 * @param ClsDayStkRegVo param
	 * @return Int
	 */
	public int updateCusOrdList(CusOrdRegVo paramVo) throws SQLException;
	
}
