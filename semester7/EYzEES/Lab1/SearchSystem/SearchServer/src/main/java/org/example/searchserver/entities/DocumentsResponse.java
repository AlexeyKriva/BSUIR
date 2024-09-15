package org.example.searchserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentsResponse {
    private List<MyDocument> myDocuments;
}
