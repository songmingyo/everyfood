package com.doppio.workplace.sm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.common.model.Result;
import com.doppio.workplace.bm.service.SalesMgmtVo;
import com.doppio.workplace.sm.service.CusOrdRegService;
import com.doppio.workplace.sm.service.CusOrdRegVo;
import com.doppio.workplace.sm.service.CusSampleRegVo;


/**
 * @Class : cusOrdRegController.java
 * @author dada
 * @Description : 매출처발주(PC/MOBILE)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.04. 18.     DADA
 * </pre>
 * @version :
 */
@Controller(value = "cusOrdRegController")
public class CusOrdRegController {
	

	private static final Logger logger = LoggerFactory.getLogger(CusShipRegController.class);
	
	@Autowired
	CusOrdRegService cusOrdRegService;
	
	/**
     *  Init Action
     *  Mobile 매출처 발주등록 
     *  
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/res/sm/custsales/cusOrdReg")
 	public String cusShipRegInit() throws Exception{
 		return "app/sm/resCustOrdReg";
 	}
 	
 	
 	/**
     *  Init Action
     *  Mobile 매출처 발주조회 
     *  
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/res/sm/custsales/cusOrdRegList")
 	public String cusShipRegListInit() throws Exception{
 		return "app/sm/resCustOrdRegList";
 	}
 	
 	/**
     *  Init Action
     *  PC 매출처 발주등록 
     *  
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/sm/custsales/cusOrdReg")
 	public String cusOrdRegListInit() throws Exception{
 		return "app/sm/cusOrdReg";
 	}
 	
 	
 	/**
	 * 매출처 발주 대상 품목 및 발주 내역 조회
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value="/app/res/sm/custsales/cusOrdReg_selOrdProdList.json", method=RequestMethod.POST)
	public @ResponseBody List<CusOrdRegVo> selectCusOrdProdListSearch(@RequestBody CusOrdRegVo param, HttpServletRequest request) throws Exception {
		return cusOrdRegService.selectCusOrdProdList(param);
	}
 	
	/**
	 * 매출처 발주 수정(모바일)
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */													     
	@RequestMapping(value="/app/res/sm/custsales/cusOrdReg_insOrdProdList.json", method=RequestMethod.POST)
	public @ResponseBody Result saveCusOrdProdList(@RequestBody CusOrdRegVo param, HttpServletRequest request) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
//		result = cusOrdRegService.updateCusOrdProdList(param);
		result = cusOrdRegService.updateCusOrdList(param);
		
		return result;
	}
	
	
	/**
	 * 매출처 발주 대상 상품 상세 정보 
	 * @param ClsDayStkRegVo
	 * @return List<CusOrdRegVo>
	 */
	@RequestMapping(value="/app/res/sm/custsales/cusOrdReg_selSalesProd.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CusOrdRegVo findSalesPrdtMstSearch(@RequestBody CusOrdRegVo searchParam) throws Exception {

		return cusOrdRegService.selectCusOrdProdData(searchParam);
	}
	
	
	
	/**
	 * 매출처발주 정보 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return  Map<String,List<CusOrdRegVo> >
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/res/sm/custsales/cusOrdRegList_selSlip.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> selectOrdSlipSearch(@RequestBody CusOrdRegVo searchParam) throws Exception {

		return cusOrdRegService.selectOrdSlipList(searchParam);
	}
	
	/**
	 * 매출처발주 정보 조회
	 * @param ClsDayStkRegVo	paramVo
	 * @return  List<CusOrdRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value = "/app/res/sm/custsales/cusOrdRegList_selProd.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<CusOrdRegVo> selectOrdProdSearch(@RequestBody CusOrdRegVo searchParam) throws Exception {

		return cusOrdRegService.selectOrdProdList(searchParam);
	}
		
	
	/**
	 * 매출처 발주 등록화면 내역 조회(PC)
	 * @param ClsDayStkRegVo
	 * @return List<CusOrdRegVo>
	 */
	@RequestMapping(value="/app/sm/custsales/cusOrdReg_selList.json", method = RequestMethod.POST)
	public @ResponseBody List<CusOrdRegVo> selectOrdList(CusOrdRegVo searchParam, HttpServletRequest request) throws Exception {

		return cusOrdRegService.selectOrdList(searchParam);
	}
	
 	/**
	 * 매출처발주등록 하나의 품목만 조회(PC)
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/sm/custsales/cusOrdReg_selPrdtAdd.json"}, method=RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> selectCusOrdRegPrdtAddSearch(@RequestBody CusOrdRegVo param, HttpServletRequest request) throws Exception {
		return cusOrdRegService.selectCusOrdRegPrdtAddSearch(param);
	}
	
	/**
	 * 매출처 발주 대상 상품 상세 정보 (PC)
	 * @param ClsDayStkRegVo
	 * @return List<CusOrdRegVo>
	 */
	@RequestMapping(value="/app/sm/custsales/cusOrdReg_selPrdtList.json", method = RequestMethod.POST)
	public @ResponseBody List<CusOrdRegVo> selectOrdPrdtList(CusOrdRegVo searchParam, HttpServletRequest request) throws Exception {

		return cusOrdRegService.selectOrdPrdtList(searchParam);
	}
	
	/**
	 * 매출처 발주 수정 (PC)
	 * @param ClsDayStkRegVo param
	 * @param HttpServletRequest
	 * @return List<CusOrdRegVo>
	 * @throws Exception
	 */													     
	@RequestMapping(value="/app/sm/custsales/cusOrdReg_insOrdList.json", method=RequestMethod.POST)
	public @ResponseBody Result saveCusOrdList(@RequestBody CusOrdRegVo param, HttpServletRequest request) throws Exception {
		Result result = new Result();
		result.setMsgCd("-1");
		result.setMessage("Error");
		
		result = cusOrdRegService.updateCusOrdList(param);
		
		return result;
	}
	

}
