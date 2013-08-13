/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exadel.borsch.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author Fedor
 */
public final class PDFCreator {

    private PDFCreator() {
    }

    public static void create(String src, String fileName) {
        //path for the PDF file to be generated
        PdfWriter pdfWriter = null;
        //create a new document
        Document document = new Document();

        try {

            //get Instance of the PDFWriter
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            //document header attributes
            document.addAuthor("betterThanZero");
            document.addCreationDate();
            document.addProducer();
            document.addCreator("MySampleCode.com");
            document.addTitle("Demo for iText XMLWorker");
            document.setPageSize(PageSize.LETTER);
            document.open();
            InputStreamReader fis = new InputStreamReader(new URL(src).openStream());
            XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
            worker.parseXHtml(pdfWriter, document, fis);
            document.close();
            pdfWriter.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
