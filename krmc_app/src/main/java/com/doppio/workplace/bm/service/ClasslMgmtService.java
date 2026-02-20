package com.doppio.workplace.bm.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.Result;


/**
 * @author j10000
 * @Description :대분류코드 관리 Service interface
 * @Class : ClasslMgmtService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2023.03.22 j10000
 * </pre>
 * @version : 1.0
 */

public interface ClasslMgmtService {

	
	

	/**
	 * 대분류코드 관리
	 * @param ClasslMgmtVo	paramVo
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectClasslMgmtList(ClasslMgmtVo paramVo) throws Exception;
	
	/**
	 * 대분류코드 정보 저장
	 * @param ClasslMgmtVo	paramVo
	 * @return List<ClasslMgmtVo>
	 * @throws Exception
	 */
	public Result insertClasslMgmt(HttpServletRequest request, ClasslMgmtVo paramVo) throws Exception;

	/**
	 * 대분류코드 상세조회 
	 * @param ClasslMgmtVo	paramVo
	 * @return ClasslMgmtVo
	 * @throws Exception
	 */
	ClasslMgmtVo selectClasslMgmtData(ClasslMgmtVo paramVo) throws Exception;

	
	/**
	 *  대분류코드 삭제 
	 *  DELETE
	 * @param ClasslMgmtVo paramVo
	 * @return ClasslMgmtVo
	 * @throws Exception
	 */
	Result deleteClasslMgmtData(ClasslMgmtVo paramVo) throws Exception ;

}
