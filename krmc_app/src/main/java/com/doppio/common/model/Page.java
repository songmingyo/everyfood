package com.doppio.common.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

public class Page extends CommonLangCdVo {
	private static final Logger logger = LoggerFactory.getLogger(Page.class);

	private static final long serialVersionUID = -3738307250469569708L;
	
	/** 페이지 숫자 */
	private String page;
	/** 페이지당 리스트 갯수 */
	private String pageRowCount;
	/** 페이징 갯수 */
	private String pagePerCount;
	/** 페이지 전체 카운트 */
	private int totalCount;
	/** 페이지 시작 로우 넘버 */
	private int startRowNo;
	/** 페이지 끝 로우 넘버 */
	private int endRowNo;
	/** 리스트 로우 넘버 */
	private String rnum;
	/** 페이지당 리스트 갯수 GRID */
	private String rows;
	/** GRID PAGING */
	private String sidx;
	private String sord;

	private boolean paging = true;
	
	/**Dashboard 및 기타 연결페이지에서 호출여부  Link param 여부(Y/N) */
	private String linkYn = "N";


	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	public String getPageRowCount() {
		return this.pageRowCount;
	}

	public void setPageRowCount(String pageRowCount) {
		this.pageRowCount = pageRowCount;
	}
	
	public String getPagePerCount() {
		return  this.pagePerCount;
	}

	public void setPagePerCount(String pagePerCount) {
		this.pagePerCount = pagePerCount;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getStartRowNo() {
		return startRowNo;
	}
	
	public void setStartRowNo(int startRowNo) {
		this.startRowNo = startRowNo;
	}
	
	public int getEndRowNo() {
		return endRowNo;
	}
	
	public void setEndRowNo(int endRowNo) {
		this.endRowNo = endRowNo;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public int getRowsParse() {
		int rtnVal = 0;
		try{
			if(StringUtils.isEmpty(this.rows))	rtnVal = 0;
			else  rtnVal = Integer.parseInt(this.rows);
		}catch(Exception ex){
			logger.debug(ex.getMessage());
			rtnVal = 0;
		}
		return  rtnVal;
	}
	
	public int getRowsParseCust(String DefaultPageRowCount) {
		int rtnVal = 0;
		try{
			if(StringUtils.isEmpty(this.rows))	rtnVal = Integer.parseInt(DefaultPageRowCount);
			else  rtnVal = Integer.parseInt(this.rows);
		}catch(Exception ex){
			logger.debug(ex.getMessage());
			rtnVal = 0;
		}
		return  rtnVal;
	}
	
	public int getPageRowCountCust(String DefaultPageRowCount) {
		int rtnVal = 0;
		try{
			if(StringUtils.isEmpty(this.pageRowCount))	rtnVal = Integer.parseInt(DefaultPageRowCount);
			else  rtnVal = Integer.parseInt(this.pageRowCount);
		}catch(Exception ex){
			logger.debug(ex.getMessage());
			rtnVal = 0;
		}
		return  rtnVal;
	}

	public int getPagePerCountCust(String DefaultPagePerCount) {
		int rtnVal = 0;
		try{
			if(StringUtils.isEmpty(this.pagePerCount))	rtnVal = Integer.parseInt(DefaultPagePerCount);
			else  rtnVal = Integer.parseInt(this.pagePerCount);
		}catch(Exception ex){
			logger.debug(ex.getMessage());
			rtnVal = 0;
		}
		return  rtnVal;
	}

	public int getPageCust() {
		int rtnVal = 0;
		 try{
			if(StringUtils.isEmpty(this.page))	rtnVal = 1;
			else  rtnVal = Integer.parseInt(this.page);
		 }catch(Exception ex){
			 logger.debug(ex.getMessage());
			 rtnVal = 0;
		 }
		return rtnVal;
	}
	
	public String getSidx() {
		return sidx;
	}
	
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	
	public String getSord() {
		return sord;
	}
	
	public void setSord(String sord) {
		this.sord = sord;
	}
	
	public boolean isPaging() {
		return paging;
	}
	
	public void setPaging(boolean paging) {
		this.paging = paging;
	}

	public String getLinkYn() {
		return linkYn;
	}

	public void setLinkYn(String linkYn) {
		this.linkYn = linkYn;
	}
	
	

}