package com.doppio.management.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrTermsVo;

/**
 * 
 * @Class : MgrTermsMapper.java
 * @Description : 약관 동의 정보
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   2023. 1. 11.       윤지아        	 약관 관련 쿼리 정리
 *
 * </pre>
 */
@Repository(value = "mgrTermsMapper")
public interface MgrTermsMapper {

	/**
	 * 
	 * @Method : selectVersion
	 * @Description : [약관관리] 약관 리스트 불러오기
	 * @param hParam
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> selectVersion(HashMap<String, Object> hParam);
	
	/**
	 * 
	 * @Method : insertTerms
	 * @Description : 약관 저장
	 * @param paramVo
	 * @return int
	 */
	public int insertTerms(MgrTermsVo paramVo);
	
	/**
	 * 
	 * @Method : updateVersion
	 * @Description :  버전정보 업데이트
	 * @param paramVo
	 * @return String
	 */
	public int updateVersion(MgrTermsVo paramVo);
	
	/**
	 * 
	 * @Method : selectTermsVersion
	 * @Description : 버전정보 가져오기
	 * @param termsVersion
	 * @return String
	 */
	public String selectTermsVersion(String termsVersion);
	
	/**
	 * 
	 * @Method : selectTerms
	 * @Description : 기등록된 약관정보 존재여부 확인 (TCM_TERMS)
	 * @param terms
	 * @return int
	 */
	public int selectTerms(String terms);
	
	/**
	 * 
	 * @Method : selectTermsList
	 * @Description : 협력사용 약관동의 정보 리스트 조회
	 * @param paramVo
	 * @return List<MgrTermsVo>
	 */
	public List<MgrTermsVo> selectTermsList(MgrTermsVo paramVo);

	/**
	 * 
	 * @Method : selectAgreeTermsChk
	 * @Description : 약관동의여부 확인
	 * @param paramVo
	 * @return int
	 */
	public int selectAgreeTermsChk (MgrTermsVo paramVo);
	
	/**
	 * 
	 * @Method : insertTermsAgree
	 * @Description : 약관 동의 저장
	 * @param paramVo
	 * @return int
	 */
	public int insertTermsAgree(MgrTermsVo paramVo);
	
	/**
	 * 
	 * @Method : selectTcmTermsRequired
	 * @Description : [필수약관체크용] 필수 동의가 필요한 정보동의 구분코드 조회
	 * @param paramVo
	 * @return List<String>
	 */
	public List<String> selectTcmTermsRequired(MgrTermsVo paramVo); 
	
}
