package com.vnazarenko.pdfattr;

import java.io.File;
import java.util.ArrayList;

public class Manager {

    public void startProcess(String pathToFolder) {
        Attributor attributor = new Attributor();
        Processor processor = new Processor();

        ArrayList<File> arrayList = new Loader().makeListPDF(pathToFolder);

        for (File file : arrayList) {
            ArrayList<String> attributes = attributor.prepareAttributes(file);
            processor.processFiles(file, attributes);
        }
    }

    public void checkFiles(String folderPath) {
        new Checker().checkDoubleDashes(folderPath);
    }
}
