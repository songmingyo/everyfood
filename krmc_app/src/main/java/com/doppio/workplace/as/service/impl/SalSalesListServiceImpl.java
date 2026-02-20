package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.SalSalesListService;
import com.doppio.workplace.as.service.SalSalesListVo;

/**
 * @author Song min kyo
 * @Description 영업목표 : SalSalesListServiceImpl implement
 * @Class : SalSalesListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("salSalesListService")
public class SalSalesListServiceImpl  implements SalSalesListService{
	
	@Autowired
	SalSalesListMapper salSalesListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  영업신규현황 조회
	 * @param SalSalesListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalSalesMstList(SalSalesListVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalSalesListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		totalCount = salSalesListMapper.selectSalSalesMstListCnt(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount > 0) {
			resultList = salSalesListMapper.selectSalSalesMstList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}
	

	/**
	 *  영업 신규개설현황 excel download
	 * @param SalSalesListVo paramVo
	 * @return SalSalesListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> salSalesMstListSelExcelDown(SalSalesListVo paramVo) throws Exception {
		return salSalesListMapper.salSalesMstListSelExcelDown(paramVo);
	}
}
