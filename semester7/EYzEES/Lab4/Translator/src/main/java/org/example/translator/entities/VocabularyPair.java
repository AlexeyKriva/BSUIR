package org.example.translator.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vocabulary")
@Builder
public class VocabularyPair implements Comparable<VocabularyPair> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("requestId")
    private Long requestId;

    @JsonProperty("enWord")
    private String enWord;

    @JsonProperty("deWord")
    private String deWord;

    @JsonProperty("frequencyEn")
    private Integer frequencyEn;

    @JsonProperty("frequencyDe")
    private Integer frequencyDe;

    @Override
    public int compareTo(VocabularyPair o) {
        return this.enWord.compareTo(o.getEnWord());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VocabularyPair that = (VocabularyPair) o;
        return Objects.equals(enWord, that.enWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enWord);
    }

}