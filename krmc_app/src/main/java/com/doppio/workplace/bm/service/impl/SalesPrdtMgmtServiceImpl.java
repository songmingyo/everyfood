package com.doppio.workplace.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
import com.doppio.workplace.bm.service.SalesPrdtMgmtService;
import com.doppio.workplace.bm.service.SalesPrdtMgmtVo;
import com.doppio.workplace.sm.service.CusPriceUnconfVo;

/**
 * @author Song
 * @Description 매출처상품관리 : SalesPrdtMgmtService implement
 * @Class : SalesPrdtMgmtServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *				
 * </pre>
 * @version : 1.0
 */



@Service("salesPrdtMgmtService")
public class SalesPrdtMgmtServiceImpl  implements SalesPrdtMgmtService{
	
	@Autowired
	SalesPrdtMgmtMapper salesPrdtMgmtMapper;
	
	
	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	/**
	 *  상품 조회 
	 * @param SalesPrdtMgmtVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalesPrdtMgmtList(SalesPrdtMgmtVo paramVo) throws Exception {
		
	
		
		HashMap<String, Object> returnMap = new HashMap<String,Object>();
		List<SalesPrdtMgmtVo> resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		resultList =  salesPrdtMgmtMapper.selectSalesPrdtMgmtList(paramVo);
		
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
	 *  매출처 상품 정보 저장 
	 * @param SalesPrdtMgmtVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertSalesPrdtMaster(SalesPrdtMgmtVo paramVo) throws Exception {

		Result result = new Result();
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		ArrayList<SalesPrdtMgmtVo> list = paramVo.getSalesPrdtMasterData();
		
		int ListLen = list.size();
		
		int executeCnt = 0;
		
		if(ListLen > 0) {
			for(int i=0; i<ListLen; i++) {
				SalesPrdtMgmtVo dataList = (SalesPrdtMgmtVo)list.get(i);
				
				dataList.setWorkId(customUser.getUserId());
				dataList.setUseYn("Y");
				
				executeCnt = salesPrdtMgmtMapper.updateSalesPrdtMasterData(dataList);
			}
		}
		
		if(executeCnt >= 1) {
			result.setMsgCd("success");
		} else if(executeCnt == 0) {
			result.setMsgCd("noData");
		}

		return result;
	}
	
	/**
	 *  선택 품목 삭제 처리
	 * @param SalesPrdtMgmtVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result updateSalesPrdtMasterDataUseFlag(SalesPrdtMgmtVo paramVo) throws Exception {

		Result result = new Result();
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		ArrayList<SalesPrdtMgmtVo> list = paramVo.getPrdtJobList();
		
		int ListLen = list.size();
		
		int executeCnt = 0;
		
		if(ListLen > 0) {
			for(int i=0; i<ListLen; i++) {
				SalesPrdtMgmtVo dataList = (SalesPrdtMgmtVo)list.get(i);
				
				executeCnt = salesPrdtMgmtMapper.updateSalesPrdtMasterDataUseFlag(dataList);
			}
		}
		
		if(executeCnt >= 1) {
			result.setMsgCd("success"); 
		} else if(executeCnt == 0) {
			result.setMsgCd("noData");
		}

		return result;
	}
	
	/**
	 *  품목 코드 일괄 수정
	 * @param SalesPrdtMgmtVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result updateSalesPrdtMaterPrdtOldNewChg(SalesPrdtMgmtVo paramVo) throws Exception {

		Result result = new Result();
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		int executeCnt = 0;
		
		executeCnt = salesPrdtMgmtMapper.updateSalesPrdtMaterPrdtOldNewChg(paramVo);
		
		if(executeCnt >= 1) {
			result.setMsgCd("success");
		} else if(executeCnt == 0) {
			result.setMsgCd("noData");
		}

		return result;
	}
	
	/**
	 *  매출처품목복사
	 * @param SalesPrdtMgmtVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result updateSalesPrdtMaterSalesCdOrgNewInsert(SalesPrdtMgmtVo paramVo) throws Exception {

		Result result = new Result();
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		int executeCnt = 0;
		
		executeCnt = salesPrdtMgmtMapper.updateSalesPrdtMaterSalesCdOrgNewInsert(paramVo);
		
		if(executeCnt >= 1) {
			result.setMsgCd("success");
		} else if(executeCnt == 0) {
			result.setMsgCd("noData");
		}

		return result;
	}
	
	/**
	 *  품목일괄적용 대상 조회
	 * @param SalesPrdtMgmtVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectSalesPrdtMgmtAddList(SalesPrdtMgmtVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		SalesPrdtMgmtVo salesPrdtMgmtVo = salesPrdtMgmtMapper.selectSalesPrdtMgmtAddList(paramVo);
		
		resultList.put("salesPrdtMgmtVo", salesPrdtMgmtVo);
		
		return resultList;
	}
	
	/**
	 *  품목 코드 일괄 적용
	 * @param SalesPrdtMgmtVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result insertSalesPrdtMaterSalesPrdtAllInsert(SalesPrdtMgmtVo paramVo) throws Exception {

		
		Result result = new Result();
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		ArrayList<SalesPrdtMgmtVo> list = paramVo.getSalesPrdtMasterData();
		
		int ListLen = list.size();
		
		int executeCnt = 0;
		
		if(ListLen > 0) {
			for(int i=0; i<ListLen; i++) {
				SalesPrdtMgmtVo dataList = (SalesPrdtMgmtVo)list.get(i);
				
				dataList.setWorkId(customUser.getUserId());
				
				executeCnt = salesPrdtMgmtMapper.updateSalesPrdtMaterSalesPrdtAllInsert(dataList);
			}
		}
		
		if(executeCnt >= 1) {
			result.setMsgCd("success");
		} else if(executeCnt == 0) {
			result.setMsgCd("noData");
		}

		return result;

	}
	
	/**
	 *  일괄판매가 수정
	 * @param SalesPrdtMgmtVo paramVo
	 * @return SalesPrdtMgmtVo
	 * @throws Exception
	 */
	public Result updateSalesPrdtMaterSalesPriceUpt(SalesPrdtMgmtVo paramVo) throws Exception {

		Result result = new Result();
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getUserId());
		/*-------------------------------------------------------*/		
		
		int executeCnt = 0;
		
		executeCnt = salesPrdtMgmtMapper.updateSalesPrdtMaterSalesPriceUpt(paramVo);
		
		if(executeCnt >= 1) {
			result.setMsgCd("success");
		} else if(executeCnt == 0) {
			result.setMsgCd("noData");
		}

		return result;
	}
	
	/**
	 * 매출품목 엑셀 다운로드 
	 * @param CusPriceUnconfVo paramVo
	 * @return CusPriceUnconfVo
	 * @throws Exception
	 */
	@Override
	public List<HashMap<String, String>> selectSalesPrdtListExcelDown(SalesPrdtMgmtVo paramVo) throws Exception {
		return salesPrdtMgmtMapper.selectSalesPrdtListExcelDown(paramVo);
	}


}
