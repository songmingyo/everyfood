package org.springframework.tronic.resolver;

import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doppio.common.model.AttachFileVo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownLoadViewResolver extends AbstractView  {

	public FileDownLoadViewResolver() {
		 
		setContentType("application/download; charset=utf-8");
 
	}
	
	
	
	
	@Value("#{config['File.StorePath.WebService']}")	// 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileStorePathWebService;
    
    @Value("#{config['File.Sys.Path']}")				// 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileSysPath;

	@Value("#{config['pdf.css.path']}")
	public String pdfCssPath;
    
    private static final Logger logger = LoggerFactory.getLogger(FileDownLoadViewResolver.class);
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AttachFileVo attachFile = (AttachFileVo)model.get("downloadFile");
		
		String filePathNm   = (String)attachFile.getFilePathNm();
		String saveFileNm   = (String)attachFile.getSaveFileNm();
		String filePath = "";
		String orgFileNm	= (String)attachFile.getOrgFileNm();
			
		filePath = FileSysPath+filePathNm; // 파일전체경로

		File file = new File(filePath, saveFileNm);
		int fSize = (int)file.length();

		if (fSize <= 0)  response.sendRedirect("/common/error/customFileNotFound.do");
		
		String mimetype = "application/x-msdownload";
		response.setContentType(mimetype);
		setDisposition(orgFileNm, request, response);
		response.setContentLength(fSize);

		BufferedInputStream		in = null;
		BufferedOutputStream	out = null;

		in = new BufferedInputStream(new FileInputStream(file));
		out = new BufferedOutputStream(response.getOutputStream());
		try{
			FileCopyUtils.copy(in, out);
			out.flush();
		} catch (Exception ex){
			logger.debug("FileDownLoadView: " + ex.getMessage());
		} finally {
			if (out != null) { try {out.close();} catch (Exception ignore) {logger.debug(ignore.getMessage());}}
			if (in  != null) { try {in.close();}  catch (Exception ignore) {logger.debug(ignore.getMessage());}}
		}

	}
	
	
	
	 /**
     * Disposition 지정하기.
     * 
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
	String browser = getBrowser(request);
	
	String dispositionPrefix = "attachment; filename=";
	String encodedFilename = null;
	
	if (browser.equals("MSIE")) {
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
		} 
		else if (c == ',') {
			sb.append(URLEncoder.encode("" + c, "UTF-8"));
		}
		else {
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

	public void manipulatePdf(String src, String dest, String imgsrc) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(src);

		int n = reader.getNumberOfPages();
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));

		// image watermark
		Image img = Image.getInstance(imgsrc);
		float w = (float) (img.getScaledWidth() / 2.5);
		float h = (float) (img.getScaledHeight() / 2.5);

		// transparency
		PdfGState gs1 = new PdfGState();
		gs1.setFillOpacity(0.5f);

		// properties
		PdfContentByte over;
		Rectangle pagesize;
		float x, y;

		// loop over every page
		for (int i = 1; i <= n; i++) {
			pagesize = reader.getPageSizeWithRotation(i);
			x = (pagesize.getLeft() + pagesize.getRight()) / 2;
			y = (pagesize.getTop() + pagesize.getBottom()) / 2;
			over = stamper.getOverContent(i);
			over.saveState();
			over.setGState(gs1);
			over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
			over.restoreState();
		}
		stamper.close();
		reader.close();
		File file = new File(src);
		file.delete();
	}


}



