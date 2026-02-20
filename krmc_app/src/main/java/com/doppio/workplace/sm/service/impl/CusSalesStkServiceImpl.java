package com.doppio.workplace.sm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;

import com.doppio.workplace.sm.service.CusSalesStkService;
import com.doppio.workplace.sm.service.CusSalesStkVo;

/**
 * @author dada
 * @Description 매입처 재고   : CusSalesStkService implement
 * @Class : CusSalesStkServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.04.12 dada 			
 * </pre>
 * @version : 1.0
 */


@Service("cusSalesStkService")
public class CusSalesStkServiceImpl implements  CusSalesStkService{

	@Autowired
	CusSalesStkMapper cusSalesStkMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 * @Method : selectSalesStkList
	 * @Description : 매츨처 재고  정보조회 (모바일)  
	 * @param CusSalesStkVo  paramVo
	 * @return Map<String,List<CusSalesStkVo> >
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectSalesStkList(CusSalesStkVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<CusSalesStkVo> 	resultList = null;
		
		resultList =  cusSalesStkMapper.selectSalesStkList(paramVo);
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = resultList.size();
		
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
	 * @Method : selectPrdtSearch
	 * @Description : 상품정보 조회 
	 * @param CusSalesStkVo  paramVo
	 * @return CusSalesStkVo
	 * @throws Exception
	 */
	@Override
	public CusSalesStkVo selectPrdtSearch(CusSalesStkVo paramVo) throws Exception {
		 return cusSalesStkMapper.selectPrdtSearch(paramVo);
	}
	
}
