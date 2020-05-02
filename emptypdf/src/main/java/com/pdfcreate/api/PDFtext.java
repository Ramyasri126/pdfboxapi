package com.pdfcreate.api;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFtext {

	public static  void textPDF( String message,String filename) throws IOException
	{
//	  String filename = "sample2.pdf";
//     String message = "This is a sample PDF document created using PDFBox.";
      
      PDDocument doc = new PDDocument();
      try {
          PDPage page = new PDPage();
          doc.addPage(page);
          
          PDFont font = PDType1Font.HELVETICA_BOLD;

          PDPageContentStream contents = new PDPageContentStream(doc, page);
          contents.beginText();
          contents.setFont(font, 30);
          contents.newLineAtOffset(50, 700);
//          contents.showText(message);
          contents.endText();
          contents.close();
          
          doc.save(filename);
      }
      finally {
          doc.close();
      }
}
	public static void main(String[] args) throws IOException
	{
	
		String message = "This is a sample PDF document created using PDFBox.";
		String filename = null;
		textPDF(message,filename);
	}
}
    



