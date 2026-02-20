package com.doppio.common.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.doppio.common.service.JasperDownloadService;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service("jasperDownloadService")
public class JasperDownloadServiceImpl implements JasperDownloadService {

	
	private static final Logger logger = LoggerFactory.getLogger(JasperDownloadServiceImpl.class);
	
    @Autowired
    private ServletContext servletContext;

    @Value("#{config['File.Sys.Path']}")			 // 어플리케이션 업로 드루트 경로
  	public String FileSysPath;

    @Autowired
    private Properties config;

	/**
	 * Jasper PDF 다운로드
	 * @param Map
	 * @return null
	 */
	@Override
	public void jasperDocumentDownload(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception{

		 if(map == null) return;

		 JasperPrint jasperPrint  = makeJasperPrint(map);

		 SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		 String date = formatter.format(new java.util.Date());

		 String fileName = map.get("fileName") + "_" + date;

		 String header = request.getHeader("User-Agent");

		 if(header.contains("MSIE") || header.contains("Trident")) {					//IE 11 => Trident
			 fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		 }else {
			 fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		 }

		 response.setContentType( "application/pdf" );

		 //viewType = browser 이면 PDF 파일 다운로드없이 Browser view
		 if(!"browser".equals(StringUtils.defaultString((String) map.get("viewType"),"")))
			 response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\";" );
		 else
			 response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".pdf\";" );


		 JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

		 //FileSave Test(OutputStream 파일명으로 하면 한글 깨짐 :::

		 /*String testFileNm = "tiếng Việt_"+map.get("fileName") + "_" + date + ".pdf";
		 JasperExportManager.exportReportToPdfFile(jasperPrint, config.getProperty("File.Sys.Path")+testFileNm);*/

 
	}

	
	/**
	 * Jasper PDF 동일 n개 레포트 병합하여 하나의 파일로 View   
	 * @param List<Map<String, Object>>
	 * @return void  [outputStream.flush] 
	 */
	@Override
	public void jasperDocumentDownloadMulit(List<Map<String, Object>> jasperList, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		if(jasperList == null || jasperList.size() <=0) return;
		response.setContentType( "application/pdf" );
		 
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		
		JasperPrint jasperPrint = null;
		for(Map<String, Object> map :  jasperList) {
			jasperPrint  = makeJasperPrint(map);
			jasperPrintList.add(jasperPrint);
		}
		
		/*JRPdfExporter   FileBufferedOutputStream  생성 -------------- */
		FileBufferedOutputStream fbos = new FileBufferedOutputStream();
		JRPdfExporter exporter = new JRPdfExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList)); // 다건의 JasperPrint 생성 
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(fbos));
		/*-------------------------------------------------------------*/
		
		try {
			exporter.exportReport();
			fbos.close();

			if (fbos.size() > 0){
				response.setContentLength(fbos.size());
				ServletOutputStream outputStream = response.getOutputStream();

				try{
					fbos.writeData(outputStream);
					fbos.dispose();
					outputStream.flush();
				}finally{
					if (outputStream != null) try{ outputStream.close();}catch (IOException ex){}
				}
			}else {
				logger.error("FileBufferedOutputStream size error!");
			}
		} catch (JRException e) {
			logger.error(e.getMessage());
			throw new ServletException(e);
		}finally{
			if(fbos != null ) try {
				fbos.close(); 	
				fbos.dispose();
			} catch (IOException ex){}
		}
		
		
		/*

		 if(!"browser".equals(viewType))
			 response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\";" );
		else
			 response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + ".pdf\";" );
		
		 if(header.contains("MSIE") || header.contains("Trident")) {					//IE 11 => Trident
			 fileName = URLEncoder.encode(fileName,"UTF-8").replaceAll("\\+", "%20");
		 }else {
			 fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		 }
		
		 response.setContentType( "application/pdf" );
		 ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		 
		 JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
		//exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(FileSysPath+fileName + ".pdf"));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfOutputStream));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();
		*/
		
	}


	/**
	 * Make JasperPrint
	 * @param Map
	 * @return JasperPrint
	 */
	@SuppressWarnings("unchecked")
	public JasperPrint makeJasperPrint(Map<String, Object> map) throws Exception {

		 JasperPrint jP  = new JasperPrint();


		 List<Map<String, Object>> contFileMapList =  (List<Map<String, Object>>) map.get("contFileMapList");


		 for(int i=0;i<contFileMapList.size();i++) {

			 String templatePath =  servletContext.getRealPath("/WEB-INF/classes/jasperReport/") +  contFileMapList.get(i).get("jasperName"+(i+1));

			 if(StringUtils.isNotEmpty(StringUtils.defaultString((String) contFileMapList.get(i).get("filePath"+(i+1)))))
				 templatePath =  FileSysPath + contFileMapList.get(i).get("filePath"+(i+1)) + contFileMapList.get(i).get("jasperName"+(i+1));

			 JRDataSource datasource = new JRBeanCollectionDataSource((Collection<?>) map.get("list"));

			 JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(templatePath);

			 if(i==0) {

				 if("empty".equals(StringUtils.defaultString((String) map.get("dataType"),"")))
					 jP  = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
				 else
					 jP  = JasperFillManager.fillReport(jasperReport, null, datasource);

			 }else {

				 JasperPrint jPNext  = new JasperPrint();

				 if("empty".equals(StringUtils.defaultString((String) map.get("dataType"),"")))
					 jPNext  = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
				 else
					 jPNext  = JasperFillManager.fillReport(jasperReport, null, datasource);


				 List pages = jPNext .getPages();

				 for (int j = 0; j < pages.size(); j++) {
				     JRPrintPage object = (JRPrintPage)pages.get(j);
				     jP.addPage(object);
				 }

			 }

		 }

		return jP;
	}

}
