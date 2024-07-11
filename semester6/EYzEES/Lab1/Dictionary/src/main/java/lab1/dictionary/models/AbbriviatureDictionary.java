package lab1.dictionary.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AbbriviatureDictionary {
    private final Map<String, String> ABBREVIATIONS_AND_FULL_WORDS = new HashMap<String, String>(){{
        put("CC", "Coordinating conjunction");
        put("CD", "Cardinal number");
        put("DT", "Determiner");
        put("EX", "Existential there");
        put("FW", "Foreign word");
        put("IN", "Preposition or subordinating conjunction");
        put("JJ", "Adjective");
        put("JJR", "Adjective, comparative");
        put("JJS", "Adjective, superlative");
        put("LS", "List item marker");
        put("MD", "Modal");
        put("NN", "Noun, singular or mass");
        put("NNS", "Noun, plural");
        put("NNP", "Proper noun, singular");
        put("NNPS", "Proper noun, plural");
        put("PDT", "Predeterminer");
        put("POS", "Possessive ending");
        put("PRP", "Personal pronoun");
        put("PRP$", "Possessive pronoun");
        put("RB", "Adverb");
        put("RBR", "Adverb, comparative");
        put("RBS", "Adverb, superlative");
        put("RP", "Particle");
        put("SYM", "Symbol");
        put("TO", "Particle 'to' before infinitive");
        put("UH", "Interjection");
        put("VB", "Verb, base form");
        put("VBD", "Verb, past tense");
        put("VBG", "Verb, gerund or present participle");
        put("VBN", "Verb, past participle");
        put("VBP", "Verb, non-3rd person singular present");
        put("VBZ", "Verb, 3rd person singular present");
        put("WDT", "Wh-determiner");
        put("WP", "Wh-pronoun");
        put("WP$", "Possessive wh-pronoun");
        put("WRB", "Wh-adverb");
    }};

    public Map<String, String> getAbbreviationsAndFullWords() {
        return ABBREVIATIONS_AND_FULL_WORDS;
    }
}