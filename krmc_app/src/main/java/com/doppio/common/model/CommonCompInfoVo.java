package com.doppio.common.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.tronic.util.DateUtil;
import org.springframework.tronic.util.StringUtil;
/**
 * @author hdh
 * @Description :
 * @Class : Result
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 * </pre>
 * @version : 1.0
 */
public class CommonCompInfoVo extends PageN implements Serializable {

	private static final long serialVersionUID = 8743755707371684250L;

	/**회사코드*/
	private String compCd			;
	private String masterId			;
	/**부모회사코드*/
	private String parentCompCd		;
	/**부모회사코드*/
	private String compNm			;
	/**사업자등록번호*/
	private String bmanNo			;
	/**법인번호*/
	private String corpnNo			;
	/**담당자 연락처 */
	private String hpNo				;
	/**담당자 이메일*/
	private String email			;
	/**사업자구분코드*/
	private String bmanDivnCd		;
	/**사업자구분명[9067]*/
	private String bmanDivnNm		;
	/**계열사유무*/
	private String affiliateYn		;
	/**대표자명*/
	private String ceoNm			;
	/**업태코드*/
	private String bizTypCd			;
	/**업태코드명*/
	private String btypNm			;
	/**업종코드*/
	private String bizItmNm			;
	/**대표브랜드*/
	private String repBrand			;
	/**설립일자*/
	private String fouDy			;
	/**대표전화*/
	private String repTelNo			;
	/**팩스전화번호*/
	private String faxTelNo			;
	/**우편번호*/
	private String zipNo			;
	/**우편주소*/
	private String zipAddr			;
	/**상세주소*/
	private String dtlAddr			;
	/**홈페이지URL*/
	private String hmpgUrl			;
	/**주요사업실적*/
	private String mainBizRsut		;
	/**본사여부*/
	private String hedoYn			;
	/**회사휴폐업코드*/
	private String compCloseCd		;
	/**취급품목내용*/
	private String dealItemContent	;
	/**회사소개*/
	private String compIntro		;
	/**승인구분코드*/
	private String aprvDivnCd       ;
	/**생성구분코드*/
	private String genDivnCd		;
	/**생성구분코드명*/
	private String genDivnNm		;
	/** 표준산업분류 대분류 */
	private String ksicLarCatCd;
	/** 표준산업분류 대분류 상세코드(표준산업분류 선택레이어에서의 중분류) */
	private String divisionCd;
	/** 표준산업분류 중분류 ; 선택한 표준산업분류 상세코드(표준산업분류 선택레이어에서의 소분류) */
	private String ksicMedCatCd;
	/**Bank account No */
	private String bankAccNo		;
	/**Bank Account Code*/
	private String bankAccCd		;
	/**Bank Beneficiary Name*/
	private String bankBeneNm		;
	/**Contract Issued by*/
	private String conIssuedBy		;
	/**Contract Issued on*/
	private String conIssuedOn		;
	/**회사명 (영문)	*/
	private String compNmEn			;
	/**우편주소 (영문) */
	private String zipAddrEn		;
	/**Contract Issued by(사업자번호발급기관 영문)*/
	private String conIssuedByEn	;
	/**tax no*/
	private String taxNo			;
	/**신분증번호/여권번호*/
	private String identityNo		;
	/**신분증,여권번호 발급기관*/
	private String identityIssuedAt	;
	/**신분증,여권번호 발급기관 영문*/
	private String identityIssuedAtEn	;
	/**신분증번호/여권번호 발급일자*/
	private String identityIssuedOn		;
	/**Power of Attorney Number*/
	private String poaNo			;
	/**Power of Attorney Number Date*/
	private String poaDy			;
	/**등록일시*/
	private String regDt			;
	/**등록자아이디*/
	private String regId			;
	/**등록자아이디*/
	private String modDt			;
	/**수정자아이디*/
	private String modId			;
	/**  담당자명 */
	private String recName			;
	/**거주지주소*/
	private String prAddr			;
	/**거주지주소*/
	private String prAddrEn			;

	private String psnInfoYn		;
	
	/** 추가 변수 --------------------- */
	/** 담당자 ID */
	private String userId			;

	/** 담당자명  */
	private String userNm			;

	/** 담당자 고유 번호  */
	private String memberCd			;

	/** 담당자 직책  */
	private String positionNm		;

	/** 승인구분코드명*/
	private String aprvDivnNm		;

	/** 표준산업분류 명*/
	private String ksicCatNm		;

	/** 담당자 직급  */
	private String classNm		;
	
	/** 담당자 전화번호  */
	private String telNo		;
	
	/** 담당자 생년월일  */
	private String brth		;
	
	/** 조회조건 변수 --------------------- */
	/** 업체명  */
	private String find_compNm;
	/** 사업자번호  */
	private String find_bmanNo;
	/** 대표자명  */
	private String find_ceoNm;
	/** 담당자명  */
	private String find_recName;
	/** 계열사유무(Y:계열사, N:협력사)  */
	private String find_affiliateYn;
	/** 본사여부(Y:본사, N:지사)  */
	private String find_hedoYn;
	
	private String find_ifYn;
	
	/*회사 등록유형(거래유형) M901*/
	private String find_tradeCompCd;

	/** 업체명  */
	private String search_compNm	;
	/** 사업자번호  */
	private String search_bmanNo	;
	/** 대표자명  */
	private String search_ceoNm		;
	/** 담당자명  */
	private String search_userNm	;
	/** 작업자아이디 */
	private String workId;
	/** 기타 업태명 */
	private String btypEtcNm;
	
	
	/**업체코드 번호 리스트 */
	private String[] compCdList;

	/**업체 필수 첨부파일 리스트*/
	/**사업자등록증*/
	private String atchBizLic;
	private String atchBizLicNm;
	/**회사소개서*/
	private String atchCompPf;
	private String atchCompPfNm;
	/**재무보고서*/
	private String atchFinRpt;
	private String atchFinRptNm;
	
	
	/**첨부파일Id*/
	private String atchFileId;
	/**첨부파일 순번*/
	private String seq;
	
	/**첨부파일명*/
	private String orgFileNm;
	
	/**파일타입명*/
	private String fileTypeNm;
	
	/**업체대표자전화번호*/
	private String ceoTelNo;
	
	private String chgYn;
	
	private String bmanNumber;
	
	/**표준산업분류정보 리스트 Vo*/
	List<CommonCompInfoVo> ksicListVo;
	
	/**업체 정회원 승인여부*/
	private String regularApprYn;
	
	/**정회원 승급대상 업체 리스트 Vo*/
	List<CommonCompInfoVo> reguCompListVo;
	
	/**업체 회사정보관리>관심분야(영역)지정 사용여부 */
	private String bidInterestFieldYn;
	
	/**공고 영역(분야)구분*/
	private String bidCriCd;
	
	/**공고 영역(분야)구분 명칭*/
	private String bidCriCdNm;
	
	/**관심입찰공고 키워드*/
	private String bidKeyWord;
	
	/**메일 알림설정[7026]*/
	private String emailAlertType;
	
	/**추정가격 최소금액*/
	private String estiPrcAmtMin;
	
	/**추정가격 최대금액*/
	private String estiPrcAmtMax;
	
	/**이메일 사용여부 */
	private String emailAlertUseYn;
	
	/**전체주소 */
	private String fullAddr;
	
	/**매핑업체갯수 */
	private String venCnt;
	
	/**인터페이스 구분(GMD , EDI) */
	private String ifCd;
	
	/**사용여부 */
	private String useYn;
	
	/**업체코드 검색 */
	private String searchMasterId;
	
	/**은행명 */
	private String bankAccNm;
	
	/**협력사 정보수정 코드 */
	private String reqUpdateCd;
	/**협력사 정보수정명 */
	private String reqUpdateNm;
	
	/**협력사 정보수정 순번 */
	private String tempSeq;
	
	private String roadAddrNo;
	
	private String roadAddrYn;
	
	private String roadPostAddr;
	private String entpPostSeq;
	private String defaultYn;
	private String branchName;
	
	/**담당자명 */
	private String entpManNm;
	/**담당자 전화번호 */
	private String entpManTel;
	/**담당자 이메일 */
	private String entpManEmail;
	
	/** 기존거래 업체 여부 */
	private String prxEntpYn;
	
	public String getPsnInfoYn() {
		return psnInfoYn;
	}
	public void setPsnInfoYn(String psnInfoYn) {
		this.psnInfoYn = psnInfoYn;
	}
	public String getFullAddr() {
		return fullAddr;
	}
	public void setFullAddr(String fullAddr) {
		this.fullAddr = fullAddr;
	}
	public String getBtypEtcNm() {
		return btypEtcNm;
	}
	public void setBtypEtcNm(String btypEtcNm) {
		this.btypEtcNm = btypEtcNm;
	}
	public String getPoaNo() {
		return poaNo;
	}
	public void setPoaNo(String poaNo) {
		this.poaNo = poaNo;
	}
	public String getPoaDy() {
		return poaDy;
	}
	public void setPoaDy(String poaDy) {
		this.poaDy = poaDy;
	}
	public String getPrAddr() {
		return prAddr;
	}
	public void setPrAddr(String prAddr) {
		this.prAddr = prAddr;
	}
	public String getPrAddrEn() {
		return prAddrEn;
	}
	public void setPrAddrEn(String prAddrEn) {
		this.prAddrEn = prAddrEn;
	}
	public String getTaxNo() {
		return taxNo;
	}
	public void setTaxNo(String taxNo) {
		this.taxNo = taxNo;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getFind_compNm() {
		return find_compNm;
	}
	public void setFind_compNm(String find_compNm) {
		this.find_compNm = find_compNm;
	}
	public String getFind_bmanNo() {
		return find_bmanNo;
	}
	public void setFind_bmanNo(String find_bmanNo) {
		this.find_bmanNo = find_bmanNo;
	}
	public String getFind_ceoNm() {
		return find_ceoNm;
	}
	public void setFind_ceoNm(String find_ceoNm) {
		this.find_ceoNm = find_ceoNm;
	}
	public String getFind_recName() {
		return find_recName;
	}
	public void setFind_recName(String find_recName) {
		this.find_recName = find_recName;
	}
	public String getFind_affiliateYn() {
		return find_affiliateYn;
	}
	public void setFind_affiliateYn(String find_affiliateYn) {
		this.find_affiliateYn = find_affiliateYn;
	}
	public String getFind_hedoYn() {
		return find_hedoYn;
	}
	public void setFind_hedoYn(String find_hedoYn) {
		this.find_hedoYn = find_hedoYn;
	}

	public String getCompCd() {
		return compCd;
	}
	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}
	public String getParentCompCd() {
		return parentCompCd;
	}
	public void setParentCompCd(String parentCompCd) {
		this.parentCompCd = parentCompCd;
	}
	public String getCompNm() {
		return compNm;
	}
	public void setCompNm(String compNm) {
		this.compNm = compNm;
	}
	public String getBmanNo() {
		return bmanNo;
	}
	public void setBmanNo(String bmanNo) {
		this.bmanNo = bmanNo;
	}
	public String getCorpnNo() {
		return corpnNo;
	}
	public void setCorpnNo(String corpnNo) {
		this.corpnNo = corpnNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBmanDivnCd() {
		return bmanDivnCd;
	}
	public void setBmanDivnCd(String bmanDivnCd) {
		this.bmanDivnCd = bmanDivnCd;
	}
	public String getAffiliateYn() {
		return affiliateYn;
	}
	public void setAffiliateYn(String affiliateYn) {
		this.affiliateYn = affiliateYn;
	}
	public String getCeoNm() {
		return ceoNm;
	}
	public void setCeoNm(String ceoNm) {
		this.ceoNm = ceoNm;
	}
	public String getBtypNm() {
		return btypNm;
	}
	public void setBtypNm(String btypNm) {
		this.btypNm = btypNm;
	}
	public String getBizItmNm() {
		return bizItmNm;
	}
	public void setBizItmNm(String bizItmNm) {
		this.bizItmNm = bizItmNm;
	}
	public String getRepBrand() {
		return repBrand;
	}
	public void setRepBrand(String repBrand) {
		this.repBrand = repBrand;
	}
	public String getFouDy() {
		return fouDy;
	}
	public void setFouDy(String fouDy) {
		this.fouDy = fouDy;
	}
	public String getRepTelNo() {
		return repTelNo;
	}
	public void setRepTelNo(String repTelNo) {
		this.repTelNo = repTelNo;
	}
	public String getFaxTelNo() {
		return faxTelNo;
	}
	public void setFaxTelNo(String faxTelNo) {
		this.faxTelNo = faxTelNo;
	}
	public String getZipNo() {
		return zipNo;
	}
	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}
	public String getZipAddr() {
		return zipAddr;
	}
	public void setZipAddr(String zipAddr) {
		this.zipAddr = zipAddr;
	}
	public String getDtlAddr() {
		return dtlAddr;
	}
	public void setDtlAddr(String dtlAddr) {
		this.dtlAddr = dtlAddr;
	}
	public String getHmpgUrl() {
		return hmpgUrl;
	}
	public void setHmpgUrl(String hmpgUrl) {
		this.hmpgUrl = hmpgUrl;
	}
	public String getMainBizRsut() {
		return mainBizRsut;
	}
	public void setMainBizRsut(String mainBizRsut) {
		this.mainBizRsut = mainBizRsut;
	}
	public String getHedoYn() {
		return hedoYn;
	}
	public void setHedoYn(String hedoYn) {
		this.hedoYn = hedoYn;
	}
	public String getCompCloseCd() {
		return compCloseCd;
	}
	public void setCompCloseCd(String compCloseCd) {
		this.compCloseCd = compCloseCd;
	}
	public String getDealItemContent() {
		return dealItemContent;
	}
	public void setDealItemContent(String dealItemContent) {
		this.dealItemContent = dealItemContent;
	}
	public String getCompIntro() {
		return compIntro;
	}
	public void setCompIntro(String compIntro) {
		this.compIntro = compIntro;
	}
	public String getAprvDivnCd() {
		return aprvDivnCd;
	}
	public void setAprvDivnCd(String aprvDivnCd) {
		this.aprvDivnCd = aprvDivnCd;
	}
	public String getGenDivnCd() {
		return genDivnCd;
	}
	public void setGenDivnCd(String genDivnCd) {
		this.genDivnCd = genDivnCd;
	}
	public String getKsicLarCatCd() {
		return ksicLarCatCd;
	}
	public void setKsicLarCatCd(String ksicLarCatCd) {
		this.ksicLarCatCd = ksicLarCatCd;
	}
	public String getKsicMedCatCd() {
		return ksicMedCatCd;
	}
	public void setKsicMedCatCd(String ksicMedCatCd) {
		this.ksicMedCatCd = ksicMedCatCd;
	}
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public String getBankAccCd() {
		return bankAccCd;
	}
	public void setBankAccCd(String bankAccCd) {
		this.bankAccCd = bankAccCd;
	}
	public String getBankBeneNm() {
		return bankBeneNm;
	}
	public void setBankBeneNm(String bankBeneNm) {
		this.bankBeneNm = bankBeneNm;
	}
	public String getConIssuedBy() {
		return conIssuedBy;
	}
	public void setConIssuedBy(String conIssuedBy) {
		this.conIssuedBy = conIssuedBy;
	}
	public String getConIssuedOn() {
		return conIssuedOn;
	}
	public void setConIssuedOn(String conIssuedOn) {
		this.conIssuedOn = conIssuedOn;
	}
	public String getCompNmEn() {
		return compNmEn;
	}
	public void setCompNmEn(String compNmEn) {
		this.compNmEn = compNmEn;
	}
	public String getZipAddrEn() {
		return zipAddrEn;
	}
	public void setZipAddrEn(String zipAddrEn) {
		this.zipAddrEn = zipAddrEn;
	}
	public String getConIssuedByEn() {
		return conIssuedByEn;
	}
	public void setConIssuedByEn(String conIssuedByEn) {
		this.conIssuedByEn = conIssuedByEn;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getIdentityIssuedAt() {
		return identityIssuedAt;
	}
	public void setIdentityIssuedAt(String identityIssuedAt) {
		this.identityIssuedAt = identityIssuedAt;
	}
	public String getIdentityIssuedAtEn() {
		return identityIssuedAtEn;
	}
	public void setIdentityIssuedAtEn(String identityIssuedAtEn) {
		this.identityIssuedAtEn = identityIssuedAtEn;
	}
	public String getIdentityIssuedOn() {
		return identityIssuedOn;
	}
	public void setIdentityIssuedOn(String identityIssuedOn) {
		this.identityIssuedOn = identityIssuedOn;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getPositionNm() {
		return positionNm;
	}
	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}
	public String getSearch_compNm() {
		return search_compNm;
	}
	public void setSearch_compNm(String search_compNm) {
		this.search_compNm = search_compNm;
	}
	public String getSearch_bmanNo() {
		return search_bmanNo;
	}
	public void setSearch_bmanNo(String search_bmanNo) {
		this.search_bmanNo = search_bmanNo;
	}
	public String getSearch_ceoNm() {
		return search_ceoNm;
	}
	public void setSearch_ceoNm(String search_ceoNm) {
		this.search_ceoNm = search_ceoNm;
	}
	public String getSearch_userNm() {
		return search_userNm;
	}
	public void setSearch_userNm(String search_userNm) {
		this.search_userNm = search_userNm;
	}
	public String getAprvDivnNm() {
		return aprvDivnNm;
	}
	public void setAprvDivnNm(String aprvDivnNm) {
		this.aprvDivnNm = aprvDivnNm;
	}
	public String getKsicCatNm() {
		return ksicCatNm;
	}
	public void setKsicCatNm(String ksicCatNm) {
		this.ksicCatNm = ksicCatNm;
	}
	public String getRegDtLocale(){
		return DateUtil.convertDate(regDt,"yyyyMMddHHmmss","");
	}
	public String getModDtLocale(){
		return DateUtil.convertDate(modDt,"yyyyMMddHHmmss","");
	}
	public String getConIssuedOnLocale() {
		return DateUtil.convertDateLocale(conIssuedOn);
	}
	public String getPoaDyLocale() {
		return DateUtil.convertDateLocale(poaDy);
	}
	public String getConIssuedOnFmt() {
		return DateUtil.convertDateLocale(conIssuedOn, "", "");
	}
	public String getIdentityIssuedOnLocale() {
		return DateUtil.convertDateLocale(identityIssuedOn);
	}
	public String getIdentityIssuedOnFmt() {
		return DateUtil.convertDateLocale(identityIssuedOn, "", "");
	}
	public String getPoaDyFmt() {
		return DateUtil.convertDateLocale(poaDy, "", "");
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String[] getCompCdList() {
		return compCdList;
	}
	public void setCompCdList(String[] compCdList) {
		this.compCdList = compCdList;
	}
	
	public String getClassNm() {
		return classNm;
	}
	
	public void setClassNm(String classNm) {
		this.classNm= classNm;
	}
	
	public String getTelNo() {
		return telNo;
	}
	
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	public String getAtchBizLic() {
		return atchBizLic;
	}
	public void setAtchBizLic(String atchBizLic) {
		this.atchBizLic = atchBizLic;
	}
	public String getAtchCompPf() {
		return atchCompPf;
	}
	public void setAtchCompPf(String atchCompPf) {
		this.atchCompPf = atchCompPf;
	}
	public String getAtchFinRpt() {
		return atchFinRpt;
	}
	public void setAtchFinRpt(String atchFinRpt) {
		this.atchFinRpt = atchFinRpt;
	}
	public String getAtchBizLicNm() {
		return atchBizLicNm;
	}
	public void setAtchBizLicNm(String atchBizLicNm) {
		this.atchBizLicNm = atchBizLicNm;
	}
	public String getAtchCompPfNm() {
		return atchCompPfNm;
	}
	public void setAtchCompPfNm(String atchCompPfNm) {
		this.atchCompPfNm = atchCompPfNm;
	}
	public String getAtchFinRptNm() {
		return atchFinRptNm;
	}
	public void setAtchFinRptNm(String atchFinRptNm) {
		this.atchFinRptNm = atchFinRptNm;
	}
	public String getBmanDivnNm() {
		return bmanDivnNm;
	}
	public void setBmanDivnNm(String bmanDivnNm) {
		this.bmanDivnNm = bmanDivnNm;
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
	public String getOrgFileNm() {
		return orgFileNm;
	}
	public void setOrgFileNm(String orgFileNm) {
		this.orgFileNm = orgFileNm;
	}
	public String getFileTypeNm() {
		return fileTypeNm;
	}
	public void setFileTypeNm(String fileTypeNm) {
		this.fileTypeNm = fileTypeNm;
	}
	public String getCeoTelNo() {
		return ceoTelNo;
	}
	public void setCeoTelNo(String ceoTelNo) {
		this.ceoTelNo = ceoTelNo;
	}
	public List<CommonCompInfoVo> getKsicListVo() {
		return ksicListVo;
	}
	public void setKsicListVo(List<CommonCompInfoVo> ksicListVo) {
		this.ksicListVo = ksicListVo;
	}
	public String getBizTypCd() {
		return bizTypCd;
	}
	public void setBizTypCd(String bizTypCd) {
		this.bizTypCd = bizTypCd;
	}
	
	public String getRegularApprYn() {
		return regularApprYn;
	}
	
	public List<CommonCompInfoVo> getReguCompListVo() {
		return reguCompListVo;
	}
	public void setReguCompListVo(List<CommonCompInfoVo> reguCompListVo) {
		this.reguCompListVo = reguCompListVo;
	}
	public void setRegularApprYn(String regularApprYn) {
		this.regularApprYn = regularApprYn;
	}
	public String getDivisionCd() {
		return divisionCd;
	}
	public void setDivisionCd(String divisionCd) {
		this.divisionCd = divisionCd;
	}
	
	public String getBidInterestFieldYn() {
		return bidInterestFieldYn;
	}
	
	public void setBidInterestFieldYn(String bidInterestFieldYn) {
		this.bidInterestFieldYn = bidInterestFieldYn;
	}
	
	public String getBidCriCd() {
		return bidCriCd;
	}
	
	public void setBidCriCd(String bidCriCd) {
		this.bidCriCd = bidCriCd;
	}
	
	public String getBidCriCdNm() {
		return bidCriCdNm;
	}
	
	public void setBidCriCdNm(String bidCriCdNm) {
		this.bidCriCdNm = bidCriCdNm;
	}
	
	public String getBidKeyWord() {
		return bidKeyWord;
	}
	
	public void setBidKeyWord(String bidKeyWord) {
		this.bidKeyWord = bidKeyWord;
	}
	
	public String getEmailAlertType(){
		return emailAlertType;
	}
	
	public void setEmailAlertType(String emailAlertType) {
		this.emailAlertType = emailAlertType;
	}
	
	public String getEstiPrcAmtMin(){
		return estiPrcAmtMin;
	}
	
	public void setEstiPrcAmtMin(String estiPrcAmtMin) {
		this.estiPrcAmtMin = estiPrcAmtMin;
	}
	
	public String getEstiPrcAmtMax(){
		return estiPrcAmtMax;
	}
	
	public void setEstiPrcAmtMax(String estiPrcAmtMax) {
		this.estiPrcAmtMax = estiPrcAmtMax;
	}
	
	public String getEmailAlertUseYn() {
		return emailAlertUseYn;
	}
	public void setEmailAlertUseYn(String emailAlertUseYn) {
		this.emailAlertUseYn = emailAlertUseYn;
	}
	public String getBrth() {
		return brth;
	}
	public void setBrth(String brth) {
		this.brth = brth;
	}
	public String getBmanNoFmt() {
		return StringUtil.bmanNoFmt(bmanNo, "");
	}
	public String getVenCnt() {
		return venCnt;
	}
	public String getIfCd() {
		return ifCd;
	}
	public void setVenCnt(String venCnt) {
		this.venCnt = venCnt;
	}
	public void setIfCd(String ifCd) {
		this.ifCd = ifCd;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSearchMasterId() {
		return searchMasterId;
	}
	public void setSearchMasterId(String searchMasterId) {
		this.searchMasterId = searchMasterId;
	}
	public String getBankAccNm() {
		return bankAccNm;
	}
	public void setBankAccNm(String bankAccNm) {
		this.bankAccNm = bankAccNm;
	}
	public String getReqUpdateCd() {
		return reqUpdateCd;
	}
	public void setReqUpdateCd(String reqUpdateCd) {
		this.reqUpdateCd = reqUpdateCd;
	}
	public String getReqUpdateNm() {
		return reqUpdateNm;
	}
	public void setReqUpdateNm(String reqUpdateNm) {
		this.reqUpdateNm = reqUpdateNm;
	}
	public String getTempSeq() {
		return tempSeq;
	}
	public void setTempSeq(String tempSeq) {
		this.tempSeq = tempSeq;
	}
	public String getFind_ifYn() {
		return find_ifYn;
	}
	public void setFind_ifYn(String find_ifYn) {
		this.find_ifYn = find_ifYn;
	}
	public String getChgYn() {
		return chgYn;
	}
	public void setChgYn(String chgYn) {
		this.chgYn = chgYn;
	}
	public String getGenDivnNm() {
		return genDivnNm;
	}
	public void setGenDivnNm(String genDivnNm) {
		this.genDivnNm = genDivnNm;
	}
	public String getBmanNumber() {
		return bmanNumber;
	}
	public void setBmanNumber(String bmanNumber) {
		this.bmanNumber = bmanNumber;
	}
	public String getRoadAddrNo() {
		return roadAddrNo;
	}
	public void setRoadAddrNo(String roadAddrNo) {
		this.roadAddrNo = roadAddrNo;
	}
	public String getRoadAddrYn() {
		return roadAddrYn;
	}
	public void setRoadAddrYn(String roadAddrYn) {
		this.roadAddrYn = roadAddrYn;
	}
	public String getRoadPostAddr() {
		return roadPostAddr;
	}
	public void setRoadPostAddr(String roadPostAddr) {
		this.roadPostAddr = roadPostAddr;
	}
	public String getEntpPostSeq() {
		return entpPostSeq;
	}
	public void setEntpPostSeq(String entpPostSeq) {
		this.entpPostSeq = entpPostSeq;
	}
	public String getDefaultYn() {
		return defaultYn;
	}
	public void setDefaultYn(String defaultYn) {
		this.defaultYn = defaultYn;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getEntpManNm() {
		return entpManNm;
	}
	public void setEntpManNm(String entpManNm) {
		this.entpManNm = entpManNm;
	}
	public String getEntpManTel() {
		return entpManTel;
	}
	public void setEntpManTel(String entpManTel) {
		this.entpManTel = entpManTel;
	}
	public String getEntpManEmail() {
		return entpManEmail;
	}
	public void setEntpManEmail(String entpManEmail) {
		this.entpManEmail = entpManEmail;
	}
	public String getPrxEntpYn() {
		return prxEntpYn;
	}
	public void setPrxEntpYn(String prxEntpYn) {
		this.prxEntpYn = prxEntpYn;
	}
	public String getFind_tradeCompCd() {
		return find_tradeCompCd;
	}
	public void setFind_tradeCompCd(String find_tradeCompCd) {
		this.find_tradeCompCd = find_tradeCompCd;
	}
	
	
	
	
}
