package com.doppio.management.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.management.service.MgrCodeService;
import com.doppio.management.service.MgrCodeVo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller(value = "mgrCodeController")
public class MgrCodeController {

	private static final Logger logger = LoggerFactory.getLogger(MgrCodeController.class);


	@Autowired
	MgrCodeService mgrCodeService;

	/**
	 * 공통코드 관리 Init
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/app/mgr/manager/mgrCode", method = RequestMethod.GET)
    public String mgrCodeInit(Model model) {
        return "/management/mgrCode";
    }

    
    /**
	 * 공통코드 마스터 조회  검색이벤트 실행
	 * @param   MgrCodeVo
	 * @return  List<MgrCodeVo>
	 * @throws  Exception
	 */
    @ResponseBody
	@RequestMapping(value="/app/mgr/manager/mgrCode_selMasterList", method=RequestMethod.POST)
	public List<MgrCodeVo> codeMasterGridList(MgrCodeVo searchParam) throws Exception {
		return mgrCodeService.selectCodeMasterList(searchParam);
	}


    /**
     * 공통코드 서브 조회
	 * @param   MgrCodeVo
	 * @return  List<MgrCodeVo>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/app/mgr/manager/mgrCode_selSubList", method=RequestMethod.POST)
	public List<MgrCodeVo> codeSubGridList(MgrCodeVo searchParam) throws Exception {
		return mgrCodeService.selectCodeSubList(searchParam);
	}




	/**
	 * 코드마스터정보 저장 JSON (UPDATE/INSERT)
	 * @param custCourseList, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrCode_insMasterData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CodeMasterSave(
			HttpServletRequest request,
			@RequestBody MgrCodeVo params, Result result)  {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	   	2. duple		: 중복코드발생
	    3. success		: 정상
	    ===================================================================*/
		result.setMsgCd("fail");
		try {

			String retString = mgrCodeService.updateCodeMaster(params, request);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}

	/**
	 * 코드마스터정보 삭제 JSON (DELETE)
	 * @param custCourseList, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrCode_delMasterData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CodeMasterDelete(
			@RequestBody MgrCodeVo params, Result result) {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	   	2. success		: 정상
	    ===================================================================*/

		result.setMsgCd("fail");
		try {

			String retString = mgrCodeService.deleteMaString(params);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}



	/**
	 * 코드서브정보 저장 JSON (UPDATE/INSERT)
	 * @param custCourseList, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrCode_insSubData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result CodeSubSave(
			HttpServletRequest request,
			@RequestBody MgrCodeVo params, Result result)  {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. =======
		1. fail 		: Excepton발생
	   	2. duple		: 중복코드발생
	    3. success		: 정상
	    ====================================================================*/

		result.setMsgCd("fail");
		try {
			String retString = mgrCodeService.updateCodeSub(params, request);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}


	/**
	 * 코드서브정보 삭제 JSON (DELETE)
	 * @param custCourseList, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrCode_delSubData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result codeSubDelete(
		   @RequestBody MgrCodeVo params, Result result) throws Exception {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. =======
		1. fail 		: Excepton발생
	   	2. success		: 정상
	    =====================================================================*/
		result.setMsgCd("fail");
		try {

			String retString = mgrCodeService.deleteCodeSub(params);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}


	/**
	 * 코드서브정보 중복체크(마스터/서브 JSON (DELETE)
	 * @param custCourseList, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrCode_codeDupleChecked", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result codeDupleChecked(
			@RequestBody MgrCodeVo params, Result result) {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ========================================
			1. fail 		: Excepton발생
		   	2. type-null	: etcType 값없음오류 ( 마스터카운트일경우 'M', 서브카운트일경우 'S' 필수로 입력받아야함
		    3. duple		: 중복코드발생
		    4. success		: 정상
		 =====================================================================================================*/

		result.setMsgCd("fail");
		try {
			String retString = mgrCodeService.selectMasterSubCodeCount(params);
			result.setMsgCd(retString);
		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}
}
