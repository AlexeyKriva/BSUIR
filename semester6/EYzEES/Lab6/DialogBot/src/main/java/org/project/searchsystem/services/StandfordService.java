package org.project.searchsystem.services;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import org.project.searchsystem.models.nlpModels.*;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.project.searchsystem.models.nlpModels.AbbreviationRelation.ABBREVIATION_AND_EXPLANATION_OF_RELATIONS;

@Service
public class StandfordService {
    private static final AbbreviationDictionary ABBRIVIATURE_DICTIONARY = new AbbreviationDictionary();

    public List<Sentence> getScannedText(String text) {
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

    private List<Sentence> getSentences(CoreDocument document) {
        List<Sentence> sentences = new ArrayList<>();
        for (CoreSentence sentence : document.sentences()) {
            sentences.add(new Sentence(sentence.text(), getTokens(document, sentence)));
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
