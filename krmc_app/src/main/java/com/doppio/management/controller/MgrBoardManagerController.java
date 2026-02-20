package com.doppio.management.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.management.service.MgrBoardManagerService;
import com.doppio.management.service.MgrBoardManagerVo;


/**
 * @author dada
 * @Description : 게시판 관리
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2013.03. 09.     DADA
 * </pre>
 * @version :
 */
@Controller
public class MgrBoardManagerController {


	@Autowired
	MgrBoardManagerService mgrBoardManagerService;

	private static final Logger logger = LoggerFactory.getLogger(MgrBoardManagerController.class);

    /**
     * 게시판관리 Init Action
 	* @param
 	* @return
 	*/
 	@RequestMapping(value="/app/mgr/manager/mgrBoardManager")
 	public String mgrBoardManagerInit() throws Exception{
 		return "management/mgrBoardManager";
 	}


	/**
     * 게시판관리 Grid 목록 조회
	* @param data
	* @return
	*/
	@ResponseBody
	@RequestMapping(value="/app/mgr/manager/boardManager_selList", method=RequestMethod.POST)
	public List<MgrBoardManagerVo> BoardManagerList(MgrBoardManagerVo searchVo) throws Exception {
		List<MgrBoardManagerVo> dataList = null;

		dataList = mgrBoardManagerService.selectBoardList(searchVo);
		return dataList;
	}

	/**
	 * 게시판관리 정보 저장 JSON (UPDATE/INSERT)
	 * @param MgrBoardManager001VO, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/boardManager_insData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result BoardManagerSave(
		   @RequestBody MgrBoardManagerVo searchVo, Result result)  {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	   	2. duple		: 중복코드발생
	    3. success		: 정상
	    ===================================================================*/
		result.setMsgCd("fail");
		try {

			String retString = mgrBoardManagerService.updateBoardManagerData(searchVo);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}



	/**
	 * 게시판관리 정보 삭제 JSON (DELETE)
	 * @param MgrBoardManager001VO, request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/boardManager_delData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result BoardManagerDelete(
		   @RequestBody MgrBoardManagerVo searchVo, Result result) {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	   	2. success		: 정상
	    ===================================================================*/

		result.setMsgCd("fail");
		try {

			String retString = mgrBoardManagerService.deleteBoardManagerData(searchVo);
			result.setMsgCd(retString);

		} catch (Exception e) {
			result.setMsgCd("Exception");
			result.setMessage(e.getMessage());
			logger.error(e.toString());
		}
		return result;
	}




}