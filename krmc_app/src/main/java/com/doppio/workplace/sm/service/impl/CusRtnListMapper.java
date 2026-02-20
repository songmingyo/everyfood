package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusRtnListVo;


/**
 *
 * @Class : CusRtnListMapper.java
 * @Description : 매출처반품현황  현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 6.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "cusRtnListMapper")
public interface CusRtnListMapper {
	
	
	/**
	 * @Method : selectCusRtnListCount
	 * @Description : 매출처반품현황  PAGE COUNT 조회 
	 * @param CusRtnListVo param
	 * @return INT
	 */
	public int selectCusRtnListCount(CusRtnListVo paramVo);
	
	/**
	 * @Method : selectCusRtnList
	 * @Description : 매출처반품현황 조회 
	 * @param CusRtnListVo param
	 * @return List<CusRtnListVo>
	 */
	public List<CusRtnListVo> selectCusRtnList(CusRtnListVo paramVo);
	
	/**
	 * @Method : selectCusRtnListExcelDown
	 * @Description :  매출처반품현황 엑셀 다운로드 
	 * @param CusRtnListVo param
	 * @return List<CusRtnListVo>
	 */                     
	public List<HashMap<String, String>> selectCusRtnListExcelDown(CusRtnListVo paramVo) throws SQLException;

}
