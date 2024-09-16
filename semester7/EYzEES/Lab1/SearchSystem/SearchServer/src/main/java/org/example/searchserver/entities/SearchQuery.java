package org.example.searchserver.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@ToString
public class SearchQuery {
    @JsonProperty("scope")
    private SearchQueryScope scope;
    @JsonProperty("text")
    private String text;
    @JsonProperty("logical_operator")
    private SearchQueryLogicalOperator logicalOperator = SearchQueryLogicalOperator.EMPTY;
}