package com.doppio.common.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @Class : EmsCommonService.java
 * @Description : 메일 공통 service  
 * @author : 안석진
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 2. 20.            안석진           최초 생성
 *
 * </pre>
 */
public interface EmsCommonService {

	/**
	 * EMAIL 발송
	 * @param HashMap<String, Object> paramMap
	 * @return null
	 */
	public void emailSendBusiness(HashMap<String, Object> paramMap, HttpServletRequest request) throws Exception;
	
}
