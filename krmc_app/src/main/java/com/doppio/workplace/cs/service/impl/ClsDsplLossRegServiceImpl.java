package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.workplace.cs.service.ClsDsplLossRegService;
import com.doppio.workplace.cs.service.ClsDsplLossRegVo;


/**
 *
 * @Class : ClsDsplLossRegServiceImpl.java
 * @Description : 폐기/로스등록 관리 
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

@Service("clsDsplLossRegService")
public class ClsDsplLossRegServiceImpl implements ClsDsplLossRegService {
	
	
	@Autowired
	ClsDsplLossRegMapper  clsDsplLossRegMapper;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	/**
	 * @Method : selectClsDsplLossRegList
	 * @Description : 폐기/로스등록 대상품목 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<ClsDsplLossRegVo>
	 */
	@Override
	public List<ClsDsplLossRegVo> clsDsplLossRegPrdtList(ClsDsplLossRegVo paramVo){
		return  clsDsplLossRegMapper.clsDsplLossRegPrdtList(paramVo);
	}
	
	
	/**
	 * @Method : selectClsDsplLossRegDetailList
	 * @Description : 폐기/로스등록 내역 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<ClsDsplLossRegVo>
	 */
	@Override
	public List<ClsDsplLossRegVo> selectClsDsplLossRegDetailList(ClsDsplLossRegVo paramVo){
		
		return  clsDsplLossRegMapper.selectClsDsplLossRegDetailList(paramVo);
	}
	
	/**
	 *  폐기/로스등록 하나의 품목만 조회 
	 * @param SalSalesGoalRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectClsDsplLossRegPrdtAdd(ClsDsplLossRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		ClsDsplLossRegVo clsDsplLossRegVo = clsDsplLossRegMapper.selectClsDsplLossRegPrdtAdd(paramVo);
		
		resultList.put("clsDsplLossRegVo", clsDsplLossRegVo);
		
		return resultList;
		
	}	
	
	/**
	 *  폐기/로스등록 저장 
	 * @param ClsDsplLossRegVo paramVo
	 * @return ClsDsplLossRegVo
	 * @throws Exception
	 */
	public Result insertClsDsplLossReg(ClsDsplLossRegVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<ClsDsplLossRegVo> dspLossList =  paramVo.getClsDsplLossRegList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || dspLossList == null || dspLossList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("등록 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		int insertCnt = 0;		// 신규 생성 카운트
		int updateCnt = 0;		// 기존 수정 카운트
		
		int dspLossListCnt = dspLossList.size();
		
		String buySlipNo = "";
		
		if(dspLossListCnt >= 1) {
			buySlipNo = dspLossList.get(dspLossListCnt-1).getBuySlipNo();
		}
		
		for(ClsDsplLossRegVo clsDsplLossRegVo : dspLossList) {
			
			if(clsDsplLossRegVo.getDspAmt().equals(clsDsplLossRegVo.getDspOrgAmt())){
				if(clsDsplLossRegVo.getRemarks().equals(clsDsplLossRegVo.getOrgRemarks())){
					continue;
				}
			}
			
			clsDsplLossRegVo.setDspDt(paramVo.getDspDt());	// 샘플일자
			clsDsplLossRegVo.setWhCd(paramVo.getWhCd());		// 창고코드
			clsDsplLossRegVo.setDspClass(paramVo.getDspClass());		// 창고코드
			
			clsDsplLossRegVo.setWorkId(customUser.getUserId());	// 작업자 아이디 

			/*매입전표번호 있고 수량이 있으면  수정  등록  */
			clsDsplLossRegVo.setUseYn("Y"); // Default 'Y'
			
			if(StringUtil.isEmpty(buySlipNo)) {
				clsDsplLossRegMapper.insertDsplLossSlipNo(clsDsplLossRegVo);
				
				if(clsDsplLossRegMapper.insertDsplLossDlv(clsDsplLossRegVo) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
					
				insertCnt++;
			}
			else {
				if(StringUtil.isEmpty(clsDsplLossRegVo.getDspQty()) || "0".equals(clsDsplLossRegVo.getDspQty())) {
					clsDsplLossRegVo.setDspQty("0");
					clsDsplLossRegVo.setUseYn("N");
				}
				
				if(clsDsplLossRegMapper.updateDsplLossDlv(clsDsplLossRegVo) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
				updateCnt++;
			}

		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
}
