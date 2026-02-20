package org.springframework.tronic.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RfcUtil {
	private static final Logger logger = LoggerFactory.getLogger(RfcUtil.class);


	@Autowired
	private Properties config;

	private  Map<String, Object> connectionModuleGet(String restfulUrl, String parameters, Map<String, String> paramMap, int readTimeout) {

		Map<String, Object> resMap = new HashMap<String, Object>();

		HttpURLConnection	conn = null;
		InputStream 		is   = null;
		BufferedReader 		bw	 = null;
		InputStreamReader  wr   = null;
		try{

			//String authId 		= config.getProperty("header.Authorization.id");
			//String authPw 		= config.getProperty("header.Authorization.password");


			// HTTP basic authentication
			//String userpassword = authId + ":" + authPw;
			//String encodedAuthorization = Base64.encodeBase64String(userpassword.getBytes());



			// url 에 접속 계정 사용
			URL url = new URL(restfulUrl+"?"+parameters);
			logger.info("URL :: " + url.toString());
			conn = (HttpURLConnection) url.openConnection();
			logger.info("conn :: " + conn.toString());
			if(readTimeout <=0) readTimeout = 10000;
			// 스트림 리드 타임아웃 10초(10000)
			conn.setReadTimeout(readTimeout);
			//conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty("Authorization", "Basic " + encodedAuthorization);

			//request parameters data
			if(StringUtils.isNotEmpty(parameters)){
				wr = new InputStreamReader(conn.getInputStream());
			}else if(paramMap != null) {
				wr = new InputStreamReader(conn.getInputStream());

			}
			
			
			bw = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			// 응답코드 체크
			if (conn.getResponseCode() != 200) {
				//logger.debug("----->http response code error(" + conn.getResponseCode() + ")");
				if (conn != null) {
					conn.disconnect();
				}

				resMap.put("msgCd", "R03");
				return resMap;
			}


			is = conn.getInputStream();
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));
			
			HashMap<String, Object> dataMap = (HashMap<String, Object>)jsonObject;
			//List<HashMap<String, String>> dataMap = (JSONArray)jsonObject.get("rMap");

			resMap.put("dataMap", dataMap);
			resMap.put("msgCd", (String)jsonObject.get("msgCd")); //response msgCd

		}catch (Exception e) {
			logger.debug(e.getMessage());
			resMap.put("msgCd", "R03");

		} finally {
			try{if (is != null)   {is.close();} }catch (Exception e) {logger.debug(e.getMessage());}
			try{if (bw != null)   {bw.close();} }catch (Exception e) {logger.debug(e.getMessage());}
			try{if (wr != null)   {wr.close();} }catch (Exception e) {logger.debug(e.getMessage());}
			try{if (conn != null) {conn.disconnect();} }catch (Exception e) {logger.debug(e.getMessage());}

		}

		return resMap;

	}
	
	
	private  Map<String, Object> connectionModulePost(String restfulUrl, String parameters, JSONObject paramData, int readTimeout) {

		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("bizCd", "R01");	// 사용자 로그인 BO API 연계  
		resMap.put("msgCd", "-1");	// Default "-1" 기본오류 코드 설정 
		

		HttpURLConnection	conn = null;
		InputStream 		is   = null;
		BufferedWriter 		bw	 = null;
		OutputStreamWriter  wr   = null;
		try{

			//String authId 		= config.getProperty("header.Authorization.id");
			//String authPw 		= config.getProperty("header.Authorization.password");


			// HTTP basic authentication
			//String userpassword = authId + ":" + authPw;
			//String encodedAuthorization = Base64.encodeBase64String(userpassword.getBytes());

			logger.debug("CONNECTION FULL URL : " + restfulUrl);

			// url 에 접속 계정 사용
			URL url = new URL(restfulUrl);
			conn = (HttpURLConnection) url.openConnection();
			
			if(readTimeout <=0) readTimeout = 100000;
			// 스트림 리드 타임아웃 20초(20000)
			conn.setReadTimeout(readTimeout);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty("Authorization", "Basic " + encodedAuthorization);

			logger.debug("CONNECTION SET : ");
			
			//request parameters data
			if(StringUtils.isNotEmpty(parameters)){

				wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(parameters);
				wr.flush();

			}else if(paramData != null) {

				wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(paramData.toString());
				wr.flush();

			}
			
			logger.debug("CONNECTION GET OUTPUTSTREAM  : ");

			//bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
			//bw.flush();


			//logger.debug("----->" + conn.getResponseCode());
			// 응답코드 체크
			if (conn.getResponseCode() != 200) {
				logger.debug("----->http response code error(" + conn.getResponseCode() + ")");
				if (conn != null) {
					conn.disconnect();
				}

				resMap.put("message", "http response code error(" + conn.getResponseCode() + ")");
				resMap.put("msgCd", "-2");
				return resMap;
			}


			is = conn.getInputStream();
			
			logger.debug("CONNECTION GET OUTPUTSTREAM  : ");
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(is, "UTF-8"));

			List<HashMap<String, Object>> dataMapList = (JSONArray)jsonObject.get("rMap");

			if(dataMapList == null) {
				HashMap<String, Object> dataMap = (HashMap<String, Object>)jsonObject;
				resMap.put("dataMap", dataMap);
			} else {
				resMap.put("dataMap", dataMapList);
			}
			
			resMap.put("msgCd", (String)jsonObject.get("msgCd")); //response msgCd

		}catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			resMap.put("msgCd", "-9");

		} finally {
			try{if (is != null)   {is.close();} }catch (Exception e) {logger.debug(e.getMessage());}
			try{if (bw != null)   {bw.close();} }catch (Exception e) {logger.debug(e.getMessage());}
			try{if (wr != null)   {wr.close();} }catch (Exception e) {logger.debug(e.getMessage());}
			try{if (conn != null) {conn.disconnect();} }catch (Exception e) {logger.debug(e.getMessage());}

		}

		return resMap;

	}
	
	/*BO POST*/
	private  Map<String, Object> connectionModulePostNew(String restfulUrl, String parameters, Map<String, String> paramMap, int readTimeout) {

		Map<String, Object> resMap = new HashMap<String, Object>();
		
		resMap.put("bizCd", "R01");	// BO API 연계  
		resMap.put("msgCd", "-1");	// Default "-1" 기본오류 코드 설정 
		
		try {

			HttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(restfulUrl);

			//요청 파라미터 및 프로퍼티 설정
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

			for (Map.Entry<String, String> entry: paramMap.entrySet()) {
			    params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}

			httpPost.addHeader("content-type", "application/x-www-form-urlencoded;charset=utf-8");
			httpPost.addHeader("Accept", "application/json");
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			//응답 받은 데이터
			HttpResponse response = httpClient.execute(httpPost);
			String responseText = EntityUtils.toString(response.getEntity(), "UTF-8");

			if(responseText != null){
    			JSONParser parser = new JSONParser();
    			JSONObject json = (JSONObject) parser.parse(responseText);

    			resMap = (HashMap<String, Object>) new ObjectMapper().readValue(json.toString(), Map.class);
    		}

			System.out.println(resMap.get("code"));
			System.out.println(resMap.get("message"));

		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("msg", "E04");
			resMap.put("message", "RFC 실행에러");
		}

		return resMap;

	}


	/**
	 * WebService
	 * @param restfulPgm
	 * @param parameters
	 * @param readTimeout
	 * @return
	 */
	public  Map<String, Object> rfcConnectionModule(String restfulPgm, String parameters, JSONObject paramData, int readTimeout) {


		String conUrl       = config.getProperty("header.Authorization.url");
		String restfulUrl   = conUrl+restfulPgm;

		return connectionModulePost(restfulUrl,parameters,paramData,readTimeout);

	}
	
	/**
	 * WebService to Interface
	 * @param restfulPgm
	 * @param parameters
	 * @param readTimeout
	 * @return
	 */
	public  Map<String, Object> rfcConnectionModule(String restfulPgm, String parameters, JSONObject paramData, int readTimeout, String method) {


		String conUrl       = config.getProperty("header.Authorization.url");
		String restfulUrl   = conUrl+restfulPgm;

		if("POST".equals(method)) return connectionModulePost(restfulUrl,parameters,paramData,readTimeout);
		else return connectionModuleGet(restfulUrl,parameters,paramData,readTimeout);

	}
	
	/**
	 * WebService to BO
	 * @param restfulPgm
	 * @param parameters
	 * @param readTimeout
	 * @return
	 */
	public  Map<String, Object> rfcConnectionModuleBo(String restfulPgm, String parameters, Map<String, String> paramMap, int readTimeout, String method) {


		String conUrl       = config.getProperty("header.Authorization.bo.url");
		String restfulUrl   = conUrl+restfulPgm;


		if("POST".equals(method)) return connectionModulePostNew(restfulUrl,parameters,paramMap,readTimeout);
		else return connectionModuleGet(restfulUrl,parameters,paramMap,readTimeout);

	}


	/* List<HashMap<String, String>> -> JSONArray */
	public static JSONArray convertListToJson(List<HashMap<String, String>> params) {

		JSONArray jsonArray = new JSONArray();

		for (Map<String, String> map : params) {

			jsonArray.add(convertMapToJson(map));

		}

		return jsonArray;

	}


	/* Map<String, String> -> JSONObject */
	public static JSONObject convertMapToJson(Map<String, String> map) {

		JSONObject json = new JSONObject();

		for (Map.Entry<String, String> entry : map.entrySet()) {

			String key = entry.getKey();

			Object value = entry.getValue();

			json.put(key, value);

		}

		return json;

	}

}
