package com.doppio.workplace.br.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyRtnRegVo;


/**
 *
 * @Class : BuyRtnRegMapper.java
 * @Description : 매입처 반품 관리 
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

@Repository(value = "BuyRtnRegMapper")
public interface BuyRtnRegMapper {


	/**
	 * @Method : selectBuyRtnListCount
	 * @Description : 매입처 반품 내역 COUNT 조회 
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int selectBuyRtnListCount(BuyRtnRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyRtnList
	 * @Description : 매입처 반품 내역 조회 
	 * @param BuyRtnRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyRtnRegVo> selectBuyRtnList(BuyRtnRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyRtnList
	 * @Description : 매입처 반품 내역 상세 조회 
	 * @param BuyRtnRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyRtnRegVo> selectBuyRtnDetailList(BuyRtnRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyRtnRegPrdtAddList
	 * @Description : 매입처 반품 추가품목 조회
	 * @param BuyRtnRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public BuyRtnRegVo selectBuyRtnRegPrdtAddList(BuyRtnRegVo paramVo);
	
	/**
	 * @Method : insertBuyRtnRegData
	 * @Description : 매입처 반품 입력
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int insertBuyRtnRegData(BuyRtnRegVo paramVo) throws SQLException;
	

	/**
	 * @Method : updateBuyRtnRegData
	 * @Description : 매입처 반품 수정
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int updateBuyRtnRegData(BuyRtnRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuySlipNo
	 * @Description : 매입처 반품 전표번호 발취
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public String selectBuySlipNo(BuyRtnRegVo paramVo);
	
	/**
	 * @Method : selectRcptSeq
	 * @Description : 매입처 반품 seq 발취
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int selectBuySeq(BuyRtnRegVo paramVo);
	
	/**
	 * @Method : insertBuyRtnSlipNo
	 * @Description : 매입처 반품 전표번호 저장
	 * @param BuyRtnRegVo param
	 * @return INT
	 */
	public int insertBuyRtnSlipNo(BuyRtnRegVo paramVo);
	
	/**
	 * @Method : selectBuyRtnList_2
	 * @Description : 매입처 반품현황 add by song min kyo 2025.08.25
	 * @param BuyRtnRegVo param
	 * @return List
	 */
	public List<BuyRtnRegVo> selectBuyRtnList_2(BuyRtnRegVo paramVo);
	
	/**
	 * @Method : selectBuyRtnListExcelDown
	 * @Description :  매입처반품현황 엑셀 다운로드 
	 * @param BuyRtnRegVo param
	 * @return List<BuyRtnRegVo>
	 */                     
	public List<HashMap<String, String>> selectBuyRtnListExcelDown(BuyRtnRegVo paramVo) throws SQLException;
}
