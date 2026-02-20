package com.doppio.workplace.sample.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.doppio.common.model.ExcelTempUploadVo;


public interface SampleService {

	/**
	 * 샘플 엑셀다운로드 데이터 조회
	 * @param paramVo
	 * @return
	 */
	public HashMap<String, Object> selectSampleExcelData(SampleExcelVo paramVo) throws Exception;
	
	/**
	 * 다운로드용 샘플 엑셀다운로드 데이터 조회
	 * @return
	 */
	public List<HashMap<String, String>> selectSampleExcelDownData(SampleExcelVo paramVo) throws Exception;

	/**
	 * 샘플 레포트 데이터 조회
	 * @param sampleVmPdfVo
	 * @return
	 * @throws Exception
	 */
	public HashMap selectSampleReportData(SampleVmPdfVo sampleVmPdfVo) throws Exception;	
	
	
	/**
	 * SAMPLE Exception 강제 발생
	 * @throws Exception
	 */
	public void sampleThrowException() throws Exception;
	
	
	/**
	 * 사용자 정보 조회
	 * @param null
	 * @return List<SampleJaserVo>
	 */
	public List<SampleJasperVo> selectUserInfo(SampleJasperVo param);
	
}
