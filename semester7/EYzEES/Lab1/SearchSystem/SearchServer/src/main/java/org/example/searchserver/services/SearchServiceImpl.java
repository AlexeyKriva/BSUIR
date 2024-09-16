package org.example.searchserver.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.example.searchserver.entities.DocumentsResponse;
import org.example.searchserver.entities.MyDocument;
import org.example.searchserver.entities.SearchQuery;
import org.example.searchserver.repositories.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MyDocumentRepository myDocumentRepository;
    @Autowired
    private QueryBuilder queryBuilder;
    public DocumentsResponse getResponseToSearchQuery(List<SearchQuery> searchQueries) {
        DocumentsResponse documentsResponse = new DocumentsResponse();
        documentsResponse.setMyDocuments(getMyDocumentsBySearchQuery(searchQueries));
        return documentsResponse;
    }

    public List<MyDocument> getMyDocumentsBySearchQuery(List<SearchQuery> searchQueries) {
        StringBuilder query = new StringBuilder(myDocumentRepository.START_USER_REQUEST);
        for (SearchQuery searchQuery : searchQueries) {
            if (queryBuilder.isSearchQueryScopeDependsOnTime(searchQuery.getScope())) {
                query.append(queryBuilder.partOfQueryToCompareTwoDates(searchQuery));
            } else {
                query.append(" d.").append(searchQuery.getScope().getScope()).append(queryBuilder.partOfQueryToFindTextIntoField(searchQuery));
            }
        }
        Query documents = entityManager.createNativeQuery(query.toString(), MyDocument.class);
        return documents.getResultList();
    }
}
