package org.example.searchserver.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.example.searchserver.entities.*;
import org.example.searchserver.mappers.MyDocumentMapper;
import org.example.searchserver.repositories.MyDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyDocumentServiceImpl implements MyDocumentService {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private MyDocumentRepository myDocumentRepository;
    private final MyDocumentMapper MY_DOCUMENT_MAPPER = MyDocumentMapper.INSTANCE;

    public MyDocument getDocumentById(long id) {
        Optional<MyDocument> documentFromDb = myDocumentRepository.findById(id);
        if (documentFromDb.isPresent()) {
            return documentFromDb.get();
        }

        throw new RuntimeException("Document not found");
    }

    public List<MyDocument> getAllDocuments() {
        return myDocumentRepository.findAll();
    }

    public MyDocument saveDocument(MyDocumentDto myDocumentDto) {
        MyDocument savingDocument = MY_DOCUMENT_MAPPER.getFromMyDocumentDtoToMyDocument(myDocumentDto);
        return myDocumentRepository.save(savingDocument);
    }

    public MyDocument updateDocumentById(long id, MyDocumentDto myDocumentDto) {
        Optional<MyDocument> documentFromDb = myDocumentRepository.findById(id);
        if (documentFromDb.isPresent()) {
            MyDocument updatingDocument = documentFromDb.get();
            MY_DOCUMENT_MAPPER.updateFromMyDocumentDtoToMyDocument(myDocumentDto, updatingDocument);

            return myDocumentRepository.save(updatingDocument);
        }

        throw new RuntimeException("Document not found");
    }

    public void deleteDocumentById(long id) {
        if (myDocumentRepository.existsById(id)) {
            myDocumentRepository.deleteById(id);
        }
    }

    public DocumentsResponse getResponseToSearchQuery(List<SearchQuery> searchQueries) {
        DocumentsResponse documentsResponse = new DocumentsResponse();
        documentsResponse.setMyDocuments(getMyDocumentsBySearchQuery(searchQueries));
        return documentsResponse;
    }

    public List<MyDocument> getMyDocumentsBySearchQuery(List<SearchQuery> searchQueries) {
        StringBuilder query = new StringBuilder(myDocumentRepository.START_USER_REQUEST);
        for (SearchQuery searchQuery : searchQueries) {
            if (isSearchQueryScopeDependsOnTime(searchQuery.getScope())) {
                query.append(partOfQueryToCompareTwoDates(searchQuery));
            } else {
                query.append(" d.").append(searchQuery.getScope().getScope()).append(partOfQueryToFindTextIntoField(searchQuery));
            }
        }
        Query documents = entityManager.createNativeQuery(query.toString(), MyDocument.class);
        return documents.getResultList();
    }

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