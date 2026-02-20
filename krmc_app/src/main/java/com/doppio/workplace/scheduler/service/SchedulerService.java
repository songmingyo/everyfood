package com.doppio.workplace.scheduler.service;

import java.util.HashMap;
import java.util.Map;

import com.doppio.common.model.Result;

/**
 * @author Song
 * @Description : 스케줄러 Service interface
 * @Class : SchedulerService
 * <pre> DADA
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  23.06.16   DADA
 * </pre>
 * @version : 1.0
 */

public interface SchedulerService {

	
	/* 사용자 정보 업데이트 sample  test 용 
	 * @param SchedulerSampleVo
	 * @return  Result
	 * @throws Exception
	 * */
	Result updateUserinfoData(SchedulerSampleVo schedulerSampleVo) throws Exception ;
	
	/**
	 * 인터페이스,배치 API 실행로그 생성 
	 * @param InterfaceVo
	 * @return  boolean
	 */
	boolean insertInterFaceResult(InterfaceVo interfaceVo) throws Exception;

	/**
	 * 인터페이스,배치 API 실행로그 갱신 
	 * @param InterfaceVo
	 * @return  boolean
	 */
	boolean updateInterFaceResult(InterfaceVo interfaceVo) throws Exception;
	
	
	/**
	 * Batch 실행 마감 SP  (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	void insertBatchCloseJobSp(BatchCommVo paramMap) throws Exception;
	
	
	/**
	 * Batch 실행 Firmbanking SP  (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	void insertBatchFirmBankingSp(BatchCommVo paramMap) throws Exception;
		
	
}
