package com.doppio.common.security;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;


public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    private String errorPage;

    public CustomAccessDeniedHandler() {
    }

    public CustomAccessDeniedHandler(String errorPage) {
        this.errorPage = errorPage;
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {

        String accept 		= request.getHeader("accept");
        String contentType 	= request.getHeader("Content-Type");

        String error = "true";
        String message = exception.getMessage();

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        
        if(contentType!=null && MediaType.APPLICATION_JSON_VALUE.equals(contentType)){
            String data = StringUtils.join(new String[] {
                " { \"response\" : {",
                " \"error\" : " , error , ", ",
                " \"message\" : \"", message , "\" ",
                "} } "
            });

            PrintWriter out = response.getWriter();
            out.print(data);
            out.flush();
            out.close();
        }else {
            response.sendRedirect(errorPage);
        }

    }

}
