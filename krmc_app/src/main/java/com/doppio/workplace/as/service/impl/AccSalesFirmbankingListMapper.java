package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.AccSalesDepRegVo;
import com.doppio.workplace.as.service.AccSalesFirmbankingListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description : 펌뱅킹입금현황 현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 14.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "accSalesFirmbankingListMapper")
public interface AccSalesFirmbankingListMapper {
	
	
	/**
	 * @Method : selectaccSalesFirmbankingList
	 * @Description : 펌뱅킹입금현황 조회 
	 * @param AccSalesFirmbankingListVo param
	 * @return List<AccSalesDepListVo>
	 */
	public List<AccSalesFirmbankingListVo> selectaccSalesFirmbankingList(AccSalesFirmbankingListVo paramVo);
	
	/**
	 * @Method : inserEmpManagementInfo
	 * @Description : 펌뱅킹입금현황 저장
	 * @param EmpMasterVo param
	 * @return INT
	 */
	public int insertAccSalesFirmbankingRegInfo(AccSalesFirmbankingListVo paramVo) throws SQLException;
	
	/**
	 * @Method : inserEmpManagementInfo
	 * @Description : 펌뱅킹입금현황 Flag update
	 * @param EmpMasterVo param
	 * @return INT
	 */
	public int updateAccSalesFirmbankingFlagInfo(AccSalesFirmbankingListVo paramVo) throws SQLException;

}
