package com.doppio.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.tronic.exception.BusinessException;
import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.Result;
import com.doppio.common.security.service.CustomUser;
import com.doppio.common.security.service.SecurityUtil;
import com.doppio.common.service.impl.CommonMapper;
import com.doppio.management.service.MgrGroupVo;
import com.doppio.management.service.MgrMemberService;
import com.doppio.management.service.MgrMemberVo;

@Service("mgrMemberService")
public class MgrMemberServiceImpl implements MgrMemberService {

	@Autowired
	private MgrMemberMapper  mgrMemberMapper;
	
	@Autowired
	private CommonMapper  commonMapper;
	
	@Value("#{jdbc['jdbc.dbname']}")
	public String dbName;
	
	/**
	 * 사용자 목록 조회
	 */
	@Override
	public Map<String,Object> memberSearchGridList(MgrMemberVo paramVo) throws Exception {
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		int total = 0;
		int totalCount = 0; 
		
		int rows = Integer.parseInt(paramVo.getRows()); // 목록에 보여질 갯수
		int page = Integer.parseInt(paramVo.getPage()); // page parameter
		
		paramVo.setStartRowNo((page * rows)-rows + 1);
		paramVo.setEndRowNo(page * rows);	
		
		List<MgrMemberVo> list = mgrMemberMapper.selectMemberList(paramVo);
		
		if(list.size()>0) {
			MgrMemberVo mvo = list.get(0);
			total = ((mvo.getTotalCount() % rows) == 0) ? (mvo.getTotalCount() / rows) : (mvo.getTotalCount() / rows)  + 1;		
			totalCount = mvo.getTotalCount();
		}

		
		result.put("list", list);
		result.put("total", total);
		result.put("records", totalCount);
		
		
		return result;
	}
	
	
	/**
	 * 사용자 상세 조회
	 */
	@Override
	public MgrMemberVo selectMemberData(MgrMemberVo paramVo) throws Exception {
		MgrMemberVo vo = mgrMemberMapper.selectMemberData(paramVo);
		return vo;
	}

	/**
	 * 사용자  권한목록 조회
	 */
	@Override
	public List<MgrGroupVo> selectGroupList(String memberCd) throws Exception {
		return mgrMemberMapper.selectMemberGroupList(memberCd);
	}
	
	
	/**
	 * 사용자정보 저장
	 */
	@Override
	public String updateMemberData(MgrMemberVo paramVo) throws Exception {
		
		// 암호화된 비밀번호 찾기 답변
		String encFindPwdAns = ""; 

		// 작업자 아이디  생성
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());

		// 아이디 중복확인
		if(StringUtils.isEmpty(paramVo.getMemberCd()) || !paramVo.getUserIdOld().trim().equals(paramVo.getUserId())) {
			int cnt = commonMapper.selectUserIdValidation(paramVo.getUserId());
			if(cnt > 0){
				return "dupl";
			}
		}

		// 그룹삭제
		if(!StringUtils.isEmpty(paramVo.getMemberCd())){
			mgrMemberMapper.deleteMemberAuth(paramVo.getMemberCd());
		}
		
		// 신규 ID 생성
		if(StringUtils.isEmpty(paramVo.getMemberCd())) {
			paramVo.setWorkType("I");		//작업유형-신규
			paramVo.setPassFailCnt("0");
			paramVo.setLockYn("N");
			paramVo.setGenDivnCd("9007001");/*[9007: 생성구분코드]-9007001 입점상담 시스템 사용자 9007002 기간계 인터페이스 9007003 인증서 사용자*/
		}else{
			paramVo.setWorkType("U");		//작업유형-변경
		}
				
		// 패스워드 암호화
		if(paramVo.getWorkType().equals("U") && StringUtils.isEmpty(paramVo.getUserPw())){
			//기존 사용자 패스워드 미변경시
			paramVo.setUserPw(paramVo.getHiddenPasswd());
		}else{
			//String encryptPassword = passwordEncoder.encode(paramVo.getUserPw());
			String encryptPassword = SecurityUtil.getEncryptedPassword(paramVo.getUserPw(), "");
			
			paramVo.setUserPw(encryptPassword);
		}

		// 운영관리자 SECURITY 권한 추가  (다중설정일경우 상위권한 순서로 등록 )
		if("SA".equals(paramVo.getUserType())){
			paramVo.setUserRoles("ROLE_ADMIN, ROLE_SYSTEM");
		}else if("SU".equals(paramVo.getUserType())){
			paramVo.setUserRoles("ROLE_SYSTEM, ROLE_USER");
		}else{
			paramVo.setUserRoles("ROLE_USER");
		}


		// 비밀번호 찾기 답 암호화
		if(!StringUtils.isEmpty(paramVo.getFindPwdAnswer())) {
			//encFindPwdAns = passwordEncoder.encode(paramVo.getFindPwdAnswer());
			//encFindPwdAns = SecurityUtil.getEncryptedPassword(paramVo.getFindPwdAnswer(), paramVo.getMemberCd());
			encFindPwdAns = SecurityUtil.getEncryptedPassword(paramVo.getFindPwdAnswer(), "");
			paramVo.setFindPwdAnswer(encFindPwdAns);
		}
		
		// 등록/수정
		mgrMemberMapper.updateMergeMemberMergeData(paramVo);
		
		// 그룹등록
		mgrMemberMapper.insertMemberAuth(paramVo);
		
		return "success";
	}

	@Override
	public List<MgrMemberVo> selectAccessIpList(MgrMemberVo paramVo) throws Exception {
		return mgrMemberMapper.selectAccessIpList(paramVo);
	}
	
	/**
	 * 사용자 정보- 비밀번호 변경
	 */
	@Override
	public Result updateMgrPassWord(MgrMemberVo paramVo) throws Exception{
		Result result = new Result();
		result.setMsgCd(Result.FAIL);
		
		//작업자 정보=====================================================================================
			CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			paramVo.setWorkId(customUser.getMemberCd());
		//=============================================================================================
		
		String pw = StringUtils.defaultString(paramVo.getUserPw());			 //비밀번호
		String userId = StringUtils.defaultString(paramVo.getUserId());		 //아이디
		String memberCd = StringUtils.defaultString(paramVo.getMemberCd());  //사용자 고유번호
		
		String notIncId = "";
		
		if ("".equals(memberCd)) {
			throw new BusinessException("noData","사용자고유번호 정보가 없습니다.");
			
		}else if("".equals(userId)) {
			throw new BusinessException("noData","사용자 아이디 정보가 없습니다.");
			
		}else if("".equals(pw)) {
			throw new BusinessException("noData","비밀번호를 입력하세요.");
			
		} 
			
		MgrMemberVo memVo = mgrMemberMapper.selectMemberData(paramVo);
		String useYn = StringUtils.defaultString(memVo.getUseYn());
		
		if("N".equals(useYn)) {
			//탈퇴회원인경우 회원정보 수정 불가
			throw new BusinessException("withDrawFg","탈퇴처리된 회원입니다. 비밀번호를 변경할 수 없습니다.");
		}
		
		//비밀번호에 아이디 포함여부 확인	
		if(userId.indexOf("@")>-1) {
			notIncId = userId.split("@")[0];
		}else {
			notIncId = userId;
		}
		
		if(pw.indexOf(notIncId)>-1) {
			throw new BusinessException("incId","비밀번호에 아이디는 포함할 수 없습니다.");
		}
		
		//비밀번호 형식 확인
		/*
		if(!pw.matches("^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$")) {
			throw new BusinessException("notMatch","비밀번호는 숫자와 영문자,특수문자를 혼용하여 8~20글자를 입력해야 합니다.");
		}
		*/
		//비밀번호를 오늘날짜로 임의 변경 >>사용시 위에 validation 주석처리 필요
		//pw = DateUtil.getTimeStamp().substring(0,8);
		
		//비밀번호
		String encryptPassword = SecurityUtil.getEncryptedPassword(pw);
		paramVo.setUserPw(encryptPassword);
		paramVo.setPassChgYn("Y"); //비밀번호 필수변경 Y
		paramVo.setPassFailCnt("0");//비밀번호 실패횟수 초기화
		paramVo.setLockYn("N");//Lock 여부 N

		
		int cnt = mgrMemberMapper.updateMgrPassWord(paramVo);
		
		if(cnt>0) {
			result.setMsgCd(Result.SUCCESS);
		}
		
		return result;
	}
}
