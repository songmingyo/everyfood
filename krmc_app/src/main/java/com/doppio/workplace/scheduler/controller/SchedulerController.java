package com.doppio.workplace.scheduler.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.util.DateUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.management.service.MgrInterfaceVo;
import com.doppio.workplace.scheduler.service.BatchCommVo;
import com.doppio.workplace.scheduler.service.InterfaceVo;
import com.doppio.workplace.scheduler.service.SchedulerSampleVo;
import com.doppio.workplace.scheduler.service.SchedulerService;




/**
 * @author dada
 * @Description :
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2023.06.15    DADA	   
 * </pre>
 * @version : 
 */
@Controller(value = "schedulerController")
public class SchedulerController {

	private static final Logger logger = LoggerFactory.getLogger(SchedulerController.class);
	
	/* BATCH 실행여부 지정 Y:BATCH실행, N:비실행 */
	@Value("#{config['batch.process.execute']}")
	public String batchProcYn;

	@Autowired
	SchedulerService schedulerService;
	
	/*  베치실행  */
	/* n분 간격으로 실행  */
	/* seconds ,  minutes ,hours ,day of month , month , day of week , years (optional) */
	@Scheduled(cron="0 0 01 * * ?")	// 매일 01 배치 실행(운영)
	public void insertBatchCloseJobSpBatch() throws Exception {
		
		if (!"Y".equals(batchProcYn)) return;	//프로퍼티 정의 
		
		logger.info("================== Start Batch -  insertBatchCloseJobSpBatch  ====================");
		
		/* param  list 변수 처리 --------------------------------------------------------------*/
		HashMap<String, String> paramsMap = null;
		List<HashMap<String, String>> paramlist = new ArrayList<HashMap<String, String>>();
		
		LocalDate currentDate = LocalDate.now();
		
		// 현재 월과 전월을 가져옵니다.
        LocalDate currentMonth = currentDate.withDayOfMonth(1);
        LocalDate lastMonth1 = currentMonth.minusMonths(1);
        LocalDate lastMonth2 = currentMonth.minusMonths(2);
        LocalDate lastMonth3 = currentMonth.minusMonths(3);
        LocalDate lastMonth4 = currentMonth.minusMonths(4);
        LocalDate lastMonth5 = currentMonth.minusMonths(5);
        LocalDate lastMonth6 = currentMonth.minusMonths(6);
        LocalDate lastMonth7 = currentMonth.minusMonths(7);
        LocalDate lastMonth8 = currentMonth.minusMonths(8);
        LocalDate lastMonth9 = currentMonth.minusMonths(9);
        LocalDate lastMonth10 = currentMonth.minusMonths(10);
        
        // 형식을 지정하여 출력합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
        String currentMonthFormatted = currentMonth.format(formatter);
        String lastMonthFormatted1 = lastMonth1.format(formatter);
        String lastMonthFormatted2 = lastMonth2.format(formatter);
        String lastMonthFormatted3 = lastMonth3.format(formatter);
        
        String lastMonthFormatted4 = lastMonth4.format(formatter);
        String lastMonthFormatted5 = lastMonth5.format(formatter);
        String lastMonthFormatted6 = lastMonth6.format(formatter);
        String lastMonthFormatted7 = lastMonth7.format(formatter);
        String lastMonthFormatted8 = lastMonth8.format(formatter);
        String lastMonthFormatted9 = lastMonth9.format(formatter);
        String lastMonthFormatted10 = lastMonth10.format(formatter);
		
		/*Params put list --*/
//        paramsMap  = new HashMap<String, String>();
//		paramsMap.put("YEARMONTH", lastMonthFormatted10);
//		paramlist.add(paramsMap);
        
//        paramsMap  = new HashMap<String, String>();
//		paramsMap.put("YEARMONTH", lastMonthFormatted9);
//		paramlist.add(paramsMap);
        
//        paramsMap  = new HashMap<String, String>();
//		paramsMap.put("YEARMONTH", lastMonthFormatted8);
//		paramlist.add(paramsMap);
        
//        paramsMap  = new HashMap<String, String>();
//		paramsMap.put("YEARMONTH", lastMonthFormatted7);
//		paramlist.add(paramsMap);
        
//       paramsMap  = new HashMap<String, String>();
//		paramsMap.put("YEARMONTH", lastMonthFormatted6);
//		paramlist.add(paramsMap);
        
//        paramsMap  = new HashMap<String, String>();
//		paramsMap.put("YEARMONTH", lastMonthFormatted5);
//		paramlist.add(paramsMap);
        
        paramsMap  = new HashMap<String, String>();
		paramsMap.put("YEARMONTH", lastMonthFormatted4);
		paramlist.add(paramsMap);
        
        paramsMap  = new HashMap<String, String>();
		paramsMap.put("YEARMONTH", lastMonthFormatted3);
		paramlist.add(paramsMap);
		
		paramsMap  = new HashMap<String, String>();
		paramsMap.put("YEARMONTH", lastMonthFormatted2);
		paramlist.add(paramsMap);
		
		paramsMap  = new HashMap<String, String>();
		paramsMap.put("YEARMONTH", lastMonthFormatted1);
		paramlist.add(paramsMap);
		
		paramsMap  = new HashMap<String, String>();
		paramsMap.put("YEARMONTH", currentMonthFormatted);
		paramlist.add(paramsMap);
		
		
		BatchCommVo params = new BatchCommVo();
		params.setParamListMap(paramlist);
		/*-----------------------------------------------------------------------------------*/
		logger.info("-----------------------------------------------------------");
		schedulerService.insertBatchCloseJobSp(params);
		
		
		logger.info("================== End   Batch -  insertBatchCloseJobSpBatch  ====================");
	}
	
	
	
	
	/*  베치실행  */
	/* n분 간격으로 실행  */
	/* seconds ,  minutes ,hours ,day of month , month , day of week , years (optional) */
//	펌뱅킹 데이터에 대한 처리 배치
	@Scheduled(cron="0 0/5 * * * ?")	// 15분마다. 배치 실행(운영)
	public void insertBatchFirmBankingBatch() throws Exception {
		
		if (!"Y".equals(batchProcYn)) return;	//프로퍼티 정의 
		
		logger.info("================== Start Batch -  insertBatchFirmBankingBatch  ====================");
		
		/* param  list 변수 처리 --------------------------------------------------------------*/
		HashMap<String, String> paramsMap = null;
		List<HashMap<String, String>> paramlist = new ArrayList<HashMap<String, String>>();
		
		LocalDate currentDate = LocalDate.now();
		
		LocalDate yesterdayDate = currentDate.minusDays(1);
		
		// 형식을 지정하여 출력합니다.
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDateFormatted = yesterdayDate.format(formatterDate);
        
        paramsMap  = new HashMap<String, String>();
		paramsMap.put("DATEPARAM", currentDateFormatted);
		paramlist.add(paramsMap);
		
		BatchCommVo params = new BatchCommVo();
		params.setParamListMap(paramlist);
		/*-----------------------------------------------------------------------------------*/
		logger.info("-----------------------------------------------------------");
		schedulerService.insertBatchFirmBankingSp(params);
		
		
		logger.info("================== End   Batch -  insertBatchCloseJobSpBatch  ====================");
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/app/mgr/manager/closeJobSpBatchMenual", method = RequestMethod.POST, headers = "Accept=application/json")
	public Result insertBatchCloseJobSpBatchMenual(@RequestBody BatchCommVo params, HttpServletRequest req) throws Exception {	
	
		Result result 		= new Result();
		result.setMsgCd("E99");
		result.setMessage("Undefined system error");
		
		String dtlCd = params.getDtlCd();
		
		/* param  list 변수 처리 --------------------------------------------------------------*/
		HashMap<String, String> paramsMap = null;
		List<HashMap<String, String>> paramlist = new ArrayList<HashMap<String, String>>();
		
		if("IFA1001".equals(dtlCd)) {
			LocalDate currentDate = LocalDate.now();
			
			// 현재 월과 전월을 가져옵니다.
	        LocalDate currentMonth = currentDate.withDayOfMonth(1);
	        LocalDate lastMonth1 = currentMonth.minusMonths(1);
	        LocalDate lastMonth2 = currentMonth.minusMonths(2);
	        LocalDate lastMonth3 = currentMonth.minusMonths(3);
	        
	        // 형식을 지정하여 출력합니다.
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	        String currentMonthFormatted = currentMonth.format(formatter);
	        String lastMonthFormatted1 = lastMonth1.format(formatter);
	        String lastMonthFormatted2 = lastMonth2.format(formatter);
	        String lastMonthFormatted3 = lastMonth3.format(formatter);
			
			/*Params put list --*/
	        paramsMap  = new HashMap<String, String>();
			paramsMap.put("YEARMONTH", lastMonthFormatted3);
			paramlist.add(paramsMap);
			
			paramsMap  = new HashMap<String, String>();
			paramsMap.put("YEARMONTH", lastMonthFormatted2);
			paramlist.add(paramsMap);
			
			paramsMap  = new HashMap<String, String>();
			paramsMap.put("YEARMONTH", lastMonthFormatted1);
			paramlist.add(paramsMap);
			
			paramsMap  = new HashMap<String, String>();
			paramsMap.put("YEARMONTH", currentMonthFormatted);
			paramlist.add(paramsMap);
			
			
			params.setParamListMap(paramlist);
			/*-----------------------------------------------------------------------------------*/
			
			
			logger.info("================== Start MENUAL -  insertBatchCloseJobSpBatch  ====================");
			
			schedulerService.insertBatchCloseJobSp(params);	
			
		}else if("IFA1002".equals(dtlCd)) {
			LocalDate currentDate = LocalDate.now();
			
			LocalDate yesterdayDate = currentDate.minusDays(1);
			
			// 형식을 지정하여 출력합니다.
	        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyyMMdd");
	        String currentDateFormatted = yesterdayDate.format(formatterDate);
	        
	        paramsMap  = new HashMap<String, String>();
			paramsMap.put("DATEPARAM", currentDateFormatted);
			paramlist.add(paramsMap);
			
			params.setParamListMap(paramlist);

			schedulerService.insertBatchFirmBankingSp(params);
		}
		
		result.setMsgCd(params.getSpRspnsCd());
		result.setMessage(params.getSpMessage());
		
		
		logger.info("================== End  MENUAL -  insertBatchCloseJobSpBatch  ====================");
		
		return result;
		
	}
	

	
}
