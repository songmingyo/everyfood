package com.doppio.management.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.doppio.management.service.MgrTermsService;
import com.doppio.management.service.MgrTermsVo;

/**
 * 
 * @Class : MgrTermsController.java
 * @Description : 약관 동의 정보
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *
 * </pre>
 */
@Controller(value = "mgrTermsController")
public class MgrTermsController {
	
	@Autowired
	MgrTermsService mgrTermsService;
	
	private static final Logger logger = LoggerFactory.getLogger(MgrTermsController.class);

	/**
	 * 약관 정보 페이지
	 * @param paramVo
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrTerms", method = RequestMethod.GET)
	public String mgrTermsInit(MgrTermsVo paramVo, Model model, HttpServletRequest request) throws Exception {
		return "/management/mgrTerms";
	}
	
	/**
	 * 약관정보 가져오기
	 * @param hParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrTerms_selList", method = RequestMethod.POST)
	public @ResponseBody List<HashMap<String, String>> selectVersion(@RequestBody HashMap<String, Object> hParam) throws Exception{
		return mgrTermsService.selectVersion(hParam);
	}
	
	/**
	 * 약관정보 저장
	 * @param paramVo
	 * @param result
	 * @return
	 * @throws Exception
	 * TODO
	 * 1.약관관리 수정 필요
	 * 	1) '시행일시, 문서명' 확인하여 version up , 시행일시 데이터 저장 필요
	 *  2) 약관 변경시 comCd='9012'의 코드명이 update 되고 있음. 코드명은 변하지 않도록 수정필요
	 */
	@RequestMapping(value = "/app/mgr/manager/mgrTerms_insert", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Result mgrTermsSave(@RequestBody MgrTermsVo paramVo, Result result) throws Exception {
		result.setMsgCd("fail");
		
		try {
			String check = mgrTermsService.insertTerms(paramVo);
			result.setMsgCd(check);
			
		} catch(Exception e) {
			logger.error(e.toString());
		}
		return result;
	}
	
	/**
	 * 협력사용 약관 조회 
	 * TODO: 협력사용 약관 조회시 시행일자 조건 추가 필요, 해당 기간에 알맞은 약관을 조회해야함. (약관관리 수정 후 추가 작업 필요)
	 */
	
	@RequestMapping(value = {"/front/common/selTermsList.json","/app/common/selTermsList.json"}, method = RequestMethod.POST)
	public @ResponseBody List<MgrTermsVo> selectTermsList(@RequestBody MgrTermsVo paramVo) throws Exception{
		return mgrTermsService.selectTermsList(paramVo);
	}
}
