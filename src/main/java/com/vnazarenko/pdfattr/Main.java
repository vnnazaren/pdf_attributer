package com.vnazarenko.pdfattr;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try (PDDocument document = PDDocument.load(new FileInputStream("srcFile.pdf"))) {
            ArrayList<String> attributes = new ArrayList<>(Arrays.asList("Алгоритмы на практике", "Даниэль Зингаро", "Алгоритмы", "Алгоритмы"));

            new Attributor().setAttributes(document, attributes);
            new Compressor().saveCompressedPDF(document, new FileOutputStream("dstFileCompressed.pdf"));

        } catch (Exception ignored) {
        }
    }


}