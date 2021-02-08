package com.ss.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;
 
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.ss.app.entity.Address;
import com.ss.app.entity.Purchase;
 
 
public class OrderPDFExporter {
	
    private List<Purchase> purchaseList;
    private Address address;
    private Double total = 0.0;
     
    public OrderPDFExporter(List<Purchase> purchaseList, Address address) {
        this.purchaseList = purchaseList;
        this.address = address;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
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
        for (Purchase purchase : purchaseList) {
        	PdfPCell cell = new PdfPCell();
        	cell.setPhrase(new Phrase(purchase.getProduct().getProdDesc()));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getQuantity())));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getProduct().getBvPrice())));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getAmount())));
        	table.addCell(cell);
        	double totalValue = purchase.getQuantity() * purchase.getAmount();
        	cell.setPhrase(new Phrase(String.valueOf(totalValue)));
        	table.addCell(cell);
        	total = total + totalValue;
        }
    }
     
    public void export(HttpServletResponse response, String memberId, String orderNumber) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(20);
        font.setColor(Color.BLUE);
        
        Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font1.setSize(14);
        font1.setColor(Color.BLACK);
         
//        Image image1 = Image.getInstance("/img/logo/logo.jpg");
//        image1.setAlignment(Element.ALIGN_CENTER);
//        image1.scaleAbsolute(450, 250);
//        //Add to document
//        document.add(image1);
        
        Paragraph p = new Paragraph("Thank you for your order!!", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        
        Paragraph p1 = new Paragraph("Order Number: "+ orderNumber, font1);
        p1.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p2 = new Paragraph("Member Id: "+memberId, font1);
        p2.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p3 = new Paragraph("Address:" + address.getAddressLineOne() + ", " + address.getAddressLineTwo(), font1);
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p4 = new Paragraph("-         " + address.getCity() + ", " + address.getPostalCode(), font1);
        p4.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p5 = new Paragraph("-         " + address.getState(), font1);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
         
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(p4);
        document.add(p5);
         
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {4.5f, 1.5f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        
        Paragraph empty = new Paragraph("");
        document.add(empty);
        Paragraph p6 = new Paragraph("Purchase Total: "+ total, font1);
        p6.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(p6);
        
         
        document.close();
         
    }
}
