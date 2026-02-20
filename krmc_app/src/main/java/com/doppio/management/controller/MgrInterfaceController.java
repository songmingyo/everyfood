package com.doppio.management.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.util.RfcUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.management.service.MgrInterfaceService;
import com.doppio.management.service.MgrInterfaceVo;

@Controller(value = "mgrInterfaceController")
public class MgrInterfaceController {

	private static final Logger logger = LoggerFactory.getLogger(MgrInterfaceController.class);
	
	@Autowired
	MgrInterfaceService mgrInterfaceService;
	
	@Autowired
	RfcUtil rfcUtil;
	
	/**
	 * 인터페이스 관리 Init
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrInterface", method = RequestMethod.GET)
	public String mgrInterfaceInit (Model model) {
		
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		model.addAttribute("today", dtf.format(localDate));
		return "/management/mgrInterface";
	}
	
	/**
	 * 작업 내역 조회
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrInterface_selMasterList", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public List<MgrInterfaceVo> interfaceMasterGridList(MgrInterfaceVo searchParam) throws Exception {
		return mgrInterfaceService.selectIfMasterList(searchParam);
	}
	
	/**
	 * 수작업 리스트 조회
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrInterface_selSubList", method = RequestMethod.POST, headers = "Accept=application/json")
	@ResponseBody
	public List<MgrInterfaceVo> interfaceSubGridList(MgrInterfaceVo searchParam) throws Exception {
		return mgrInterfaceService.selectIfSubList(searchParam);
	}
	
	/**
	 * 수동 배치 실행 ( 인터페이스 API 다른 컨테이너 구성시 사용)
	 * @param searchParam
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/app/mgr/manager/mgrInterface_selManualBatch", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result interfaceManualBatch(@RequestBody MgrInterfaceVo searchParam) throws Exception {

		
		Result result = new Result();
		/*
		result.setMsgCd("fail");
		
		String applCd = searchParam.getDtlCd();
		
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		
		// 사용자 정보
		if ("IFA1001".equals(applCd))
			rtnMap = rfcUtil.rfcConnectionModule("/updateUserIfBatchReq", null, null, 0);
		if (rtnMap == null) {
			result.setMsgCd("fail");
			return result;
		}
		
		// 성공이 아닐 경우
		if (!"S00".equals(rtnMap.get("msgCd"))) {
			result.setMsgCd("fail");
			return result;
		}
		
		result.setMsgCd("success");
		*/
		return result;
		
	}
}
