package com.doppio.workplace.as.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.as.service.AccSalesDepListVo;
import com.doppio.workplace.as.service.AnlAccRecvListVo;


/**
 *
 * @Class : accSalesDepListMapper.java
 * @Description :외상매입금 현황
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

@Repository(value = "anlAccRecvListMapper")
public interface AnlAccRecvListMapper {
	
	
	/**
	 * @Method : selectAnlAccRecvList
	 * @Description : 외상매입금 조회 
	 * @param AnlAccRecvListVo param
	 * @return List<AnlAccRecvListVo>
	 */
	public List<AnlAccRecvListVo> selectAnlAccRecvList(AnlAccRecvListVo paramVo);
	
	/**
	 * @Method : selectAccRecvPrintList
	 * @Description :  외상매입금 출력 다운로드 
	 * @param AnlAccRecvListVo param
	 * @return List<AnlAccRecvListVo>
	 */                     
	public List<AnlAccRecvListVo> selectAccRecvPrintList(AnlAccRecvListVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectAnlAccRecvListExcelDown
	 * @Description :  외상매입금 엑셀 다운로드 
	 * @param AnlAccRecvListVo param
	 * @return List<AnlAccRecvListVo>
	 */                     
	public List<HashMap<String, String>> selectAnlAccRecvListExcelDown(AnlAccRecvListVo paramVo) throws SQLException;
	

}
