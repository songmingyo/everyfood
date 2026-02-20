package com.doppio.workplace.sample.controller;


import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.Result;
import com.doppio.workplace.sample.service.SampleService;

/**
 * Handles requests for the application Sample page.
 */
@Controller
public class SampleExceptionController {
	
	
	@Autowired
	private SampleService sampleService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	private static final Logger logger = LoggerFactory.getLogger(SampleExceptionController.class);
	
	/**
	 * (Ajax) return Json Exception Page
	 */
	@RequestMapping(value = "/app/smp/CustomException", method = RequestMethod.GET)
	public String initExceptionSample(Model model) {
		return "sample/sampleException";
	}
	/**
	 * Handler 에서 처리
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/app/sample/sampleException.json", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Map<String,Object> sampleException(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		
		try{
			throw new RuntimeException();
			
		} catch (Exception e) {
			try {
				response.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, e.toString());
			} catch (IOException e1) {
			}
		}
		
		return result;
	}
	/**
	 * 개발자가 받아서 예외 처리
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/app/sample/sampleExceptionResult.json", method=RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody Map<String,Object> sampleExceptionResult(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> error = null;
		try{
			// list 
			throw new RuntimeException();

		} catch (Exception e) {
			
			error = new HashMap<String,Object>();
			error.put("code", "Exception");
			error.put("message", e.toString());
			result.put("error", error);
			logger.error(e.toString());
		}
		return result;
	}
	
	@RequestMapping(value="/app/sample/sampleExceptionThrow.json", method=RequestMethod.POST, headers="Accept=application/json")
	public void sampleExceptionThrow(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
	//	response.sendError(8888, "abcd Error!");
		throw new AccessDeniedException(this.message.getMessage("error.msg.custom.datanotfound.title"));
		//sampleService.sampleThrowException();
	}
	
	@RequestMapping(value="/app/sample/sampleExceptionSendError.json", method=RequestMethod.POST, headers="Accept=application/json")
	public void sampleExceptionSendError(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{

			sampleService.sampleThrowException();

		}catch(Exception e){
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@RequestMapping(value="/app/sample/sampleException.do")
	public void exceptionThrow(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String param = request.getParameter("param") ==  null ? "" : request.getParameter("param") ;
		if(param.equals("AccessDenied")){
			throw new AccessDeniedException("잘못된 접근입니다.");
		}else if(param.equals("Runtime")){
			throw new RuntimeException("실행중 오류가 발생하였습니다.");
		}else if(param.equals("JavaLength")){
			String a = "a";
			Integer.parseInt(a);
			
		}else{
			throw new Exception("시스템오류가 발생하였습니다.");
		}
		
	}
	
	
	@RequestMapping(value="/app/wicketMgrCompFileDownDataException.do")
	public ModelAndView wicketMgrCompFileDownDataException(HttpServletRequest request,Model model) throws Exception{
		
		 
		ModelAndView modelAndView = null;
		Result result = new Result();
		
		/* 파일조회  BIZ LOGIC ............*/
		
		
		
		/*등록파일 테이블 정보 확인 안됨*/
		result.setMsgCd("ERROR-FILE-01");
		result.setMessage(this.message.getMessage("error.msg.custom.file.nodata"));	// message : 등록된 파일정보가 없습니다.
		result.setResultCode("File Data Not Found");
			
		modelAndView = new ModelAndView("common/error/customFailure");
		modelAndView.addObject("result", result);
		
		
		return modelAndView;

	}
	
	
	@RequestMapping(value="/app/wicketMgrCompFileDownPermissionException.do")
	public ModelAndView wicketMgrCompFileDownPermissionException(HttpServletRequest request,Model model) throws Exception{
		
		
		ModelAndView modelAndView = null;
		Result result = new Result();
		
		/* 파일조회  BIZ LOGIC ............*/
		
		
		/*해당파일이  파일이없고 권한이 없을경우*/
		result.setMsgCd("ERROR-FILE-02");
		result.setMessage(this.message.getMessage("error.msg.custom.access.permission.denied")); // message : 관련문서이 다운로드 권한이 없습니다.
		result.setResultCode("File Permission Denied");
			
		modelAndView = new ModelAndView("common/error/customFailure");
		modelAndView.addObject("result", result);
		
		return modelAndView;

	}


}
