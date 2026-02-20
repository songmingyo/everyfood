package com.doppio.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.management.service.SysAccessLogService;
import com.doppio.management.service.SysAccessVo;


/**
 * @author :DADA
 * @Description :접근로그조회  Service implements
 * @Class : SysAccessLogServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2017.08.21  DADA		
 * </pre>
 * @version : 1.0
 */


@Service("sysAccessLogService")
public class SysAccessLogServiceImpl implements SysAccessLogService {
	
	

	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount;
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount;
	
	
	@Autowired
	private SysAccessLogMapper  sysAccessLogMapper;
	
	
	
	public Map<String, Object> selectAccessLogList(SysAccessVo paramVo){
		
		Map<String,Object> resultMap 	= new HashMap<String,Object>();
    	List<SysAccessVo>  list 	 	= null;
    	Paging 			   pagination	= null;	
    	int totalCount  			 	= 0;
    	int pageContu					= 0;
    	
    	
    	/* TOTLA COUNT */
    	totalCount = sysAccessLogMapper.selectAccessLogCount(paramVo);
    	
    	/* Page List */
    	if(totalCount >0){
			pagination 	= PagingFactory.makePagingObject(totalCount
										, paramVo.getRowsParse()
										, paramVo.getPagePerCountCust(DefaultPagePerCount)
										, paramVo.getPageCust());
			
			pageContu = pagination.getPageCount();		
			
			paramVo.setStartRowNo(pagination.getStartRowNum());
			paramVo.setEndRowNo(pagination.getEndRowNum()); 
			list = sysAccessLogMapper.selectAccessLog(paramVo);
		}

		/*RESULT GRID */
		resultMap.put("list", 		list);
    	resultMap.put("total", 		pageContu);
    	resultMap.put("records", 	totalCount);
		
		return resultMap;
	}

	/**
	 * 로그 상세 조회
	 * @param String
	 * @return List<SysAccessVo>
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectDetailLogList(SysAccessVo paramVo) throws Exception {
		List<SysAccessVo> list = null;
		Map<String, Object> result = new HashMap<String, Object>();
		
		list = sysAccessLogMapper.selectDetailLogList(paramVo);	// 리스트 조회
		
		/*RESULT GRID */
		result.put("layoutList", list);							// dataList

		return result;
	}
	
	/**
	 * Access Log 일자/시간대별  조회
	 */
	@Override
	public List<HashMap<String, String>> selectAccessLogTimesList(HashMap<String, String> search) throws Exception {
		if(StringUtils.isNotEmpty(search.get("searchDate")))	
			search.put("searchDateLocale", DateUtil.convertDateLocale(search.get("searchDate")));
		return sysAccessLogMapper.selectAccessLogTimesList(search);
	}
   
	/**
	 * 사용자 정보 조회(EXCEL)
	 * @param null
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectAccessLogExl(SysAccessVo paramVo){
		return sysAccessLogMapper.selectAccessLogExl(paramVo);
	}
}
