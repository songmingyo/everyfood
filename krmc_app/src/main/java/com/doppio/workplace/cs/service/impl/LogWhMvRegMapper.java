package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.LogWhMvRegVo;


/**
 *
 * @Class : LogWhMvRegMapper.java
 * @Description : 센터이동 등록 관리 
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

@Repository(value = "LogWhMvRegMapper")
public interface LogWhMvRegMapper {


	/**
	 * @Method : selectLogWhMvRegOrdList
	 * @Description : 센터이동 등록 조회 
	 * @param LogWhMvRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<LogWhMvRegVo> selectLogWhMvRegList(LogWhMvRegVo paramVo);
	
	/**
	 * @Method : selectLogWhMvRegPrdtAddList
	 * @Description : 센터이동 등록 하나의 품목 조회 
	 * @param LogWhMvRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public LogWhMvRegVo selectLogWhMvRegPrdtAddList(LogWhMvRegVo paramVo);
	
	
	/**
	 * @Method : insertLogWhMvRegData
	 * @Description : 센터이동 등록 입력
	 * @param LogWhMvRegVo param
	 * @return INT
	 */
	public int insertLogWhMvRegData(LogWhMvRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateLogWhMvRegData
	 * @Description : 센터이동 등록 수정
	 * @param LogWhMvRegVo param
	 * @return INT
	 */
	public int updateLogWhMvRegData(LogWhMvRegVo paramVo) throws SQLException;
	

	
}
