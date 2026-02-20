package com.doppio.common.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class JcoVo implements Serializable {

	private static final long serialVersionUID = 3452469544031503477L;
	
	private String repositoryNm;							// SAP REPOSITORY 명
	private String functionNm; 								// SAP Function 명
	
	private Map<String, String> importParamMap;				// import 파라미터
	
	private String importTableNm; 							// import table 명 
	private List<Map<String, String>> importTableListMap;	// import table 파라미터
	
	private String structureNm; 							// import structure 명
	private Map<String, String> structureParamMap;			// import structure 파라미터
	
	
	public String getRepositoryNm() {
		return repositoryNm;
	}
	public void setRepositoryNm(String repositoryNm) {
		this.repositoryNm = repositoryNm;
	}
	public String getFunctionNm() {
		return functionNm;
	}
	public void setFunctionNm(String functionNm) {
		this.functionNm = functionNm;
	}
	public Map<String, String> getImportParamMap() {
		return importParamMap;
	}
	public void setImportParamMap(Map<String, String> importParamMap) {
		this.importParamMap = importParamMap;
	}
	public String getImportTableNm() {
		return importTableNm;
	}
	public void setImportTableNm(String importTableNm) {
		this.importTableNm = importTableNm;
	}
	public List<Map<String, String>> getImportTableListMap() {
		return importTableListMap;
	}
	public void setImportTableListMap(List<Map<String, String>> importTableListMap) {
		this.importTableListMap = importTableListMap;
	}
	public String getStructureNm() {
		return structureNm;
	}
	public void setStructureNm(String structureNm) {
		this.structureNm = structureNm;
	}
	public Map<String, String> getStructureParamMap() {
		return structureParamMap;
	}
	public void setStructureParamMap(Map<String, String> structureParamMap) {
		this.structureParamMap = structureParamMap;
	}
	
}