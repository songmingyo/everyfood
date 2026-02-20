package com.doppio.community.service;

import java.io.Serializable;

import org.springframework.tronic.util.DateUtil;

/**
 * @author tak
 * @Description : 게시판 코멘트 Vo
 * @Class : CmtCommentVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      		수정자 		          수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version : 1.0
 */
public class CmtCommentVo implements Serializable {


	private static final long serialVersionUID = -7345348349103060706L;
	
	/**	게시물인덱스*/				
	private String boardIdx				;
	/**	한줄댓글인덱스*/				
	private String commentIdx			;
	/**	한줄댓글내용*/
	private String commentsContent		;
	/**	한줄댓글작성자 코드*/
	private String memberCd				;
	/**	게시판코드*/				
	private String boardCd				;
	/**	사용자명*/				
	private String userNm				;	
	/**	사용자ID*/				
	private String regDt				;
	/**	등록자ID*/
	private String regId				;
	/**	수정일시*/
	private String modDt				;
	/**	수정자ID*/
	private String modId				;
	
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(String commentIdx) {
		this.commentIdx = commentIdx;
	}
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(String boardCd) {
		this.boardCd = boardCd;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getRegDtLocale() {
		return DateUtil.convertDateLocale(regDt,"yyyyMMddHHmmss","");
	}
	
}
