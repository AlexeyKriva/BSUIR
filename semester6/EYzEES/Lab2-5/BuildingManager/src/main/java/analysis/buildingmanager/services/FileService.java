package analysis.buildingmanager.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileService {
    private static final String PATH_TO_FILES = "/Users/aliaksei/Desktop/BSUIR/EYzEES/Lab2/TextsAboutAnimals/textHull.docx";

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

    public void saveTextInFile(String text){
        try {
            Files.deleteIfExists(Paths.get(PATH_TO_FILES));
            XWPFDocument document = new XWPFDocument();
            document.createParagraph().createRun().setText(text);
            try (FileOutputStream out = new FileOutputStream(PATH_TO_FILES)) {
                document.write(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
