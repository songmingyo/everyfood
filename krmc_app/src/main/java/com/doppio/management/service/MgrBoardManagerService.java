package com.doppio.management.service;

import java.util.List;

/**
 * 게시판관리 하는 비즈니스 인터페이스 클래스
 * @author DADA
 * @since 2013.03.09
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2013.03.09  DADA          최초 생성 
 *  
 *  </pre>
 */
public interface MgrBoardManagerService {
	
	/**
     * 게시판 내역 조회 리스트
     * 
     * @param MgrBoardManagerVo
     * @return List<MgrBoardManagerVo>
     * @throws Exception
     */
	public List<MgrBoardManagerVo> selectBoardList(MgrBoardManagerVo paramVo) throws Exception;
	
	
	/**
     * 게시판 생성 등록/수정
     * 
     * @param MgrBoardManagerVo
     * @return String
     * @throws Exception
     */
	public String updateBoardManagerData(MgrBoardManagerVo paramVo) throws Exception ;
	
	
	/**
     * 게시판 생성 삭제
     * 
     * @param MgrBoardManagerVo
     * @return String
     * @throws Exception
     */
	public String deleteBoardManagerData(MgrBoardManagerVo paramVo) throws Exception ;
	
}
