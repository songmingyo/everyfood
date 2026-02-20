package com.doppio.workplace.sm.service.impl;

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
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusSampleRegService;
import com.doppio.workplace.sm.service.CusSampleRegVo;


/**
 *
 * @Class : CusSampleRegServiceImpl.java
 * @Description : 매출처샘플등록 관리 
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

@Service("cusSampleRegService")
public class CusSampleRegServiceImpl implements CusSampleRegService {
	
	
	@Autowired
	CusSampleRegMapper  cusSampleRegMapper;
	
	@Autowired
	CusShipRegMapper  cusShipRegMapper;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	/**
	 * @Method : selectCusSampleRegList
	 * @Description : 매출처샘플등록 대상품목 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<CusSampleRegVo>
	 */
	@Override
	public List<CusSampleRegVo> selectCusSampleRegList(CusSampleRegVo paramVo){
		return  cusSampleRegMapper.selectCusSamplePrdtList(paramVo);
	}
	
	
	/**
	 * @Method : selectCusSampleRegDetailList
	 * @Description : 매출처샘플등록 내역 조회 
	 * @param SalSalesGoalRegVo param
	 * @return List<CusSampleRegVo>
	 */
	@Override
	public List<CusSampleRegVo> selectCusSampleRegDetailList(CusSampleRegVo paramVo){
		
		return  cusSampleRegMapper.selectCusSampleRegList(paramVo);
	}
	
	/**
	 *  매출처샘플등록 하나의 품목만 조회 
	 * @param SalSalesGoalRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusSampleRegPrdtAdd(CusSampleRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		CusSampleRegVo cusSampleRegVo = cusSampleRegMapper.selectCusSampleRegPrdtAdd(paramVo);
		
		resultList.put("cusSampleRegVo", cusSampleRegVo);
		
		return resultList;
		
	}	
	
	/**
	 *  매출처샘플등록 저장 
	 * @param SalSalesGoalRegVo paramVo
	 * @return CusSampleRegVo
	 * @throws Exception
	 */
	public Result insertCusSampleReg(CusSampleRegVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<CusSampleRegVo> sampleList =  paramVo.getCusSampleRegList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || sampleList == null || sampleList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("등록 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		String remarks1 = paramVo.getRemarks1();
		
		int remarkCnt = 0;
		
		int insertCnt = 0;		// 신규 생성 카운트
		int updateCnt = 0;		// 기존 수정 카운트
		
		String salesSlipNo = "";
		
		for(CusSampleRegVo cusSampleReg : sampleList) {
			
			cusSampleReg.setFreeDt(paramVo.getFreeDt());	// 샘플일자
			cusSampleReg.setWhCd(paramVo.getWhCd());		// 창고코드
			cusSampleReg.setFreeClass(paramVo.getFreeClass());		// 창고코드
			
			cusSampleReg.setWorkId(customUser.getUserId());	// 작업자 아이디 

			/*매입전표번호 있고 수량이 있으면  수정  등록  */
			cusSampleReg.setUseYn("Y"); // Default 'Y'
			
			if(StringUtil.isEmpty(cusSampleReg.getSalesSlipNo()) ) {
				
				cusSampleReg.setSalesSlipNo(salesSlipNo);
				
				cusSampleRegMapper.insertSampleSlipNo(cusSampleReg);	// 전표번호가 25개가 넘어가면 생성 아니면 조회
				
				salesSlipNo = cusSampleReg.getSalesSlipNo();
				
				/*매입전표번호 없으면 신규 등록 (Before SALES_SLIP_NO 생성)  */
				if(cusSampleRegMapper.insertSamepleDlv(cusSampleReg) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
				
				insertCnt++;
			} else {
				
				if(StringUtil.isEmpty(cusSampleReg.getFreeQty()) || "0".equals(cusSampleReg.getFreeQty())) {
					cusSampleReg.setFreeQty("0");
					cusSampleReg.setUseYn("N"); // 수량이 없거나 0인경우 사용여부 "N"
				}

				if(cusSampleRegMapper.updateSampleDlv(cusSampleReg) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
				
				updateCnt++;
			}
			
			cusSampleReg.setRemarks1(remarks1);
			
			remarkCnt = cusSampleRegMapper.selectSampleRemarksListCount(cusSampleReg);
			
			if(remarkCnt > 0) {
				cusSampleRegMapper.updateSampleRemarks(cusSampleReg);
			} else {
				cusSampleRegMapper.insertSampleRemarks(cusSampleReg);
			}
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	

	
}
