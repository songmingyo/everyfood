/*
 * $Id: ButtonTag.java,v 1.1 2007/03/02 05:25:24 user1 Exp $
 *
 * Copyright 1999-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.doppio.common.taglibs.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.tronic.util.MessageUtil;


import com.doppio.common.model.MenuSession;


public class CustomTagButtonService extends TagSupport {
	private static final Logger logger = LoggerFactory.getLogger(CustomTagButtonService.class);

	private static final long serialVersionUID = 126984945885442747L;

    // ----------------------------------------------------- Constructor
	public CustomTagButtonService() {
        super();
       // doDisabled = false;
    }

    // ------------------------------------------------------------- Properties

    public final static int SKIP_BODY = 0;

    /**button object ID tag */
    protected String 	id  	 = "";
    /**button object NAME tag */
    protected String 	name 	 = "";
    /**botton object VALUE tag  */
    protected String 	value 	 = "";
    /**botton object VISIBLE tag */
    protected boolean	visible  = false;
    /**botton object Script function  */
    protected String 	event 	 = null;
    /**botton object STYLE tag */
    protected String 	style 	 = "";
    /**botton object DISABLED tag */
    protected String 	disabled = "";
    /**botton object TITLE tag  */
    protected String 	objTitle = "";
    /**botton object CLASS tag  */
    protected String 	css		 = "";
    /**button object TYPE tag */
    protected String    type     ="";
    /**button values 속성용 message properties key 값*/
    protected String 	msgKey 	 = "";

    /**버튼 권한속성
     * select, save, insert, delete, edit, print, excel
     *  */
    protected String 	auth 	 = "";



    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuth(){
    	return this.auth;
    }
    public void setAuth(String auth){
    	this.auth = auth;
    }

    public String getName(){
    	return this.name;
    }
    public void setName(String name){
    	this.name = name;
    }

    public String getValue(){
    	return this.value;
    }
    public void setValue(String value){
    	this.value = value;
    }

    public boolean getVisible() {
        return (this.visible);
    }
    public void setVisible(boolean visible) {

        this.visible = visible;
    }

    public String getEvent() {
        return (this.event);
    }
    public void setEvent(String event) {

        this.event = event;
    }

    public String getStyle() {
        return (this.style);
    }
    public void setStyle(String style) {
    	this.style = style;
    }

	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public void setCss(String css) {
		if(css == null) this.css= "";
		this.css = css;
	}

	public void setObjTitle(String objTitle) {
		this.objTitle = objTitle;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getMsgKey() {
		return msgKey;
	}
	public void setMsgKey(String msgKey) {
		this.msgKey = msgKey;
	}
	/**
     * Release any acquired resources.
     */
    @Override
    public void release() {

        super.release();
        /*required : fall 파라메터 정보는 초기화 필요 -----------------------------------------*/
    	this.name 		= "";
    	this.value 		= "";
    	this.visible 	= false;
    	this.event 		= null;
    	this.style 		= "";
    	this.type       = "";
    	this.disabled 	= "";
    	this.msgKey 	= "";
    	this.css 		= "";
        /*---------------------------------------------------------------------------------*/
    }


	public int doStartTag()  {

		HttpServletRequest 					request = (HttpServletRequest)pageContext.getRequest();
		HttpServletResponse					response = (HttpServletResponse)pageContext.getResponse();
		HttpSession 						session = request.getSession();

		 // Generate the name definition or image element
        StringBuffer results = new StringBuffer();
        MenuSession menuInfo = null;


        try{

        	menuInfo = (MenuSession)request.getAttribute("acessMenu");
        	setDisabled(getDisabled().toLowerCase());

        	/* 요청 Auth(권한속성) 별 버튼 true/fasle 설정 ---------------------------------------*/
        	if(!StringUtils.isEmpty(getAuth()) ) {
        		if(menuInfo != null) {
        			String auth = getAuth().toUpperCase();
	        		if("SELECT".equals(auth)) {
	        			if("Y".equals(menuInfo.getReadAuth()) ) setVisible(false);
	        			else setVisible(true);
	        		}
	        		else if("SAVE".equals(auth) || "INSERT".equals(auth) ){
	        			if("Y".equals(menuInfo.getWriteAuth()) ) setVisible(false);
	        			else setVisible(true);
	        		}
	        		else if("DELETE".equals(auth) ){
	        			if("Y".equals(menuInfo.getDeleteAuth()) ) setVisible(false);
	        			else setVisible(true);
	        		}
	        		else if("EDIT".equals(auth) || "UPDATE".equals(auth)){
	        			if("Y".equals(menuInfo.getEditAuth()) ) setVisible(false);
	        			else setVisible(true);
	        		}
	        		else if("PRINT".equals(auth) || "EXCEL".equals(auth) || "PDF".equals(auth)){
	        			if("Y".equals(menuInfo.getPrintAuth()) ) setVisible(false);
	        			else setVisible(true);
	        		}
	        		else {
	        			setVisible(false);
	        		}
        		}
        		else  setVisible(false);

        		/* value 속성이없을경우
        		 *    -msgKey 없을경우 : 공통 message properties 에 정의된 'button.'+auth  key
        		 *    -msgKey 있을경우 : 설정한 message properties key
        		 *  */
            	if(StringUtils.isEmpty(getValue()))
            	{
            		if(StringUtils.isEmpty(getMsgKey()))
            			 setValue(MessageUtil.getMessage("button."+getAuth()));
            		else setValue(MessageUtil.getMessage(getMsgKey()));
            	}
        	}
        	/*---------------------------------------------------------------------------------*/


        	/*  Disabled 값만으로 버튼을 제어한다 (권한 무시하고 버튼 속성 정의)-------------------- */
        	if(!StringUtils.isEmpty(getDisabled())) {
	        	if("true".equals(getDisabled())) setVisible(true);
        		else if("false".equals(getDisabled())) setVisible(false);
        	}
        	/*---------------------------------------------------------------------------------*/


        	/* class CSS 속성이 없을경우---------------------------------------------------------*/
        	if(StringUtils.isEmpty(this.css) ) {
        		if("print".equals(getAuth()) 	   || "excel".equals(getAuth()) || "pdf".equals(getAuth()) ) this.css ="button btn_white";
        		else if("insert".equals(getAuth()) || "delete".equals(getAuth())|| "save".equals(getAuth())) this.css ="button btn_red";
        		else if("update".equals(getAuth()) || "edit".equals(getAuth())) this.css = "button btn_blue";
        		else this.css="button btn_gray";
        	}
        	/*---------------------------------------------------------------------------------*/

        	/* button type DEFAULT : 'button'*/
        	if(StringUtils.isEmpty(this.type)) {
        		this.type = "button";
        	}
        	/*---------------------------------------------------------------------------------*/

        	/* 버튼 html 생성-------------------------------------------------------------------*/
        	results.append("<input 						  	");
        	results.append("id='"+this.id+"'  			  	");

        	if(!StringUtils.isEmpty(this.name) )
        		results.append("name='"+this.name+"'  		");

        	results.append("type='"+this.type+"'  		  	");
        	results.append("class='"+this.css+"'  		  	");

        	if(!StringUtils.isEmpty(this.objTitle))
        		results.append("title='"+this.objTitle+"'  	");

        	if(!StringUtils.isEmpty(this.style) )
        		results.append("style='"+this.style+"'  	");

        	if(getVisible()) results.append(" disabled 	  	");
        	if(!StringUtils.isEmpty(getEvent()) )
        		results.append( getEvent() 	 );

        	results.append("value='  "+getValue()+"  '    ");
        	results.append(" />							  ");
        	/*---------------------------------------------------------------------------------*/



        	JspWriter out = pageContext.getOut();
        	out.println(results.toString());
			out.flush();

		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}

		setValue(null);
		release();
		return SKIP_BODY;
	}

}
