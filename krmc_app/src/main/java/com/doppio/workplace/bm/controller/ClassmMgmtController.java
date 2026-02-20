package com.doppio.workplace.bm.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.ClassmMgmtService;
import com.doppio.workplace.bm.service.ClassmMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;

/**
 * @author j10000
 * @Description : 중분류코드관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03. 22.     j10000
 * </pre>
 * @version :
 */
@Controller(value = "classmMgmtController")
public class ClassmMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(ClassmMgmtController.class);
	
	@Autowired
	ClassmMgmtService  classmMgmtService;
	
	/**
     *  Init Action
 	* @param
 	* @return
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/classmMgmt")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basClassmMgmt";
 	}
 	
 	

	/**
	 * 중분류코드  조회 Event
	 * @param ClassmMgmtVo param
	 * @param HttpServletRequest
	 * @return List<ClassmMasterVo>
	 * @throws Exception
	 */

	@RequestMapping(value= {"/app/bm/baseinfo/classmMgmt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClassmMgmtSearch(ClassmMgmtVo param, HttpServletRequest request) throws Exception {
		return classmMgmtService.selectClassmMgmtList(param);
	}
 	
	/**
	 * 중분류코드 상세 조회 Event
	 * @param ClassmMgmtVo param
	 * @param HttpServletRequest
	 * @return ClassmMasterVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/classmMgmt_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ClassmMgmtVo selectClassmMgmtDetail (HttpServletRequest request,  @RequestBody ClassmMgmtVo param) throws Exception {
		return classmMgmtService.selectClassmMgmtData(param);
	}

	
	
	/**
	 * 중분류코드  저장 Event
	 * @param ClassmMgmtVo param
	 * @param HttpServletRequest
	 * @return List<ClassmMgmtVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/bm/baseinfo/classmMgmt_insInfo.json", method=RequestMethod.POST)
	public Result insertClassmMgmtInfo(HttpServletRequest request, @RequestBody ClassmMgmtVo param) throws Exception {
		return classmMgmtService.insertClassmMgmt(request, param);
	}

	/**
	 * 중분류코드 삭제 Event
	 * @param ClassmMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/classmMgmt_delData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result deleteClassmMgmt (HttpServletRequest request,  @RequestBody ClassmMgmtVo param, Result result) throws Exception {
		return classmMgmtService.deleteClassmMgmtData(param);
	}
	
	
 	

}
