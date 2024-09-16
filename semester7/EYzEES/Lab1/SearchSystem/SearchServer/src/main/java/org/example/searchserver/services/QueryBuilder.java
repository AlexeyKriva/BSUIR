package org.example.searchserver.services;

import org.example.searchserver.entities.SearchQuery;
import org.example.searchserver.entities.SearchQueryLogicalOperator;
import org.example.searchserver.entities.SearchQueryScope;
import org.springframework.stereotype.Component;

@Component
public class QueryBuilder {
    public Character findComparisonSign(SearchQueryScope scope) {
        return switch (scope) {
            case PUBLISHED_BEFORE -> {
                yield '<';
            }
            case PUBLISHED_AT -> {
                yield '=';
            }
            case PUBLISHED_AFTER -> {
                yield '>';
            }
            default -> {
                yield '\0';
            }
        };
    }

    public boolean isQueryHasNotOperator(SearchQueryLogicalOperator logicalOperator) {
        return logicalOperator.getOperator().equals("NOT");
    }

    public boolean isSearchQueryScopeDependsOnTime(SearchQueryScope scope) {
        return scope.getScope().equals("before_published_at") ||
                scope.getScope().equals("published_at") ||
                scope.getScope().equals("after_published_at");
    }

    public String partOfQueryToFindTextIntoField(SearchQuery searchQuery) {
        if (isQueryHasNotOperator(searchQuery.getLogicalOperator())) {
            return searchQuery.getLogicalOperator().getOperator() + " ILIKE '%" +
                    searchQuery.getText().toLowerCase() + "%' ";
        } else {
            return " ILIKE '%" + searchQuery.getText().toLowerCase() + "%' " +
                    searchQuery.getLogicalOperator().getOperator() + " ";
        }
    }

    public String partOfQueryToCompareTwoDates(SearchQuery searchQuery) {
        Character comparisonSign = findComparisonSign(searchQuery.getScope());
        if (isQueryHasNotOperator(searchQuery.getLogicalOperator())) {
            return searchQuery.getLogicalOperator().getOperator() + " d." +
                    searchQuery.getScope().getScope().replaceAll("before_", "")
                            .replaceAll("after_", "") + " " + comparisonSign + " '" +
                    searchQuery.getText() + "' ";
        } else {
            return " d." + searchQuery.getScope().getScope().replaceAll("before_", "")
                    .replaceAll("after_", "") + " " + comparisonSign + " '" +
                    searchQuery.getText() + "' " +
                    searchQuery.getLogicalOperator().getOperator() + " ";
        }
    }
}
