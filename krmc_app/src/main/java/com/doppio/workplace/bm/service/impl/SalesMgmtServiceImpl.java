package com.doppio.workplace.bm.service.impl;

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
import com.doppio.workplace.as.service.SalSalesPrdtPrftListVo;
import com.doppio.workplace.bm.service.SalesMgmtService;
import com.doppio.workplace.bm.service.SalesMgmtVo;

/**
 * @author j10000
 * @Description 매출처관리 : salesMgmtService implement
 * @Class : SalesMgmtServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.17 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("salesMgmtService")
public class SalesMgmtServiceImpl  implements SalesMgmtService{
	
	@Autowired
	SalesMgmtMapper salesMgmtMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출처 조회 
	 * @param SalesMgmtVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalesMgmtList(SalesMgmtVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalesMgmtVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList =  salesMgmtMapper.selectSalesMgmtList(paramVo);
		
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
	 *  매출처 마스터 상세조회
	 * @param SalesMgmtVo paramVo
	 * @return SalesMgmtVo
	 * @throws Exception
	 */
	public SalesMgmtVo selectSalesMgmtData(SalesMgmtVo paramVo) throws Exception {
		return  salesMgmtMapper.selectSalesMgmtData(paramVo);
	}

	/**
	 *  매출처 정보 저장 
	 *  TFM_SALES_MST.SALES_CD PK 조건 
	 *  INSERT/UPDATE (INSERT BEFOR CAR_CD)
	 * @param SalesMgmtVo paramVo
	 * @return SalesMgmtVo
	 * @throws Exception
	 */
	public Result insertSalesMgmt(HttpServletRequest request, SalesMgmtVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("fall");
		
		// 작업자정보 ==============================
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String regId = StringUtils.defaultString(customUser.getUserId());			// 작업자 아이디
		paramVo.setWorkId(regId);	//작업자정보셋팅
		// 작업자정보 ==============================
		
		int executeCnt = 0;
		
		int warrAmt			=	paramVo.getWarrAmt().equals("") || paramVo.getWarrAmt() == null  ? 0 : Integer.parseInt(paramVo.getWarrAmt());
		int storeSalesAmt	=	paramVo.getExpSalesAmt().equals("") || paramVo.getExpSalesAmt() == null  ? 0 : Integer.parseInt(paramVo.getExpSalesAmt());
		
		paramVo.setWarrAmt(String.valueOf(warrAmt));
		paramVo.setExpSalesAmt(String.valueOf(storeSalesAmt));
		
		if(StringUtil.isNotEmpty(paramVo.getSalesCd())) {
			salesMgmtMapper.updateSalesMgmt(paramVo);
			salesMgmtMapper.updateSalesAddInfo(paramVo);
			executeCnt = salesMgmtMapper.updateSalesHqClass(paramVo);
			
			salesMgmtMapper.updateSalesMember(paramVo);
		} else {
			salesMgmtMapper.insertSalesMgmt(paramVo); 
			salesMgmtMapper.insertSalesAddInfo(paramVo);
			executeCnt = salesMgmtMapper.insertSalesHqClass(paramVo);
			
			salesMgmtMapper.insertSalesMember(paramVo);
			salesMgmtMapper.insertSalesMemberAuth(paramVo);
			
		}

		if(executeCnt == 1) {
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getSalesCd());	// 수정,등록 CAR_CD 
		}

		return result;
	}
	
	/**
	 *  매출처 품목별이익현황 엑셀 다운로드 
	 * @param SalesMgmtVo paramVo
	 * @return SalesMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalesMgmtListExcelDown(SalesMgmtVo paramVo) throws Exception {
		return salesMgmtMapper.selectSalesMgmtListExcelDown(paramVo);
	}
	
}
