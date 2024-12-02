package org.example.translator.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.translator.clients.TranslatorClient;
import org.example.translator.entities.GrammarInfo;
import org.example.translator.entities.Response;
import org.example.translator.entities.Request;
import org.example.translator.entities.VocabularyPair;
import org.example.translator.repositories.RequestRepository;
import org.example.translator.repositories.VocabularyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranslationService {
    private final TranslatorClient translatorClient;

    private final VocabularyService vocabularyService;

    private final RequestRepository requestRepository;

    private final TextProcessorService textProcessorService;


    private final String ADDITIONAL_PATH = "/response";

    private final String PATH = "/Users/aliaksei/Desktop/BSUIR/semester7/EYzEES/Lab4/Translator/files/";

    @Value("${huggingface.api.key}")
    private String apiKey;

    private final String ENGLISH = "english";

    private final String DEUTSCH = "deutsch";

    public Response translateSourceText(String source, String from, String to) {
        //Сохранение запроса
        Request request = new Request();
        request.setTime(LocalDateTime.now());
        request = requestRepository.save(request);

        List<String> sourceWords = textProcessorService.tokenize(source);

        //Переведённый текст
        List<String> translatedWords = translateText(sourceWords, from);

        Integer sourceWordsNumber = textProcessorService.numberOfWordsInText(sourceWords);
        Integer targetWordsNumber = textProcessorService.numberOfWordsInText(translatedWords);

        String tree = textProcessorService.getTree(source);

        List<GrammarInfo> grammarInfos = textProcessorService.defineGrammarInfo(source);

        List<VocabularyPair> vocabularyPairs = textProcessorService.getVocabularyPairs(
                String.join(" ", sourceWords), String.join(" ", translatedWords),
                from, request.getId()
        );

        vocabularyService.saveAllVocabularyPairs(vocabularyPairs);

        //Создание ответа
        Response response = Response.builder()
                .source(source)
                .from(from)
                .to(to)
                .target(String.join(" ", translatedWords))
                .sourceWordsNumber(sourceWordsNumber)
                .targetWordsNumber(targetWordsNumber)
                .grammarInfos(grammarInfos)
                .vocabulary(vocabularyPairs)
                .tree(tree)
                .build();

        saveAbstractResponseInFile(response);

        return response;
    }

    @SneakyThrows
    public Response saveAbstractResponseInFile(Response response) {
        Files.write(Path.of(PATH + ADDITIONAL_PATH + LocalDateTime.now() + ".txt"),
                ("Source:\n" + response.getSource() +
                        "\n===================================================\nFrom:\n" + response.getFrom() +
                        "\n===================================================\nTo:\n" + response.getTo() +
                        "\n===================================================\nTarget:\n" + response.getTarget() +
                        "\n===================================================\nSourceWordsNumber\n" +
                        response.getSourceWordsNumber() +
                        "\n===================================================\nTargetWordsNumber\n" +
                        response.getTargetWordsNumber() +
                        "\n===================================================\nWords: \n" + response.getVocabulary() +
                        "\n===================================================\nTree:\n" + response.getTree()
                        ).getBytes()
        );

        return response;
    }

    public List<String> translateText(List<String> lemmas, String from) {
        from = from.toLowerCase();

        List<String> targetWords = new ArrayList<>();

        switch (from) {
            case ENGLISH -> {
                for (String lemma: lemmas) {
                    String targetWord = lemma;

                    if (!textProcessorService.isBannedWord(lemma)) {
                        targetWord = translatorClient.translateTextFromEnglishToDeutsch(apiKey,
                                lemma).getBody().get(0).getTranslationText();
                    }

                    targetWords.add(targetWord);
                }

                return targetWords;
            }
            case DEUTSCH -> {
                for (String lemma: lemmas) {
                    String targetWord = translatorClient.translateTextFromDeutschToEnglish(apiKey, lemma)
                            .getBody().get(0).getTranslationText();

                    targetWords.add(targetWord);
                }

                return targetWords;
            }
            default -> {
                return List.of("Unknown language.");
            }
        }
    }
}