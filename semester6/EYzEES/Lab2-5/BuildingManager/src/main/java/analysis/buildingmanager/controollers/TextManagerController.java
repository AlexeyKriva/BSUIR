package analysis.buildingmanager.controollers;

import analysis.buildingmanager.models.*;

import static analysis.buildingmanager.models.Sentence1.*;

import analysis.buildingmanager.repositories.SemanticAnalysisRepository;
import analysis.buildingmanager.repositories.SentencesAnalysisRepository;
import analysis.buildingmanager.repositories.TextHullRepository;
import analysis.buildingmanager.services.FileService;
import analysis.buildingmanager.services.ScanTextService;
import analysis.buildingmanager.services.SearchService;
import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.semgraph.SemanticGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"markedHull", "sentences", "text1", "vertexOfAnalysis", "sentences1", "text2"})
public class TextManagerController {
    private static final String text333 = "Bill Gates, the co-founder of Microsoft Corporation, is one of the most influential figures in the technology industry and philanthropy.\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"He was born on October 28, 1955, in Seattle, Washington, Gates showed an early interest in computers and programming.\\n\" +\n" +
            "                \"\\n\" +\n" +
            "                \"Together with his childhood friend Paul Allen, Gates founded Microsoft in 1975, with the vision of putting a computer on every desk and in every home.";
    private FileService fileService;
    private ScanTextService scanTextService;
    private SearchService searchService;
    private TextHullRepository textHullRepository;
    private SentencesAnalysisRepository sentencesAnalysisRepository;
    private SemanticAnalysisRepository semanticAnalysisRepository;

    @Autowired
    public TextManagerController(FileService fileService, ScanTextService scanTextService,
                                 TextHullRepository textHullRepository, SearchService searchService,
                                 SentencesAnalysisRepository sentencesAnalysisRepository,
                                 SemanticAnalysisRepository semanticAnalysisRepository) {
        this.fileService = fileService;
        this.scanTextService = scanTextService;
        this.textHullRepository = textHullRepository;
        this.searchService = searchService;
        this.sentencesAnalysisRepository = sentencesAnalysisRepository;
        this.semanticAnalysisRepository = semanticAnalysisRepository;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/unmarkedHull")
    public String uploadFile(Model model) {
        String text = fileService.readFile();
        model.addAttribute("text", text);
        return "unmarked-hull";
    }

    @PostMapping("/updateTextHull")
    public String updateTextHull(Model model, @RequestParam String text) {
        fileService.saveTextInFile(text);
        List<Sentence1> sentences = scanTextService.getScannedText(text);
        textHullRepository.save(sentences);
        textHullRepository.deleteNull();
        model.addAttribute("markedHull", sentences);
        return "home";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        textHullRepository.deleteAll();
        return "home";
    }

    @GetMapping("/markedHull")
    public String viewMarkedHull(Model model) {
        model.addAttribute("markedHull", textHullRepository.select());
        return "marked-hull";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("parameters", textHullRepository.select());
        return "search";
    }

    @PostMapping("/search")
    public String searchInTextHull(Model model, @RequestParam(name = "query") List<String> query,
                                   @RequestParam(name = "enumValue", required = false) List<String> parameters) {
        List<Sentence1> sentences = searchService.search(query, parameters, textHullRepository.select());
        sentences = searchService.reduceAmountOfWords(query, parameters, sentences);
        model.addAttribute("sentences", sentences);
        model.addAttribute("query", query);
        model.addAttribute("parameters", parameters);
        return "results";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/analysis")
    public String analysis(Model model) {
        String text = fileService.readFile();
        List<Sentence1> sentences = scanTextService.getScannedText(text);
        model.addAttribute("sentences", sentences);
        return "show-text-to-analysis";
    }

    @PostMapping("/analysis")
    public String makeAnalysis(Model model, @RequestParam("sentenceNumber") int numberOfSentence,
                               @ModelAttribute("sentences") List<Sentence1> sentences) {
        String text = fromSentenceToStrings(sentences, numberOfSentence);

        SemanticGraph semanticGraphs = scanTextService.syntacticAnalysis(text);
        List<VertexOfAnalysis> vertexOfSyntatics = scanTextService.transferFromSemanticGraphToVertexOfSyntatic(semanticGraphs);

        model.addAttribute("text1", sentences.get(numberOfSentence - 1).getSentence());
        model.addAttribute("vertexOfAnalysis", vertexOfSyntatics);
        return "show-result-analysis";
    }

    @PostMapping("/saveAnalysis")
    public String saveAnalysis(@ModelAttribute("text1") String text1, @ModelAttribute("vertexOfAnalysis") List<VertexOfAnalysis> vertexOfAnalyses) {
        sentencesAnalysisRepository.save(vertexOfAnalyses, text1);
        return "home";
    }

    @GetMapping("/getAnalysis")
    public String selectAnalysis(Model model) {
        model.addAttribute("sentenceAnalyses", sentencesAnalysisRepository.select());
        return "results-of-analysis";
    }

    @GetMapping("/delete-sentence-analysis")
    public String deleteAnalysis() {
        sentencesAnalysisRepository.deleteAll();
        return "home";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/semanticAnalysis")
    public String makeSemanticAnalysis(Model model) {
        String text = fileService.readFile();
        List<Sentence1> sentences = scanTextService.getScannedText(text);
        model.addAttribute("sentences1", sentences);
        return "show-text-semantic-analysis";
    }

    @PostMapping("/semanticAnalysis")
    public String showSemanticAnalysis(Model model, @ModelAttribute("sentences1") List<Sentence1> sentences) {
        String text = fromSentencesToStrings(sentences);
        model.addAttribute("text2", text);
        System.out.println(text);
        Annotation document = scanTextService.getDocument(text333);
        System.out.println(document.get(CorefCoreAnnotations.CorefChainAnnotation.class).toString());
        List<NamedEntity> namedEntities = scanTextService.getNamedEntities(document);
        List<RelationInformation> relationInformations = scanTextService.getRelationTriple(document);
        SemanticAnaphora semanticAnaphora = scanTextService.getSemanticAnaphora(document);
        SemanticAnalysis semanticAnalysis = new SemanticAnalysis(namedEntities, relationInformations, semanticAnaphora);
        semanticAnalysisRepository.saveNamedEntity(namedEntities);
        semanticAnalysisRepository.saveRelationInformation(relationInformations);
        semanticAnalysisRepository.saveSemanticAnaphora(semanticAnaphora);
        model.addAttribute("semanticAnalysis", semanticAnalysis);
        return "show-result-semantic-analysis";
    }

    @PostMapping("/saveSemanticAnalysis")
    public String saveSemanticAnalysis() {
        return "home";
    }

    @GetMapping("/deleteSemanticAnalysis")
    public String deleteAllSemanticAnalysis() {
        semanticAnalysisRepository.deleteAll();
        return "home";
    }

    @GetMapping("/getSemanticAnalysis")
    public String getSemanticAnalysis(Model model) {
        model.addAttribute("namedEntities", semanticAnalysisRepository.getNamedEntity());
        model.addAttribute("relationInformations", semanticAnalysisRepository.getRelationInformation());
        model.addAttribute("simpleSemanticAnaphora", semanticAnalysisRepository.getSimpleSemanticAnaphora());
        return "results-of-semantic-analysis";
    }

    @GetMapping("/searchSemantic")
    public String getSearchSemantic(Model model, @RequestParam("search") String search) {
        model.addAttribute("namedEntities", semanticAnalysisRepository.getNamedEntityWithParameters(search));
        model.addAttribute("relationInformations", semanticAnalysisRepository.getRelationInformationWithParameters(search));
        model.addAttribute("simpleSemanticAnaphora", semanticAnalysisRepository.getSimpleSemanticAnaphoraWithParameters(search));
        return "results-of-semantic-analysis";
    }
}