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
import com.doppio.workplace.bm.service.EmpManagementService;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.common.model.UserInfoVo;

/**
 * @author j10000
 * @Description : 사원정보관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.03. 11.     j10000
 * </pre>
 * @version :
 */
@Controller(value = "empManagementController")
public class EmpManagementController {

	private static final Logger logger = LoggerFactory.getLogger(EmpManagementController.class);
	
	@Autowired
	EmpManagementService  empManagementService;
	
	/**
     *  Init Action
 	* @param
 	* @return
 	*/
 	@RequestMapping(value="/app/bm/baseinfo/empManagement")
 	public String mgrBoardManagerInit() throws Exception{
 		return "app/bm/basEmpManagement";
 	}
 	
 	

	/**
	 * 사원정보  조회 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */

	@RequestMapping(value= {"/app/bm/baseinfo/empManagement_selList.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectEmpManagementSearch(EmpMasterVo param, HttpServletRequest request) throws Exception {
		return empManagementService.selectEmpManagementList(param);
	}
 	
	/**
	 * 영업사원정보 상세 조회 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return EmpMasterVo
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/empManagement_selDetail.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody EmpMasterVo selectEmpManangementDetail (HttpServletRequest request,  @RequestBody EmpMasterVo param) throws Exception {
		return empManagementService.selectEmpManagementData(param);
	}

	
	
	/**
	 * 영업사원정보  저장 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return List<EmpMasterVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/bm/baseinfo/empManagement_insInfo.json", method=RequestMethod.POST)
	public Result insertLEmpManagementInfo(HttpServletRequest request, @RequestBody EmpMasterVo param) throws Exception {
		return empManagementService.insertEmpManagementInfo(request, param);
	}

	/**
	 * 영업사원정보 삭제 Event
	 * @param EmpMasterVo param
	 * @param HttpServletRequest
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/bm/baseinfo/empManagement_delData.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Result deleteEmpManagement (HttpServletRequest request,  @RequestBody EmpMasterVo param, Result result) throws Exception {
		return empManagementService.deleteEmpManagementData(param);
	}
	
	
 	

}
