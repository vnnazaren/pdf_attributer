package com.vnazarenko.pdfattr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;
import java.io.OutputStream;

public class Compressor {

    public void saveCompressedPDF(PDDocument document, OutputStream os) throws IOException {
        PDDocument outDoc = new PDDocument();
        outDoc.setDocumentInformation(document.getDocumentInformation());
        for (PDPage srcPage : document.getPages()) {
            new PDPageContentStream(outDoc, srcPage, PDPageContentStream.AppendMode.APPEND, true).close();
            outDoc.addPage(srcPage);
        }
        outDoc.save(os);
        outDoc.close();
    }
}