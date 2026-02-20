package com.doppio.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.doppio.common.service.ExcelFileManagerService;


@Service("excelFileManagerService")
public class ExcelFileManagerServiceImpl implements ExcelFileManagerService {

	private static final Logger logger = LoggerFactory.getLogger(ExcelFileManagerServiceImpl.class);
	
	@Value("#{config['File.Sys.Path']}")
	public String excelFolder;
	
	/**
	 * excel data 생성
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "null" })
	public List<HashMap<String, Object>> excelUploadDataList(CommonsMultipartFile mFile) throws Exception{
		List<HashMap<String, Object>> list = null;
		HashMap hData = null;
		String value = null;			// 엑셀 데이타
		
		String fileName = mFile.getOriginalFilename();
		
		if(fileName.toUpperCase().indexOf(".XLSX") > -1) {
			XSSFWorkbook workbookXss = excelUploadFilesXss(mFile);
			if(workbookXss == null) {
				throw new NullPointerException();
			}
			XSSFSheet sheetXss = workbookXss.getSheetAt(0);
			
			int cells = sheetXss.getRow(0).getLastCellNum();	// cell 개수 : 업로드할 셀 개수 세팅
		    int rows = sheetXss.getPhysicalNumberOfRows();		//row 개수  
		        
		    //--------잘못된  엑셀 형식 체크------------------------------// 
		    Row rowCheck = sheetXss.getRow(0);
		    if(rowCheck == null || rowCheck.getCell((short)0)==null) return null;	// 엑셀 값이 null인 경우
		    //--------------------------------------------------------//
		    
		    list = new ArrayList<HashMap<String,Object>>();
		    // rows 개수 만큼 반복
		    for(int i=1;i<rows;i++){
		    	Row row = sheetXss.getRow(i);
		    	hData = new HashMap<String, Object>();
		    	// cell 개수 만큼 반복 
	    		for (int c = 0; c < cells; c++) {
	    			value = cellValueXss(row,c);
					hData.put(("COL_"+c), value.replace("'", ""));
				}// end for(cell 개수 만큼 반복 )
	    		
	    		list.add(hData);
			}
		}
		else
		{
			HSSFWorkbook workbookHss = excelUploadFilesHss(mFile);
			if(workbookHss == null) {
				throw new NullPointerException();
			}
			HSSFSheet sheetHss 		 = workbookHss.getSheetAt(0);
			
			int cells = sheetHss.getRow(0).getLastCellNum();	// cell 개수 : 업로드할 셀 개수 세팅
		    int rows = sheetHss.getPhysicalNumberOfRows();		//row 개수  
		        
		    //--------잘못된  엑셀 형식 체크------------------------------// 
		    Row rowCheck = sheetHss.getRow(0);
		    if(rowCheck == null || rowCheck.getCell((short)0)==null) return null;	// 엑셀 값이 null인 경우
		    //--------------------------------------------------------//
		  
		    list = new ArrayList<HashMap<String,Object>>();
		    // rows 개수 만큼 반복
		    for(int i=1;i<rows;i++){
		    	Row row = sheetHss.getRow(i);
		        hData = new HashMap<String, Object>();
		        	
		        // cell 개수 만큼 반복 
		        if(row!= null) {
		    		for (int c = 0; c < cells; c++) {
		    			value = cellValueHss(row,c);
						hData.put("COL_"+c, value.replace("'", ""));
					}// end for(cell 개수 만큼 반복 )
		    		
		    		list.add(hData);
		        }
			}
		}
		
		
		return list;
	}
	
	
	
	private XSSFWorkbook excelUploadFilesXss(CommonsMultipartFile mFile) throws Exception{
		
			String filePath = null;			// 파일 저장 경로
			String fileName = null;			// 파일명
			File saveFile = null;			// 엑셀 파일
			File saveFolder= null;			// 엑셀 데이터 저장 경로
			FileInputStream file = null;	
			FileOutputStream outputStream = null;
			XSSFWorkbook workbookXss    = null;	// 엑셀 테이터 xss (XLSX)
			
			
			try{
				
				//엑셀 임시정장 결로
				String excelTempFolder=excelFolder+"excel_temp"+File.separator;
				fileName = mFile.getOriginalFilename();
				// 외부 입력받은 값을 경로순회 문자열(./\)을 제거하고 사용해야한다.
				if (fileName != null) {fileName = fileName.replaceAll("\\.", "").replaceAll("/", "").replaceAll("\\\\", "");}
				
				filePath =excelTempFolder+fileName;
				saveFile = new File(filePath);
				
				// 억셀 임시정장 폴더가 없을 경우 생성한다.
				saveFolder = new File(excelTempFolder);
				if(!saveFolder.exists()){ 
					saveFolder.mkdirs(); 
				}
			
				outputStream = new FileOutputStream(new File(filePath)); 
				outputStream.write(mFile.getFileItem().get());
				file = new FileInputStream(new File(filePath));
				
				
				workbookXss = new XSSFWorkbook(file);
				
			   
			}catch (IOException | NullPointerException e) {
				if(saveFile != null && saveFile.exists()) {
					saveFile.delete();
				}
				return workbookXss;
			}
			finally{
				if (outputStream  != null) { try {outputStream.close();}  catch (IOException ignore) {}}
				if (file != null) { try {file.close();} catch (IOException ignore) {}}
			}
		    return workbookXss;
	}
	
	
	private HSSFWorkbook excelUploadFilesHss(CommonsMultipartFile mFile) throws Exception{
		
		String filePath = null;			// 파일 저장 경로
		String fileName = null;			// 파일명
		File saveFile = null;			// 엑셀 파일
		File saveFolder= null;			// 엑셀 데이터 저장 경로
		FileInputStream file = null;	
		FileOutputStream outputStream = null;
		HSSFWorkbook workBookHss 	= null;	// 엑셀 테이터 hss (XLS)
		try{
			
			//엑셀 임시정장 결로
			String excelTempFolder=excelFolder+"excel_temp"+File.separator;
			fileName = mFile.getOriginalFilename();
			// 외부 입력받은 값을 경로순회 문자열(./\)을 제거하고 사용해야한다.
			if (fileName != null) {fileName = fileName.replaceAll("\\.", "").replaceAll("/", "").replaceAll("\\\\", "");}
			
			filePath =excelTempFolder+fileName;
			saveFile = new File(filePath);
			
			// 억셀 임시정장 폴더가 없을 경우 생성한다.
			saveFolder = new File(excelTempFolder);
			if(!saveFolder.exists()){ 
				saveFolder.mkdirs(); 
			}
		
			outputStream = new FileOutputStream(new File(filePath)); 
			outputStream.write(mFile.getFileItem().get());
			file = new FileInputStream(new File(filePath));
			
			workBookHss = new HSSFWorkbook(file);
			file.close();
			outputStream.close();
		}
		catch (IOException e) {
			if (saveFile.exists()) {
				fileDelete(saveFile);
			}
		} finally {
			if (file != null) {try {file.close();} catch (Exception e) {logger.error(e.getMessage());}}
			if (outputStream != null) {try {outputStream.close();} catch (Exception e) {logger.error(e.getMessage());}}}
	    return workBookHss;
	}
	
	private synchronized void fileDelete(File saveFile) {
		saveFile.delete();
	}	
	
	private String   cellValueXss(Row row, int c) throws Exception{
		String value = "";
		if(row!= null && row.getCell(c) != null) {
			switch (row.getCell(c).getCellType()) {
			case XSSFCell.CELL_TYPE_FORMULA:
				value = row.getCell(c).getCellFormula();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				value = "" + Long.toString(Long.parseLong(String.valueOf(Math.round(row.getCell(c).getNumericCellValue()))));
				break;
			case XSSFCell.CELL_TYPE_STRING:
				value = "" + row.getCell(c).getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				value = "" + row.getCell(c).getErrorCellValue();
				break;
				default:
			}
		}
		return value;
	}
	
	
	private String   cellValueHss(Row row, int c) throws Exception{
		String value = "";
		
		switch (row.getCell(c).getCellType()) {
		case HSSFCell.CELL_TYPE_FORMULA:
			value = row.getCell(c).getCellFormula();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			value = "" + Long.toString(Long.parseLong(String.valueOf(Math.round(row.getCell(c).getNumericCellValue()))));
			break;
		case HSSFCell.CELL_TYPE_STRING:
			value = "" + row.getCell(c).getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			value = "";
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			value = "" + row.getCell(c).getErrorCellValue();
			break;
		default:
		}
		return value;
	}
    
}
