package org.example.translator.entities;

import edu.stanford.nlp.trees.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private String from;
    private String to;
    private String source;
    private String target;
    private Integer sourceWordsNumber;
    private Integer targetWordsNumber;
    private List<GrammarInfo> grammarInfos;
    private List<VocabularyPair> vocabulary;
    private String tree;
}