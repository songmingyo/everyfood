package com.doppio.workplace.as.service;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @Class : AnlAccRecvListService.java
 * @Description : 외상매입금 
 * @author : j10000
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 5. 18.      j10000        	  
 *
 * </pre>
 */

public interface AnlAccRecvListService {
	
	
	

	/**
	 * 외상매입금(상세) 조회
	 * @param AnlAccRecvListVo	paramVo
	 * @return List<AnlAccRecvListVo>
	 * @throws Exception
	 */
	HashMap<String, Object> selectAnlAccRecvList(AnlAccRecvListVo paramVo) throws Exception;
	
	/**
	 * 외상매입금(상세) 출력
	 * @param AnlAccRecvListVo	paramVo
	 * @return List<AnlAccRecvListVo>
	 * @throws Exception
	 */
	List<AnlAccRecvListVo> selectAccRecvPrintList(AnlAccRecvListVo paramVo) throws Exception;
	
	/**
	 * 외상매입금(상세) 엑셀 다운로드
	 * @param AnlAccRecvListVo
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectAnlAccRecvListExcelDown(AnlAccRecvListVo paramVo) throws Exception;
	




}
