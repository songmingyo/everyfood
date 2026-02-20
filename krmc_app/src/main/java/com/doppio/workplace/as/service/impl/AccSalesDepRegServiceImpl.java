package com.doppio.workplace.as.service.impl;

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
import com.doppio.workplace.as.service.AccSalesDepRegVo;
import com.doppio.workplace.bm.service.EmpMasterVo;
import com.doppio.workplace.cs.service.LogWhMvRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;
import com.doppio.workplace.as.service.AccSalesDepRegService;


/**
 * @author j10000
 * @Description 매출처별입금현황  : AccSalesDepRegService implement
 * @Class : AccSalesDepRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.03.31 j10000 			
 * </pre>
 * @version : 1.0
 */



@Service("accSalesDepRegService")
public class AccSalesDepRegServiceImpl  implements AccSalesDepRegService{
	
	@Autowired
	AccSalesDepRegMapper AccSalesDepRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출처별입금현황  확인 
	 * @param AccSalesDepRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectAccSalesDepReg(AccSalesDepRegVo paramVo) throws Exception {
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<AccSalesDepRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  AccSalesDepRegMapper.selectAccSalesDepReg(paramVo);
		
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
	 * @Method : selectCusShipRegDetailList
	 * @Description : 매출처출고등록 상품 상세  조회 
	 * @param CusSalesDlvVo param
	 * @return List<CusSalesDlvVo>
	 */
	@Override
	public List<AccSalesDepRegVo> selectAccSalesDepRegDetail(AccSalesDepRegVo paramVo){
		return  AccSalesDepRegMapper.selectAccSalesDepRegDetail(paramVo);
	}
	
	
	
	
	/**
	 * 매출처별입금현황 저장
	 */
	@Override 
	public Result insertAccSalesDepRegInfo(AccSalesDepRegVo paramVo) throws Exception{
		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/		
		
		List<AccSalesDepRegVo> list = paramVo.getAccSalesDepRegList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		int rowCnt = 0;
		
		for(AccSalesDepRegVo accSalesDepRegVo : list) {
			
			accSalesDepRegVo.setWorkId(customUser.getUserId());

			accSalesDepRegVo.setUseYn("Y");
			
			if("U".equals(accSalesDepRegVo.getGridFlag())){
				AccSalesDepRegMapper.updateAccSalesDepReg(accSalesDepRegVo);					
				
				updateCnt++;
			} else if("I".equals(accSalesDepRegVo.getGridFlag())){
				
				rowCnt = AccSalesDepRegMapper.selectAccSalesDepRegCount(accSalesDepRegVo);
				
				if(rowCnt >= 1) {
					accSalesDepRegVo.setRowCount(String.valueOf(++rowCnt));
										
					result.setMsgCd("1");
					result.setMessage("이미 입력된 데이타가 있습니다.");
					return result;
				}
				
				AccSalesDepRegMapper.insertAccSalesDepReg(accSalesDepRegVo);
					
				insertCnt++;
			}			
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}


}
