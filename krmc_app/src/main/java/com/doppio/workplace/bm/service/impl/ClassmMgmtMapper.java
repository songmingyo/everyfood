package com.doppio.workplace.bm.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.doppio.workplace.bm.service.ClassmMgmtVo;


/**
 *
 * @Class : ClassmMgmtMapper.java
 * @Description : 중분류코드 관리
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

@Repository(value = "classmMgmtMapper")
public interface ClassmMgmtMapper {


	/**
	 * @Method : selectClassmMgmtListCount
	 * @Description : 중분류코드 PAGE COUNT 조회 
	 * @param ClassmMgmtVo param
	 * @return INT
	 */
	public int selectClassmMgmtListCount(ClassmMgmtVo paramVo);
	
	/**
	 * @Method : selectClassmMgmtList
	 * @Description : 중분류코드 조회 
	 * @param ClassmMgmtVo param
	 * @return List<ClassmMgmtVo>
	 */
	public List<ClassmMgmtVo> selectClassmMgmtList(ClassmMgmtVo paramVo);
	
	
	/**
	 * @Method : selectClassmMgmtData
	 * @Description : 중분류코드 상세조회 
	 * @param ClassmMgmtVo param
	 * @return ClassmMgmtVo
	 */
	public ClassmMgmtVo selectClassmMgmtData(ClassmMgmtVo paramVo);
	/**
	 * @Method : insertClassmMgmt
	 * @Description : 중분류코드 저장
	 * @param ClassmMgmtVo param
	 * @return INT
	 */
	public int insertClassmMgmt(ClassmMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectClassmMgmtSalesPrCd
	 * @Description : 중분류코드 PAGE COUNT 조회 
	 * @param ClasslMgmtVo param
	 * @return string
	 */
	public String selectClassmMgmtSalesPrCd() throws SQLException;

	
	/**
	 * @Method : updateClassmMgmt
	 * @Description : 중분류코드 수정 
	 * @param ClassmMgmtVo param
	 * @return Int
	 */
	public int updateClassmMgmt(ClassmMgmtVo paramVo);
	
	/**
	 * @Method : deleteClassmMgmt
	 * @Description : 중분류코드 삭제 
	 * @param ClassmMgmtVo param
	 * @return Int
	 */
	public int deleteClassmMgmt(ClassmMgmtVo paramVo);
}
