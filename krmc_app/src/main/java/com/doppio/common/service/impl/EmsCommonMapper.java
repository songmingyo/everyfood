package com.doppio.common.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository(value = "emsCommonMapper")
public interface EmsCommonMapper {

	/**
	 * INSERT TCM_EMS_HEAD
	 * @param paramMap
	 */
	public void insertEmsHeadData(HashMap<String, Object> paramMap);

	/**
	 * INSERT TCM_EMS_DTL
	 * @param paramMap
	 */
	public void insertEmsDetailData(HashMap<String, Object> paramMap);
	
	
	/**
	 * 메일 발송 대상 조회(제안업체명,대표상품명,담당md)  
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String,Object>> selectMailDataMd(HashMap<String, Object> paramMap) throws SQLException;
	
	/**
	 * 메일 발송 대상 조회(제안업체명,대표상품명,입점제안 담당자) 
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String,Object>> selectMailDataPart(HashMap<String, Object> paramMap) throws SQLException;

	/**
	 * 메일 발송 대상 조회(제안업체명,대표상품명,처리기한(#월#일),담당md ,각 진행상태별 처리기한 계산 필요)
	 * @param paramMap
	 * @return
	 * @throws SQLException
	 */
	public List<HashMap<String,Object>> selectMailProcPerdDataMd(HashMap<String, Object> paramMap) throws SQLException;
	
}
