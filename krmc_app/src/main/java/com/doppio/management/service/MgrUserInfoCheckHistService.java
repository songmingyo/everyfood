package com.doppio.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @Class : MgrUserInfoCheckHistService.java
 * @Description : 개인정보조회이력 및 사용자별 파일조회 리스트 
 * @author : JS
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 1. 18.        최진성        	 최초 생성
 *
 * </pre>
 */
public interface MgrUserInfoCheckHistService {
	
	/**
	 * 
	 * @Method : selectMgrUserInfoCheckList
	 * @Description : 개인정보 가져오기
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectMgrUserInfoCheckList(MgrPerInfoVo mgrPerInfoVo) throws Exception;
	

	/**
	 * 
	 * @Method : selectUserCheckListExcelDown
	 * @Description : 다운로드용 엑셀 데이터 조회
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectUserCheckListExcelDown(MgrPerInfoVo mgrPerInfoVo) throws Exception;
	
	/**
	 * 
	 * @Method : selectMgrUserFileHistory
	 * @Description : 사용자별 파일이력 가져오기
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> selectMgrUserFileHistory(MgrPerInfoVo mgrPerInfoVo) throws Exception;
	
	
	/**
	 * 
	 * @Method : selectMgrUserFileHistoryExcelDown
	 * @Description : 다운로드용 엑셀 데이터 조회
	 * @param mgrPerInfoVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectMgrUserFileHistoryExcelDown(MgrPerInfoVo mgrPerInfoVo) throws Exception;
}
