package com.vnazarenko.pdfattr;

import java.io.File;
import java.util.ArrayList;

public class Loader {
    private final ArrayList<File> listOfAllPDF = new ArrayList<>();

    public ArrayList<File> makeListPDF(String pathToFolder) {
        File[] files = new File(pathToFolder).listFiles();

        if (files == null) return null;

        for (File file : files) {
            if (file.isDirectory()) {
                makeListPDF(file.getAbsolutePath());
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
}
