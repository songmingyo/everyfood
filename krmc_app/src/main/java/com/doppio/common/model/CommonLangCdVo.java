package com.doppio.common.model;

import java.io.Serializable;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class CommonLangCdVo implements Serializable {

	private static final long serialVersionUID = 6997275208463886605L;

	Locale locale = LocaleContextHolder.getLocale();

	private String langCd = locale.getLanguage().toUpperCase();

	public void setLangCd(String langCd) {
		this.langCd = langCd;
	}

	public String getLangCd() {
		return langCd;
	}
	
}