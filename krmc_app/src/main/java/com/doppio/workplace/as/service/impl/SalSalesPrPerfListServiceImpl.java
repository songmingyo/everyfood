package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.SalSalesPrPerfListService;
import com.doppio.workplace.as.service.SalSalesPrPerfListVo;

/**
 * @author Song
 * @Description 영업실적 현황 : SalSalesPrPerfListServiceImpl implement
 * @Class : SalSalesPrPerfListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */



@Service("salSalesPrPerfListService")
public class SalSalesPrPerfListServiceImpl  implements SalSalesPrPerfListService{
	
	@Autowired
	SalSalesPrPerfListMapper salSalesPrPerfListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  영업 실적 조회 
	 * @param SalSalesPrPerfListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalSalesPrPerfList(SalSalesPrPerfListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalSalesPrPerfListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = salSalesPrPerfListMapper.selectSalSalesPrPerfList(paramVo);
		
		totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}

	

	/**
	 *  영업 실적 엑셀 다운로드 
	 * @param SalSalesPrPerfListVo paramVo
	 * @return SalSalesPrPerfListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalSalesPrPerfListExcelDown(SalSalesPrPerfListVo paramVo) throws Exception {
		return salSalesPrPerfListMapper.selectSalSalesPrPerfListExcelDown(paramVo);
	}

}
