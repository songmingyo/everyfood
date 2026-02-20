package com.doppio.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author KIM YOUNG HAG
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
public class ExcelFileVo implements Serializable {

	private static final long serialVersionUID = 5376442012626409949L;
		
	/** 저장할 파일이름 */
	private String FileName;
	/** title map */
	private HashMap<String, String> titleMap;
	/** cell style map */
	private HashMap<String, String> dataStyleMap;
	/** Keyset */
	private String[] keyset;
	/** data list */
	private List<HashMap<String, String>> datalist;
	/** cellWidth list */
	private HashMap<String, Integer> cellWidthMap;
	
	/**엑셀파일*/
	private CommonsMultipartFile file;
	
	
	/** title map */
	private HashMap<String, String> titleMapSum;
	/** cell style map */
	private HashMap<String, String> dataStyleMapSum;
	/** Keyset */
	private String[] keysetSum;
	/** data list */
	private List<HashMap<String, String>> datalistSum;
	/** cellWidth list */
	private HashMap<String, Integer> cellWidthMapSum;
	
	public HashMap<String, String> getTitleMapSum() {
		return titleMapSum;
	}
	public void setTitleMapSum(HashMap<String, String> titleMapSum) {
		this.titleMapSum = titleMapSum;
	}
	public HashMap<String, String> getDataStyleMapSum() {
		return dataStyleMapSum;
	}
	public void setDataStyleMapSum(HashMap<String, String> dataStyleMapSum) {
		this.dataStyleMapSum = dataStyleMapSum;
	}
	public String[] getKeysetSum() {
		return keysetSum;
	}
	public void setKeysetSum(String[] keysetSum) {
		this.keysetSum = keysetSum;
	}
	public List<HashMap<String, String>> getDatalistSum() {
		return datalistSum;
	}
	public void setDatalistSum(List<HashMap<String, String>> datalistSum) {
		this.datalistSum = datalistSum;
	}
	public HashMap<String, Integer> getCellWidthMapSum() {
		return cellWidthMapSum;
	}
	public void setCellWidthMapSum(HashMap<String, Integer> cellWidthMapSum) {
		this.cellWidthMapSum = cellWidthMapSum;
	}
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public HashMap<String, String> getTitleMap() {
		return titleMap;
	}
	public void setTitleMap(HashMap<String, String> titleMap) {
		this.titleMap = titleMap;
	}
	public HashMap<String, String> getDataStyleMap() {
		return dataStyleMap;
	}
	public void setDataStyleMap(HashMap<String, String> dataStyleMap) {
		this.dataStyleMap = dataStyleMap;
	}
	public String[] getKeyset() {
		return keyset;
	}
	public void setKeyset(String[] keyset) {
		this.keyset = keyset;
	}
	public List<HashMap<String, String>> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<HashMap<String, String>> datalist) {
		this.datalist = datalist;
	}
	public HashMap<String, Integer> getCellWidthMap() {
		return cellWidthMap;
	}
	public void setCellWidthMap(HashMap<String, Integer> cellWidthMap) {
		this.cellWidthMap = cellWidthMap;
	}
	
}
