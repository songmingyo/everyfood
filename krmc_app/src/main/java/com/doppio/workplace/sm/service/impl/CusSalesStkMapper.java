package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusSalesStkVo;

/**
*
* @Class : CusSalesStkMapper.java
* @Description : 매출처 재고  현황
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
@Repository(value = "cusSalesStkMapper")
public interface CusSalesStkMapper {
	
	
	/**
	 * @Method : selectSalesStkListCount
	 * @Description : 매출처 재고  (TSH_SALES_ORDER)
	 * @param CusSalesStkVo paramVo
	 * @return INT
	 */
	public int selectSalesStkListCount(CusSalesStkVo paramVo);


	/**
	 * @Method : selectSalesStkList
	 * @Description : 매입처 재고  조회 
	 * @param CusSalesStkVo param
	 * @return List<CusSalesStkVo>
	 */
	public List<CusSalesStkVo> selectSalesStkList(CusSalesStkVo paramVo) throws SQLException;
	

	/**
	 * @Method : selectSalesStkList
	 * @Description : 상품정보  조회 
	 * @param CusSalesStkVo param
	 * @return CusSalesStkVo
	 */
	public CusSalesStkVo selectPrdtSearch(CusSalesStkVo paramVo) throws SQLException;
	
	
	
}
