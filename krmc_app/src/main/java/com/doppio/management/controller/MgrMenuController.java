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
import com.doppio.management.service.MgrMenuService;
import com.doppio.management.service.MgrMenuVo;


@Controller(value = "mgrMenuController")
public class MgrMenuController {

	private static final Logger logger = LoggerFactory.getLogger(MgrMenuController.class);

	@Autowired
	MgrMenuService mgrMenuService;

	/**
	 * 공통메뉴 관리 Init
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/app/mgr/manager/mgrMenu", method = RequestMethod.GET)
    public String mgrMenuInit(Model model) {
        return "/management/mgrMenu";
    }

    /**
	 * 공통메뉴 조회
	 * @param   MgrMenuVo
	 * @return  List<MgrMenuVo>
	 * @throws  Exception
	 */
    @ResponseBody
    @RequestMapping(value="/app/mgr/manager/mgrMenu_selMenuList", method=RequestMethod.POST)
	public List<MgrMenuVo> mgrMenuGridList(MgrMenuVo searchParam) throws Exception {
		return mgrMenuService.selectMenuList(searchParam);
	}

	/**
	 * 메뉴 등록/수정 (MERGE)
	 * @param MgrMenuVo, request
	 * @return Result
	 */
	@RequestMapping(value="/app/mgr/manager/mgrMenu_insData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result menuSave(@RequestBody MgrMenuVo params,  Result result) {

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
	    3. success		: 정상
	    ===================================================================*/
		result.setMsgCd("fail");
		
		try {

			String retString = mgrMenuService.updateMenuData(params);
			result.setMsgCd(retString);

		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		return result;
	}


	/**
	 * 메뉴 삭제 (DELETE)
	 * @param MgrMenuVo
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value="/app/mgr/manager/mgrMenu_delData", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result menuDelete(
			@RequestBody MgrMenuVo params, Result result){

		/* ==== retString  정의 (JSP Script에서 메시지 코드에따라 처리함. ======
		1. fail 		: Excepton발생
		2. nodata		: 삭제된 자료가 없음(처리된 자료가 없음)
	    3. success		: 정상
	    ===================================================================*/
		result.setMsgCd("fail");
		try {
			String retString  = mgrMenuService.deleteMenuData(params);
			result.setMsgCd(retString);

		} catch (Exception e) {
			logger.error(e.toString());
		}
		return result;
	}




}
