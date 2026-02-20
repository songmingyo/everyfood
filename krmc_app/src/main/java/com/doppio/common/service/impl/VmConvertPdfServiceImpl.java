package com.doppio.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.tronic.util.StringUtil;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.Result;
import com.doppio.common.service.VmConvertPdfService;
import com.doppio.common.util.pagination.HeaderFooterPageEvent;
import com.doppio.common.util.velocity.ReportTool;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

/**
 * @Class : VmConvertPdfServiceImpl
 * @Description : VM파일 변환 관련 서비스 인터페이스
 * @author : 김동호
 * <pre>
 *  << 개정이력(Modification Information) >>
 *
 *  수정일				수정자			 수정내용
 *  ----------------	------------	---------------------------
 *  2021. 10. 18.		김동호			최초 생성
 *  2022. 04. 20.		김동호			(추가) VM Convert To HTML
 *
 * </pre>
 */
@Service("vmConvertPdfService")
public class VmConvertPdfServiceImpl implements VmConvertPdfService {

    private static final Logger logger = LoggerFactory.getLogger(VmConvertPdfServiceImpl.class);

    @Value("#{config['File.Sys.Path']}")			 			// 어플리케이션 업로드루트 경로
  	public String FileSysPath;

    @Value("#{config['pdf.css.path']}")
    public String pdfCssPath;

	@Value("#{config['File.StorePath.WebService.Url']}")		// 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String fileWebServicePath;
	
	@Value("#{config['File.StorePath.AppService.Doc']}")		// 중간 업무경로명
	public String fileAppServiceDocPath;
	
	@Autowired
	FileManagerMapper fileManagerMapper;
	
	/**
     * VM Convert To HTML
     */
	@Override
	public String vmConvertToHtml(Map<String, Object> map, HttpServletRequest request) throws Exception {
		String strData = ""; // HTML
		String path = StringUtil.nullConvert(map.get("filePath")); // 파일경로
		String vmFile = StringUtil.nullConvert(map.get("vmFileNm")); // 템플릿명
		String workType = StringUtil.nullConvert(map.get("workType")); // 문서 종류
		
		try {
			ReportTool tool = new ReportTool(request, path, vmFile);
			
			/* 데이터 리스트가 더 추가될시 업무 구분[map.get("workType")]에 따라 추가 필요함.  */
			tool.context.put("data", map.get("data"));			//master Data
			tool.context.put("listData", map.get("listData"));	//세부항목 Data
			tool.context.put("message", map.get("message"));	//message Data
			tool.context.put("webUrl", fileWebServicePath);		//message Data
			
			if("ECSCONT".equals(workType)) {		// 전자계약-계약서
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data
				
			}else if("ECSCHN".equals(workType)) {		// 가맹계약-계약서
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data
	
				tool.context.put("chnCont", map.get("chnCont"));			//가맹계약정보 Data
	
				tool.context.put("chnArstrList", map.get("chnArstrList"));	//인근가맹정보 목록 Data
				tool.context.put("dlgdMtrlList", map.get("dlgdMtrlList"));	//공급 원ㆍ부재료 목록 Data
				tool.context.put("dlgdEquipList", map.get("dlgdEquipList"));//공급 주방기기 목록 Data
				
				tool.context.put("imgFile", map.get("imgFile"));//영업지역의 표지(img) 데이터
				tool.context.put("FileSysPath", map.get("FileSysPath"));//시스템파일경로
			}
		
			strData = tool.getBindVelData();
			strData = strData.replaceAll("class=\"yellowHide\"", "class=\"yellow\"");
			strData = strData.replaceAll("□", "<input type='checkbox' name='contAgree' />");
			strData = strData.replaceAll("▣", "<input type='checkbox' name='contAgree' />");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return  strData;
	}
	
    /**
     * VM Convert To Pdf
     */
    @Override
    public void vmConvertToPdf(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	/* 데이터 리스트가 더 추가될시 업무 구분[map.get("workType")]에 따라 추가 필요함.  */
    	//map.get("data")를 HashMap으로 강제형변환하면 Map으로만 받아야해서 Vo로 넘기면 넣을 수가 없음

		//HashMap<String, Object> resultData 		 = (HashMap<String, Object>) map.get("data");				//master Data
		//List<HashMap<String,Object>>  resultList = 	(List<HashMap<String,Object>>) map.get("listData");		//세부항목 data

		// 가로/세로
		String printMode = StringUtil.nullConvert(map.get("printMode"));
		// 문서 종류
		String workType = StringUtil.nullConvert(map.get("workType"));

		// 템플릿명
		String vmFile = StringUtil.nullConvert(map.get("vmFileNm"));

		// 파일경로(없을경우 /WEB-INF/velocity/)
		String path = StringUtil.nullConvert(map.get("filePath"));
		
		// 용지 및 여백 설정(용지(가로,세로), left, right, top, bottom)
		float[] whiteSpace = {50,50,30,30};
		if(map.get("whiteSpace") != null) {
			whiteSpace = (float[])map.get("whiteSpace");
		}

		// Document 생성
		Document document = null;
		PdfWriter writer = null;
		FileInputStream fis = null;
    	try {
    		//-----PDF 생성할 VM파일의 HTML 요소 추출
    		ReportTool tool = null;
    		tool = new ReportTool(request, path, vmFile);

	    	/* 데이터 리스트가 더 추가될시 업무 구분[map.get("workType")]에 따라 추가 필요함.  */
			tool.context.put("data", map.get("data"));			//master Data
			tool.context.put("listData", map.get("listData"));	//세부항목 Data
			tool.context.put("message", map.get("message"));	//message Data
			tool.context.put("webUrl", fileWebServicePath);		//message Data
			
			if("ECSCONT".equals(workType)) {		// 전자계약-계약서
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data
				
			}else if("ECSCHN".equals(workType)) {		// 가맹계약-계약서
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data

				tool.context.put("chnCont", map.get("chnCont"));			//가맹계약정보 Data

				tool.context.put("chnArstrList", map.get("chnArstrList"));	//인근가맹정보 목록 Data
				tool.context.put("dlgdMtrlList", map.get("dlgdMtrlList"));	//공급 원ㆍ부재료 목록 Data
				tool.context.put("dlgdEquipList", map.get("dlgdEquipList"));//공급 주방기기 목록 Data
				
				tool.context.put("imgFile", map.get("imgFile"));//영업지역의 표지(img) 데이터
				tool.context.put("FileSysPath", map.get("FileSysPath"));//시스템파일경로
				
			}

			String strVelocity =  tool.getBindVelData();
			//logger.info(strVelocity);

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"vm.pdf\";" );
			setDisposition("none", request, response);

			
			// 가로/세로 및 용지 및 여백 설정(용지(가로,세로), left, right, top, bottom)
			if(printMode.equals("horizontal")) {
				document = new Document(PageSize.A4.rotate(), whiteSpace[0], whiteSpace[1], whiteSpace[2], whiteSpace[3]);
			}else {
				document = new Document(PageSize.A4, whiteSpace[0], whiteSpace[1], whiteSpace[2], whiteSpace[3]);
			}

			// PdfWriter 생성
			writer = PdfWriter.getInstance(document, response.getOutputStream());
			writer.setInitialLeading(12.5f);

	        // add header and footer
	         HeaderFooterPageEvent event = new HeaderFooterPageEvent(printMode, workType);
	         writer.setPageEvent(event);

			// Document 오픈
			document.open();
			
			XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

			// CSS
			CSSResolver cssResolver = new StyleAttrCSSResolver();
			fis = new FileInputStream(pdfCssPath + "pdf.css");
			CssFile cssFile = helper.getCSS(fis);
			cssResolver.addCss(cssFile);

			// HTML, 폰트 설정
			XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
			fontProvider.register(pdfCssPath + "IGOTHIC.ttf", "igothic");	// MalgunGothic은 alias,
			CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

			HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

			// Pipelines
			PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);


			HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
			CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

			XMLWorker worker = new XMLWorker(css, true);
			XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));

			StringReader strReader = new StringReader(strVelocity);
			logger.info("strReader ==================================" + strReader);
			xmlParser.parse(strReader);

		} catch (Exception e) {
			logger.error("pdf 생성에러 : " + e.getMessage());
		} finally {
			if (fis != null) {try {fis.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (writer != null) {try {writer.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (document != null) {try {document.close();} catch (Exception e) {logger.debug(e.getMessage());}}
		}
    }
    
    /**
     * VM Convert To Pdf 파일 생성
     */
    @Override
    public void vmConvertToPdfFileCreate(Map<String, Object> map, HttpServletRequest request) throws Exception {

    	/* 데이터 리스트가 더 추가될시 업무 구분[map.get("workType")]에 따라 추가 필요함.  */
    	//map.get("data")를 HashMap으로 강제형변환하면 Map으로만 받아야해서 Vo로 넘기면 넣을 수가 없음

		//HashMap<String, Object> resultData 		 = (HashMap<String, Object>) map.get("data");				//master Data
		//List<HashMap<String,Object>>  resultList = 	(List<HashMap<String,Object>>) map.get("listData");		//세부항목 data

		// 가로/세로
		String printMode = StringUtil.nullConvert(map.get("printMode"));
		// 문서 종류
		String workType = StringUtil.nullConvert(map.get("workType"));

		// 템플릿명
		String vmFile = StringUtil.nullConvert(map.get("vmFileNm"));
		
		// pdf파일명
		String pdfFile = StringUtil.nullConvert(map.get("pdfFileNm"));

		// 파일경로(없을경우 /WEB-INF/velocity/)
		String path = StringUtil.nullConvert(map.get("filePath"));
		
		// pdf파일경로
		String pdfPath = StringUtil.nullConvert(map.get("pdfFilePath"));

		// 용지 및 여백 설정(용지(가로,세로), left, right, top, bottom)
		float[] whiteSpace = {50,50,30,30};
		if(map.get("whiteSpace") != null) {
			whiteSpace = (float[])map.get("whiteSpace");
		}
		
		// Document 생성
		Document document = null;
		PdfWriter writer = null;
		OutputStream outputSt = null;
    	try {
    		//-----PDF 생성할 VM파일의 HTML 요소 추출
    		ReportTool tool = null;
    		tool = new ReportTool(request, path, vmFile);
    		
	    	/* 데이터 리스트가 더 추가될시 업무 구분[map.get("workType")]에 따라 추가 필요함.  */
			tool.context.put("data", map.get("data"));			//master Data
			tool.context.put("listData", map.get("listData"));	//세부항목 Data
			tool.context.put("message", map.get("message"));	//message Data
			tool.context.put("webUrl", fileWebServicePath);		//message Data
			
			if("ECSCONT".equals(workType)) {		// 전자계약-계약서
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data
				
			}else if("ECSCHN".equals(workType)) {		// 가맹계약-계약서
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data

				tool.context.put("chnCont", map.get("chnCont"));			//가맹계약정보 Data

				tool.context.put("chnArstrList", map.get("chnArstrList"));	//인근가맹정보 목록 Data
				tool.context.put("dlgdMtrlList", map.get("dlgdMtrlList"));	//공급 원ㆍ부재료 목록 Data
				tool.context.put("dlgdEquipList", map.get("dlgdEquipList"));//공급 주방기기 목록 Data
				
				tool.context.put("imgFile", map.get("imgFile"));//영업지역의 표지(img) 데이터
				tool.context.put("FileSysPath", map.get("FileSysPath"));//시스템파일경로
				
			}else if("ECSCHN_ESTSALE".equals(workType)) {	// 가맹계약-예상매출액산정서
				tool.context.put("data", map.get("data"));					//예상매출액산정서 Data
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data
				
				tool.context.put("expnlAroList", map.get("expnlAroList"));	//주변 주요시설물 현황 Data
				tool.context.put("rvcoSaleList", map.get("rvcoSaleList"));	//주변 주요경쟁점 현황 Data
				tool.context.put("strAroundList", map.get("strAroundList"));//인근점포 5개점 매출환산액 현황 Data
				tool.context.put("strAroundList2", map.get("strAroundList2"));//인근점포 5개점 매출환산액 현황(거리) Data
			}else if("BALANCE".equals(workType)){
				tool.context.put("compDataMst", map.get("compDataMst"));	//회사 주체 Data				
				tool.context.put("compDataSub", map.get("compDataSub"));	//회사 이행 Data				
				tool.context.put("chnCont", map.get("chnCont"));			//가맹계약정보 Data
				tool.context.put("FileSysPath", map.get("FileSysPath"));	//시스템파일경로
			}

			String strVelocity =  tool.getBindVelData();
			//logger.info(strVelocity);

			//response.setContentType("application/pdf");
			//response.setHeader("Content-Disposition", "attachment; filename=\"vm.pdf\";" );
			//setDisposition("none", request, response);
			if("BALANCE".equals(workType)){
				outputSt = new FileOutputStream(new File(FileSysPath + pdfPath + pdfFile));
			}else{
				outputSt = new FileOutputStream(new File(path + pdfFile)); // ex) FileSysPath/DOC/회사코드/2021/11/15/sign/계약서파일.pdf
			}
			// 가로/세로 및 용지 및 여백 설정(용지(가로,세로), left, right, top, bottom)
			if(printMode.equals("horizontal")) {
				document = new Document(PageSize.A4.rotate(), whiteSpace[0], whiteSpace[1], whiteSpace[2], whiteSpace[3]);
			}else {
				document = new Document(PageSize.A4, whiteSpace[0], whiteSpace[1], whiteSpace[2], whiteSpace[3]);
			}

			// PdfWriter 생성
			writer = PdfWriter.getInstance(document, outputSt);
			writer.setInitialLeading(12.5f);

	        // add header and footer
	         HeaderFooterPageEvent event = new HeaderFooterPageEvent(printMode, workType);
	         writer.setPageEvent(event);

			// Document 오픈
			document.open();
			
			XMLWorkerHelper helper = XMLWorkerHelper.getInstance();

			// CSS
			CSSResolver cssResolver = new StyleAttrCSSResolver();
			CssFile cssFile = helper.getCSS(new FileInputStream(pdfCssPath + "pdf.css"));
			cssResolver.addCss(cssFile);

			// HTML, 폰트 설정
			XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
			fontProvider.register(pdfCssPath + "IGOTHIC.ttf", "igothic");	// MalgunGothic은 alias,
			CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

			HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

			// Pipelines
			PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);


			HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
			CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

			XMLWorker worker = new XMLWorker(css, true);
			XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));

			StringReader strReader = new StringReader(strVelocity);
			logger.info("strReader ==================================" + strReader);
			xmlParser.parse(strReader);

		} catch (Exception e) {
			logger.error("pdf 생성에러 : " + e.getMessage());
			throw new Exception();
		} finally {
			if (document != null) {try {document.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (writer != null) {try {writer.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (outputSt != null) {try {outputSt.close();} catch (Exception e) {logger.debug(e.getMessage());}}
		}
    }


	/**
	 * Disposition 지정하기
	 * @param filename
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	 private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {

	 	String browser = getBrowser(request);
			String dispositionPrefix = "attachment; filename=";
			String encodedFilename = null;

			if (browser.equals("MSIE") || browser.equals("Trident")) {
			    encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
			} else if (browser.equals("Firefox")) {
			    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			} else if (browser.equals("Opera")) {
			    encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
			} else if (browser.equals("Chrome")) {
			    StringBuffer sb = new StringBuffer();
			    for (int i = 0; i < filename.length(); i++) {
			    	char c = filename.charAt(i);
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

			if ("Opera".equals(browser)){
				response.setContentType("application/octet-stream;charset=UTF-8");
			}
	 }


	 /**
	  * 브라우저 구분 얻기.
	  *
	  * @param request
	  * @return
	  */
	 private String getBrowser(HttpServletRequest request) {

	     String header = request.getHeader("User-Agent");

	     if (header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1) {
	         return "MSIE";
	     } else if (header.indexOf("Chrome") > -1) {
	         return "Chrome";
	     } else if (header.indexOf("Opera") > -1) {
	         return "Opera";
	     }

	     return "Firefox";
	 }

	 /**
	  * @Method : vmFileValidation
	  * @Description : VM 유효성 검사
	  * @param attachFileVo
	  * @param request
	  * @return Result
	  * @throws Exception
	  */
	@Override
	public Result vmFileValidation(AttachFileVo attachFileVo, HttpServletRequest request) throws Exception {
		Result result = new Result();
		result.setMsgCd("fail");
		
		String filePath ="";
		String strFileName  ="";
		
		Document document 	= null;
		PdfWriter writer    = null;
		FileInputStream fis	= null;
		
		try {
			AttachFileVo attachFile = fileManagerMapper.selectAttachFileDataList(attachFileVo);
			if(attachFile == null) {
				result.setMessage("파일을 찾을 수 없습니다.");
				return result;
			}
			if(!"vm".equals(attachFile.getFileTypeNm())) {
				result.setMessage("올바르지 않은 파일 형식입니다.");
				return result;
			}
			
			filePath = FileSysPath + attachFile.getFilePathNm();
			strFileName = attachFile.getSaveFileNm();
			
			document = new Document();
			writer = PdfWriter.getInstance(document, null);
			writer.setInitialLeading(12.5f);
			
			// Document 오픈
			document.open();
			XMLWorkerHelper helper = XMLWorkerHelper.getInstance();
			
			// CSS
			CSSResolver cssResolver = new StyleAttrCSSResolver();
			fis = new FileInputStream(pdfCssPath+"pdf.css");
			CssFile cssFile = helper.getCSS(fis);
			cssResolver.addCss(cssFile);
			
			// HTML, 폰트 설정
			XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
			fontProvider.register(pdfCssPath + "IGOTHIC.ttf", "igothic");	// MalgunGothic은 alias, 
			CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
			
			HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
			htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
			
			// Pipelines
			PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
			HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
			CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);
	
			XMLWorker worker = new XMLWorker(css, true);
			XMLParser xmlParser = new XMLParser(worker, Charset.forName("UTF-8"));
			
			ReportTool tool = new ReportTool(request, filePath, strFileName);
			String htmlStr = "";
			if(tool.getBindVelData() != null) {
				htmlStr =  tool.getBindVelData();
			}
								
			StringReader strReader = new StringReader(htmlStr);
			xmlParser.parse(strReader);					

			result.setMsgCd("success");
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.setMsgCd("fail");
			result.setMessage("파일 내용이 올바르지 않습니다.\n파일을 다시 업로드해주세요.");
		}finally {
			if(fis != null) try { fis.close(); } catch (Exception e) { logger.error(e.getMessage()); }
			if(writer!= null) try { writer.close(); } catch (Exception e) { logger.error(e.getMessage()); }
			if(document != null) try { document.close(); } catch (Exception e) { logger.error(e.getMessage()); }
		}
		
		return result;
	 }
}