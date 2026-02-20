package com.doppio.workplace.bm.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.context.support.MessageSourceAccessor;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.bm.service.LogisticsWarehouseService;
import com.doppio.workplace.bm.service.LogisticsWarehouseVo;
import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;


/**
 * @author Song
 * @Description :물류창고 관리 LogisticsWarehouseService implement
 * @Class : LogisticsWarehouseServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("logisticsWarehouseServiceImpl")
public class LogisticsWarehouseServiceImpl  implements LogisticsWarehouseService{
	
	@Autowired
	LogisticsWarehouseMapper logisticsWarehouseMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;		//message properties
	
	/**
	 *  물류창고 조회 
	 * @param DlvrMasterVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectLogisticsWarehouseList(LogisticsWarehouseVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<LogisticsWarehouseVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = logisticsWarehouseMapper.selectLogisticsWarehouseListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  logisticsWarehouseMapper.selectLogisticsWarehouseList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}

	
	/**
	 * 물류창고 저장
	 */
	@Override 
	public Result insertLogisticsWarehouseInfo(HttpServletRequest request, LogisticsWarehouseVo paramVo) throws Exception{
		// 작업자정보 ==============================
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String regId = StringUtils.defaultString(customUser.getUserId());			// 작업자 아이디
		paramVo.setRegId(regId);	//작업자정보셋팅
		paramVo.setModId(regId);	//작업자정보셋팅
		// 작업자정보 ==============================
			
		// 결과값 기본 셋팅
		Result result = new Result();
		result.setMsgCd("fail");
		
		int executeCnt = 0;
		
		if(StringUtil.isNotEmpty(paramVo.getWhCd())) 
			 executeCnt = logisticsWarehouseMapper.updateLogisticsWarehouseInfo(paramVo);	// 수정 
		else executeCnt = logisticsWarehouseMapper.insertLogisticsWarehouseInfo(paramVo);	// 신규 생성 
		
		if(executeCnt == 1) {
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getWhCd());
		}
		
		return result;
	}



}
