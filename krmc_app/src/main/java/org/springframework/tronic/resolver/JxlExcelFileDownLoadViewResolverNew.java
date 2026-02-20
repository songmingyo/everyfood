package org.springframework.tronic.resolver;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.jdom2.output.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.tronic.util.StringUtil;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.doppio.common.model.ExcelFileVo;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WriteException;



public class JxlExcelFileDownLoadViewResolverNew extends AbstractXlsxView  {

	@Value("#{config['excel.Encoding']}")			// charSet
	public String charSet;
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final Logger logger = LoggerFactory.getLogger(JxlExcelFileDownLoadViewResolverNew.class);



	/**
	 * 엑셀만들기
	 */
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ExcelFileVo excelFileVO = (ExcelFileVo) model.get("excelFile");
		//column title
		HashMap<String, String> title = excelFileVO.getTitleMap();
		//column style
		HashMap<String, String> dataStyle = excelFileVO.getDataStyleMap();
		//HashMap Keyset
		String[] keyset = excelFileVO.getKeyset();
		//datalist
		List<HashMap<String, String>> dataList = excelFileVO.getDatalist();
		//cell width
		HashMap<String, Integer> cellWidth = excelFileVO.getCellWidthMap();

		//HashMap<String, WritableCellFormat> dataStyleMap = setCellStyleList(workbook, dataStyle, keyset);
		
		HashMap<String, String> dataStyleMap = setCellStyleLists(workbook, dataStyle, keyset);

		String sheetName = excelFileVO.getFileName();
		SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new java.util.Date());
		String fileName = sheetName + "_" + date + ".xlsx";

		double dataListSize = Math.ceil((double)dataList.size()/60000); // sheet 별로 60000건씩 출력
		int dataSize = 0;	//data.size();
		
		CellStyle style = null;
		CellStyle styl2 = null;
		CellStyle titleStyle = workbook.createCellStyle();

		
		DataFormat dataFormat ;
				
		//filename 설정
		setFileNameToResponse(request, response, fileName);
		
		Row row = null;
		
		//결과가 없을때 column title만 출력
		if(dataList.size() == 0){
			Sheet sheet = workbook.createSheet(sheetName+"(1)");
			
			row = sheet.createRow(0);

			//title align
			titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			
			//title border
			titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
			titleStyle.setBorderRight(CellStyle.BORDER_THIN);
			titleStyle.setBorderTop(CellStyle.BORDER_THIN);
			
			//title color
			titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			for(int col = 0; col < title.size(); col++) {
				row.createCell(col).setCellValue(title.get(keyset[col]));
				row.getCell(col).setCellStyle(titleStyle);
			}
		}

		//60000 : sheet 별로 60000건씩 출력
		for(int sheetNum = 0; sheetNum < dataListSize; sheetNum++){
			Sheet sheet = workbook.createSheet(sheetName+"("+(sheetNum+1)+")");
			
			row = sheet.createRow(0);
			
			//title align
			titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
			//title border
			titleStyle.setBorderBottom(CellStyle.BORDER_THIN);
			titleStyle.setBorderLeft(CellStyle.BORDER_THIN);
			titleStyle.setBorderRight(CellStyle.BORDER_THIN);
			titleStyle.setBorderTop(CellStyle.BORDER_THIN);
			
			//title color
			titleStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
			titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			for(int col = 0; col < title.size(); col++) {
				row.createCell(col).setCellValue(title.get(keyset[col]));
				row.getCell(col).setCellStyle(titleStyle);
			}
			// data set
			for(int rownum = 1; rownum <= 60000+1; rownum++) {
				HashMap<String, String> data = dataList.get(sheetNum*60000+rownum-1);
				dataSize = data.size();
				row = sheet.createRow(rownum);
				row.createCell(0).setCellValue(String.valueOf(rownum));
				
				/*[workbook CellStyle은 첫번째 행만으로 적용  (Row 별로 인스턴스 생성하면 64000 개 이상 일경우 오류 )]*/
				
				if( style == null) {
					style = workbook.createCellStyle(); //스타일 초기화
					
	
					style.setAlignment(CellStyle.ALIGN_CENTER);
					style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
					style.setBorderBottom(CellStyle.BORDER_THIN);
					style.setBorderLeft(CellStyle.BORDER_THIN);
					style.setBorderRight(CellStyle.BORDER_THIN);
					style.setBorderTop(CellStyle.BORDER_THIN);
					
					//column align
					if("LEFT".equals(dataStyle.get(keyset[0]))) {
						style.setAlignment(CellStyle.ALIGN_LEFT);
					} else if("CENTER".equals(dataStyle.get(keyset[0]))) {
						style.setAlignment(CellStyle.ALIGN_CENTER);
					} else if("RIGHT".equals(dataStyle.get(keyset[0]))) {
						style.setAlignment(CellStyle.ALIGN_RIGHT);
					} else {
						style.setAlignment(CellStyle.ALIGN_GENERAL);
					}
				}
			
				
				/*-------------------------------------------------------------------------------------*/
				
				
				row.getCell(0).setCellStyle(style);
				
				dataFormat = workbook.createDataFormat();
				Cell cell;
				String df = "";
				for(int col = 1; col < dataSize + 1; col++) {
					Object cellData = data.get(keyset[col]);
					if(cellData == null) cellData = " ";
					
					cell = row.createCell(col);
					
					df = dataStyleMap.get(keyset[col]).trim();
					styl2 = workbook.createCellStyle(); 
					
					// Double:#,##0.00  Integer : #,##0
					if("0".equals(df) || "#,##0".equals(df)){
												
						styl2.setDataFormat(dataFormat.getFormat("#,##0"));
						styl2.setAlignment(CellStyle.ALIGN_RIGHT);
						
						if(isInteger(cellData.toString()))  cell.setCellValue(Long.parseLong(cellData.toString()));
						else cell.setCellValue(0);
						
	 				} else if("0.00".equals(df) || "#,##0.00".equals(df)){
	 					
	 					styl2.setDataFormat(dataFormat.getFormat("#,##0.00"));
	 					styl2.setAlignment(CellStyle.ALIGN_RIGHT);
	 					
	 					if(isDouble(cellData.toString())) {
	 						cell.setCellValue(Double.valueOf(cellData.toString()));
	 					} else if (isInteger(cellData.toString())) {
	 						cell.setCellValue(Long.parseLong(cellData.toString()));
	 					} else {
	 						cell.setCellValue(0);
	 					}
	 					  
	 				} else {
	 					styl2.setAlignment(CellStyle.ALIGN_CENTER);
	 					cell.setCellValue(cellData.toString());
	 					
	 				}
					
//					style = workbook.createCellStyle();
//					style.setDataFormat(dataFormat.getFormat(dataStyleMap.get(keyset[col])));
					cell.setCellStyle(styl2);
					
				}

				if(sheetNum*60000+rownum==dataList.size())break;
			}

			//column size 조정
			for(int col = 1; col < keyset.length; col++){ 
				sheet.setColumnWidth(col,cellWidth.get(keyset[col])*256);
				//sheet.autoSizeColumn(col, false);	//col auto width
			}
			 
		}
	}
	
	
	 public static boolean isInteger(String strValue) {
	    try {
	    Long.parseLong(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	 
	 public static boolean isDouble(String strValue) {
		    try {
		    	Double.parseDouble(strValue);
		      return true;
		    } catch (NumberFormatException ex) {
		      return false;
		    }
		}
	 
	
	
	/**
	 * @Method : getBrowser
	 * @Description : 브라우저 구분 얻기
	 * @param request
	 * @return String
	 */
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
			return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}

	/**
	 * @Method : setFileNameToResponse
	 * @Description : 엑셀파일명
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws Exception
	 */
	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
		String browser = getBrowser(request);

		String dispositionPrefix = "attachment; fileName=";
		String encodedFilename = null;

		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < fileName.length(); i++) {
				char c = fileName.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			//throw new RuntimeException("Not supported browser");
			throw new IOException("Not supported browser");
		}

		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
	public HashMap<String, String> setCellStyleLists(Workbook workbook, HashMap<String, String> dataStyle, String[] keyset) throws WriteException{
		 
 		HashMap<String, String> cellStyleMap = new java.util.HashMap<String, String>();
 		String style[] = null;
 		double size = dataStyle.size();
 
 		for(int i = 0; i < size; i++){
 
 			style = dataStyle.get(keyset[i]).split("/");
 		
 
 			if(style.length == 2 && StringUtil.isNotEmpty(style[1])  ) 	{
 				 cellStyleMap.put((String) keyset[i], style[1].trim());
 				 
 				 logger.info(" SET : "+style[1].trim());
 			}else {
 				cellStyleMap.put((String) keyset[i], "");
 			}
 				
 		}
 
 		return cellStyleMap;
 	}
	
	
	

	
	/**
	 * 셀스타일
	 * @param workbook
	 * @param dataStyle
	 * @param keyset
	 * @return
	 * @throws WriteException
	 */
 	public HashMap<String, WritableCellFormat> setCellStyleList(Workbook workbook, HashMap<String, String> dataStyle, String[] keyset) throws WriteException{
 
 		HashMap<String, WritableCellFormat> cellStyleMap = new java.util.HashMap<String, WritableCellFormat>();
 		String style[] = null;
 		double size = dataStyle.size();
 
 		for(int i = 0; i < size; i++){
 
 			style = dataStyle.get(keyset[i]).split("/");
 			WritableCellFormat cellStyle;
 			NumberFormat numberFormat = null;
 
 			logger.info("setCellStyleList =================================");
 			
 			for(int j = 0; j < style.length; j++){
 				
 				logger.info("style.name : "+style[j].trim());
 				
 				if("0".equals(style[j].trim())){
 					numberFormat = new NumberFormat("0");
 				} else if("0.00".equals(style[j].trim())){
 					numberFormat = new NumberFormat("0.00");
 				} else if("#,##0".equals(style[j].trim())){
 					numberFormat = new NumberFormat("#,##0");
 				} else if("#,##0.00".equals(style[j].trim())){
 					numberFormat = new NumberFormat("#,##0.00");
 				} else if("0%".equals(style[j].trim())){
 					numberFormat = new NumberFormat("0%");
 				} else if("0.00%".equals(style[j].trim())){
 					numberFormat = new NumberFormat("0.00%");
 				}
 			}
 
 			if(numberFormat != null){
 				cellStyle = new WritableCellFormat(numberFormat);
 				cellStyle.setLocked(true);
 			} else {
 				cellStyle = new WritableCellFormat();
 				cellStyle.setLocked(false);
 			}
 
 			for(int j = 0; j < style.length; j++){
 
 				cellStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
 
 				//  align
 				if("LEFT".equals(style[j].trim())){
 					cellStyle.setAlignment(Alignment.LEFT);
 				} else if("CENTER".equals(style[j].trim())){
 					cellStyle.setAlignment(Alignment.CENTRE);
 				} else if("RIGHT".equals(style[j].trim())){
 					cellStyle.setAlignment(Alignment.RIGHT);
 				}
 
 				//  cell backgroundcolor
 				if("BG_AQUA".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.AQUA);
 				} else if("BG_BLACK".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.BLACK);
 				} else if("BG_BLUE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.BLUE);
 				} else if("BG_BLUE_GREY".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.BLUE_GREY);
 				} else if("BG_BRIGHT_GREEN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.BRIGHT_GREEN);
 				} else if("BG_BROWN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.BROWN);
 				} else if("BG_CORAL".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.CORAL);
 				} else if("BG_DARK_BLUE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.DARK_BLUE);
 				} else if("BG_DARK_GREEN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.DARK_GREEN);
 				} else if("BG_DARK_RED".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.DARK_RED);
 				} else if("BG_DARK_TEAL".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.DARK_TEAL);
 				} else if("BG_DARK_YELLOW".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.DARK_YELLOW);
 				} else if("BG_GOLD".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.GOLD);
 				} else if("BG_GREEN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.GREEN);
 				} else if("BG_GREY_25_PERCENT".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.GREY_25_PERCENT);
 				} else if("BG_GREY_40_PERCENT".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.GREY_40_PERCENT);
 				} else if("BG_GREY_50_PERCENT".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.GREY_50_PERCENT);
 				} else if("BG_GREY_80_PERCENT".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.GREY_80_PERCENT);
 				} else if("BG_INDIGO".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.INDIGO);
 				} else if("BG_LAVENDER".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.LAVENDER);
 				} else if("BG_LIGHT_BLUE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.LIGHT_BLUE);
 				} else if("BG_LIGHT_GREEN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.LIGHT_GREEN);
 				} else if("BG_LIGHT_ORANGE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.LIGHT_ORANGE);
 				} else if("BG_LIGHT_TURQUOISE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.LIGHT_TURQUOISE);
 				} else if("BG_LIME".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.LIME);
 				} else if("BG_OLIVE_GREEN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.OLIVE_GREEN);
 				} else if("BG_ORANGE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.ORANGE);
 				} else if("BG_PALE_BLUE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.PALE_BLUE);
 				} else if("BG_PINK".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.PINK);
 				} else if("BG_PLUM".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.PLUM);
 				} else if("BG_RED".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.RED);
 				} else if("BG_ROSE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.ROSE);
 				} else if("BG_SEA_GREEN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.SEA_GREEN);
 				} else if("BG_SKY_BLUE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.SKY_BLUE);
 				} else if("BG_TAN".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.TAN);
 				} else if("BG_TEAL".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.TEAL);
 				} else if("BG_TURQUOISE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.TURQUOISE);
 				} else if("BG_VIOLET".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.VIOLET);
 				} else if("BG_WHITE".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.WHITE);
 				} else if("BG_YELLOW".equals(style[j].trim())){
 					cellStyle.setBackground(Colour.YELLOW);
 				}
 			}
 
 			cellStyleMap.put((String) keyset[i], cellStyle);
 		}
 
 		return cellStyleMap;
 	}
	
	

}