package org.springframework.tronic.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.tronic.exception.BusinessException;

public class DateUtil{
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	
	public static String getAddDate(String strDay){
		
		String format = "";
		Locale locale = LocaleContextHolder.getLocale();
		
		if(Locale.KOREAN.equals(locale)) {
			format =  "yyyy-MM-dd";
		}else {  //베트남, 영어 etc..	
			format =  "dd-MM-yyyy";  
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
	    Date date = new Date();
	    int day = Integer.parseInt(strDay);
	    
	    Calendar cal = new GregorianCalendar(locale);
	   	cal.setTime(date);
	   	cal.add(Calendar.DATE, day);
	    
	   	String rtnDate = simpleDateFormat.format(cal.getTime());
	   	
		return rtnDate;
	}	
	
	
	public static String convertDate(String strSource, String fromDateFormat, String toDateFormat){
		
		SimpleDateFormat simpledateformat = null;
	    Date date = null;

	    if(StringUtils.isEmpty(strSource)){
	    	return "";
	    }
	    
	    strSource = strSource.replaceAll("[^0-9]", "");;

	    if(StringUtils.isEmpty(fromDateFormat)){
	    	if (strSource.length() >= 15) {
	    		strSource = strSource.substring(0, 14);
	    	}
	    	fromDateFormat = "yyyyMMddHHmmss";
	    }
	    
	    if(StringUtils.isEmpty(toDateFormat)){
	    	Locale locale = LocaleContextHolder.getLocale();
	        if(Locale.ENGLISH.equals(locale)){
	        	toDateFormat = "dd-MM-yyyy HH:mm:ss";
	        }
	        if(Locale.KOREAN.equals(locale)){
	        	toDateFormat = "yyyy-MM-dd HH:mm:ss";
	        }else{
	        	toDateFormat = "dd-MM-yyyy HH:mm:ss";
	        }
	    }
	    
	    //data parsing error 방지를 위한 length check
	    if(fromDateFormat.length() != strSource.length()) {
	    	logger.error("date parsing format error : not matched format-data length");
	    	return "";
	    }
	    
	    try{
	    	simpledateformat = new SimpleDateFormat(fromDateFormat, Locale.getDefault());
	    	date = simpledateformat.parse(strSource);

	    	simpledateformat = new SimpleDateFormat(toDateFormat, Locale.getDefault());
	    }catch (Exception exception){
	    	logger.debug(exception.getMessage());
	    }
	    
	    return simpledateformat.format(date);
	}

	
	public static String convertDateLocale(String strSource, String fromDateFormat, String toDateFormat){

		if(StringUtils.isEmpty(strSource)){
			return "";
		}

	    SimpleDateFormat simpledateformat = null;
	    Date date = null;

	    strSource = strSource.replaceAll("[^0-9]", "");

	    Locale locale = LocaleContextHolder.getLocale();

	    if(StringUtils.isEmpty(fromDateFormat)){
	    	fromDateFormat = "yyyyMMdd";
	    }

	    if(StringUtils.isEmpty(toDateFormat)){
	    	if(Locale.ENGLISH.equals(locale)){
	    		toDateFormat =  "dd-MM-yyyy";
	    	}else if(Locale.KOREAN.equals(locale)){
	    		toDateFormat = "yyyy-MM-dd";
	    	}else{
	    		toDateFormat = "dd-MM-yyyy";
	    	}
	    }

	    try{
	    	simpledateformat = new SimpleDateFormat(fromDateFormat, Locale.getDefault());
	    	date = simpledateformat.parse(strSource);
	    	
	    	simpledateformat = new SimpleDateFormat(toDateFormat, Locale.getDefault());
	    }catch(Exception exception){
	    	logger.debug(exception.getMessage());
	    }
	    
	    return simpledateformat.format(date);
	}


	public static String convertDateLocale(String strSource){
		
		SimpleDateFormat simpledateformat = null;
	    Date date = null;

	    String fromDateFormat = "yyyyMMdd";
	    String toDateFormat   = "yyyyMMdd";

	    if(StringUtils.isEmpty(strSource)){
	    	return "";
	    }

	    strSource = strSource.replaceAll("[^0-9]", "");

	    Locale locale = LocaleContextHolder.getLocale();

	    if(Locale.ENGLISH.equals(locale)){
	    	fromDateFormat = "ddMMyyyy";
	    }else if(Locale.KOREAN.equals(locale)){
	    	fromDateFormat = "yyyyMMdd";
	    }else{
	    	fromDateFormat = "ddMMyyyy";
	    }

	    if(StringUtils.isEmpty(fromDateFormat)){
	    	fromDateFormat = "yyyyMMdd";
	    }
	    if(StringUtils.isEmpty(toDateFormat)){
	    	toDateFormat = "yyyyMMdd";
	    }
	    
	    //data parsing error 방지를 위한 length check
	    if(fromDateFormat.length() != strSource.length()) {
	    	logger.error("date parsing format error : not matched format-data length");
	    	return "";
	    }
	    
	    try{
	    	simpledateformat = new SimpleDateFormat(fromDateFormat, Locale.getDefault());
	    	date = simpledateformat.parse(strSource);

	    	simpledateformat = new SimpleDateFormat(toDateFormat, Locale.getDefault());
	    }catch(Exception exception){
	    	logger.debug(exception.getMessage());
	    }
	    
	    return simpledateformat.format(date);
	}

	
	public static String convertDateLocale(String strSource, String type){

		SimpleDateFormat simpledateformat = null;
		Date date = null;
		
		String fromDateFormat = "yyyyMMdd";
		String toDateFormat   = "yyyyMMdd";
		
		if(StringUtils.isEmpty(strSource)){
			return ""; 
		}
		
		strSource = strSource.replaceAll("[^0-9]", "");
		
		Locale locale = LocaleContextHolder.getLocale();
		
		// 언어가 한국어일시 
		if(Locale.KOREAN.equals(locale)) {
			if("Month".equals(type)) {		//년월까지 변환 
				  fromDateFormat = "yyyyMM";
				  toDateFormat = "yyyyMM";
			}
		}else { //영어, 베트남어 등등 (다른 변환이 필요하다면 추가 필요)								
			if("Month".equals(type)) {		//년월까지 변환	
				  fromDateFormat = "MMyyyy";
				  toDateFormat = "yyyyMM";
			}else{
				  fromDateFormat = "ddMMyyyy"; 
			}
		}
		
		try{
			simpledateformat = new SimpleDateFormat(fromDateFormat, Locale.getDefault());
			date = simpledateformat.parse(strSource);

			simpledateformat = new SimpleDateFormat(toDateFormat, Locale.getDefault());
		}catch (Exception exception){
			logger.debug(exception.getMessage());
		}
		
		return simpledateformat.format(date);
	}	  
	  

	public static String getTimeStamp(){
		
		String pattern = "yyyyMMddHHmmssSSS";
		
		return  getTimeStampPattern(pattern);
	}
	
	public static String getTimeStamp(String pattern){
		
		return  getTimeStampPattern(pattern);
	}
	
	private static String getTimeStampPattern(String pattern){
		
		String rtnStr = null;
		
		
		try{
			SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			rtnStr = sdfCurrent.format(Long.valueOf(ts.getTime()));
		}catch(Exception e){
			logger.debug(e.getMessage());
		}
		
		return rtnStr;
	}


	public static String convertDateFmt(String strSource, String fromDateFormat, String toDateFormat){

		if(StringUtils.isEmpty(strSource)){
			return "";
		}

	    SimpleDateFormat simpledateformat = null;
	    Date date = null;

	    strSource = strSource.replaceAll("[^0-9]", "");
	    
	    Locale locale = LocaleContextHolder.getLocale();

	    if(StringUtils.isEmpty(fromDateFormat)){
	    	fromDateFormat = "yyyyMMdd";
	    }

	    if(StringUtils.isEmpty(toDateFormat)){
	    	if(Locale.ENGLISH.equals(locale)){
	    		toDateFormat =  "dd-MM-yyyy";
	    	}else if(Locale.KOREAN.equals(locale)){
	    		toDateFormat =  "yyyy-MM-dd";
	    	}else{
	    		toDateFormat =  "dd-MM-yyyy";
	    	}
	    }
	    
	    //data parsing error 방지를 위한 length check
	    if(fromDateFormat.length() != strSource.length()) {
	    	logger.error("date parsing format error : not matched format-data length");
	    	return "";
	    }

	    try{
	    	simpledateformat = new SimpleDateFormat(fromDateFormat, Locale.getDefault());
	    	date = simpledateformat.parse(strSource);

	    	simpledateformat = new SimpleDateFormat(toDateFormat, Locale.getDefault());
	    }catch(Exception exception){
	    	logger.debug(exception.getMessage());
	    }
	    
	    return simpledateformat.format(date);
	}


	public static String getNowDate(String format){
		//return getDateString(getDate(), format);
		return getDate2String(getDate(), format);
	}
	  
	  
	public static Date getDate(){
		return new Date();
	}
	 

	public static String getNowDate(){
		String format = "";

		Locale locale = LocaleContextHolder.getLocale();
		   
		if(Locale.KOREAN.equals(locale)){
			format = "yyyy-MM-dd";
		}else {  //베트남, 영어 etc..	
			format = "dd-MM-yyyy";  
		}

		return getDate2String(getDate(), format);
	}	
	

	public static String getDateString(Date date, String format){
		
		if(date != null){
			if(StringUtils.isEmpty(format)){
	    	  Locale locale = LocaleContextHolder.getLocale();
	    	  
	    	  if(Locale.ENGLISH.equals(locale)) format =  "dd-MM-yyyy";
	    	  else format =  "yyyy-MM-dd";
			}
	      
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
		
		return null;
	}

	
	public static String getDate2String(Date date, String format){

		if(date != null) {

			if(StringUtils.isNotEmpty(format)) {
				Locale locale = LocaleContextHolder.getLocale();
				
				if("yyyy-MM".equals(format)) {
					if(Locale.KOREAN.equals(locale)) {
						format = "yyyy-MM";
					}else {  //베트남, 영어 etc..	
						format = "MM-yyyy";  
					}
				}
			}

			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}

		return null;
	}	
	
	
	public static String getYearMonthFirstDay() {
		  
		Calendar cal = Calendar.getInstance();
		String rtn = "";

		String year = Integer.toString(cal.get(cal.YEAR));
		String month = Integer.toString(cal.get(cal.MONTH)+1); 
		  
		if(month.length() == 1){
			month = "0" + month;
		}

		Locale locale = LocaleContextHolder.getLocale();
		if(Locale.KOREAN.equals(locale)) {
			rtn = year+"-"+month+"-"+"01";
		}else {  //베트남, 영어 etc..	
			rtn = "01"+"-"+month+"-"+year;
		}
	
		return rtn;
	}
	
	
	public static String getMonthLastDayDate() {
		Calendar calendar = Calendar.getInstance();

		String YYYY = StringUtil.lPad(Integer.toString(calendar.get(1)), 4, '0');
		String MM = StringUtil.lPad(Integer.toString(calendar.get(2) + 1), 2, '0');
		String DD = StringUtil.lPad(Integer.toString(calendar.get(5)), 2, '0');
			
		int intFromDate = Integer.parseInt(DD);
		int intFromMonth = Integer.parseInt(MM);
		int intFtomYear = Integer.parseInt(YYYY);
		    
		Calendar cal = Calendar.getInstance();
			
		cal.set(intFtomYear, intFromMonth-1,intFromDate);
		int newDate = cal.getActualMaximum(Calendar.DATE);    
		DD = StringUtil.lPad(Integer.toString(newDate), 2, '0');
			
		String strDate ="";
			
		Locale locale = LocaleContextHolder.getLocale();
			
		if(Locale.KOREAN.equals(locale)) {
			strDate = YYYY+"-"+MM+"-"+DD;
		}else {  //베트남, 영어 etc..	
			strDate = DD+"-"+MM+"-"+YYYY;
		}
			
		return strDate;
	}	
	
	/**
	 * @Method : dateCheck
	 * @Description : 입력받은 날짜의 유효성을 체크하여 정상적이면 yyyyMMdd 형식으로 반환
	 * @param dateStr
	 * @param name
	 * @return String
	 * @throws Exception
	 */
	public static String dateCheck(String dateStr, String name) throws Exception {
		String result = null;
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // 날짜포맷 setting
		simpleDateFormat.setLenient(false); // 잘못된 형식일 경우 에러를 발생시키는 설정, true이면 정상적인 값으로 바꿔줌
		
		//date null 체크
		if(dateStr == null) {
			return null;
		}
		
		// 공백제거, "-" 제거
		dateStr = dateStr.trim().replaceAll("-", "");
		
		//date 빈값 체크
		if(dateStr.length() == 0) {
			return null;
		}
		
		//date 체크 및 길이 8자리 체크
		if(dateStr.length() != 8) {
			throw new BusinessException("", name + "은(는) 유효한 날짜가 아닙니다.(yyyyMMdd)");
		}
		
		//입력받은 날짜로 변환, 잘못된 입력이면 catch
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			throw new BusinessException("", name + "은(는) 유효한 날짜가 아닙니다.");
		}
		
		result = simpleDateFormat.format(date); // String으로 변환
		return result;
	}
	
	/**
	 * @Method : dateFromToCheck
	 * @Description : 시작날짜, 종료날짜를 비교하여 시작날짜가 종료날짜보다 큰지 체크
	 * @param fromDateStr
	 * @param toDateStr
	 * @param fromName
	 * @param toName
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean dateFromToCheck(String fromDateStr, String toDateStr, String fromName, String toName) throws Exception {
		boolean result = false;
		Date fromDate = null;
		Date toDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // 날짜포맷 setting
		simpleDateFormat.setLenient(false); // 잘못된 형식일 경우 에러를 발생시키는 설정, true이면 정상적인 값으로 바꿔줌
		
		// 유효성 체크
		fromDateStr = DateUtil.dateCheck(fromDateStr, fromName);
		toDateStr = DateUtil.dateCheck(toDateStr, toName);
		
		// date setting
		fromDate = simpleDateFormat.parse(fromDateStr);
		toDate = simpleDateFormat.parse(toDateStr);
		
		// date 비교 - from이 to보다 이후이면 error 발생
		if(fromDate.after(toDate)) {
			throw new BusinessException("", fromName + "이(가) " + toName + "보다 이전이거나 같아야 합니다.");
		}
		
		result = true;
		return result;
	}
	
}
