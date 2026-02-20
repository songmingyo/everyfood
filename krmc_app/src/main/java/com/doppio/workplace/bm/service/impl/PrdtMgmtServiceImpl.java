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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.management.service.MgrCodeVo;
import com.doppio.workplace.bm.service.PrdtMgmtService;
import com.doppio.workplace.bm.service.PrdtMgmtVo;
import com.doppio.workplace.sample.service.SampleExcelVo;

/**
 * @author Song
 * @Description 상품관리 : PrdtMgmtService implement
 * @Class : PrdtMgmtServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("prdtMgmtService")
public class PrdtMgmtServiceImpl  implements PrdtMgmtService{
	
	@Autowired
	PrdtMgmtMapper prdtMgmtMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  상품 조회 
	 * @param DlvrMasterVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectPrdtMgmtList(PrdtMgmtVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<PrdtMgmtVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = prdtMgmtMapper.selectPrdtMgmtListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  prdtMgmtMapper.selectPrdtMgmtList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}
	
	/**
	 *  상품정보 상세조회
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	public PrdtMgmtVo selectPrdtMgmtData(PrdtMgmtVo paramVo) throws Exception {
		return  prdtMgmtMapper.selectPrdtMgmtData(paramVo);
	}
	
	/**
	 *  상품 최근입고 조회
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	public HashMap<String, Object> selectPrdtBuyList(PrdtMgmtVo paramVo) throws Exception {
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<PrdtMgmtVo> resultList = null;		
		
		resultList =  prdtMgmtMapper.selectPrdtBuyList(paramVo);
		
		returnMap.put("resultList", resultList);							//조회결과 데이터
		
		return returnMap;
	}

	/**
	 *  상품 최근출고 조회
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	public HashMap<String, Object> selectPrdtSalesList(PrdtMgmtVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<PrdtMgmtVo> resultList = null;
		
		
		resultList =  prdtMgmtMapper.selectPrdtSalesList(paramVo);
		
		returnMap.put("resultList", resultList);							//조회결과 데이터
		
		return returnMap;
		
	}
	
	/**
	 *  상품정보 저장 
	 *  TFM_DLVR_MST.CAR_CD PK 조건 
	 *  INSERT/UPDATE (INSERT BEFOR CAR_CD)
	 * @param DlvrMasterVo paramVo
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	public Result insertPrdtMgmt(PrdtMgmtVo paramVo) throws Exception {

		Result result = new Result();
		result.setMsgCd("fall");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		int executeCnt = 0;
		if(StringUtil.isNotEmpty(paramVo.getPrdtCd()) ) 
			 executeCnt = prdtMgmtMapper.updatePrdtMgmt(paramVo);		// 수정 
		else executeCnt = prdtMgmtMapper.insertPrdtMgmt(paramVo);		// 신규 생성 ( BEFOR select CAR_CD ) 

		if(executeCnt == 1) {
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getPrdtCd());	// 수정,등록 CAR_CD 
		}

		return result;
	}
	
	
	/**
	 *  상품 엑셀 다운로드 
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectPrdtMgmtListExcelDown(PrdtMgmtVo paramVo) throws Exception {
		return prdtMgmtMapper.selectPrdtMgmtListExcelDown(paramVo);
	}


}
