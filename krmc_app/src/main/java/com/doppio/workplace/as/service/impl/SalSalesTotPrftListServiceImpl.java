package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.as.service.SalSalesTotPrftListService;
import com.doppio.workplace.as.service.SalSalesTotPrftListVo;

/**
 * @author Song
 * @Description 통합매출처이익률(전월대비) : SalSalesTotPrftListServiceImpl implement
 * @Class : SalSalesTotPrftListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */



@Service("salSalesTotPrftListService")
public class SalSalesTotPrftListServiceImpl  implements SalSalesTotPrftListService{
	
	@Autowired
	SalSalesTotPrftListMapper salSalesTotPrftListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  통합매출처이익률(전월대비) 조회 
	 * @param SalSalesTotPrftListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalSalesTotPrftList(SalSalesTotPrftListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalSalesTotPrftListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = salSalesTotPrftListMapper.selectSalSalesTotPrftList(paramVo);
		
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
	 * @param SalSalesTotPrftListVo paramVo
	 * @return SalSalesPrdtPrftListVo
	 * @throws Exception
	 */
	public SalSalesTotPrftListVo selectSalesTotPrftFooter(SalSalesTotPrftListVo paramVo) throws Exception {
		return  salSalesTotPrftListMapper.selectSalesTotPrftFooter(paramVo);
	}
	
	/**
	 *  통합매출처이익률(전월대비) 출력
	 * @param SalSalesTotPrftListVo paramVo
	 * @return SalSalesTotPrftListVo
	 * @throws Exception
	 */
	@Override
	public List<SalSalesTotPrftListVo> selectSalesTotPrftPrintList(SalSalesTotPrftListVo paramVo) throws Exception {
		return salSalesTotPrftListMapper.selectSalesTotPrftPrintList(paramVo);
	}
	

	/**
	 *  통합매출처이익률(전월대비) 엑셀 다운로드 
	 * @param SalSalesTotPrftListVo paramVo
	 * @return SalSalesTotPrftListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalSalesTotPrftListExcelDown(SalSalesTotPrftListVo paramVo) throws Exception {
		return salSalesTotPrftListMapper.selectSalSalesTotPrftListExcelDown(paramVo);
	}

}
