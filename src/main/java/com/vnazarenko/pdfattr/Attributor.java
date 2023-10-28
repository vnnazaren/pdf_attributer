package com.vnazarenko.pdfattr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Attributor {

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

    public void setAttributes(PDDocument document, List<String> list) {
        PDDocumentInformation info = document.getDocumentInformation();

        info.setAuthor(list.get(0));
        info.setCreator(list.get(0));

        info.setTitle(list.get(1));

        info.setSubject(list.get(2));
        info.setKeywords(list.get(2));

        info.setProducer("NVN v.0.1");

    }
}