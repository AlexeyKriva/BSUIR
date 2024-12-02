package org.example.declarations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyFunctionDeclaration {
    private String name;
    private List<MyVariableDeclaration> variables;
    private String returnType;
    private String scope;
    private Boolean inClass;
}
