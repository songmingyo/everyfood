package com.doppio.workplace.bm.service.impl;

import java.util.List;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.LogisticsWarehouseVo;


/**
 *
 * @Class : LogisticsWarehouseMapper.java
 * @Description : 물류창고 관리 
 * @author : Song
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 3. 6.      DADA        	  
 *
 * </pre>
 */

@Repository(value = "logisticsWarehouseMapper")
public interface LogisticsWarehouseMapper {


	/**
	 * @Method : selectLogisticsWarehouseListCount
	 * @Description : 뭄류창고정보 PAGE COUNT 조회 
	 * @param DlvrMasterVo param
	 * @return INT
	 */
	public int selectLogisticsWarehouseListCount(LogisticsWarehouseVo paramVo);
	
	/**
	 * @Method : selectLogisticsWarehouseList
	 * @Description : 뭄류창고정보 조회 
	 * @param DlvrMasterVo param
	 * @return List<DlvrMasterVo>
	 */
	public List<LogisticsWarehouseVo> selectLogisticsWarehouseList(LogisticsWarehouseVo paramVo);
	
	
	/**
	 * @Method : insertLogisticsWarehouseInfo
	 * @Description : 물류창고 생성
	 * @param DlvrMasterVo param
	 * @return INT
	 */
	public int insertLogisticsWarehouseInfo(LogisticsWarehouseVo paramVo) throws SQLException;
	
	/**
	 * @Method : insertLogisticsWarehouseInfo
	 * @Description : 물류창고 수정
	 * @param DlvrMasterVo param
	 * @return INT
	 */
	public int updateLogisticsWarehouseInfo(LogisticsWarehouseVo paramVo) throws SQLException;
	
}
