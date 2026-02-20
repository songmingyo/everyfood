package com.doppio.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.RfcUtil;
import org.springframework.tronic.util.StringUtil;


import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.common.model.CommonMemberInfoVo;
import com.doppio.common.model.CustomCodeVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.CustomeLogoutSuccessHandler;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.security.service.impl.CustomUserMapper;
import com.doppio.common.service.CommonService;
import com.doppio.common.taglibs.service.impl.CustomTagComboMapper;
import com.doppio.community.service.CmtBoardVo;

/**
 * 
 * @Class : CommonServiceImpl.java
 * @Description : 공통 CRUD 요청 비즈니스 클래스
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   
 *
 * </pre>
 */
@Service("commonService")
public  class CommonServiceImpl implements CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CustomUserMapper  customUserMapper;

	@Autowired
	CustomeLogoutSuccessHandler customeLogoutSuccessHandler;
	
	@Autowired
	RfcUtil rfcUtil;
	
	@Autowired
	CustomUserService customUserService;
	
	@Autowired
	CustomTagComboMapper customTagComboMapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Value("#{config['count.per.page']}")
	public String DefaultPagePerCount;
	@Value("#{config['count.row.page']}")
	public String DefaultPageRowCount;
	
	/**
	 * @Method : selectUserIdValidation
	 * @Description : 사용자 중복 확인
	 * @param userId
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String selectUserIdValidation(String userId) throws Exception {

		if(StringUtils.isEmpty(userId) ) return "emptyparam";
		int cnt = commonMapper.selectUserIdValidation( userId);
		if(cnt > 0) return "dupl";

		else return "success";

	}
	
	/**
	 * @Method : selectBmanNoValidation
	 * @Description : 사업자등록번호 중복확인
	 * @param bmanNo
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String selectBmanNoValidation(String bmanNo) throws Exception {
		if(StringUtils.isEmpty(bmanNo) ) return "fail";
		int cnt = commonMapper.selectBmanNoValidation(bmanNo);
		if(cnt > 0) {
			return "dupl";
		}else {
			return "success";
		}
	}

	/**
	 * @Method : selectCompInfoList
	 * @Description : 업체정보조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> selectCompInfoList(CommonCompInfoVo paramVo) throws Exception {

		Map<String,Object> result = new HashMap<String,Object>();


	      int total = 0;
	      int totalCount = 0;

	      int rows = Integer.parseInt(paramVo.getRows()); // 목록에 보여질 갯수
	      int page = Integer.parseInt(paramVo.getPage()); // page parameter
	      paramVo.setStartRowNo((page * rows)-rows + 1);
	      paramVo.setEndRowNo(page * rows);


	      List<CommonCompInfoVo> list = commonMapper.selectCompInfoList(paramVo);

	      if(list.size()>0) {
	    	  CommonCompInfoVo cvo = list.get(0);
		         total = ((cvo.getTotalCount() % rows) == 0) ? (cvo.getTotalCount() / rows) : (cvo.getTotalCount() / rows)  + 1;
		         totalCount = cvo.getTotalCount();
		      }

		      result.put("list", list);
		      result.put("total", total);
		      result.put("records", totalCount);

		      return result;
	}
	
	/**
	 * @Method : selectCommunityList
	 * @Description : 커뮤니티 최근글 목록조회  INCLUDE 공통
	 * @param paramMap
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectCommunityList(HashMap<String, String> paramMap) throws Exception{
		if("anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal())){
			paramMap.put("boardExposureCd","S");
		} else {
			/*작업자 아이디  생성---------------------------------------*/
			CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			/*-------------------------------------------------------*/
			if("SA".equals(customUser.getUserType())) {}/*내부*/
			else if("SU".equals(customUser.getUserType()))
				paramMap.put("boardExposureCd","I");
			else /*외부*/
				paramMap.put("boardExposureCd","S");
		}


		return commonMapper.selectCommunityList(paramMap);
	}

	/**
	 * @Method : selectDasboardCommunityView
	 * @Description : 커뮤니티 최근글 목록조회  INCLUDE 공통
	 * @param paramMap
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	@Override
	public HashMap<String, String> selectDasboardCommunityView(HashMap<String, String> paramMap) throws Exception{
		return commonMapper.selectDasboardCommunityView(paramMap);
	}

	/**
	 * @Method : selectPopupZoneList
	 * @Description : 팝업존  목록조회  INCLUDE 공통
	 * @param paramMap
	 * @return List<HashMap<String, Object>>
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> selectPopupZoneList(HashMap<String, String> paramMap) throws Exception{
		return commonMapper.selectPopupZoneList(paramMap);
	}

	/**
	 * @Method : selectPopupZoneData
	 * @Description : 팝업존  상세조회 공통
	 * @param paramMap
	 * @return HashMap<String,Object>
	 * @throws Exception
	 */
	public HashMap<String,Object> selectPopupZoneData(HashMap<String, String> paramMap) throws Exception{
		List<HashMap<String, Object>> list = commonMapper.selectPopupZoneList(paramMap);
		HashMap<String,String> hashData = null;

		if(list == null || list.size() <= 0) return  null;

		HashMap<String, Object> hashOut = list.get(0);

		Object value = null;
		if( hashOut.get("POP_CN") instanceof java.sql.Clob ) {
			value = StringUtil.clobToString(((java.sql.Clob)hashOut.get("POP_CN")));
		} else {
		  value = hashOut.get("POP_CN");
		}
		hashOut.put("POP_CN", 	(String)value);

		return hashOut;

	}


	/**
	 * @Method : selectMemberInfoList
	 * @Description : 회원정보 조회 공통
	 * @param paramVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	@Override
	public Map<String,Object> selectMemberInfoList(CommonMemberInfoVo paramVo) throws Exception {

		Map<String,Object> result = new HashMap<String,Object>();


		int total = 0;
		int totalCount = 0;

		int rows = Integer.parseInt(paramVo.getRows()); // 목록에 보여질 갯수
		int page = Integer.parseInt(paramVo.getPage()); // page parameter
		paramVo.setStartRowNo((page * rows)-rows + 1);
		paramVo.setEndRowNo(page * rows);


	    List<CommonMemberInfoVo> list = commonMapper.selectMemberInfoList(paramVo);

	    if(list.size()>0) {
	    	CommonMemberInfoVo cvo = list.get(0);
		    total = ((cvo.getTotalCount() % rows) == 0) ? (cvo.getTotalCount() / rows) : (cvo.getTotalCount() / rows)  + 1;
		    totalCount = cvo.getTotalCount();
		 }


	     result.put("list", list);
	     result.put("total", total);
	     result.put("records", totalCount);

	     return result;
	}

	/**
	 * @Method : selectCommonLargeCdCodeSub
	 * @Description : 중분류 코드 조회
	 * @param paramVo
	 * @return List<CustomCodeVo>
	 * @throws Exception
	 */
	@Override
	public List<CustomCodeVo> selectCommonLargeCdCodeSub(CustomCodeVo paramVo) throws Exception {
		return commonMapper.selectCommonLargeCdCodeSub(paramVo);
	}


	/**
	 * @Method : selectUserEmailValidation
	 * @Description : 사용자 이메일 중복 확인
	 * @param email
	 * @return String
	 * @throws Exception
	 */
	@Override
	public String selectUserEmailValidation(String email) throws Exception {
		if(StringUtils.isEmpty(email) ) return "emptyparam";

		int cnt = commonMapper.selectUserEmailValidation(email);
		if(cnt > 0) return "dupl";

		else return "success";
	}

	/**
	 * @Method : selectCommunityPopupList
	 * @Description : 팝업존 목록 조회
	 * @param cmtBoardVo
	 * @return List<CmtBoardVo>
	 * @throws Exception
	 */
	@Override
	public List<CmtBoardVo> selectCommunityPopupList(CmtBoardVo cmtBoardVo) throws Exception {
		return commonMapper.selectCommunityPopupList(cmtBoardVo);
	}

	/**
	 * 계약서 작성 엑셀 업로드 업체 검색
	 * @param paramMap
	 * @return
	 */	
	public List<CommonCompInfoVo> selectContWriteExcelUploadCompany(List<HashMap<String, Object>> list) throws Exception{
		Map<String, Object> paramMap	=	new HashMap<String, Object>();
		String masterIds[]	=	new String[list.size()];
		
		for (int i = 0; i < list.size(); i++) {			
			masterIds[i]	=	(String) list.get(i).get("COL_0");
		}
		
		paramMap.put("masterIds", masterIds);
		List<CommonCompInfoVo> resultList	=	commonMapper.selectContWriteExcelUploadCompany(paramMap);
		
		return resultList;
	}
	
	/**
	 * 사용자 로그인 검증 (개인정보관리 마스킹 해제)
	 */
	public Result selectLoginVerify(HashMap<String, String> customUser) throws Exception{
		
		Result result = new Result();
		result.setMsgCd("-1");
		result.setResultCode("fail");
		
		//1. PLS 전용사용자 정보 우선 조회 
		customUser.put("genDivnCd", "9007001");	//PLS 전용사용자
				
		CustomUser user = null;
		user = customUserMapper.selectSecurityData(customUser);
			
		//2. PLS 사용자 정보가 없을 경우, 기간계 시스템 사용자 정보 조회
		if(user == null ) {
			 String params = "userId="+customUser.get("userName")+"&password="+ customUser.get("password");
			 
			//웹서비스에 Connection
			 Map<String, Object> rtnMap  = rfcUtil.rfcConnectionModule("/restApi/verifyUserInfo", params, null, 0);
			 
			 /*접속 오류 */
			if(rtnMap == null) {
				result.setMsgCd("-2");
				return result;
			}
			
			/*기간계 시스템 사용자 로그인 실패*/
			if(!"S00".equals(rtnMap.get("msgCd"))) {
				result.setMsgCd("-3");
				return result;
			}
		}else {
			if(customUser.get("password") != user.getPassword()) {
				if(!passwordEncoder.matches(customUser.get("password"), user.getPassword())){
					result.setMsgCd("-3");
					return result;
		        }
			}
		}
		//-------------------------------------------------- */
		
		result.setResultCode("success");
		result.setMsgCd("0");
		return result;
	}
	
	public Result connSsoVerify(HashMap<String, String> paramMap, HttpServletRequest req) throws Exception {
		Result result = new Result();
		String sessionId = req.getSession().getId();
		
		String bizsIdntCd = paramMap.get("bizsIdntCd");
		String reqCode = paramMap.get("reqCode");
		String userId = paramMap.get("userId");
		
		String params = "reqCode="+reqCode+"&bizsIdntCd="+ bizsIdntCd+"&userId="+ userId+"&SessionID="+ sessionId;
		
		//웹서비스에 Connection
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap = rfcUtil.rfcConnectionModule("/restApi/verifySsoUserInfo", params, null, 0);
		
		result.setResultMap((HashMap<String, Object>) rtnMap);
		
		return result;
	}
	
	/**
	 * 공통코드 조회
	 */
	public String selectGridController(CustomCodeVo customCodeVO) throws Exception {
		 
		HashMap<String, Object>   map = new HashMap<String, Object>();		
		map.put("parentId", customCodeVO.getParentCd());
	
		List<HashMap<String, String>> dataList  = customTagComboMapper.getCodeName(map);
		
		   StringBuilder sb = new StringBuilder();
		    sb.append(":선택안함");
		     for(int i=0;i<dataList.size();i++){
		    	 HashMap<String, String> map2= (HashMap<String, String>)dataList.get(i);
		         String val   = (String)map2.get("CODE_ID").toString();
		         String text = (String)map2.get("CODE_NAME").toString();
		    
		         sb.append(";"+val+":"+text);
		         
		       /*  sb.append(val+":"+text);
		         if(i < dataList.size()-1) sb.append(";");*/
		         
		       
		     }
		    return sb.toString();
	}
	
	/**
	 * 업체 정보 변경 BO API
	 */
	public Result updateCompModify(HashMap<String, String> paramMap, HttpServletRequest request) throws Exception {
		Result result = new Result();
		
		String entpCode = paramMap.get("entpCode");
		String tempSeq = paramMap.get("tempSeq");
		
		String params = "entpCode="+entpCode+"&tempSeq="+tempSeq;
		
		//웹서비스에 Connection
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap = rfcUtil.rfcConnectionModule("/restApi/modifyCompInfo", params, null, 0);
		
		result.setResultMap((HashMap<String, Object>) rtnMap);
		
		return result;
	}
	
	/**
	 * 공통코드 리스트 조회
	 */
	@Override
	public List<HashMap<String, String>> selectCustomCodeList(CustomCodeVo customCodeVo) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", customCodeVo.getParentCd());
		map.put("subCodes", customCodeVo.getSubValue());
		map.put("subCodes2", customCodeVo.getSubValue2());
		return customTagComboMapper.selectInternalCode(map);
	}
	
	/**
	 * 서브코드 List (EXTENT01 = COM_CD로 맵핑되는 코드 리스트)
	 */
	public List<HashMap<String,String>> selectInternalSubCode(CustomCodeVo customCodeVo) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("masterParentCd", customCodeVo.getMasterParentCd());
		map.put("masterDtlCd", customCodeVo.getMasterDtlCd());
		map.put("subCodes", customCodeVo.getSubValue());
		map.put("subCodes2", customCodeVo.getSubValue2());
		return customTagComboMapper.selectInternalSubCode(map);
	}
	
	/**
	 * 그리드 SELECT BOX에 사용할 공통코드 조회
	 */
	@Override
	public String selectGridCodeList(CustomCodeVo customCodeVo) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", customCodeVo.getParentCd());
		map.put("subCode", 	customCodeVo.getSubCode());
		map.put("subCode2", customCodeVo.getSubCode2());

		List<HashMap<String, String>> dataList = commonMapper.selectCodeName(map);

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < dataList.size(); i++) {
			HashMap<String, String> map2 = (HashMap<String, String>) dataList.get(i);

			String val = (String) map2.get("CODE_ID").toString();
			String text = (String) map2.get("CODE_NAME").toString();

			sb.append(val + ":" + text);

			if (i < dataList.size() - 1) {
				sb.append(";");
			}
		}

		return sb.toString();
	}
	
}
