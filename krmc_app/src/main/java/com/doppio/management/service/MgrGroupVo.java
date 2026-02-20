package com.doppio.management.service;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.doppio.common.model.Page;

/**
 * @author dada
 * @Description :그룹관리 vo
 * @Class : MgrGroupVo
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *
 * </pre>
 * @version : 1.0
 */
public class MgrGroupVo extends Page  implements Serializable {


	private static final long serialVersionUID = 3636290277045031085L;

	/**그룹아이디*/
	private String groupId    ;
	/**insert/update 그룹아이디조회용*/
	private String fintGroupId;
	/**그룹명칭*/
	private String groupNm    ;
	/**그룹확장명칭*/
	private String groupSubNm    ;
	/**그룹부가설명*/
	private String groupDesc  ;
	/**그룹구분코드[CODE 9201 B:시스템,F:내부사용자, V:외부사용자,C:공통] */
	private String groupFg    ;
	/**그룹구분코드명칭*/
	private String groupFgNm   ;
	/**사용자 권한유무*/
	private String userAuth   ;
	/**사용자 여부*/
	private String useYn      ;
	/**시스템 빌트인 여부*/
	private String sysYn      ;
	/**최종변경일자*/
	private String modDt      ;
	/**작업자아이디*/
	private String workId     ;
	/**작업구분(I:신규등록, U:수정*/
	private String saveMode	  ;
	





	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getFintGroupId() {
		return fintGroupId;
	}
	public void setFintGroupId(String fintGroupId) {
		this.fintGroupId = fintGroupId;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public String getGroupSubNm() {
		return groupSubNm;
	}
	public void setGroupSubNm(String groupSubNm) {
		this.groupSubNm = groupSubNm;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public String getGroupFg() {
		return groupFg;
	}
	public void setGroupFg(String groupFg) {
		this.groupFg = groupFg;
	}
	
	public String getGroupFgNm() {
		return groupFgNm;
	}
	public void setGroupFgNm(String groupFgNm) {
		this.groupFgNm = groupFgNm;
	}
	public String getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(String userAuth) {
		this.userAuth = userAuth;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getSysYn() {
		return sysYn;
	}
	public void setSysYn(String sysYn) {
		this.sysYn = sysYn;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getSaveMode() {
		return saveMode;
	}
	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}

	public String getGroupLocaleNm(){
		Locale locale 	= LocaleContextHolder.getLocale();
		String language = locale.getLanguage();

		if("ko".equals(language) || "ko_KR".equals(language)){
			return this.groupNm;
		} else return this.groupSubNm;
	}


}
