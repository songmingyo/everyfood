package com.doppio.community.service;

import java.io.Serializable;

/**
 * @author tak
 * @Description : 게시판 관리 Vo
 * @Class : CmtManagerVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      		수정자 		          수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version : 1.0
 */
public class CmtManagerVo implements Serializable {

	private static final long serialVersionUID = 3636290277045031081L;
	
	/** 게시판코드*/				
	private String boardCd				;
	/**	게시판유형코드[CODE 9020]*/				
	private String boardKindCd			;
	/**	게시판관리자코드*/				
	private String memberCd				;
	/**	업로드 가능 한 파일수*/				
	private String fileCount			;
	/**	업로드 가능 한 파일사이즈*/
	private String fileUploadSize		;
	/**	글쓰기사용 유무*/
	private String writeYn				;
	/**	답글사용 유무*/
	private String replyYn				;
	/**	한줄답글 사용 유무*/
	private String commentYn			;
	/**	게시판사용 유무*/
	private String useYn				;

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
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
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
}
