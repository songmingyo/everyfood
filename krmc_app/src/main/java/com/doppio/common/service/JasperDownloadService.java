package com.doppio.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface JasperDownloadService {

	/**
	 * Jasper PDF 다운로드
	 * @param Map
	 * @return null
	 */
	public void jasperDocumentDownload(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public void jasperDocumentDownloadMulit(List<Map<String, Object>> list, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
