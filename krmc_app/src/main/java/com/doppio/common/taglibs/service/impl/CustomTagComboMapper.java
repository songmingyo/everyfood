package com.doppio.common.taglibs.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;


@Repository(value = "customTagComboMapper")
public interface CustomTagComboMapper {

	/**
	 * 
	 * @Method : selectInternalCode
	 * @Description : 코드 LIST
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectInternalCode(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selectProdtClass1
	 * @Description : 상품대분류 (TFM_PRDT_CLASS1_MST) List
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectProdtClass1(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selectProdtClass2
	 * @Description : 상품중분류 (TFM_PRDT_CLASS2_MST) List
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectProdtClass2(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selectWhCd
	 * @Description : 창고코드 (TFM_WH_MST) List
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectWhCd(HashMap<String, Object> hParam);	
	
	/**
	 * 
	 * @Method : selectInternalMenu
	 * @Description : 메뉴 List
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectInternalMenu(HashMap<String, Object> hParam);
			
	/**
	 * 
	 * @Method : getCodeName
	 * @Description : 코드 LIST (jqGrid구성용)
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getCodeName(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selectInternalSubCode
	 * @Description : 서브코드 List (EXTENT01 = COM_CD로 맵핑되는 코드 리스트)
	 * @param hParam
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> selectInternalSubCode(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selectCarCd
	 * @Description : 배송차량 (TFM_DLVR_MST) List
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectCarCd(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selectHqCd
	 * @Description : 본사
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectHqCd(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : selecSalesPrCd
	 * @Description : 영업사원
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selecSalesPrCd(HashMap<String, Object> hParam);
	
	
}
