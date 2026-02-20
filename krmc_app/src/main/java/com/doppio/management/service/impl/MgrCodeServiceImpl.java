package com.doppio.management.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.security.service.CustomUser;
import com.doppio.management.service.MgrCodeService;
import com.doppio.management.service.MgrCodeVo;

@Service("mgrCodeService")
public class MgrCodeServiceImpl implements MgrCodeService {
	private static final Logger logger = LoggerFactory.getLogger(MgrCodeServiceImpl.class);
	
	@Resource
    SqlSessionFactory sqlSessionFactory;

	@Autowired
	private MgrCodeMapper  mgrCodeMapper;


	/**
	 * 마스터 코드 조회
	 */
	public List<MgrCodeVo> selectCodeMasterList(MgrCodeVo paramVo){
		return mgrCodeMapper.selectCodeMasterList(paramVo);
    }

	/**
	 * 서브 코드 조회
	 */
	public List<MgrCodeVo> selectCodeSubList(MgrCodeVo paramVo){
		return mgrCodeMapper.selectCodeSubList(paramVo);
	}

	/**
	 * 마스터코드 저장
	 */
	public String updateCodeMaster(MgrCodeVo paramVo,HttpServletRequest request) throws Exception {

		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
		/*-------------------------------------------------------*/

		/* 코드 중복체크-------------------------------------------*/
		/* 입력값과 기 등록된 값이 다르면 중복확인 함                    */
		if(!paramVo.getComCd().equals(paramVo.getComCdOld())) {
			String cnt = mgrCodeMapper.selectMasterCodeCount(paramVo);
			if(Integer.parseInt(cnt) > 0 ) {
				return  "duple";
			}
		}
		/*-------------------------------------------------------*/

		//수정일경우
		if(!StringUtils.isEmpty(paramVo.getComCdOld()) ) {
			// 분류코드가 변경되었을경우 세부코드의 분류코드 전체 UPDATE함
			mgrCodeMapper.upateCodeSubAll(paramVo);
			// 분류 코드 수정
			mgrCodeMapper.updateCodeMaster(paramVo);

		}else {
			/* 분류 코드 저장(신규등록)----------------------------*/
			mgrCodeMapper.insertCodeMaster(paramVo);
			/*-------------------------------------------------------*/
		}

//		setInitializationCode();	// TAG LIB용  CACHE 초기화

		return "success";
	}



	/**
	 * 마스터코드 삭제
	 */
	public String deleteMaString(MgrCodeVo paramVo) throws Exception {

		paramVo.setComCd(paramVo.getComCdOld());	// 세부코드삭제용 키

		mgrCodeMapper.deleteCodeMaster(paramVo);		//마스터코드삭제
		mgrCodeMapper.deleteCodeSub(paramVo);			//마스터코드의 세부코드 전체삭제

//		setInitializationCode();	// TAG LIB용  CACHE 초기화
		return "success";
	}

	/**
	 * 서브코드 저장
	 */
	public String updateCodeSub(MgrCodeVo paramVo,HttpServletRequest request) throws Exception {

		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
		/*-------------------------------------------------------*/

		/*코드 중복체크--------------------------------------------*/
		if(!paramVo.getDtlCd().equals(paramVo.getDtlCdOld())) {	// 입력값과 기 등록된 값이 다르면 중복확인 함
			String cnt = mgrCodeMapper.selectSubCodeCount(paramVo);
			if(Integer.parseInt(cnt) > 0 ) {
				return  "duple";
			}
		}
		/*-------------------------------------------------------*/

		/*코드 서브 저장(신규등록/수정)----------------------------*/
		if(StringUtils.isEmpty(paramVo.getSortNo())) paramVo.setSortNo("0");


		if(StringUtils.isNotEmpty(paramVo.getDtlCdOld()) )
			mgrCodeMapper.updateCodeSub(paramVo);
		else  mgrCodeMapper.insertCodeSub(paramVo);
		/*-------------------------------------------------------*/

//		setInitializationCode();	// TAG LIB용  CACHE 초기화 

		return "success";
	}

	/**
	 * 서브코드 삭제
	 */
	public String deleteCodeSub(MgrCodeVo paramVo) throws Exception {
		mgrCodeMapper.deleteCodeSub(paramVo);

//		setInitializationCode();
		return "success";
	}

	/**
	 * 마스터코드 서브코드 카운트(중복확인)
	 */
	public String selectMasterSubCodeCount(MgrCodeVo paramVo) throws Exception {
		String codeCnt    = "";

		/* getEtcType 의 넘어온값에따라 마스터 카운트 체크인지 서브카운트 체크인지 구분
		 * getEtcType 값이 없으면 오류메세지 리턴
		 */
		if("M".equals(paramVo.getEtcType()))
			codeCnt = mgrCodeMapper.selectMasterCodeCount(paramVo);
		else if("S".equals(paramVo.getEtcType()))
			codeCnt = mgrCodeMapper.selectSubCodeCount(paramVo);
		else return "type-null";

		/* 카운트 가 0 보다크면 중복 오류 에러메시지 리턴-----------*/
		if(Integer.parseInt(codeCnt) > 0 ) {
			return  "duple";
		}
		else return  "success";
		/*----------------------------------------------------*/
	}


	/**
	 * 마스터코드 카운트(중복확인)
	 */
	public String selectMasterCodeCount(MgrCodeVo paramVo) throws Exception {
		return mgrCodeMapper.selectMasterCodeCount(paramVo);
	}

	/**
	 * 서브코드 카운트(중복확인)
	 */
	public String selectSubCodeCount(MgrCodeVo paramVo) throws Exception {
		return mgrCodeMapper.selectSubCodeCount(paramVo);
	}


	/**
	 * SQL Cache 초기화(미사용)
	 *   - 향후 공통으로 이동
	 */
	private void setInitializationCode(){
		Configuration config = sqlSessionFactory.getConfiguration();
		Cache cache = config.getCache("com.doppio.common.taglibs.service.impl.CustomTagComboMapper");
		try{if(cache != null) cache.clear();}catch(Exception ex){ logger.error("setInitializationCode error : " +ex.getMessage()); }

	}


}
