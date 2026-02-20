package com.doppio.workplace.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.workplace.bm.service.BuyMgmtVo;


/**
 *
 * @Class : BuyMgmtMapper.java
 * @Description : 매입처마스터 관리 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 19.      j10000        	  
 *
 * </pre>
 */

@Repository(value = "BuyMgmtMapper")
public interface BuyMgmtMapper {


	/**
	 * @Method : selectBuyMgmtListCount
	 * @Description : 매입처관리 PAGE COUNT 조회 
	 * @param BuyOrderVo param
	 * @return INT
	 */
	public int selectBuyMgmtListCount(BuyMgmtVo paramVo);
	
	/**
	 * @Method : selectBuyMgmtList
	 * @Description : 매입처관리 조회 
	 * @param BuyOrderVo param
	 * @return List<BuyMgmtVo>
	 */
	public List<BuyMgmtVo> selectBuyMgmtList(BuyMgmtVo paramVo);
	
	/**
	 * @Method : selectBuyMgmtList
	 * @Description : 매입처 상세조회 
	 * @param BuyOrderVo param
	 * @return BuyMgmtVo
	 */
	public BuyMgmtVo selectBuyMgmtData(BuyMgmtVo paramVo);
	
	
	/**
	 * @Method : insertBuyMgmt
	 * @Description : 매입처 생성
	 * @param BuyMgmtVo param
	 * @return INT
	 */
	public int insertBuyMgmt(BuyMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : updateBuyMgmt
	 * @Description : 매입처 수정
	 * @param BuyOrderVo param
	 * @return INT
	 */
	public int updateBuyMgmt(BuyMgmtVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : insertBuyMgmt
	 * @Description : 매입처 로그인 계정 생성
	 * @param BuyMgmtVo param
	 * @return INT
	 */
	public int insertMember(BuyMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertBuyMgmt
	 * @Description : 매입처 로그인 계정 수정
	 * @param BuyMgmtVo param
	 * @return INT
	 */
	public int updateMember(BuyMgmtVo paramVo) throws SQLException;
	
	
	/**
	 * @Method : insertMemberAuth
	 * @Description : 매입처 로그인 계정 생성
	 * @param BuyMgmtVo param
	 * @return INT
	 */
	public int insertMemberAuth(BuyMgmtVo paramVo) throws SQLException;
	
	/**
	 * @Method : selectBuyMgmtListExcelDown
	 * @Description : 매입처 관리 Excel DownLoad
	 * @since : 2024.09.22
	 * @param BuyMgmtVo param
	 * @return List
	 */
	public List<HashMap<String, String>> selectBuyMgmtListExcelDown(BuyMgmtVo paramVo);
}
