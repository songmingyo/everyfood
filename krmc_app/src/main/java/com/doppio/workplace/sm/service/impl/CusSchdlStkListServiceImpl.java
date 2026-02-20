package com.doppio.workplace.sm.service.impl;

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
import com.doppio.workplace.sm.service.CusSchdlStkListVo;
import com.doppio.workplace.sm.service.CusSchdlStkListService;

/*
 * @author j10000
 * @Description 출고예정 재고리스트: CusSchdlStklListServiceImpl implement
 * @Class : CusSchdlStklListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.04.23 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("cusSchdlStklListService")
public class CusSchdlStkListServiceImpl  implements CusSchdlStkListService{
	
	@Autowired
	CusSchdlStkListMapper CusSchdlStkListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  출고예정 재고리스트 
	 * @param CusSchdlStkListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusSchdlStkList(CusSchdlStkListVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<CusSchdlStkListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  CusSchdlStkListMapper.selectCusSchdlStkList(paramVo);
		
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
	 *  출고예정 재고리스트 엑셀 다운로드 
	 * @param CusSchdlStkListVo paramVo
	 * @return CusSchdlStklListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectCusSchdlStkListExcelDown(CusSchdlStkListVo paramVo) throws Exception {
		return CusSchdlStkListMapper.selectCusSchdlStkListExcelDown(paramVo);
	}

	

}
