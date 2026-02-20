package com.doppio.common.model;

import java.io.Serializable;

public class CommonMessageVo implements Serializable {
/*003*/
private static final long serialVersionUID = 2463386006995881200L;
	
	/* 고객번호 */
	private String custNo         = "";
	/* 고객번호 */
	private String custNos[];
	
	/* 고객명 */
	private String custNm         = "";
	/* sms-ems 플래그 */
	private String channelFg      = "";
	/* 대상구분(파트너/점주) */
	private String sendTarget     = "";
	/* 점포 */
	private String strCd          = "";
	/* 로그인관리자번호 */
	private String sendEmpNo      = "";
	/* 대상순번 */
	private String jobCd          = "";
	/* 메세지 내용 */
	private String content        = "";
	/* 메세지 제목 */
	private String title          = "";
	/* job_seq */
	private String jobSeq         = "";
	/* send_seq */
	private String sendSeq        = "";
	/* ex_seq */
	private String exSeq          = "";
	/* 반송전화번호 */
	private String returnPhone    = "";
	/* 반송 메일주소 */
	private String returnMail     = "";
	/* 전화번호 */
	private String hpNo           = "";
	/* 이메일 */
	private String email          = "";
	/* sms수신여부 */
	private String smsRecvFg      = "";
	/* ems수신여부 */
	private String emailRecvFg    = "";
	/* 발송여부 */
	private String sendFg    	  = "";
	/* 강좌코드 */
	private String clsCd    	  = "";
	/*메세지전송구분*/
	private String msgSendGbn 	  = "";
	/*메시지톡 템플릿 코드 */
	private String tempCd 		  = "";
	/*biztalk 전송코드 1000:정상발송*/
	private String reportCode 	  = "";
	/*biztalk 전송코드 1000:정상발송*/
	private String reportCodeName = "";
	/*biztalk 전송 정상:0/오류:1 코드 */
	private String talkSendFg 	  = "";
	
	/* 생성 리턴값(sms대체문자에서 사용) */
	private String jobSeqRtn      = "";
	private String sendSeqRtn	  = "";
	
	/*알림톡 내부식별순번(SMS대체문자 발송순번[SEND_SEQ])*/
	private String inSeq			 ="";
	
	private String workId			="";
	
	
	private String ciSmsRecv		;
	private String ciEmailRecv  	;
	
	
	private String sendSeqs[] ;
	
	
	public String getClsCd() {
		return clsCd;
	}
	public void setClsCd(String clsCd) {
		this.clsCd = clsCd;
	}
	public String getSendFg() {
		return sendFg;
	}
	public void setSendFg(String sendFg) {
		this.sendFg = sendFg;
	}
	public String getSmsRecvFg() {
		return smsRecvFg;
	}
	public void setSmsRecvFg(String smsRecvFg) {
		this.smsRecvFg = smsRecvFg;
	}
	public String getEmailRecvFg() {
		return emailRecvFg;
	}
	public void setEmailRecvFg(String emailRecvFg) {
		this.emailRecvFg = emailRecvFg;
	}
	public String getReturnPhone() {
		return returnPhone;
	}
	public void setReturnPhone(String returnPhone) {
		this.returnPhone = returnPhone;
	}
	public String getReturnMail() {
		return returnMail;
	}
	public void setReturnMail(String returnMail) {
		this.returnMail = returnMail;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobSeq() {
		return jobSeq;
	}
	public void setJobSeq(String jobSeq) {
		this.jobSeq = jobSeq;
	}
	public String getSendSeq() {
		return sendSeq;
	}
	public void setSendSeq(String sendSeq) {
		this.sendSeq = sendSeq;
	}
	public String getExSeq() {
		return exSeq;
	}
	public void setExSeq(String exSeq) {
		this.exSeq = exSeq;
	}
	public String getCustNm() {
		return custNm;
	}
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getChannelFg() {
		return channelFg;
	}
	public void setChannelFg(String channelFg) {
		this.channelFg = channelFg;
	}
	public String getSendTarget() {
		return sendTarget;
	}
	public void setSendTarget(String sendTarget) {
		this.sendTarget = sendTarget;
	}
	public String getStrCd() {
		return strCd;
	}
	public void setStrCd(String strCd) {
		this.strCd = strCd;
	}
	public String getSendEmpNo() {
		return sendEmpNo;
	}
	public void setSendEmpNo(String sendEmpNo) {
		this.sendEmpNo = sendEmpNo;
	}
	public String getJobCd() {
		return jobCd;
	}
	public void setJobCd(String jobCd) {
		this.jobCd = jobCd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMsgSendGbn() {
		return msgSendGbn;
	}
	
	public void setMsgSendGbn(String msgSendGbn) {
		this.msgSendGbn = msgSendGbn;
	}
	
	public String getTempCd() {
		return tempCd;
	}
	public void setTempCd(String tempCd) {
		this.tempCd = tempCd;
	}
	
	@Override
	public String toString() {
		return "PmcmMessage0003VO [custNo=" + custNo + ", custNm=" + custNm
				+ ", channelFg=" + channelFg + ", sendTarget=" + sendTarget
				+ ", strCd=" + strCd + ", sendEmpNo=" + sendEmpNo + ", jobCd="
				+ jobCd + ", content=" + content + ", title=" + title
				+ ", jobSeq=" + jobSeq + ", sendSeq=" + sendSeq + ", exSeq="
				+ exSeq + ", returnPhone=" + returnPhone + ", returnMail="
				+ returnMail + ", hpNo=" + hpNo + ", email=" + email + "]";
	}
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getTalkSendFg() {
		return talkSendFg;
	}
	public void setTalkSendFg(String talkSendFg) {
		this.talkSendFg = talkSendFg;
	}
	public String getReportCodeName() {
		return reportCodeName;
	}
	public void setReportCodeName(String reportCodeName) {
		this.reportCodeName = reportCodeName;
	}
	public String[] getSendSeqs() {
		return sendSeqs;
	}
	public void setSendSeqs(String[] sendSeqs) {
		this.sendSeqs = sendSeqs;
	}
	public String getJobSeqRtn() {
		return jobSeqRtn;
	}
	public void setJobSeqRtn(String jobSeqRtn) {
		this.jobSeqRtn = jobSeqRtn;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getSendSeqRtn() {
		return sendSeqRtn;
	}
	public void setSendSeqRtn(String sendSeqRtn) {
		this.sendSeqRtn = sendSeqRtn;
	}
	public String getInSeq() {
		return inSeq;
	}
	public void setInSeq(String inSeq) {
		this.inSeq = inSeq;
	}
	public String getCiSmsRecv() {
		return ciSmsRecv;
	}
	public void setCiSmsRecv(String ciSmsRecv) {
		this.ciSmsRecv = ciSmsRecv;
	}
	public String getCiEmailRecv() {
		return ciEmailRecv;
	}
	public void setCiEmailRecv(String ciEmailRecv) {
		this.ciEmailRecv = ciEmailRecv;
	}
	public String[] getCustNos() {
		return custNos;
	}
	public void setCustNos(String[] custNos) {
		this.custNos = custNos;
	}
	
	
	
	
	
	
	
	
}
