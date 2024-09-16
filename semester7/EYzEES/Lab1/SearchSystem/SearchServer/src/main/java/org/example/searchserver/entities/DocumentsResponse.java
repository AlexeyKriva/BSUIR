package org.example.searchserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocumentsResponse {
    private List<MyDocument> myDocuments;

    public void print() {
        for (MyDocument myDocument: myDocuments) {
            System.out.println(myDocument);
        }
    }
}
