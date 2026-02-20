package org.springframework.tronic.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.tronic.exception.BusinessException;
import org.springframework.tronic.exception.CustomGenericException;
import org.springframework.tronic.exception.CustomGenericExceptionUtil;
import org.springframework.tronic.exception.CustomIfException;
import org.springframework.tronic.exception.CustomNoContException;
import org.springframework.tronic.util.MessageUtil;
import org.springframework.tronic.util.StringUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.Result;

import org.apache.commons.lang.exception.ExceptionUtils;

@ControllerAdvice
@Controller(value = "globalExceptionController")
public class GlobalExceptionController {

	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionController.class);
	
	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {
		logger.error("{}",ex);
		
		ModelAndView model = new ModelAndView("/common/error/customeException");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;

	}
	
	/**
	 * 예외처리 
	 * @param Exception ex
	 * @return ModelAndView
	 * @throws Exception 
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Throwable ex, HttpServletRequest request,HttpServletResponse response )  {
		logger.error("{}",ex);	
		String exceptionName = ex.getClass().getSimpleName();
		
		
		// ajax 인 경우 Header 값  ( application/json, text/javascript, */*; q=0.01 )
		boolean json = false;
		if(request.getHeader("Accept").indexOf("application/json") > -1) json = true;
		
		if(json) {
			/*
			try { 
				response.sendError(500, exceptionName);
			} catch (IOException e) { 	e.printStackTrace(); }
			return null;
			*/
		}
		
		ModelAndView mav = null;
		if ("AccessDeniedException".equals(exceptionName))	mav =  accessDeniedExceptionHandle(ex);
		if ("DataAccessException".equals(exceptionName)) 	mav =  dataAccessExceptionHandler(ex);
		if ("RuntimeException".equals(exceptionName)) 		mav =  runtimeExceptionHandler(ex);
		
		
		if(mav == null ) return getExceptionModelAndView(ex);
		else return mav;
	}


	
	/**
	 * AccessDeniedException
	 * @param AccessDeniedException ex
	* @return ModelAndView
	 */
	private ModelAndView accessDeniedExceptionHandle(Throwable ex) {
		
		String exceName = MessageUtil.getMessage("error.AccessDeniedException");				// ACCESS DENIED				
		String message 	= MessageUtil.getMessage("error.msg.access.denied.pagenotfound.title");	// 비정상적인 접근이거나 요청하신 페이지를 찾을 수 없습니다.
		return getExceptionModelAndView(ex,exceName,message );
	}	 
	
	
	// DataAccessException SQL Exception
	private ModelAndView dataAccessExceptionHandler(Throwable ex) {
		
		String exceName = MessageUtil.getMessage("error.DataAccessException");					// DATA ACCESS DENIED	
		String message 	= MessageUtil.getMessage("error.msg.access.denied.title");				// 접근중 오류가 발생하였습니다.(Data Access Denied)
		return getExceptionModelAndView(ex,exceName,message );
	}
	
	
	// RuntimeException
	private ModelAndView runtimeExceptionHandler(Throwable ex) {
		
		String exceName = MessageUtil.getMessage("error.RuntimeException");						// RUNTIME EXCEPTION
		String message 	= MessageUtil.getMessage("error.msg.runtime.error.title");				// Data 처리중 오류가 발생하였습니다.(Runtime Exception)
		return getExceptionModelAndView(ex,exceName,message );
	}
	
	
	
	
	
	
	/*--- 이하 아직 정리 못함 -----*/
	
	
	//업무관련 사용자 정의 Exception
	@ExceptionHandler(BusinessException.class)
	public @ResponseBody Result handleBusinessException(BusinessException ex) {
		logger.error("{}",ex.getErrMsg(),ex);	
		Result result = new Result();
		result.setMsgCd(Result.BUSEXCEPTION);
		result.setMessage(ex.getErrMsg());
		result.setResultCode(ex.getErrCode());
		return result;
	}
	
	// if Exception
	@ExceptionHandler(CustomIfException.class)
	public ModelAndView handleCustomIfException(CustomIfException ex) {
		logger.error("{}",ex);	
		Result result = new Result();
		
		result.setMsgCd(Result.IFEXCEPTION);
		
		ModelAndView model = new ModelAndView("/common/error/customIfException");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;
	}
	
	// if Exception
	@ExceptionHandler(CustomNoContException.class)
	public ModelAndView handleCustomNoContException(CustomNoContException ex) {
		
		Result result = new Result();
		
		result.setMsgCd(Result.IFEXCEPTION);
		
		ModelAndView model = new ModelAndView("/common/error/customNoContException");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;
	}

	
	public ModelAndView getExceptionModelAndView(Throwable ex, String exceName, String errMsg) {
		return exceptionModelAndView( ex,  exceName,  errMsg);
	}
	
	public ModelAndView getExceptionModelAndView(Throwable ex, String exceName ) {
		return exceptionModelAndView( ex,  exceName,  null);
	}
	
	public ModelAndView getExceptionModelAndView(Throwable ex ) {
		return exceptionModelAndView( ex,   null, null);
	}

	private ModelAndView exceptionModelAndView(Throwable ex, String exceName, String errMsg) {
		
		HashMap<String, String> errMap = new HashMap<String, String>();
	
		errMap.put("httpStatus", 	CustomGenericExceptionUtil.getStates(ex).toString());
		errMap.put("simpleName", 	ex.getClass().getSimpleName());
		errMap.put("name", 			ex.getClass().getName());
		errMap.put("typeName", 		ex.getClass().getTypeName());
		errMap.put("stackTrace", 	getExceptionTrace(ex,false).toString());
		
		/*Custom Error page title and Message ----------------------*/
		if(StringUtil.isNotEmpty(exceName)) errMap.put("exceptionName", 	exceName);
		else errMap.put("exceptionName", 	MessageUtil.getMessage("error.CommonException"));				//COSTOM EXCEPTION
			
		if(StringUtil.isNotEmpty(errMsg))	 errMap.put("errMessage", 		errMsg);
		else  errMap.put("errMessage", 		MessageUtil.getMessage("error.msg.common.exception.title"));	//Data 처리중 알수없는 오류가 발생하였습니다.
		/*----------------------------------------------------------*/
		
		return new ModelAndView("/common/error/customeException").addObject("customException", errMap);
	}
	
	
	
	
	/**
	 * Exception StackTrace Message 처리 
	 * @param Throwable e
	 * @param boolean messageAll
	 * @return StringBuffer
	 */
	private StringBuffer  getExceptionTrace(Throwable e, boolean messageAll) {
		
		StringBuffer sbEx = new StringBuffer();
	
		if(messageAll) {
			sbEx.append(" getMessage : " + ExceptionUtils.getMessage(e));
			sbEx.append(" getRootCauseMessage : " + ExceptionUtils.getRootCauseMessage(e));
			sbEx.append(" getThrowableCount : " + ExceptionUtils.getThrowableCount(e));
			sbEx.append(" getThrowables : ");

	        for (final Throwable element : ExceptionUtils.getThrowables(e)) {
	        	sbEx.append(element.getMessage());
	        }

	        sbEx.append(" indexOfThrowable(e,RuntimeException.class) : "
	              + ExceptionUtils.indexOfThrowable(e, RuntimeException.class));
	        sbEx.append(" indexOfThrowable(e,Throwable.class) : "
	              + ExceptionUtils.indexOfThrowable(e, Throwable.class));
	        sbEx.append(" indexOfType(e,RuntimeException.class) : "
	              + ExceptionUtils.indexOfType(e, RuntimeException.class));
	        sbEx.append(" indexOfType(e,Throwable.class) : " 
	              + ExceptionUtils.indexOfType(e, Throwable.class));
	        sbEx.append(" getCause : " + ExceptionUtils.getCause(e));
	        sbEx.append(" getRootCause : " + ExceptionUtils.getRootCause(e));
	        sbEx.append(" getRootCauseStackTrace : ");
		}

        for (final String element : ExceptionUtils.getRootCauseStackTrace(e)) {
        	sbEx.append("\n");
        	sbEx.append(element);
        }
        
        return sbEx;
	}
	
	
	

	
}