package com.doppio.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.security.service.CustomUser;
import com.doppio.management.service.MgrAuthListVo;
import com.doppio.management.service.MgrAuthProcessVo;
import com.doppio.management.service.MgrAuthVo;
import com.doppio.management.service.MgrGroupService;
import com.doppio.management.service.MgrGroupVo;
import com.doppio.management.service.MgrMemberVo;

@Service("mgrGroupService")
public class MgrGroupServiceImpl implements MgrGroupService {

	@Autowired
	private MgrGroupMapper  mgrGroupMapper;
	
	
	/**
	 * 그룹정보 조회
	 */
	@Override
	public List<MgrGroupVo> selectGroupList(MgrGroupVo paramVo) throws Exception {
		return mgrGroupMapper.selectGroupList(paramVo);
	}
	
	
	/**
	 * 그룹 상세 조회
	 */
	@Override
	public MgrGroupVo selectGroupData(MgrGroupVo paramVo) throws Exception {
		return mgrGroupMapper.selectGroupData(paramVo);
	}
	

	/**
	 * 그룹 정보 저장
	 */
	@Override
	public String updateGroupData(MgrGroupVo paramVo) throws Exception {
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
		/*-------------------------------------------------------*/
	
		/*그룹아이디 중복확인------------*/
		if("I".equals(paramVo.getSaveMode())) {
			int cnt = mgrGroupMapper.selectGroupIdValidation(paramVo.getGroupId());
			if(cnt > 0)return "duple";
		}
		/*----------------------------*/
		
	
		mgrGroupMapper.mergeGroupData(paramVo);
		
		return "success";
		
	}
	
	
	/**
	 * 그룹 정보 삭제
	 */
	@Override
	public String deleteGroupData(MgrGroupVo paramVo) throws Exception {
		
		mgrGroupMapper.deleteGroup(paramVo);			// 그룹삭제
		mgrGroupMapper.deleteGroupAuth(paramVo);		// 그룹권한삭제
		mgrGroupMapper.deleteMemberAuth(paramVo);		// 사용자 그룹메핑정보 삭제
		
		return "success";
	}
	
	
	
	/**
	 * 그룹에 매핑된 사용자 List 조회
	 */
	@Override
	public List<MgrMemberVo> selectGroupAuthMemberList(MgrMemberVo paramVo) {
		return mgrGroupMapper.selectGroupAuthMemberList(paramVo);
	}


	/**
	 *  매핑된 그룹외 사용자 조회
	 */
	@Override
	public List<MgrMemberVo> selectGroupMemberList(MgrMemberVo paramVo) {
		return mgrGroupMapper.selectGroupMemberList(paramVo);
	}

	
	/**
	 * 사용자 그룹매핑 추가/삭제
	 */
	@Override
	public String updateGroupAuthSave(MgrAuthVo paramVo) throws Exception {
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
		/*-------------------------------------------------------*/
		
		if("I".equals(paramVo.getState()))
			mgrGroupMapper.insertGroupMemberAuth(paramVo);	// 선택사용자 그룹추가
		else if("D".equals(paramVo.getState()))
			mgrGroupMapper.deleteGroupMemberAuth(paramVo);	// 선택사용자 그룹제거
		else return null;
		
		
		return "success";
		
	}
	
	
	/**
	 * 그룹별 메뉴권한 리스트 조회
	 */
	@Override
	public List<MgrAuthVo> selectAuthMenuList(MgrAuthVo paramVo) throws Exception {
		return mgrGroupMapper.selectAuthMenuList(paramVo);
	}
	
	
	
	/**
	 * 그룹권한 저장
	 */
	@Override
	public String updateAuthInfoList(MgrAuthProcessVo paramVo) throws Exception {
		
		/*작업자 아이디  생성---------------------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String workId = customUser.getMemberCd();
		/*-------------------------------------------------------*/
		
		String strState = "";	//변경유무
		int intCnt 		= 0;	//작업카운트
		
		String strStateNew = "";
		String strStateOld = "";
		
		MgrAuthListVo mgrAuthListVo = paramVo.getAuthInfoList();
		for ( MgrAuthVo authInfo : mgrAuthListVo ) {
			
			/*
			System.out.println("=====START PGM ============================================");
			System.out.println(authInfo.getPgmQry().trim()+"|"+authInfo.getPgmQryOld().trim());
			System.out.println(authInfo.getPgmInt().trim()+"|"+authInfo.getPgmIntOld().trim());
			System.out.println(authInfo.getPgmUpt().trim()+"|"+authInfo.getPgmUptOld().trim());
			System.out.println(authInfo.getPgmDel().trim()+"|"+authInfo.getPgmDelOld().trim());
			System.out.println(authInfo.getPgmPrt().trim()+"|"+authInfo.getPgmPrtOld().trim());
			System.out.println("=====END PGM ================================================");
			*/
			
			// 변경사항 검사  ----------------------------------------------
			if((authInfo.getPgmQry().equals(authInfo.getPgmQryOld()) )  &&
			   (authInfo.getPgmInt().equals(authInfo.getPgmIntOld()) )  &&
			   (authInfo.getPgmUpt().equals(authInfo.getPgmUptOld()) )  &&
			   (authInfo.getPgmDel().equals(authInfo.getPgmDelOld()) )  &&
			   (authInfo.getPgmPrt().equals(authInfo.getPgmPrtOld()) ) )
			{
				strState = "F";	
			} else  strState = "T";	
			//-----------------------------------------------------------
			
			
			if(strState.equals("T")){
				intCnt ++;
				strStateNew = authInfo.getPgmQry()+authInfo.getPgmInt()+authInfo.getPgmUpt()+authInfo.getPgmDel()+authInfo.getPgmPrt();
				strStateOld = authInfo.getPgmQryOld()+authInfo.getPgmIntOld()+authInfo.getPgmUptOld()+authInfo.getPgmDelOld()+authInfo.getPgmPrtOld();
			
				authInfo.setWorkId(workId);	// 작업자 아이디
				
				//-- INSERT -----------------------------
				if(strStateOld.trim().length() <= 0 && strStateNew.trim().length() !=0) {
					mgrGroupMapper.insertAuthMenu(authInfo);
				}
 				//-- UPDATE -----------------------------
				else if(strStateOld.length() > 0 && strStateNew.trim().length() > 0) 
					mgrGroupMapper.updateAuthMenu(authInfo);
				//-- DELETE -----------------------------
				else mgrGroupMapper.deleteAuthMenu(authInfo);

			}
		}
		
		
		if(intCnt <= 0)  return  "nodate";
		
		return "success";
		
	}
	
	
	
}
