package com.doppio.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.common.model.CommonMemberInfoVo;
import com.doppio.common.model.CustomCodeVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.community.service.CmtBoardVo;

/**
 * @Class : CommonService
 * @Description : ecs시스템의 공통적인 CRUD 요청을 처리하는 비즈니스 인터페이스
 * @author : 김동호
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *  수정일				수정자			 수정내용
 *  ----------------	------------	---------------------------
 *  2021. 10. 18.		김동호			최초 생성
 *  2021. 12. 24.		김동호			(추가) 점포개발-전자계약 시스템간 연동
 *  2022. 01. 18.		김동호			(추가) 점포개발-전자계약 시스템간 연동 및 화면이동
 *  2022. 04. 13.		김동호			(추가) 사업자등록번호 중복확인
 *
 * </pre>
 */
public interface CommonService {
	
	/**
	 * @Method : selectUserIdValidation
	 * @Description : 사용자 중복 확인
	 * @param userId
	 * @return String
	 * @throws Exception
	 */
	String selectUserIdValidation(String userId) throws Exception;
	
	/**
	 * @Method : selectCompInfoList
	 * @Description : 업체정보조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	Map<String,Object> selectCompInfoList(CommonCompInfoVo paramVo) throws Exception;
	
	/**
	 * @Method : selectCommunityList
	 * @Description : 커뮤니티 최근글 목록조회  INCLUDE 공통 
	 * @param paramMap
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	List<HashMap<String, String>> selectCommunityList(HashMap<String, String> paramMap) throws Exception;
	
	/**
	 * @Method : selectDasboardCommunityView
	 * @Description : 커뮤니티 최근글 목록조회  INCLUDE 공통
	 * @param paramMap
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	HashMap<String, String> selectDasboardCommunityView(HashMap<String, String> paramMap) throws Exception;
	
	/**
	 * @Method : selectPopupZoneList
	 * @Description : 팝업존  목록조회  INCLUDE 공통 
	 * @param paramMap
	 * @return List<HashMap<String, Object>>
	 * @throws Exception
	 */
	List<HashMap<String, Object>> selectPopupZoneList(HashMap<String, String> paramMap) throws Exception;
	
	/**
	 * @Method : selectPopupZoneData
	 * @Description : 팝업존  상세조회 공통 
	 * @param paramMap
	 * @return HashMap<String,Object>
	 * @throws Exception
	 */
	HashMap<String,Object> selectPopupZoneData(HashMap<String, String> paramMap) throws Exception;
	
	/**
	 * @Method : selectMemberInfoList
	 * @Description : 회원정보 조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	Map<String,Object> selectMemberInfoList(CommonMemberInfoVo paramVo) throws Exception;
	
	/**
	 * @Method : selectCommonLargeCdCodeSub
	 * @Description : 중분류 코드 조회
	 * @param paramVo
	 * @return List<CustomCodeVo>
	 * @throws Exception
	 */
	List<CustomCodeVo> selectCommonLargeCdCodeSub(CustomCodeVo paramVo) throws Exception;

	/**
	 * @Method : selectUserEmailValidation
	 * @Description : 사용자 이메일 중복 확인
	 * @param email
	 * @return String
	 * @throws Exception
	 */
	String selectUserEmailValidation(String email) throws Exception;

	/**
	 * @Method : selectCommunityPopupList
	 * @Description : 팝업존 목록 조회
	 * @param cmtBoardVo
	 * @return List<CmtBoardVo>
	 * @throws Exception
	 */
	List<CmtBoardVo> selectCommunityPopupList(CmtBoardVo cmtBoardVo) throws Exception;

	/**
	 * @Method : selectBmanNoValidation
	 * @Description : 사업자등록번호 중복확인
	 * @param string
	 * @return
	 * @throws Exception
	 */
	String selectBmanNoValidation(String string) throws Exception;
	
	/**
	 * 계약서 작성 엑셀 업로드 업체 검색
	 * @param paramMap
	 * @return
	 */	
	public List<CommonCompInfoVo> selectContWriteExcelUploadCompany(List<HashMap<String, Object>> list) throws Exception;
	
	/**
	 * 사용자 로그인 검증 및 로그인 처리
	 * @param customUser
	 * @return 
	 * @throws Exception
	 */
	public Result selectLoginVerify(HashMap<String, String> customUser) throws Exception;
	
	public Result connSsoVerify(HashMap<String, String> paramMap, HttpServletRequest req) throws Exception;
	
	/**
	 * 그리드 공통 코드 조회
	 * @param customCodeVO
	 * @return
	 * @throws Exception
	 */
	public String selectGridController(CustomCodeVo customCodeVO) throws Exception ;
	
	/**
	 * 업체 정보변경 API
	 * @param paramMap
	 * @param request
	 * @return Result
	 * @throws Exception
	 */
	public Result updateCompModify(HashMap<String, String> paramMap, HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * @Method : selectCustomCodeList
	 * @Description : 공통코드 리스트 조회
	 * @param customCodeVo
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectCustomCodeList(CustomCodeVo customCodeVo) throws Exception ;
	
	/**
	 * 
	 * @Method : selectInternalSubCode
	 * @Description : 서브코드 List (EXTENT01 = COM_CD로 맵핑되는 코드 리스트)
	 * @param customCodeVo
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String,String>> selectInternalSubCode(CustomCodeVo customCodeVo) throws Exception ;
	
	/**
	 * 그리드 SELECT BOX에 사용할 공통코드 조회
	 * @param CustomCodeVo
	 * @return String
	 */
	String selectGridCodeList(CustomCodeVo customCodeVo) throws SQLException;
}
