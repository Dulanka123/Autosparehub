package lk.ijse.backend.util;

import lk.ijse.backend.DTO.ItemDTO;
import lk.ijse.backend.DTO.PlacePaymentDTO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class InvoiceGenerator {
    private static final DecimalFormat MONEY = new DecimalFormat("0.00");

    public static byte[] generateInvoicePDF(PlacePaymentDTO dto, long orderId) {
        if (dto == null) throw new IllegalArgumentException("PlacePaymentDTO cannot be null");
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4, 36, 36, 48, 36);
            PdfWriter.getInstance(document, baos);
            document.open();

            addLogo(document);
            addHeader(document);
            addOrderInfo(document, dto, orderId);
            addItemsTable(document, dto);
            addTotals(document, dto);
            addFooterMessage(document, dto);

            document.close();
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate invoice PDF", e);
        }
    }

    private static void addLogo(Document document) {
        try {
            Image logo = null;
            URL resource = Thread.currentThread().getContextClassLoader().getResource("static/images/logo.png");
            if (resource != null) {
                logo = Image.getInstance(resource);
            } else {
                File fallback = new File("../frontEnd/uren/assets/images/menu/logo/Parts LK Logo 2 Parts-2.png");
                if (fallback.exists()) {
                    logo = Image.getInstance(fallback.getAbsolutePath());
                }
            }
            if (logo != null) {
                logo.scaleToFit(180, 70);
                logo.setAlignment(Element.ALIGN_CENTER);
                document.add(logo);
                document.add(Chunk.NEWLINE);
            }
        } catch (Exception ignored) {
        }
    }

    private static void addHeader(Document document) throws DocumentException {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD, new BaseColor(34, 102, 221));
        Paragraph header = new Paragraph("INVOICE", headerFont);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(Chunk.NEWLINE);
    }

    private static void addOrderInfo(Document document, PlacePaymentDTO dto, long orderId) throws DocumentException {
        Font label = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
        Font normal = new Font(Font.FontFamily.HELVETICA, 11);
        document.add(makeLine("Order ID: ", String.valueOf(orderId), label, normal));
        document.add(makeLine("Customer Name: ", dto.getCustomerName(), label, normal));
        document.add(makeLine("Email: ", dto.getCustomerEmail(), label, normal));
        document.add(makeLine("Address: ", dto.getCustomerAddress() + ", " + dto.getCity() + ", " + dto.getCountry(), label, normal));
        document.add(makeLine("Payment Method: ", dto.getPaymentMethod(), label, normal));
        document.add(Chunk.NEWLINE);
    }

    private static Paragraph makeLine(String label, String value, Font labelFont, Font valueFont) {
        Phrase phrase = new Phrase();
        phrase.add(new Chunk(label, labelFont));
        phrase.add(new Chunk(value == null ? "-" : value, valueFont));
        Paragraph p = new Paragraph(phrase);
        p.setSpacingAfter(2f);
        return p;
    }

    private static void addItemsTable(Document document, PlacePaymentDTO dto) throws DocumentException {
        PdfPTable table = new PdfPTable(new float[]{4f, 2f, 2f, 2f});
        table.setWidthPercentage(100f);
        addTableHeader(table, "Item", "Price", "Qty", "Total");
        Font cellFont = new Font(Font.FontFamily.HELVETICA, 10);
        if (dto.getItemDTOS() != null) {
            for (ItemDTO item : dto.getItemDTOS()) {
                double lineTotal = item.getItemPrice() * item.getItemQty();
                table.addCell(makeCell(item.getItemName(), cellFont, Element.ALIGN_LEFT));
                table.addCell(makeCell(MONEY.format(item.getItemPrice()), cellFont, Element.ALIGN_RIGHT));
                table.addCell(makeCell(String.valueOf(item.getItemQty()), cellFont, Element.ALIGN_CENTER));
                table.addCell(makeCell(MONEY.format(lineTotal), cellFont, Element.ALIGN_RIGHT));
            }
        }
        document.add(table);
        document.add(Chunk.NEWLINE);
    }

    private static void addTableHeader(PdfPTable table, String... headers) {
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.WHITE);
        BaseColor bg = new BaseColor(102, 126, 234);
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(6f);
            cell.setBackgroundColor(bg);
            cell.setBorderColor(new BaseColor(230, 230, 230));
            table.addCell(cell);
        }
    }

    private static PdfPCell makeCell(String text, Font font, int align) {
        PdfPCell cell = new PdfPCell(new Phrase(text == null ? "-" : text, font));
        cell.setHorizontalAlignment(align);
        cell.setPadding(5f);
        cell.setBorderColor(new BaseColor(235, 235, 235));
        return cell;
    }

    private static void addTotals(Document document, PlacePaymentDTO dto) throws DocumentException {
        double grandTotal = 0;
        if (dto.getItemDTOS() != null) {
            for (ItemDTO item : dto.getItemDTOS()) {
                grandTotal += item.getItemPrice() * item.getItemQty();
            }
        }
        Font totalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, new BaseColor(34, 102, 221));
        Paragraph total = new Paragraph("Total Amount: Rs. " + MONEY.format(grandTotal), totalFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);
        document.add(Chunk.NEWLINE);
    }

    private static void addFooterMessage(Document document, PlacePaymentDTO dto) throws DocumentException {
        String footerMessage;
        if (dto.getPaymentMethod() != null && dto.getPaymentMethod().equalsIgnoreCase("Direct Bank Transfer")) {
            footerMessage = "Make your payment directly into our bank account.\n" +
                    "Please use your Order ID as the payment reference.\n" +
                    "Your order won't be shipped until the funds have cleared in our account.";
        } else {
            footerMessage = "Thank you for your purchase!";
        }
        Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.DARK_GRAY);
        Paragraph p = new Paragraph(footerMessage, footerFont);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
    }
}
