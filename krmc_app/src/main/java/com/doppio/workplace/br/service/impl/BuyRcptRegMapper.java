package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptRegVo;


/**
 *
 * @Class : BuyRcptRegMapper.java
 * @Description : 매입처 입고 관리 
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

@Repository(value = "BuyRcptRegMapper")
public interface BuyRcptRegMapper {


	
	/**
	 * @Method : selectBuyRcptListCount
	 * @Description : 매입처 입고 내역 COUNT 조회 
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public int selectBuyRcptListCount(BuyRcptRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyRcptList
	 * @Description : 매입처 입고 내역 조회 
	 * @param BuyRcptRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyRcptRegVo> selectBuyRcptList(BuyRcptRegVo paramVo);
	

	
	/**
	 * @Method : selectBuyRcptListCount
	 * @Description : 매입처 입고 내역 상세 COUNT 조회 
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public int selectBuyRcptDetailListCount(BuyRcptRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyRcptList
	 * @Description : 매입처 입고 내역 상세 조회 
	 * @param BuyRcptRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<BuyRcptRegVo> selectBuyRcptDetailList(BuyRcptRegVo paramVo);
	
	
	/**
	 * @Method : selectBuyRcptRegPrdtAddList
	 * @Description : 매입처 입고 추가품목 조회
	 * @param BuyRcptRegVo param
	 * @return List<DlvrMasterVo>
	 */
	public BuyRcptRegVo selectBuyRcptRegPrdtAddList(BuyRcptRegVo paramVo);
	
	/**
	 * @Method : insertBuyRcptRegData
	 * @Description : 매입처 입고 저장
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public int insertBuyRcptRegData(BuyRcptRegVo paramVo) throws SQLException;
	

	/**
	 * @Method : updateBuyRcptRegData
	 * @Description : 매입처 입고 수정
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public int updateBuyRcptRegData(BuyRcptRegVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuySlipNo
	 * @Description : 매입처 입고 전표번호 발취
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public String selectBuySlipNo(BuyRcptRegVo paramVo);
	
	/**
	 * @Method : selectRcptSeq
	 * @Description : 매입처 입고 seq 발취
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public int selectBuySeq(BuyRcptRegVo paramVo);
	
	/**
	 * @Method : insertBuyRcptSlipNo
	 * @Description : 매입처 입고 전표번호 저장
	 * @param BuyRcptRegVo param
	 * @return INT
	 */
	public void insertBuyRcptSlipNo(BuyRcptRegVo paramVo);
	
}
