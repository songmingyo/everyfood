package com.doppio.workplace.sample.service.impl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.ExcelTempUploadVo;
import com.doppio.workplace.sample.service.SampleExcelVo;
import com.doppio.workplace.sample.service.SampleJasperVo;
import com.doppio.workplace.sample.service.SampleVmPdfVo;


@Repository(value = "sampleMapper")
public interface SampleMapper {

	/**
	 * 샘플 엑셀다운로드 데이터 조회 총 갯수
	 * @param searchParam
	 * @return
	 * @throws SQLException
	 */
	public int selectSampleExcelDataCount(SampleExcelVo paramVo) throws SQLException;		
	
	/**
	 * 샘플 엑셀다운로드 데이터 조회
	 * @param paramVo
	 * @return
	 */
	public List<SampleExcelVo> selectSampleExcelData(SampleExcelVo paramVo) throws SQLException;
	
	/**
	 * 샘플 다운로드용 엑셀데이터 조회
	 * @param paramVo
	 * @return
	 */
	public List<HashMap<String, String>> selectSampleExcelDownData(SampleExcelVo paramVo) throws SQLException;
	
	/**
	 * 샘플 기등록 엑셀데이터 삭제
	 * @param paramMap
	 * @throws SQLException
	 */
	public void deleteSampleExcelTemp(HashMap<String, String> paramMap) throws SQLException;	
	
	/**
	 * 샘플 엑셀데이터 등록
	 * @param paramVo
	 * @return
	 */
	public int insertSampleExcelTemp(ExcelTempUploadVo paramVo) throws SQLException;	
	
	/**
	 * 코드 마스터 데이터 조회
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public SampleVmPdfVo selectCodeMst(SampleVmPdfVo paramVo) throws SQLException;
	
	/**
	 * 코드 상세 데이터 조회
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public List<SampleVmPdfVo> selectCodeDtl(SampleVmPdfVo paramVo) throws SQLException;
	
	
	/**
	 * 사용자 정보 조회
	 * @param SampleJasperVo 
	 * @return List<SampleJaserVo>
	 */
	public List<SampleJasperVo> selectUserInfo(SampleJasperVo param);
	

}
