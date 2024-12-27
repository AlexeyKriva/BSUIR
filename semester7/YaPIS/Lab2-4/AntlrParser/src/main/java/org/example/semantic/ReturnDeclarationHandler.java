package org.example.semantic;

import org.example.MyParser;
import org.example.declarations.MyFunctionDeclaration;
import org.example.declarations.MyVariableDeclaration;

import java.util.List;

public class ReturnDeclarationHandler {
    private static final String VOID = "void";

    public static void returnDeclaration(MyParser.ExpressionContext ctx, MyFunctionDeclaration functionDeclaration,
                                  List<MyVariableDeclaration> globalVariableDeclarations) {
        VariableDeclarationHandler.checkVariableTypeAndFindVariableValue(ctx, functionDeclaration.getName(),
                functionDeclaration.getReturnType(),
                functionDeclaration.getVariables(), globalVariableDeclarations);
    }

    public static void returnVoidDeclaration(MyFunctionDeclaration functionDeclaration) {
        if (!FunctionDeclarationHandler.isFunctionHasTargetReturnType(functionDeclaration.getReturnType(), VOID)) {
            throw new RuntimeException("Метод " + functionDeclaration.getName() + " не может возвращать значение.");
        }
    }
}