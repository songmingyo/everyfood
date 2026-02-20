package com.doppio.workplace.br.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.br.service.BuyRtnRegService;
import com.doppio.workplace.br.service.BuyRtnRegVo;
import com.doppio.workplace.sm.service.CusRtnListVo;

/**
 * @author Song
 * @Description 매입처 반품 관리 : BuyRtnRegServiceImpl implement
 * @Class : BuyRtnRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("buyRtnRegService")
public class BuyRtnRegServiceImpl  implements BuyRtnRegService{
	
	@Autowired
	BuyRtnRegMapper buyRtnRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처 반품 조회 
	 * @param BuyRtnRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyRtnRegList(BuyRtnRegVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<BuyRtnRegVo> resultList = null;
		
		int rowCount = 0;
		
		rowCount = buyRtnRegMapper.selectBuyRtnListCount(paramVo);
		
		if(rowCount > 0) {
			resultList = buyRtnRegMapper.selectBuyRtnList(paramVo);
		}
		
		returnMap.put("resultList", resultList);							//조회결과 데이터
		
		return returnMap;
		
	}
	
	/**
	 *  매입처 반품 상세 조회 
	 * @param BuyRtnRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public List<BuyRtnRegVo> selectBuyRtnDetailList(BuyRtnRegVo paramVo) throws Exception {
		
		List<BuyRtnRegVo> resultList = null;
		
		resultList = buyRtnRegMapper.selectBuyRtnDetailList(paramVo);
		
		return resultList;
		
	}

	
	/**
	 *  매입처 반품 정보 저장 
	 * @param BuyRtnRegVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertBuyRtnReg(BuyRtnRegVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getBuyRtnRegList() == null || paramVo.getBuyRtnRegList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("반품 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		String whCd = paramVo.getWhCd();
		String rtnClass = paramVo.getRtnClass();
		String rtnDt = paramVo.getRtnDt();
		
		List<BuyRtnRegVo> list = paramVo.getBuyRtnRegList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		
//		String buySlipNo = list.get(0).getBuySlipNo();
		
		for(BuyRtnRegVo buyRcptRegVo : list) {
			
			buyRcptRegVo.setWorkId(customUser.getUserId());
			buyRcptRegVo.setWhCd(whCd);
			 
			if("".equals(buyRcptRegVo.getRtnClass()) || buyRcptRegVo.getRtnClass() == null) {
				buyRcptRegVo.setRtnClass(rtnClass);
			}				
			if("".equals(buyRcptRegVo.getRtnDt())) {
				buyRcptRegVo.setRtnDt(rtnDt);
			}

//			if("".equals(buySlipNo) || StringUtil.isEmpty(buySlipNo)) {
			
			
				
//				buySlipNo = buyRcptRegVo.getBuySlipNo();
//			} else {
//				buyRcptRegVo.setBuySlipNo(buySlipNo);
//			}
			
			if("U".equals(buyRcptRegVo.getGridFlag())){
				updateCnt = updateCnt + buyRtnRegMapper.updateBuyRtnRegData(buyRcptRegVo);
			} else if("I".equals(buyRcptRegVo.getGridFlag())){
				buyRtnRegMapper.insertBuyRtnSlipNo(buyRcptRegVo);
				
				insertCnt = insertCnt + buyRtnRegMapper.insertBuyRtnRegData(buyRcptRegVo);
			}
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	
	/**
	 *  매입처 반품 품목 조회 
	 * @param BuyRtnRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyRtnRegPrdtAddList(BuyRtnRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		BuyRtnRegVo buyRtnRegVo = buyRtnRegMapper.selectBuyRtnRegPrdtAddList(paramVo);
		
		resultList.put("buyRtnRegVo", buyRtnRegVo);
		
		return resultList;
		
	}	
	
	
	/**
	 * 매입처 반품현황 add by song min kyo 2025.08.26
	 * @param BuyRtnRegVo	paramVo
	 * @return BuyRtnRegVo
	 * @throws Exception
	 */
	public HashMap<String, Object>  selectBuyRtnList_2(BuyRtnRegVo paramVo) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<BuyRtnRegVo> resultList = null;
		
//		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
//		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
//		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  buyRtnRegMapper.selectBuyRtnList_2(paramVo);
		
//		int totalCount = resultList.size();
		
//		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
//		int startRowNum = paging.getStartRowNum();
//		int endRowNum = paging.getEndRowNum();
				
//		paramVo.setStartRowNo(startRowNum);
//		paramVo.setEndRowNo(endRowNum);
		
//		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
//		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	/**
	 * 매입처반품현황 excel
	 * @param BuyRtnRegVo paramVo
	 * @return BuyRtnRegVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectBuyRtnListExcelDown(BuyRtnRegVo paramVo) throws Exception {
		return buyRtnRegMapper.selectBuyRtnListExcelDown(paramVo);
	}

}
