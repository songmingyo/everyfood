package com.doppio.workplace.bm.service;

import java.util.HashMap;
import com.doppio.common.model.Result;


/**
 * @author dada
 * @Description :차량관리 Service interface
 * @Class : DeliVehicleService
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface DeliVehicleService {

	/**
	 * 차량정보 조회
	 * @param DlvrMasterVo	paramVo
	 * @return List<DlvrMasterVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectDelivehicleList(DlvrMasterVo paramVo) throws Exception;

	/**
	 * 차량정보 상세조회 
	 * @param DlvrMasterVo	paramVo
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	DlvrMasterVo selectDelivehicleData(DlvrMasterVo paramVo) throws Exception;

	/**
	 * 차량정보 저장(insert OR update) 
	 * @param DlvrMasterVo	paramVo
	 * @return Result
	 * @throws Exception
	 */
	Result insertDelivehicleData(DlvrMasterVo paramVo) throws Exception;

	/**
	 *  차량정보 삭제 
	 *  TFM_DLVR_MST.CAR_CD PK   
	 *  DELETE
	 * @param DlvrMasterVo paramVo
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	Result deleteDelivehicleData(DlvrMasterVo paramVo) throws Exception ;
	
}
