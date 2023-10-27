package com.vnazarenko.pdfattr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private final ArrayList<File> listOfAllPDF = new ArrayList<>();

    public ArrayList<File> readFileNames(String pathToFolder) {
        File[] files = new File(pathToFolder).listFiles();

        if (files == null) return null;

        for (File file : files) {
            if (file.isDirectory()) {
                readFileNames(file.getAbsolutePath());
                continue;
            }
            String name = file.getName();
            String[] splitFileName = name.split("\\.");
            if (splitFileName[splitFileName.length - 1].equalsIgnoreCase("pdf")) {
                listOfAllPDF.add(file);
            }
        }
        return listOfAllPDF;
    }

    public ArrayList<String> prepareAttributes(File file) {
        ArrayList<String> result = new ArrayList<>();

        String[] string = file.getName().split("--");

        // Автор
        result.add(string[0].trim());

        //Название
        result.add(string[1].trim().substring(0, string[1].length() - 5));

        String[] parentFolder = file.getParent().split("/");
        String subject = parentFolder[parentFolder.length - 1];
        parentFolder[parentFolder.length - 1] = subject + " - DONE";

        // Тема
        result.add(subject);

        // Выходной каталог
        result.add(String.join("/", parentFolder));


        return result;
    }

    public void processFiles(File file, ArrayList<String> attributes) {
        if (new File(attributes.get(3)).mkdirs())
            System.out.println("Directory created successfully: " + attributes.get(3));

        File output = new File(attributes.get(3) + "/" + file.getName());

        try (PDDocument document = PDDocument.load(new FileInputStream(file.getAbsolutePath()));
             FileOutputStream fileOutputStream = new FileOutputStream(output.getAbsolutePath())) {

            setAttributes(document, attributes);
            saveCompressedPDF(document, fileOutputStream);

        } catch (Exception ignored) {
        }
    }

    public void setAttributes(PDDocument document, List<String> list) {
        PDDocumentInformation info = document.getDocumentInformation();

        info.setAuthor(list.get(0));
        info.setCreator(list.get(0));

        info.setTitle(list.get(1));

        info.setSubject(list.get(2));
        info.setKeywords(list.get(2));

        info.setProducer("NVN v.0.1");
    }

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
