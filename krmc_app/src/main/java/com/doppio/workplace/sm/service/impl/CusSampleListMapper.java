package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.sm.service.CusSampleListVo;


/**
 *
 * @Class : CusShipRegMapper.java
 * @Description : 샘플출고현황
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023.05.05      j10000        	  
 *
 * </pre>
 */

@Repository(value = "cusSampleListMapper")
public interface CusSampleListMapper {
	
	
	/**
	 * @Method : selectCusSampleList
	 * @Description : 샘플출고현황 조회 
	 * @param CusSampleListVo param
	 * @return List<CusSampleListVo>
	 */
	public List<CusSampleListVo> selectCusSampleList(CusSampleListVo paramVo);
	
	/**
	 * @Method : selectCusSampleListExcelDown
	 * @Description :  샘플출고현황 엑셀 다운로드 
	 * @param CusSampleListVo param
	 * @return List<CusSampleListVo>
	 */                     
	public List<HashMap<String, String>> selectCusSampleListExcelDown(CusSampleListVo paramVo) throws SQLException;

}
