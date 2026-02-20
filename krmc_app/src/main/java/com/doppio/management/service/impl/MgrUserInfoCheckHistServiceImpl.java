package com.doppio.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.management.service.MgrPerInfoVo;
import com.doppio.management.service.MgrUserInfoCheckHistService;
import com.doppio.management.service.SysAccessVo;
import com.doppio.workplace.sample.service.SampleExcelVo;

/**
 * 
 * @Class : MgrUserInfoCheckHistServiceImpl.java
 * @Description : 개인정보 조회  및 사용자별 파일조회
 * @author : JS
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 1. 18.        최진성            최초 생성
 *
 * </pre>
 */
@Service("mgrUserInfoCheckHistService")
public class MgrUserInfoCheckHistServiceImpl implements MgrUserInfoCheckHistService {
	
	@Value("#{config['count.per.page']}")
	public String DefaultPagePerCount;

	@Autowired
	MgrUserInfoCheckHistMapper mgrUserInfoCheckHistMapper;

	/**
	 * 개인정보 조회이력 리스트
	 */
	@Override
	public Map<String, Object> selectMgrUserInfoCheckList(MgrPerInfoVo mgrPerInfoVo) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MgrPerInfoVo> list 	  = null;
		Paging pagination			  = null;
		int totalCount				  = 0;
		int pageCount				  = 0;

		/* TOTAL COUNT */
		totalCount = mgrUserInfoCheckHistMapper.selectMgrUserInfoCheckListCount(mgrPerInfoVo);
		
		/* PAGE LIST 카운트 조회 시 list 데이터 조회 */
		if (totalCount > 0) {
			pagination = PagingFactory.makePagingObject(totalCount, 
														mgrPerInfoVo.getRowsParse(),
														mgrPerInfoVo.getPagePerCountCust(DefaultPagePerCount),
														mgrPerInfoVo.getPageCust());
			pageCount = pagination.getPageCount();
			
			mgrPerInfoVo.setStartRowNo(pagination.getStartRowNum());
			mgrPerInfoVo.setEndRowNo(pagination.getEndRowNum());

			list = mgrUserInfoCheckHistMapper.selectMgrUserInfoCheckList(mgrPerInfoVo);
		}
		//RESULT GRID
		resultMap.put("list", list);
		resultMap.put("total", pageCount);
		resultMap.put("records", totalCount);

		return resultMap;
	}

	
	/**
	 *  개인정보 조회이력 엑셀다운로드 데이터 조회
	 */
	@Override
	public List<HashMap<String, String>> selectUserCheckListExcelDown(MgrPerInfoVo mgrPerInfoVo) throws Exception{
		return mgrUserInfoCheckHistMapper.selectUserCheckListExcelDown(mgrPerInfoVo);
	}	
	
	/**
	 * 사용자별 파일이력조회 리스트
	 */
	@Override
	public Map<String,Object> selectMgrUserFileHistory(MgrPerInfoVo mgrPerInfoVo) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MgrPerInfoVo> list 	  = null;
		Paging pagination			  = null;
		int totalCount				  = 0;
		int pageCount				  = 0;

		/* TOTAL COUNT */
		totalCount = mgrUserInfoCheckHistMapper.selectMgrUserFileHistoryCount(mgrPerInfoVo);
		
		/* PAGE LIST 카운트 조회 시 list 데이터 조회 */
		if (totalCount > 0) {
			pagination = PagingFactory.makePagingObject(totalCount, 
														mgrPerInfoVo.getRowsParse(),
														mgrPerInfoVo.getPagePerCountCust(DefaultPagePerCount),
														mgrPerInfoVo.getPageCust());
			pageCount = pagination.getPageCount();
			
			mgrPerInfoVo.setStartRowNo(pagination.getStartRowNum());
			mgrPerInfoVo.setEndRowNo(pagination.getEndRowNum());

			list = mgrUserInfoCheckHistMapper.selectMgrUserFileHistory(mgrPerInfoVo);
		}
		//RESULT GRID
		resultMap.put("list", list);
		resultMap.put("total", pageCount);
		resultMap.put("records", totalCount);

		return resultMap;
	}
	
	/**
	 *  사용자별 파일조회 엑셀 다운로드 데이터 조회
	 */
	@Override
	public List<HashMap<String, String>> selectMgrUserFileHistoryExcelDown(MgrPerInfoVo mgrPerInfoVo) throws Exception{
		return mgrUserInfoCheckHistMapper.selectMgrUserFileHistoryExcelDown(mgrPerInfoVo);
	}	
}
	 

	 
		


