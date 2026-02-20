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
import com.doppio.workplace.cs.service.ClsDayStkRegService;
import com.doppio.workplace.cs.service.ClsDayStkRegVo;

/**
 * @author Song
 * @Description 일재고 관리 : ClsDayStkRegServiceImpl implement
 * @Class : ClsDayStkRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("clsDayStkRegService")
public class ClsDayStkRegServiceImpl  implements ClsDayStkRegService{
	
	@Autowired
	ClsDayStkRegMapper clsDayStkRegMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  일재고 조회 
	 * @param SalSalesGoalListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectClsDayStkRegList(ClsDayStkRegVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<ClsDayStkRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		resultList = clsDayStkRegMapper.selectClsDayStkRegList(paramVo);
		
		totalCount = resultList.size();
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
		
	}
	
	
	/**
	 *  일재고 footer 조회
	 * @param SalSalesGoalListVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public ClsDayStkRegVo selectDayStkRegFooter(ClsDayStkRegVo paramVo) throws Exception {
		
		return clsDayStkRegMapper.selectDayStkRegFooter(paramVo);
		
	}

	
	/**
	 *  일재고 정보 저장
	 * @param SalSalesGoalListVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertClsDayStkReg(ClsDayStkRegVo paramVo) throws Exception {

		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getClsDayStkRegList() == null || paramVo.getClsDayStkRegList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("재고데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/		
		
		List<ClsDayStkRegVo> list = paramVo.getClsDayStkRegList();		
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		for(ClsDayStkRegVo clsDayStkRegVo : list) {
			
			clsDayStkRegVo.setWorkId(customUser.getUserId());
			clsDayStkRegVo.setUseYn("Y");
				
			if("U".equals(clsDayStkRegVo.getGridFlag())){
				updateCnt = updateCnt + clsDayStkRegMapper.updateClsDayStkRegData(clsDayStkRegVo);
			} else if("I".equals(clsDayStkRegVo.getGridFlag())){
				insertCnt = insertCnt + clsDayStkRegMapper.insertClsDayStkRegData(clsDayStkRegVo);
			}			
		}
		
		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
	}
	
	
	/**
	 *  일재고 엑셀 다운로드 
	 * @param PrdtMgmtVo paramVo
	 * @return PrdtMgmtVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectClsDayStkRegListExcel(ClsDayStkRegVo paramVo) throws Exception {
		return clsDayStkRegMapper.selectClsDayStkRegListExcel(paramVo);
	}

	
	/**
	 * 일재고 출력 조회
	 * @param SalSalesGoalListVo paramVo
	 * @return List<ClsDayStkRegVo>
	 */
	@Override
	public List<ClsDayStkRegVo> selectStkInspPrintList(ClsDayStkRegVo paramVo) {
		return clsDayStkRegMapper.selectStkInspPrintList(paramVo);
	}
}

