package com.doppio.common.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
/*import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.tronic.util.FileUtil;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.service.ImageLoadService;


/**
 * @author jyLee
 * @Description : 이미지 로더 ( 시스템 이미지(사이즈별, 이력별) )
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *  2014.10.23	jyLee	path 파라메터가 없는 경우 Exception 발생시  NoImage 처리
 * </pre>
 * @version :
 */


@Controller
public class ImageLoadController {

	private static final Logger logger = LoggerFactory.getLogger(ImageLoadController.class);
	
	@Autowired
	ImageLoadService imageLoadService;	
	
	@Value("#{config['File.Sys.Path']}")					// 웹루트 경로 (웹서비스용 파일업로드 패스)
	public String FileSysPath;

	@Value("#{config['File.StorePath.AppService.Prove']}")	// 업무구분 폴더명
	public String bizPath;

	//업로드가능한 img 파일 양식 리스트
	@Value("#{config['accept.file.types.img']}")
	protected String acceptFileTypesImg;
	//업로드가능한 doc 파일 양식 리스트
	@Value("#{config['accept.file.types.doc']}")
	protected String acceptFileTypesDoc;


	/**
	 * 공통 이미지 로드 : imagePath - 파일아이디_순번
	 */
	@RequestMapping(value= {"/front/common/{imageInfo}/loadImageCommon","/app/common/{imageInfo}/loadImageCommon"}, method = RequestMethod.GET)
	public void loadImageCommon(@PathVariable("imageInfo") String imageInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AttachFileVo fileVo = new AttachFileVo();
		String thumGuBun = null;

		String[] tempArr =  imageInfo.split("_");

		fileVo.setAtchFileId(tempArr[0]);
		fileVo.setSeq(tempArr[1]);

		if(tempArr.length > 2){
			thumGuBun = tempArr[2]; // 썸네일 있을시
		}
		
		fileVo = imageLoadService.selectCommonFileInfo(fileVo, request);

		InputStream fi = null;

		String path = FileSysPath + StringUtils.defaultString(fileVo.getFilePathNm()) + StringUtils.defaultString(fileVo.getSaveFileNm());
		String fileName = StringUtils.defaultString(fileVo.getSaveFileNm());
		//썸네일 이미지
		if(StringUtils.isNotEmpty(thumGuBun)) {
			path = FileSysPath + StringUtils.defaultString(fileVo.getFilePathNm()) + StringUtils.defaultString(thumGuBun+"_"+fileVo.getSaveFileNm());
			fileName = StringUtils.defaultString(thumGuBun+"_"+fileVo.getSaveFileNm());
		}
		
		try {
			File file = new File(path);
			// path 파라메터가 없는 경우 디렉토리 여부 확인 -> getTempImagePath()를 사용하려면 업로드 폴더에 noImage 파일을 넣고 지정해줘야함.
			/*			
			if (!file.exists() || file.isDirectory()) {
				logger.error("file not exist. path :" + path);
				path = getTempImagePath();

				file = new File(path);
				if (!file.exists()) {
					logger.error("temp file not exist. path :" + path);
				}
			}
			*/
			// 허용 가능한 확장자 
			String allowUploadList = acceptFileTypesImg + "|" + acceptFileTypesDoc;
			// 파일명 중 확장자 부분만 저장
			String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
			int point = allowUploadList.indexOf(fileExt);
			
			//파일이 있을 경우에만 이미지 로드(404 에러 방지, img onError 처리)
			if(file.exists() && point > -1) {
				
				fi = new FileInputStream(path);
				response.setContentLength((int) file.length());

				if("pdf".equals(fileVo.getFileTypeNm())){
					response.setContentType( "application/pdf" );
					response.setHeader("Content-Disposition", "attachment; filename=\"" +   "Test.pdf\";" );
				}else response.setHeader("Content-Disposition", "inline;");

				FileCopyUtils.copy(fi, response.getOutputStream());
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			throw new Exception(e.toString());
		}finally {
			if(fi != null) fi.close();
		}
	}


	/**
	 * getTempImagePath
	 */
	public String getTempImagePath(){

		//String tempCopyImage = "noimage.png";
		String tempCopyImage = "notFoundImage.png";
		String tempCopyImagePath = this.getClass().getResource("").getPath() + "/" + tempCopyImage;

		logger.debug("tempCopyImagePath : "+ tempCopyImagePath);

		//File temp = new File(".");	// D:\Project\IDE\springsource3.1\sts-3.1.0.RELEASE\.

		String path = FileSysPath + tempCopyImage;

		File temp = new File(path);

		FileInputStream inputStream   =  null;
		FileOutputStream outputStream = null;
		
		BufferedInputStream  bin = null;
		BufferedOutputStream bout = null;
		if(!temp.exists()){
			try {
				File copyTemp = new File(tempCopyImagePath);
				inputStream = new FileInputStream(copyTemp);
				outputStream = new FileOutputStream(path);
				
				bin = new BufferedInputStream(inputStream);
				bout = new BufferedOutputStream(outputStream);

				int bytesRead = 0;
				byte[] buffer = new byte[1024];
				while ((bytesRead = bin.read(buffer, 0, 1024)) != -1) {
				    bout.write(buffer, 0, bytesRead);
				}
				

			} catch (FileNotFoundException e) {
				logger.error("FileNotFoundException :" + e);
			} catch (IOException e) {
				logger.error("IOException :" + e);
			}
			finally {
				try { if(inputStream != null)	inputStream.close();}catch (Exception e) {logger.debug(e.getMessage());}
				try { if(outputStream != null)	outputStream.close();}catch (Exception e) {logger.debug(e.getMessage());}
				try { if(bin != null) 			bin.close();}catch (Exception e) {logger.debug(e.getMessage());}
				try { if(bout != null)			bout.close();}catch (Exception e) {logger.debug(e.getMessage());}
			}

		}

		return path;

	}
	
	/**
	 * 
	 * @Method : createThumbLoadImageCommon
	 * @Description : 썸네일 생성 후 이미지 로드
	 * @param imageInfo
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value= {"/app/common/{imageInfo}/createThumbLoadImageCommon","/front/common/{imageInfo}/createThumbLoadImageCommon"}, method = RequestMethod.GET)
	public void createThumbLoadImageCommon(@PathVariable("imageInfo") String imageInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AttachFileVo fileVo = new AttachFileVo();
		String[] tempArr = imageInfo.split("_");
		fileVo.setAtchFileId(tempArr[0]);
		fileVo.setSeq(tempArr[1]);
		
		// 파일정보 조회
		fileVo = imageLoadService.selectCommonFileInfo(fileVo, request);
		
		if(fileVo != null){	
			// 원본파일 경로
			String originFilePath = StringUtils.defaultString(fileVo.getFilePathNm());
			// 원본파일명
			String originFileNm = StringUtils.defaultString(fileVo.getSaveFileNm());
			
			if(!"".equals(originFilePath) && !"".equals(originFileNm)){
				int lastIndex = originFileNm.lastIndexOf(".");
				String fileName = originFileNm.substring(0, lastIndex);
				String fileExt = originFileNm.substring(lastIndex + 1);
				String suffix = fileExt.toLowerCase();
				
				// 1. 원본파일 존재여부 및 허용 가능한 확장자 여부 체크 
				String allowUploadList = acceptFileTypesImg + "|" + acceptFileTypesDoc;
				int point = allowUploadList.indexOf(suffix);				
				String path = FileSysPath + originFilePath;
				String originFullPath = path +"/"+ originFileNm;
				
				File originFile = new File(originFullPath);

				if(originFile.exists() && point > -1) {
					int fileLength = 0;
					String loadPath = "";
					String thumbFullPath = path + fileName + "_T." + fileExt;

					// 2. 썸네일 파일 존재여부 체크
					File thumbnailFile = new File(thumbFullPath);
					if(!thumbnailFile.isFile()){
						// 3. 썸네일 파일 생성(파일생성경로, 원본파일, 가로사이즈, 세로사이즈, 썸네일파일생성구분명)
						FileUtil.pixelGrabberThumbImage(path, originFile, 640, 360, "_T");
						// 썸네일 파일 생성되었는지 체크
						File createCheckFile = new File(thumbFullPath);
						if(!createCheckFile.isFile()){
							// 생성 오류로 인해 썸네일 파일이 생성되지 않은경우 원본파일로 로드
							loadPath = originFullPath;
							fileLength = (int)originFile.length();
						}else{
							loadPath = thumbFullPath;
							fileLength = (int)createCheckFile.length();
						}
					}else{
						// 썸네일 파일이 이미 존재하면 해당 파일로 그대로 로드 
						loadPath = thumbFullPath;
						fileLength = (int)thumbnailFile.length();
					}
					
					// 4. 이미지파일 로드
					InputStream fi = null;
					
					try{
						fi = new FileInputStream(loadPath);
						response.setContentLength(fileLength);
						response.setHeader("Content-Disposition", "inline;");
						FileCopyUtils.copy(fi, response.getOutputStream());
					}catch(Exception e){
						logger.debug(e.getMessage());
						throw new Exception(e.toString());
					}finally{
						if(fi != null){
							fi.close();
						}
					}
				}
			}
		}
	}	

}