package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.as.service.SalSalesTotPrftListVo;


/**
 *
 * @Class : SalSalesTotPrftListMapper.java
 * @Description : 통합매출처이익률(전월대비)
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "SalSalesTotPrftListMapper")
public interface SalSalesTotPrftListMapper {

	
	/**
	 * @Method : selectSalSalesTotPrftList
	 * @Description : 통합매출처이익률(부가세별도) 조회 
	 * @param SalSalesTotPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<SalSalesTotPrftListVo> selectSalSalesTotPrftList(SalSalesTotPrftListVo paramVo);
	
	/**
	 * @Method : selectSalSalesTotPrftList
	 * @Description : 통합매출처이익률(부가세별도) footer
	 * @param SalSalesTotPrftListVo param
	 * @return List<BuyMgmtVo>
	 */
	public SalSalesTotPrftListVo selectSalesTotPrftFooter(SalSalesTotPrftListVo paramVo);
	
	
	/**
	 * @Method : selectSalSalesTotPrftListListExcelDown
	 * @Description : 통합매출처이익률(부가세별도) 출력
	 * @param SalSalesTotPrftListVo param
	 * @return List<SalSalesTotPrftListVo>
	 */                     
	public List<SalSalesTotPrftListVo> selectSalesTotPrftPrintList(SalSalesTotPrftListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectSalSalesTotPrftListListExcelDown
	 * @Description : 통합매출처이익률(부가세별도) 엑셀 다운로드
	 * @param SalSalesTotPrftListVo param
	 * @return List<SalSalesTotPrftListVo>
	 */                     
	public List<HashMap<String, String>> selectSalSalesTotPrftListExcelDown(SalSalesTotPrftListVo paramVo) throws SQLException;
	
}
