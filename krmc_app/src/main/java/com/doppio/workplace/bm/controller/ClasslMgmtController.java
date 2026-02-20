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
import com.doppio.workplace.bm.service.ClasslMgmtService;
import com.doppio.workplace.bm.service.ClasslMgmtVo;
import com.doppio.workplace.common.model.UserInfoVo;

/**
 * @author j10000
 * @Description : 대분류코드관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03. 22.     j10000
 * </pre>
 * @version :
 */
@Controller(value = "classlMgmtController")
public class ClasslMgmtController {

	private static final Logger logger = LoggerFactory.getLogger(ClasslMgmtController.class);
	
	@Autowired
	ClasslMgmtService  classlMgmtService;
	
	/**
     *  Init Action
 	* @param
 	* @return
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/classlMgmt")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basClasslMgmt";
 	}
 	
 	

	/**
	 * 대분류코드  조회 Event
	 * @param ClasslMgmtVo param
	 * @param HttpServletRequest
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */

	@RequestMapping(value= {"/app/bm/baseinfo/classlMgmt_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectClasslMgmtSearch(ClasslMgmtVo param, HttpServletRequest request) throws Exception {
		return classlMgmtService.selectClasslMgmtList(param);
	}
 	
	/**
	 * 대분류코드 상세 조회 Event
	 * @param ClasslMgmtVo param
	 * @param HttpServletRequest
	 * @return ClasslMgmtVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/classlMgmt_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ClasslMgmtVo selectClasslMgmtDetail (HttpServletRequest request,  @RequestBody ClasslMgmtVo param) throws Exception {
		return classlMgmtService.selectClasslMgmtData(param);
	}

	
	
	/**
	 * 대분류코드  저장 Event
	 * @param ClasslMgmtVo param
	 * @param HttpServletRequest
	 * @return List<ClasslMgmtVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/bm/baseinfo/classlMgmt_insInfo.json", method=RequestMethod.POST)
	public Result insertClasslMgmtInfo(HttpServletRequest request, @RequestBody ClasslMgmtVo param) throws Exception {
		return classlMgmtService.insertClasslMgmt(request, param);
	}

	/**
	 * 대분류코드 삭제 Event
	 * @param ClasslMgmtVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/classlMgmt_delData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result deleteClasslMgmt (HttpServletRequest request,  @RequestBody ClasslMgmtVo param, Result result) throws Exception {
		return classlMgmtService.deleteClasslMgmtData(param);
	}
	
	
 	

}
