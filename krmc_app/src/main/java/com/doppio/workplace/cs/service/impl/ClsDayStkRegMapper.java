package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.cs.service.ClsDayStkRegVo;


/**
 *
 * @Class : ClsDayStkRegMapper.java
 * @Description : 일재고 관리 
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

@Repository(value = "ClsDayStkRegMapper")
public interface ClsDayStkRegMapper {


	/**
	 * @Method : selectClsDayStkRegOrdList
	 * @Description : 일재고 조회 
	 * @param ClsDayStkRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<ClsDayStkRegVo> selectClsDayStkRegList(ClsDayStkRegVo paramVo);
	
	/**
	 * @Method : selectDayStkRegFooter
	 * @Description : 일재고 footer
	 * @param ClsDayStkRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public ClsDayStkRegVo selectDayStkRegFooter(ClsDayStkRegVo paramVo);
	
	
	
	/**
	 * @Method : insertClsDayStkRegData
	 * @Description : 일재고 입력
	 * @param ClsDayStkRegVo param
	 * @return INT
	 */
	public int insertClsDayStkRegData(ClsDayStkRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateClsDayStkRegData
	 * @Description : 일재고 수정
	 * @param ClsDayStkRegVo param
	 * @return INT
	 */
	public int updateClsDayStkRegData(ClsDayStkRegVo paramVo) throws SQLException;
	

	
	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 일재고 엑셀 다운로드
	 * @param ClsDayStkRegVo param
	 * @return List<ClsDayStkRegVo>
	 */                     
	public List<HashMap<String, String>> selectClsDayStkRegListExcel(ClsDayStkRegVo paramVo) throws SQLException;
	

	/**
	 * @Method : selectStkInspPrintList
	 * @Description : 일재고 재고조사표 출력
	 * @param ClsDayStkRegVo param
	 * @return List<ClsDayStkRegVo>
	 */
	public List<ClsDayStkRegVo> selectStkInspPrintList(ClsDayStkRegVo paramVo);
	
	
}
