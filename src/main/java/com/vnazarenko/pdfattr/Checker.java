package com.vnazarenko.pdfattr;

import java.io.File;
import java.util.ArrayList;

public class Checker {

    public void checkDoubleDashes(String folderPath){
        ArrayList<File> files = new Loader().makeListPDF(folderPath);

        for (File file : files){
            String [] separatedFileName = file.getName().split(" -- ");
            if (separatedFileName.length != 2) {
                System.out.println();
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("!      " + file.getName());
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println();
            }
        }
    }
}
