package com.doppio.userInfo.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.CommonCompInfoVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.userInfo.service.UserInfo001Service;
import com.doppio.workplace.common.model.UserInfoVo;

@Controller(value = "userInfo001Controller")
public class UserInfo001Controller {

	private static final Logger logger = LoggerFactory.getLogger(UserInfo001Controller.class);
	
	@Autowired
	UserInfo001Service userInfo001Service;
	
	/**
	 * 사용자 정보 Init
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/userInfo/userInfo.do")
	public String userInfoView (HttpServletRequest request, Model model) throws Exception {
		return "/userInfo/UserInfo00101";
	}
	
	@RequestMapping(value = "/app/userInfo/userInfo_selUserInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> memberView (HttpServletRequest request, HttpServletResponse response, @RequestBody UserInfoVo userInfoVo, Result result) throws Exception {
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		// 비멀번호 검증 추가
		
		userInfoVo.setMemberCd(customUser.getMemberCd());
		userInfoVo.setUserId(customUser.getUserId());
		
		HashMap<String, Object> rtnData = new HashMap<String, Object>();
		
		UserInfoVo userData = userInfo001Service.selectUserData(userInfoVo);
		rtnData.put("userData", userData);
		
		return rtnData;
	}
	
	/**
	 * 회사 정보 조회
	 * @param request
	 * @param response
	 * @param userInfoVo
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/userInfo/userInfo_selCompInfo.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> CompView (HttpServletRequest request, HttpServletResponse response, @RequestBody CommonCompInfoVo compVo, Result result) throws Exception {
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		// 비멀번호 검증 추가
		
		compVo.setMasterId(customUser.getMasterId());
		
		HashMap<String, Object> rtnData = new HashMap<String, Object>();
		
		CommonCompInfoVo compData = userInfo001Service.selectCompData(compVo);
		rtnData.put("compData", compData);
		
		return rtnData;
	}
	
}
