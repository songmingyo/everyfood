package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;


/**
 * @author j10000
 * @Description :사원정보관리 Service interface
 * @Class : EmpManagementService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface EmpManagementService {

	
	

	/**
	 * 영업사원 정보 관리
	 * @param EmpMasterVo	paramVo
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectEmpManagementList(EmpMasterVo paramVo) throws Exception;
	
	/**
	 * 영업사원 정보 저장
	 * @param EmpMasterVo	paramVo
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */
	public Result insertEmpManagementInfo(HttpServletRequest request, EmpMasterVo paramVo) throws Exception;

	/**
	 * 영업사원정보 상세조회 
	 * @param EmpMasterVo	paramVo
	 * @return EmpMasterVo
	 * @throws Exception
	 */
	EmpMasterVo selectEmpManagementData(EmpMasterVo paramVo) throws Exception;

	
	/**
	 *  영업사원정보 삭제 
	 *  DELETE
	 * @param EmpMasterVo paramVo
	 * @return EmpMasterVo
	 * @throws Exception
	 */
	Result deleteEmpManagementData(EmpMasterVo paramVo) throws Exception ;

}
