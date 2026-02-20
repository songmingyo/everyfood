package com.doppio.community.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.ModelAndView;

import com.doppio.common.model.AttachFileVo;

public interface CmtBoardService {
	
	
	/**
	 * 게시판 코드/제한값 확인
	 * @param cmtBoard001VO
	 * @return CmtManagerVo
	 * @throws Exception
	 */
	CmtManagerVo selectBoardManager(CmtBoardVo paramVo) throws Exception ;
	
	/**
	 * 게시판 리스트 불러오기 
	 * @param CmtBoardVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	Map<String,Object> selectBoardList(CmtBoardVo paramVo) throws Exception ;
	
	/**
	 * 게시판 리스트 불러오기 
	 * @param CmtBoardVo
	 * @return Map<String,Object>
	 * @throws Exception
	 */
	Map<String,Object> selectBoardMainList(CmtBoardVo paramVo) throws Exception ;

	/**
	 * 게시물 총 수
	 * @param CmtBoardVo
	 * @return INTEGER
	 * @throws Exception
	 */
	int selectListTotCnt(CmtBoardVo paramVo)throws Exception;
	
	/**
	 * 조회수 증가
	 * @param cmtBoard001VO
	 * @throws Exception
	 */
	void updateBoardCount(CmtBoardVo paramVo) throws Exception ;

	/**
	 * 게시판 상세보기
	 * @param CmtBoardVo
	 * @return CmtBoardVo
	 * @throws Exception
	 */
	CmtBoardVo selectListDetail(CmtBoardVo paramVo) throws Exception ;
	
	/**
	 * 다음글/이전글 조회
	 * @param CmtBoardVo
	 * @return List<CmtBoardVo>
	 * @throws Exception
	 */
	List<CmtBoardVo> selectListPreNext(CmtBoardVo paramVo) throws Exception;
	
	/**
	 * 부모글 Thread/Depth 가져오기
	 * @param CmtBoardVo
	 * @return CmtBoardVo
	 * @throws Exception
	 */
	CmtBoardVo selectListThreadDepth(CmtBoardVo paramVo) throws Exception;
	
	/**
	 * 수정 시 파일 조회
	 * @param CmtBoardVo
	 * @return List<AttachFileVo>
	 * @throws Exception
	 */
	List<AttachFileVo> selectListFile(CmtBoardVo paramVo) throws Exception;
	
	/**
	 * 게시물 Index 가져오기
	 * @return String
	 * @throws Exception
	 */
	String selectListIndex() throws Exception ;
	
	/**
	 * 게시글 등록/수정
	 * @param CmtBoardVo, request
	 * @throws Exception
	 */
	void insertUpdateBoardList(CmtBoardVo paramVo, HttpServletRequest request) throws Exception ;
	
	/**
	 *  게시판 글 삭제
	 * @param CmtBoardVo
	 * @return String
	 * @throws Exception
	 */
	String deleteBoardList(CmtBoardVo paramVo) throws Exception ;
	
	/**
	 * 한줄답글 조회
	 * @param CmtCommentVo
	 * @return List<CmtCommentVo>
	 * @throws Exception
	 */
	List<CmtCommentVo> selectListComment(CmtCommentVo paramVo) throws Exception ;
	
	/**
	 * 한줄답글 입력
	 * @param CmtCommentVo
	 * @return String
	 * @throws Exception
	 */
	String insertBoardComment(CmtCommentVo paramVo) throws Exception ;
	
	/**
	 * 한줄답글 삭제
	 * @param CmtCommentVo
	 * @return String
	 * @throws Exception
	 */
	String deleteBoardComment(CmtCommentVo paramVo) throws Exception ;
	
	/**
	 * 파일 삭제
	 * @param CmtBoardVo
	 * @throws Exception
	 */
	String deleteBoardFile(CmtBoardVo paramVo) throws Exception ;
	
	/**
	 * 게시판 및 게시글 유무 확인
	 * @param  CmtBoardVo
	 * @return boolean
	 * @throws Exception
	 */
	boolean selectBoardDataCheck(CmtBoardVo paramVo) throws Exception ;
	
	/**
	 * 게시판 상세 및 목록
	 * @param  CmtBoardVo
	 * @return ModelAndView
	 * @throws Exception
	 */
	ModelAndView boardDetail(CmtBoardVo paramVo)throws Exception ;
	
	/**
	 * 게시판 data 권한체크
	 * @param  String
	 * @return boolean
	 * @throws Exception
	 */
	boolean checkedAuthorityException(String boardIdx, HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException, HttpSessionRequiredException, IOException,  SQLException;
	
}
