package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.SalSalesCarShipListService;
import com.doppio.workplace.as.service.SalSalesCarShipListVo;
import com.doppio.workplace.bm.service.PrdtMgmtVo;

/**
 * @author Song
 * @Description 차량별 배송현황표 : SalSalesCarShipListServiceImpl implement
 * @Class : SalSalesCarShipListServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */



@Service("salSalesCarShipListService")
public class SalSalesCarShipListServiceImpl  implements SalSalesCarShipListService{
	
	@Autowired
	SalSalesCarShipListMapper salSalesCarShipListMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  차량별 배송현황표 조회 
	 * @param SalSalesCarShipListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalSalesCarShipList(SalSalesCarShipListVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalSalesCarShipListVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = salSalesCarShipListMapper.selectSalSalesCarShipList(paramVo);
		
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
	 *  차량별 배송현황표 Footer
	 * @param SalSalesCarShipListVo paramVo
	 * @return SalSalesCarShipListVo
	 * @throws Exception
	 */
	public SalSalesCarShipListVo selectSalSalesCarShipFooter(SalSalesCarShipListVo paramVo) throws Exception {
		return  salSalesCarShipListMapper.selectSalSalesCarShipFooter(paramVo);
	}

	/**
	 *  차량별 배송현황표 엑셀 다운로드 
	 * @param SalSalesCarShipListVo paramVo
	 * @return SalSalesCarShipListVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalSalesCarShipListExcelDown(SalSalesCarShipListVo paramVo) throws Exception {
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		paramVo.setUserType(customUser.getUserType());
		
		return salSalesCarShipListMapper.selectSalSalesCarShipListExcelDown(paramVo);
	}

}
