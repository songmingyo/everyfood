package com.doppio.workplace.bm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.doppio.workplace.bm.service.ClasslMgmtVo;


/**
 *
 * @Class : ClasslMgmtMapper.java
 * @Description : 대분류코드 관리
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 21.      j100000        	  
 *
 * </pre>
 */

@Repository(value = "classlMgmtMapper")
public interface ClasslMgmtMapper {


	/**
	 * @Method : selectClasslMgmtListCount
	 * @Description : 대분류코드 PAGE COUNT 조회 
	 * @param ClasslMgmtVo param
	 * @return INT
	 */
	public int selectClasslMgmtListCount(ClasslMgmtVo paramVo);
	
	/**
	 * @Method : selectClasslMgmtList
	 * @Description : 대분류코드 조회 
	 * @param ClasslMgmtVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<ClasslMgmtVo> selectClasslMgmtList(ClasslMgmtVo paramVo);
	
	
	/**
	 * @Method : selectClasslMgmtData
	 * @Description : 대분류코드 상세조회 
	 * @param ClasslMgmtVo param
	 * @return ClasslMgmtVo
	 */
	public ClasslMgmtVo selectClasslMgmtData(ClasslMgmtVo paramVo);
	/**
	 * @Method : insertClasslMgmt
	 * @Description : 대분류코드 저장
	 * @param ClasslMgmtVo param
	 * @return INT
	 */
	public int insertClasslMgmt(ClasslMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectClasslMgmtSalesPrCd
	 * @Description : 대분류코드 PAGE COUNT 조회 
	 * @param ClasslMgmtVo param
	 * @return string
	 */
	public String selectClasslMgmtSalesPrCd() throws SQLException;

	
	/**
	 * @Method : updateEmpMangement
	 * @Description : 대분류코드 수정 
	 * @param ClasslMgmtVo param
	 * @return Int
	 */
	public int updateClasslMgmt(ClasslMgmtVo paramVo);
	
	/**
	 * @Method : deleteClasslMgmt
	 * @Description : 대분류코드 삭제 
	 * @param ClasslMgmtVo param
	 * @return Int
	 */
	public int deleteClasslMgmt(ClasslMgmtVo paramVo);
}
