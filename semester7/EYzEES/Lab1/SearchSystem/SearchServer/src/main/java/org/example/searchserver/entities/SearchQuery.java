package org.example.searchserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class SearchQuery {
    @JsonProperty("scope")
    private SearchQueryScope scope;
    @JsonProperty("text")
    private String text;
    @JsonProperty("logical_operator")
    private SearchQueryLogicalOperator logicalOperator = SearchQueryLogicalOperator.EMPTY;
}