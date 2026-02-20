package com.doppio.workplace.sm.service.impl;

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
import com.doppio.workplace.br.service.BuyOrderRegVo;
import com.doppio.workplace.br.service.BuyRcptRegVo;
import com.doppio.workplace.sm.service.CusRtnListVo;
import com.doppio.workplace.sm.service.CusSalesEstRegService;
import com.doppio.workplace.sm.service.CusSalesEstRegVo;

/**
 * @author song
 * @Description 매출 견적서 등록  : CusSalesEstRegService implement
 * @Class : CusSalesEstRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.04.12 			
 * </pre>
 * @version : 1.0
 */



@Service("cusSalesEstRegService")
public class CusSalesEstRegServiceImpl implements CusSalesEstRegService {

	
	@Autowired
	CusSalesEstRegMapper cusSalesEstRegMapper;
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  매출 견적서 품목 조회 List
	 * @param CusSalesEstRegVo paramVo
	 * @return List<CusSalesEstRegVo>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusSalesEstRegPrdtList(CusSalesEstRegVo paramVo) throws Exception{
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<CusSalesEstRegVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  cusSalesEstRegMapper.selectCusSalesEstPrdtList(paramVo);
		
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
	 *  매출 견적서 Head 조회 List
	 * @param CusSalesEstRegVo paramVo
	 * @return List<CusSalesEstRegVo>
	 * @throws Exception
	 */
	public CusSalesEstRegVo selectCusSalesEstRegHeadList(CusSalesEstRegVo paramVo) throws Exception{
		
		return cusSalesEstRegMapper.selectCusSalesEstHeadList(paramVo);
	}
	
	/**
	 *  매출 견적서 Item 조회 List
	 * @param CusSalesEstRegVo paramVo
	 * @return List<CusSalesEstRegVo>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusSalesEstRegItemList(CusSalesEstRegVo paramVo) throws Exception{
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<CusSalesEstRegVo> resultList = null;
		
		resultList = cusSalesEstRegMapper.selectCusSalesEstItemList(paramVo);
		
		returnMap.put("resultList", resultList);							//조회결과 데이터
		
		return returnMap;

	}
	
	
	/**
	 * 매출 견적서 등록
	 * @param CusSalesEstRegVo	paramVo
	 * @return Result
	 * @throws Exception
	 */
	public Result insertCusSalesEstReg(CusSalesEstRegVo paramVo) throws Exception{
		
		Result result = new Result();
		
		result.setMsgCd("-1");
		result.setMessage("");
		
		if(paramVo == null || paramVo.getCusSalesEstRegList() == null || paramVo.getCusSalesEstRegList().size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("견적 데이터가 없습니다.");
			return result;
		}
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		List<CusSalesEstRegVo> list = paramVo.getCusSalesEstRegList();
		
		int updateCnt = 0;
		int insertCnt = 0;
		
		String estNum = list.get(0).getEstNum();
		
		paramVo.setWorkId(customUser.getUserId());
		
		if(StringUtil.isEmpty(estNum)) {
			insertCnt = cusSalesEstRegMapper.insertEstHead(paramVo);
		}else {
			paramVo.setEstNum(estNum);
			
			updateCnt = cusSalesEstRegMapper.updateEstHead(paramVo);
		}
		
		String estNumNew = paramVo.getEstNum();
		
		for(CusSalesEstRegVo cusSalesEstRegVo : list) {
			cusSalesEstRegVo.setWorkId(customUser.getUserId());
			cusSalesEstRegVo.setEstNum(estNumNew);
				
			if("U".equals(cusSalesEstRegVo.getGridFlag())){
				updateCnt = cusSalesEstRegMapper.updateEstItem(cusSalesEstRegVo);
			} else if("I".equals(cusSalesEstRegVo.getGridFlag())){
				insertCnt = cusSalesEstRegMapper.insertEstItem(cusSalesEstRegVo);
			}
		}
		
 		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setRtnValue03( String.valueOf(estNumNew) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
		
	}
	
	/**
	 * 내부견적서 출력 조회
	 * @param CusSalesEstRegVo paramVo
	 * @return List<CusSalesEstRegVo>
	 */
	@Override
	public List<CusSalesEstRegVo> selectCurSalesEstPrintList(CusSalesEstRegVo paramVo) {
		return cusSalesEstRegMapper.selectCurSalesEstPrintList(paramVo);
	}
	

}
