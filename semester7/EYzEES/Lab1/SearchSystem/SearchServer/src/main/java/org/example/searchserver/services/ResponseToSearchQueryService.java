package org.example.searchserver.services;

import org.example.searchserver.entities.ResponseToSearchQuery;
import org.example.searchserver.entities.SearchQuery;

import java.util.List;

public interface ResponseToSearchQueryService {
    List<ResponseToSearchQuery> getResponseToSearchQuery(SearchQuery searchQuery);
}
