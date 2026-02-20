package org.springframework.tronic.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class XSSFilterWrapper extends HttpServletRequestWrapper{

	   private byte[] bodyData;
	   public XSSFilterWrapper(HttpServletRequest servletRequest)throws IOException {
	      super(servletRequest);
	   }

	   @Override
	   public String[] getParameterValues(String parameter) {
	      String[] values = super.getParameterValues(parameter);

	      if (values==null){
	         return null;
	      }

	      int count = values.length;
	      String[] encodedValues = new String[count];

	      for (int i = 0; i < count; i++) {
	         encodedValues[i] = cleanXSS(values[i]);
	      }

	      return encodedValues;
	   }


	   @Override
	   public String getParameter(String parameter) {

	      String value = super.getParameter(parameter);
	      if (value == null){
	         return null;
	      }
	      return cleanXSS(value);
	   }


	   @Override
	   public Map getParameterMap() {

	      return super.getParameterMap();
	   }


	   @Override
	   public ServletInputStream getInputStream() throws IOException {

	      String body = null;
	      StringBuilder stringBuilder = new StringBuilder();
	      BufferedReader bufferedReader = null;

	      InputStream is           = null;
	      ByteArrayInputStream bis = null;
	      ByteArrayInputStream bois = null;

	      try{
	         is =super.getInputStream();
	         // Content-Type 을 확인해서 첨부파일이면 XSS를 적용하지 않는다.
	         String sType = getHeader("Content-Type");
	         
	         if(sType == null || !sType.toLowerCase().startsWith("multipart/form-data"))
	         {// 첨부파일이 아니고,게시글 

	            //inputString byte[] 배열로 담는다
	            bodyData = IOUtils.toByteArray(is);
	            bis = new ByteArrayInputStream(bodyData);

	            /*===http request body String으로 변환================================*/
	            if (bis != null) {
	               bufferedReader = new BufferedReader(new InputStreamReader(bis,"UTF-8"));

	               char[] charBuffer = new char[128];
	               int bytesRead = -1;

	               while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                  stringBuilder.append(charBuffer, 0, bytesRead);
	               }

	            }


	            body = cleanXSS(stringBuilder.toString());
	            /*====================================================================*/

	            // 변환 된 Strig을 inputStream으로 변환
	            is = IOUtils.toInputStream(body,"UTF-8");
	         }

	         bodyData = IOUtils.toByteArray(is);
	         bois = new ByteArrayInputStream(bodyData);

	      } catch(IOException ex){
	         throw ex;
	      } finally{
	         if (is != null) { try {is.close();} catch (Exception ex) {}}
	         if (bis != null) { try {bis.close();} catch (Exception ex) {}}
	         if (bufferedReader != null) { try {bufferedReader.close();} catch (Exception ex) {}}
	      }

	      return new ServletImpl(bois);

	   }


	   @Override
	   public String getHeader(String name) {

	      String value = super.getHeader(name);
	      if (value == null)
	         return null;
	      return cleanXSS(value);

	   }

	   private String cleanXSS(String value) {
	      /*
	      //You'll need to remove the spaces from the html entities below
	      value = value.replaceAll("<", "＜");
	      value = value.replaceAll(">", "＞");
	      // value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");


	      value = value.replaceAll("script", "");
	      value = value.replaceAll("SCRIPT", "");
	      value = value.replaceAll("iframe", "");
	      value = value.replaceAll("IFRAME", "");


	      value = value.replaceAll("eval\\((.*)\\)", "");
	      value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	       */
	       //value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
	        //value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
	        //value = value.replaceAll("'", "& #39;");
	        //value = value.replaceAll("eval\\((.*)\\)", "");
	        //value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	        //value = value.replaceAll("script", "");


	      value = value.replaceAll("<", " ");
	      value = value.replaceAll(">", " ");
	      value = value.replaceAll("script", "");
	      value = value.replaceAll("iframe", "");
//	      value = value.replaceAll("\\(", "（").replaceAll("\\)", "）");



	      //value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

	      value = value.replaceAll("eval\\((.*)\\)", "");
	      value = value.replaceAll("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']", "\"\"");


	      return value;


	   }
	
}
