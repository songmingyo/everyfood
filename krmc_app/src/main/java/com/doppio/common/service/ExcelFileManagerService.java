package com.doppio.common.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public interface ExcelFileManagerService {

	public List<HashMap<String, Object>> excelUploadDataList(CommonsMultipartFile mFile) throws Exception;
	
	
}
