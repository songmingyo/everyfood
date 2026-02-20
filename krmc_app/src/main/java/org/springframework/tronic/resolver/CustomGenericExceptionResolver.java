package org.springframework.tronic.resolver;


import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.tronic.exception.CustomGenericExceptionUtil;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;


/**
 * Exception 처리
 * @author dada
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * 2016.09.11	dada	   resolveException() Override
 * </pre>
 */

public class CustomGenericExceptionResolver extends SimpleMappingExceptionResolver {

	private ResourceBundleMessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(CustomGenericExceptionResolver.class);
	
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {		
		logger.debug(ex.getMessage());
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler,  Exception ex) {
		
		/*
		 400 - Bad Request
		 500 - Internal Server Error
		 406 - Not Acceptable
		 415 - Unsupported Media Type
		 405 - Method Not Allowed
		*/
		
		String contentType = request.getHeader("Content-Type");
		ModelAndView model = null;
		
		int statusCode= HttpStatus.INTERNAL_SERVER_ERROR.value();
		String reason= HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();;
	        
		// ajax 인 경우 Header 값  ( application/json, text/javascript, */*; q=0.01 )
		logger.debug(request.getHeader("Accept"));
		

		HttpStatus httpStatus = CustomGenericExceptionUtil.getStates(ex);
		
		
		
		logger.error("\n--------------------------------------------------Exception Resolver---------------------------------------------------");
		//logger.error("Object handler : "+					handler.toString());
		
		
		logger.error("Responce Status : "+				    response.getStatus());
		logger.error("HttpStatus Status : "+				httpStatus.value());
		logger.error("Exception ReasonPhrase : "+ 			httpStatus.getReasonPhrase() );
		logger.error("Exception ErrorClass : "+   			ex.getClass().getName());
		logger.error("Exception ErrorMessage : "+ 			ex.getMessage());

		logger.error("Exception Cause : "+				    ex.getCause());
		logger.error("Exception Cause ShortName : "+	    ClassUtils.getShortName(ex.getClass()));
		
		
		
		logger.error("response value : "+ response.getStatus() );
		
		
		logException(ex, request);
		logger.error("---------START EXCEPTION STACK TRACE-----------");
		logger.error("STACK TRACE===\n"+ex.getStackTrace());
		logger.error("---------END EXCEPTION STACK TRACE-----------");
		logger.error("\n----------------------------------------------------------------------------------------------------------------------");
		
		
		

        // Content-Type 확인, json 만 View를 따로 처리함.
        if(contentType!=null && MediaType.APPLICATION_JSON_VALUE.equals(contentType)){
            model = new ModelAndView("jsonView");
            ResponseStatus annotation = ex.getClass().getAnnotation(ResponseStatus.class);
 
            if(annotation!=null){
                reason = annotation.reason();
                statusCode = annotation.value().value();
            }
 
            model.addObject("reason",reason);
            model.addObject("statusCode",statusCode);
     
            return model;
 
        } else {
            return super.resolveException(request, response, handler,ex);
        }
 
       
		
		
		/*
		// Header 값이외에 request.getRequestURL() 로 비교해도 된다.
		if(request.getHeader("Accept").indexOf("application/json") > -1){
			
			
			// Message.properties 에서 가져와야한다. jquery.handler.js 가 공통으로 빠져있어서 
		    // Locale locale = RequestContextUtils.getLocale(request);
		    // String messageText = messageSource.getMessage("systemError", null, locale);
		    
		    Map<String, String> message = new HashMap<String, String>();
		    message.put("errorCode", 	"A");
		    message.put("errorMessage", "B");
		    ModelAndView mav = new ModelAndView();
		    mav.setView(new MappingJackson2JsonView());
		    mav.addObject("message", message);
		    
		    try {
		    	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.toString());
			} catch (IOException e) {
			}
		    
		    
		    return mav;
	    
		}else{
				
			return super.resolveException(request, response, handler,ex);
		}
		*/
		
	}
	
	
	/**
	* @param t , Throwable
	* @return String of the exception
	*/
	public static String getStackTrace(Throwable t) {
		StringWriter stringWritter = new StringWriter();
		PrintWriter printWritter = new PrintWriter(stringWritter, true);
		logger.debug(t.getMessage());
		printWritter.flush();
		stringWritter.flush();
		return stringWritter.toString();
	}
	
	
	
}
