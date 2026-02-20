package com.doppio.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.security.service.CustomUser;
import com.doppio.management.service.MgrMenuService;
import com.doppio.management.service.MgrMenuVo;

@Service("mgrMenuService")
public class MgrMenuServiceImpl implements MgrMenuService {

	@Autowired
	private MgrMenuMapper mgrMenuMapper;
	
	/**
	 * 메뉴  조회
	 */
	@Override
	public List<MgrMenuVo> selectMenuList(MgrMenuVo paramVo) throws Exception {
		return mgrMenuMapper.selectMenuList(paramVo);
	}

	/**
	 * 메뉴  등록/수정
	 */
	@Override
	public String updateMenuData(MgrMenuVo paramVo) throws Exception {
		
		// 작업자 아이디  생성
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());

		// 메뉴 등록,수정 [MERGE]
		if("L".equals(paramVo.getNowTypes())){
			mgrMenuMapper.updateMergeMenuLagData(paramVo);		// 대분류 메뉴등록
		}else{
			mgrMenuMapper.updateMergeMenuMidData(paramVo);		// 중,소분류 메뉴등록
		}
		
		return "success";
	}
	
	
	/**
	 * 메뉴 삭제 및 권한제거
	 */
	@Override
	public String deleteMenuData(MgrMenuVo paramVo) throws Exception {
		
		/* 삭제대상메뉴 권한그룹별 권한 삭제-----------------*/
		mgrMenuMapper.deleteMenuAuthData(paramVo);
		/*-----------------------------------------------*/
 
		/*메뉴 삭제---------------------------------------*/
		int cnt = mgrMenuMapper.deleteMenuData(paramVo);
		/*-----------------------------------------------*/
		 
		if(cnt > 0) return "success";
		else return "nodata";
	}
	
	
}
