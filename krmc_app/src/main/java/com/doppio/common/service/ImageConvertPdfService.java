package com.doppio.common.service;

import java.util.Map;

public interface ImageConvertPdfService {

	/**
	 * Convert Images to Pdf
	 * @param String
	 * @return String
	 */
	public String convertImageTopdf(Map<String, Object> pMap) throws Exception;

}
