package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListService;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;

/**
 * @author Song
 * @Description 매출처 품목별이익현황 : SalSalesPrdtPrftListServiceImpl implement
 * @Class : SalSalesPrdtPrftListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */



@Service("salSalesPrdtPrftListService")
public class SalSalesPrdtPrftListServiceImpl  implements SalSalesPrdtPrftListService{
	
	@Autowired
	SalSalesPrdtPrftListMapper salSalesPrdtPrftListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출처 품목별이익현황 조회 
	 * @param SalSalesPrdtPrftListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalSalesPrdtPrftList(SalSalesPrdtPrftListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalSalesPrdtPrftListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = salSalesPrdtPrftListMapper.selectSalSalesPrdtPrftList(paramVo);
		
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
	 *  매출처 품목별이익현황 Footer
	 * @param SalSalesPrdtPrftListVo paramVo
	 * @return SalSalesPrdtPrftListVo
	 * @throws Exception
	 */
	public SalSalesPrdtPrftListVo selectSalSalesPrdtPrftFooter(SalSalesPrdtPrftListVo paramVo) throws Exception {
		return  salSalesPrdtPrftListMapper.selectSalSalesPrdtPrftFooter(paramVo);
	}

	/**
	 *  매출처 품목별이익현황 엑셀 다운로드 
	 * @param SalSalesPrdtPrftListVo paramVo
	 * @return SalSalesPrdtPrftListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalSalesPrdtPrftListExcelDown(SalSalesPrdtPrftListVo paramVo) throws Exception {
		return salSalesPrdtPrftListMapper.selectSalSalesPrdtPrftListExcelDown(paramVo);
	}

}
