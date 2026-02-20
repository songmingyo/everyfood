package com.doppio.management.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;



public interface MgrCodeService {

	/**
	 * 마스터 코드 조회
	 * @param MgrCodeVo
	 * @return List<MgrCodeVo>
	 * @throws Exception
	 */
	List<MgrCodeVo> selectCodeMasterList(MgrCodeVo paramVo);
	
	
	/**
	 * 서브 코드 조회
	 * @param MgrCodeVo
	 * @return List<MgrCodeVo>
	 * @throws Exception
	 */
	List<MgrCodeVo> selectCodeSubList(MgrCodeVo paramVo);
	
	

	/**
	 * 마스터코드 저장
	 * @param MgrCodeVo
	 * @return String
	 * @throws Exception
	 */
	String updateCodeMaster(MgrCodeVo paramVo,HttpServletRequest request) throws Exception ;
	
	/**
	 * 마스터코드 삭제
	 * @param MgrCodeVo
	 * @return String
	 * @throws Exception
	 */
	String deleteMaString(MgrCodeVo paramVo) throws Exception ;
	
	/**
	 *  서브코드 저장
	 * @param paramVo
	 * @param request
	 * @return String
	 * @throws Exception
	 */
	String updateCodeSub(MgrCodeVo paramVo,HttpServletRequest request) throws Exception ;
	
	
	/**
	 * 서브코드 삭제
	 * @param MgrCodeVo
	 * @return String
	 * @throws Exception
	 */
	String deleteCodeSub(MgrCodeVo paramVo) throws Exception ;
	
	/**
	 * 마스터코드 서브코드 카운트(중복확인)
	 * @param MgrCodeVo
	 * @return String
	 * @throws Exception
	 */
	public String selectMasterSubCodeCount(MgrCodeVo paramVo) throws Exception ;

	
}
