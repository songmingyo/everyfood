package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.br.service.BuyInspectListVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;


/**
 *
 * @Class : BuyInspectListMapper.java
 * @Description : 매입처검수확인
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 04. 04.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "BuyInspectListMapper")
public interface BuyInspectListMapper {

	
	/**
	 * @Method : selectBuyConfirmList
	 * @Description : 매입처검수확인 조회 
	 * @param BuyInspectListVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<BuyInspectListVo> selectBuyConfirmList(BuyInspectListVo paramVo);
	
	/**
	 * @Method : selectBuyConfirmPrt
	 * @Description : 매입처검수확인 출력 조회 
	 * @param BuyInspectListVo param
	 * @return List<BuyInspectListVo>
	 */
	public List<BuyInspectListVo> selectBuyConfirmPrt(BuyInspectListVo paramVo);
	
	
	/**
	 * @Method : selectBuyInspectListExcelDown
	 * @Description : 엑셀 다운로드 
	 * @param BuyInspectListVo param
	 * @return List<BuyInspectListVo>
	 */                     
	public List<HashMap<String, String>> selectBuyInspectListExcelDown(BuyInspectListVo paramVo) throws SQLException;
}
