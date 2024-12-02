package org.example.semantic;

import org.example.MyParser;
import org.example.declarations.MyVariableDeclaration;

import java.util.List;

import static org.example.semantic.VariableDeclarationHandler.getVariableType;


public class IfStatementDeclarationHandler {
    public static void ifStatementDeclaration(MyParser.ComparativeExpressionContext ctx,
                                              List<MyVariableDeclaration> variableDeclarations,
                                              List<MyVariableDeclaration> globalVariableDeclarations) {
        int counter = 0;
        if (ctx.expression() != null && !ctx.expression().isEmpty()) {
            if (!VariableDeclarationHandler.getVariableType(ctx.expression().get(0).primary(), variableDeclarations,
                    globalVariableDeclarations).equals(VariableDeclarationHandler.getVariableType(
                    ctx.expression().get(1).primary(), variableDeclarations,
                    globalVariableDeclarations))) {
                System.err.println("Разные типы данных в блоке if.");
            }
        } else {
            ifStatementDeclaration(ctx.comparativeExpression().get(counter++), variableDeclarations,
                    globalVariableDeclarations);
        }
    }
}
