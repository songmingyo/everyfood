package com.doppio.common.taglibs.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.doppio.common.security.service.CustomUser;
import com.doppio.common.taglibs.service.impl.CustomTagComboMapper;

public class CustomTagComboService extends TagSupport {
	private static final Logger logger = LoggerFactory.getLogger(CustomTagComboService.class);

	private static final long serialVersionUID = -5054456460755360648L;

	@Autowired
	private CustomTagComboMapper  customTagComboMapper;
	
	/** 속성s */
	private String attr;

	/** Object Name */
	private String objName;

	/** Object ID */
	private String objId;

	/** Object Title */
	private String objTitle;

	/** Form name */
	private String formName;

	/** Componet Type */
	private String comType;			// select , radio, checkbox

	/** selectbox의 optional 속성 */
	private String selSubComType;	// select, option

	/** DB조회 시 parentCode value */
	private String parentID;

	/** DB조회 시 childCode value */
	private String codeID;

	/** DB 조회시 subCode value */
	private String subCode;

	/** DB 조회시 subCode2 value */
	private String subCode2;
	
	/** 선택되어 있는 값 표시**/
	private String selectParam;

	/** 첫번째 기본 옵션의 명칭**/
	private String defName;

	/** 첫번째 기본 옵션의 값**/
	private String defValue;

	/** disabled(true/false)**/
	private String disabled;

	/** class**/
	private String classNm;

	/** width 사이즈 **/
	private String width;
	
	/** width 사이즈 **/
	private String height;

	/** Event */
	private String event;

	/** checkbox , radio 간격 */
	private String space;

	/** 바인드 자료 정보 (Default : TCM_CODE table) CP  **/
	private String dataType;

	/** 코드 + 명칭 / 명칭 */
	private String viewType;

	/** subCode IN /NOT IN 조건 **/
	private String dtlNotIn;
	private String notIn;
	private String notIn2;
	
	/** dtlCode **/
	private String dtlCode;

	
	public String getAttr() {
		return attr;
	}
	public void setAttr(String attr) {
		this.attr = attr;
	}
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getObjTitle() {
		return objTitle;
	}
	public void setObjTitle(String objTitle) {
		this.objTitle = objTitle;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public String getSelSubComType() {
		return selSubComType;
	}
	public void setSelSubComType(String selSubComType) {
		this.selSubComType = selSubComType;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getCodeID() {
		return codeID;
	}
	public void setCodeID(String codeID) {
		this.codeID = codeID;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getSelectParam() {
		return selectParam;
	}
	public void setSelectParam(String selectParam) {
		this.selectParam = selectParam;
	}
	public String getDefName() {
		return defName;
	}
	public void setDefName(String defName) {
		this.defName = defName;
	}
	public String getDefValue() {
		return defValue;
	}
	public void setDefValue(String defValue) {
		this.defValue = defValue;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getClassNm() {
		return classNm;
	}
	public void setClassNm(String classNm) {
		this.classNm = classNm;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getViewType() {
		return viewType;
	}
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	public String getDtlCode() {
		return dtlCode;
	}
	public void setDtlCode(String dtlCode) {
		this.dtlCode = dtlCode;
	}
	public String getSubCode2() {
		return subCode2;
	}
	public void setSubCode2(String subCode2) {
		this.subCode2 = subCode2;
	}
	public String getDtlNotIn() {
		return dtlNotIn;
	}
	public void setDtlNotIn(String dtlNotIn) {
		this.dtlNotIn = dtlNotIn;
	}
	public String getNotIn() {
		return notIn;
	}
	public void setNotIn(String notIn) {
		this.notIn = notIn;
	}	
	public String getNotIn2() {
		return notIn2;
	}
	public void setNotIn2(String notIn2) {
		this.notIn2 = notIn2;
	}
	
	
	/**
     * Release any acquired resources.
     */
    @Override
    public void release() {
        super.release();
        /*required : fall 파라메터 정보는 초기화 필요 -------*/
        this.selectParam 	= null;
        this.defName		= null;
        this.defValue		= null;
        this.event			= null;
        this.width			= null;
        this.attr			= null;
        this.space			= null;
        this.dataType		= null;
        this.disabled		= null;
        this.classNm		= null;
        this.dtlCode		= null;
        this.subCode		= null;
        this.subCode2		= null;        
        this.dtlNotIn		= null;
        this.notIn			= null;
        this.notIn2			= null;        
		/*--------------------------------------------*/
    }


    public int doStartTag(){

    	HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession();

		List<HashMap<String,String>> rsList = null;
		HashMap<String, Object> map = new HashMap<String, Object>();

		Locale locales = LocaleContextHolder.getLocale();
		String localesNm = locales.toString();

		CustomUser customUser = null;

		// 사용자 로그인 정보 설정
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null && !"anonymousUser".equals(auth.getPrincipal())){
        	customUser = (CustomUser)auth.getPrincipal();
        	map.put("memberCd",  customUser.getMemberCd());
        	map.put("masterId", customUser.getMasterId());
        }

        try{
			customTagComboMapper = (CustomTagComboMapper)WebApplicationContextUtils.getWebApplicationContext(
			session.getServletContext()).getBean("customTagComboMapper");
			
			/* Locale 설정으로 언어별 코드값 조회 조건(KO/EN) 언어가 추가 확장될 경우 DB 컬럼 조정이 필요함 */
			map.put("language", localesNm.toUpperCase());

			
			if(!StringUtils.isEmpty(this.parentID)){
				map.put("parentId", this.parentID);
			}
						
			if(!StringUtils.isEmpty(this.dtlCode)){
				String[] dtlCodes = this.dtlCode.split(",");
				if(dtlCodes.length > 1){
					map.put("dtlCodes", dtlCodes);
				}else{
					map.put("dtlCode", this.dtlCode);
				}
			}			
			
			/* 서브코드가 N개 넘어왔을경우 조건을 배열로 처리하여 IN 조건으로 QUERY한다 */
			if(!StringUtils.isEmpty(this.subCode)){
				String[] subCodes = this.subCode.split(",");
				if(subCodes.length > 1){
					map.put("subCodes", subCodes);
				}else{
					map.put("subCode", this.subCode);
				}
			}
			
			/* 서브코드2가 N개 넘어왔을경우 조건을 배열로 처리하여 IN 조건으로 QUERY한다 */
			if(!StringUtils.isEmpty(this.subCode2)){
				String[] subCodes2 = this.subCode2.split(",");
				if(subCodes2.length > 1){
					map.put("subCodes2", subCodes2);
				}else{
					map.put("subCode2", this.subCode2);
				}
			}			

			if(!StringUtils.isEmpty(this.dtlNotIn)){
				map.put("dtlNotIn", this.dtlNotIn);
			}
			
			if(!StringUtils.isEmpty(this.notIn)){
				map.put("notIn", this.notIn);
			}
			
			if(!StringUtils.isEmpty(this.notIn2)){
				map.put("notIn2", this.notIn2);
			}				
			
			if(StringUtils.isEmpty(this.dataType)){
				this.dataType = "EPR";
			}
		
			// DB조회하여 결과 List
			if("YEAR".equals(this.dataType)){
				if(StringUtils.isEmpty(this.subCode)){
					this.subCode = "5" ;
				}
				rsList = getYearData(Integer.parseInt(this.subCode));
			}else if("HOURS".equals(this.dataType)){
				rsList = getHoursData(0, 23);
			}else if ("MINS".equals(this.dataType)){
				rsList = getMinsData();
			}else if("MENU".equals(this.dataType)){
				rsList = customTagComboMapper.selectInternalMenu(map);
			}else if("PRC1".equals(this.dataType)){
				rsList = customTagComboMapper.selectProdtClass1(map);	
			}else if("PRC2".equals(this.dataType)){
				rsList = customTagComboMapper.selectProdtClass2(map);
			}else if("PRC3".equals(this.dataType)){
				rsList = customTagComboMapper.selectWhCd(map);
			}else if("CAR".equals(this.dataType)){
				rsList = customTagComboMapper.selectCarCd(map);
			}else if("HQCD".equals(this.dataType)){
				rsList = customTagComboMapper.selectHqCd(map);
			}else if("SALESPRCD".equals(this.dataType)){
				rsList = customTagComboMapper.selecSalesPrCd(map);
			}else{
				rsList = customTagComboMapper.selectInternalCode(map);
			}

			JspWriter out = pageContext.getOut();

			String html = generatorComponet(comType, rsList);

			out.println(html);
			out.flush();

		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}

		release();
		return SKIP_BODY;
	}



	private List<HashMap<String, String>> getYearData(int year)
	{
		List<HashMap<String, String>> list =  new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hData = null;

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
		String nowYear = sdf.format(new Date());

		int fromYear = Integer.parseInt(nowYear)+1;

		for(int i=1; i<year+1; i++)
		{
			hData =  new HashMap<String, String>();

			hData.put("CODE_ID", String.valueOf(fromYear-i));
			hData.put("CODE_NAME", String.valueOf(fromYear-i));

			list.add(hData);
		}

		return list;
	}

	/**
	 * HTML생성 Method
	 * @param comType
	 * @return 문자열 - 생성된 HTML
	 */
	public String generatorComponet(String comType , List<HashMap<String, String>> rsList )throws Exception{

		HashMap<String, String> rsMap = new HashMap<String, String>();

		StringBuffer htm = new StringBuffer();
		if("SELECT".equals(this.comType) && !"OPTION".equals(this.selSubComType)) {
			if(StringUtils.isEmpty(this.classNm)) this.classNm = "form-control";


			htm.append("<select id="+this.objId+" name=" +this.objName);
			htm.append(" class='"+ this.classNm + "' ");

			if( !StringUtils.isEmpty(this.width)) 			htm.append(" style='width:"+this.width+"' ");
			if( !StringUtils.isEmpty(this.height)) 			htm.append(" style='height:"+this.height+"' ");
			if( !StringUtils.isEmpty(this.objTitle)) 		htm.append(" title='"+this.objTitle+"' ");
			if( !StringUtils.isEmpty(this.event))			htm.append(" " + this.event + " ");
			if( !StringUtils.isEmpty(this.attr))			htm.append(" "+ this.attr +" ");
			if( !StringUtils.isEmpty(this.disabled))		htm.append(" disabled='"+ this.disabled + "' ");

			htm.append(">");


			if(!StringUtils.isEmpty(this.defValue) || !StringUtils.isEmpty(this.defName)){

				if(StringUtils.isEmpty(this.defValue))  this.defValue = "";
				if(StringUtils.isEmpty(this.defName))   this.defName =  "";

				htm.append("<option value='"+this.defValue+"'>"+this.defName+"</option>");
			}

			if(rsList != null)
			{
				for(int i=0; i < rsList.size(); i++){
					rsMap = rsList.get(i);
					htm.append("<option value='" +rsMap.get("CODE_ID").toString().trim()+ "'");
					if(this.selectParam != null && this.selectParam.equals(rsMap.get("CODE_ID").toString().trim()))
						htm.append("selected");

					htm.append(" >"+rsMap.get("CODE_NAME")+"</option>");
				}
			}

			htm.append("</select>");


		}else if("SELECT".equals(this.comType) && !"OPTION".equals(this.selSubComType)) {
			htm.delete(0, htm.length());

			for(int i=0; i < rsList.size(); i++){
				rsMap = rsList.get(i);
				htm.append("<option value='" +rsMap.get("CODE_ID").toString().trim()+ "'>"+rsMap.get("CODE_NAME")+"</option>");
			}

		//radio
		}else if("RADIO".equals(this.comType)){


			for(int i=0; i < rsList.size(); i++){
				rsMap = rsList.get(i);
				htm.append("<input type='radio' id='"+this.objId+(i+1)+"' name='" +this.objName+"' ");
				if(!StringUtils.isEmpty(this.classNm)) htm.append(" class='"+ this.classNm +"' ");
				htm.append("value='"+rsMap.get("CODE_ID").toString().trim()+"' ");
				if(this.event != null)
					htm.append(" " + this.event + " ");
				//if(this.attr != null)
				//	htm.append(" "+ this.attr +" ");

				if(this.selectParam != null){
					if(this.selectParam.equals(rsMap.get("CODE_ID")) )
							htm.append(" checked ");
					else
					{
						if(this.selectParam.equals("0") && i==0 )
							htm.append(" checked ");
					}
				}

				htm.append("> <label for='"+this.objId+(i+1)+"'>"+rsMap.get("CODE_NAME")+" &nbsp;</label>");
				
				int spaceCnt = 0;
				if(!StringUtils.isEmpty(this.space)) {
					try{spaceCnt = Integer.parseInt(this.space); }catch (Exception e) {
						logger.debug(e.getMessage());
						spaceCnt = 0;
					}
				}
				for(int roop=0 ; roop < spaceCnt; roop++)
					htm.append("&nbsp;");
			}

		//checkbox
		}else if("CHECKBOX".equals(this.comType)){

			for(int i=0; i < rsList.size(); i++){
				rsMap = rsList.get(i);

				htm.append("<input type='checkbox'  name='"+this.objName+"' id='"+this.objName+(i+1)+"' value='"+rsMap.get("CODE_ID")+"' ");
				if(!StringUtils.isEmpty(this.classNm))
					htm.append(" class='"+ this.classNm + "' ");

				//if(this.attr != null)
				//	htm.append(" " + this.attr);

				if(this.event != null)
					htm.append(" " + this.event);

				if(this.selectParam != null){
					String selArray[] = this.selectParam.split(",");
					for(int j=0; j < selArray.length; j++) {
						if(selArray[j].equals((String)rsMap.get("CODE_ID"))) htm.append(" checked ");
					}
				}
				
				if(this.disabled != null) {
					htm.append(" disabled='"+ this.disabled + "' ");
				}

				htm.append("/>");
				htm.append("&nbsp;");
				htm.append("<label for='"+this.objName+(i+1)+"'>");
				htm.append(rsMap.get("CODE_NAME"));
				htm.append("</label>");

				int spaceCnt = 3;
				if(!StringUtils.isEmpty(this.space)) {
					try{spaceCnt = Integer.parseInt(this.space); }catch (Exception e) {
						logger.debug(e.getMessage());
						spaceCnt = 3;
					}
				}
				for(int roop=0 ; roop < spaceCnt; roop++)
					htm.append("&nbsp;");

			}
		}

		return htm.toString();
	}

	/**
	 * 시간 comboBox
	 * @param fromHours
	 * @param toHours
	 * @return
	 */
	private List<HashMap<String, String>> getHoursData(int fromHours, int toHours) {
		List<HashMap<String, String>> list =  new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hData = null;
		String hours = "";
		
		for (int i = fromHours; i <= toHours; i++) {
			if (i < 10) {
				hours = String.valueOf("0" + String.valueOf(i));
			} else {
				hours = String.valueOf(i);
			}
			hData =  new HashMap<String, String>();
			
			hData.put("CODE_ID", hours);
			hData.put("CODE_NAME", hours);
			
			list.add(hData);
		}		
		return list;
	}
	
	/**
	 * 분() comboBox
	 * @param fromHours
	 * @param toHours
	 * @return
	 */
	private List<HashMap<String, String>> getMinsData() {
		List<HashMap<String, String>> list =  new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hData = null;
		String min = "";
		
		for (int i = 0; i <= 59; i+=5) {
			min = String.valueOf(i);
			
			if (min.length() < 2) {
				min = String.valueOf("0" + min);
			}
			
			hData =  new HashMap<String, String>();
			
			hData.put("CODE_ID", min);
			hData.put("CODE_NAME", min);
			
			list.add(hData);
		}		
		return list;
	}




}

