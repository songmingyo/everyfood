package com.doppio.management.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrPerInfoVo;


/**
 * 
 * @Class : MgrUserInfoCheckHistMapper.java
 * @Description : 개인정보 조회  및 사용자별 파일조회
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
@Repository(value = "mgrUserInfoCheckHistMapper")
public interface MgrUserInfoCheckHistMapper {
	
	
	/**
	 * 
	 * @Method : selectMgrUserInfoCheckList
	 * @Description : 개인정보 리스트 불러오기
	 * @param mgrPerInfoVo
	 * @return
	 * @throws SQLException
	 */
	public List<MgrPerInfoVo> selectMgrUserInfoCheckList(MgrPerInfoVo mgrPerInfoVo) throws SQLException;
	
	/**
	 * 
	 * @Method : selectMgrUserInfoCheckListCount
	 * @Description : 개인정보 갯수 
	 * @param mgrPerInfoVo
	 * @return
	 * @throws SQLException
	 */
	public int selectMgrUserInfoCheckListCount(MgrPerInfoVo mgrPerInfoVo) throws SQLException;
	
	
	/**
	 * 
	 * @Method : selectUserCheckListExcelDown
	 * @Description : 개인정보 다운로드용 엑셀 데이터 조회
	 * @param mgrPerInfoVo
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> selectUserCheckListExcelDown(MgrPerInfoVo mgrPerInfoVo) throws SQLException;
	/**
	 * 
	 * @Method : selectMgrUserFileHistory
	 * @Description : 사용자별 파일이력 리스트 불러오기
	 * @param mgrPerInfoVo
	 * @return
	 * @throws SQLException
	 */
	public List<MgrPerInfoVo> selectMgrUserFileHistory(MgrPerInfoVo mgrPerInfoVo) throws SQLException;
	/**
	 * 
	 * @Method : selectMgrUserFileHistoryCount
	 * @Description : 사용자별 파일이력 갯수
	 * @param mgrPerInfoVo
	 * @return
	 * @throws SQLException
	 */
	public int selectMgrUserFileHistoryCount(MgrPerInfoVo mgrPerInfoVo) throws SQLException;
	
	/**
	 * 
	 * @Method : selectMgrUserFileHistoryExcelDown
	 * @Description : 사용자별 파일이력 다운로드용 엑셀 데이터 조회
	 * @param mgrPerInfoVo
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> selectMgrUserFileHistoryExcelDown(MgrPerInfoVo mgrPerInfoVo) throws SQLException;
	
}
