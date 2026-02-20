package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;

/**
 *
 * @Class : AccBuyWdrlRegService.java
 * @Description : 매입처별지급현황 
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

public interface AccBuyWdrlRegService {
	

	/**
	 * 매입처별지급현황 조회
	 * @param AccBuyWdrlRegVo	paramVo
	 * @return List<AccBuyWdrlRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAccBuyWdrlReg(AccBuyWdrlRegVo paramVo) throws Exception;
	

	
	/**
	 * @Method : selectAccBuyWdrlRegDetail
	 * @Description : 매입처별지급현황 상품 상세  조회 
	 * @param AccBuyWdrlRegVo param
	 * @return List<AccBuyWdrlRegVo>
	 */
	public List<AccBuyWdrlRegVo> selectAccBuyWdrlRegDetail(AccBuyWdrlRegVo paramVo);
	

	/**
	 * 매입처별지급현황 저장
	 * @param AccBuyWdrlRegVo	paramVo
	 * @return List<AccBuyWdrlRegVo>
	 * @throws Exception
	 */
	public Result insertAccBuyWdrlRegInfo(AccBuyWdrlRegVo paramVo) throws Exception;


}
