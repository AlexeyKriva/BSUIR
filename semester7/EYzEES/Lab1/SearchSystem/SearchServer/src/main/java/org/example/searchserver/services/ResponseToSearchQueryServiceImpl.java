package org.example.searchserver.services;

import org.example.searchserver.entities.ResponseToSearchQuery;
import org.example.searchserver.entities.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseToSearchQueryServiceImpl implements ResponseToSearchQueryService {
    public List<ResponseToSearchQuery> getResponseToSearchQuery(SearchQuery searchQuery) {
        return null;
    }
}
