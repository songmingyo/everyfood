package com.doppio.workplace.cs.service.impl;

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
import com.doppio.workplace.cs.service.LogWhMvRegService;
import com.doppio.workplace.cs.service.LogWhMvRegVo;

/**
 * @author Song
 * @Description 센터이동 등록 관리 : LogWhMvRegServiceImpl implement
 * @Class : LogWhMvRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("logWhMvRegService")
public class LogWhMvRegServiceImpl  implements LogWhMvRegService{
	
	@Autowired
	LogWhMvRegMapper logWhMvRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  센터이동 등록 조회 
	 * @param LogWhMvRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectLogWhMvRegList(LogWhMvRegVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<LogWhMvRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		resultList = logWhMvRegMapper.selectLogWhMvRegList(paramVo);
		
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
	 *  센터이동 등록 단품 품목 조회 
	 * @param LogWhMvRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectLogWhMvRegPrdtAddList(LogWhMvRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		LogWhMvRegVo logWhMvRegVo = logWhMvRegMapper.selectLogWhMvRegPrdtAddList(paramVo);
		
		resultList.put("logWhMvRegVo", logWhMvRegVo);
		
		return resultList;
		
	}
	
	/**
	 *  센터이동 등록 정보 저장
	 * @param LogWhMvRegVo paramVo
	 * @return LogWhMvRegVo
	 * @throws Exception
	 */
	public Result insertLogWhMvReg(LogWhMvRegVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/		
		
		List<LogWhMvRegVo> list = paramVo.getLogWhMvRegList();		
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		for(LogWhMvRegVo logWhMvRegVo : list) {
			
			logWhMvRegVo.setWorkId(customUser.getUserId());
			logWhMvRegVo.setMvDt(paramVo.getMvDt());
			logWhMvRegVo.setUseYn("Y");
			logWhMvRegVo.setMvConfYn("Y");
				
			if(logWhMvRegVo.getMvQty() != logWhMvRegVo.getMvQtyOrg()){
				logWhMvRegMapper.insertLogWhMvRegData(logWhMvRegVo);
				
				insertCnt++;
			}			
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}


}
