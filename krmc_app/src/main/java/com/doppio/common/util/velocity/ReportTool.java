package com.doppio.common.util.velocity;

import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.view.ToolboxManager;
import org.apache.velocity.tools.view.context.ChainedContext;
import org.apache.velocity.tools.view.servlet.ServletToolboxManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.doppio.common.util.velocity.ReportTool;

public class ReportTool {

	private static final Logger logger = LoggerFactory.getLogger(ReportTool.class);
	 
	protected HttpServletRequest request = null;
    public VelocityEngine ve = null;
    public ToolboxManager manager  = null;
    public ChainedContext context = null;
    public  Template template = null;
    
    public ReportTool(HttpServletRequest request, String path, String fileName) throws Exception{
        this.request = request;
        
      
        try
        {
        	
        	if(StringUtils.isEmpty(path)) {
        		path = request.getSession().getServletContext().getRealPath("/WEB-INF/velocity/");
        	}
        	
	        if (ve == null) 
	        {
	            synchronized(ReportTool.class) 
	            {
	                if (ve == null) ve = getVelocityEngine(path);
	            }
	        }
	        
	        // Velocity Tool Box
	        if (manager == null) 
	        {
	            synchronized(ReportTool.class) {
	                if (manager == null) {
	                    manager = ServletToolboxManager.getInstance(request.getSession().getServletContext(), "/WEB-INF/velocity/toolbox.xml");
	                }
	            }
	        }
	        
	        context = new ChainedContext(ve, request, null, request.getSession().getServletContext());
	        context.setToolbox(manager.getToolbox(context));
	        
	        
	        if(getFileCheck(path+fileName)) template = ve.getTemplate(fileName);
	        
        }catch(Exception ex)
        {
        	
        	logger.debug(ex.getMessage());
        }
    }
    
    public String getBindVelData() throws Exception{
    	  StringWriter writer = new StringWriter();
          template.merge(context, writer);
         
          String strVelocity =  writer.toString();
          
          return strVelocity;
    }
    
    /**
     * Velocity Engine 
     * @return
     */
    private  VelocityEngine getVelocityEngine(String path) {
        try {
            Properties prop = new Properties();
            	
            prop.setProperty("file.resource.loader.path",path);
            prop.setProperty("input.encoding",  "UTF-8");
            prop.setProperty("output.encoding", "UTF-8");
            
            prop.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.JdkLogChute");
            
          
            return new VelocityEngine(prop);
        } catch(Exception e) {
        	logger.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean getFileCheck(String filePath) throws Exception{
  	  
    	java.io.File file = null;
		try
		{
			file = new java.io.File(filePath);
			if(file.isFile()) 
				return true;
		}
		catch(Exception ex)
		{
			logger.debug(ex.getMessage());
			return false;
			
		}
		return false;
  }
}
