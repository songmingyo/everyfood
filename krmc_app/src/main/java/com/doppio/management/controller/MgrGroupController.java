package com.doppio.management.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.management.service.MgrAuthProcessVo;
import com.doppio.management.service.MgrAuthVo;
import com.doppio.management.service.MgrGroupService;
import com.doppio.management.service.MgrGroupVo;
import com.doppio.management.service.MgrMemberVo;


@Controller(value = "mgrGroupController")
public class MgrGroupController {

	private static final Logger logger = LoggerFactory.getLogger(MgrGroupController.class);

	@Autowired
	MgrGroupService mgrGroupService;

	/**
	 * 그룹 관리 Init
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/app/mgr/manager/mgrGroup", method = RequestMethod.GET)
    public String mgrGroupInit(Model model) {
        return "/management/mgrGroup";
    }


    /**
   	 * 그룹정보 조회
   	 * @param   MgrGroupVo
   	 * @return  List<MgrGroupVo>
   	 * @throws  Exception
   	 */
    @ResponseBody
   	@RequestMapping(value="/app/mgr/manager/mgrGroup_selGroupList", method=RequestMethod.POST)
   	public List<MgrGroupVo> codeMasterGridList(MgrGroupVo searchParam) throws Exception {
   		return mgrGroupService.selectGroupList(searchParam);
   	}



	/**
	 * 그룹정보 수정시 상세조회
	 * @param MgrGroupVo
	 * @return List<MgrGroupVo>
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrGroup_selGroupEdit", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody MgrGroupVo groupEditView(@RequestBody MgrGroupVo searchParam) throws Exception{
		return mgrGroupService.selectGroupData(searchParam);
	}


	/**
	 * 그룹정보 저장 JSON
	 * @param MgrGroupVo
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrGroup_insGroupData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result groupDataSave(
			@RequestBody MgrGroupVo searchParam,
			Result result) throws Exception {
		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
		2. duple		: 중복
		3. success		: 정상
	    ===================================================================*/
		result.setMsgCd("fail");
		try {

			String retString = mgrGroupService.updateGroupData(searchParam);
			result.setMsgCd(retString);


		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}


	/**
	 * 그룹정보 삭제 JSON
	 * @param MgrGroupVo
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrGroup_delGroupData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result groupDelete(
			@RequestBody MgrGroupVo searchParam, Result result) throws Exception {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	   	2. success		: 정상
	    ===================================================================*/

		result.setMsgCd("fail");
		try {

			String retString = mgrGroupService.deleteGroupData(searchParam);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}


	/**
	 * 그룹에 매핑된 사용자 List 조회
	 * @param MgrMemberVo
	 * @return  List<MgrMemberVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/mgr/manager/mgrGroup_selAuthMemberList", method=RequestMethod.POST)
	public List<MgrMemberVo> selectGroupAuthMemberList(MgrMemberVo searchParam) throws Exception {
		return mgrGroupService.selectGroupAuthMemberList(searchParam);
	}


	/**
	 * 매핑된 그룹 외 사용자  List 조회
	 * @param MgrMemberVo
	 * @return  List<MgrMemberVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/mgr/manager/mgrGroup_selMemberList", method=RequestMethod.POST)
	public List<MgrMemberVo> selectGroupMemberList(MgrMemberVo searchParam) throws Exception {
		return mgrGroupService.selectGroupMemberList(searchParam);
	}


	/**
	 * 매핑 정보 저장(등록/삭제)
	 * @param MgrAuthVo
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrGroup_insAuthGroup", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result groupAuthSave(
		   @RequestBody MgrAuthVo searchParam,	Result result) throws Exception {

		result.setMsgCd("fail");
		try {

			String retString = mgrGroupService.updateGroupAuthSave(searchParam);
			result.setMsgCd(retString);

		} catch (Exception e) {
			logger.error(e.toString());
		}
		return result;
	}


	/**
	 * 그룹별 메뉴권한 리스트 조회
	 * @param MgrAuthVo
	 * @return  List<MgrAuthVo>
	 */
	@ResponseBody
	@RequestMapping(value="/app/mgr/manager/mgrGroup_selAuthMenuList", method=RequestMethod.POST)
	public List<MgrAuthVo> selectAuthMenuGridList(MgrAuthVo searchParam) throws Exception {
		return mgrGroupService.selectAuthMenuList(searchParam);
	}


	/**
	 * 권한 메뉴정보 저장 JSON (UPDATE/INSERT/DELETE)
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrGroup_insAuthMenu", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result authMenuSave(
		   @RequestBody MgrAuthProcessVo searchParam,
		   Result result) throws Exception {

		result.setMsgCd("fail");
		try {

			String retString = mgrGroupService.updateAuthInfoList(searchParam);
			result.setMsgCd(retString);

		} catch (Exception e) {
			logger.error(e.toString());
		}
		return result;
	}


}
