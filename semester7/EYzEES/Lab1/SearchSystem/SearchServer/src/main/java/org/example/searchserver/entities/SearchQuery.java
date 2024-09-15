package org.example.searchserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class SearchQuery {
    @JsonProperty("content")
    private String content;
    @JsonProperty("properties")
    private Map<Object, Object> properties;
}
