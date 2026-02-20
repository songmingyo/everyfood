package com.doppio.workplace.sm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.doppio.workplace.sm.service.CusPriceUptService;
import com.doppio.workplace.sm.service.CusPriceUptVo;

/**
 * @author Song
 * @Description 매출 단가 일괄 수정 : CusPriceUptServiceImpl implement
 * @Class : CusPriceUptServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("cusPriceUptService")
public class CusPriceUptServiceImpl  implements CusPriceUptService{
	
	@Autowired
	CusPriceUptMapper cusPriceUptMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출 단가 일괄 조회 
	 * @param CusPriceUptVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusPriceUptList(CusPriceUptVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<CusPriceUptVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList = cusPriceUptMapper.selectCusPriceUptList(paramVo);
		
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
	 *  매출 단가 일괄 정보 저장 
	 * @param CusPriceUptVo paramVo
	 * @throws Exception
	 */
	public Result updateCusPriceUpt(CusPriceUptVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getCusPriceUptList() == null || paramVo.getCusPriceUptList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("조회 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		List<CusPriceUptVo> list = paramVo.getCusPriceUptList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		for(CusPriceUptVo cusPriceUptVo : list) {
			if("U".equals(cusPriceUptVo.getGridFlag())) {
				cusPriceUptVo.setWorkId(customUser.getUserId());
				
				updateCnt = cusPriceUptMapper.updateCusPriceUptData(cusPriceUptVo);
			}
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	

}
