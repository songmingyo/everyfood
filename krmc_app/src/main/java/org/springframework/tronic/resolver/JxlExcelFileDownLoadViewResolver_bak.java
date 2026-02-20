package org.springframework.tronic.resolver;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.doppio.common.model.ExcelFileVo;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;



public class JxlExcelFileDownLoadViewResolver_bak extends AbstractXlsView  {

	@Value("#{config['excel.Encoding']}")			// charSet
	public String charSet;


	/**
	 * 엑셀만들기
	 */
	protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

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

		HashMap<String, WritableCellFormat> dataStyleMap = setCellStyleList(workbook, dataStyle, keyset);

		String sheetName = excelFileVO.getFileName();
		SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new java.util.Date());
		String fileName = sheetName + "_" + date + ".xls";

		int titleSize = 0; 	//title.size();
		int keysetCnt = 0; 	//keyset.length;
		double dataListSize = Math.ceil((double)dataList.size()/60000); // sheet 별로 60000건씩 출력
		int dataSize = 0;	//data.size();

		//filename 설정
		setFileNameToResponse(request, response, fileName);

		//결과가 없을때 column title만 출력
		if(dataList.size() == 0){
			WritableSheet sheet = workbook.createSheet(sheetName+"(1)", 0);

			WritableCellFormat titleFormat = new WritableCellFormat();

			//title set
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFormat.setBackground(Colour.PALE_BLUE);

			titleSize = title.size();
			keysetCnt = keyset.length;

			for(int col=0; col < titleSize; col++) {
				sheet.addCell(new jxl.write.Label(col, 0, title.get(keyset[col]), titleFormat));
			}
			for(int col=1; col <keysetCnt; col++){
				sheet.setColumnView(col, cellWidth.get(keyset[col]));
			}
		}

		//60000 : sheet 별로 60000건씩 출력
		for(int sheetNum = 0; sheetNum < dataListSize; sheetNum++){
			WritableSheet sheet = workbook.createSheet(sheetName+"("+(sheetNum+1)+")", sheetNum);

			WritableCellFormat titleFormat = new WritableCellFormat();

			//title set
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			titleFormat.setBackground(Colour.PALE_BLUE);
			titleFormat.setWrap(true);
			for(int col = 0; col < title.size(); col++) {
				sheet.addCell(new jxl.write.Label(col, 0, title.get(keyset[col]), titleFormat));
			}
			/*
			//data set
			for(int row=1; row <= (sheetNum+1)*60000+1; row++) {
				HashMap<String, String> data = dataList.get(sheetNum*60000+row-1);
				sheet.addCell(new jxl.write.Label(0, row, String.valueOf(sheetNum*60000+row),dataStyleMap.get(keyset[0])));
				for(int col=1; col < data.size()+1; col++) {
					if(dataStyleMap.get(keyset[col]).isLocked()==true){
						sheet.addCell(new jxl.write.Number(col, row, Double.parseDouble(String.valueOf(data.get(keyset[col]))) ,dataStyleMap.get(keyset[col])));
					} else {
						sheet.addCell(new jxl.write.Label(col, row, String.valueOf(data.get(keyset[col])).replace("null", "") ,dataStyleMap.get(keyset[col])));
					}
				}
				if(sheetNum*60000+row==dataList.size())break;
			}
			*/
			// data set
			for(int row = 1; row <= (sheetNum+1)*60000+1; row++) {
				HashMap<String, String> data = dataList.get(sheetNum*60000+row-1);
				sheet.addCell(new jxl.write.Label(0, row, String.valueOf(sheetNum*60000+row),dataStyleMap.get(keyset[0])));
				dataSize = data.size();

				for(int col = 1; col < keyset.length; col++) {

					if(dataStyleMap.get(keyset[col]).isLocked() == true){
						if(data.get(keyset[col]) == null || "".equals(String.valueOf(data.get(keyset[col])).replace("null", ""))) {
							sheet.addCell(new jxl.write.Label(col, row, "", dataStyleMap.get(keyset[0])));
						}else {
							sheet.addCell(new jxl.write.Number(col, row, Double.parseDouble(String.valueOf(data.get(keyset[col]))), dataStyleMap.get(keyset[col])));
						}
					} else {
						sheet.addCell(new jxl.write.Label(col, row, String.valueOf(data.get(keyset[col])).replace("null", ""), dataStyleMap.get(keyset[col])));
					}
				}

				if(sheetNum*60000+row==dataList.size())break;
			}

			//column size 조정
			for(int col = 1; col < keyset.length; col++){
				sheet.setColumnView(col, cellWidth.get(keyset[col]));
			}
		}
	}

	/**
	 * 엑셀파일명
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws UnsupportedEncodingException
	 */
	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
		String userAgent = request.getHeader("User-Agent");

		if (userAgent.indexOf("MSIE") !=-1 || userAgent.indexOf("Trident") !=-1) {
			response.setContentType("doesn/matter");
			response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20") + ";");
		} else if(userAgent.indexOf("Opera")!=-1 || userAgent.indexOf("Safari")!=-1 || userAgent.indexOf("Firefox")!=-1 || userAgent.indexOf("Chrome")!=-1){
			response.setHeader("Content-Disposition","attachment; filename=\""+new String(fileName.getBytes("UTF-8"), charSet)+"\"");
		} else {
			response.setHeader("Content-Disposition","attachment; filename=\""+new String(fileName.getBytes("EUC-KR"), charSet)+"\"");
		}
	}

	/**
	 * 셀스타일
	 * @param workbook
	 * @param dataStyle
	 * @param keyset
	 * @return
	 * @throws WriteException
	 */
	public HashMap<String, WritableCellFormat> setCellStyleList(WritableWorkbook workbook, HashMap<String, String> dataStyle, String[] keyset) throws WriteException{

		HashMap<String, WritableCellFormat> cellStyleMap = new java.util.HashMap<String, WritableCellFormat>();
		String style[] = null;
		double size = dataStyle.size();

		for(int i = 0; i < size; i++){

			style = dataStyle.get(keyset[i]).split("/");
			WritableCellFormat cellStyle;
			NumberFormat numberFormat = null;

			for(int j = 0; j < style.length; j++){
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

				// align
				if("LEFT".equals(style[j].trim())){
					cellStyle.setAlignment(Alignment.LEFT);
				} else if("CENTER".equals(style[j].trim())){
					cellStyle.setAlignment(Alignment.CENTRE);
				} else if("RIGHT".equals(style[j].trim())){
					cellStyle.setAlignment(Alignment.RIGHT);
				}

				// cell backgroundcolor
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

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

}