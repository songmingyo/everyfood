package com.doppio.management.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.exception.BusinessException;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.management.service.MgrTermsService;
import com.doppio.management.service.MgrTermsVo;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/**
 * 
 * @Class : MgrTermsServiceImpl.java
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
@Service("mgrTermsService")
public class MgrTermsServiceImpl implements MgrTermsService {

	@Autowired
	MgrTermsMapper mgrTermsMapper;
	
	/**
	 * 약관정보 가져오기
	 */
	public List<HashMap<String, String>> selectVersion(HashMap<String, Object>hParam) throws Exception {
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();
		hParam.put("language", language.toUpperCase());
		
		return mgrTermsMapper.selectVersion(hParam);
	}
	
	/**
	 * 약관정보 저장
	 */
	public String insertTerms(MgrTermsVo paramVo) throws Exception {
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
//		paramVo.setRegId(customUser.getMemberCd());
		//버전 정보 수정 안하면 저장 안되게 막기
		//버전 정보 불러오기
		String mgrversion = mgrTermsMapper.selectTermsVersion(paramVo.getTermsDivnCd());
		//처음 등록할 때 정보 있는지 확인
		int tcmTerms = mgrTermsMapper.selectTerms(paramVo.getTermsDivnCd());
		
		//정보가 있을 떼
		if(tcmTerms > 0) {
			//버전 정보가 같으면 already
			if(mgrversion.equals(paramVo.getTermsVersionInfo())) {
	 			return "already";
			}
		}
		//약관정보 저장
		int count = mgrTermsMapper.insertTerms(paramVo);
		
		//저장 후 버전값 변경
		int version = mgrTermsMapper.updateVersion(paramVo);
		return "success";
	}
	
	/**
	 * 협력사용 약관동의 정보 리스트 조회
	 */
	public List<MgrTermsVo> selectTermsList(MgrTermsVo paramVo) throws Exception {
		return mgrTermsMapper.selectTermsList(paramVo);
	}
	
	/**
	 * 약관 동의 확인
	 */
	public int selectAgreeTermsChk(MgrTermsVo paramVo) throws Exception {
		return mgrTermsMapper.selectAgreeTermsChk(paramVo);
	}
	
	/**
	 * 약관 동의 저장
	 */
	public int insertTermsAgree(MgrTermsVo paramVo) throws Exception {
		return mgrTermsMapper.insertTermsAgree(paramVo);
	}
	
	/**
	 * [필수약관체크용] 필수 동의가 필요한 정보동의 구분코드 조회
	 */
	public List<String> selectTcmTermsRequired(MgrTermsVo paramVo) throws Exception{
		return mgrTermsMapper.selectTcmTermsRequired(paramVo);
	}
	
	/**
	 * 필수약관 동의여부 확인
	 * 
	 * 001 : 약관동의정보/약관동의자정보 없음
	 * 002 : 필수데이터 없음
	 * 003 : 필수 약관 미동의
	 * success : 성공
	 * fail : 실패
	 */
	public Result selectTcmTermsRequiredChkAgree(MgrTermsVo termsVo, String termsGbn) throws Exception {
		//기본 결과값 셋팅
		Result result = new Result();
		result.setMsgCd(Result.FAIL);
		
		//[001] 약관 동의정보가 없을 경우 return fail
		if(termsVo == null) {
			result.setMsgCd("TERM001");
			result.setMessage("동의 약관 정보 누락 ::: termsVo");
			return result;
		}
		//[002] 필수약관 동의여부 체크용 구분값 존재여부 확인
		if("".equals(termsGbn)) {
			result.setMsgCd("TERM002");
			result.setMessage("필수 데이터 누락 ::: termsGbn");
			return result;
		}
		
		
		/* 1. 필수약관 리스트 조회 --------------------------------------------------------------*/
		List<String> reqTermsList = null;			//필수 동의약관 list
		MgrTermsVo reqVo = new MgrTermsVo();		//필수 동의약관 조회용 vo
		
		switch(termsGbn) {
			case "JOINUS":					//------------------- 회원가입
				reqVo.setExtent01("1");
				break;
			case "BANKACC":
				reqVo.setExtent01("5");
				break;
			default : 
				break;
		}
		
		//필수 동의 약관 list 조회
		reqTermsList = mgrTermsMapper.selectTcmTermsRequired(reqVo);
		/* --------------------------------------------------------------------------------*/
		
		/* 2. 모든 필수약관에 동의했는지 확인 -----------------------------------------------------*/
		//사용자가 동의한 약관정보
		String joinTermsMapp = StringUtils.defaultString(termsVo.getJoinTermsMapp());
		List<String> agreeTermsList = Arrays.asList(joinTermsMapp.split(","));
		
		//필수 약관이 있는 경우에만 체크
		if(reqTermsList != null && reqTermsList.size() > 0) {
			//[001] 사용자가 동의한 약관정보 없음
			if(agreeTermsList == null || agreeTermsList.size() < 0) {
				result.setMsgCd("TERM001");
				result.setMessage("동의 약관 정보 누락 ::: joinTermsMapp");
				return result;
			}
			
			//[003] 모든 필수약관에 동의하지 않음
			if(!agreeTermsList.containsAll(reqTermsList)) {
				result.setMsgCd("TERM003");
				result.setMessage("필수 약관 미동의 ::: agreeTermsList");
				return result;
			}
		}
		/* --------------------------------------------------------------------------------*/
		
		//return data 생성
		HashMap<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("agreeTermsList", agreeTermsList);		//사용자가 동의한 약관 list
		
		//체크 성공 시 결과값 셋팅
		result.setMsgCd(Result.SUCCESS);
		result.setResultMap(resultMap);
		return result;
	}
	
	/**
	 * 약관동의 정보 등록
	 * 
	 * 001 : 약관동의정보/약관동의자정보 없음
	 * 002 : 필수데이터 없음
	 * 003 : 필수 약관 미동의
	 * success : 성공
	 * fail : 실패
	 */
	public Result insertTcmTermsAgreeInfo(MgrTermsVo termsVo, String termsGbn, String chkReqYn) throws Exception {
		Result result = new Result();
		result.setMsgCd(Result.FAIL);
		
		/* 1. 필수 데이터 확인  ----------------------------------------------------------------*/
		//[001] 약관 동의정보가 없을 경우 return fail
		if(termsVo == null) {
			result.setMsgCd("TERM001");
			result.setMessage("동의 약관 정보 누락 ::: termsVo");
			return result;
		}
		
		//약관 동의자 고유번호(멤버코드)
		String termsPclNo = StringUtils.defaultString(termsVo.getTermsPclNo());
		
		//[001] 약관 동의자 정보가 없을 경우 return fail
		if("".equals(termsPclNo)) {
			result.setMsgCd("TERM001");
			result.setMessage("약관 동의자 정보 누락 ::: termsPclNo");
			return result;
		}
		/* --------------------------------------------------------------------------------*/
		
		/* 2. 필수 약관 동의 여부 확인-----------------------------------------------------------*/
		//chkReqYn 이 Y일 경우, 필수약관 동의여부 체크
		if("Y".equals(StringUtils.defaultString(chkReqYn))) {
			//필수약관 동의여부 확인
			Result resultChkReq = new Result();
			resultChkReq = this.selectTcmTermsRequiredChkAgree(termsVo, termsGbn);
			String resultChkReqMsgCd = StringUtils.defaultString(resultChkReq.getMsgCd());
			
			if(!(Result.SUCCESS).equals(resultChkReqMsgCd)) {
				// 필수약관 동의여부 확인 실패 시, 해당 결과값 return
				throw new BusinessException(StringUtils.defaultString(resultChkReq.getResultCode()), resultChkReq.getMessage());
			}else {
				// 필수약관 동의여부 확인 성공 시,
				
				HashMap<String,Object> chkedAgreeTermsList = resultChkReq.getResultMap();
				//[001] 동의한 약관 정보 없음_returnMap 없음
				if(chkedAgreeTermsList == null) {
					result.setMsgCd("TERM001");
					result.setMessage("약관 동의 정보 누락 ::: chkedAgreeTermsList");
					return result;
				}
				
				//동의한 약관 정보 Vo에 셋팅
				termsVo.setTermsDivnCds((List<String>) chkedAgreeTermsList.get("agreeTermsList"));
			}
		}
		/* --------------------------------------------------------------------------------*/
		
		/* 3. 약관 동의 정보 등록 -------------------------------------------------------------*/
		//사용자가 동의한 약관 리스트
		List<String> agreeTermsList = termsVo.getTermsDivnCds();
		
		//[001] 동의한 약관 정보가 없음
		if(agreeTermsList == null || agreeTermsList.size() < 1) {
			result.setMsgCd("TERM001");
			result.setMessage("약관 동의 정보 누락 ::: agreeTermsList");
			return result;
		}
		
		//사용자가 동의한 약관 버전정보 조회
		List<MgrTermsVo> agreeTermsVo = mgrTermsMapper.selectTermsList(termsVo);
		
		//동의약관 정보 저장
		for(MgrTermsVo vo : agreeTermsVo) {
			vo.setTermsPclNo(termsPclNo);	//약관동의자 정보셋팅
			mgrTermsMapper.insertTermsAgree(vo);
		}
		/* --------------------------------------------------------------------------------*/
		
		//성공시 결과값 셋팅
		result.setMsgCd(Result.SUCCESS);
		return result;
	}
}
