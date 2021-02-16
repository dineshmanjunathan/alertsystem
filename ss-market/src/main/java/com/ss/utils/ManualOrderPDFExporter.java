package com.ss.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ss.app.entity.Address;
import com.ss.app.entity.StockPointPurchase;
 
 
public class ManualOrderPDFExporter {
	
    private List<StockPointPurchase> purchaseList;
    private Double total = 0.0;
     
    public ManualOrderPDFExporter(List<StockPointPurchase> purchaseList, Address address) {
        this.purchaseList = purchaseList;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(6);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Product", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("BV", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price Per Item", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);
         
    }
     
    private void writeTableData(PdfPTable table) {
        for (StockPointPurchase purchase : purchaseList) {
        	PdfPCell cell = new PdfPCell();
        	cell.setPhrase(new Phrase(purchase.getProductCode().getProdDesc()));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getQty())));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getProductCode().getBvPrice()* purchase.getQty())));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getPrice())));
        	table.addCell(cell);
        	double totalValue = purchase.getQty() * purchase.getPrice();
        	cell.setPhrase(new Phrase(String.valueOf(totalValue)));
        	table.addCell(cell);
        	total = total + totalValue;
        }
    }
     
    public void export(HttpServletResponse response, String memberId, String orderNumber,String txnDate) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(20);
        font.setColor(Color.BLUE);
        
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(14);
        font1.setColor(Color.BLACK);
         
		ClassPathResource imgResource= new ClassPathResource("src/main/resources/static/img/logo/logo.jpg");
		Image image1 = Image.getInstance(imgResource.getPath());	
		image1.setAlignment(Element.ALIGN_CENTER); 
		image1.scaleAbsolute(300, 150);
		document.add(image1);
        
        Paragraph p = new Paragraph("Thank you for your order!!", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        Paragraph p1 = new Paragraph("Order Number: "+ orderNumber, font1);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2 = new Paragraph("Member Id: "+memberId, font1);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        
        Paragraph txnDateTime = new Paragraph("Transaction Date: "+txnDate, font1);
        txnDateTime.setAlignment(Paragraph.ALIGN_LEFT);
        
        document.add(p1);
        document.add(p2);
        document.add(txnDateTime);
        
        Paragraph emptyPara = new Paragraph("");
        document.add(emptyPara);
         
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {4.5f, 1.5f, 1.5f, 1.5f, 1.5f,1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        
        Paragraph empty = new Paragraph("");
        document.add(empty);
        
        Paragraph purchaseTotal = new Paragraph("Purchase Total: "+ total, font1);
        purchaseTotal.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(purchaseTotal);
          
        document.close();
         
    }
}
