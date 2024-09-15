package org.example.searchserver.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchQueryLogicalOperator {
    AND("AND"), OR("OR"), NOT("NOT"), EMPTY("");
    private final String operator;
}
