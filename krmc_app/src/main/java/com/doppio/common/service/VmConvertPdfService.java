package com.doppio.common.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;

/**
 * @Class : VmConvertPdfService
 * @Description : VM파일 변환 관련 서비스 인터페이스
 * @author : 김동호
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *  수정일				수정자			 수정내용
 *  ----------------	------------	---------------------------
 *  2021. 10. 18.		김동호			최초 생성
 *  2022. 04. 20.		김동호			(추가) VM Convert To HTML
 *
 * </pre>
 */
public interface VmConvertPdfService {

	
	/**
	 * VM CONVERT TO PDF 
	 * @param Map
	 * @return null
	 */
	public void vmConvertToPdf(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * VM CONVERT TO PDF FILE CREATE
	 * @param Map
	 * @return null
	 */
	public void vmConvertToPdfFileCreate(Map<String, Object> map, HttpServletRequest request) throws Exception;

	/**
	 * @Method : vmFileValidation
	 * @Description : VM 유효성 검사
	 * @param attachFileVo
	 * @return Result
	 * @throws Exception
	 */
	public Result vmFileValidation(AttachFileVo attachFileVo, HttpServletRequest request) throws Exception;

	/**
	 * @Method : vmConvertToHtml
	 * @Description : VM Convert To HTML
	 * @param map
	 * @param request
	 * @param response
	 * @return String
	 * @throws Exception
	 */
	public String vmConvertToHtml(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
}
