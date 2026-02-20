package com.doppio.workplace.cs.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.cs.service.ClsDsplLossListService;
import com.doppio.workplace.cs.service.ClsDsplLossListVo;

/**
 * @author Song
 * @Description 폐기로스 조회 : ClsDsplLossListServiceImpl implement
 * @Class : ClsDsplLossListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("clsDsplLossListService")
public class ClsDsplLossListServiceImpl  implements ClsDsplLossListService{
	
	@Autowired
	ClsDsplLossListMapper clsDsplLossListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  폐기로스 조회 
	 * @param ClsDsplLossListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectClsDsplLossList(ClsDsplLossListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<ClsDsplLossListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		resultList = clsDsplLossListMapper.selectClsDsplLossList(paramVo);
		
		totalCount = resultList.size();
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}

	
	/**
	 *  폐기로스 엑셀 다운로드 
	 * @param ClsDsplLossListVo paramVo
	 * @return ClsDsplLossListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectClsDsplLossListExcel(ClsDsplLossListVo paramVo) throws Exception {
		return clsDsplLossListMapper.selectClsDsplLossListExcel(paramVo);
	}

}
