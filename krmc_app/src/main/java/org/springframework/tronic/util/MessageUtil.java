package org.springframework.tronic.util;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtil {
	private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);
 
	/**
     * MessageSourceAccessor
     */
	 private static MessageSourceAccessor msAcc = null;
	 public void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
		 MessageUtil.msAcc = msAcc;
	 }


    

    
	 	public static String getMessage(String key, Locale locale) {
	 		return getMessageRtn(key, null,  locale) ;
	 	}
	    
	    public static String getMessage(String key) {
	    	return getMessageRtn(key, null,  null) ;
	    }
	    
	    public static String getMessageRtn(String key, Object[] objs, Locale locale) {
	    	
	        try {
	           // ResourceBundleMessageSource bean = new ResourceBundleMessageSource();
	            //bean.setBasename("message/messages");
	        	 if(locale ==  null) {
	        	//	 HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        	//	 HttpSession session = httpRequest.getSession(); 
	        	//	 locale = (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
	        	
	        		 locale 	= LocaleContextHolder.getLocale();
	        		 
	        	 }
	        	 return msAcc.getMessage(key, objs, locale);
	          
	        }
	        catch (Exception e) {
	        	logger.debug(e.getMessage());
	            return "Unresolved key: " + key;
	        }
	    }
	
	    
}