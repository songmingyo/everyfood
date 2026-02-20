package com.doppio.management.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.management.service.MgrGroupVo;
import com.doppio.management.service.MgrMemberService;
import com.doppio.management.service.MgrMemberVo;

/**
 * 
 * @Class : MgrMemberController.java
 * @Description : 시스템관리 -사용자 정보 Controller
 * @author : 안석진
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2022. 12. 22.            안석진           최초 생성 (사용자 정보 -비밀번호 변경 추가)
 *
 * </pre>
 */

@Controller(value = "mgrMemberController")
public class MgrMemberController {

	private static final Logger logger = LoggerFactory.getLogger(MgrMemberController.class);


	@Autowired
	MgrMemberService mgrMemberService;


	/**
	 * 사용자 관리 Init
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/app/mgr/manager/mgrMember", method = RequestMethod.GET)
    public String mgrMemberInit(Model model) {
        return "/management/mgrMember";
    }


    /**
   	 * 사용자 정보 조회
   	 * @param   MgrMemberVo
   	 * @return  Map<String,Object>
   	 * @throws  Exception
   	 */
    @ResponseBody
   	@RequestMapping(value="/app/mgr/manager/mgrMember_selList", method=RequestMethod.POST)
   	public Map<String,Object> memberSearchGridList(MgrMemberVo searchParam) throws Exception {
   		return mgrMemberService.memberSearchGridList(searchParam);
   	}



	/**
	 * 사용자  상세 조회
	 * @param MgrMemberVo
	 * @return HashMap
	*/
	@RequestMapping(value="/app/mgr/manager/mgrMember_selDetail", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody HashMap<String, Object> memberEditView(
			@RequestBody MgrMemberVo searchParam, Result result) {

		HashMap<String, Object> rtnData = new HashMap<String, Object>();
		try{

			/*사용자 정보 조회----------------------------------------------------------*/
			if(!StringUtils.isEmpty(searchParam.getMemberCd())) {
				MgrMemberVo memberData = mgrMemberService.selectMemberData(searchParam);
				rtnData.put("memberData", 	memberData);
				System.out.println(" -->>>>>"+  memberData.getPassLastDyLocale());
			}
			/*------------------------------------------------------------------------*/

			/*사용자 권한그룹 조회-------------------------------------------------------*/
			List<MgrGroupVo> groupList = mgrMemberService.selectGroupList(searchParam.getMemberCd());
			rtnData.put("groupList", 	groupList);
			/*------------------------------------------------------------------------*/


		} catch (Exception e) {

			logger.error(e.toString());
		}
		return rtnData;

	}

	/**
	* 사용자정보 저장
	* @param MgrMemberVo
	* @return Result
	*/
	@RequestMapping(value="/app/mgr/manager/mgrMember_insData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result memberSave(
				@RequestBody MgrMemberVo searchParam, Result result) {
		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	    2. success		: 정상
	    ===================================================================*/

		result.setMsgCd("fail");
		try{

			String retString = mgrMemberService.updateMemberData(searchParam);
			result.setMsgCd(retString);

		} catch (Exception e) {

			logger.error(e.toString());
		}
		return result;
	}

	/**
	* 사용자 접근아이피 목록 조회
	* @param data
	* @return
	*/
	@ResponseBody
	@RequestMapping(value="/app/mgr/manager/mgrMember_selAccessIpList", method=RequestMethod.POST, headers="Accept=application/json")
	public List<MgrMemberVo> memberSearchAccessIpList(MgrMemberVo paramVo,HttpServletRequest request) throws Exception {
		return mgrMemberService.selectAccessIpList(paramVo);
	}

	/**
	 * 
	 * @Method : updateMgrPassWord
	 * @Description : 사용자 정보- 비밀번호 변경
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrMember_updPw", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result updateMgrPassWord(@RequestBody MgrMemberVo paramVo) throws Exception {
		return mgrMemberService.updateMgrPassWord(paramVo);

	}

}
