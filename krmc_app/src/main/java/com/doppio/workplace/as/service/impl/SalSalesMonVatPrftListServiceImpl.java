package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.SalSalesMonVatPrftListService;
import com.doppio.workplace.as.service.SalSalesMonVatPrftListVo;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;

/**
 * @author Song
 * @Description 매출처 월별이익현황 : SalSalesMonVatPrftListServiceImpl implement
 * @Class : SalSalesMonVatPrftListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */



@Service("salSalesMonVatPrftListService")
public class SalSalesMonVatPrftListServiceImpl  implements SalSalesMonVatPrftListService{
	
	@Autowired
	SalSalesMonVatPrftListMapper salSalesMonVatPrftListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출처 월별이익현황 조회 
	 * @param SalSalesMonVatPrftListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalSalesMonVatPrftList(SalSalesMonVatPrftListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalSalesMonVatPrftListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = salSalesMonVatPrftListMapper.selectSalSalesMonVatPrftList(paramVo);
		
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
	 *  매출처 월별이익현황 Footer
	 * @param SalSalesPrdtPrftListVo paramVo
	 * @return SalSalesPrdtPrftListVo
	 * @throws Exception
	 */
	public SalSalesMonVatPrftListVo selectSalSalesMonVatPrftFooter(SalSalesMonVatPrftListVo paramVo) throws Exception {
		return  salSalesMonVatPrftListMapper.selectSalSalesMonVatPrftFooter(paramVo);
	}

	/**
	 *  매출처 월별이익현황 엑셀 다운로드 
	 * @param SalSalesMonVatPrftListVo paramVo
	 * @return SalSalesMonVatPrftListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalSalesMonVatPrftListExcelDown(SalSalesMonVatPrftListVo paramVo) throws Exception {
		return salSalesMonVatPrftListMapper.selectSalSalesMonVatPrftListExcelDown(paramVo);
	}

}
