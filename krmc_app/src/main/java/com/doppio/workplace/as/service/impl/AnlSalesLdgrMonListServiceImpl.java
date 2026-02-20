package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.doppio.management.service.MgrCodeVo;
import com.doppio.workplace.as.service.AnlSalesLdgrMonListVo;
import com.doppio.workplace.as.service.AnlSalesLdgrMonListService;


/**
 * @author j10000
 * @Description 매출처원장(월별)  : AnlSalesLdgrMonListServiceImpl implement
 * @Class : AnlSalesLdgrMonListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.05.20 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("anlSalesLdgrMonListService")
public class AnlSalesLdgrMonListServiceImpl  implements AnlSalesLdgrMonListService{
	
	@Autowired
	AnlSalesLdgrMonListMapper AnlSalesLdgrMonListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 * 매출처원장(월별)  확인 
	 * @param AnlSalesLdgrMonListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAnlSalesLdgrMonList (AnlSalesLdgrMonListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<AnlSalesLdgrMonListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;		
		
		resultList =  AnlSalesLdgrMonListMapper.selectAnlSalesLdgrMonList(paramVo);
		
		totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		
		String salesCdTemp = "";
		String salesCd = "";
		String sort = "";
		double paidAmt1 = 0;
		double paidAmt2 = 0;
		double paidAmt3 = 0;
		double paidAmt4 = 0;
		double balAmt = 0;
		double totAmt = 0;
		double balAmtLast = 0;
		
		for(int i=0; i<resultList.size(); i++) {
			salesCd = resultList.get(i).getSalesCd();
			sort = resultList.get(i).getSort();
						
			balAmt = Double.parseDouble(resultList.get(i).getBalAmt());
			totAmt = Double.parseDouble(resultList.get(i).getTotAmt());
			paidAmt1 = Double.parseDouble(resultList.get(i).getPaidAmt1());
			paidAmt2 = Double.parseDouble(resultList.get(i).getPaidAmt2());
			paidAmt3 = Double.parseDouble(resultList.get(i).getPaidAmt3());
			paidAmt4 = Double.parseDouble(resultList.get(i).getPaidAmt4());
			
			if(!salesCdTemp.equals(salesCd)) {
				balAmtLast = 0;
				balAmtLast = balAmt;
				salesCdTemp = salesCd;
			}else if("2".equals(sort)) {
				balAmtLast = balAmtLast + totAmt;
			}else if("3".equals(sort)) {
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
			}
			
			resultList.get(i).setBalAmt(Double.toString(balAmtLast));
		}
		
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}

	
	/**
	 * 매출처원장(월별) 엑셀 다운로드 
	 * @param AnlSalesLdgrMonListVo paramVo
	 * @return AnlSalesLdgrMonListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectAnlSalesLdgrMonListExcelDown(AnlSalesLdgrMonListVo paramVo) throws Exception {
		return AnlSalesLdgrMonListMapper.selectAnlSalesLdgrMonListExcelDown(paramVo);
	}
	

}
