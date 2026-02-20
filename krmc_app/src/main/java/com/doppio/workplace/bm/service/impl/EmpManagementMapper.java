package com.doppio.workplace.bm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.doppio.workplace.bm.service.EmpMasterVo;


/**
 *
 * @Class : EmpManagementMapper.java
 * @Description : 사원정보관리
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 11.      j100000        	  
 *
 * </pre>
 */

@Repository(value = "empManagementMapper")
public interface EmpManagementMapper {


	/**
	 * @Method : selectEmpManagementListCount
	 * @Description : 사원정보관리 PAGE COUNT 조회 
	 * @param DlvrMasterVo param
	 * @return INT
	 */
	public int selectEmpManagementListCount(EmpMasterVo paramVo);
	
	/**
	 * @Method : selectEmpManagementList
	 * @Description : 영업사원정보 조회 
	 * @param EmpMasterVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<EmpMasterVo> selectEmpManagementList(EmpMasterVo paramVo);
	
	
	/**
	 * @Method : selectEmpManagementData
	 * @Description : 영업사원정보 상세조회 
	 * @param EmpMasterVo param
	 * @return EmpMasterVo
	 */
	public EmpMasterVo selectEmpManagementData(EmpMasterVo paramVo);
	/**
	 * @Method : inserEmpManagementInfo
	 * @Description : 영업사원정보 저장
	 * @param EmpMasterVo param
	 * @return INT
	 */
	public int insertEmpManagementInfo(EmpMasterVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectEmpMangementSalesPrCd
	 * @Description : 영업사원정보 PAGE COUNT 조회 
	 * @param EmpMasterVo param
	 * @return string
	 */
	public String selectEmpMangementSalesPrCd() throws SQLException;

	
	/**
	 * @Method : updateEmpMangement
	 * @Description : 영업사원정보 수정 
	 * @param EmpMasterVo param
	 * @return Int
	 */
	public int updateEmpMangement(EmpMasterVo paramVo);
	
	/**
	 * @Method : deleteEmpManagement
	 * @Description : 영업사원정보 삭제 
	 * @param EmpMasterVo param
	 * @return Int
	 */
	public int deleteEmpManagement(EmpMasterVo paramVo);
}
