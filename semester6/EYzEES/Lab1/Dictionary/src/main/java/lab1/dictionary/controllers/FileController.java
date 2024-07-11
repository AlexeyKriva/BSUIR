package lab1.dictionary.controllers;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import lab1.dictionary.models.*;
import lab1.dictionary.repository.ParsedWordRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

@Controller
@Slf4j
@RequestMapping("/file")
public class FileController {
    private AbbriviatureDictionary abbriviatureDictionary;
    private ParsedWordRepository parsedWordRepository;

    @Autowired
    public FileController(AbbriviatureDictionary abbriviatureDictionary, ParsedWordRepository parsedWordRepository) {
        this.abbriviatureDictionary = abbriviatureDictionary;
        this.parsedWordRepository = parsedWordRepository;
    }

    @GetMapping("/upload")
    public String uploadFileView(){
        return "upload-file";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model){
        String fileContents = readFile(file);

        Set<ParsedWord> scannedText = getScannedText(fileContents);
        System.out.println(scannedText);
        parsedWordRepository.save(scannedText);
        model.addAttribute("originalText", fileContents);
        model.addAttribute("scannedText", scannedText);
        return "sort-dictionary";
    }

    @GetMapping("/sort/dictionary")
    public String viewSortDictionary(){
        return "sort-dictionary";
    }

    @GetMapping("/instruction")
    public String viewInstruction() { return "instruction"; }

    @GetMapping("/watch-dictionary")
    public String watch(Model model) {
        model.addAttribute("dictionary", parsedWordRepository.select());
        return "watch-dictionary";
    }

    @PostMapping("/watch-dictionary")
    public String watchSort(Model model, @RequestParam("text") String text) {
        if (checkNumbers(text)) model.addAttribute("dictionary", parsedWordRepository.selectDefiniteWithNumber(Integer.parseInt(text)));
        else if (text.equals("")) model.addAttribute("dictionary", parsedWordRepository.select());
        else model.addAttribute("dictionary", parsedWordRepository.selectDefiniteWithWord(text));
        return "watch-dictionary";
    }

    @PostMapping("/update")
    public String updateDataBase(@RequestParam("scannedText") List<ParsedWord> updateContentOfWords){
        return "home";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    private boolean checkNumbers(String text){
        for (int i = 0; i < text.length(); i++){
            if (!Character.isDigit(text.charAt(i))) return false;
        }
        return text.equals("") ? false : true;
    }

    private String readFile(MultipartFile file){
        String fileContents = null;
        if (!file.isEmpty()) {
            try {
                fileContents = readFileToStringPDF(file);
                return fileContents.trim();
            } catch (Exception e) {
                log.error(String.valueOf(e));
            }
        } else {
            return null;
        }
        return fileContents;
    }

    private String readFileToString(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             XWPFDocument document = new XWPFDocument(is);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            return extractor.getText();
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }

        return null;
    }

    private String readFileToStringPDF(MultipartFile file) {
        try {
            File file1 = File.createTempFile("temp", "pdf");
            file.transferTo(file1);
            PDDocument document = PDDocument.load(file1);

            PDFTextStripper pdfStripper = new PDFTextStripper();

            // Извлечение текста из PDF
            String text = pdfStripper.getText(document);

            // Закрытие документа
            document.close();

            return text;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Set<ParsedWord> getScannedText(String text){
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        Set<ParsedWord> words = parseDocument(document);
        return words;
    }

    private Set<ParsedWord> parseDocument(CoreDocument coreDocument){
        Set<ParsedWord> scannedText = new TreeSet<>();
        for (int index = 0; index < coreDocument.sentences().size(); index++) {
            CoreSentence sentence = coreDocument.sentences().get(index);

            List<CoreLabel> words = sentence.tokens();
            Map<String, Integer> wordFrequencies = countWordFrequency(words);

            List<String> posTags = sentence.posTags();

            for (int tokenIndex = 0; tokenIndex < words.size(); tokenIndex++) {
                String currentWord = words.get(tokenIndex).word();
                int wordFrequency = wordFrequencies.get(currentWord);
                String parameters = fromAbbreviationToFullForm(posTags.get(tokenIndex));
                String lemma = words.get(tokenIndex).lemma();

                boolean isLemma = currentWord.toLowerCase().equals(lemma);

                scannedText.add(new ParsedWord(currentWord, wordFrequency, parameters, isLemma));
            }
        }
        return scannedText;
    }

    private Map<String, Integer> countWordFrequency(List<CoreLabel> words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (CoreLabel token : words) {
            String word = token.word();
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        return wordFrequency;
    }

    private String fromAbbreviationToFullForm(String abbreviation){
        return abbriviatureDictionary.getAbbreviationsAndFullWords().get(abbreviation);
    }




    @GetMapping("/create")
    public String createFileForm(){
        return "create-file";
    }

    @PostMapping("/create")
    public String createFile(@RequestParam("fileContent") String fileContent, @RequestParam("fileName") String fileName, @RequestParam("fileType") String fileType){
        createNewFile(fileContent, fileName, fileType);
        return "home";
    }

    private XWPFDocument createNewFile(String text, String fileName, String fileType){
        XWPFDocument document = null;
        try {
            document = new XWPFDocument();

            XWPFParagraph paragraph = document.createParagraph();

            XWPFRun run = paragraph.createRun();
            run.setText(text);

            String desktopPath = System.getProperty("user.home") + "/Desktop/";
            String fullFileName = fileName + "." + fileType;
            String filePath = desktopPath + fullFileName;

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return document;
    }
}