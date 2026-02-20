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
import com.doppio.workplace.sm.service.CusPriceUnconfVo;
import com.doppio.workplace.sm.service.CusOrdListVo;
import com.doppio.workplace.sm.service.CusPriceUnconfService;


/**
 * @author j10000
 * @Description 매출단가미확정건 확인서 : CusPriceUnconfServiceImpl implement
 * @Class : CusPriceUnconfServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.04.30 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("cusPriceUnconfService")
public class CusPriceUnconfServiceImpl  implements CusPriceUnconfService{
	
	@Autowired
	CusPriceUnconfMapper CusPriceUnconfMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출단가미확정건 확인 
	 * @param CusPriceUnconfVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusPriceUnconfList(CusPriceUnconfVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<CusPriceUnconfVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  CusPriceUnconfMapper.selectCusPriceUnconfList(paramVo);
		
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
	 * 매출단가미확정건 엑셀 다운로드 
	 * @param CusPriceUnconfVo paramVo
	 * @return CusPriceUnconfVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectCusPriceUnconfListExcelDown(CusPriceUnconfVo paramVo) throws Exception {
		return CusPriceUnconfMapper.selectCusPriceUnconfListExcelDown(paramVo);
	}
	
	/**
	 * 매출단가미확정건 출력 조회
	 * @param CusPriceUnconfVo paramVo
	 * @return List<CusPriceUnconfVo>
	 */
	@Override
	public List<CusPriceUnconfVo> selectCusPricePrintList(CusPriceUnconfVo paramVo) {
		return CusPriceUnconfMapper.selectCusPricePrintList(paramVo);
	}


}
