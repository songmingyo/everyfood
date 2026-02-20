package com.doppio.workplace.br.service.impl;

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
import com.doppio.workplace.br.service.BuyRcptListVo;
import com.doppio.workplace.as.service.SalPrdtPrftListVo;
import com.doppio.workplace.br.service.BuyOrdListVo;
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptListService;


/**
 * @author j10000
 * @Description 매입처입고 조회 : buyStoreService implement
 * @Class : BuyStoreServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.31 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("buyRcptListService")
public class BuyRcptListServiceImpl  implements BuyRcptListService{
	
	@Autowired
	BuyRcptListMapper BuyRcptListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처입고 조회 
	 * @param CusOrdListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyRcptList(BuyRcptListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<BuyRcptListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  BuyRcptListMapper.selectBuyRcptList(paramVo);
		
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
	 *  매입처입고 상세조회
	 * @param BuyRcptListVo paramVo
	 * @return BuyRcptListVo
	 * @throws Exception
  */
	public BuyRcptListVo selectBuyRcptListData(BuyRcptListVo paramVo) throws Exception {
		return  BuyRcptListMapper.selectBuyRcptListData(paramVo);
	}
	
	/**
	 *  매입처입고 Footer
	 * @param BuyRcptListVo paramVo
	 * @return BuyRcptListVo
	 * @throws Exception
	 */
	public BuyRcptListVo selectBuyRcptListFooter(BuyRcptListVo paramVo) throws Exception {
		return  BuyRcptListMapper.selectBuyRcptListFooter(paramVo);
	}

	/**
	 * 매입처입고 엑셀 다운로드 
	 * @param BuyRcptListVo paramVo
	 * @return BuyRcptListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectBuyRcptListExcelDown(BuyRcptListVo paramVo) throws Exception {
		return BuyRcptListMapper.selectBuyRcptListExcelDown(paramVo);
	}

	
	
	/**
	 * 매입처입고 출력 조회
	 * @param BuyRcptListVo paramVo
	 * @return List<BuyRcptListVo>
	 */
	@Override
	public List<BuyRcptListVo> selectBuyRcptPrintList(BuyRcptListVo paramVo) {
		return BuyRcptListMapper.selectBuyRcptPrintList(paramVo);
	}
	
}
