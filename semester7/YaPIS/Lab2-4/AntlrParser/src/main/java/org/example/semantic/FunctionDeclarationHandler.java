package org.example.semantic;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.declarations.MyFunctionDeclaration;
import org.example.declarations.MyVariableDeclaration;

import java.util.List;

import static org.example.semantic.ClassDeclarationHandler.findClassTypeByName;

public class FunctionDeclarationHandler {
    public static String scope(TerminalNode local, TerminalNode global) {
        if (local == null && global != null) {
            return global.getText();
        } else if (local != null && global == null) {
            return local.getText();
        }

        return "Invalid scope";
    }

    public static boolean isFunctionWasDeclared(List<MyFunctionDeclaration> functionDeclarations,
                                          String functionName, List<MyVariableDeclaration> parameters) {
        for (MyFunctionDeclaration functionDeclaration: functionDeclarations) {
            if (functionDeclaration.getName().equals(functionName)) {
                int coincidence = 0;

                if (parameters.isEmpty() && functionDeclaration.getParameters().isEmpty()) {
                    return true;
                }

                for (int i = 0; i < parameters.size(); i++) {
                    if (parameters.get(i).getType().equals(functionDeclaration.getParameters().get(i).getType())) {
                        coincidence++;
                    }
                }

                if (coincidence == functionDeclaration.getVariables().size()) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isFunctionHasTargetReturnType(String functionType, String targetReturnType) {
        return functionType.equals(targetReturnType);
    }

    public static String findFunctionTypeByName(String functionName, List<MyFunctionDeclaration> functionDeclarations) {
        for (MyFunctionDeclaration functionDeclaration: functionDeclarations) {
            if (functionDeclaration.getName().equals(functionName)) {
                return functionDeclaration.getReturnType();
            }
        }

        if (findClassTypeByName(functionName, SemanticAnalyzer.classDeclarations).equals("unknown")) {
            throw new RuntimeException("Метод или класс " + functionName + " не объявлен.");
        }

        return "unknown";
    }
}
