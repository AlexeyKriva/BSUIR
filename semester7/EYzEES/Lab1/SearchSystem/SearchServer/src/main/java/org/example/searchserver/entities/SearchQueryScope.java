package org.example.searchserver.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SearchQueryScope {
    TITLE("title"), AUTHOR("author"), CONTENT("content"), PUBLISHED_BEFORE("before_published_at"),
    PUBLISHED_AT("published_at"), PUBLISHED_AFTER("after_published_at");
    private final String scope;
}
