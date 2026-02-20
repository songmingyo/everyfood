package com.doppio.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :DADA
 * @Description :접근로그조회  Service interface
 * @Class : SysAccessLogService
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2017.08.21  DADA		
 * </pre>
 * @version : 1.0
 */

public interface SysAccessLogService {

	/**
	 * 접근로그 조회 
	 * @param SysAccessVo
	 * @return  Map<String, Object>
	 */
	Map<String, Object> selectAccessLogList(SysAccessVo paramVo);
	
	
	/**
	 * 로그 상세 조회
	 * @param String
	 * @return List<SysAccessVo>
	 * @throws Exception
	 */
	public Map<String, Object> selectDetailLogList(SysAccessVo paramVo) throws Exception;
	
	/**
	 * 프로그램 접근통계 조회 
	 * @param HashMap<String, String>
	 * @return  List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectAccessLogTimesList(HashMap<String, String> search) throws Exception;
	
	/**
	 * 사용자 정보 조회(EXCEL)
	 * @param null
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectAccessLogExl(SysAccessVo paramVo);
}
