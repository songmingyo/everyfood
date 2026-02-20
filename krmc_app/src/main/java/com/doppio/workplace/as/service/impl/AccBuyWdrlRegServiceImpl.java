package com.doppio.workplace.as.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.as.service.AccBuyWdrlRegVo;
import com.doppio.workplace.as.service.AccBuyWdrlRegService;


/**
 * @author j10000
 * @Description 매입처별지급현황  : AccBuyWdrlRegService implement
 * @Class : AccBuyWdrlRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.31 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("accBuyWdrlRegService")
public class AccBuyWdrlRegServiceImpl  implements AccBuyWdrlRegService{
	
	@Autowired
	AccBuyWdrlRegMapper AccBuyWdrlRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처별지급현황  확인 
	 * @param AccBuyWdrlRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAccBuyWdrlReg(AccBuyWdrlRegVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<AccBuyWdrlRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  AccBuyWdrlRegMapper.selectAccBuyWdrlReg(paramVo);
		
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
	 * @Method : selectCusShipRegDetailList
	 * @Description : 매출처출고등록 상품 상세  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<AccBuyWdrlRegVo> selectAccBuyWdrlRegDetail(AccBuyWdrlRegVo paramVo){
		return  AccBuyWdrlRegMapper.selectAccBuyWdrlRegDetail(paramVo);
	}
	
	
	
	
	/**
	 * 매입처별지급현황 저장
	 */
	@Override 
	public Result insertAccBuyWdrlRegInfo(AccBuyWdrlRegVo paramVo) throws Exception{
		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/		
		
		List<AccBuyWdrlRegVo> list = paramVo.getAccBuyWdrlRegList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		for(AccBuyWdrlRegVo accBuyWdrlRegVo : list) {
			
			accBuyWdrlRegVo.setWorkId(customUser.getUserId());

			accBuyWdrlRegVo.setUseYn("Y");
				
			if("U".equals(accBuyWdrlRegVo.getGridFlag())){
				AccBuyWdrlRegMapper.updateAccBuyWdrlReg(accBuyWdrlRegVo);					
				
				updateCnt++;
			} else if("I".equals(accBuyWdrlRegVo.getGridFlag())){
				if(AccBuyWdrlRegMapper.selectAccBuyWdrlRegCount(accBuyWdrlRegVo) >= 1) {
					result.setMsgCd("1");
					result.setMessage("이미 입력된 데이타가 있습니다.");
					return result;
				}
				
				AccBuyWdrlRegMapper.insertAccBuyWdrlReg(accBuyWdrlRegVo);
					
				insertCnt++;
			}			
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}


}
