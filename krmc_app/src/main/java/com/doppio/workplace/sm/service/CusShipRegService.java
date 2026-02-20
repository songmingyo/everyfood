package com.doppio.workplace.sm.service;

import java.util.List;

import com.doppio.common.model.Result;
import com.doppio.workplace.br.service.BuyRcptRegVo;


/**
 *
 * @Class : CusShipRegService.java
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

public interface CusShipRegService {
	
	
	

	/**
	 * @Method : selectCusShipRegList
	 * @Description : 매출처출고등록 마스터 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegList(CusSalesDlvVo paramVo);
	
	
	/**
	 * @Method : selectCusShipRegDetailList
	 * @Description : 매출처출고등록 상품 상세  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegDetailList(CusSalesDlvVo paramVo);
	
	/**
	 * 매출처출고등록 정보 저장
	 * @param CusSalesDlvVo	paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	Result insertCusShipReg(CusSalesDlvVo paramVo) throws Exception;
	
	/**
	 * 매출처출고등록 엑셀 업로드 저장
	 * @param CusSalesDlvVo	paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	Result insertCusShipExcelUploadReg(CusSalesDlvVo paramVo) throws Exception;
	
	/**
	 * 매출처출고등록 엑셀 확정 저장
	 * @param CusSalesDlvVo	paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	Result insertCusShipExcelReg(CusSalesDlvVo paramVo) throws Exception;
	
	/**
	 * @Method : selectCusShipRegExcelUploadList
	 * @Description : 매출처출고등록 엑셀 업로드  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegExcelUploadList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectCusShipRegExcelUploadErrorList
	 * @Description : 매출처출고등록 엑셀 업로드 에러 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegExcelUploadErrorList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectCusShipRegExcelUploadErrorList
	 * @Description : 매출처출고등록 거래명세표 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectTranPrintList(CusSalesDlvVo paramVo);
	
	
	/**
	 * @Method : selectSalesOtherPrintList
	 * @Description : 매출처출고등록 고객 출력
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectSalesCustPrintList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectSalesOtherPrintList
	 * @Description : 매출처출고등록 차량 출력
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectSalesCarPrintList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectSalesStkPrintList
	 * @Description : 매출처출고 재고집계표 출력
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectSalesStkPrintList(CusSalesDlvVo paramVo);
	
	/**
	 * 매출처출고 출력정보 저장
	 * @param CusSalesDlvVo	paramVo
	 * @return CusSalesDlvVo
	 * @throws Exception
	 */
	Result insertCusShipPrtYnReg(CusSalesDlvVo paramVo) throws Exception;

}
