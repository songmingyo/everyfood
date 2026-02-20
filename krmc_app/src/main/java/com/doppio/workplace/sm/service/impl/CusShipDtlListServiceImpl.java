package com.doppio.workplace.sm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.sm.service.CusShipDtlListVo;
import com.doppio.workplace.sm.service.CusPeriodListVo;
import com.doppio.workplace.sm.service.CusShipDtlListService;


/**
 * @author j10000
 * @Description 매출처출고현황(상세) 현황 : CusShipDtlListServiceImpl implement
 * @Class : CusShipDtlListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.05.06 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("cusShipDtlListService")
public class CusShipDtlListServiceImpl  implements CusShipDtlListService{
	
	@Autowired
	CusShipDtlListMapper CusShipDtlListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출처출고현황(상세) 확인 
	 * @param CusShipDtlListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusShipDtlList(CusShipDtlListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<CusShipDtlListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList =  CusShipDtlListMapper.selectCusShipDtlList(paramVo);
		
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
	
	@Override
	public List<HashMap<String, String>> selectCusShipDtlListExcelDown(CusShipDtlListVo paramVo) throws Exception {
		return CusShipDtlListMapper.selectCusShipDtlListExcelDown(paramVo);
	}

	
	/**
	 * 매출처출고현황(상세) 엑셀 다운로드 
	 * @param CusShipDtlListVo paramVo
	 * @return CusShipDtlListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectCusShipDtlListExcelDownSum(CusShipDtlListVo paramVo) throws Exception {
		return CusShipDtlListMapper.selectCusShipDtlListExcelDownSum(paramVo);
	}
	
	/**
	 * 매출처출고현황(상세) 출력 조회
	 * @param CusShipDtlListVo paramVo
	 * @return List<CusShipDtlListVo>
	 */
	@Override
	public List<CusShipDtlListVo> selectCusShipDtlPrintList(CusShipDtlListVo paramVo) {
		return CusShipDtlListMapper.selectCusShipDtlPrintList(paramVo);
	}


}
