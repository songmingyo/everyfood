package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;

/**
 *
 * @Class : AccSalesDepRegService.java
 * @Description : 매출처별입금현황 
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

public interface AccSalesDepRegService {
	
	
	

	/**
	 * 매출처별입금현황 조회
	 * @param AccSalesDepRegVo	paramVo
	 * @return List<AccSalesDepRegVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAccSalesDepReg(AccSalesDepRegVo paramVo) throws Exception;
	

	
	/**
	 * @Method : selectAccSalesDepRegDetail
	 * @Description : 매출처별입금현황 상품 상세  조회 
	 * @param AccSalesDepRegVo param
	 * @return List<AccSalesDepRegVo>
	 */
	public List<AccSalesDepRegVo> selectAccSalesDepRegDetail(AccSalesDepRegVo paramVo);
	

	/**
	 * 매출처별입금현황 저장
	 * @param AccSalesDepRegVo	paramVo
	 * @return List<AccSalesDepRegVo>
	 * @throws Exception
	 */
	public Result insertAccSalesDepRegInfo(AccSalesDepRegVo paramVo) throws Exception;


}
