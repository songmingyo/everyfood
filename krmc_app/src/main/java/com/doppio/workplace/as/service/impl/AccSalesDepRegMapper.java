package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;
import com.doppio.workplace.as.service.AccSalesDepRegVo;
import com.doppio.workplace.bm.service.EmpMasterVo;


/**
 *
 * @Class : accSalesDepRegMapper.java
 * @Description : 매출처입금관리 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 20.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "accSalesDepRegMapper")
public interface AccSalesDepRegMapper {
	
	
	/**
	 * @Method : selectAccSalesDepRegCount
	 * @Description : 매출처별입금현황 PAGE COUNT 조회 
	 * @param AccSalesDepRegVo param
	 * @return INT
	 */
	public int selectAccSalesDepRegCount(AccSalesDepRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectAccSalesDepReg
	 * @Description : 매출처별입금현황 조회 
	 * @param AccSalesDepRegVo param
	 * @return List<AccSalesDepRegVo>
	 */
	public List<AccSalesDepRegVo> selectAccSalesDepReg(AccSalesDepRegVo paramVo);
	
	
	/**
	 * @Method : selectAccSalesDepRegDetail
	 * @Description : 매출처별입금현황  상세  조회 
	 * @param AccSalesDepRegVo param
	 * @return List<AccSalesDepRegVo>
	 */
	public List<AccSalesDepRegVo> selectAccSalesDepRegDetail(AccSalesDepRegVo paramVo);

	
	/**
	 * @Method : inserEmpManagementInfo
	 * @Description : 매출처별입금현황 저장
	 * @param EmpMasterVo param
	 * @return INT
	 */
	public int insertAccSalesDepReg(AccSalesDepRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateEmpMangement
	 * @Description : 영업사원정보 수정 
	 * @param EmpMasterVo param
	 * @return Int
	 */
	public int updateAccSalesDepReg(AccSalesDepRegVo paramVo);
}
