package com.doppio.workplace.sm.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doppio.workplace.sm.service.CusSalesStkService;
import com.doppio.workplace.sm.service.CusSalesStkVo;

/**
 * @Class : CusSalesStkController.java
 * @author dada
 * @Description : 매출처재고 (PC/MOBILE)
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
*    2023.05. 14.     DADA
 * </pre>
 * @version :
 */
@Controller(value = "cusSalesStkController")
public class CusSalesStkController {
	
	private static final Logger logger = LoggerFactory.getLogger(CusShipRegController.class);
	
	@Autowired
	CusSalesStkService cusSalesStkService;
	/**
     *  Init Action
     *  Mobile 매출처 발주등록 
     *  
 	* @param
 	* @return
    * @throws Exception
 	*/
 	@RequestMapping(value="/app/res/sm/custsales/cusStkList")
 	public String cusShipRegInit() throws Exception{
 		return "app/sm/resCustStkList";
 	}

 	
 	/**
	 * 매출처재고  정보 조회 (모바일)
	 * @param CusSalesStkVo	paramVo
	 * @return  Map<String,List<CusSalesStkVo> >
	 * @throws Exception
	 */						 
	@RequestMapping(value = "/app/res/sm/custsales/cusStkList_selStkList.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody HashMap<String, Object> selectSearch(@RequestBody CusSalesStkVo searchParam) throws Exception {

		return cusSalesStkService.selectSalesStkList(searchParam);
	}
	
	
	/**
	 * 상품정보  조회
	 * @param CusSalesStkVo	paramVo
	 * @return CusSalesStkVo
	 * @throws Exception
	 */						 
	@RequestMapping(value = "/app/res/sm/custsales/cusStkList_selProd.json", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody CusSalesStkVo selectPrdtSearch(@RequestBody CusSalesStkVo searchParam) throws Exception {

		return cusSalesStkService.selectPrdtSearch(searchParam);
	}
	
	
	
	
}
