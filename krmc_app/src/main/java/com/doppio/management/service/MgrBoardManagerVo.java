package com.doppio.management.service;

import java.io.Serializable;

import com.doppio.common.model.Page;

/**
 * 게시판 관리하기 위한 VO 클래스
 * @author DADA
 * @since 2012.12.03
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2013.03.09	DADA			최조작성
 *
 * </pre>
 */
public class MgrBoardManagerVo extends Page implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5231522052580588831L;
	
	
	/** 게시판코드*/
	private String boardCd			;
	/** 게시판유형코드[CODE 9020]*/
	private String boardKindCd		;
	/** 게시판유형코드 명칭*/
	private String boardKindCdNm	;
	/** 게시판유형코드 확장 명칭*/
	private String boardKindCdSubNm	;
	/** 게시판명*/
	private String boardNm			;
	/** 관리자고유번호*/
	private String memberCd			;
	/** 관리자명칭*/
	private String userNm			;
	/** 업로드파일갯수*/
	private String fileCount		;
	/** 파일업로드사이즈*/
	private String fileUploadSize 	;
	/** 글쓰기유무*/
	private String writeYn			;
	/** 댓글사용여부*/
	private String replyYn			;
	/** 한줄답변글사용여부*/
	private String commentYn		;
	/** 사용여부*/
	private String useYn			;
	/** 작업자 아이디*/
	private String workId			;
	
	/** 해당게피판코드에 등록된 게시글*/
	private String boardCount		;
	/** 수정모드의 게시판코드*/
	private String oldBoardCd		;
	
	public String getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(String boardCd) {
		this.boardCd = boardCd;
	}
	public String getBoardKindCd() {
		return boardKindCd;
	}
	public void setBoardKindCd(String boardKindCd) {
		this.boardKindCd = boardKindCd;
	}
	public String getBoardKindCdNm() {
		return boardKindCdNm;
	}
	public void setBoardKindCdNm(String boardKindCdNm) {
		this.boardKindCdNm = boardKindCdNm;
	}
	public String getBoardKindCdSubNm() {
		return boardKindCdSubNm;
	}
	public void setBoardKindCdSubNm(String boardKindCdSubNm) {
		this.boardKindCdSubNm = boardKindCdSubNm;
	}
	public String getBoardNm() {
		return boardNm;
	}
	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getFileCount() {
		return fileCount;
	}
	public void setFileCount(String fileCount) {
		this.fileCount = fileCount;
	}
	public String getFileUploadSize() {
		return fileUploadSize;
	}
	public void setFileUploadSize(String fileUploadSize) {
		this.fileUploadSize = fileUploadSize;
	}
	public String getWriteYn() {
		return writeYn;
	}
	public void setWriteYn(String writeYn) {
		this.writeYn = writeYn;
	}
	public String getReplyYn() {
		return replyYn;
	}
	public void setReplyYn(String replyYn) {
		this.replyYn = replyYn;
	}
	public String getCommentYn() {
		return commentYn;
	}
	public void setCommentYn(String commentYn) {
		this.commentYn = commentYn;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(String boardCount) {
		this.boardCount = boardCount;
	}
	public String getOldBoardCd() {
		return oldBoardCd;
	}
	public void setOldBoardCd(String oldBoardCd) {
		this.oldBoardCd = oldBoardCd;
	}

	
	
	
	
	
	

	
	
	
	

}
