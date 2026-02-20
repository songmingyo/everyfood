package com.doppio.workplace.sm.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;


/**
 *
 * @Class : CusRtnRegService.java
 * @Description : 매출처반품등록 관리 
 * @author : song 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.      song        	  
 *
 * </pre>
 */

public interface CusRtnRegService {

	/**
	 * @Method : selectCusRtnRegList
	 * @Description : 매출처반품등록 마스터 조회 
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	public List<CusRtnRegVo> selectCusRtnRegList(CusRtnRegVo paramVo);
	
	
	/**
	 * @Method : selectCusRtnRegDetailList
	 * @Description : 매출처반품등록 상품 상세  조회 
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	public List<CusRtnRegVo> selectCusRtnRegDetailList(CusRtnRegVo paramVo);
	
	/**
	 * 매출처반품등록 하나의 품목만 조회 
	 * @param CusRtnRegVo	paramVo
	 * @return 
	 * @throws Exception
	 */
	HashMap<String,Object>  selectCusRtnRegPrdtAdd(CusRtnRegVo paramVo) throws Exception;
	
	/**
	 * 매출처반품등록 정보 저장
	 * @param CusRtnRegVo	paramVo
	 * @return CusRtnRegVo
	 * @throws Exception
	 */
	Result insertCusRtnReg(CusRtnRegVo paramVo) throws Exception;

	/**
	 * @Method : selectRtnPrintList
	 * @Description : 매출처반품등록 거래명세표 출력 조회
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	public List<CusRtnRegVo> selectRtnPrintList(CusRtnRegVo paramVo);
	

}
