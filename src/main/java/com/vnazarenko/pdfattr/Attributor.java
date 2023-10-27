package com.vnazarenko.pdfattr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import java.util.List;

public class Attributor {

    public void setAttributes(PDDocument document, List<String> list) {
        PDDocumentInformation info = document.getDocumentInformation();

        info.setTitle(list.get(0));
        info.setAuthor(list.get(1));
        info.setSubject(list.get(2));
        info.setKeywords(list.get(3));

        info.setProducer("NVN v.0.1");
    }
}
