package com.doppio.workplace.bm.service.impl;

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
import com.doppio.management.service.MgrCodeVo;
import com.doppio.workplace.bm.service.DeliVehicleService;
import com.doppio.workplace.bm.service.DlvrMasterVo;

/**
 * @author DADA
 * @Description :차량관리 DeliVehicleService implement
 * @Class : DeliVehicleServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("deliVehicleService")
public class DeliVehicleServiceImpl  implements DeliVehicleService{
	
	@Autowired
	DeliVehicleMapper deliVehicleMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  차량정보 조회 
	 * @param DlvrMasterVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectDelivehicleList(DlvrMasterVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<DlvrMasterVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = deliVehicleMapper.selectDelivehicleListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  deliVehicleMapper.selectDelivehicleList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}
	
	/**
	 *  차량정보 상세조회
	 *  TFM_DLVR_MST.CAR_CD PK 조건 
	 * @param DlvrMasterVo paramVo
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	public DlvrMasterVo selectDelivehicleData(DlvrMasterVo paramVo) throws Exception {
		return  deliVehicleMapper.selectDelivehicleData(paramVo);
	}

	/**
	 *  차량정보 저장 
	 *  TFM_DLVR_MST.CAR_CD PK 조건 
	 *  INSERT/UPDATE (INSERT BEFOR CAR_CD)
	 * @param DlvrMasterVo paramVo
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	public Result insertDelivehicleData(DlvrMasterVo paramVo) throws Exception {
		
		Result result = new Result();
		result.setMsgCd("fall");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/
		
		
		int executeCnt = 0;
		if(StringUtil.isNotEmpty(paramVo.getCarCd()) ) 
			 executeCnt = deliVehicleMapper.updateDelivehicle(paramVo);		// 수정 
		else executeCnt = deliVehicleMapper.insertDelivehicle(paramVo);		// 신규 생성 ( BEFOR select CAR_CD ) 

		if(executeCnt == 1) {
			result.setMsgCd("success");
			result.setRtnValue01(paramVo.getCarCd());	// 수정,등록 CAR_CD 
		}

		return result;
	}
	
	/**
	 *  차량정보 삭제 
	 *  TFM_DLVR_MST.CAR_CD PK   
	 *  DELETE
	 * @param DlvrMasterVo paramVo
	 * @return DlvrMasterVo
	 * @throws Exception
	 */
	public Result deleteDelivehicleData(DlvrMasterVo paramVo) throws Exception {
		
		Result result = new Result();
		result.setMsgCd("fall");
		
		int executeCnt = 0;
		 executeCnt = deliVehicleMapper.deleteDelivehicle(paramVo);
		 
		 if(executeCnt > 0)  result.setMsgCd("success");

		 return result;
	}
}
