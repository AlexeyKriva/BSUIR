package org.project.searchsystem.services;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class ScanTextFromFileService {
    private static final String PATH_TO_FILES = "/Users/aliaksei/Desktop/BSUIR/CourseProject/SearchSystem/text-Hull.docx";

    public String readFile() {
        File file = new File(PATH_TO_FILES);
        return readFileToString(file);
    }

    private String readFileToString(File file) {
        try (InputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            return extractor.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
