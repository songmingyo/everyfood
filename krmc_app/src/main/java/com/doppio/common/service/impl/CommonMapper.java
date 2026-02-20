package com.doppio.common.service.impl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.common.model.CommonMemberInfoVo;
import com.doppio.common.model.CustomCodeVo;
import com.doppio.common.security.service.CustomUser;
import com.doppio.community.service.CmtBoardVo;

@Repository(value = "commonMapper")
public interface CommonMapper {


	/**
	 * 사용자아이디 중복확인
	 * @param String
	 * @return Integer
	 */
	public int selectUserIdValidation(String userId);


	/**
	 * 업체 조회(협력/계열 조건별)
	 * @param CommonCompInfoVo
	 * @return List<CommonCompInfoVo>
	 */
	public List<CommonCompInfoVo> selectCompInfoList(CommonCompInfoVo paramVo);

	/**
	 * 테이블 시퀀스 생성 리턴
	 * @param String
	 * @return HashMap<String,String>
	 */
	//public HashMap<String,String> selectSequenceNo(HashMap<String, String> paramVo);


	/**
	 * 시퀀스 생성 리턴
	 * @param String
	 * @return HashMap<String,String>
	 */
	public void insertSequenceNo(HashMap<String, String> paramVo);


	/**
	 * 커뮤니티 최근글 목록조회  INCLUDE 공통
	 * @param HashMap<String, String> paramMap
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectCommunityList(HashMap<String, String> paramMap);
	
	/**
	 * 커뮤니티 최근글 상세  INCLUDE 공통
	 * @param HashMap<String, String> paramMap
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> selectDasboardCommunityView(HashMap<String, String> paramMap);
	
	/**
	 *팝업존  목록조회  INCLUDE 공통
	 * @param HashMap<String, String> paramMap
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, Object>> selectPopupZoneList(HashMap<String, String> paramMap);


	/**
	 * 사용자 조회(협력/계열 조건별)
	 * @param CommonMemberInfoVo
	 * @return List<CommonMemberInfoVo>
	 */
	public List<CommonMemberInfoVo> selectMemberInfoList(CommonMemberInfoVo paramVo);


	/**
	 *  중분류 코드 조회
	 * @param CustomCodeVo
	 * @return List<CustomCodeVo>
	 */
	public List<CustomCodeVo> selectCommonLargeCdCodeSub(CustomCodeVo paramVo);

	/**
	 * 사용자 이메일 중복 확인
	 * @param email
	 * @return
	 */
	public int selectUserEmailValidation(String email);
	
	/**
	 * 게시글 내용 조회
	 * @param paramVo
	 * @return
	 */
	public List<CmtBoardVo> selectCommunityPopupList(CmtBoardVo paramVo);

	/**
	 * @Method : selectCustomUser
	 * @Description : 사용자정보 조회
	 * @param userMap
	 * @return CustomUser
	 * @throws SQLException
	 */
	public CustomUser selectCustomUser(Map<String, Object> userMap) throws SQLException;

	/**
	 * @Method : selectBmanNoValidation
	 * @Description : 사업자등록번호 중복확인
	 * @param bmanNo
	 * @return Integer
	 * @throws SQLException
	 */
	public int selectBmanNoValidation(String bmanNo) throws SQLException;
	
	/**
	 * 계약서 작성 엑셀 업로드 업체 검색
	 * @param paramMap
	 * @return
	 */	
	public List<CommonCompInfoVo> selectContWriteExcelUploadCompany(Map<String, Object> paramMap) throws SQLException;
	
	/**
	 * 공통 코드 상세 조회
	 * @param HashMap<String, Object>
	 * @return List<HashMap<String, String>>
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> selectCodeName(HashMap<String, Object> searchMap) throws SQLException;
	
}
