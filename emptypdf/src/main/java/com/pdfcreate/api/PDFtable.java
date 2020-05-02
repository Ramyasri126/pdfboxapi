package com.pdfcreate.api;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFtable {

	public static  void drawTable(PDPage page , PDPageContentStream contentStream ,
            float  y,  float margin,
           String[][]   content) throws IOException {
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

public static void main(String[] args) throws IOException{
PDDocument doc = new PDDocument();
PDPage page = new PDPage();
doc.addPage( page );

PDPageContentStream contentStream =
     new PDPageContentStream(doc, page);

String[][] content = {{"StudentName","StudentId", "Marks"},
           {"John","121", "56"},
           {"Smith","122", "82"},
           {"Rock","123", "62"},
          {"Ben","124", "85"}} ;         
String path = "C:\\Users\\RAMYASRI\\eclipse-workspace";


drawTable(page, contentStream, 700, 100, content);


contentStream.close();
doc.save("sample.pdf" );
System.out.println("done");
}

	
}







