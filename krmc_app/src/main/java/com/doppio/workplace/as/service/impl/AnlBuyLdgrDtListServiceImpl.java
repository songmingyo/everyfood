package com.doppio.workplace.as.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.doppio.workplace.as.service.AnlBuyLdgrDtListVo;
import com.doppio.workplace.as.service.AnlAccRecvListVo;
import com.doppio.workplace.as.service.AnlBuyLdgrDtListService;


/**
 * @author j10000
 * @Description 매입처원장(일별)  : AnlBuyLdgrDtListServiceImpl implement
 * @Class : AnlBuyLdgrDtListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.05.20 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("anlBuyLdgrDtListService")
public class AnlBuyLdgrDtListServiceImpl  implements AnlBuyLdgrDtListService{
	
	@Autowired
	AnlBuyLdgrDtListMapper AnlBuyLdgrDtListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처원장(일별)  확인 
	 * @param AccSalesDepListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAnlBuyLdgrDtList(AnlBuyLdgrDtListVo paramVo) throws Exception {
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<AnlBuyLdgrDtListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList =  AnlBuyLdgrDtListMapper.selectAnlBuyLdgrDtList(paramVo);
		
		totalCount = resultList.size();
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		
		String buyCdTemp = "";
		String buyCd = "";
		String sort = "";
		double paidAmt1 = 0;
		double paidAmt2 = 0;
		double paidAmt3 = 0;
		double paidAmt4 = 0;
		double balAmt = 0;
		double balAmtLast = 0;
		
		for(int i=0; i<resultList.size(); i++) {
			buyCd = resultList.get(i).getBuyCd();
			sort = resultList.get(i).getSort();
						
			balAmt = Double.parseDouble(resultList.get(i).getBalAmt());
			paidAmt1 = Double.parseDouble(resultList.get(i).getPaidAmt1());
			paidAmt2 = Double.parseDouble(resultList.get(i).getPaidAmt2());
			paidAmt3 = Double.parseDouble(resultList.get(i).getPaidAmt3());
			paidAmt4 = Double.parseDouble(resultList.get(i).getPaidAmt4());
			
			if(!buyCdTemp.equals(buyCd)) {
				balAmtLast = 0;
				balAmtLast = balAmt;
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
				buyCdTemp = buyCd;
			}else if("2".equals(sort) || "3".equals(sort) ) {
				balAmtLast = balAmtLast + balAmt;
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
			}else if("4".equals(sort)) {
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);				
			}else if("4".equals(sort) && !buyCdTemp.equals(buyCd)) {
				balAmtLast = 0;
				balAmtLast = balAmt;
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
			}
			
			resultList.get(i).setBalAmt(Double.toString(balAmtLast));
			
		}
		
		return returnMap;
		
	}
	
	/**
	 *  매입처원장(일별)  출력
	 * @param AccSalesDepListVo paramVo
	 * @return List<AnlBuyLdgrDtListVo>
	 * @throws Exception
	 */
	public List<AnlBuyLdgrDtListVo> selectbuyLdgrListPrint(AnlBuyLdgrDtListVo paramVo) throws Exception {
		List<AnlBuyLdgrDtListVo> returnList = new ArrayList<AnlBuyLdgrDtListVo>();
		
		returnList = AnlBuyLdgrDtListMapper.selectbuyLdgrListPrint(paramVo);
		/*
		for(int i = 0; i < returnList.size(); i++) {
			String balAmt = returnList.get(i).getBalAmt();
			
			returnList.get(i).setBalAmt(balAmt);
		}*/
		
		String buyCdTemp = "";
		String buyCd = "";
		String sort = "";
		int paidAmt1 = 0;
		int paidAmt2 = 0;
		int paidAmt3 = 0;
		int paidAmt4 = 0;
		int balAmt = 0;
		int balAmtLast = 0;
		
		for (AnlBuyLdgrDtListVo vo : returnList) {
//			String balAmt = vo.getBalAmt();
//			
//			vo.setBalAmt(balAmt);
			
			buyCd = vo.getBuyCd();
			sort = vo.getSort();
						
			balAmt = vo.getPrtBalAmt();
			paidAmt1 = vo.getPrtPaidAmt1();
			paidAmt2 = vo.getPrtPaidAmt2();
			paidAmt3 = vo.getPrtPaidAmt3();
			paidAmt4 = vo.getPrtPaidAmt4();
			
			if(!buyCdTemp.equals(buyCd)) {
				balAmtLast = 0;
				balAmtLast = balAmt;
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
				buyCdTemp = buyCd;
			}else if("2".equals(sort) || "3".equals(sort) ) {
				balAmtLast = balAmtLast + balAmt;
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
			}else if("4".equals(sort)) {
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);				
			}else if("4".equals(sort) && !buyCdTemp.equals(buyCd)) {
				balAmtLast = 0;
				balAmtLast = balAmt;
				balAmtLast = balAmtLast - (paidAmt1 + paidAmt2 + paidAmt3 + paidAmt4);
			}
			
			vo.setPrtBalAmt(balAmtLast);
		}
		
		return returnList;
	}
	
	/**
	 * 매입처원장(일별) 엑셀 다운로드 
	 * @param AnlBuyLdgrDtListVo paramVo
	 * @return AnlBuyLdgrDtListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectAnlBuyLdgrDtListExcelDown(AnlBuyLdgrDtListVo paramVo) throws Exception {
//		List<HashMap<String, String>> returnList = new ArrayList<HashMap<String,String>>();
//		
//		returnList = AnlBuyLdgrDtListMapper.selectAnlBuyLdgrDtListExcelDown(paramVo);
//		
//		for (HashMap<String, String> hashMap : returnList) {
//			String PRDT_NM = hashMap.get("PRDT_NM");
//			
//			hashMap.put("PRDT_NM", PRDT_NM);
//		}
		
		return AnlBuyLdgrDtListMapper.selectAnlBuyLdgrDtListExcelDown(paramVo);
	}

	
}
