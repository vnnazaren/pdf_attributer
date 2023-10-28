package com.vnazarenko.pdfattr;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Processor {
    public void processFiles(File file, ArrayList<String> attributes) {
        if (new File(attributes.get(3)).mkdirs())
            System.out.println("Directory created successfully: " + attributes.get(3));

        File output = new File(attributes.get(3) + "/" + file.getName());

        try (PDDocument document = PDDocument.load(new FileInputStream(file.getAbsolutePath()));
             FileOutputStream fileOutputStream = new FileOutputStream(output.getAbsolutePath())) {

            new Attributor().setAttributes(document, attributes);
            new Compressor().saveCompressedPDF(document, fileOutputStream);

        } catch (Exception ignored) {
        }
    }
}
