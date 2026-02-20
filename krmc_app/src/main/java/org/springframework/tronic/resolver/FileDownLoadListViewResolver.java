package org.springframework.tronic.resolver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.doppio.common.model.AttachFileVo;


public class FileDownLoadListViewResolver extends AbstractView  {

	public FileDownLoadListViewResolver() {
		 
		setContentType("application/download; charset=utf-8");
 
	}
	
	@Value("#{config['File.StorePath.WebService']}")	// 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileStorePathWebService;
    
    @Value("#{config['File.Sys.Path']}")				// 웹루트 경로 (웹서비스용 파일업로드 패스)
    public String FileSysPath;
  
    static String fullFileSysPath;						// full path
    
    static String zipFileName;							// zip 이름
  
    static String saveZipFileName;						// 저장할 zip 이름
    
    private static final Logger logger = LoggerFactory.getLogger(FileDownLoadListViewResolver.class);
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model
			,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// full path set 
		fullFileSysPath = FileSysPath;
		
		List<AttachFileVo> list = (List<AttachFileVo>) model.get("downloadListFile");
		List<AttachFileVo> zipFileList = new ArrayList<AttachFileVo>();
		
		if(list!=null && list.size()>0) {
			for(AttachFileVo vo : list) {
				String saveFileNm = StringUtils.defaultString(vo.getSaveFileNm());
				String filePath = StringUtils.defaultString(vo.getFilePathNm());
				String fullPath = FileSysPath+filePath+saveFileNm;
				fullPath.replaceAll("//", "/");
				File file = new File(fullPath);
				
				if(file.exists() && file.isFile()) {
					//경로에 파일이 존재하고 디렉토리가 아닐때
					zipFileList.add(vo);
				}else {
					logger.error("해당 경로에 파일을 찾지 못함:" + fullPath);
				}
			}
		}
		// zip 생성..
//		newZipAdd(list);
		
		if(zipFileList !=null && zipFileList.size()>0) {
			newZipAdd(zipFileList);

			File file = new File(zipFileName);
			int fSize = (int)file.length();
			if (fSize > 0) {
				String mimetype = "application/x-msdownload";
				
				response.setContentType(mimetype);
				setDisposition(saveZipFileName, request, response);
				response.setContentLength(fSize);
				
				BufferedInputStream		in = null;
				BufferedOutputStream	out = null;
				
				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(response.getOutputStream());
				try
				{
					FileCopyUtils.copy(in, out);
					out.flush();
			    } catch (Exception ex) 
			    {
			    	logger.debug("FileDownLoadView: " + ex.getMessage());
				} finally {
					if (in  != null) { try {in.close();}  catch (Exception ignore) {logger.debug("FileDownLoadView: " + ignore.getMessage());}}
					if (out != null) { try {out.close();} catch (Exception ignore) {logger.debug("FileDownLoadView: " + ignore.getMessage());}}
					
					// zip delete
					file.delete();
				}
			
				out.flush();
			}
		}else {
			response.sendRedirect("/common/error/customFileNotFound.do");
		}
		
		    
	}// end renderMergedOutputModel
	
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
		} else {
		    sb.append(c);
		}
	    }
	    encodedFilename = sb.toString();
	} else {
	    //throw new RuntimeException("Not supported browser");
	    throw new IOException("Not supported browser");
	}
	
	System.out.println("encodedFilename : " + encodedFilename );
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
    
    public static void newZipAdd(List<AttachFileVo> list) throws Exception {
        
    	SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		String date = formatter.format(new java.util.Date());
		
		// zip 이름
		zipFileName = fullFileSysPath + "/" + date + ".zip";
		zipFileName.replaceAll("//","/");
		
		// 다운로드할 zip 이름
		saveZipFileName = date + ".zip";
		
//    	byte[] buf = new byte[4096];
    	FileInputStream in = null;
    	ZipOutputStream out = null;
    	String preFileNm = "";
    	String fileName = "";
    	int cnt = 0;
    	try {
    	    out = new ZipOutputStream(new FileOutputStream(zipFileName));
    	    for (int i=0; i< list.size() ; i++) {
    	        
    	    	String filePathNm  = list.get(i).getFilePathNm();
    	    	String saveFileNm  = list.get(i).getSaveFileNm(); 
    	    	String zipFilePath = (fullFileSysPath+"/"+filePathNm+"/"+saveFileNm).replaceAll("//","/");
    	    	String orgFileNm   = list.get(i).getOrgFileNm();
    	    	int orgFileSize = Integer.parseInt(list.get(i).getFileSize());
    	    	
    	    	in = new FileInputStream( zipFilePath);
    	        Path p = Paths.get( zipFilePath );
    	        fileName = p.getFileName().toString();
    	        
    	        if(preFileNm.equals(orgFileNm)) {	//파일명이 동일한 경우 zip 생성시 덮어써짐 >> 파일명 수정 필요 ex> A, A(1)
    	        	cnt++;
    	        	preFileNm = orgFileNm;
    	        	String[] fileInfo = orgFileNm.split("\\.");
    	        	int len = fileInfo.length-1;
    	        	String fileType = fileInfo[len]; //파일형식
    	        	String fileNm ="";
    	        	for(int k=0; k<len; k++) {
    	        		fileNm += fileInfo[k];
    	        		fileNm +=".";
    	        	}
    	        	if(".".equals(fileNm.substring(fileNm.length()-1))) {
    	        		fileNm=fileNm.substring(0,fileNm.length()-1);
    	        	}
    	        	fileName = fileNm+"("+cnt+")."+fileType; //zip에 들어갈 새로운 파일명
    	        }else {
    	        	fileName = orgFileNm;
    	        	preFileNm = orgFileNm;
    	        }
    	        
    	        
    	        
    	        ZipEntry ze = new ZipEntry(fileName);
    	        out.putNextEntry(ze);
    	          
    	        int len;
    	        byte[] buf = new byte[orgFileSize];
    	        logger.error("원본파일 사이즈:"+orgFileSize);
    	        
    	        while ((len = in.read(buf)) > 0) {
    	            out.write(buf, 0, len);
    	        }
    	          
    	        out.closeEntry();
    	        
    	    }
    	          
    	    
    	} catch (IOException e) {
    		logger.debug(e.getMessage());
    		logger.error(e.getMessage());
    	}finally {
    		if(in != null) in.close();
    		if(out != null) out.close();
    	}
    }// end newZipAdd
    
}



