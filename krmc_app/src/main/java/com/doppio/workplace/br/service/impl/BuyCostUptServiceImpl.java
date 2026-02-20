package com.doppio.workplace.br.service.impl;

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
import com.doppio.workplace.br.service.BuyCostUptService;
import com.doppio.workplace.br.service.BuyCostUptVo;
import com.doppio.workplace.br.service.BuyRtnRegVo;

/**
 * @author Song
 * @Description 매입 단가 일괄 수정 : BuyCostUptServiceImpl implement
 * @Class : BuyCostUptServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("buyCostUptService")
public class BuyCostUptServiceImpl  implements BuyCostUptService{
	
	@Autowired
	BuyCostUptMapper buyCostUptMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입 단가 일괄 조회 
	 * @param CusPriceUptVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyCostUptList(BuyCostUptVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<BuyCostUptVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		totalCount = buyCostUptMapper.selectBuyCostUptListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount > 0) {
			resultList = buyCostUptMapper.selectBuyCostUptList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}

	
	/**
	 *  매입 단가 일괄 정보 저장 
	 * @param CusPriceUptVo paramVo
	 * @throws Exception
	 */
	public Result updateBuyCostUpt(BuyCostUptVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getBuyCostUptList() == null || paramVo.getBuyCostUptList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("조회 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		List<BuyCostUptVo> list = paramVo.getBuyCostUptList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		for(BuyCostUptVo buyCostUptVo : list) {
			if("U".equals(buyCostUptVo.getGridFlag())) {
				buyCostUptVo.setWorkId(customUser.getUserId());
				
				updateCnt = updateCnt + buyCostUptMapper.updateBuyCostUptData(buyCostUptVo);
			}
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	

}
