package com.doppio.common.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.util.StringUtil;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.common.model.CommonMemberInfoVo;
import com.doppio.common.model.CustomCodeVo;
import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.model.MenuSession;
import com.doppio.common.model.Result;
import com.doppio.common.security.CustomeLogoutSuccessHandler;
import com.doppio.common.security.service.CustomUserService;
import com.doppio.common.service.CommonService;
import com.doppio.common.service.ExcelFileManagerService;
import com.doppio.common.service.VmConvertPdfService;
import com.doppio.common.service.impl.FileManagerMapper;
import com.doppio.community.service.CmtBoardService;
import com.doppio.community.service.CmtBoardVo;
import com.doppio.community.service.CmtManagerVo;
/**
 * 
 * @Class : CommonController.java
 * @Description : 시스템 공통 Controller
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 0. 0.                    	최초 생성
 *
 * </pre>
 */
@Controller(value = "commonController")
public class CommonController {

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	CommonService commonService;

	@Autowired
	CustomUserService customUserService;
	
	@Autowired
	VmConvertPdfService vmConvertPdfService;

	@Autowired
	ExcelFileManagerService excelFileManagerService;
	
	@Autowired
	CmtBoardService 	cmtBoardService;
	
	@Autowired
	FileManagerMapper fileManagerMapper;

	@Autowired
	CustomeLogoutSuccessHandler customeLogoutSuccessHandler;
	
	@Value("#{config['File.Sys.Path']}")
	String FileSysPath;
	
	@Value("#{config['zipItSrch.IpAddredd']}")
	private String zipSrchIpAddr;
	@Value("#{config['zipItSrch.PortNum']}")
	private String zipSrchPortNum;
	@Value("#{config['zipIt.PortNum']}")
	private String zipPortNum;


    /**
     * 사업자등록번호 중복확인
     *
     * @param Map
     * @return Result
     * @throws Exception
     */
    
    @RequestMapping(value = {"/app/common/bmanNoValidation","/security/bmanNoValidation"}, method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody Result bmanNoValidation(@RequestBody HashMap<String, String> searchParam, Result result) throws Exception {
    	result.setMsgCd("fail");
    	try {
    		String retString = commonService.selectBmanNoValidation(searchParam.get("bmanNo").toString());
    		result.setMsgCd(retString);
    	} catch (Exception e) {
    		logger.error(e.getMessage());
    	}
    	return result;
    }

	/**
	 * 사용자 아이디 중복확인
	 *
	 * @param Map
	 * @return Result
	 * @throws Exception
	 */

	@RequestMapping(value = {"/app/common/userIdValidation","/security/userIdValidation"}, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result userIdValidation(@RequestBody HashMap<String, String> searchParam, Result result) throws Exception {

		result.setMsgCd("fail");
		try {
			String msgCd = commonService.selectUserIdValidation(searchParam.get("userId").toString());
			result.setMsgCd(msgCd);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 회사찾기 공통팝업
	 *
	 * @param CommonCompInfoVo
	 * @return List<CommonCompInfoVo>
	 */
	@RequestMapping(value = "/app/common/findCompany", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Map<String,Object> findCompanySearch(CommonCompInfoVo searchParam) throws Exception {

		return commonService.selectCompInfoList(searchParam);
	}
	
	/**
	 * 커뮤니티 최근글 목록조회 INCLUDE 공통 Controller
	 *
	 * @param @PathVariable String
	 * @return ModelMap
	 */
	@RequestMapping(value={"/app/common/inc/communityList", "/web/common/inc/communityList"})
	public String communityListInit(@RequestParam HashMap<String, String> searchMap, HttpServletRequest request, ModelMap model) throws Exception {

		List<HashMap<String, String>> list = commonService.selectCommunityList(searchMap);
		model.addAttribute("communityList", list);

		return "/common/commonView/comCommunityList";

	}


	/**
	 * 커뮤니티 전체글  목록조회 INCLUDE 공통 Controller
	 *
	 * @param @PathVariable String
	 * @return ModelMap
	 */
	@RequestMapping(value={"/app/common/inc/communityFullList", "/web/common/inc/communityFullList"})
	public String communityFullListInit(@RequestParam HashMap<String, String> searchMap, HttpServletRequest request, ModelMap model) throws Exception {

		CmtBoardVo paramVo = new CmtBoardVo();
		paramVo.setBoardCd(searchMap.get("boardCd"));
		CmtManagerVo cmtManagerVo =cmtBoardService.selectBoardManager(paramVo);  
		
		model.addAttribute("mgrVO", cmtManagerVo);
		
		return "/common/commonView/comCommunityFullList";

	}
	
	/**
	 * 커뮤니티 최근글 조회
	 * @author DADA
	 * @param HashMap<String, Object>
	 * @return List<HashMap<String, String>>
	*/
	@RequestMapping(value={"/app/common/communityList", "/web/common/communityList"}, method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody List<HashMap<String, String>> selectCommunity(@RequestBody HashMap<String, String> searchMap) throws Exception{
		
		List<HashMap<String, String>> listData = commonService.selectCommunityList(searchMap);
		
		return listData;
	}
	
	
	/**
	 * 커뮤니티 상세조회
	 * @author DADA
	 * @param HashMap<String, Object>
	 * @return HashMap<String, Object>
	*/
	@RequestMapping(value={"/app/common/communityDetail", "/web/common/communityDetail"}, method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody HashMap<String, String> selectDasboardCommunityView(@RequestBody HashMap<String, String> searchParam) throws Exception{
		return commonService.selectDasboardCommunityView(searchParam);
	}
	

	/**
	 * 팝업존 목록조회 INCLUDE 공통 Controller
	 *
	 * @param @PathVariable String
	 * @return ModelMap
	 */
	@RequestMapping(value = "/app/common/inc/popupZoneList")
	public String popupZoneListInit(@RequestParam HashMap<String, String> searchMap, HttpServletRequest request, ModelMap model) throws Exception {

		/** 팝업존 Data 조회 */
		List<HashMap<String, Object>> list = commonService.selectPopupZoneList(searchMap);
		model.addAttribute("popupZone", list);

		return "common/commonView/comPopupZoneList";
	}

	/**
	 * 팝업존 사용자 상세조회 공통 Controller
	 *
	 * @param @PathVariable String
	 * @return ModelMap
	 */
	@RequestMapping(value = "/app/common/inc/popupZoneView", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> popupZoneView(@RequestBody HashMap<String, String> searchMap, HttpServletRequest request) throws Exception {
		/** 팝업존 Data 조회 */
		return commonService.selectPopupZoneData(searchMap);

	}

	/**
	 * 사용자찾기 공통팝업 json
	 * @param CommonMemberInfoVo
	 * @return List<CommonMemberInfoVO>
	 */
	@ResponseBody
	@RequestMapping(value="/app/common/findMember.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public Map<String,Object> findMemberSearch(CommonMemberInfoVo searchParam) throws Exception {

		return commonService.selectMemberInfoList(searchParam);

	}


	/**
	 * 메뉴 비동기 방식
	 *
	 * @param HashMap<String,String>, HttpServletRequest
	 * @return HashMap<String,String>
	 * TODOCHECK
	 */
	@RequestMapping(value = "/common/frame/selectMgrMenu", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> selectMgrMenu(@RequestBody HashMap<String, String> param, HttpServletRequest request) {
		
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		List<MenuSession> leftList = new ArrayList<MenuSession>();
		MenuSession pgmMenu = new MenuSession();
		String menuSession_key = MenuSession.MANAGER_MENU_SESSION_KEY + "_" + request.getSession().getId();
		
		String sysCode = StringUtil.nullConvert(param.get("sysCode"));
		logger.debug("selectMgrMenu sysCode : " + sysCode);
		
		try {
			List<MenuSession> allLeftList = (List<MenuSession>) request.getSession().getAttribute(menuSession_key);
			MenuSession menuNext = null;

			for (MenuSession menu : allLeftList) {

				/* 참조할 대메뉴를 이용하여 하위메뉴리스트를 생성한다.------ */
				if (menu.getSort().indexOf(param.get("menuId")) > -1) {

					// if(menu.getLevel() != 1 && menu.getLeaf() != 0) {
					if (menu.getLevel() != 1) {
						menuNext = new MenuSession();

						menuNext.setTitle(menu.getTitle());
						menuNext.setTitleSub(menu.getTitleSub());
						menuNext.setTitleLocale(menu.getTitleLocale());
						menuNext.setMenuId(menu.getMenuId());
						menuNext.setParentId(menu.getParentId());
						menuNext.setWebPage(menu.getWebPage());
						menuNext.setLeaf(menu.getLeaf());
						menuNext.setHideYn(menu.getHideYn());
						menuNext.setLevel(menu.getLevel());
						leftList.add(menuNext);
					}

					if (menu.getLevel() == 1 && menu.getLeaf() == 0) {
						pgmMenu.setTopMenuTitleLocale(menu.getTitleLocale());
						pgmMenu.setTopMenuId(menu.getMenuId());
					}
				}
				/*----------------------------------------------------*/
			}

			returnMap.put("TopMenuInfo", pgmMenu);
			returnMap.put("LeftMenuInfo", leftList);

		} catch (Exception ex) {
			logger.error("selectMgrMenu error : " +ex.getMessage());
		}
		return returnMap;
	}

	/**
	 * main 메뉴 화면 이동
	 *
	 * @param HttpServletRequest, ModelMap, MenuSession
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/frame/commonFrameMenu", method = { RequestMethod.POST, RequestMethod.GET })
	public String commonFrameMenu(HttpServletRequest request, ModelMap model, MenuSession menuSession)
			throws Exception {
		/*
		 * request.setAttribute("frameMenuUrl", vo.getMenuUrl());
		 * request.setAttribute("frameMenuTitle", vo.getTitle());
		 * request.setAttribute("frameMenuParent", vo.getParentId());
		 */
		request.setAttribute("frameMenu", menuSession);
		return "/common/commonView/commonFrame";
	}

	/**
	 * 중분류 코드 조회
	 *
	 * @param CustomCodeVO
	 * @return List<CustomCodeVO>
	 */
	@RequestMapping(value ="/web/common/selectCommonLargeCdCodeSub" , method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<CustomCodeVo> selectLargeCdController(@RequestBody CustomCodeVo paramVo, HttpServletRequest request) throws Exception {
		return commonService.selectCommonLargeCdCodeSub(paramVo);
	}

	/**
	 * 사용자 이메일 중복확인
	 *
	 * @param Map
	 * @return Result
	 * @throws Exception
	 */

	@RequestMapping(value = {"/app/common/userEmailValidation","/security/userEmailValidation"}, method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result selectUserEmailValidation(@RequestBody HashMap<String, String> searchParam, Result result) throws Exception {
		result.setMsgCd("fail");

		try {
			String retString = commonService.selectUserEmailValidation(searchParam.get("email").toString());
			result.setMsgCd(retString);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 커뮤니티 팝업공지 목록 조회
	 * @param request
	 * @param paramVo
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/web/common/inc/communityPopupInit")
	public String communityPopupListInit(HttpServletRequest request, CmtBoardVo paramVo, Model model) throws Exception {
		return "common/commonView/comCommunityPopupList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/web/common/inc/communityPopupList", method = RequestMethod.POST)
	public List<CmtBoardVo> popupZoneAlertView(@RequestBody CmtBoardVo paramVo, HttpServletRequest request) throws Exception {
		List<CmtBoardVo> list = commonService.selectCommunityPopupList(paramVo);
		
		return list;
	}

	
	/**
     *  PDF 보기 팝업
	 * @return String
	 */
	@RequestMapping(value="/app/common/comPdfFilePreview")
	public String commonPdf() throws Exception {
		return "/common/commonView/comPdfFilePreview";
	}

	/**
	 * Desc :  엑셀 업로드
	 * @Method Name : sampleExcelUpload
	 * @param SampleVO
	 * @param HttpServletRequest
	 * @param BindingResult
	 * @param Model
	 * @return view 페이지
	 */
	@RequestMapping(value="/app/common/excelUpload.json", method=RequestMethod.POST)
	  public @ResponseBody Map<String, Object> excelUploadJson(final MultipartHttpServletRequest multiRequest, ExcelFileVo excel, HttpServletRequest request, Result result, HttpServletResponse response) throws Exception{
		
		Map<String, Object> resultMap	=	new HashMap<String, Object>();
		
		List<HashMap<String, Object>> list = null;
		try {
			list = excelFileManagerService.excelUploadDataList(excel.getFile());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("EXCEPTION:::::::::::::::" + e.toString());
		}
	
		
		resultMap.put("resultListSize", list.size());
		resultMap.put("resultList",		list);

		return resultMap;		
	}
	
	/**
	 * 로그인 검증 API 호출 (개인정보관리 마스킹 해제)
	 */
	@RequestMapping(value="/security/verifyUserInfo.json", method=RequestMethod.POST)
	public @ResponseBody Result loginVerify(@RequestBody HashMap<String, String> customUser, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Result result = commonService.selectLoginVerify(customUser);
		
		return result;
	}
	

	@RequestMapping(value="/app/common/verifySsoInfo.json", method=RequestMethod.POST)
	public @ResponseBody Result connSsoVerify(@RequestBody HashMap<String, String> paramMap, HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Result result = commonService.connSsoVerify(paramMap, req);
		
		return result;
	}
	
	/**
    * 그리드 공통코드 조회 json 
	* @param CustomCodeVO
	* @return List<CustomCodeVO>
	*/
 	@ResponseBody
	@RequestMapping(value="/app/common/selectGridController.json")
	public void selectGridController(CustomCodeVo customCodeVO,HttpServletResponse response) throws Exception{
 		String result="";
 		try {
 			
 			result= commonService.selectGridController(customCodeVO);
 			response.setCharacterEncoding("utf-8"); 
 			response.getWriter().println(result);
 			
		}catch(IOException ex) {
			logger.error(ex.toString());
		}catch (SQLException e) {
			logger.error(e.toString());
		}
	}
 	
 	/**
 	 * 업체정보수정 api 테스트
 	 */
	@RequestMapping(value="/app/common/uptCompModify.json", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result updateCompModify(@RequestBody HashMap<String, String> paramMap, HttpServletRequest request) throws Exception {		
		return commonService.updateCompModify(paramMap, request);
	}
	
	
	// 202301 이후 추가 ====================================================================================
	
	/**
	 * 
	 * @Method : selectCustomCodeList
	 * @Description : 공통코드 리스트 조회
	 * @param paramVo
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	@RequestMapping(value="/app/common/selCustomCodeList.json", method=RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> selectCustomCodeList(@RequestBody CustomCodeVo paramVo) throws Exception {
		return commonService.selectCustomCodeList(paramVo);
	}
	
	/**
	 * 
	 * @Method : selectInternalSubCode
	 * @Description : 서브코드 List (EXTENT01 = COM_CD로 맵핑되는 코드 리스트)
	 * @param paramVo
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	@RequestMapping(value="/web/common/selCustomSubCodeList.json", method=RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> selectInternalSubCode(@RequestBody CustomCodeVo paramVo) throws Exception {
		return commonService.selectInternalSubCode(paramVo);
	}
	
	/**
     *  주소검색 팝업창
	 * @return String
	 */
	@RequestMapping(value= {"/web/common/addrSearch", "/security/addrSearch"})
	public String addrSearch() throws Exception {
		return "/common/commonView/addrSearch";
	}

	
}