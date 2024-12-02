package org.example.declarations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyVariableDeclaration {
    private String name;
    private String type;
    private String scopeName;
    private ScopeType scopeType;
    private String value;
}