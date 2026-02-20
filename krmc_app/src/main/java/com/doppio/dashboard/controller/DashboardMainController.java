package com.doppio.dashboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.doppio.dashboard.service.DashboardMainService;




/**
 * 
 * @Class : DashboardMainController.java
 * @Description : 메인대쉬보드 컨트롤러 
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 1.                  최초 생성
 *
 * </pre>
 */
@Controller(value = "dashboardMain")
public class DashboardMainController {

	private static final Logger logger = LoggerFactory.getLogger(DashboardMainController.class);
	
	@Autowired
	DashboardMainService dashboardMainService;

	
	
	/**
	 * @Method : initMainDashboard
	 * @Description : 메인화면 분기
	 *  PC,TABLET & MOBILE
	 * @param model
	 * @param request
	 * @param paramVo
	 * @return /main/inMainDashboard
	 * @throws Exception
	 */
	@RequestMapping(value = "/main/mainDashboard", method = RequestMethod.GET)
	public String initMainDashboard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Device device = DeviceUtils.getCurrentDevice(request);
	
		if(device.isMobile() ) return "redirect:/res/main/mainDashboard";	//MOBILE DASHBOARD REDIRECT
		else return "/main/inMainDashboard";								// PC, TABLET	DASHBOARD UI
	
	}

	
	/**
	 * MOBILE Main Dashboard
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/res/main/mainDashboard", method = RequestMethod.GET)
	 public String initResMainDashboard(Model model, HttpServletRequest request) throws Exception{
			return "/main/resMainDashboard";	 // MOBILE  DASHBOARD UI
	
	 }
	
	
	
	
	
}