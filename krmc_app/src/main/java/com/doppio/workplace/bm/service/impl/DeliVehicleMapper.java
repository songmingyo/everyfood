package com.doppio.workplace.bm.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.doppio.workplace.bm.service.DlvrMasterVo;

/**
 *
 * @Class : DeliVehicleMapper.java
 * @Description : 차량관리 
 * @author : DADA
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      DADA        	  
 *
 * </pre>
 */

@Repository(value = "deliVehicleMapper")
public interface DeliVehicleMapper {


	/**
	 * @Method : selectDelivehicleListCount
	 * @Description : 챠량정보 PAGE COUNT 조회 
	 * @param DlvrMasterVo param
	 * @return INT
	 */
	public int selectDelivehicleListCount(DlvrMasterVo paramVo);
	
	/**
	 * @Method : selectDelivehicleList
	 * @Description : 챠량정보 조회 
	 * @param DlvrMasterVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<DlvrMasterVo> selectDelivehicleList(DlvrMasterVo paramVo);
	
	/**
	 * @Method : selectDelivehicleData
	 * @Description : 챠량정보 상세조회 
	 * @param DlvrMasterVo param
	 * @return DlvrMasterVo
	 */
	public DlvrMasterVo selectDelivehicleData(DlvrMasterVo paramVo);
	
	/**
	 * @Method : insertDelivehicleData
	 * @Description : 챠량정보 신규 저장 (insert) 
	 * @param DlvrMasterVo param
	 * @return Int
	 */
	public int insertDelivehicle(DlvrMasterVo paramVo);

	/**
	 * @Method : updateDelivehicleData
	 * @Description : 챠량정보 수정 
	 * @param DlvrMasterVo param
	 * @return Int
	 */
	public int updateDelivehicle(DlvrMasterVo paramVo);
	
	/**
	 * @Method : deleteDelivehicleData
	 * @Description : 챠량정보 삭제 
	 * @param DlvrMasterVo param
	 * @return Int
	 */
	public int deleteDelivehicle(DlvrMasterVo paramVo);
	
	
	
	
	
	
}
