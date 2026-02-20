package org.springframework.tronic.util;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.tronic.exception.BusinessException;

import com.doppio.common.model.Result;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class RestApi {

	private static final Logger logger = LoggerFactory.getLogger(RestApi.class);

    @Value("#{config['batch.url']}")
    public void setId(String value) {
        batchUrl = value;
    }
    static String batchUrl;
    /**
     *
     */
    public static Result interfaceUrl(HashMap<String, Object> interFaceMap) {
        Result result = new Result();
        HttpURLConnection conn = null;
        String resultJSON = "";
        OutputStream os = null;
        URL url;
        String apiURL = batchUrl + "/web/interFaceTest";
        try {
            url = new URL(apiURL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            if(conn != null) {

                //param
                JSONObject param = new JSONObject();

                param.put("applCd",interFaceMap.get("applCd"));
                param.put("extent01",interFaceMap.get("extent01"));

                os = conn.getOutputStream();
                os.write(param.toString().getBytes("UTF-8")); //param
                os.flush();

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                    resultJSON = readBody(conn.getInputStream());
                } else { // 에러 발생
                    resultJSON = readBody(conn.getErrorStream());
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            throw new BusinessException("001", "Connect Exception");
        }

        org.json.simple.parser.JSONParser jsonParse = new org.json.simple.parser.JSONParser();
        org.json.simple.JSONObject obj = null;

        try{
            obj = (org.json.simple.JSONObject) jsonParse.parse(resultJSON);
        }catch(Exception e){
            logger.debug(e.getMessage());
            throw new BusinessException("002", "JsonParser Exception");
        }
        result.setResultCode("success");
//        result.setRtnValue01(String.valueOf(obj.get("id")));
//        result.setRtnValue01(String.valueOf(obj.get("oid")));
        return result;
    }

    public static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
        	logger.debug(e.getMessage());
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    public static String get(String apiUrl, Map<String, String> requestHeaders, String encdoing){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream(), encdoing);
            } else { // 에러 발생
                return readBody(con.getErrorStream(), encdoing);
            }
        } catch (IOException e) {
        	logger.debug(e.getMessage());
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
        	logger.debug(e.getMessage());
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
        	logger.debug(e.getMessage());
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body) throws UnsupportedEncodingException {
        InputStreamReader streamReader = new InputStreamReader(body,"UTF-8");

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
        	logger.debug(e.getMessage());
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

    private static String readBody(InputStream body, String encdoing) throws UnsupportedEncodingException {
        InputStreamReader streamReader = new InputStreamReader(body, encdoing);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }

}
