package com.doppio.workplace.sm.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;

import org.springframework.stereotype.Repository;
import com.doppio.workplace.sm.service.CusSalesDlvVo;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;


/**
 *
 * @Class : CusShipRegMapper.java
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

@Repository(value = "cusShipRegMapper")
public interface CusShipRegMapper {
	
	
	/**
	 * @Method : selectCusShipRegList
	 * @Description : 매출처출고등록 마스터 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectCusShipRegDlvDetailList
	 * @Description : 매출처 출고등록 상품 상세  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegDlvDetailList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectCusShipRegFreeDetailList
	 * @Description : 매출처 샘플 등록 상품 상세  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegFreeDetailList(CusSalesDlvVo paramVo);
	
	/** 
	 * @Method : insertSalesSlipNo
	 * 전포번호 생성 / 조회 
	 * (25개이상인 경우 신규 전표번호 생성 하고 Insert/전표번호 반환,  25이하인경우 전표번호 INSERT 무시, 조회된 전표번호 반환  )  
	  [전표번호 생성 Sequence 및 규칙 적용 필요 ]		
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertSalesSlipNo(CusSalesDlvVo paramVo) throws SQLException;
	
	/** 
	 * @Method : insertSalesSlipNoExcel
	 * 엑셀 전포번호 생성 / 조회 
	 * (25개이상인 경우 신규 전표번호 생성 하고 Insert/전표번호 반환,  25이하인경우 전표번호 INSERT 무시, 조회된 전표번호 반환  )  
	  [전표번호 생성 Sequence 및 규칙 적용 필요 ]		
	 * @param paramVo
	 * @return
	 * @throws SQLException
	 */
	public int insertSalesSlipNoExcel(CusSalesDlvVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처 츨고 관리 MERGE(INSERT)
	 * @param CusSalesDlvVo param
	 * @return Int
	 * @return List<CustSalesDlvVo>
	 */
	public int updateSalesDlv(CusSalesDlvVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처 츨고 관리 MERGE(INSERT )
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int insertSalesDlv(CusSalesDlvVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectSalesRemarksListCount
	 * @Description : 매출처 전표별 비고 Count
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int selectSalesRemarksListCount(CusSalesDlvVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateSalesRemarks
	 * @Description : 매출처 전표별 비고 수정
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int updateSalesRemarks(CusSalesDlvVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertSalesRemarks
	 * @Description : 매출처 전표별 비고 입력
	 * @param CusSalesDlvVo param
	 * @return Int
	 */	
	public int insertSalesRemarks(CusSalesDlvVo paramVo) throws SQLException;

	
	/**
	 * @Method : insertSalesDlv
	 * @Description : 매출처 츨고 엑셀 업로드 저장
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int insertSalesDlvExcelUpload(HashMap<String, String> paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectCusShipRegExcelUploadList
	 * @Description : 매출처 엑셀 업로드  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegExcelUploadList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectCusShipRegExcelUploadErrorList
	 * @Description : 매출처 엑셀 업로드 에러 조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectCusShipRegExcelUploadErrorList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : deleteSalesDlvExcelUpload
	 * @Description : 매출처 엑셀 업로드 기존 자료 삭제
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int deleteSalesDlvExcelUpload(CusSalesDlvVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertCusShipPrtYnReg
	 * @Description : 매출처 츨고 출력 유무 저장
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int insertCusShipPrtYnReg(CusSalesDlvVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateCusShipPrtYnReg
	 * @Description : 매출처 츨고 출력 유무 수정(거래명세표만)
	 * @param CusSalesDlvVo param
	 * @return Int
	 */
	public int updateCusShipPrtYnReg(CusSalesDlvVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : selectTranPrintList
	 * @Description : 매출처 거래명세표 출력 (전표번호별) 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectTranPrintList(CusSalesDlvVo paramVo);
	
	public List<CusSalesDlvVo> selectTranPrintListTest(CusSalesDlvVo paramVo);
	
	
	/**
	 * @Method : selectSalesOtherPrintList
	 * @Description : 매출처출고등록 고객 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectSalesCustPrintList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectSalesOtherPrintList
	 * @Description : 매출처출고등록 차량 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectSalesCarPrintList(CusSalesDlvVo paramVo);
	
	/**
	 * @Method : selectSalesStkPrintList
	 * @Description : 매출처출고 재고집계 출력
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	public List<CusSalesDlvVo> selectSalesStkPrintList(CusSalesDlvVo paramVo);
	


}
