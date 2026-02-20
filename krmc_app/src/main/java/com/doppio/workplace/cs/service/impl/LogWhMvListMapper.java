package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.LogWhMvListVo;


/**
 *
 * @Class : LogWhMvListMapper.java
 * @Description : 센터이동 조회
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

@Repository(value = "LogWhMvListMapper")
public interface LogWhMvListMapper {


	/**
	 * @Method : selectLogWhMvList
	 * @Description : 센터이동 조회 
	 * @param LogWhMvListVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<LogWhMvListVo> selectLogWhMvList(LogWhMvListVo paramVo);
	

	
	/**
	 * @Method : selectLogWhMvListExcel
	 * @Description : 센터이동 엑셀 다운로드
	 * @param LogWhMvListVo param
	 * @return List<LogWhMvListVo>
	 */                     
	public List<HashMap<String, String>> selectLogWhMvListExcel(LogWhMvListVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectLogWhMvListPrint
	 * @Description : 센터이동 출력
	 * @param LogWhMvListVo param
	 * @return List<LogWhMvListVo>
	 */                     
	public List<LogWhMvListVo> selectLogWhMvListPrint(LogWhMvListVo paramVo) throws SQLException;
	
}
