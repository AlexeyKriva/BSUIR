package org.example.translator.services;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import org.example.translator.entities.AbbreviationVocabulary;
import org.example.translator.entities.GrammarInfo;
import org.example.translator.entities.VocabularyPair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TextProcessorService {
    StanfordCoreNLP pipeline;

    public String getTree(String text) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);

        CoreDocument document = new CoreDocument(text);

        pipeline.annotate(document);

        List<Tree> trees = new ArrayList<>();

        for (CoreMap sentence : document.annotation().get(CoreAnnotations.SentencesAnnotation.class)) {
            Tree syntaxTree = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);

            trees.add(syntaxTree);
        }

        return treeToHtml(trees.get(0));
    }

    public String treeToHtml(Tree tree) {
        if (tree == null) {
            return "<em>Не указано</em>";
        }
        StringBuilder html = new StringBuilder();
        html.append("<ul>");
        treeToHtmlHelper(tree, html);
        html.append("</ul>");
        return html.toString();
    }

    private void treeToHtmlHelper(Tree tree, StringBuilder html) {
        if (tree.isLeaf()) {
            html.append("<li>").append(tree.value()).append("</li>");
            return;
        }
        html.append("<li>").append(tree.value() != null ? tree.value() : "ROOT");
        html.append("<ul>");
        for (Tree child : tree.children()) {
            treeToHtmlHelper(child, html);
        }
        html.append("</ul>");
        html.append("</li>");
    }

    public Integer numberOfWordsInText(List<String> words) {
        Integer numberOfWords = 0;

        for (String token : words) {
            if (!isBannedWord(token)) {
                numberOfWords++;
            }
        }

        return numberOfWords;
    }

    public Boolean isBannedWord(String word) {
        return word.equals(".") || word.equals(",") || word.equals(" ") || word.equals("!") || word.equals("?") ||
                word.equals(";") || word.equals(":") || word.equals("\"") || word.equals("(") || word.equals(")") ||
                word.equals("-");
    }

    public List<String> tokenize(String source) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);

        CoreDocument sourceDocument = new CoreDocument(source);

        pipeline.annotate(sourceDocument);

        List<String> words = new ArrayList<>();

        for (int i = 0; i < sourceDocument.tokens().size(); i++) {
            String word = sourceDocument.tokens().get(i).word();

            words.add(word);
        }

        return words;
    }

    public List<VocabularyPair> getVocabularyPairs(String source, String target, String from, Long requestId) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);

        CoreDocument sourceDocument = new CoreDocument(source);
        CoreDocument targetDocument = new CoreDocument(target);

        pipeline.annotate(sourceDocument);
        pipeline.annotate(targetDocument);

        Set<VocabularyPair> vocabularyPairs = new HashSet<>();

        if (sourceDocument.tokens().size() >= targetDocument.tokens().size()) {
            for (int i = 0; i < targetDocument.tokens().size(); i++) {
                String word = sourceDocument.tokens().get(i).word();
                String parallelWord = targetDocument.tokens().get(i).word();

                if (!isBannedWord(word)) {
                    Integer frequencyForWord = calcFrequency(word, sourceDocument.tokens());
                    Integer frequencyForParallelWord = calcFrequency(parallelWord, targetDocument.tokens());

                    if (from.equals("english")) {
                        vocabularyPairs.add(VocabularyPair.builder()
                                .requestId(requestId)
                                .enWord(word.toLowerCase())
                                .deWord(parallelWord.toLowerCase())
                                .frequencyEn(frequencyForWord)
                                .frequencyDe(frequencyForParallelWord)
                                .build());
                    } else {
                        vocabularyPairs.add(VocabularyPair.builder()
                                .requestId(requestId)
                                .enWord(parallelWord.toLowerCase())
                                .deWord(word.toLowerCase())
                                .frequencyEn(frequencyForParallelWord)
                                .frequencyDe(frequencyForWord)
                                .build());
                    }
                }
            }
        } else {
            for (int i = 0; i < sourceDocument.tokens().size(); i++) {
                String word = sourceDocument.tokens().get(i).word();
                String parallelWord = targetDocument.tokens().get(i).word();

                if (!isBannedWord(word)) {
                    Integer frequencyForWord = calcFrequency(word, sourceDocument.tokens());
                    Integer frequencyForParallelWord = calcFrequency(parallelWord, targetDocument.tokens());

                    if (from.equals("english")) {
                        vocabularyPairs.add(VocabularyPair.builder()
                                .requestId(requestId)
                                .enWord(word.toLowerCase())
                                .deWord(parallelWord.toLowerCase())
                                .frequencyEn(frequencyForWord)
                                .frequencyDe(frequencyForParallelWord)
                                .build());
                    } else {
                        vocabularyPairs.add(VocabularyPair.builder()
                                .requestId(requestId)
                                .enWord(parallelWord.toLowerCase())
                                .deWord(word.toLowerCase())
                                .frequencyEn(frequencyForParallelWord)
                                .frequencyDe(frequencyForWord)
                                .build());
                    }
                }
            }
        }

        return vocabularyPairs.stream()
                .sorted(Comparator.comparing(VocabularyPair::getFrequencyEn).reversed())
                .collect(Collectors.toList());
    }
    public Integer calcFrequency(String word, List<CoreLabel> tokens) {
        Integer frequency = 0;

        for (CoreLabel token : tokens) {
            if (word.equals(token.word())) {
                frequency++;
            }
        }

        return frequency;
    }

    public List<GrammarInfo> defineGrammarInfo(String source) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);

        List<GrammarInfo> grammarInfos = new ArrayList<>();

        CoreDocument document = new CoreDocument(source);

        pipeline.annotate(document);

        List<CoreLabel> tokens = document.tokens();

        for (CoreLabel token : tokens) {
            String word = token.word();
            String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

            GrammarInfo grammarInfo = new GrammarInfo(word, pos,
                    AbbreviationVocabulary.ABBREVIATIONS_AND_FULL_WORDS.get(pos));

            grammarInfos.add(grammarInfo);
        }

        return grammarInfos;
    }

    public List<VocabularyPair> buildVocabulary(List<String> sourceWords, List<String> targetWords) {
        List<VocabularyPair> vocabularyPairs = new ArrayList<>();

        return vocabularyPairs;
    }
}