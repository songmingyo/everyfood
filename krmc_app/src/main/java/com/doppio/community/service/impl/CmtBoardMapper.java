package com.doppio.community.service.impl;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.doppio.common.model.AttachFileVo;
import com.doppio.community.service.CmtBoardVo;
import com.doppio.community.service.CmtCommentVo;
import com.doppio.community.service.CmtManagerVo;

@Repository(value = "cmtBoardMapper")
public interface CmtBoardMapper {

	
	/**
	 * 게시판 및 게시글 유무 확인  
	 * @param CmtBoardVo
	 * @return Integer
	 */
	int selectBoardDataCheck(CmtBoardVo paramVo);
	
	
	
	/**
	 * 게시판 관리정보 조회
	 * @param CmtBoardVo
	 * @return CmtManagerVo
	 */
	CmtManagerVo selectBoardManager(CmtBoardVo paramVo);
	
	
	
	/**
	 * 조회수 수정
	 * @param CmtBoardVo
	 * @return Integer
	 */
	int updateBoardCount(CmtBoardVo paramVo);
	
	
	
	/**
	 * 게시판 상세보기
	 * @param CmtBoardVo
	 * @return CmtBoardVo
	 */
	CmtBoardVo selectListDetail(CmtBoardVo paramVo);
	
	
	/**
	 * 이전 다음글 조회
	 * @param CmtBoardVo
	 * @return List<CmtBoardVo>
	 */
	List<CmtBoardVo> selectListPreNext(CmtBoardVo paramVo);

	
	/**
	 * 게시판 글 총 카운트
	 * @param CmtBoardVo
	 * @return Integer
	 */
	int selectListTotCnt(CmtBoardVo paramVo);
	
	/**
	 * 게시판 상단공지 Count
	 * @param paramVo
	 * @return
	 */
	int selectListTopTotCnt(CmtBoardVo paramVo);
	
	
	/**
	 * 게시판 글 조회  
	 * @param CmtBoardVo
	 * @return List<CmtBoardVo> 
	 */
	List<CmtBoardVo> selectBoardList(CmtBoardVo paramVo);
	
	
	/**
	 * 게시판 글 조회  
	 * @param CmtBoardVo
	 * @return List<CmtBoardVo> 
	 */
	List<CmtBoardVo> selectBoardMainList(CmtBoardVo paramVo);
	
	
	/**
	 * 한줄답글 조회
	 * @param CmtCommentVo
	 * @return List<CmtCommentVo>
	 */
	List<CmtCommentVo> selectListComment(CmtCommentVo paramVo);
	
	
	/**
	 * 게시물 Index 가져오기
	 * @return String
	 */
	String selectListIndex();
	
	
	/**
	 * 게시글 등록
	 * @param CmtBoardVo
	 * @return INTEGER
	 */
	void insertBoardList(CmtBoardVo paramVo);

	
	/**
	 * 부모글 Thread/Depth 가져오기
	 * @param CmtBoardVo
	 * @return CmtBoardVo
	 */
	CmtBoardVo selectListThreadDepth(CmtBoardVo paramVo);
	
	
	/**
	 * 수정 시 파일 조회
	 * @param CmtBoardVo
	 * @return List<AttachFileVo>
	 */
	List<AttachFileVo> selectListFile(CmtBoardVo paramVo);
	
	
	/**
	 * 게시글 수정
	 * @param CmtBoardVo
	 * @return INTEGER
	 */
	int updateBoardList(CmtBoardVo paramVo);
	
	
	/**
	 * 한줄답글 입력
	 * @param CmtCommentVo
	 */
	void insertBoardComment(CmtCommentVo paramVo);
	
	
	
	/**
	 * 한줄답글 삭제
	 * @param CmtCommentVo
	 */
	void deleteBoardComment(CmtCommentVo paramVo);

	
	
	/**
	 *  게시판 글 삭제
	 * @param CmtBoardVo
	 */
	void deleteBoardList(CmtBoardVo paramVo);
	
	
	
	/**
	 * 게시물 DATA 권한 체크
	 * @param String
	 * @return
	 */
	HashMap<String, String> selectBoardManagerData(String boardIdx);
	
	
	
}
