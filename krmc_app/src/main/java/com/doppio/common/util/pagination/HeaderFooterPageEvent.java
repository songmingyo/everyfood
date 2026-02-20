package com.doppio.common.util.pagination;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	public HeaderFooterPageEvent(String printMode, String workType) {
		this.mode = printMode;
		this.workType = workType;
	}

    private PdfTemplate t;
    private Image total;
    private String mode;
    private String workType;

    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        //addHeader(writer);
         addFooter(writer);
    }


    private void addFooter(PdfWriter writer){
        PdfPTable footer = new PdfPTable(2);
        try {
            footer.setWidths(new int[]{2, 24});
            footer.setWidthPercentage(50);

            footer.setTotalWidth(560);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(30);
            footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            //footer.getDefaultCell().setBorderColor(BaseColor.RED);
            // here for the text Page 100 of, word of goes below in next line.
            //It should be in same line.
            footer.addCell(new Phrase(String.format("Page %d of", Integer.parseInt(writer.getPageNumber()+"")), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.NO_BORDER);
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            footer.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);

            // 가로모드 일 경우
            if(mode.equals("horizontal")) {
            	footer.writeSelectedRows(0, -1, 400, 27, canvas);
            }else { // 세로모드 일 경우
            	footer.writeSelectedRows(0, -1, 285, 30, canvas);
            }


            //footer.writeSelectedRows(0, -1, 34, 20, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    public void onCloseDocument(PdfWriter writer, Document document) {
        int totalLength = String.valueOf(writer.getPageNumber()).length();
        int totalWidth = totalLength * 5;
        ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
                new Phrase(String.valueOf(((this.mode.equals("horizontal") && !this.workType.equals("RCA")) || this.workType.equals("RAL") || this.workType.equals("RALE"))  ? writer.getPageNumber()-1 : writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)),
                totalWidth, 6, 0);
    }
}