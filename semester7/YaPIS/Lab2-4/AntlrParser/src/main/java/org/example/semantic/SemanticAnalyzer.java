package org.example.syntax.semantic;

import lombok.Getter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.MyParser;
import org.example.MyParserBaseListener;
import org.example.declarations.MyClassDeclaration;
import org.example.declarations.MyFunctionDeclaration;
import org.example.declarations.MyVariableDeclaration;
import org.example.declarations.ScopeType;

import java.util.*;

import static org.example.syntax.semantic.ClassDeclarationHandler.classScope;
import static org.example.syntax.semantic.ClassDeclarationHandler.isClassWasDeclared;
import static org.example.syntax.semantic.FunctionDeclarationHandler.isFunctionWasDeclared;
import static org.example.syntax.semantic.FunctionDeclarationHandler.scope;
import static org.example.syntax.semantic.IfStatementDeclarationHandler.ifStatementDeclaration;
import static org.example.syntax.semantic.VariableDeclarationHandler.*;
import static org.example.syntax.semantic.ReturnDeclarationHandler.*;

@Getter
public class SemanticAnalyzer extends MyParserBaseListener {
    public static final List<MyVariableDeclaration> globalVariableDeclarations = new ArrayList<>();
    public static final List<MyFunctionDeclaration> functionDeclarations = new ArrayList<>();
    public static final List<MyClassDeclaration> classDeclarations = new ArrayList<>();

    private ScopeType currentScopeType = ScopeType.ALL;
    private String currentScopeName = "all";

    public void analyze(ParseTree tree) {
        visit(tree);
    }

    private void visit(ParseTree node) {
        // Проверяем тип узла и выполняем действия
        if (node instanceof MyParser.FunctionDeclarationContext) {
            currentScopeType = ScopeType.FUNCTION;

            handleFunctionDeclaration((MyParser.FunctionDeclarationContext) node);
        } else if (node instanceof MyParser.ClassDeclarationContext) {
           handleClassDeclaration((MyParser.ClassDeclarationContext) node);
        } else if (node instanceof MyParser.StatementContext) {
            currentScopeName = "all";
            currentScopeType = ScopeType.ALL;

            handleStatement((MyParser.StatementContext) node, functionDeclarations, globalVariableDeclarations);
        }

        // Рекурсивно посещаем все дочерние узлы
        for (int i = 0; i < node.getChildCount(); i++) {
            visit(node.getChild(i));
        }
    }

    private void handleClassDeclaration(MyParser.ClassDeclarationContext ctx) {
        String className = ctx.ID().getText();

        currentScopeName = className;

        String scope = classScope(ctx.LOCAL(), ctx.GLOBAL());

        if (isClassWasDeclared(className, classDeclarations)) {
            System.err.println("Ошибка: класс " + className + " уже определен.");
        } else {
            classDeclarations.add(new MyClassDeclaration(className));
        }
    }

    private void handleFunctionDeclaration(MyParser.FunctionDeclarationContext ctx) {
        String functionName = ctx.ID().getText();

        currentScopeName = functionName;

        List<MyVariableDeclaration> variables = new ArrayList<>();

        if (ctx.parameterList() != null) {
            variables = buildParameters(ctx.parameterList().parameter(), currentScopeName,
                    currentScopeType);
        }

        String returnType = ctx.type().getText();

        String scope = scope(ctx.LOCAL(), ctx.GLOBAL());

        if (isFunctionWasDeclared(functionDeclarations, functionName, variables)) {
            System.err.println("Ошибка: функция " + functionName + " уже определена.");
        } else {
            functionDeclarations.add(new MyFunctionDeclaration(functionName, variables, returnType, scope,
                    false));

            handleBlockStatements(ctx.block().statement(), functionDeclarations, variables);
        }
    }

    private void handleBlockStatements(List<MyParser.StatementContext> statementContexts,
                                       List<MyFunctionDeclaration> functionDeclarations,
                                       List<MyVariableDeclaration> variableDeclarations) {
        for (MyParser.StatementContext statementContext: statementContexts) {
            handleStatement(statementContext, functionDeclarations, variableDeclarations);
        }
    }

    private void handleStatement(MyParser.StatementContext statementContext,
                                 List<MyFunctionDeclaration> functionDeclarations,
                                 List<MyVariableDeclaration> variableDeclarations) {
        if (statementContext.variableDeclaration() != null) {
            handleVariableDeclaration(statementContext.variableDeclaration(), variableDeclarations,
                    globalVariableDeclarations, currentScopeName, currentScopeType);
       } else if (statementContext.assignment() != null) {
            checkVariableTypeAndFindVariableValue(statementContext.assignment().expression(),
                    statementContext.assignment().ID().getText(),
                    findVariableTypeByName(statementContext.assignment().ID().getText(), variableDeclarations,
                            globalVariableDeclarations), variableDeclarations, globalVariableDeclarations);
        } else if (statementContext.returnStatement() != null) {
            returnDeclaration(statementContext.returnStatement().expression(),
                    functionDeclarations.get(functionDeclarations.size() - 1),
                    globalVariableDeclarations);
        } else if (statementContext.ifStatement() != null) {
            ifStatementDeclaration(statementContext.ifStatement().comparativeExpression(),
                    variableDeclarations, globalVariableDeclarations);
        }
    }
}