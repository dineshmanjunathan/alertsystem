package com.ss.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
 
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.core.io.ClassPathResource;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.ss.app.entity.Address;
import com.ss.app.entity.Purchase;
 
 
public class OrderPDFExporter {
	
    private List<Purchase> purchaseList;
    private Address address;
    private Double total = 0.0;
    private Long redeemPoints = 0L;
    private String paymentType;
     
    public OrderPDFExporter(List<Purchase> purchaseList, Address address) {
        this.purchaseList = purchaseList;
        this.address = address;
        if(!CollectionUtils.isEmpty(purchaseList)) {
        	redeemPoints = purchaseList.get(0).getRedeemedPoints();
        	paymentType = purchaseList.get(0).getPaymentType();
        }
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
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getProduct().getBvPrice()* purchase.getQuantity())));
        	table.addCell(cell);
        	cell.setPhrase(new Phrase(String.valueOf(purchase.getAmount())));
        	table.addCell(cell);
        	double totalValue = purchase.getQuantity() * purchase.getAmount();
        	if(redeemPoints !=null && redeemPoints > 0) {
        		totalValue = totalValue - redeemPoints;
        	}
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
        
        Paragraph p3 = new Paragraph("Address:" ,font1);
        Paragraph addressPara = new Paragraph(address.getAddressLineOne() + ",", font1);
        addressPara.setAlignment(Paragraph.ALIGN_LEFT);
        
        p3.setAlignment(Paragraph.ALIGN_LEFT);
        
        Paragraph p4=null;
        if(address.getAddressLineTwo()!=null && !address.getAddressLineTwo().trim().isEmpty()) {
        	 p4 = new Paragraph(address.getAddressLineTwo()+",", font1);
             p4.setAlignment(Paragraph.ALIGN_LEFT);
        }
       
        Paragraph p5 = new Paragraph(address.getCity() + "-" + address.getPostalCode()+",", font1);
        p5.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph p6 = new Paragraph(address.getState(), font1);
        p6.setAlignment(Paragraph.ALIGN_LEFT);
         
        document.add(p1);
        document.add(p2);
        document.add(p3);
        document.add(addressPara);
        if(address.getAddressLineTwo()!=null && !address.getAddressLineTwo().trim().isEmpty()) {
        	document.add(p4);
        }
        document.add(p5);
        document.add(p6);
        
        Paragraph emptyPara = new Paragraph("");
        document.add(emptyPara);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {4.5f, 1.5f, 1.5f, 1.5f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        
        Paragraph empty = new Paragraph("");
        document.add(empty);
        
        Paragraph p7 = new Paragraph("Payment Type: "+ paymentType, font1);
        p7.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(p7);
        
        if(redeemPoints!=null && redeemPoints > 0) {
        	 Paragraph p10 = new Paragraph("Points Redeemed: -"+redeemPoints , font1);
             p10.setAlignment(Paragraph.ALIGN_RIGHT);
             document.add(p10);
        }
        Paragraph p8 = new Paragraph("Purchase Total: "+ total, font1);
        p8.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(p8);
          
        document.close();
         
    }
}
