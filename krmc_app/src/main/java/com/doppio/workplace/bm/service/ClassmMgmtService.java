package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;


/**
 * @author j10000
 * @Description :중분류코드관리 Service interface
 * @Class : ClassmMgmtService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.03.22 j10000
 * </pre>
 * @version : 1.0
 */

public interface ClassmMgmtService {

	
	

	/**
	 * 중분류코드 관리
	 * @param ClassmMgmtVo	paramVo
	 * @return List<ClassmMgmtVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClassmMgmtList(ClassmMgmtVo paramVo) throws Exception;
	
	/**
	 * 대분류코드 정보 저장
	 * @param ClassmMgmtVo	paramVo
	 * @return List<ClassmMgmtVo>
	 * @throws Exception
	 */
	public Result insertClassmMgmt(HttpServletRequest request, ClassmMgmtVo paramVo) throws Exception;

	/**
	 * 중분류코드 상세조회 
	 * @param ClassmMgmtVo	paramVo
	 * @return ClassmMgmtVo
	 * @throws Exception
	 */
	ClassmMgmtVo selectClassmMgmtData(ClassmMgmtVo paramVo) throws Exception;

	
	/**
	 *  중분류코드 삭제 
	 *  DELETE
	 * @param ClassmMgmtVo paramVo
	 * @return ClassmMgmtVo
	 * @throws Exception
	 */
	Result deleteClassmMgmtData(ClassmMgmtVo paramVo) throws Exception ;

}
