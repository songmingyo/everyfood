package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.AccBuyWdrlRegVo;
import com.doppio.workplace.as.service.AccSalesDepRegVo;


/**
 *
 * @Class : accBuyWdrlRegMapper.java
 * @Description : 매입처지급관리 현황
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

@Repository(value = "accBuyWdrlRegMapper")
public interface AccBuyWdrlRegMapper {

	/**
	 * @Method : selectAccBuyWdrlReg
	 * @Description : 매입처지급관리 조회 
	 * @param AccBuyWdrlRegVo param
	 * @return List<AccBuyWdrlRegVo>
	 */
	public List<AccBuyWdrlRegVo> selectAccBuyWdrlReg(AccBuyWdrlRegVo paramVo);
	
	
	/**
	 * @Method : selectAccBuyWdrlRegDetail
	 * @Description : 매입처지급관리  상세  조회 
	 * @param AccBuyWdrlRegVo param
	 * @return List<AccBuyWdrlRegVo>
	 */
	public List<AccBuyWdrlRegVo> selectAccBuyWdrlRegDetail(AccBuyWdrlRegVo paramVo);

	
	/**
	 * @Method : selectAccSalesDepRegCount
	 * @Description : 매입처지급관리 PAGE COUNT 조회 
	 * @param AccSalesDepRegVo param
	 * @return INT
	 */
	public int selectAccBuyWdrlRegCount(AccBuyWdrlRegVo paramVo);
	
	/**
	 * @Method : inserEmpManagementInfo
	 * @Description : 매입처지급관리 저장
	 * @param EmpMasterVo param
	 * @return INT
	 */
	public int insertAccBuyWdrlReg(AccBuyWdrlRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateEmpMangement
	 * @Description : 매입처지급관리 수정 
	 * @param EmpMasterVo param
	 * @return Int
	 */
	public int updateAccBuyWdrlReg(AccBuyWdrlRegVo paramVo);
}
