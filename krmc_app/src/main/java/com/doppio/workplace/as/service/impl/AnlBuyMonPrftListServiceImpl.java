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
import com.doppio.workplace.as.service.AnlBuyMonPrftListVo;
import com.doppio.workplace.as.service.AnlCreditSalesListVo;
import com.doppio.workplace.as.service.AnlBuyMonPrftListService;


/**
 * @author j10000
 * @Description 매입처월별이익현황  : AnlBuyMonPrftListServiceImpl implement
 * @Class : AnlBuyMonPrftListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.05.20 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("anlBuyMonPrftListService")
public class AnlBuyMonPrftListServiceImpl  implements AnlBuyMonPrftListService{
	
	@Autowired
	AnlBuyMonPrftListMapper AnlBuyMonPrftListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처월별이익현황  확인 
	 * @param AccSalesDepListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAnlBuyMonPrftList(AnlBuyMonPrftListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<AnlBuyMonPrftListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList =  AnlBuyMonPrftListMapper.selectAnlBuyMonPrftList(paramVo);
		
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
	 * 매입처월별이익현황 엑셀 다운로드 
	 * @param AnlBuyMonPrftListVo paramVo
	 * @return AnlBuyMonPrftListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectAnlBuyMonPrftListExcelDown(AnlBuyMonPrftListVo paramVo) throws Exception {
		return AnlBuyMonPrftListMapper.selectAnlBuyMonPrftListExcelDown(paramVo);
	}

}
