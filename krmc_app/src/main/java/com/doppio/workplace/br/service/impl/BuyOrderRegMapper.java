package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;


/**
 *
 * @Class : BuyOrderRegMapper.java
 * @Description : 매입처 발주 관리 
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      song        	  
 *
 * </pre>
 */

@Repository(value = "BuyOrderRegMapper")
public interface BuyOrderRegMapper {


	/**
	 * @Method : selectBuyOrderRegListOrdCount
	 * @Description : 매입처 발주 기 발주 PAGE COUNT 조회 
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int selectBuyOrderRegListOrdCount(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectBuyOrderRegListNotOrdCount
	 * @Description : 매입처 발주 대상 PAGE COUNT 조회 
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int selectBuyOrderRegListNotOrdCount(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectBuyOrderRegNotOrdList
	 * @Description : 매입처 발주 대상품목 조회 
	 * @param BuyOrderRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyOrderRegVo> selectBuyOrderRegNotOrdList(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectBuyOrderRegOrdList
	 * @Description : 매입처 발주 기 발주내역 조회 
	 * @param BuyOrderRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyOrderRegVo> selectBuyOrderRegOrdList(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectBuyOrderRegPrdtAddList
	 * @Description : 매입처 발주 기 발주내역 조회 
	 * @param BuyOrderRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public BuyOrderRegVo selectBuyOrderRegPrdtAddList(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : insertBuyOrderRegData
	 * @Description : 매입처 발주 입력
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int insertBuyOrderRegData(BuyOrderRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateBuyOrderRegData
	 * @Description : 매입처 발주 수정
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int updateBuyOrderRegData(BuyOrderRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertBuyRcptRegData
	 * @Description : 매입처 입고 입력
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int insertBuyRcptRegData(BuyOrderRegVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : updateBuyRcptRegData
	 * @Description : 매입처 입고 수정
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int updateBuyRcptRegData(BuyOrderRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuyOrderRegNoteCount
	 * @Description : 매입처 발주 노트유무 count
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int selectBuyOrderRegNoteCount(BuyOrderRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateBuyOrderRegNote
	 * @Description : 매입처 발주 입력
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int updateBuyOrderRegNote(BuyOrderRegVo paramVo) throws SQLException;
	

	/**
	 * @Method : updateBuyOrderRegData
	 * @Description : 매입처 발주 수정
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int insertBuyOrderRegNote(BuyOrderRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectOrdSeq
	 * @Description : 매입처 발주 order 전표번호 발취
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public String selectBuySlipNo(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectOrdSeq
	 * @Description : 매입처 발주 order seq 발취
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int selectOrdSeq(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectOrdSeq
	 * @Description : 매입처 발주 전표번호 저장
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public void insertBuyOrderSlipNo(BuyOrderRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyOrderListPrintCnt
	 * @Description : 매입처 발주서 출력 건수 조회
	 * @param BuyOrderRegVo param
	 * @return INT
	 */
	public int selectBuyOrderListPrintCnt(BuyOrderRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuyOrderRegOrdPrtList
	 * @Description : 매입처 발주 발주내역 출력 조회 
	 * @param BuyOrderRegVo param
	 * @return List<BuyOrderRegVo>
	 */
	public List<BuyOrderRegVo> selectBuyOrderRegOrdPrtList(BuyOrderRegVo paramVo);
	
	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 매입처 발주 엑셀 다운로드
	 * @param BuyOrderRegVo param
	 * @return List<BuyOrderRegVo>
	 */                     
	public List<HashMap<String, String>> selectBuyOrdRegListExcelDown(BuyOrderRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuyOrdRegListExcelDown
	 * @Description : 매입처 발주 엑셀 다운로드
	 * @param BuyOrderRegVo param
	 * @return List<BuyOrderRegVo>
	 */                     
	public List<HashMap<String, String>> selectBuyOrdRegNotListExcelDown(BuyOrderRegVo paramVo) throws SQLException;
	
}
