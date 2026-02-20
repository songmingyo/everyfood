package org.springframework.tronic.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.doppio.common.model.AttachFileVo;
import com.doppio.common.model.ExcelFileVo;
import com.doppio.common.security.service.CustomUser;
import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

@Component
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    @Value("#{config['File.Sys.Path']}")			 // 어플리케이션 업로드루트 경로
	public String FileSysPath;

    @Value("#{config['File.StorePath.WebService']}") // 웹서비스 업로드루트 경로
    public String FileStorePathWebService;
    

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     *
     * @param files
     * @return
     * @throws Exception
     */
    public List<AttachFileVo> parseFileInf(Map<String, MultipartFile> files, String KeyStr, String storePath, String dbPath, String thumb) throws Exception {
    	int fileKey = 1;
    	int smallImgWidth=120;
    	int smallImgHeight=100;
    	int mediumImgWidth=314;
        int mediumImgHeight=235;


    	CustomUser customUser 				= new CustomUser();		//로그인 사용자 정보

		/*사용자 로그인 정보 설정 ------------------------------------------------------------------*/
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

	    if(auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
	    	customUser 	= (CustomUser)auth.getPrincipal();
	    }

    	String storePathString = "";
    	String atchFileIdString = "";

    	// 넘어온 물리적경로(storePath)가 없을경우 Default 로 프로퍼티 시스템 업로드 패스를 참조함.
    	if (storePath == null || "".equals(storePath) ) {
        	storePathString = FileSysPath+dbPath;
    	} else {
    		storePathString = storePath+dbPath;
    	}


    	File saveFolder = new File(storePathString);

    	if (!saveFolder.exists() || saveFolder.isFile()) {
    	    saveFolder.mkdirs();
    	}

    	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
    	MultipartFile file;
    	String filePath = "";
    	List<AttachFileVo> result  = new ArrayList<AttachFileVo>();
    	AttachFileVo fvo;

    	while (itr.hasNext()) {
    	    Entry<String, MultipartFile> entry = itr.next();

    	    file = entry.getValue();
    	    String orginFileName = file.getOriginalFilename();

    	    //--------------------------------------
    	    // 원 파일명이 없는 경우 처리
    	    // (첨부가 되지 않은 input file type)
    	    //--------------------------------------
    	    if ("".equals(orginFileName)) {
    		continue;
    	    }
    	    ////------------------------------------

    	    int index = orginFileName.lastIndexOf(".");
    	    String fileExt = orginFileName.substring(index + 1);
    	    String newName = KeyStr+"_"+ DateUtil.getTimeStamp() +"_"+ fileKey + "."+fileExt;
    	    long _size = file.getSize();

    	    if (!"".equals(orginFileName)) {
    	    	filePath = storePathString + File.separator + newName;
    	    	file.transferTo(new File(filePath));

    	    	// 썸네일 생성
    	    	if("Y".equals(thumb)) {
    	    		String thumbPath = storePathString + File.separator;

        	    	thumbImage(thumbPath, new File(filePath), mediumImgWidth, mediumImgHeight, "M_" ) ;	 // 중간 사이즈 썸네일 만들기
    				thumbImage(thumbPath, new File(filePath), smallImgWidth, smallImgHeight,  "S_" ) ;	 // 스몰 사이즈 썸네일 만들기
    	    	}

    	    }

    	    fvo = new AttachFileVo();
    	    fvo.setFileTypeNm(fileExt);				// 파일확장자
    	    fvo.setFilePathNm(dbPath);				// 저장경로
    	    fvo.setFileSize(Long.toString(_size));	// 사이즈
    	    fvo.setOrgFileNm(orginFileName);		// 원본파일명
    	    fvo.setSaveFileNm(newName);				// 저장파일명
    	    fvo.setAtchFileId(atchFileIdString);	// 업로드파일아이디
    	    fvo.setSeq(String.valueOf(fileKey));	// 파일순번
    	    fvo.setFileKindCd(KeyStr);				// 파일구분코드

    	    /* IP 로 대체 할지 판단 필요 ------------------------------*/
    	    if(StringUtils.isEmpty(customUser.getMemberCd()))
    	    	fvo.setWorkId("guest");
    	    else
    	    	fvo.setWorkId(customUser.getMemberCd()); // 작업자 멤버코드
    	    /*------------------------------------------------*/

    	    result.add(fvo);

    	    fileKey++;
    	}

    	return result;
    }

    /**
     * @Method : fileWrite
     * @Description : 파일 쓰기
     * @param strFile : 경로
     * @param strFileName : 파일명
     * @param strCon : 내용
     * @return boolean
     * @throws Exception
     */
	 public static boolean fileWrite(String strFile, String strFileName, String strCon) throws Exception {
		FileWriterWithEncoding  fileWriter = null;
		boolean retValue = false;
		File fr = null;
		try {
			fr=new File(strFile,strFileName);
			fileWriter = new FileWriterWithEncoding(fr, "UTF-8");
			fileWriter.write(strCon);
			retValue = true;
		} catch(IOException ex) {
			logger.debug(ex.getMessage());
		} finally {
			if (fileWriter != null) {try {fileWriter.close();} catch (IOException e) {logger.debug(e.getMessage());}}
		}
		return retValue;
	}

    public static ExcelFileVo toExcel(String fileName, List<HashMap<String, String>> datalist, String[] title, String[] dataStyle, int[] cellWidth, String[]keyset){
		//Excel파일 정보/dataList
	    ExcelFileVo ExcelFileVo = new ExcelFileVo();
		//data list
		/*
		 * data는 Excel에 출력할 Column만 Query
		 */
		//title map
		HashMap<String, String> titleMap = new java.util.HashMap<String, String>();
		//cell style map
		HashMap<String, String> dataStyleMap = new java.util.HashMap<String, String>();
		//cell width
		HashMap<String, Integer> cellWidthMap = new java.util.HashMap<String, Integer>();

		for(int i=0; i<keyset.length; i++){
		//	System.out.println("============Row: "+i+"  title:"+title[i]+" dataStyle:"+dataStyle[i]+" cellWidth:"+cellWidth[i]);
			titleMap.put(keyset[i], title[i]);
			dataStyleMap.put(keyset[i], dataStyle[i]);
			cellWidthMap.put(keyset[i], cellWidth[i]);
		}

		ExcelFileVo.setFileName(fileName);
		ExcelFileVo.setDataStyleMap(dataStyleMap);
		ExcelFileVo.setTitleMap(titleMap);
		ExcelFileVo.setKeyset(keyset);
		ExcelFileVo.setDatalist(datalist);
		ExcelFileVo.setCellWidthMap(cellWidthMap);
		return ExcelFileVo;
	}
    
    public static ExcelFileVo toExcelNew(String fileName, List<HashMap<String, String>> datalist, String[] title, String dataStyle, int cellWidth, String[]keyset){
	   //Excel파일 정보/dataList
	   ExcelFileVo ExcelFileVo = new ExcelFileVo();
	   HashMap<String, String> dataMap;
	   //data list

	   /*
	    * data는 Excel에 출력할 Column만 Query
	    */
	   //title map
	   HashMap<String, String> titleMap = new java.util.HashMap<String, String>();
	   //cell style map
	   HashMap<String, String> dataStyleMap = new java.util.HashMap<String, String>();
	   //cell width
	   HashMap<String, Integer> cellWidthMap = new java.util.HashMap<String, Integer>();

	   for(int i=0; i<keyset.length; i++){
	      // System.out.println("============Row: "+i+"  title:"+title[i]+" dataStyle:"+dataStyle[i]+" cellWidth:"+cellWidth[i]);
	      titleMap.put(keyset[i], title[i]);
	      dataStyleMap.put(keyset[i], dataStyle);
	      cellWidthMap.put(keyset[i], cellWidth);
	   }

	   ExcelFileVo.setFileName(fileName);
	   ExcelFileVo.setDataStyleMap(dataStyleMap);
	   ExcelFileVo.setTitleMap(titleMap);
	   ExcelFileVo.setKeyset(keyset);
	   ExcelFileVo.setDatalist(datalist);
	   ExcelFileVo.setCellWidthMap(cellWidthMap);

	   return ExcelFileVo;
	}



	/** 계약서 파일 디렉토리 명칭  return*/
	public static String getContractPath(String gubunValue, String dcDate) throws Exception
	{
		String strValue = "";

		strValue = "cont/"
				 + gubunValue +"/"
				 +dcDate.substring(0,4)+"/"
				 +dcDate.substring(4,6)+"/"
		 		 +dcDate.substring(6)+"/";
	
		return strValue;
	}



	/**
	 * 파일 copy
	 *
	 */
	public static boolean getFileCopy(String strNowFile, String strCopyFile) throws IOException {
		boolean rtn = true;
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			in  = new FileInputStream(strNowFile);
			out = new FileOutputStream(strCopyFile);
			
			int BUFFER_SIZE = 4096;
			int byteCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			
		} catch(Exception ex) {
			logger.debug(ex.getMessage());
			rtn = false;
		} finally {
			if (out != null) {try {out.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (in != null) {try {in.close();} catch (Exception e) {logger.debug(e.getMessage());}}
		}
		return rtn;
	}

	/**
	 * @Method : getNio2FileCopy
	 * @Description : 파일 NIO.2 file copy
	 * @param nowFilePath : 현재파일경로
	 * @param copyFilePath : 복사파일경로
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean getNio2FileCopy(String nowFilePath, String copyFilePath) throws IOException {
		boolean rtn = true;
		Path source = Paths.get(nowFilePath);
		Path target = Paths.get(copyFilePath); 
		Path targetDir = Paths.get(copyFilePath.substring(0, copyFilePath.lastIndexOf("/")+1));
		
		// 사전체크
		if (source == null) {
			return false;
		}
		if (target == null) {
			return false;
		}
		
		// 소스파일이 존재하는지 체크
		if (!Files.exists(source, new LinkOption[] {})) {
			return false;
		}
		
		// 타겟파일 디렉토리 체크
		if (!Files.exists(targetDir, new LinkOption[] {})) {
			Files.createDirectories(targetDir); // 디렉토리가 없으면 생성
		}
		
		
		
		//파일 복사
		try {
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			logger.debug(e.getMessage());
			return false;
		}
		
		// 파일이 정상적으로 생성이 됐는지 체크
		if (!Files.exists(target, new LinkOption[] {})) {
			logger.error("파일이 존재하지 않습니다.");
			return false; //파일이 존재하지않음
		}
		
		return rtn;
	}
	
	/**
	 * 파일 NIO.2 file size 추출
	 */
	public static String getNio2FileSize(String file) throws Exception {
		Path source = Paths.get(file);
		
		// 소스파일이 존재하는지 체크
		if (source == null || !Files.exists(source, new LinkOption[] {})) {
			throw new NullPointerException("파일이 존재하지 않습니다.");
		}
		
		// file 속성 가져오기
		BasicFileAttributes fileAttr = Files.readAttributes(source, BasicFileAttributes.class);
		
		// file size 반환
		return String.valueOf(fileAttr.size());
	}

	/**
	 *	물리 파일 삭제
	 */
	public boolean delUploadFile(AttachFileVo  paramVo) throws Exception {
		boolean rtn = true;

		List<String> delFilePath = new ArrayList<>();

		String filePath = FileSysPath + paramVo.getFilePathNm();
		String fileFullPath = filePath + paramVo.getSaveFileNm();

		delFilePath.add(fileFullPath);

		// 계약서 스캔 이미지 삭제 일경우 썸네일 삭제
		if("SCAN".equals(paramVo.getFileKindCd())) {
			delFilePath.add(filePath+"M_"+paramVo.getSaveFileNm());
			delFilePath.add(filePath+"S_"+paramVo.getSaveFileNm());
		}

		try {

			for(String str : delFilePath) {

				java.io.File file = new java.io.File(str);
				file.delete();
			}

		} catch (Exception e) {
			logger.debug(e.getMessage());
			rtn = false;
		}


		return rtn;
	}




	public  void thumbImage(String thumbPath, File originFile, int thumbWidth, int thumbHeight, String thumb ) {

		BufferedImage originFileBuffer = null;
		BufferedImage rescaledImage    = null;
		ResampleOp resampleOp = null;



		try {
			// 디렉토리 없으면 디렉토리 생성
			File thumbDir = new File(thumbPath);
			if(!thumbDir.exists()) {
				thumbDir.mkdirs();
	        }

			// 원본파일 버퍼 생성
			originFileBuffer = ImageIO.read( originFile ) ;

			// 썸네일 높이 계산
			int originWidth = originFileBuffer.getWidth();
			int originHeight = originFileBuffer.getHeight();
			if(thumbHeight ==0)	thumbHeight = originHeight * thumbWidth / originWidth ;

			// 확장자 체크 및 원본 경로
			int lastIndex 	= originFile.getName().lastIndexOf(".");
			String fileExt 	= originFile.getName().substring( lastIndex+1 );
			String fileName = originFile.getName().substring(0, lastIndex);

			resampleOp = new ResampleOp(thumbWidth, thumbHeight) ;
			resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Soft );
			rescaledImage = resampleOp.filter(originFileBuffer, null );

			// 썸네일 파일 생성
			File thumbFile =  new File(thumbPath + thumb + fileName  +"."+fileExt );
			ImageIO.write( rescaledImage, fileExt, thumbFile );

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		finally {

		}
	}

	/**
	 * 
	 * @Method : pixelGrabberThumbImage
	 * @Description : 속도향상 썸네일 파일 생성
	 * @param thumbPath
	 * @param originFile
	 * @param thumbWidth
	 * @param thumbHeight
	 * @param thumb
	 */
	public static void pixelGrabberThumbImage(String thumbPath, File originFile, int thumbWidth, int thumbHeight, String thumb) {
		
		try {
			// 디렉토리 없으면 디렉토리 생성
			File thumbDir = new File(thumbPath);
			if(!thumbDir.exists()) {
				thumbDir.mkdirs();
	        }
			
			Image srcImg = null;
			
			int lastIndex = originFile.getName().lastIndexOf(".");
			String fileName = originFile.getName().substring(0, lastIndex);			
			String fileExt = originFile.getName().substring(lastIndex + 1);
			String suffix = fileExt.toLowerCase();
			
			if(suffix.equals("bmp") || suffix.equals("png") || suffix.equals("gif")){
				srcImg = ImageIO.read(originFile);
            }else {     
                // jpeg, jpg는 ImageIcon을 활용해서 Image 생성
                // getScaledInstance를 통해 구한 이미지를 PixelGrabber.grabPixels로 리사이즈 할때 빠르게 처리하기 위해
                srcImg = new ImageIcon(thumbPath + originFile.getName()).getImage();
            }

			srcImg = ImageIO.read(originFile);
			
			/* 생성 사이즈 정보가 무조건 넘어오기 때문에 계산로직은 주석처리함 
			int srcWidth = srcImg.getWidth(null);
			int srcHeight = srcImg.getHeight(null);
			int destWidth = -1;
			int destHeight = -1;
            
            if (thumbWidth == -1) {
                destWidth = srcWidth;
            } else if (thumbWidth > 0) {
                destWidth = thumbWidth;
            }
            
            if (thumbHeight == -1) {
                destHeight = srcHeight;
            } else if (thumbHeight > 0) {
                destHeight = thumbHeight;
            }
            
            if (thumbWidth == 0 && thumbHeight == 0) {
                destWidth = srcWidth;
                destHeight = srcHeight;
            } else if (thumbWidth == 0) {
                double ratio = ((double)destHeight) / ((double)srcHeight);
                destWidth = (int)((double)srcWidth * ratio);
            } else if (thumbHeight == 0) {
                double ratio = ((double)destWidth) / ((double)srcWidth);
                destHeight = (int)((double)srcHeight * ratio);
            }
            */
						
            Image imgTarget = srcImg.getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);
            int pixels[] = new int[thumbWidth * thumbHeight]; 
            PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, thumbWidth, thumbHeight, pixels, 0, thumbWidth); 
            pg.grabPixels();

            BufferedImage destImg = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB); 
            destImg.setRGB(0, 0, thumbWidth, thumbHeight, pixels, 0, thumbWidth);
            
			// 썸네일 파일 생성
			File thumbFile = new File(thumbPath + fileName  + thumb + "." + fileExt);
            ImageIO.write(destImg, fileExt, thumbFile);
		}catch(Exception e){
			logger.error(e.getMessage());
		}finally {
		}
		
	}	
	
	/**
	* 파일 경로를 받아서 그경로에 위치한 파일을 해쉬한다. 그 해쉬값을 base64로 encoding하고 반환한다.
	*/
	public String getHash(String fileLoc) throws Exception {
		String result = "";

		File file = new File(fileLoc);
		FileInputStream fis = null;

		try {
			if (file.isFile()) {
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				fis = new FileInputStream(file);
				byte[] dataBytes = new byte[1024];

				int nread = 0;

				while ((nread = fis.read(dataBytes)) != -1) {
					md.update(dataBytes, 0, nread);
				}
				result = Base64Utils.encodeToString(md.digest());
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		} finally {
			if (fis != null) {try {fis.close();} catch (Exception e) {logger.debug(e.getMessage());}}
		}

		return result;
	}
	
	/**
	 * 파일경로에서 파일을 읽고 base64로 인코딩하여 반환
	 */
	public String fileToString(String fileLoc) throws Exception {
		File file = new File(fileLoc);
		
	    String fileString = new String();
	    FileInputStream inputStream =  null;
	    ByteArrayOutputStream byteOutStream = null;

	    try {
	        inputStream = new FileInputStream(file);
	        byteOutStream = new ByteArrayOutputStream();
	        int len = 0;
	        byte[] buf = new byte[1024];

	        while ((len = inputStream.read(buf)) != -1) {
	             byteOutStream.write(buf, 0, len);
	        }

	        byte[] fileArray = byteOutStream.toByteArray();
	        fileString = new String(Base64Utils.encodeToString(fileArray));

	    } catch (IOException e) {
			logger.debug(e.getMessage());
	    } finally {
	    	if (byteOutStream != null) try {byteOutStream.close();} catch(Exception e) {logger.debug(e.getMessage());}
	    	if (inputStream != null) try {inputStream.close();} catch(Exception e) {logger.debug(e.getMessage());}
	    }
	    return fileString;
	}
	
	/**
	 * base64로 인코딩 된 파일데이터를 서버에 저장
	 */
	public String dataToFile(String base64FileData, String fileLoc) throws Exception {
		byte[] fileData = Base64Utils.decodeFromString(base64FileData);
		File file = new File(fileLoc);
		
		String fileString = new String();
		ByteArrayInputStream byteInputStream =  null;
		FileOutputStream fileOutStream = null;
		
		try {
			byteInputStream = new ByteArrayInputStream(fileData);
			fileOutStream = new FileOutputStream(file, false);
			int len = 0;
			byte[] buf = new byte[1024];
			
			while ((len = byteInputStream.read(buf)) != -1) {
				fileOutStream.write(buf, 0, len);
			}
			
			
		} catch (IOException e) {
			logger.debug(e.getMessage());
		} finally {
			if (fileOutStream != null) {try {fileOutStream.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (byteInputStream != null) {try {byteInputStream.close();} catch (Exception e) {logger.debug(e.getMessage());}}
		}
		return fileString;
	}
	
	/**
	 * 파일경로 체크
	 */
	public static String getFilePath(String path) throws Exception {
		
		String filePath = path;
		
		java.io.File file = null;
		file = new java.io.File(path);
		if(!file.isDirectory()) file.mkdirs();
		file = null;
		
		return filePath;
	}
	/**
	 * 파일경로 생성
	 */
	public static void makePath(String strPath)
	{
		java.io.File file = null;
		
		file = new java.io.File(strPath);
		if(!file.isDirectory()) file.mkdirs();
		file = null;
	}
	
	/**
	 * 파일 삭제 
	 */
    @SuppressWarnings("rawtypes")
	public static void reMoveFile(ArrayList arrFile) {
    	if(arrFile == null) return;
		for(int i=0; i< arrFile.size(); i++)
		{
			File file = new File((String)arrFile.get(i));
			 
			file.delete();
		}
    }
	/**
	 * 서명이미지 파일 서버에 추가
	 */
	public static boolean base64ToFile(String base64Str, String fullPath) throws Exception {
		boolean result = false;
		
		String data = base64Str.split(",")[1];
		
		byte[] imageBytes = DatatypeConverter.parseBase64Binary(data);
		
		getFilePath(fullPath); // 파일경로 체크
		
		try {
			BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
			ImageIO.write(bufImg, "png", new File(fullPath));
			
		} catch (IOException e) {
			logger.debug(e.getMessage());
			return false;
		}
		
		result = true;
		return result;
	}
	
	/**
	 * 
	 * @Method : getFileMove
	 * @Description : 물리 파일 경로 이동
	 * @param nowFilePath
	 * @param moveFilePath
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean getFileMove(String nowFilePath, String moveFilePath) throws Exception {
		boolean result = true;
		
		Path source = Paths.get(nowFilePath);		//원본파일 full 경로
		Path target = Paths.get(moveFilePath);		//이동파일 full 경로
		Path targetDirPath = Paths.get(moveFilePath.substring(0, moveFilePath.lastIndexOf("/")+1));	//이동할 directory
		String targetDir = moveFilePath.substring(0, moveFilePath.lastIndexOf("/")+1);
		//필수 data 체크
		/* 1. 필수 data 체크 ------------------------------------*/
		//원본파일 경로 추출 실패
		if(source == null) return false;
		//이동할 경로 추출 실패
		if(target == null) return false;
		
		/* 2. 파일 이동 대상 디렉토리 존재여부 체크 ---------------------*/
		//이동할 파일 디렉토리가 없으면 생성
//		if(!Files.exists(targetDir, new LinkOption[] {})) {
//			Files.createDirectories(targetDir);
//		}
		
		File saveFolder = new File(targetDir);

    	if (!saveFolder.exists() || saveFolder.isFile()) {
    	    saveFolder.mkdirs();
    	}
		
		/* 3. 파일 이동 -----------------------------------------*/
		try {
			Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			logger.debug(e.getMessage());
			return false;
		}
		
		//파일이 정상적으로 이동됐는지 체크
		if(!Files.exists(target, new LinkOption[] {})) {
			logger.error("파일이 존재하지 않습니다.");
			return false;
		}
		
		return result;
	}
	
	public static ExcelFileVo toExcelWithSum(String fileName, List<HashMap<String, String>> datalist, String[] title, String[] dataStyle, int[] cellWidth, String[]keyset
			, List<HashMap<String, String>> datalistSum, String[] titleSum, String[] dataStyleSum, int[] cellWidthSum, String[]keysetSum){
			//Excel파일 정보/dataList
			ExcelFileVo ExcelFileVo = new ExcelFileVo();
			//data list
			/*
			* data는 Excel에 출력할 Column만 Query
			*/
			//title map
			HashMap<String, String> titleMap = new java.util.HashMap<String, String>();
			//cell style map
			HashMap<String, String> dataStyleMap = new java.util.HashMap<String, String>();
			//cell width
			HashMap<String, Integer> cellWidthMap = new java.util.HashMap<String, Integer>();
			
			
			
			//title map
			HashMap<String, String> titleMapSum = new java.util.HashMap<String, String>();
			//cell style map
			HashMap<String, String> dataStyleMapSum = new java.util.HashMap<String, String>();
			//cell width
			HashMap<String, Integer> cellWidthMapSum = new java.util.HashMap<String, Integer>();
			
			
			for(int i=0; i<keyset.length; i++){
			//	System.out.println("============Row: "+i+"  title:"+title[i]+" dataStyle:"+dataStyle[i]+" cellWidth:"+cellWidth[i]);
			titleMap.put(keyset[i], title[i]);
			dataStyleMap.put(keyset[i], dataStyle[i]);
			cellWidthMap.put(keyset[i], cellWidth[i]);
			}
			
			for(int i=0; i<keysetSum.length; i++){
			//	System.out.println("============Row: "+i+"  title:"+title[i]+" dataStyle:"+dataStyle[i]+" cellWidth:"+cellWidth[i]);
			titleMapSum.put(keysetSum[i], titleSum[i]);
			dataStyleMapSum.put(keysetSum[i], dataStyleSum[i]);
			cellWidthMapSum.put(keysetSum[i], cellWidthSum[i]);
			}
			
			ExcelFileVo.setFileName(fileName);
			ExcelFileVo.setDataStyleMap(dataStyleMap);
			ExcelFileVo.setTitleMap(titleMap);
			ExcelFileVo.setKeyset(keyset);
			ExcelFileVo.setDatalist(datalist);
			ExcelFileVo.setCellWidthMap(cellWidthMap);
			
			
			ExcelFileVo.setDataStyleMapSum(dataStyleMapSum);
			ExcelFileVo.setTitleMapSum(titleMapSum);
			ExcelFileVo.setKeysetSum(keysetSum);
			ExcelFileVo.setDatalistSum(datalistSum);
			ExcelFileVo.setCellWidthMapSum(cellWidthMapSum);
		
			return ExcelFileVo;
		}
	
	
}
