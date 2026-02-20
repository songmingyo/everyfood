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
import com.doppio.management.service.MgrCodeVo;
import com.doppio.workplace.bm.service.BuyMgmtVo;
import com.doppio.workplace.as.service.AccBuyWdrlListVo;
import com.doppio.workplace.bm.service.BuyMgmtService;


/**
 * @author j10000
 * @Description 매입처관리 : buyMgmtService implement
 * @Class : BuyMgmtServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.19 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("buyMgmtService")
public class BuyMgmtServiceImpl  implements BuyMgmtService{
	
	@Autowired
	BuyMgmtMapper BuyMgmtMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매입처 조회 
	 * @param BuyOrderVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectBuyMgmtList(BuyMgmtVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<BuyMgmtVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList =  BuyMgmtMapper.selectBuyMgmtList(paramVo);
		
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
	 *  매입처 마스터 상세조회
	 * @param BuyOrderVo paramVo
	 * @return BuyMgmtVo
	 * @throws Exception
	 */
	public BuyMgmtVo selectBuyMgmtData(BuyMgmtVo paramVo) throws Exception {
		return  BuyMgmtMapper.selectBuyMgmtData(paramVo);
	}

	/**
	 *  매입처 정보 저장 
	 *  TFM_BUY_MST.BUY_CD PK  
	 *  INSERT/UPDATE (INSERT BEFOR CAR_CD)
	 * @param BuyOrderVo paramVo
	 * @return BuyMgmtVo
	 * @throws Exception
	 */
	public Result insertBuyMgmt(HttpServletRequest request, BuyMgmtVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("fall");
		
		// 작업자정보 ==============================
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String regId = StringUtils.defaultString(customUser.getUserId());			// 작업자 아이디
		paramVo.setRegId(regId);	//작업자정보셋팅
		paramVo.setModId(regId);	//작업자정보셋팅
		// 작업자정보 ==============================
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		if(StringUtil.isNotEmpty(paramVo.getBuyCd()) ) 
			updateCnt = BuyMgmtMapper.updateBuyMgmt(paramVo);		// 수정 
		else insertCnt = BuyMgmtMapper.insertBuyMgmt(paramVo);		// 신규 생성 ( BEFOR select CAR_CD ) 

		if(insertCnt == 1) {
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getBuyCd());	// 수정,등록 CAR_CD
			
			BuyMgmtMapper.insertMember(paramVo);
			BuyMgmtMapper.insertMemberAuth(paramVo);
		} else if(updateCnt == 1){
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getBuyCd());	// 수정,등록 CAR_CD
			
			BuyMgmtMapper.updateMember(paramVo);
		}

		return result;
	}
	
	
	/**
	 *  매입처 관리 Excel DownLoad add by song min kyo 2024.09.22
	 *  TFM_BUY_MST.BUY_CD PK  
	 *  INSERT/UPDATE (INSERT BEFOR CAR_CD)
	 * @param BuyOrderVo paramVo
	 * @return BuyMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectBuyMgmtListExcelDown(BuyMgmtVo paramVo) throws Exception {
		return BuyMgmtMapper.selectBuyMgmtListExcelDown(paramVo);
	}
}
