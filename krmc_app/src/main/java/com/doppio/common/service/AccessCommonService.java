package com.doppio.common.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.doppio.common.model.AccessLog;
import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.MenuSession;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;

public interface AccessCommonService {

	/**
	 * 메뉴정보 조회
	 * @param memberCd
	 * @return List
	 */
	public List<MenuSession> selectGrantMenuList(CustomUser CustomUser) ;
	
	/**
	 * 요청url 정보를 참조하여 메뉴아이디를 조회 
	 * @param webPage
	 * @return String
	 */
	public String selectMenuidValidation(String webPage) ;
	
	/**
	 * 해당 메뉴 실행 권한 조회 실시간 db 조회 or MenuSession
	 * @param memberCd
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public boolean selectFuncPermission( String memberCd, String url, MenuSession acessMenu);
	
	/**
	 * 접근로그 저장 
	 * @param AccessLog 
	 * @return void
	 */
	public void insertAccessLog(AccessLog accessLog) ;
	
	
	/**
	 * 사용자 권한별 최상단 노출 메뉴조회
	 * @param CustomUser
	 * @return
	 */
	public List<MenuSession> selectGrantAuthSysMenuList(CustomUser customUser);
	
	
	/**
	 * 대메뉴 분류코드 유효성 체크 
	 * @param paramMap
	 * @return
	 */
	public int selectValidSysMenu(HashMap<String, String> paramMap);
	

	/**
	 * 
	 * @Method : selectAccessDt
	 * @Description : 모바일 최근 접속일시 조회
	 * @param paramMap
	 * @return
	 * @throws Exception
	 */
	public Result selectAccessDt(HashMap<String, String> paramMap) throws Exception;	
	
	/**
	 * 접근허용아이피 정보 조회
	 * @param ipAddress
	 * @param url
	 * @param acessMenu
	 * @return boolean
	 */
	public boolean selectFuncIpPermission(String ipAddress, String url, MenuSession acessMenu);
	
	/**
	 * 
	 * @Method : selectFuncUserDevicePermission
	 * @Description : 기기인증정보 확인
	 * @param url
	 * @param customUser
	 * @param request
	 * @return Result
	 */
	public Result selectFuncUserDevicePermission(String url, CustomUser customUser, HttpServletRequest request);
	
	
	
	/**
	 *  파일다운로드 이력 등록 
	 * @param FileDownloadLogVo
	 * @return INTEGER
	 * @throws Exception
	 */
	public int insertFileDownloadHist(AttachFileVo attachFileVo) throws Exception ;
}
