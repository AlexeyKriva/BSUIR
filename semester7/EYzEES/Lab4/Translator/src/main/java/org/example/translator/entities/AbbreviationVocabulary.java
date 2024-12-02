package org.example.translator.entities;

import java.util.HashMap;
import java.util.Map;

public class AbbreviationVocabulary {
    public static final Map<String, String> ABBREVIATIONS_AND_FULL_WORDS = new HashMap<String, String>() {{
        put("CC", "coordinating conjunction");
        put("CD", "cardinal number");
        put("DT", "determiner");
        put("EX", "existential there");
        put("FW", "foreign word");
        put("IN", "preposition or subordinating conjunction");
        put("JJ", "adjective");
        put("JJR", "adjective, comparative");
        put("JJS", "adjective, superlative");
        put("LS", "list item marker");
        put("MD", "modal");
        put("NN", "noun, singular or mass");
        put("NNS", "noun, plural");
        put("NNP", "proper noun, singular");
        put("NNPS", "proper noun, plural");
        put("PDT", "predeterminer");
        put("POS", "possessive ending");
        put("PRP", "personal pronoun");
        put("PRP$", "possessive pronoun");
        put("RB", "adverb");
        put("RBR", "adverb, comparative");
        put("RBS", "adverb, superlative");
        put("RP", "particle");
        put("SYM", "symbol");
        put("TO", "particle 'to' before infinitive");
        put("UH", "interjection");
        put("VB", "verb, base form");
        put("VBD", "verb, past tense");
        put("VBG", "verb, gerund or present participle");
        put("VBN", "verb, past participle");
        put("VBP", "verb, non-3rd person singular present");
        put("VBZ", "verb, 3rd person singular present");
        put("WDT", "wh-determiner");
        put("WP", "wh-pronoun");
        put("WP$", "possessive wh-pronoun");
        put("WRB", "wh-adverb");
        put("WRB", "wh-adverb");
    }};

}
