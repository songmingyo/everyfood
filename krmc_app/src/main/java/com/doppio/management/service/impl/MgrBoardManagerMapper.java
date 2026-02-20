package com.doppio.management.service.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.management.service.MgrBoardManagerVo;


/**
 * @Class Name : mgrBoardManagerDao.java
 * @Description : 게시판 관리
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2012. 12. 03.     DADA
 *
 * @author  DADA
 * @since 2013. 03. 09.
 * @version
 * @see
 *
 */
@Repository(value = "mgrBoardManagerMapper")
public interface MgrBoardManagerMapper {

	/**
     * 게시판  대상 정보 상세리스트  조회
     * 
     * @param MgrBoardManagerVo
     * @return List<MgrBoardManagerVo>
     */
    List<MgrBoardManagerVo> selectBoardList(MgrBoardManagerVo paramVo);
    
    /**
     * 게시판  대상 정보 등록
     * 
     * @param MgrBoardManagerVo
     * @return INTEGER
     */
    public void insertBoardManager(MgrBoardManagerVo paramVo);
    
    
    /**
     * 게시판  대상 정보 수정
     * 
     * @param MgrBoardManagerVo
     * @return INTEGER
     */
    public int updateBoardManager(MgrBoardManagerVo paramVo);
    
    
    /**
     * 게시판  대상 정보 삭제
     * 
     * @param MgrBoardManagerVo
     * @return INTEGER
     */
    public int deleteBoardManager(MgrBoardManagerVo paramVo);
    
    
    /**
     * 게시판 코드 관련게시글 삭제플레그 처리
     * 
     * @param MgrBoardManagerVo
     * @return INTEGER
     */
    public int updateBoardMstAllDelFlage(MgrBoardManagerVo paramVo);
    
    /**
     * 게시판 코드 관련게시글  코드 업데이트 처리
     * 
     * @param MgrBoardManagerVo
     * @return INTEGER
     */
    public int updateBoardMstBoardCd(MgrBoardManagerVo paramVo);
    
    /**
     * 게시판코드 중복 확인 카운트
     * 
     * @param MgrBoardManagerVo
     * @return String
     */
    public String selectBoardManagerCount(MgrBoardManagerVo paramVo);
	

}
