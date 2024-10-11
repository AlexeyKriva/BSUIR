package org.example.uploadingfilesserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MyDocumentDto {
    @JsonProperty("title")
    private String title;
    @JsonProperty("author")
    private String author;
    @JsonProperty("content")
    private String content;
    @JsonProperty("published_at")
    //@Pattern(regexp = "YYYY-MM-DD")
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate publishedAt;
    @JsonProperty("version")
    private String version;
}
