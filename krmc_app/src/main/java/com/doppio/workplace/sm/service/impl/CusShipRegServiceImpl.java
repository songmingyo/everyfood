package com.doppio.workplace.sm.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusShipRegService;
import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;


/**
 *
 * @Class : CusShipRegServiceImpl.java
 * @Description : 매출처출고등록 관리 
 * @author : DADA 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 4. 08.      DADA        	  
 *
 * </pre>
 */

@Service("cusShipRegService")
public class CusShipRegServiceImpl implements CusShipRegService {
	
	
	@Autowired
	CusShipRegMapper  cusShipRegMapper;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	/**
	 * @Method : selectCusShipRegList
	 * @Description : 매출처출고등록 마스터 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectCusShipRegList(CusSalesDlvVo paramVo){
		return  cusShipRegMapper.selectCusShipRegList(paramVo);
	}
	
	
	/**
	 * @Method : selectCusShipRegDetailList
	 * @Description : 매출처출고등록 상품 상세  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectCusShipRegDetailList(CusSalesDlvVo paramVo){
		
		String freeClass = paramVo.getFreeClass();
		
		if("00".equals(freeClass)) {
			return  cusShipRegMapper.selectCusShipRegDlvDetailList(paramVo);
		} else {
			return  cusShipRegMapper.selectCusShipRegFreeDetailList(paramVo);
		}
	}
	
	/**
	 *  매출처출고 저장 
	 * @param CusSalesDlvVo paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	public Result insertCusShipReg(CusSalesDlvVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<CusSalesDlvVo> cusList =  	paramVo.getCusSalesDlvList();

		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || cusList == null || cusList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("출고 데이터가 없습니다.");
			return result;
		}
		
		Set<String> duplPrdtCdSet	=	new HashSet<>();
		List<String> prdtCdList		=	cusList.stream().map(CusSalesDlvVo::getPrdtCd).collect(Collectors.toList());	
		
		for (String prdtCd : prdtCdList) {
			if ( prdtCdList.indexOf(prdtCd) != prdtCdList.lastIndexOf(prdtCd) ) {
				duplPrdtCdSet.add(prdtCd);
			}
		}

		//-----품목코드 중복
		if (duplPrdtCdSet.size() > 0) {
			result.setMsgCd("dupl");
			result.setMessage(duplPrdtCdSet + "해당 품목코드가 중복입니다.");
			return result;
		}
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		String remarks1 = paramVo.getRemarks1();
		
		int remarkCnt = 0;
		
		int insertCnt = 0;		// 신규 생성 카운트
		int updateCnt = 0;		// 기존 수정 카운트
		
		int cusListCnt = cusList.size();
		
		String salesSlipNo = cusList.get(cusListCnt-1).getSalesSlipNo();
		
		for(CusSalesDlvVo cusSalesDlvVo : cusList) {
			
			cusSalesDlvVo.setRemarks1(remarks1);
			
			remarkCnt = cusShipRegMapper.selectSalesRemarksListCount(cusSalesDlvVo);
			
			if(remarkCnt > 0) {
				cusShipRegMapper.updateSalesRemarks(cusSalesDlvVo);
			} else {
				cusShipRegMapper.insertSalesRemarks(cusSalesDlvVo);
			}
			
			if(cusSalesDlvVo.getWhCd().equals(paramVo.getWhCd())) {
				if(cusSalesDlvVo.getOrgAmt().equals(cusSalesDlvVo.getPureAmt())) {
					if(cusSalesDlvVo.getOrgQty().equals(cusSalesDlvVo.getSalesQty())){
						continue;
					}
				}
			}
			
			
			cusSalesDlvVo.setSalesDt(paramVo.getSalesDt());		// 납품일자
			cusSalesDlvVo.setOrdDt(paramVo.getOrdDt());		// 발주일자
			cusSalesDlvVo.setWhCd(paramVo.getWhCd());		// 창고코드
			cusSalesDlvVo.setWorkId(customUser.getUserId());	// 작업자 아이디
			
			if("0".equals(cusSalesDlvVo.getSalesQty())){
				cusSalesDlvVo.setPureAmt("0");
				cusSalesDlvVo.setVatAmt("0");
				cusSalesDlvVo.setTotAmt("0");
			}
			
			if(StringUtil.isEmpty(cusSalesDlvVo.getSalesSlipNo()) ) {
				
				cusSalesDlvVo.setSalesSlipNo(salesSlipNo);
				
				cusShipRegMapper.insertSalesSlipNo(cusSalesDlvVo);	// 전표번호 생성 or 조회
				
				salesSlipNo = cusSalesDlvVo.getSalesSlipNo();
					
				/*매츨전표번호 없으면 신규 등록 (Before SALES_SLIP_NO 생성)  */
				if(cusShipRegMapper.insertSalesDlv(cusSalesDlvVo) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
				
				insertCnt++;
			} else {				
				/*매출전표번호 있고 수량이 있으면  수정  등록  */
				cusSalesDlvVo.setUseYn("Y"); // Default 'Y'
				
				if(StringUtil.isEmpty(cusSalesDlvVo.getSalesQty()) || "0".equals(cusSalesDlvVo.getSalesQty())) {
					cusSalesDlvVo.setSalesQty("0");
					cusSalesDlvVo.setUseYn("N"); // 수량이 없거나 0인경우 사용여부 "N"
				}

				if(cusShipRegMapper.updateSalesDlv(cusSalesDlvVo) != 1) {
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
	
	/**
	 *  매출처출고 엑셀 업로드 저장 
	 * @param CusSalesDlvVo paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	public Result insertCusShipExcelUploadReg(CusSalesDlvVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<HashMap<String, String>> cusList =  paramVo.getCusSalesDlvExcelUploadList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || cusList == null || cusList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("업로드 대상 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/

		int insertCnt = 0;		// 신규 생성 카운트

		String salesCd = cusList.get(0).get("COL_1").toString();
		
		paramVo.setSalesCd(salesCd);
		
		cusShipRegMapper.deleteSalesDlvExcelUpload(paramVo);
		
		for(HashMap<String, String> cusSalesDlvVo : cusList) {
			
			if(!"".equals(cusSalesDlvVo.get("COL_1").toString())) {
				cusSalesDlvVo.put("workId", customUser.getUserId());
				cusSalesDlvVo.put("ordDt", paramVo.getOrdDt());
				cusSalesDlvVo.put("salesDt", paramVo.getSalesDt());

				if(cusShipRegMapper.insertSalesDlvExcelUpload(cusSalesDlvVo) != 1) {
					throw new Exception("에러가 발생했습니다.");
				}
			} else if(insertCnt != 0){
				result.setRtnValue01( String.valueOf(insertCnt) );
				result.setMsgCd("0");
				result.setMessage("SUCCESS");

				return result;
			} else{
				throw new Exception("에러가 발생했습니다.");
			}
			
			insertCnt++;
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	/**
	 *  매출처출고 엑셀 업로드 자료 저장 
	 * @param CusSalesDlvVo paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	public Result insertCusShipExcelReg(CusSalesDlvVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<CusSalesDlvVo> cusList =  paramVo.getCusSalesDlvList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || cusList == null || cusList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("출고 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		int insertCnt = 0;		// 신규 생성 카운트
		
	    // 박스 및 금애 수정을 위한 선언
//		double salesQty = 0;
//		int qtyBox = 0;
//		String strQtyBox = "";
//		String qtyEa = "";
//		String ordUnit = "";
//		String stdYn = "";
//		
//		int price = 0;
//		String vatYn = "";
//		
//		int pureAmt = 0;
//		int vatAmt = 0;
		
		String fbClass = "";
		String salesCd = "";
		String salesSlipNo = "";
		
		for(CusSalesDlvVo cusSalesDlvVo : cusList) {
			
			cusSalesDlvVo.setSalesDt(paramVo.getSalesDt());		// 납품일자
			cusSalesDlvVo.setOrdDt(paramVo.getOrdDt());		// 발주일자
			cusSalesDlvVo.setWhCd(paramVo.getWhCd());		// 창고코드
			cusSalesDlvVo.setWorkId(customUser.getUserId());	// 작업자 아이디
			
			//박스 수량 계산
//			salesQty = Double.parseDouble(cusSalesDlvVo.getSalesQty());
//			qtyBox = Integer.parseInt(cusSalesDlvVo.getQtyBox());
//			
//			strQtyBox = Integer.toString(((int) Math.floor(salesQty)) / qtyBox);			
//			qtyEa = Double.toString(salesQty % (double) qtyBox);
//			stdYn = cusSalesDlvVo.getStdYn();
//			ordUnit = cusSalesDlvVo.getOrdUnit();
//			
//			if("2".equals(stdYn)) {
//				cusSalesDlvVo.setBoxQty(Double.toString(salesQty) + ordUnit);
//			}else {
//				if(salesQty < qtyBox) {
//					cusSalesDlvVo.setBoxQty(Double.toString(salesQty) + ordUnit);
//				}else if("0".equals(qtyEa)) {
//					if(qtyBox > 1) {
//						cusSalesDlvVo.setBoxQty(strQtyBox + "BOX");
//					}else {
//						cusSalesDlvVo.setBoxQty(strQtyBox + ordUnit);
//					}
//				}else {
//					cusSalesDlvVo.setBoxQty(strQtyBox + "BOX/" + qtyEa + ordUnit);
//				}
//			}
//			
//			// 금액 계산
//			price = Integer.parseInt(cusSalesDlvVo.getPrice());
//			vatYn = cusSalesDlvVo.getVatYn();
//			
//			pureAmt = (int)Math.round(salesQty * price);
//			
//			if("2".equals(vatYn)) {
//				vatAmt = 0;
//			}else {
//				vatAmt = (int)Math.round(salesQty * price * 0.1);
//			}
//			
//			cusSalesDlvVo.setPureAmt(Integer.toString(pureAmt));
//			cusSalesDlvVo.setVatAmt(Integer.toString(vatAmt));
//			cusSalesDlvVo.setTotAmt( Integer.toString(pureAmt + vatAmt));
			
			if(!fbClass.equals(cusSalesDlvVo.getFbClass())) {				
				cusSalesDlvVo.setSlipNoChg("C");
				fbClass = cusSalesDlvVo.getFbClass();
				salesCd = cusSalesDlvVo.getSalesCd();
			}else {
				if(!salesCd.equals(cusSalesDlvVo.getSalesCd())) {
					cusSalesDlvVo.setSlipNoChg("C");
					fbClass = cusSalesDlvVo.getFbClass();
					salesCd = cusSalesDlvVo.getSalesCd();
				}else {
					cusSalesDlvVo.setSlipNoChg("S");
					cusSalesDlvVo.setSalesSlipNo(salesSlipNo);
				}
			} 
			
			cusShipRegMapper.insertSalesSlipNoExcel(cusSalesDlvVo);	// 전표번호 생성 or 조회
			
			salesSlipNo = cusSalesDlvVo.getSalesSlipNo();
				
			/*매츨전표번호 없으면 신규 등록 (Before SALES_SLIP_NO 생성)  */
			if(cusShipRegMapper.insertSalesDlv(cusSalesDlvVo) != 1) {
				throw new Exception("에러가 발생했습니다.");
			}
				
			insertCnt++;
			
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	
	/**
	 * @Method : selectCusShipRegExcelUploadList
	 * @Description : 매출처출고등록 엑셀 업로드 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectCusShipRegExcelUploadList(CusSalesDlvVo paramVo){
		return  cusShipRegMapper.selectCusShipRegExcelUploadList(paramVo);
	}
	
	/**
	 * @Method : selectCusShipRegExcelUploadList
	 * @Description : 매출처출고등록 엑셀 업로드 에러 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectCusShipRegExcelUploadErrorList(CusSalesDlvVo paramVo){
		return  cusShipRegMapper.selectCusShipRegExcelUploadErrorList(paramVo);
	}
	
	/**
	 * @Method : selectTranPrintList
	 * @Description : 매출처 거래명세표 출력 (전표번호별) 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectTranPrintList(CusSalesDlvVo paramVo){

//		return  cusShipRegMapper.selectTranPrintList(paramVo);	2025.08.14 by song min kyo
		return  cusShipRegMapper.selectTranPrintListTest(paramVo);
	}
	

	
	/**
	 * @Method : selectSalesOtherPrintList
	 * @Description : 매출처출고등록 고객 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectSalesCustPrintList(CusSalesDlvVo paramVo){
		
		return cusShipRegMapper.selectSalesCustPrintList(paramVo);
	}
	
	/**
	 * @Method : selectSalesOtherPrintList
	 * @Description : 매출처출고등록 차량 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectSalesCarPrintList(CusSalesDlvVo paramVo){
		
		return cusShipRegMapper.selectSalesCarPrintList(paramVo);
	}
	
	/**
	 * @Method : selectSalesStkPrintList
	 * @Description : 매출처출고 재고집계표 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<CusSalesDlvVo> selectSalesStkPrintList(CusSalesDlvVo paramVo){
		
		return  cusShipRegMapper.selectSalesStkPrintList(paramVo);
	}

	
	/**
	 *  매출처출고 출력유무 저장 
	 * @param CusSalesDlvVo paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	public Result insertCusShipPrtYnReg(CusSalesDlvVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		paramVo.setWorkId(customUser.getUserId());
		
		if("A".equals(paramVo.getPrtClass()) || "E".equals(paramVo.getPrtClass())) {//거래명세표
			cusShipRegMapper.insertCusShipPrtYnReg(paramVo);
		}else if("B".equals(paramVo.getPrtClass())) {//고객사집계표
			cusShipRegMapper.insertCusShipPrtYnReg(paramVo);
		}else if("C".equals(paramVo.getPrtClass())) {//자량별집계표
			cusShipRegMapper.insertCusShipPrtYnReg(paramVo);
		}else if("D".equals(paramVo.getPrtClass())) {//재고집계표
			cusShipRegMapper.insertCusShipPrtYnReg(paramVo);
		}
		
		result.setRtnValue01( "1" );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
}
