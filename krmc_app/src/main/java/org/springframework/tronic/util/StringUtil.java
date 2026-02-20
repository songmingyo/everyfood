package org.springframework.tronic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.tronic.exception.BusinessException;



public class StringUtil {
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	private  static String keys = "DADAPRODUCTION01038124206";
	private static final char[] alphas = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'X', 'Y', 'V', 'W', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'x', 'y', 'v', 'w', 'z' };
	private static final Random generator = new Random(System.currentTimeMillis());



    public static boolean isNull(String str) {
    	if (str != null) {
        str = str.trim();
    	}

    	return (str == null) || ("".equals(str));
    }

	public static String clobToString(Clob clob) throws SQLException,IOException {

		if (clob == null)  return "";
		StringBuffer strOut = new StringBuffer();

		String str = "";
		BufferedReader br = new BufferedReader(clob.getCharacterStream());

		while ((str = br.readLine()) != null) {
			strOut.append(str);
		}
		return strOut.toString();
	}


	public static String getRandomKey(int length) {
		String strReturn = "";
	    /*char code[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'W', 'X', 'Y', 'Z',
	    			   '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	    int cnt =0;
	    while(length > cnt){
	    	strReturn += String.valueOf(code[(int)(Math.random() * code.length)]).toString();
        	cnt++;
        }*/

		if (length > 0) {
			strReturn = RandomStringUtils.random(length, 48, 91, true, true);
		}

	    return strReturn;
	 }
	
	public static String getRandomKeyOnlyNum(int length) {
		String strReturn = "";
		if (length > 0) {
			strReturn = RandomStringUtils.random(length, false, true);
		}

	    return strReturn;
	}


     public static int string2integer(String str) {
    	 if (isNull(str)) {
    		 return 0;
	 	 }

    	 return Integer.parseInt(str);
     }

    /* 숫자 -> 영문, 베트남어*/
 	/*1~19*/
 	public static final String[] units = { "", "one", "two", "three", "four",
 			"five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
 			"thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
 			"eighteen", "nineteen" };

 	/*10자리수*/
 	public static final String[] tens = {
 			"", 		// 0
 			"",			// 1
 			"twenty", 	// 2
 			"thirty", 	// 3
 			"forty", 	// 4
 			"fifty", 	// 5
 			"sixty", 	// 6
 			"seventy",	// 7
 			"eighty", 	// 8
 			"ninety" 	// 9
 	};


 	/*임시주석 숫자->영문 사이트:[https://www.convertworld.com/ko/numerals/]*/
 	public static String convertNumberToWord(String number, String type) {
 		/*return number To words*/
 	    String rtnWords ;
 	    /*받은 number*/
 	    Double num = Double.parseDouble(number);
 	    String [] numberArray = number.split("\\."); 			//정수, 소수로 나누려는 Array
		long integer ;											//정수
		String decimal;											//소수
		String rtnDecimal ="";									//가공된 소수;
		String gap;												//공백 처리
  		if("EN".equals(type)){	/*영문, 한국어 일때*/
 	    	if(num == 0){
 	    		rtnWords = "zero";
 	    	}else{
 	    		if(number.indexOf(".") > -1){// 소수 존재여부
 	    			integer = Long.parseLong(numberArray[0]);			//정수
 	    			decimal =  numberArray[1];						//소수
 	    			if(Integer.parseInt(decimal) != 0){			//11.00일 경우는 11로 판단
 	    				for(int i = 0; i< decimal.length(); i++){
 	 	    				if("0".equals(decimal.substring(i, i+1))){
 	 	    					rtnDecimal += " zero";
 	 	    				}else{
 	 	    					rtnDecimal += " "+units[Integer.parseInt(decimal.substring(i, i+1))];
 	 	    				}

 	 	    			}
 	    				gap = integer == 0 ? "":" ";
 	    				rtnDecimal =  gap + "point"+  rtnDecimal;
 	    			}
 	    			rtnWords =convertEn(integer) + rtnDecimal;
 	    		}else{
 	    			rtnWords =convertEn(Long.parseLong(number));
 	    		}

 	    	}
 	    }else{ 	/*베트남어*/
 	    	if(num == 0){
 	    		rtnWords = "không";			//[không = 0]
 	    	}else{
 	    		if(number.indexOf(".") > -1){
 	    			integer = Long.parseLong(numberArray[0]);			//정수
 	    			decimal =  numberArray[1];						//소수
 	    			if(Integer.parseInt(decimal) != 0){
 	    				for(int i = 0; i< decimal.length(); i++){

 	 	    				if("0".equals(decimal.substring(i ,  i + 1))){
 	 	    					rtnDecimal += " không";
 	 	    				}else{
 	 	    					rtnDecimal += " "+convertVn(Integer.parseInt(decimal.substring(i,decimal.length())));
 	 	    					break;
 	 	    				}

 	 	    			}
 	    				gap = integer == 0 ? "":" ";
 	    				rtnDecimal =  gap + "chấm"+  rtnDecimal;		//[chấm=point]
 	    			}
 	    			rtnWords =convertVn(integer) + rtnDecimal;
 	    		}else{
 	    			rtnWords =convertVn(Long.parseLong(number));
 	    		}
 	    	}
 	    }
 	    return Character.toUpperCase(rtnWords.charAt(0)) + rtnWords.substring(1);
 	}

 	/*영문으로 convert*/
 	public static String convertEn(long number) {


 		if (number < 0) {
 			return "Minus " + convertEn(-number);
 		}

 		if (number < 20) {
 			return units[(int)number];
 		}

 		if (number < 100) {
 			return tens[(int)number / 10] + ((number % 10 != 0) ? " " : "") + units[(int)number % 10];
 		}

 		if (number < 1000) {
 			return units[(int)number / 100] + " hundred" + ((number % 100 != 0) ? " " : "") + convertEn(number % 100);
 		}

 		if (number < 1000000) {
 			return convertEn(number / 1000) + " thousand" + ((number % 1000 != 0) ? " " : "") + convertEn(number % 1000);
 		}

 	    if (number < 1000000000) {
 			return convertEn(number / 1000000) + " million " + ((number % 1000000 != 0) ? " " : "") + convertEn(number % 1000000);
 	    }

 	    if(number < 1000000000000L){
 	    	return convertEn(number / 1000000000) + " billion " + ((number % 1000000000 != 0) ? " " : "") + convertEn(number % 1000000000);
 	    }

 	    return convertEn(number / 1000000000000L) + " trillion " + ((number % 1000000000000L != 0) ? " " : "") + convertEn(number % 1000000000000L);

 	}

 	/*1자리수*/
 	public static final String[] unitsVn = { "",
 			"một",				//1
 			"hai",				//2
 			"ba",				//3
 			"bốn",				//4
 			"năm",				//5
 			"sáu",				//6
 			"bảy",				//7
 			"tám",				//8
 			"chín"				//9
 			};

 	/*10자리수*/
 	public static final String[] tensVn = {
 			"mười", 			// 10
 			"hai mươi",			// 20
 			"ba mươi",			// 30
 			"bốn mươi",			// 40
 			"năm mươi",			// 50
 			"sáu mươi",			// 50
 			"bảy mươi",			// 70
 			"tám mươi",			// 80
 			"chín mươi",		// 90

 	};

 	/*베트남어으로 convert*/
 	public static String convertVn(long number) {
 		String unitsDigit = "";//1의자리수

 		if (number < 0) {
 			return "- " + convertVn(-number);
 		}

 		if (number < 10) {

 			return unitsVn[(int)number % 10];
 		}
 		if (number < 100) {
 			if(number % 10 == 1 && number > 20){/*21부터 1은 mốt로발음*/
 				unitsDigit = "mốt";
 			}else if(number % 10 != 0 && number % 5 == 0 && 15 <= number){/*15~95 까지 1의자리수가 5일 경우 lăm로발음*/
 				unitsDigit = "lăm";
 			}else{
 				unitsDigit = unitsVn[(int)number % 10];
 			}

 			return tensVn[((int)number / 10)-1] + ((number % 10 != 0) ? " " : "") + unitsDigit;
 		}

 		if (number < 1000) {

 			if(0 < number % 100 && number % 100 < 10){/*10의자리가  0이면 10자리에 lẻ 추가*/
 				unitsDigit  = " lẻ " + unitsVn[(int)number % 10];
 			}else{
 				unitsDigit = ((number % 100 != 0) ? " " : "") + convertVn(number % 100);
 			}
 			return unitsVn[((int)number / 100)] + " trăm" + unitsDigit;
 		}

 		if (number < 1000000) {
 			return convertVn(number / 1000) + " nghìn" + ((number % 1000 != 0) ? " " : "") + convertVn(number % 1000);
 		}

 	    if (number < 1000000000) {
 			return convertVn(number / 1000000) + " triệu" + ((number % 1000000 != 0) ? " " : "") + convertVn(number % 1000000);
 	    }

 	    if(number < 1000000000000L){
 	    	return convertVn(number / 1000000000) + " tỷ" + ((number % 1000000000 != 0) ? " " : "") + convertVn(number % 1000000000);//억
 	    }

 	   return convertVn(number / 1000000000000L) + " nghìn tỷ" + ((number % 1000000000000L != 0) ? " " : "") + convertVn(number % 1000000000000L);//조

 	}




 	public static String getEncrypt( String pStr)  {
		String key = keys;
		byte[] bytsKey = key.getBytes();
		Seedx seed = new Seedx(bytsKey);
		String strEnc = pStr;

		try {
			byte[] strByte = pStr.getBytes();
			byte[] encryptedText = seed.Encrypt(strByte);
			strEnc = new String(Base64.encode(encryptedText),"UTF-8");

		} catch (Exception e) {logger.debug(e.getMessage());}
		return strEnc;
	}


	public static String getDecrypt( String s)  {
		String key = keys;
		byte[] bytsKey = key.getBytes();
		String strDenc = s;
		try {

			byte[] abyte1 = s.getBytes();
			byte[] abyte2 = Base64.decode(abyte1);
			Seedx seed = new Seedx(bytsKey);

			strDenc = new String(seed.Decrypt(abyte2),"UTF-8");
		} catch (Exception e) {logger.debug(e.getMessage());}
		return strDenc;
	}



	public static  String formatValuePattern(String value, String format)
    {
		StringBuffer stringbuffer = new StringBuffer("");

		int i = 0;
		int j = 0;
		boolean flag = false;

		try{
		if(format == null || format.trim().length() <= 0) return value;

	        for(; i < format.length(); i++){
	            if(j >= value.length()) break;

	            char c = format.charAt(i);
	            if(c == '#') {
	                if(flag){
	                    stringbuffer.append("#");
	                }else{
	                    stringbuffer.append(value.charAt(j));
	                    j++;
	                }
	                flag = false;
	            }
	            else{
	            	stringbuffer.append(c);
	            	flag = false;
	            }
	        }

		    if(j < value.length()) stringbuffer.append(value.substring(j));
		}catch(Exception e){
			logger.debug(e.getMessage());
		    stringbuffer.append("");
		}

	    return stringbuffer.toString();
    }

   //String 콤마 찍기(화폐)
   public static String replaceComma(String data)  {

    long convert = Long.parseLong(data);
    DecimalFormat df = new DecimalFormat("#,###");

    String formatNum=(String)df.format(convert);
    return formatNum;

   }

   public static String nvlStr(String orgStr, String initStr)
   {
     if ((orgStr == null) || (orgStr.equals(""))) {
       return initStr;
     }
     return orgStr;
   }
 
   public static String nvlStr(String orgStr)
   {
     return nvlStr(orgStr, "");
   }
 
   public static boolean isNVL(String orgStr)
   {
     return (orgStr == null) || ("".equals(orgStr.trim()));
   }
 
   public static boolean isNotNVL(String orgStr)
   {
     return !isNVL(orgStr);
   }
   
   public static String nvl(String request, String str) {
		if(request == null || request.trim().equals("")|| (request.trim().equals("null"))){
			return str;
		}else{
			return request;
		}
	}

   // null을 공백으로 변환
   public static String nullConvert(Object src){

	   if ((src != null) && ((src instanceof BigDecimal))) {
		   return ((BigDecimal)src).toString();
	   }

	   if ((src == null) || (src.equals("null"))) {
		   return "";
	   }

	   return ((String)src).trim();
   }   
   
   // null을 공백으로 변환
   public static String nullConvert(String src){

	   if ((src == null) || (src.equals("null")) || ("".equals(src)) || (" ".equals(src))) {
		   return "";
	   }

	   return src.trim();
   }
   
   public static String lPad(String str, int len, char pad) {  
	   return lPad(str, len, pad, false);
   }

   public static String lPad(String str, int len, char pad, boolean isTrim){

		if (isNull(str)) {
		  return null;
		}
		
		if (isTrim) {
		  str = str.trim();
		}
		
		for (int i = str.length(); i < len; i++) {
		  str = new StringBuilder().append(pad).append(str).toString();
		}
		
		return str;
   }
	 

   public static String rPad(String str, int len, char pad){
    	return rPad(str, len, pad, false);
   }

   
   public static String rPad(String str, int len, char pad, boolean isTrim){
	   
		if(isNull(str)) {
		  return null;
		}
		
		if(isTrim) {
		  str = str.trim();
		}
		
		for(int i = str.length(); i < len; i++){
		  str = new StringBuilder().append(str).append(pad).toString();
		}
		
		return str;
   }   
   
   /**
    * @Method : numberCheck
    * @Description : 입력받은 숫자의 공백, 콤마, 소수점이하를 제거하고 정합성 및 길이를 체크하여 정상적이면 반환함
    * @param numStr
    * @param name
    * @param maxLength
    * @return String
    * @throws Exception
    */
	public static String numberCheck(String numStr, String name, int maxLength) throws Exception {
		String result = null;
		BigDecimal bd = null;
		
		// 빈 값이면 null return
		if(StringUtil.isNull(numStr)) {
			return null;
		}
				
		// 콤마제거, 공백제거
		numStr = numStr.trim().replaceAll(",", "");
		
		// 콤마,공백을 제거했을 경우 빈 값이면 null return
		if(StringUtil.isNull(numStr)) {
			return null;
		}
		
		try {
			bd = new BigDecimal(numStr);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new BusinessException("", name+ "은(는) 적절하지 않은 값입니다.");
		}
		
		// 소수점 버림(소수점 표시 안됨)
		bd = bd.setScale(0, BigDecimal.ROUND_FLOOR); //버림
		result = bd.toString();
		
		// 자리수 체크
		if(result.length() > maxLength) {
			throw new BusinessException("", name + "이 " + maxLength + "자리를 초과하였습니다.");
		}
		
		return result;
	}
   
	/**
	 * @Method : byteCheck
	 * @Description : 입력받은 문자열과 최대byte를 받아서 유효한지 체크
	 * @param str
	 * @param maxByte
	 * @param name
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean byteCheck(String str, int maxByte, String name) throws Exception {
		boolean result = false;
		int curByte = 0;
		
		// 빈 값 체크
		if(StringUtil.isNull(str)) {
			return true;
		}
		
		// byte 체크
		char[] charArr = str.toCharArray();
		for (char c : charArr) {
			if (c >= '\uAC00' && c <= '\uD7A3') { // 한글이면 2byte 증가
           	curByte += 2; // 2바이트 증가
           } else {
           	curByte++; // 1바이트 증가
           }
		}
		
		// byte 비교
		if(curByte > maxByte) {
			throw new BusinessException("", name + "(이)가 입력 가능한 자리수를 초과 했습니다.");
		}
		
		result = true;
		return result;
	}
	
	public static String telNoFmt(String telNo, String emptyValue) {
		String telNoFmt = "";
		if(StringUtil.isNVL(telNo)) return null;
		
		telNo = telNo.replaceAll("-", "").trim();
		
		if(telNo.length() == 9 || telNo.length() == 10 || telNo.length() == 11) {
			if("02".equals(telNo.substring(0, 2))) { // 서울 지역번호
				if(telNo.length() == 9) { // 02-000-0000
					telNoFmt += telNo.substring(0, 2) + "-";
					telNoFmt += telNo.substring(2, 5) + "-";
					telNoFmt += telNo.substring(5);
					
				}else if(telNo.length() == 10) { // 02-0000-0000
					telNoFmt += telNo.substring(0, 2) + "-";
					telNoFmt += telNo.substring(2, 6) + "-";
					telNoFmt += telNo.substring(6);
					
				}else {
					return emptyValue;
				}
				
			}else {
				if(telNo.length() == 10) { // 000-000-0000
					telNoFmt += telNo.substring(0, 3) + "-";
					telNoFmt += telNo.substring(3, 6) + "-";
					telNoFmt += telNo.substring(6);
					
				}else if(telNo.length() == 11) { // 000-0000-0000
					telNoFmt += telNo.substring(0, 3) + "-";
					telNoFmt += telNo.substring(3, 7) + "-";
					telNoFmt += telNo.substring(7);
					
				}else {
					return emptyValue;
				}
			}
		}else {
			return emptyValue;
		}
		
		return telNoFmt;
	}
	
	public static String bmanNoFmt(String bmanNo, String emptyValue) {
		String bmanNoFmt = "";
		
		if(StringUtil.isNVL(bmanNo)) return null;
		
		bmanNo = bmanNo.replaceAll("-", "").trim();
		
		if(bmanNo.length() == 10) { // 000-00-00000
			bmanNoFmt += bmanNo.substring(0, 3) + "-";
			bmanNoFmt += bmanNo.substring(3, 5) + "-";
			bmanNoFmt += bmanNo.substring(5);
			
		}else {
			return emptyValue;
		}
		
		return bmanNoFmt;
	}
	
	public static boolean isEmpty(String str) {
	     return (str == null) || (str.length() == 0);
    }

	public static boolean isNotEmpty(String str) {
	 return !isEmpty(str);
    }
	
	public static String xssFilter(String str) {
		String result = "";
		result = str;
		result = result.replaceAll("[,]", "&#44;");
		result = result.replaceAll("[=]", "&#61;");
		result = result.replaceAll("\\[", "&#91;");
		result = result.replaceAll("]", "&#93;");
		result = result.replaceAll("\\{", "&#123;");
		result = result.replaceAll("}", "&#125;");
		result = result.replaceAll("\\.", "&#46;");
		return result;
    }
	
	/**
	 * 개인정보 마스킹
	 */
	public static String prsnInfoMsk(String type,String str) {
		/**
		 * W쇼핑 개인정보관리지침에 따른 처리기준
		 * 1. 이름 
		 * 	1) 두글자 - 이름의 두번째 글자 표시 제한
		 *  2) 세글자 - 이름의 두번째 글자 표시 제한
		 *  3) 네글자 - 이름의 두번째,세번째 글자 표시 제한
		 * 
		 * 2. 주민등록번호 : 뒤에서부터 6자리 표시 제한
		 * 3. 연락처 : 4~7번째 자리 표시 제한
		 * 4. 카드번호 : 5~12 번째 자리 표시 제한
		 * 5. 계좌번호 : 뒤에서부터 5자리 표시 제한
		 * 6. 아이디 : 
		 * 	6-1.이메일형식X : 앞에서 2번째 자리 뒤부터 표시 제한
		 *  6-2.이메일형식O : 이메일 마스킹 적용
		 */
		
		String result = "";
		String regex =""; //정규식
		
		if("".equals(StringUtils.defaultString(str))){
			return str;
		}
		
		if("name".equals(type)) { 
			regex = "(^[가-힣]+)$"; // 한글만 (영어, 숫자 포함 이름은 제외)
			
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if(matcher.find()) {
				int length = str.length();
				
				String middleMask = "";
				if(length > 2) {
					middleMask = str.substring(1, length - 1);
				} else {	// 이름이 외자
					middleMask = str.substring(1, length);
				}
				
				String dot = "";
				for(int i = 0; i<middleMask.length(); i++) {
					dot += "*";
				}
				
				if(length > 2) {
					return str.substring(0, 1)
							+ middleMask.replace(middleMask, dot)
							+ str.substring(length-1, length);
				} else { // 이름이 외자 마스킹 리턴
					return str.substring(0, 1)
							+ middleMask.replace(middleMask, dot);
				}
			}
			
			result = str;
			
		}else if("hpNo".equals(type)||"telNo".equals(type)) {
			
			regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";
			
			Matcher matcher = Pattern.compile(regex).matcher(str);
			if(matcher.find()) {
				String target = matcher.group(2);
				int length = target.length();
				char[] c = new char[length];
				Arrays.fill(c, '*');
				
				return str.replace(target, String.valueOf(c));
			}
				
		}else if("email".equals(type)|| "userId".equals(type)) {
				
				regex = "^[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[@]{1}[-A-Za-z0-9_]+[-A-Za-z0-9_.]*[.]{1}[A-Za-z]{1,5}$";
				
				Matcher matcher = Pattern.compile(regex).matcher(str);
				if(matcher.find()) {
					int idx = str.indexOf("@");
					
					String id = str.substring(0,idx); 
					String host =str.substring(idx+1);
					String newId ="";
					String newHost ="";
					
					int idLen = id.length();
					int hostLen = host.length();
							
					if(idLen > 3) {
						char[] c = new char[idLen - 3];
						Arrays.fill(c, '*');
						newId = id.replace(id, id.substring(0,3)+ String.valueOf(c));
//						return str.replace(target, target.substring(0, 3) + String.valueOf(c));
					}else {
						newId = id.replace(id, id.substring(0,1)+"*" +id.substring(2,idLen));
					}
					
					if(hostLen > 3) {
						char[] c = new char[hostLen - 3];
						Arrays.fill(c, '*');
						newHost = host.replace(host, host.substring(0,3)+ String.valueOf(c));
//						return str.replace(target, target.substring(0, 3) + String.valueOf(c));
					}else {
						newHost = host.replace(host, host.substring(0,1)+"*" +host.substring(2,hostLen));
					}
					
					result = newId+"@"+newHost;
				}else {
					regex = "(?<=.{2}).";
					String newId = str.replaceAll(regex, "*");
					result = newId;
				}
			
		}else if("addr".equals(type)) {
			int length = str.length();
			char [] c = new char[length];
			Arrays.fill(c, '*');
			
			return str.replace(str, String.valueOf(c));
			
		}else if("bankNo".equals(type)) {
			int length = str.length();
			String target = "";
			
			if(length > 5) {
				target = str.substring(str.length()-5,length);
			}else {
				return "";
			}
			
			String dot ="";
			for(int i=0; i<target.length(); i++) {
				dot += "*";
			}
			
			return str.replace(target, dot);
		}	
		return result;
	}
	
	public static String isToNumberStr(String strSource, String strReplace) {
		if(StringUtils.isEmpty(strSource)) return strSource;
		if(StringUtils.isEmpty(strReplace)) strReplace ="";
		
		return strSource.replaceAll("[^0-9]", strReplace);
	}
	
	public static String isToNumberStr(String strSource) {
		return isToNumberStr(strSource, null);
	}
	
}