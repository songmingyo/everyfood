package com.doppio.workplace.sample.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.model.ExcelTempUploadVo;
import com.doppio.workplace.sample.service.SampleExcelVo;
import com.doppio.workplace.sample.service.SampleJasperVo;
import com.doppio.workplace.sample.service.SampleLnchVo;
import com.doppio.workplace.sample.service.SampleService;
import com.doppio.workplace.sample.service.SampleVmPdfVo;


@Service("sampleService")
public class SampleServiceImpl implements SampleService {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);	
	
	@Autowired
	SampleMapper  sampleMapper;

	
    // 파일 DRM 경로
	@Value("#{config['drm.fsdinit.path']}")
	public String fsdinit;

    // 파일 DRM 경로
	@Value("#{config['drm.fsdinit.path.drm']}")
	public String fsdinitdrm;
	

	/**
	 * 샘플 엑셀다운로드 데이터 조회
	 */
	@Override
	public HashMap<String, Object> selectSampleExcelData(SampleExcelVo paramVo) throws Exception{
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();

		int totalCount = sampleMapper.selectSampleExcelDataCount(paramVo);
		// 총갯수를 셋하면 총페이징번호가 계산되고 이후 조회조건에서 사용할 StartRowNo와 EndRowNo도 같이 셋팅함
		paramVo.setTotalCount(totalCount);
		
		List<SampleExcelVo> resultList = new ArrayList<SampleExcelVo>();
		if(totalCount > 0){
			resultList = sampleMapper.selectSampleExcelData(paramVo);
		}
		
		returnMap.put("list", resultList);
		returnMap.put("totalPageCount", paramVo.getPagePerCount());
		returnMap.put("totalCount", totalCount);
		
		return returnMap;
	}	
	
	
	/**
	 * 샘플 엑셀다운로드 데이터 조회
	 */
	@Override
	public List<HashMap<String, String>> selectSampleExcelDownData(SampleExcelVo paramVo) throws Exception{
		return sampleMapper.selectSampleExcelDownData(paramVo);
	}
	


	/**
	 * 엑셀 데이터 Temp테이블 등록
	 */
	private int insertExcelTemp(ExcelTempUploadVo excelTempUploadVo) throws Exception {
		return sampleMapper.insertSampleExcelTemp(excelTempUploadVo);
	}

	/**
	 * 샘플 pdf데이터  
	 */
	@Override
	public HashMap selectSampleReportData(SampleVmPdfVo sampleVmPdfVo) throws Exception {

		HashMap resultMap = new HashMap();
		
		// 코드 마스터 데이터
		SampleVmPdfVo mstData = new SampleVmPdfVo();
		mstData = sampleMapper.selectCodeMst(sampleVmPdfVo);

		// 코드 상세 데이터
		List<SampleVmPdfVo> dtlList = new ArrayList<SampleVmPdfVo>();
		dtlList = sampleMapper.selectCodeDtl(sampleVmPdfVo);

		resultMap.put("data", mstData);
		resultMap.put("listData", dtlList);
		resultMap.put("vmFileNm", "vmSamplePDF.vm");

		return resultMap;
	}
	

	
	/**
	 * Exception 강제 발생시킨다.
	 */
	public void sampleThrowException() throws Exception{
		// Exception 강제  발생시킨다.
		//throw new RuntimeException();
		throw new RuntimeException("asdfasdfasdfasdf");
	}
	
	
	
	/**
	 * 사용자 정보 조회
	 * @param null
	 * @return List<SampleJaserVo>
	 */
	@Override
	public List<SampleJasperVo> selectUserInfo(SampleJasperVo param) {
		return sampleMapper.selectUserInfo(param);
	}
	
}