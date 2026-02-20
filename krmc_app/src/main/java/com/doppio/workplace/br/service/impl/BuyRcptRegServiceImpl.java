package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptRegService;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;

/**
 * @author Song
 * @Description 매입처 입고 관리 : BuyRcptRegServiceImpl implement
 * @Class : BuyRcptRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("buyRcptRegService")
public class BuyRcptRegServiceImpl  implements BuyRcptRegService{
	
	@Autowired
	BuyRcptRegMapper buyRcptRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처 입고 조회 
	 * @param BuyRcptRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyRcptRegList(BuyRcptRegVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();		
		List<BuyRcptRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = buyRcptRegMapper.selectBuyRcptList(paramVo);
		
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
	 *  매입처 입고 상세 조회 
	 * @param BuyRcptRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public List<BuyRcptRegVo> selectBuyRcptDetailList(BuyRcptRegVo paramVo) throws Exception {
		
		List<BuyRcptRegVo> resultList = null;
		
		resultList = buyRcptRegMapper.selectBuyRcptDetailList(paramVo);
		
		return resultList;
		
	}

	
	/**
	 *  매입처 입고 정보 저장 
	 * @param BuyRcptRegVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertBuyRcptReg(BuyRcptRegVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getBuyRcptRegList() == null || paramVo.getBuyRcptRegList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("입고데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		String whCd = paramVo.getWhCd();
		
		List<BuyRcptRegVo> list = paramVo.getBuyRcptRegList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		 
//		int ordListCnt = list.size();
//		
//		String buySlipNo = list.get(ordListCnt-1).getBuySlipNo();
		
		for(BuyRcptRegVo buyRcptRegVo : list) {
			
			buyRcptRegVo.setWorkId(customUser.getUserId());
			buyRcptRegVo.setWhCd(whCd);
				
			if("U".equals(buyRcptRegVo.getGridFlag())){
				updateCnt = updateCnt + buyRcptRegMapper.updateBuyRcptRegData(buyRcptRegVo);
			} else if("I".equals(buyRcptRegVo.getGridFlag())){
				buyRcptRegMapper.insertBuyRcptSlipNo(buyRcptRegVo);
				
				insertCnt = insertCnt + buyRcptRegMapper.insertBuyRcptRegData(buyRcptRegVo);
			}
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	
	/**
	 *  매입처 입고 단품 품목 조회 
	 * @param BuyRcptRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyRcptRegPrdtAddList(BuyRcptRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		BuyRcptRegVo buyRcptRegVo = buyRcptRegMapper.selectBuyRcptRegPrdtAddList(paramVo);
		
		resultList.put("buyRcptRegVo", buyRcptRegVo);
		
		return resultList;
		
	}	

}
