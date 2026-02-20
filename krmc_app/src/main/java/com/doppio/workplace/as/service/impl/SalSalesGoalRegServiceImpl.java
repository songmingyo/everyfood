package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Field;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.as.service.SalSalesGoalRegService;
import com.doppio.workplace.as.service.SalSalesGoalRegVo;
import com.doppio.workplace.bm.service.PrdtMgmtVo;


/**
 *
 * @Class : SalSalesGoalRegServiceImpl.java
 * @Description : 영업목표 관리 
 * @author : song 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.      song        	  
 *
 * </pre>
 */

@Service("salSalesGoalRegService")
public class SalSalesGoalRegServiceImpl implements SalSalesGoalRegService {
	
	
	@Autowired
	SalSalesGoalRegMapper  salSalesGoalRegMapper;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	/**
	 * @Method : selectSalSalesGoalRegList
	 * @Description : 영업목표 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<SalSalesGoalRegVo>
	 */
	@Override
	public List<SalSalesGoalRegVo> selectSalSalesGoalRegList(SalSalesGoalRegVo paramVo){
		return  salSalesGoalRegMapper.selectSalSalesGoalRegList(paramVo);
	}
	
	
	/**
	 *  영업목표 저장 
	 * @param SalSalesGoalRegVo paramVo
	 * @return SalSalesGoalRegVo
	 * @throws Exception
	 */
	public Result insertSalSalesGoalReg(SalSalesGoalRegVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<SalSalesGoalRegVo> salesGoalList =  paramVo.getSalSalesGoalRegList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || salesGoalList == null || salesGoalList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("등록 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		int insertCnt = 0;		// 신규 생성 카운트
		int updateCnt = 0;		// 기존 수정 카운트
		
		String goalAmt = "";
		
		String goalMon = "";
		
		for(SalSalesGoalRegVo salSalesGoalReg : salesGoalList) {
			
			salSalesGoalReg.setGoalYear(paramVo.getGoalYear());	// 목표년도
			
			salSalesGoalReg.setWorkId(customUser.getUserId());	// 작업자 아이디 
			
			for(int i=1; i<=12; i++) {
				
//				if(i<=9) {
//					goalMon = "0"+i;
//				}else {
//					goalMon = i+"";
//				}				
				
				if(i==1) {
					salSalesGoalReg.setGoalMon("01");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt01());
				}
				if(i==2) {
					salSalesGoalReg.setGoalMon("02");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt02());
				}
				if(i==3) {
					salSalesGoalReg.setGoalMon("03");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt03());
				}
				if(i==4) {
					salSalesGoalReg.setGoalMon("04");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt04());
				}
				if(i==5) {
					salSalesGoalReg.setGoalMon("05");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt05());
				}
				if(i==6) {
					salSalesGoalReg.setGoalMon("06");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt06());
				}
				if(i==7) {
					salSalesGoalReg.setGoalMon("07");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt07());
				}
				if(i==8) {
					salSalesGoalReg.setGoalMon("08");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt08());
				}
				if(i==9) {
					salSalesGoalReg.setGoalMon("09");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt09());
				}
				if(i==10) {
					salSalesGoalReg.setGoalMon("10");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt10());
				}
				if(i==11) {
					salSalesGoalReg.setGoalMon("11");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt11());
				}
				if(i==12) {
					salSalesGoalReg.setGoalMon("12");
					salSalesGoalReg.setGoalAmt(salSalesGoalReg.getGoalAmt12());
				}
				
				salSalesGoalRegMapper.insertSalesGoalReg(salSalesGoalReg);
				
				
				insertCnt++;
			}
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	/**
	 *  영업목표 엑셀 다운로드 
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalSalesGoalRegExcelDown(SalSalesGoalRegVo paramVo) throws Exception {
		return salSalesGoalRegMapper.selectSalSalesGoalRegExcelDown(paramVo);
	}
	
}
