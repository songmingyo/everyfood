package com.doppio.common.util.pagination;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;

public class ContHeaderFooterPageEvent extends PdfPageEventHelper {
    String fontSrc = "";
    String wmImgPath = "";
    Font font;
    
    public ContHeaderFooterPageEvent(String fontSrc, String wmImgPath) throws DocumentException, IOException {
        this.fontSrc = fontSrc;
        BaseFont bfont = BaseFont.createFont(fontSrc, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        this.font = new Font(bfont,7);
        this.font.setColor(BaseColor.DARK_GRAY);
        this.wmImgPath = wmImgPath;
    }
    
    public void onStartPage(PdfWriter writer, Document document) {

        // image watermark
        Image img = null;
        try {
            img = Image.getInstance(wmImgPath);
            float w = (float) (img.getScaledWidth() / 2.5);
            float h = (float) (img.getScaledHeight() / 2.5);

            // transparency
            PdfGState gs1 = new PdfGState();
            gs1.setFillOpacity(0.5f);
            float wmX = 305 - (w/2);
            float wmY = 550;
            PdfContentByte canvas = writer.getDirectContent();
            canvas.saveState();
            canvas.setGState(gs1);
            Chunk p = new  Chunk();
            ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_MIDDLE, new Phrase(new Chunk(img, 0, -200, true)), wmX, wmY, 0);
            canvas.restoreState();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("왼쪽 위", font), 30, 800, 0);
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("왼쪽 위", font), 568, 815, 0);
    }

    public void onEndPage(PdfWriter writer, Document document) {


        ColumnText ct = new ColumnText(writer.getDirectContent());
        ct.setSimpleColumn(30, 0, 600 , 50);
//        ct.addElement(new Paragraph("*본 계약서는 상기 계약당사자 간에 전자서명법 등 관련법령에 근거하여 전자서명으로 체결한 전자계약서입니다.", font));
        
        try {
            ct.go();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_LEFT, new Phrase("왼쪽 아래askdjflkajslkdjflkasjf 어쩌고저쩌고\r\n<br/>줄바꿈", font), 30, 100, 0);
//        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT, new Phrase("page " + document.getPageNumber(), font), 550, 30, 0);
    }
}