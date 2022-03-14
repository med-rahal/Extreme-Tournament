/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extreme.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import extreme.model.Produit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import extreme.services.Produitservices;

/**
 *
 * @author dell
 */
class ServicePdfA {
    
    public void liste_ProduitPDF() throws FileNotFoundException, DocumentException, SQLException {

        Produitservices ps = new Produitservices();
        String message = "--------**Gestion des produits**-------- \n\n";
        
        String file_name = "src/liste_produit.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Produit> Produit = ps.afficherPr();
        PdfPTable table = new PdfPTable(7);

        PdfPCell cl = new PdfPCell(new Phrase("refProd"));
        table.addCell(cl);
        PdfPCell cl1 = new PdfPCell(new Phrase("nomProd"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("prix"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("TotalEnStock"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Descriptif"));
        table.addCell(cl4);
        PdfPCell cl5 = new PdfPCell(new Phrase("CategorieProd"));
        table.addCell(cl5);
        PdfPCell cl6 = new PdfPCell(new Phrase("disponibilite"));
        table.addCell(cl6);

        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Produit.size(); i++) {
            table.addCell("" + Produit.get(i).getRefProd());
            table.addCell("" + Produit.get(i).getNomProd());
            table.addCell("" + Produit.get(i).getPrix());
            table.addCell("" + Produit.get(i).getTotalEnStock());
            table.addCell("" + Produit.get(i).getDescriptif());
            table.addCell("" + Produit.get(i).getCategorieProd());
            table.addCell("" + Produit.get(i).getDisponibilite());

        }
        document.add(table);

        document.close();

    }
}
