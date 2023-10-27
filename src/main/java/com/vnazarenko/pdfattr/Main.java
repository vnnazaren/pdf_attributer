package com.vnazarenko.pdfattr;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String pathToFolder = "/Users/vnazaren/Отладка/iBooks - New - Debug - PDF/";
        Manager manager = new Manager();

//        ArrayList<File> arrayList = manager.readFileNames(args[0]);
        ArrayList<File> arrayList = manager.readFileNames(pathToFolder);

        for (File file : arrayList) {
            ArrayList<String> attributes = manager.prepareAttributes(file);
            manager.processFiles(file, attributes);
        }
    }


}