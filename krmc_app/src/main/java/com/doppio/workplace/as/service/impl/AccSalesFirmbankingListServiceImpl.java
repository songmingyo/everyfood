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
import org.springframework.dao.DataAccessException;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.AccSalesFirmbankingListVo;
import com.doppio.workplace.as.service.AccSalesDepRegVo;
import com.doppio.workplace.as.service.AccSalesFirmbankingListService;


/**
 * @author j10000
 * @Description 펌뱅킹입금현황  : AccSalesFirmbankingListService implement
 * @Class : AccSalesFirmbankingListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.31 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("accSalesFirmbankingListService")
public class AccSalesFirmbankingListServiceImpl  implements AccSalesFirmbankingListService{
	
	@Autowired
	AccSalesFirmbankingListMapper AccSalesFirmbankingListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  펌뱅킹입금현황  확인 
	 * @param AccSalesFirmbankingListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAccSalesFirmbankingList(AccSalesFirmbankingListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<AccSalesFirmbankingListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  AccSalesFirmbankingListMapper.selectaccSalesFirmbankingList(paramVo);
		
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
	 * 매출처별입금현황 저장
	 */
	@Override 
	public Result insertAccSalesFirmbankingRegInfo(AccSalesFirmbankingListVo paramVo) throws Exception{
		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/		
		
		List<AccSalesFirmbankingListVo> list = paramVo.getAccSalesFirmbankingRegList();
		
		int rowCnt = 0;
		int resultCnt = 0;
		
		for(AccSalesFirmbankingListVo accSalesFirmbankingListVo : list) {
			
			accSalesFirmbankingListVo.setWorkId(customUser.getUserId());
			
			try {
				resultCnt = AccSalesFirmbankingListMapper.insertAccSalesFirmbankingRegInfo(accSalesFirmbankingListVo);
			} catch (DataAccessException e) {
				result.setMsgCd("-2");
				result.setMessage("이미 반영된 데이터입니다..");
				
				result.setRtnValue01("0" );

				return result;
			}
			
			if(resultCnt > 0) {
				resultCnt = AccSalesFirmbankingListMapper.updateAccSalesFirmbankingFlagInfo(accSalesFirmbankingListVo);
				
				result.setMsgCd("0");
				result.setMessage("SUCCESS");
			} else {
				result.setMsgCd("-1");
				result.setMessage("매출처의 가상계좌를 확인하세요.");
			}
			
			rowCnt++;					
		}
		
		result.setRtnValue01( String.valueOf(rowCnt) );

		return result;
	}
	

}
