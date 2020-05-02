package com.pdfcreate.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.itextpdf.text.log.Logger;

public class PDFExporter<T> implements IPDFConvert<T>  {
    @Override
    public File export(final Collection<ISheet> pages,String Filename) throws IOException {
    	if (Objects.isNull(pages) || pages.isEmpty()) {
    		 throw new IllegalArgumentException("Invalid data provided");
        }
    	File file=new File("C:\\Users\\RAMYASRI\\eclipse-workspace"+Filename);
		FileOutputStream fout=new FileOutputStream(file);

            	PDDocument doc = new PDDocument();
            	PDPage page1 = new PDPage();
            	doc.addPage( page1 );

            	PDPageContentStream contentStream =
            	        new PDPageContentStream(doc, page1);
            	String[][] content={};
            	 float margin = 0;
				float  y = 0;
				 drawTable( page1,  contentStream,
                          y,  margin,
                         content) ;
				return file;	
            }


				public static  void drawTable(PDPage page , PDPageContentStream contentStream ,
			               float  y,  float margin,
			                String[][] content) throws IOException {
			final int rows = content.length;
			final int cols = content[0].length;
			final float rowHeight = 20f;
			final float tableWidth = page.getMediaBox().getWidth()-(2*margin);
			final float tableHeight = rowHeight * rows;
			final float colWidth = tableWidth/(float)cols;
			final float cellMargin=5f;

			//draw the rows
			float nexty = y ;
			for (int i = 0; i <= rows; i++) {
			contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
			nexty-= rowHeight;
			}

			//draw the columns
			float nextx = margin;
			for (int i = 0; i <= cols; i++) {
			contentStream.drawLine(nextx,y,nextx,y-tableHeight);
			nextx += colWidth;
			}

			//now add the text
			contentStream.setFont(PDType1Font.HELVETICA_BOLD,12);

			float textx = margin+cellMargin;
			float texty = y-15;
			for(int i = 0; i < content.length; i++){
			for(int j = 0 ; j < content[i].length; j++){
			String text = content[i][j];
			contentStream.beginText();
			contentStream.moveTextPositionByAmount(textx,texty);
			contentStream.drawString(text);
			contentStream.endText();
			textx += colWidth;
			}
			texty-=rowHeight;
			textx = margin+cellMargin;
			}
			}

     

    @Override
    public File export(final T object, final String FileName) throws IOException {
    	PDFtext text=new PDFtext();
    	
		File file=new File("C:\\Users\\RAMYASRI\\eclipse-workspace\\"+FileName);
		FileOutputStream fout=new FileOutputStream(file);
    	final String message = null;
		 final String filename = null;
		text.textPDF(message,filename) ;
		return file;
       
    }
    public static void textPDF(String message, String filename) throws IOException
	{
//	  String filename = "sample2.pdf";      
      PDDocument doc = new PDDocument();
      try {
          PDPage page = new PDPage();
          doc.addPage(page);
          
          PDFont font = PDType1Font.HELVETICA_BOLD;

          PDPageContentStream contents = new PDPageContentStream(doc, page);
          contents.beginText();
          contents.setFont(font, 30);
          contents.newLineAtOffset(50, 700);
          contents.endText();
          contents.close();
          
          doc.save(filename);
      }
      finally {
          doc.close();
      }
}

    

}


