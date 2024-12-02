package com.software.bsuir.abstractbuilder.services;

import com.software.bsuir.abstractbuilder.entities.FileProperty;
import com.software.bsuir.abstractbuilder.entities.GeneralAbstract;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StandfordNlpService {
    private final Integer KEYWORDS_PERCENT = 8;

    private final ClassicAbstractService classicAbstractService;

    Properties properties;

    StanfordCoreNLP pipeline;

    public GeneralAbstract buildAbstractByKeyWords(FileProperty fileProperty) {
        properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);

        Annotation document = new Annotation(fileProperty.getContent());

        pipeline.annotate(document);

        Map<String, Integer> keyPhrases = new HashMap<>();

        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);

                System.out.println(lemma + " - " + pos);

                if (pos.startsWith("NN") || pos.startsWith("NNS") || pos.startsWith("NNP") || pos.startsWith("NNPS")) {
                    keyPhrases.put(lemma, keyPhrases.getOrDefault(lemma, 0) + 1);
                }
            }
        }

        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            List<String> phrase = new ArrayList<>();
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);

                if (pos.startsWith("NN") || pos.startsWith("JJ") || pos.startsWith("NNS") || pos.startsWith("NNP")
                    || pos.startsWith("NNPS")) {
                    phrase.add(lemma);
                } else {
                    if (phrase.size() > 1) {
                        String keyPhrase = String.join(" ", phrase);
                        keyPhrases.put(keyPhrase, keyPhrases.getOrDefault(keyPhrase, 0) + 1);
                        phrase.clear();
                    } else {
                        phrase.clear();
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> listOfKeyWords = new ArrayList<>(keyPhrases.entrySet());

        deleteExtraSpanishWords(listOfKeyWords);

        listOfKeyWords.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        StringBuilder keywords = new StringBuilder();
        keywords.append(listOfKeyWords.get(0).getKey());

        for (int i = 1; i < listOfKeyWords.size() / KEYWORDS_PERCENT; i++) {
            keywords.append(", ");
            keywords.append(listOfKeyWords.get(i).getKey());
        }

        return new GeneralAbstract(keywords.toString());
    }

    public void deleteExtraSpanishWords(List<Map.Entry<String, Integer>> listOfKeyWords) {
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("que"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("los"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("y"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("de"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("la"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("el"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("lo"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("La"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("por"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("han"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("una"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("solo"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("como"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("su"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("Las"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("las"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("El"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("sin"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("sido"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("fue"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("un"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("other"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("Other"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("others"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("Others"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("another"));
        listOfKeyWords.removeIf(entry -> entry.getKey().equals("Another"));
    }

    public GeneralAbstract generalBuildClassicAbstract(FileProperty fileProperty) {
        properties = new Properties();
        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");

        pipeline = new StanfordCoreNLP(properties);

        Map<String, Double> sentenceWeights = buildSentenceWeights(pipeline, fileProperty.getContent());

        String builtAbstract = buildClassicAbstractBySentenceWeights(sentenceWeights);

        return new GeneralAbstract(builtAbstract);
    }

    private Map<String, Double> buildSentenceWeights(StanfordCoreNLP pipeline, String text) {
        CoreDocument document = new CoreDocument(text);

        pipeline.annotate(document);

        List<String> sentences = document.sentences().stream()
                .map(CoreSentence::text)
                .toList();

        Map<String, Double> sentenceWeights = new HashMap<>();

        for (String sentence: sentences) {
            sentenceWeights.put(sentence, sentenceWeights.getOrDefault(sentence, 0.0) +
                    classicAbstractService.scoreS(sentence, text));
        }

        return sentenceWeights;
    }

    public String buildClassicAbstractBySentenceWeights(Map<String, Double> sentenceWeights) {
        Integer numberOfSentences = sentenceWeights.keySet().size();

        List<Map.Entry<String, Double>> listOfSentenceWeights = new ArrayList<>(sentenceWeights.entrySet());

        listOfSentenceWeights.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Double> entry: listOfSentenceWeights) {
            System.out.println(entry);
        }

        String preAbstract = listOfSentenceWeights.stream()
                .limit(numberOfSentences >= 15 ? 10 : numberOfSentences >= 3 ? numberOfSentences / 2 :
                        numberOfSentences)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(" "));

        return buildTotalClassicAbstract(preAbstract);
    }

    public String buildTotalClassicAbstract(String preAbstract) {
        CoreDocument document = new CoreDocument(preAbstract);

        pipeline.annotate(document);

        StringBuilder totalClassicAbstract = new StringBuilder();

        for (CoreSentence sentence: document.sentences()) {
            for (CoreLabel token: sentence.tokens()) {
                String word = token.word();

                if (isTermNotSign(word)) {
                    totalClassicAbstract.append(" ");
                }

                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                if (!pos.startsWith("UH") && !pos.startsWith("RB")) {
                    totalClassicAbstract.append(word);
                }
            }

            if (isTermNotLastSign(classicAbstractService.toTerm(totalClassicAbstract
                    .charAt(totalClassicAbstract.length() - 1)))) {
                totalClassicAbstract.append(". ");
            }
        }

        return totalClassicAbstract.toString();
    }

    public boolean isTermNotLastSign(String term) {
        return !term.equals(".") && !term.equals("!") && !term.equals("?");
    }

    public boolean isTermNotSign(String term) {
        return !term.equals(".") && !term.equals("!") && !term.equals("?") &&
                !term.equals(",") && !term.equals(":") && !term.equals(";") &&
                !term.equals("\"") && !term.equals("'");
    }
}