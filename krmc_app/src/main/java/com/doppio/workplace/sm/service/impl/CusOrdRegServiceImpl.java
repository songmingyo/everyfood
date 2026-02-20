package com.doppio.workplace.sm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.util.pagination.Paging;
import com.doppio.common.util.pagination.PagingFactory;
import com.doppio.workplace.common.model.BuyMstVo;
import com.doppio.workplace.common.service.impl.BizCommonMapper;
import com.doppio.workplace.sm.service.CusOrdRegService;
import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusSalesDlvVo;
import com.doppio.workplace.sm.service.CusSampleRegVo;




/**
 * @author dada
 * @Description 매출처 발주 등록  : CusOrdRegService implement
 * @Class : CusOrdRegServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *	2023.04.12 dada 			
 * </pre>
 * @version : 1.0
 */



@Service("cusOrdRegService")
public class CusOrdRegServiceImpl implements CusOrdRegService {

	
	@Autowired
	CusOrdRegMapper cusOrdRegMapper;
	
	@Autowired
	BizCommonMapper bizCommonMapper;
	

	@Value("#{config['count.per.page']}")	
	public String DefaultPagePerCount; //한 화면에 나타나는 페이지 수
	
	@Value("#{config['count.row.page']}")	
	public String DefaultPageRowCount; //한 페이지에 나타나는 row수
	
	
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor message;
	
	
	/**
	 *  매출처발주상품 현황 
	 * @param ClsDayStkRegVo paramVo
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	public List<CusOrdRegVo> selectCusOrdProdList(CusOrdRegVo paramVo) throws Exception{
		return cusOrdRegMapper.selectCusOrdProdList(paramVo);
	}
	
	
	/**
	 * 매출처발주상품 발주정보 등록(TSH_SALES_ORDER) 모바일
	 * @param ClsDayStkRegVo	paramVo
	 * @return Result
	 * @throws Exception
	 * 
	 *  사용자 정의 오류코드 
	 *    -1 : 알수없는 오류 ( Data 처리중 오류가 발생하였습니다.)
	 *     1 : getOrderRegList = null or size 0
	 *     2 : 발주시간 마감 
	 *     3 : 대상 전표번호 등록 마감   
	 */
	public Result updateCusOrdProdList(CusOrdRegVo paramVo) throws Exception{
		
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		String userType = customUser.getUserType();
		
		List<CusOrdRegVo> ordList =  paramVo.getOrderRegList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || ordList == null || ordList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("발주 데이타 없습니다.!");
			return result;
		}
		/*-------------------------------------------------------*/
		
		/*매출처 발주마감 시간 검증 -- --------------------------------*/
		HashMap<String, String> paramClossMap    = new HashMap<String, String>();
		paramClossMap.put("SALES_CD", paramVo.getSalesCd());	// 매출처코드 
		HashMap<String, String> responseCloseMap = bizCommonMapper.selectSalesOrderCloseCheck(paramClossMap);
		
		if("S1".equals(userType) || "S2".equals(userType)) {
			if(!"N".equals(responseCloseMap.get("ORD_DDLN_CLOSE_YN"))) {
				result.setMsgCd("2");
				result.setMessage("발주시간이 마감되었습니다.");
				result.setRtnValue01(responseCloseMap.get("ORD_DDLN_TM")); 
				
				return result;
			}
		}
		/*-------------------------------------------------------*/
		
		/*전표번호  발주마감 시간 검증  --------------------------------*/
		HashMap<String, String> paramSlipMap   		= null;
		HashMap<String, String> responseSlipMap		= null;
	
		String salesSlipNo = "";
		
		if("S1".equals(userType) || "S2".equals(userType)) {
			for(CusOrdRegVo cusOrdRegVo : ordList) {
				
				/*전표번호가 없거나 이전 검증값과 같으면 continue */
				if(StringUtil.isEmpty(cusOrdRegVo.getSalesSlipNo()) || salesSlipNo.equals(cusOrdRegVo.getSalesSlipNo()))  continue;
				
				salesSlipNo = cusOrdRegVo.getSalesSlipNo();
				
				paramSlipMap    = new HashMap<String, String>();
				
				paramSlipMap.put("SALES_SLIP_NO", salesSlipNo);	// 전표번호  
				responseSlipMap = bizCommonMapper.selectOrderSlipCloseCheck(paramSlipMap);
				
				if(responseSlipMap != null && "Y".equals(responseSlipMap.get("CLOSE_FLAG"))) {
					
					result.setMsgCd("3");
					result.setMessage("대상 전표의 발주가 마감되었습니다.");
					result.setRtnValue01(responseSlipMap.get("SALES_SLIP_NO")); 
					result.setRtnValue02(responseSlipMap.get("CLOSE_FLAG")); 
					return result;
				}
			}
		}
		/*-------------------------------------------------------*/
		

		String remarks1 = paramVo.getRemarks1();
		
		int insertCnt = 0;		// 신규 생성 카운트
		int updateCnt = 0;		// 기존 수정 카운트
		
		int remarkCnt = 0;
		
		
		for(CusOrdRegVo cusOrdRegVo : ordList) {
			
			cusOrdRegVo.setSalesCd(paramVo.getSalesCd());	// 매출처코드
			cusOrdRegVo.setOrdDt(paramVo.getOrdDt());		// 발주일자	
			cusOrdRegVo.setDlvDt(paramVo.getDlvDt());		// 납품일자	
			cusOrdRegVo.setWorkId(customUser.getUserId());	// 작업자 아이디 

			if(StringUtil.isEmpty(cusOrdRegVo.getSalesSlipNo()) ) {
				
				cusOrdRegMapper.insertSalesSlipNo(cusOrdRegVo);	// 전표번호 생성 or 조회 
				
				/*매출전표번호 없으면 신규 등록 (Before SALES_SLIP_NO 생성)  */
				if(cusOrdRegMapper.insertCusOrdProdList(cusOrdRegVo) != 1) {
					throw new Exception("insertCusOrdProdList [TSH_SALES_ORDER] insert result zero ! ");
				}
				
				/*TSH_SALES_DLV, TSH_SALES_DLV_ETC(매출처 발주 관리,매출처 판매 기타 정보)  INSERT */
				cusOrdRegMapper.insertSalesDlv(cusOrdRegVo);
				

				/* TSH_SALES_DLV_ETC(매출처 판매 기타 정보) INSERT IGNORE  */
				cusOrdRegMapper.insertSalesDlvEtc(cusOrdRegVo);
				
				
				insertCnt++;
			} else {
				
				/*이전등록 수량 null 일경우  "0" 으로 초기화 */
				if(StringUtil.isEmpty(cusOrdRegVo.getOrdQtyBef()) ) cusOrdRegVo.setOrdQtyBef("0");
				
				/*매출전표번호 있고 수량이 있으면  수정  등록  */
				cusOrdRegVo.setUseYn("Y"); // Default 'Y'
				if(StringUtil.isEmpty(cusOrdRegVo.getOrdQty()) || "0".equals(cusOrdRegVo.getOrdQty())) {
					cusOrdRegVo.setOrdQty("0");
					cusOrdRegVo.setUseYn("N"); // 수량이 없거나 0인경우 사용여부 "N"
				}
				
				/*수량 변경이 있는경우에만 UPDATE 진행   (수량변동이 없는 데이터는 제외처리 )*/
				if(cusOrdRegVo.getOrdQty().equals(cusOrdRegVo.getOrdQtyBef())) continue;
				
				
				if(cusOrdRegMapper.updateCusOrdProdList(cusOrdRegVo) != 1) {
					throw new Exception("updateCusOrdProdList [TSH_SALES_ORDER] update result zero 0 ! ");
				}
				
				/*TSH_SALES_DLV(매출처 발주 관리), TSH_SALES_DLV_ETC(매출처 판매 기타 정보)(UPDATE) */
				cusOrdRegMapper.updateSalesDlv(cusOrdRegVo);
				
				updateCnt++;
			}
			
			cusOrdRegVo.setRemarks1(remarks1);
			
			remarkCnt = cusOrdRegMapper.selectCusOrdRemarksListCount(cusOrdRegVo);
			
			if(remarkCnt > 0) {
				cusOrdRegMapper.updateCusOrdRemarks(cusOrdRegVo);
			} else {
				cusOrdRegMapper.insertCusOrdRemarks(cusOrdRegVo);
			}
			
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
		
	}
	
	
	
	/**
	 * 매출처발주 대상 상품 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return CusOrdRegVo
	 * @throws Exception
	 */
	public CusOrdRegVo selectCusOrdProdData(CusOrdRegVo paramVo) throws Exception{
		return cusOrdRegMapper.selectCusOrdProdData(paramVo);
	}
	
	
	
	
	/**
	 * @Method : selectOrdSlipList
	 * @Description : 매출처 발주 정보조회 
	 * @param ClsDayStkRegVo  paramVo
	 * @return Map<String,List<CusOrdRegVo> >
	 * @throws Exception
	 */
	@Override
	public HashMap<String,Object> selectOrdSlipList(CusOrdRegVo paramVo) throws Exception {

		HashMap<String, Object> returnMap  = new HashMap<String,Object>();
		List<CusOrdRegVo> 		resultList = null;
		
		int page = Integer.parseInt(StringUtils.defaultString(paramVo.getPage(), "1"));
		int pageRowCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPageRowCount(),DefaultPageRowCount));
		int pagePerCount = Integer.parseInt(StringUtils.defaultString(paramVo.getPagePerCount(),DefaultPagePerCount));
		
		int totalCount = 0;
		totalCount = cusOrdRegMapper.selectCusOrdSlipListCount(paramVo);
		
		Paging paging = PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page);
		int startRowNum = paging.getStartRowNum();
		int endRowNum = paging.getEndRowNum();
		
		paramVo.setStartRowNo(startRowNum);
		paramVo.setEndRowNo(endRowNum);
		
		if(totalCount>0) {
			resultList =  cusOrdRegMapper.selectCusOrdSlipList(paramVo);
		}
		
		returnMap.put("totalCount", totalCount);							//조회된 데이터 총갯수
		returnMap.put("resultList", resultList);							//조회결과 데이터
		returnMap.put("paging", PagingFactory.makePagingObject(totalCount, pageRowCount, pagePerCount, page));
		
		return returnMap;
	}
	
	
	
	/**
	 *  매출처발주등록 상세  현황 
	 * @param ClsDayStkRegVo paramVo
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	public List<CusOrdRegVo> selectOrdProdList(CusOrdRegVo paramVo) throws Exception{
		return cusOrdRegMapper.selectOrdProdList(paramVo);
	}
	
	
	/**
	 * @Method : selectOrdSlipList
	 * @Description : 매출처 발주 등록 내역 조회 
	 * @param ClsDayStkRegVo  paramVo
	 * @return Map<String,List<CusOrdRegVo> >
	 * @throws Exception
	 */
	@Override
	public List<CusOrdRegVo> selectOrdList(CusOrdRegVo paramVo) throws Exception {

		return cusOrdRegMapper.selectOrdList(paramVo);
	}
	
	/**
	 *  매출처발주등록 하나의 품목만 조회 
	 * @param ClsDayStkRegVo paramVo
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */
	public HashMap<String, Object> selectCusOrdRegPrdtAddSearch(CusOrdRegVo paramVo) throws Exception {
		
		HashMap<String, Object> resultList = new HashMap<String,Object>();
		
		CusOrdRegVo cusOrdRegVo = cusOrdRegMapper.selectCusOrdRegPrdtAdd(paramVo);
		
		resultList.put("cusOrdRegVo", cusOrdRegVo);
		
		return resultList;
		
	}
	
	
	/**
	 * @Method : selectOrdPrdtList
	 * @Description : 매출처 발주 대상 품목 조회
	 * @param ClsDayStkRegVo  paramVo
	 * @return Map<String,List<CusOrdRegVo> >
	 * @throws Exception
	 */
	@Override
	public List<CusOrdRegVo> selectOrdPrdtList(CusOrdRegVo paramVo) throws Exception {

		return cusOrdRegMapper.selectOrdPrdtList(paramVo);
	}
	
	/**
	 * 매출처발주상품 발주정보 등록 (PC)
	 * @param ClsDayStkRegVo	paramVo
	 * @return Result
	 * @throws Exception
	 * 
	 *  사용자 정의 오류코드 
	 *    -1 : 알수없는 오류 ( Data 처리중 오류가 발생하였습니다.)
	 *     1 : getOrderRegList = null or size 0
	 *     2 : 발주시간 마감 
	 *     3 : 대상 전표번호 등록 마감   
	 */
	public Result updateCusOrdList(CusOrdRegVo paramVo) throws Exception{
		
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage(this.message.getMessage("error.msg.data.access.failuer.title"));
		
		List<CusOrdRegVo> ordList =  paramVo.getOrderRegList();
		/*발주등록 List Param Data 검증 ----------------------------*/
		if(paramVo == null || ordList == null || ordList.size() <= 0) {
			result.setMsgCd("1");
			result.setMessage("발주 데이타 없습니다.!");
			return result;
		}
		/*-------------------------------------------------------*/
		
		/*작업자 아이디  생성----------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		/*-------------------------------------------------------*/
		
		String userType = customUser.getUserType();
		
		/*매출처 발주마감 시간 검증 -- --------------------------------*/
		HashMap<String, String> paramClossMap    = new HashMap<String, String>();
		paramClossMap.put("SALES_CD", paramVo.getSalesCd());	// 매출처코드 
		HashMap<String, String> responseCloseMap = bizCommonMapper.selectSalesOrderCloseCheck(paramClossMap);
		
		if("S1".equals(userType) || "S2".equals(userType)) {
			if(!"N".equals(responseCloseMap.get("ORD_DDLN_CLOSE_YN"))) {
				result.setMsgCd("2");
				result.setMessage("발주시간이 마감되었습니다.");
				result.setRtnValue01(responseCloseMap.get("ORD_DDLN_TM")); 
				
				return result;
			}
		}
		
		/*-------------------------------------------------------*/
		
		/*전표번호  발주마감 시간 검증  --------------------------------*/
		HashMap<String, String> paramSlipMap   		= null;
		HashMap<String, String> responseSlipMap		= null;
	
		String salesSlipNo = "";
		
		if("S1".equals(userType) || "S2".equals(userType)) {
			for(CusOrdRegVo cusOrdRegVo : ordList) {
				
				/*전표번호가 없거나 이전 검증값과 같으면 continue */
				if(StringUtil.isEmpty(cusOrdRegVo.getSalesSlipNo()) || salesSlipNo.equals(cusOrdRegVo.getSalesSlipNo()))  continue;
				
				salesSlipNo = cusOrdRegVo.getSalesSlipNo();
				
				paramSlipMap    = new HashMap<String, String>();
				
				paramSlipMap.put("SALES_SLIP_NO", salesSlipNo);	// 전표번호  
				responseSlipMap = bizCommonMapper.selectOrderSlipCloseCheck(paramSlipMap);
				
				if(responseSlipMap != null && "Y".equals(responseSlipMap.get("CLOSE_FLAG"))) {				
					result.setMsgCd("3");
					result.setMessage("대상 전표의 발주가 마감되었습니다.");
					result.setRtnValue01(responseSlipMap.get("SALES_SLIP_NO")); 
					result.setRtnValue02(responseSlipMap.get("CLOSE_FLAG")); 
					return result;
				}
			}
		}
		/*-------------------------------------------------------*/
		
		String remarks1 = paramVo.getRemarks1();
		
		int insertCnt = 0;		// 신규 생성 카운트
		int updateCnt = 0;		// 기존 수정 카운트
		
		int remarkCnt = 0;
		
		salesSlipNo = "";
		
		for(CusOrdRegVo cusOrdRegVo : ordList) {
			
			cusOrdRegVo.setSalesCd(paramVo.getSalesCd());	// 매출처코드
			cusOrdRegVo.setOrdDt(paramVo.getOrdDt());		// 발주일자	
			cusOrdRegVo.setDlvDt(paramVo.getDlvDt());		// 납품일자	
			cusOrdRegVo.setWhCd(paramVo.getWhCd());			// 창고코드
			cusOrdRegVo.setWorkId(customUser.getUserId());	// 작업자 아이디 

			if(StringUtil.isEmpty(cusOrdRegVo.getSalesSlipNo()) ) {
				
				cusOrdRegVo.setSalesSlipNo(salesSlipNo);
				
				cusOrdRegMapper.insertSalesSlipNo(cusOrdRegVo);	// 전표번호 생성 or 조회
				
				salesSlipNo = cusOrdRegVo.getSalesSlipNo();
				
				/*매출전표번호 없으면 신규 등록 (Before SALES_SLIP_NO 생성)  */
				if(cusOrdRegMapper.insertCusOrdList(cusOrdRegVo) != 1) {
					throw new Exception("insertCusOrdProdList [TSH_SALES_ORDER] insert result zero ! ");
				}
				
				/*TSH_SALES_DLV, TSH_SALES_DLV_ETC(매출처 발주 관리,매출처 판매 기타 정보)  INSERT */
				cusOrdRegMapper.insertSalesDlv(cusOrdRegVo);
				

				/* TSH_SALES_DLV_ETC(매출처 판매 기타 정보) INSERT IGNORE  */
				cusOrdRegMapper.insertSalesDlvEtc(cusOrdRegVo);
				
				
				insertCnt++;
			} else {
				
				/*이전등록 수량 null 일경우  "0" 으로 초기화 */
				if(StringUtil.isEmpty(cusOrdRegVo.getOrdQtyBef()) ) cusOrdRegVo.setOrdQtyBef("0");
				
				/*매출전표번호 있고 수량이 있으면  수정  등록  */
				cusOrdRegVo.setUseYn("Y"); // Default 'Y'
				if(StringUtil.isEmpty(cusOrdRegVo.getOrdQty()) || "0".equals(cusOrdRegVo.getOrdQty())) {
					cusOrdRegVo.setOrdQty("0");
					cusOrdRegVo.setUseYn("N"); // 수량이 없거나 0인경우 사용여부 "N"
				}
				
				/*수량 변경이 있는경우에만 UPDATE 진행   (수량변동이 없는 데이터는 제외처리 )*/
				//if(cusOrdRegVo.getOrdQty().equals(cusOrdRegVo.getOrdQtyBef())) continue;
				
				
				if(cusOrdRegMapper.updateCusOrdList(cusOrdRegVo) != 1) {
					throw new Exception("updateCusOrdProdList [TSH_SALES_ORDER] update result zero 0 ! ");
				}
				
				/*TSH_SALES_DLV(매출처 발주 관리), TSH_SALES_DLV_ETC(매출처 판매 기타 정보)(UPDATE) */
				cusOrdRegMapper.updateSalesDlv(cusOrdRegVo);
				
				updateCnt++;
			}
			
			cusOrdRegVo.setRemarks1(remarks1);
			
			remarkCnt = cusOrdRegMapper.selectCusOrdRemarksListCount(cusOrdRegVo);
			
			if(remarkCnt > 0) {
				cusOrdRegMapper.updateCusOrdRemarks(cusOrdRegVo);
			} else {
				cusOrdRegMapper.insertCusOrdRemarks(cusOrdRegVo);
			}
			
		}

		result.setRtnValue01( String.valueOf(insertCnt) );
		result.setRtnValue02( String.valueOf(updateCnt) );
		result.setMsgCd("0");
		result.setMessage("SUCCESS");

		return result;
		
	}

}
