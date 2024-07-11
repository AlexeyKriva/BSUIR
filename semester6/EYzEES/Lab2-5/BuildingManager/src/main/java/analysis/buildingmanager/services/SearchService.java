package analysis.buildingmanager.services;

import analysis.buildingmanager.models.ParsedWord;
import analysis.buildingmanager.models.Sentence1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static analysis.buildingmanager.models.Signs.signs;

@Service
@Slf4j
public class SearchService {
    public List<Sentence1> search(List<String> query, List<String> parameters, List<Sentence1> markedHull) {
        for (String string : query) {
            if (checkContent(string)) {
                markedHull = searchWordInMarkedHull(string, markedHull);
            }
        }
        for (String parameter : parameters) {
            if (!parameter.equals("no_parameters")) {
                markedHull = searchParameterInMarkedHull(parameter, markedHull);
            }
        }
        return markedHull;
    }

    public List<Sentence1> reduceAmountOfWords(List<String> query, List<String> parameters, List<Sentence1> markedHull) {
        List<Sentence1> results = new ArrayList<>();
        for (Sentence1 sentence: markedHull) {
            Set<ParsedWord> parsedWords = new HashSet<>();
            for (ParsedWord parsedWord: sentence.getListOfWords()) {
                for (String text: query) {
                    if (text.equals(parsedWord.getWord()) || text.equals(parsedWord.getLemma())) {
                        parsedWords.add(parsedWord);
                        break;
                    }
                }
                for (String parameter: parameters) {
                    Pattern pattern = Pattern.compile(parameter);
                    Matcher matcher = pattern.matcher(parsedWord.getParameters());
                    if (matcher.find()) {
                        parsedWords.add(parsedWord);
                        break;
                    }
                }
            }
            results.add(new Sentence1(sentence.getSentence(), new ArrayList<>(parsedWords)));
        }

        return results;
    }

    public List<Sentence1> searchWordInMarkedHull(String text, List<Sentence1> markedHull) {
        List<Sentence1> sentencesWithSearchText = new ArrayList<>();
        for (Sentence1 sentence : markedHull) {
            for (ParsedWord parsedWord : sentence.getListOfWords()) {
                Pattern pattern = Pattern.compile(text);
                Matcher matcher = pattern.matcher(parsedWord.getWord());
                if (matcher.find()) {
                    sentencesWithSearchText.add(sentence);
                    break;
                }
            }
        }

        return sentencesWithSearchText;
    }

    public List<Sentence1> searchParameterInMarkedHull(String parameter, List<Sentence1> markedHull) {
        List<Sentence1> sentencesWithSearchText = new ArrayList<>();
        for (Sentence1 sentence : markedHull) {
            for (ParsedWord parsedWord : sentence.getListOfWords()) {
                Pattern pattern = Pattern.compile(parameter);
                Matcher matcher = pattern.matcher(parsedWord.getParameters());
                if (matcher.find()) {
                    sentencesWithSearchText.add(sentence);
                    break;
                }
            }
        }

        return sentencesWithSearchText;
    }

    public boolean checkContent(String text) {
        return !text.isEmpty();
    }

    public boolean checkSigns(String word) {
        for (String sign: signs) {
            if (word.equals(sign)) {
                return true;
            }
        }

        return false;
    }
}