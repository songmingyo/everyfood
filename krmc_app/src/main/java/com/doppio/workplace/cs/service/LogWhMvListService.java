package com.doppio.workplace.cs.service;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Song
 * @Description : 센터이동 조회 Service interface
 * @Class : LogWhMvListVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */

public interface LogWhMvListService {

	/**
	 * 센터이동 조회
	 * @param LogWhMvListVo	paramVo
	 * @return List<LogWhMvListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectLogWhMvList(LogWhMvListVo paramVo) throws Exception;
	

	/**
	 * 센터이동 엑셀 다운로드
	 * @param LogWhMvListVo	paramVo
	 * @return List<LogWhMvListVo>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectLogWhMvListExcel(LogWhMvListVo paramVo) throws Exception;
	
	/**
	 * 센터이동 출력
	 * @param LogWhMvListVo	paramVo
	 * @return List<LogWhMvListVo>
	 * @throws Exception
	 */
	public List<LogWhMvListVo> selectLogWhMvListPrint(LogWhMvListVo paramVo) throws Exception;
}

