package com.doppio.workplace.scheduler.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.controller.DeliVehicleController;
import com.doppio.workplace.scheduler.service.BatchCommVo;
import com.doppio.workplace.scheduler.service.InterfaceVo;
import com.doppio.workplace.scheduler.service.SchedulerSampleVo;
import com.doppio.workplace.scheduler.service.SchedulerService;

@Service("schedulerService")
public class SchedulerServiceImpl implements SchedulerService  {
	
	
	@Autowired
	SchedulerMapper schedulerMapper;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	

	/* 사용자 정보 업데이트 sample  test 용 
	 *   1. 사용자정보 업데이트 
	 *   2. 결과 Return
	 * @param SchedulerSampleVo
	 * @return  Result
	 * @throws Exception
	 * */
	@Override
	public Result updateUserinfoData(SchedulerSampleVo schedulerSampleVo) throws Exception {
		
		Result result = new Result();
		result.setMsgCd("E99");
		result.setMessage("BaseInfo User Login I/F  System error has occurred.");
		
		int resCnt = 0;
				
		try {
			resCnt = schedulerMapper.updateUserinfoData(schedulerSampleVo);
	
			result.setMsgCd("S00");
			result.setMessage("SUCCESS");
			result.setRtnValue01(String.valueOf(resCnt));
		}catch (Exception e) {
			logger.error("updateUserinfoData  UPDATE ERROR :" + e.getMessage());
			
			result.setMsgCd("E98");
			result.setMessage("updateUserinfoData - "+ e.getMessage());
		}
		
		return result;
	}
	
	/**
	 * 인터페이스,배치  API 실행로그 생성 
	 * @param InterfaceVo
	 * @return  boolean
	 */
	public boolean insertInterFaceResult(InterfaceVo interfaceVo) throws Exception {
		boolean rtn =  false;
		try {
			int resCnt = schedulerMapper.insertInterFaceResult(interfaceVo);
			if(resCnt >0) rtn = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rtn;
	}
	
	
	/**
	 * 인터페이스,배치 API 실행로그 생성 
	 * @param InterfaceVo
	 * @return  boolean
	 */
	public boolean updateInterFaceResult(InterfaceVo interfaceVo) throws Exception {
		boolean rtn =  false;
		try {
			int resCnt = schedulerMapper.updateInterFaceResult(interfaceVo);
			if(resCnt >0) rtn = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rtn;
	}

	
	/**
	 * Batch 실행 SP (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	public void insertBatchCloseJobSp(BatchCommVo paramMap){
		
		for(HashMap<String, String > parms : paramMap.getParamListMap()) {
			
			paramMap.setParamData01(parms.get("YEARMONTH"));
			
			schedulerMapper.insertBatchCloseJobSp(paramMap);
			
			logger.info("RESULT MESSAGE : "+paramMap.getSpMessage() );
			logger.info("RESULT CODE : "+paramMap.getSpRspnsCd() );
		}
	
	}
	
	
	/**
	 * Batch 실행 SP (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	public void insertBatchFirmBankingSp(BatchCommVo paramMap){
		
		for(HashMap<String, String > parms : paramMap.getParamListMap()) {
			
			paramMap.setParamData01(parms.get("DATEPARAM"));
			
			schedulerMapper.insertBatchFirmBankingSp(paramMap);
			
			if("00".equals(paramMap.getSpRspnsCd())) {
				schedulerMapper.insertFirmBankingConfirmSp(paramMap);
			}
			
			logger.info("RESULT MESSAGE : "+paramMap.getSpMessage() );
			logger.info("RESULT CODE : "+paramMap.getSpRspnsCd() );
		}	
	}
	
}

