package com.doppio.workplace.scheduler.service.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.scheduler.service.BatchCommVo;
import com.doppio.workplace.scheduler.service.InterfaceVo;
import com.doppio.workplace.scheduler.service.SchedulerSampleVo;

/**
 * 
 * @Class : SchedulerMapper.java
 * @Description : 배치 스케줄러 공통 Mapper
 * @author : DADA
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 16.      DADA        	  
 *
 * </pre>
 */

@Repository(value = "schedulerMapper")
public interface SchedulerMapper {

	/**
	 * 사용자정보 업데이트 (휴면 처리 Sample 테스트용   ) 
	 * @param SchedulerSampleVo
	 * @return Integer
	 */
	public int updateUserinfoData(SchedulerSampleVo schedulerSampleVo);
	
	
	
	/**
	 * 인터페이스,배치 실행로그 생성 
	 * @param InterfaceVo
	 * @return Integer
	 */
	public int insertInterFaceResult(InterfaceVo interfaceVo);
	
	/**
	 * 인터페이스,배치 실행로그 갱신
	 * @param InterfaceVo
	 * @return Integer
	 */
	public int updateInterFaceResult(InterfaceVo interfaceVo);
	
	
	

	/**
	 * Batch 실행 마감 SP (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	public BatchCommVo  insertBatchCloseJobSp(BatchCommVo paramMap);
	
	
	/**
	 * Batch 실행 펌뱅킹 SP (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	public BatchCommVo  insertBatchFirmBankingSp(BatchCommVo paramMap);
	
	/**
	 * 펌뱅킹 데이터 실 테이블 적용 SP (저장 프로시저 )
	 * @param HashMap
	 * @return HashMap
	 */
	public BatchCommVo  insertFirmBankingConfirmSp(BatchCommVo paramMap);

	
}
