package com.doppio.common.service.impl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.AccessLog;
import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.MenuSession;
import com.doppio.common.model.PermissionResult;
import com.doppio.common.security.service.CustomUser;

@Repository(value = "accessCommonMapper")
public interface AccessCommonMapper {

	/**
	 * 권한별 메뉴 목록 조회
	 * @param memberCd
	 * @return List<MenuSession>
	 */
	public List<MenuSession> selectMenuListById(HashMap<String, String> paramMap);
	
	/**
	 * GUEST  메뉴 목록 조회
	 * @param memberCd
	 * @return List<MenuSession>
	 */
	public List<MenuSession> selectMenuListByGuest();
	
	/**
	 * 접근 URL을 통한 메뉴아이디 조회
	 * @param webPage
	 * @return String
	 */
	public String selectMenuidValidation(String webPage);
	
	/**
	 * 접근 URL 사용자 버튼 이벤트 권한 조회 
	 * @param PermissionResult
	 * @return PermissionResult
	 */
	public MenuSession selectFuncPermission(PermissionResult permissionResult);
	
	/**
	 * 접근로그 정보 마스터/디테일  저장 프로시저 
	 * @param AccessLog
	 * @return AccessLog
	 */
	public AccessLog insertAccessLogSp(AccessLog accessLog);
	
	/**
	 * 사용자 권한별 최상단 노출 메뉴조회
	 * @param memberCd
	 * @return
	 */
	public List<MenuSession> selectGrantAuthSysMenuList(CustomUser customUser);
	
	/**
	 * 메뉴 대분류코드
	 * @param selectValidSysMenu
	 * @return
	 */
	public int selectValidSysMenu(HashMap<String, String> selectValidSysMenu);	
	
	/**
	 * 
	 * @Method : selectAccessDt
	 * @Description : 모바일 최근 접속일시 조회
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public String selectAccessDt(HashMap<String, String> paramMap) throws SQLException;	
	
	
	
	/**
	 * 접근허용아이피 정보 조회
	 * @param List<HashMap<String, Strring>>
	 * @return AccessLog
	 */
	public List<HashMap<String, String>> selectAccessIpList();
	
	/**
	 * @Method : selectChkUserDeviceAuthCookieValidYn
	 * @Description : 사용자 기기인증 쿠키 정보 유효성 확인
	 * @param param
	 * @return String
	 */
	public String selectChkUserDeviceAuthCookieValidYn(HashMap<String,String> param);
	
	
	
	
	/**
	 * 파일다운로드 이력 생성 
	 * @param fileDownloadLogVo
	 * @return int
	 */
	public int insertFileDownloadHist(AttachFileVo attachFileVo);
	
	
}
