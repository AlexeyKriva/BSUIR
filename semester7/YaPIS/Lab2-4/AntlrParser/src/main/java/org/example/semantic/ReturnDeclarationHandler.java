package org.example.syntax.semantic;

import org.example.MyParser;
import org.example.declarations.MyFunctionDeclaration;
import org.example.declarations.MyVariableDeclaration;

import java.util.List;

import static org.example.syntax.semantic.FunctionDeclarationHandler.isFunctionHasTargetReturnType;
import static org.example.syntax.semantic.VariableDeclarationHandler.checkVariableTypeAndFindVariableValue;

public class ReturnDeclarationHandler {
    private static final String VOID = "void";

    public static void returnDeclaration(MyParser.ExpressionContext ctx, MyFunctionDeclaration functionDeclaration,
                                  List<MyVariableDeclaration> globalVariableDeclarations) {
        checkVariableTypeAndFindVariableValue(ctx, functionDeclaration.getName(), functionDeclaration.getReturnType(),
                functionDeclaration.getVariables(), globalVariableDeclarations);
    }

    public static void returnVoidDeclaration(MyFunctionDeclaration functionDeclaration) {
        if (!isFunctionHasTargetReturnType(functionDeclaration.getReturnType(), VOID)) {
            System.err.println("Метод " + functionDeclaration.getName() + " не может возвращать значение.");
        }
    }
}
