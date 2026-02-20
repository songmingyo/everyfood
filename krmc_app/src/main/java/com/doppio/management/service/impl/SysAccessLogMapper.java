package com.doppio.management.service.impl;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.SysAccessVo;


/**
 * @author :DADA
 * @Description :접근로그조회  Mapper interface
 * @Class : SysAccessLogMapper
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2017.08.21  DADA		
 * </pre>
 * @version : 1.0
 */
@Repository(value = "sysAccessLogMapper")
public interface SysAccessLogMapper {

	/**
	 * Access log  PAGE TOTAL COUNT  
	 * @param SysAccessVo
	 * @return Integer
	 */
	public int selectAccessLogCount(SysAccessVo commonPartvo) ;
	
	
	/**
	 * Access log  PAGE LIST  
	 * @param SysAccessVo
	 * @return Integer
	 */
	public List<SysAccessVo> selectAccessLog(SysAccessVo paramVo);
	
	/**
	 * 로그 상세 조회
	 * @param String
	 * @return List<SysAccessVo>
	 * @throws Exception
	 */
	public List<SysAccessVo> selectDetailLogList(SysAccessVo paramVo) throws Exception;
	 
	
	/**
	 * 프로그램 접근통계 조회 
	 * @param HashMap<String, String>
	 * @return  List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectAccessLogTimesList(HashMap<String, String> search) throws SQLException;
	
	/**
	 * 사용자 정보 조회(EXCEL)
	 * @param null
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> selectAccessLogExl(SysAccessVo paramVo);

}
