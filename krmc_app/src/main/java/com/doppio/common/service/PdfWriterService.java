package com.doppio.common.service;

import java.util.Map;

public interface PdfWriterService {

	/**
	 * PDF에 사인패드 추가
	 * @param Map
	 * @return null
	 */
	public void addSignPadToPdf(Map<String, Object> map) throws Exception;

}
