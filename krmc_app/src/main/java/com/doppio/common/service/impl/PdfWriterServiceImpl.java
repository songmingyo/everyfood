package com.doppio.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.doppio.common.service.PdfWriterService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;

@Service("pdfWriterService")
public class PdfWriterServiceImpl implements PdfWriterService {
	private static final Logger logger = LoggerFactory.getLogger(PdfWriterServiceImpl.class);
    

	/**
	 * PDF에 사인패드 추가
	 * @param Map
	 * @return null
	 */
	@Override
	public void addSignPadToPdf(Map<String, Object> map) throws Exception{
		Document document = new Document(PageSize.A4);
		
		// 원본 pdf 파일(클래스패스에서 가져옴)
		PdfReader reader = new PdfReader((String)map.get("originFilePath"));
		
		FileOutputStream fos = null;
		PdfWriter writer = null;
		try {
			// 출력할 pdf 파일
			fos = new FileOutputStream((String)map.get("newFilePath"));
	        writer = PdfWriter.getInstance(document, fos);
	
	        document.open();
	
	        PdfContentByte canvas = writer.getDirectContent();
	
	        // 추가할 서명 이미지
	        String signImgAPath = (String)map.get("signImgA");
	        String signImgBPath = (String)map.get("signImgB");
	        
	        Image img1 = null;
	        if(signImgAPath != null && !"".equals(signImgAPath)) {
	        	img1 = Image.getInstance(Base64.decode(signImgAPath)); //party A
	        	img1.scalePercent(20); //이미지 비율 조정
	        	img1.setAbsolutePosition(0, PageSize.A4.getHeight() - img1.getScaledHeight()); //삽입할 좌표 좌상단 설정(0,0 = 왼쪽하단)
	        }
	        
	        Image img2 = null;
	        if(signImgBPath != null && !"".equals(signImgBPath)) {
		        img2 = Image.getInstance(Base64.decode(signImgBPath)); //party B
		        img2.scalePercent(20); //이미지 비율 조정
		        img2.setAbsolutePosition(PageSize.A4.getWidth() - img2.getScaledWidth(), PageSize.A4.getHeight() - img2.getScaledHeight()); //삽입할 좌표 우상단 설정(0,0 = 왼쪽하단)
	        }
	        
	        // 원본 pdf 에서 페이지를 하나씩 읽어서 출력 pdf 에 추가한다.
	        for (int num = 1, size = reader.getNumberOfPages(); num <= size; num++) {
	            PdfImportedPage page = writer.getImportedPage(reader, num);
	
	            document.newPage();
	
	            // 0, 0 위치에 페이지 추가(참고로 itext에서 좌표 체계는 왼쪽 아래에서 부터 0, 0으로 시작함, 그래프와 비슷함)
	            canvas.addTemplate(page, 0, 0);
	
	            // 이미지 투명하게 처리
	            //PdfGState state = new PdfGState();
	            //state.setFillOpacity(0.2f);
	            //canvas.setGState(state);
	
	            // 첫장에만 서명 이미지 추가
	            if(num == 1) {
	            	if(img1 != null) canvas.addImage(img1);
	            	if(img2 != null) canvas.addImage(img2);
	            }
	        }

	        //새파일 -> 기존파일로 덮어쓰기
	        File originFile = new File((String)map.get("originFilePath"));
	        File newFile = new File((String)map.get("newFilePath"));
	        if(originFile.exists()) {
	        	fileDelete(originFile);
	        }
	        newFile.renameTo(new File((String)map.get("originFilePath")));
	        
		} finally {
			if (document != null) {try {document.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (writer != null) {try {writer.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (reader != null) {try {reader.close();} catch (Exception e) {logger.debug(e.getMessage());}}
			if (fos != null) {try {fos.close();} catch (Exception e) {logger.debug(e.getMessage());}}
		}
	}
	
	private synchronized void fileDelete(File originFile) {
		originFile.delete();
	}



}
