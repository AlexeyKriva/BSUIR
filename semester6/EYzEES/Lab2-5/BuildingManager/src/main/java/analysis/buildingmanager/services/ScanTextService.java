package analysis.buildingmanager.services;

import analysis.buildingmanager.models.*;

import static analysis.buildingmanager.models.AbbreviationRelation.*;

import analysis.buildingmanager.models.Pair;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ie.util.RelationTriple;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.simple.Sentence;

import java.util.*;

@Service
public class ScanTextService {
    private static final AbbreviationDictionary ABBRIVIATURE_DICTIONARY = new AbbreviationDictionary();
    private static final int SKIP = 2;
    private static final char START_OBJECT_AND_PRONOUN_SYMBOL = '"';
    private static final char FINISH_OBJECT_AND_PRONOUN_SYMBOL1 = ',';
    private static final char FINISH_OBJECT_AND_PRONOUN_SYMBOL2 = ']';
    public List<Sentence1> getScannedText(String text) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return getSentences(document);
    }

    public SemanticGraph syntacticAnalysis(String text) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);

        return getTree(document);
    }

    public SemanticAnalysis semanticAnalysis(String text) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, depparse, coref, relation");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        return new SemanticAnalysis(getNamedEntities(document),
                getRelationTriple(document),
                getSemanticAnaphora(document));
    }

    public Annotation getDocument(String text) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, coref");

        // Создание объекта Pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);

        // Текст для анализа
        String text333 = "Bill Gates, the co-founder of Microsoft Corporation, is one of the most influential figures in the technology industry and philanthropy.\n" +
                "\n" +
                "He was born on October 28, 1955, in Seattle, Washington, Gates showed an early interest in computers and programming.\n" +
                "\n" +
                "Together with his childhood friend Paul Allen, Gates founded Microsoft in 1975, with the vision of putting a computer on every desk and in every home.";

        // Создание аннотации на основе текста
        Annotation document = new Annotation(text333);

        // Запуск процесса аннотации
        pipeline.annotate(document);

        return document;
    }

    public SemanticAnaphora getSemanticAnaphora(Annotation document) {
        String semanticAnaphora = document.get(CorefCoreAnnotations.CorefChainAnnotation.class).toString();
        return parseSemanticAnaphora(semanticAnaphora);
    }

    private SemanticAnaphora parseSemanticAnaphora(String semanticAnaphora) {
        List<List<String>> objectsOfEntities = new ArrayList<>();
        List<List<Integer>> numbersOfSentences = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < semanticAnaphora.length(); i++) {
            if (semanticAnaphora.charAt(i) == '"') {
                objectsOfEntities.add(new ArrayList<>());
                numbersOfSentences.add(new ArrayList<>());
                boolean key = true;
                while (key) {
                    StringBuilder objectOfEntity = new StringBuilder();
                    StringBuilder numberOfSentence = new StringBuilder();
                    int endIndex = i;
                    for (int j = i + 1; j < semanticAnaphora.length(); j++) {
                        if (semanticAnaphora.charAt(j) == '"') {
                            endIndex = j;
                            break;
                        }
                        objectOfEntity.append(semanticAnaphora.charAt(j));
                    }
                    for (int j = endIndex; j < semanticAnaphora.length(); j++) {
                        if (semanticAnaphora.charAt(j) == ',') {
                            i = j + 2;
                            break;
                        }
                        if (semanticAnaphora.charAt(j) == ']') {
                            i = j + 2;
                            key = false;
                            break;
                        }
                        if (Character.isDigit(semanticAnaphora.charAt(j))) {
                            numberOfSentence.append(semanticAnaphora.charAt(j));
                        }
                    }
                    objectsOfEntities.get(index).add(objectOfEntity.toString());
                    numbersOfSentences.get(index).add(Integer.parseInt(numberOfSentence.toString()));
                }
                index++;
            }
        }

        return new SemanticAnaphora(objectsOfEntities, numbersOfSentences);
    }

    public List<NamedEntity> getNamedEntities(Annotation document) {
        List<NamedEntity> namedEntities = new ArrayList<>();
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreMap entity : sentence.get(CoreAnnotations.MentionsAnnotation.class)) {
                namedEntities.add(new NamedEntity(entity.get(CoreAnnotations.EntityTypeAnnotation.class),
                        entity.get(CoreAnnotations.TextAnnotation.class)));
            }
        }

        return namedEntities;
    }

    public List<RelationInformation> getRelationTriple(Annotation document) {
        List<RelationInformation> relationInformations = new ArrayList<>();
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            Sentence sent = new Sentence(sentence.toString());
            Collection<RelationTriple> triples = sent.openieTriples();
            for (RelationTriple triple : triples) {
                relationInformations.add(new RelationInformation(triple.confidence,
                        triple.subjectLemmaGloss(), triple.relationLemmaGloss(),
                        triple.objectLemmaGloss()));
            }
        }

        return relationInformations;
    }

    private SemanticGraph getTree(CoreDocument coreDocument) {
        return coreDocument.sentences().get(0).dependencyParse();
    }

    public List<VertexOfAnalysis> transferFromSemanticGraphToVertexOfSyntatic(SemanticGraph semanticGraph) {
        return processGraph(semanticGraph);
    }
    public static List<VertexOfAnalysis> processGraph(SemanticGraph graph) {
        List<VertexOfAnalysis> vertexOfAnalyses = new ArrayList<>();
        Deque<IndexedWord> stack = new ArrayDeque<>();
        Set<IndexedWord> visited = new HashSet<>();

        for (IndexedWord root : graph.getRoots()) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            IndexedWord current = stack.pop();

            if (!visited.contains(current)) {
                List<Pair> pairs = new ArrayList<>();
                for (SemanticGraphEdge edge : graph.outgoingEdgeList(current)) {
                    IndexedWord dependent = edge.getDependent();
                    stack.push(dependent);
                    pairs.add(new Pair(dependent.word(), ABBREVIATION_AND_EXPLANATION_OF_RELATIONS.get(edge.getRelation().toString())));
                }
                if (!pairs.isEmpty()) {
                    vertexOfAnalyses.add(new VertexOfAnalysis(current.word(), pairs));
                }

                visited.add(current);
            }
        }

        return vertexOfAnalyses;
    }

    private static boolean isIncluded(String word, List<VertexOfAnalysis> vertexOfSyntatics) {
        for (VertexOfAnalysis vertexOfSyntatic : vertexOfSyntatics) {
            if (word.equals(vertexOfSyntatic.getWord())) {
                return true;
            }
        }

        return false;
    }

    private List<Sentence1> getSentences(CoreDocument document) {
        List<Sentence1> sentences = new ArrayList<>();
        for (CoreSentence sentence : document.sentences()) {
            sentences.add(new Sentence1(sentence.text(), getTokens(document, sentence)));
        }

        return sentences;
    }

    private List<ParsedWord> getTokens(CoreDocument coreDocument, CoreSentence coreSentence) {
        List<ParsedWord> scannedText = new ArrayList<>();
        List<CoreLabel> words = coreSentence.tokens();
        List<String> posTags = coreSentence.posTags();

        for (int tokenIndex = 0; tokenIndex < words.size(); tokenIndex++) {
            String currentWord = words.get(tokenIndex).word();
            int wordFrequency = countWordFrequency(coreDocument, currentWord);
            String parameters = fromAbbreviationToFullForm(posTags.get(tokenIndex));
            String lemma = words.get(tokenIndex).lemma();

            scannedText.add(new ParsedWord(currentWord, lemma, wordFrequency, parameters));
        }

        return scannedText;
    }

    private int countWordFrequency(CoreDocument coreDocument, String word) {
        int wordFrequency = 0;

        for (CoreSentence sentence : coreDocument.sentences()) {
            List<CoreLabel> words = sentence.tokens();
            for (CoreLabel coreLabel : words) {
                if (word.equals(coreLabel.word())) {
                    wordFrequency++;
                }
            }
        }

        return wordFrequency;
    }

    private String fromAbbreviationToFullForm(String abbreviation) {
        return ABBRIVIATURE_DICTIONARY.getAbbreviationsAndFullWords().get(abbreviation);
    }
}
