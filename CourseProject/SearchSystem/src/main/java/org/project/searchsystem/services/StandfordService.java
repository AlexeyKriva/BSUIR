//package org.project.searchsystem.services;
//
//import edu.stanford.nlp.ling.CoreLabel;
//import edu.stanford.nlp.pipeline.CoreDocument;
//import edu.stanford.nlp.pipeline.CoreSentence;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import org.project.searchsystem.models.nlpModels.*;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class StandfordService {
//    private static final AbbreviationDictionary ABBRIVIATURE_DICTIONARY = new AbbreviationDictionary();
//
//    public String getScannedText(String text) {
//        Properties properties = new Properties();
//        properties.setProperty("annotators", "tokenize,ssplit,pos,lemma");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);
//        CoreDocument document = new CoreDocument(text);
//        pipeline.annotate(document);
//        return getLemmas(document);
//    }
//
//    private String getLemmas(CoreDocument document) {
//        List<Sentence> sentences = new ArrayList<>();
//        for (CoreSentence sentence : document.sentences()) {
//            sentences.add(new Sentence(sentence.text(), getTokens(document, sentence)));
//        }
//
//        return sentences;
//    }
//
//    private String getTokens(CoreDocument coreDocument, CoreSentence coreSentence) {
//        List<ParsedWord> scannedText = new ArrayList<>();
//        List<CoreLabel> words = coreSentence.tokens();
//        List<String> posTags = coreSentence.posTags();
//
//        for (int tokenIndex = 0; tokenIndex < words.size(); tokenIndex++) {
//            String currentWord = words.get(tokenIndex).word();
//            String parameters = fromAbbreviationToFullForm(posTags.get(tokenIndex));
//            String lemma = words.get(tokenIndex).lemma();
//
//        }
//
//        return scannedText;
//    }
//}
