package com.doppio.community.service;

import java.io.Serializable;

import org.springframework.tronic.util.DateUtil;

import com.doppio.common.model.PageN;

/**
 * @author tak
 * @Description : 게시판 글 VO
 * @Class : CmtBoardVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      		수정자 		          수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version : 1.0
 */
public class CmtBoardVo extends PageN implements Serializable {

	private static final long serialVersionUID = 2454537810526737315L;
	
	/**	게시인덱스*/				
	private String boardIdx				;
	/**	게시판코드*/
	private String boardCd				;
	/** 답글의 부모글 인덱스*/
	private String parentBoardIdx		;
	/**	게시물출력순번*/
	private int thread					;
	/**	답변글깊이*/
	private int depth					;
	/**	게시판유형코드[CODE 9020]*/
	private String boardKindCd			;
	/**	게시제목*/
	private String boardTitle			;
	/**	게시제목*/
	private String boardContent			;
	/**	조회카운트*/
	private String boardCount			;
	/**	첨부파일ID */
	private String atchFileId			;
	/**	첨부파일SEQ*/
	private String seq					;
	/** 첨부파일갯수 */
	private String atchFileCnt			;
	/**	등록자명*/
	private String userNm				;
	/**	등록자코드*/
	private String memberCd				;
	/**	등록자EMAIL*/
	private String email				;
	/**	등록자ID*/
	private String regId				;
	/**	등록일시*/
	private String regDt				;
	/**	수정자 ID*/
	private String modId				;
	/**	수정일시*/
	private String modDt				;
	/**	검색조건[제목]*/
	private String titleYn				;
	/**	검색조건[내용]*/
	private String contentYn			;
	/**	검색조건[작성자]*/
	private String userNmYn				;
	/**	검색단어*/
	private String search				;
	/**	오늘날짜*/
	private String toDy					;
	/**	한줄댓글 수*/
	private String commentNum			;
	/**	다음글/이전글 구분*/
	private String preNext				;
	/**	사용자 ID*/
	private String userId				;
	/**	상단공지 여부*/
	private String boardNoticeYn		;
	/**	강조사용 여부*/
	private String isUrgencyYn			;
	/**	게시취소 여부*/
	private String delYn				;
	/**	팝업 공지 시작기간*/
	private String alertFrDt			;
	/**	팝업 공지 종료기간*/
	private String alertToDt			;
	/**	수정/답변 유무*/
	private String updateAnswer			;
	/**	공지/일반글 구분*/
	private String noticeYn				;
	/**	게시판 관리자 코드*/
	private String boardMemberCd		;
	/** 게시 노출 유형*/
	private String boardExposureCd		;
	/** 게시 노출 유형*/
	private String boardExposureNm		;
	/** 팝업 호출 유무 */
	private String alertYn				;
	/** 팝업 최대 개수 */
	private String rowCount				;
	
	/** 210826 요청순번(최신것부터 1시작)*/
	private String rnumSub				;
	/**게시구분코드(9022)*/
	private String alertTypeCd			;
	/**게시구분코드명칭(9022)*/
	private String alertTypeNm			;
	
	public String getRowCount() {
		return rowCount;
	}
	public void setRowCount(String rowCount) {
		this.rowCount = rowCount;
	}
	public String getAlertYn() {
		return alertYn;
	}
	public void setAlertYn(String alertYn) {
		this.alertYn = alertYn;
	}
	public String getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(String boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardCd() {
		return boardCd;
	}
	public void setBoardCd(String boardCd) {
		this.boardCd = boardCd;
	}
	public String getParentBoardIdx() {
		return parentBoardIdx;
	}
	public void setParentBoardIdx(String parentBoardIdx) {
		this.parentBoardIdx = parentBoardIdx;
	}
	public int getThread() {
		return thread;
	}
	public void setThread(int thread) {
		this.thread = thread;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getBoardKindCd() {
		return boardKindCd;
	}
	public void setBoardKindCd(String boardKindCd) {
		this.boardKindCd = boardKindCd;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(String boardCount) {
		this.boardCount = boardCount;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getTitleYn() {
		return titleYn;
	}
	public void setTitleYn(String titleYn) {
		this.titleYn = titleYn;
	}
	public String getContentYn() {
		return contentYn;
	}
	public void setContentYn(String contentYn) {
		this.contentYn = contentYn;
	}
	public String getUserNmYn() {
		return userNmYn;
	}
	public void setUserNmYn(String userNmYn) {
		this.userNmYn = userNmYn;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getToDy() {
		return toDy;
	}
	public void setToDy(String toDy) {
		this.toDy = toDy;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	public String getPreNext() {
		return preNext;
	}
	public void setPreNext(String preNext) {
		this.preNext = preNext;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBoardNoticeYn() {
		return boardNoticeYn;
	}
	public void setBoardNoticeYn(String boardNoticeYn) {
		this.boardNoticeYn = boardNoticeYn;
	}
	public String getIsUrgencyYn() {
		return isUrgencyYn;
	}
	public void setIsUrgencyYn(String isUrgencyYn) {
		this.isUrgencyYn = isUrgencyYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getAlertFrDt() {
		return alertFrDt;
	}
	public String getAlertFrDtLocale() {
		return DateUtil.convertDateLocale(alertFrDt);
	}
	public void setAlertFrDt(String alertFrDt) {
		this.alertFrDt = alertFrDt;
	}
	public String getAlertToDt() {
		return alertToDt;
	}
	public String getAlertToDtLocale() {
		return DateUtil.convertDateLocale(alertToDt);
	}
	public void setAlertToDt(String alertToDt) {
		this.alertToDt = alertToDt;
	}
	public String getUpdateAnswer() {
		return updateAnswer;
	}
	public void setUpdateAnswer(String updateAnswer) {
		this.updateAnswer = updateAnswer;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(String noticeYn) {
		this.noticeYn = noticeYn;
	}
	public String getBoardMemberCd() {
		return boardMemberCd;
	}
	public void setBoardMemberCd(String boardMemberCd) {
		this.boardMemberCd = boardMemberCd;
	}
	public String getBoardExposureCd() {
		return boardExposureCd;
	}
	public void setBoardExposureCd(String boardExposureCd) {
		this.boardExposureCd = boardExposureCd;
	}
	public String getRegDtLocale() {
		return DateUtil.convertDate(regDt,"","");
	}
	
	public String getRegDtLocaleShort() {
		return DateUtil.convertDateLocale(regDt,"yyyyMMddHHmmss","");
	}
	
	public String getRnumSub() {
		return rnumSub;
	}
	
	public void setRnumSub(String rnumSub) {
		this.rnumSub = rnumSub;
	}
	public String getAtchFileCnt() {
		return atchFileCnt;
	}
	public void setAtchFileCnt(String atchFileCnt) {
		this.atchFileCnt = atchFileCnt;
	}
	public String getAlertTypeCd() {
		return alertTypeCd;
	}
	public void setAlertTypeCd(String alertTypeCd) {
		this.alertTypeCd = alertTypeCd;
	}
	public String getBoardExposureNm() {
		return boardExposureNm;
	}
	public void setBoardExposureNm(String boardExposureNm) {
		this.boardExposureNm = boardExposureNm;
	}
	public String getAlertTypeNm() {
		return alertTypeNm;
	}
	public void setAlertTypeNm(String alertTypeNm) {
		this.alertTypeNm = alertTypeNm;
	}
	
	
}

