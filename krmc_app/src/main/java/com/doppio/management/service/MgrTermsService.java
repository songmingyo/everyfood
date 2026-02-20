package com.doppio.management.service;

import java.util.HashMap;
import java.util.List;

import com.doppio.common.model.Result;

/**
 * 
 * @Class : MgrTermsService.java
 * @Description : 약관 동의 정보
 * @author : 
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *          수정일          수정자           수정내용
 *  ----------------    ------------    ---------------------------
 *   
 *
 * </pre>
 */
public interface MgrTermsService {

	/**
	 * 약관정보 가져오기
	 * @param hParam
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> selectVersion(HashMap<String, Object> hParam) throws Exception;
	
	/**
	 * 약관정보 저장
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public String insertTerms(MgrTermsVo paramVo) throws Exception;
	
	/**
	 * 
	 * @Method : selectTermsList
	 * @Description : 협력사용 약관동의 정보 리스트 조회
	 * @param paramVo
	 * @return List<MgrTermsVo>
	 * @throws Exception
	 */
	public List<MgrTermsVo> selectTermsList(MgrTermsVo paramVo) throws Exception;
	
	/**
	 * 약관 동의 확인
	 * @param paramVo
	 * @return
	 * @throws Exception
	 */
	public int selectAgreeTermsChk(MgrTermsVo paramVo) throws Exception;
	
	/**
	 * 약관 동의 저장
	 * @param paramVo
	 * @throws Exception
	 */
	public int insertTermsAgree(MgrTermsVo paramVo) throws Exception;
	
	/**
	 * 
	 * @Method : selectTcmTermsRequired
	 * @Description : [필수약관체크용] 필수 동의가 필요한 정보동의 구분코드 조회
	 * @param paramVo
	 * @return List<String>
	 * @throws Exception
	 */
	public List<String> selectTcmTermsRequired(MgrTermsVo paramVo) throws Exception;
	
	/**
	 * 
	 * @Method : selectTcmTermsRequiredChkAgree
	 * @Description : 필수약관 동의여부 확인
	 * @param termsVo
	 * @param termsGbn
	 * @return Result
	 * @throws Exception
	 */
	public Result selectTcmTermsRequiredChkAgree(MgrTermsVo termsVo, String termsGbn) throws Exception;
	
	/**
	 * 
	 * @Method : insertTcmTermsAgreeInfo
	 * @Description : 약관동의정보 등록
	 * @param termsVo
	 * @param termsGbn
	 * @param chkReqYn
	 * @return Result
	 * @throws Exception
	 */
	public Result insertTcmTermsAgreeInfo(MgrTermsVo termsVo, String termsGbn, String chkReqYn) throws Exception;
}
