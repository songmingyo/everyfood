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
import com.doppio.workplace.sm.service.CusRtnRegService;
import com.doppio.workplace.sm.service.CusRtnRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;


/**
 *
 * @Class : CusRtnRegServiceImpl.java
 * @Description : 매출처반품등록 관리 
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

@Service("cusRtnRegService")
public class CusRtnRegServiceImpl implements CusRtnRegService {
	
	
	@Autowired
	CusRtnRegMapper  cusRtnRegMapper;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	/**
	 * @Method : selectCusRtnRegList
	 * @Description : 매출처반품등록 목록 조회 
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	@Override
	public List<CusRtnRegVo> selectCusRtnRegList(CusRtnRegVo paramVo){
		return  cusRtnRegMapper.selectCusRtnRegList(paramVo);
	}
	
	
	/**
	 * @Method : selectCusRtnRegDetailList
	 * @Description : 매출처반품등록 내역 조회 
	 * @param CusRtnRegVo param
	 * @return List<CusRtnRegVo>
	 */
	@Override
	public List<CusRtnRegVo> selectCusRtnRegDetailList(CusRtnRegVo paramVo){
		
		return  cusRtnRegMapper.selectCusRtnRegDetailList(paramVo);
	}
	
	/**
	 *  매출처반품등록 하나의 품목만 조회 
	 * @param BuyRcptRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusRtnRegPrdtAdd(CusRtnRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		CusRtnRegVo cusRtnRegVo = cusRtnRegMapper.selectCusRtnRegPrdtAdd(paramVo);
		
		resultList.put("cusRtnRegVo", cusRtnRegVo);
		
		return resultList;
		
	}	
	
	/**
	 *  매출처반품등록 저장 
	 * @param CusRtnRegVo paramVo
	 * @return CusRtnRegVo
	 * @throws Exception
	 */
	public Result insertCusRtnReg(CusRtnRegVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<CusRtnRegVo> rtnList =  paramVo.getCusRtnRegList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || rtnList == null || rtnList.size() <= 0) {
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
		
		int rtnListCnt = rtnList.size();
		
		String salesSlipNo = rtnList.get(rtnListCnt-1).getSalesSlipNo();
		
		for(CusRtnRegVo cusRtnReg : rtnList) {
			
			cusRtnReg.setRtnDt(paramVo.getRtnDt());			// 반품일자
			cusRtnReg.setWhCd(paramVo.getWhCd());			// 창고코드
			cusRtnReg.setRtnClass(paramVo.getRtnClass());	// 반품구분
			
			cusRtnReg.setWorkId(customUser.getUserId());	// 작업자 아이디 

			/*매입전표번호 있고 수량이 있으면  수정  등록  */
			cusRtnReg.setUseYn("Y"); // Default 'Y'
			
			if(StringUtil.isEmpty(cusRtnReg.getSalesSlipNo()) ) {
				
				cusRtnReg.setSalesSlipNo(salesSlipNo);
				
				cusRtnRegMapper.insertRtnSlipNo(cusRtnReg);	// 전표번호가 25개가 넘어가면 생성 아니면 조회
				
				salesSlipNo = cusRtnReg.getSalesSlipNo();
				
				/*매입전표번호 없으면 신규 등록 (Before SALES_SLIP_NO 생성)  */
				if(cusRtnRegMapper.insertSalesRtnReg(cusRtnReg) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
				
				insertCnt++;
			} else {
				
				if(StringUtil.isEmpty(cusRtnReg.getRtnQty()) || "0".equals(cusRtnReg.getRtnQty())) {
					cusRtnReg.setRtnQty("0");
					cusRtnReg.setUseYn("N"); // 수량이 없거나 0인경우 사용여부 "N"
				}

				if(cusRtnRegMapper.updateSalesRtnReg(cusRtnReg) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
				
				updateCnt++;
			}
			
			remarkCnt = cusRtnRegMapper.selectSalesRemarksListCount(cusRtnReg);
			
			cusRtnReg.setRemarks1(remarks1);
			
			if(remarkCnt > 0) {
				cusRtnRegMapper.updateSalesRemarks(cusRtnReg);
			} else {
				cusRtnRegMapper.insertSalesRemarks(cusRtnReg);
			}
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	
	/**
	 * @Method : selectTranPrintList
	 * @Description : 매출처 거래명세표 출력 (전표번호별) 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusRtnRegVo> selectRtnPrintList(CusRtnRegVo paramVo){
		return  cusRtnRegMapper.selectRtnPrintList(paramVo);
	}

	
}
