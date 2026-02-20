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
import com.doppio.workplace.bm.service.ClassmMgmtVo;
import com.doppio.workplace.bm.service.ClassmMgmtService;


/**
 * @author j10000
 * @Description :중분류코드관리 ClassmMgmtService implement
 * @Class : ClassmMgmtServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.21  j10000			
 * </pre>
 * @version : 1.0
 */



@Service("classmMgmtService")
public class ClassmMgmtServiceImpl  implements ClassmMgmtService{
	
	@Autowired
	ClassmMgmtMapper classmMgmtMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  중분류코드 조회 
	 * @param ClassmMgmtVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectClassmMgmtList(ClassmMgmtVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<ClassmMgmtVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = classmMgmtMapper.selectClassmMgmtListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  classmMgmtMapper.selectClassmMgmtList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}

	/**
	 *  중분류코드 상세조회
	 *  TFM_SALES_PR_MST.SALES_PR_CD  PK 조건 
	 * @param ClassmMgmtVo paramVo
	 * @return ClassmMgmtVo
	 * @throws Exception
	 */
	public ClassmMgmtVo selectClassmMgmtData(ClassmMgmtVo paramVo) throws Exception {
		return  classmMgmtMapper.selectClassmMgmtData(paramVo);
	}

	
	
	/**
	 * 중분류코드 저장
	 */
	@Override 
	public Result insertClassmMgmt(HttpServletRequest request, ClassmMgmtVo paramVo) throws Exception{
		Result result = new Result();
		result.setMsgCd("fall");
		
		// 작업자정보 ==============================
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String regId = StringUtils.defaultString(customUser.getUserId());			// 작업자 아이디
		paramVo.setRegId(regId);	//작업자정보셋팅
		paramVo.setModId(regId);	//작업자정보셋팅
		// 작업자정보 ==============================
		
		int executeCnt = 0;
		
		executeCnt = classmMgmtMapper.insertClassmMgmt(paramVo);		// 신규 생성 ( BEFOR select CAR_CD ) 

		if(executeCnt == 1) {
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getmCd());	// 수정,등록 CAR_CD 
		}

		return result;
	}

	/**
	 *  중분류코드 삭제 
	 *  TFM_SALES_PR_MST.SALES_PR_CD PK   
	 *  DELETE
	 * @param ClassmMgmtVo paramVo
	 * @return ClassmMgmtVo
	 * @throws Exception
	 */
	public Result deleteClassmMgmtData(ClassmMgmtVo paramVo) throws Exception {
		
		Result result = new Result();
		result.setMsgCd("fall");
		
		int executeCnt = 0;
		 executeCnt = classmMgmtMapper.deleteClassmMgmt(paramVo);
		 
		 if(executeCnt > 0)  result.setMsgCd("success");

		 return result;
	}

}
