package com.doppio.management.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doppio.common.security.service.CustomUser;
import com.doppio.management.service.MgrBoardManagerService;
import com.doppio.management.service.MgrBoardManagerVo;


/**
 * @author dada
 * @Description : 게시판 관리 서비스 구현
 * @Class : MgrBoardManager001ServiceImpl
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  
 * </pre>
 * @version : 1.0
 */

@Service("mgrBoardManagerService")
public class MgrBoardManagerServiceImpl  implements MgrBoardManagerService {

	@Autowired
	private MgrBoardManagerMapper mgrBoardManagerMapper;
	
    
    
	 /**
     * 게시판 생성 정보 리스트  Data
     * 
     * @param MgrBoardManagerVo
     * @return List<MgrBoardManagerVo>
     * @throws Exception
     */
	@Override
	public List<MgrBoardManagerVo> selectBoardList(MgrBoardManagerVo paramVo) throws Exception {
		return mgrBoardManagerMapper.selectBoardList(paramVo);
	}
	
	
	/**
     * 게시판 생성 등록/수정
     * 
     * @param MgrBoardManagerVo
     * @return String
     * @throws Exception
     */
	@Override
	public String updateBoardManagerData(MgrBoardManagerVo paramVo) throws Exception {
		
		/*작업자 아이디  생성----------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
		/*---------------------------------------------*/
		
		/* 코드 중복체크--------------------------------*/
		/* 입력값과 기 등록된 값이 다르면 중복확인 함        */
		if(!paramVo.getBoardCd().equals(paramVo.getOldBoardCd())) {	
			String cnt = mgrBoardManagerMapper.selectBoardManagerCount(paramVo);
			if(Integer.parseInt(cnt) > 0 ) return  "duple";
		}
		/*--------------------------------------------*/
		
		/* 등록파일 속성이 없을경우 '0/50' ---------------*/
		if(paramVo.getFileUploadSize().isEmpty() || "0".equals(paramVo.getFileUploadSize())) {
			
			// 업로드 파일 갯수가 있고 파일사이즈가 없을경우 기본 50M
			if(paramVo.getFileCount().isEmpty())
				paramVo.setFileUploadSize("0");
			else paramVo.setFileUploadSize("50");	
		}

		if(paramVo.getFileCount().isEmpty()) {
			paramVo.setFileCount("0");
		}
		/*---------------------------------------------*/
		
		if(StringUtils.isNotEmpty(paramVo.getOldBoardCd()) ) {		// 수정
			
			// 게시판 코드 수정
			mgrBoardManagerMapper.updateBoardManager(paramVo);
			
			// 게시판 코드가 변경되었을경우 관련게시글 전체 UPDATE함
			if(!paramVo.getBoardCd().equals(paramVo.getOldBoardCd())) 
				mgrBoardManagerMapper.updateBoardMstBoardCd(paramVo);
			
		}else {																	    // 신규
			/* 게시판 코드 신규등록----------------------*/
			mgrBoardManagerMapper.insertBoardManager(paramVo);
			/*-----------------------------------------*/
		}
		
		
		return "success";
		
	}
	
	
	/**
     * 게시판 생성 삭제
     * 
     * @param MgrBoardManagerVo
     * @return String
     * @throws Exception
     */
	public String deleteBoardManagerData(MgrBoardManagerVo paramVo) throws Exception {
		
		/*작업자 아이디  생성----------------------------*/
		CustomUser customUser = (CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		paramVo.setWorkId(customUser.getMemberCd());
		/*---------------------------------------------*/
		
		mgrBoardManagerMapper.deleteBoardManager(paramVo);			//게시판관리 DATA 삭제 (DELFETE)	
		mgrBoardManagerMapper.updateBoardMstAllDelFlage(paramVo);		//관련게시글 삭제 플레그 처리(UPDATE)
		
		return "success";
	}
	
	
	
	
	
}
