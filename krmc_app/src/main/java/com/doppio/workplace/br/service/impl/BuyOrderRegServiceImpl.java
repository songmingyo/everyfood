package com.doppio.workplace.br.service.impl;

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
import com.doppio.workplace.br.service.BuyOrderRegService;
import com.doppio.workplace.br.service.BuyOrderRegVo;

/**
 * @author Song
 * @Description 매입처 발주 관리 : BuyOrderRegServiceImpl implement
 * @Class : BuyOrderRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("buyOrderRegService")
public class BuyOrderRegServiceImpl  implements BuyOrderRegService{
	
	@Autowired
	BuyOrderRegMapper buyOrderRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처 발주 조회 
	 * @param SalSalesGoalListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyOrderRegList(BuyOrderRegVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<BuyOrderRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		if("10".equals(paramVo.getOrdYn())) {
			resultList = buyOrderRegMapper.selectBuyOrderRegOrdList(paramVo);
		} else {
			resultList = buyOrderRegMapper.selectBuyOrderRegNotOrdList(paramVo);
		}
		
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
	 *  매입처 발주 정보 저장 (매입 입고도 같이 저장됨)
	 * @param SalSalesGoalListVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertBuyOrderReg(BuyOrderRegVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getBuyOrderRegList() == null || paramVo.getBuyOrderRegList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("발주데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/		
		
		List<BuyOrderRegVo> list = paramVo.getBuyOrderRegList();		
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		String ordQqty = "";
		String ordQqtyOrg = "";
		
//		int ordListCnt = list.size();
		
//		String buySlipNo = list.get(ordListCnt-1).getBuySlipNo();
		 
		for(BuyOrderRegVo buyOrderRegVo : list) {
			
			ordQqty = buyOrderRegVo.getOrdQty();
			ordQqtyOrg = buyOrderRegVo.getOrdQtyOrg();
			
			if( !ordQqty.equals(ordQqtyOrg) ) {
				buyOrderRegVo.setWorkId(customUser.getUserId());
				
				if("U".equals(buyOrderRegVo.getGridFlag())){
					buyOrderRegMapper.updateBuyOrderRegData(buyOrderRegVo);					
					updateCnt = updateCnt + buyOrderRegMapper.updateBuyRcptRegData(buyOrderRegVo);
				} else if("I".equals(buyOrderRegVo.getGridFlag())){
					buyOrderRegMapper.insertBuyOrderSlipNo(buyOrderRegVo);
					
					buyOrderRegMapper.insertBuyOrderRegData(buyOrderRegVo);
					insertCnt = insertCnt + buyOrderRegMapper.insertBuyRcptRegData(buyOrderRegVo);
				}
			}			
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	/**
	 *  매입처 발주 정보 비고 저장 
	 * @param SalSalesGoalListVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertBuyOrdNoteReg(BuyOrderRegVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		int rowCount = buyOrderRegMapper.selectBuyOrderRegNoteCount(paramVo);
		
		if(rowCount > 0) {
			updateCnt = buyOrderRegMapper.updateBuyOrderRegNote(paramVo);
		} else{
			insertCnt = buyOrderRegMapper.insertBuyOrderRegNote(paramVo);
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	/**
	 *  매입처 발주 단품 품목 조회 
	 * @param SalSalesGoalListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyOrderRegPrdtAddList(BuyOrderRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		BuyOrderRegVo buyOrderRegVo = buyOrderRegMapper.selectBuyOrderRegPrdtAddList(paramVo);
		
		resultList.put("buyOrderRegVo", buyOrderRegVo);
		
		return resultList;
		
	}
	
	/**
	 *  매입처 발주서 출력 건수
	 * @param SalSalesGoalListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public Result selectBuyOrderListPrintCnt(BuyOrderRegVo paramVo) throws Exception {
		
		
		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		int rowCount = buyOrderRegMapper.selectBuyOrderListPrintCnt(paramVo);
		
		result.setRtnValue01( String.valueOf(rowCount) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
		
	}

	
	/**
	 * 발주서 출력 조회
	 * @param SalSalesGoalListVo paramVo
	 * @return List<BuyOrderRegVo>
	 */
	@Override
	public List<BuyOrderRegVo> selectOrdPrintList(BuyOrderRegVo paramVo) {
		return buyOrderRegMapper.selectBuyOrderRegOrdPrtList(paramVo);
	}
	
	/**
	 *  상품 엑셀 다운로드 
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectBuyOrdRegListExcelDown(BuyOrderRegVo paramVo) throws Exception {
		return buyOrderRegMapper.selectBuyOrdRegListExcelDown(paramVo);
	}
	
	/**
	 *  상품 엑셀 다운로드 
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectBuyOrdRegNotListExcelDown(BuyOrderRegVo paramVo) throws Exception {
		return buyOrderRegMapper.selectBuyOrdRegNotListExcelDown(paramVo);
	}

}
