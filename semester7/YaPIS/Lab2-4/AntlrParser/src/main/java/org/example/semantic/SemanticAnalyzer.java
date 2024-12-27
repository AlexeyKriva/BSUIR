package org.example.semantic;

import lombok.Getter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.MyParser;
import org.example.MyParserBaseListener;
import org.example.declarations.MyClassDeclaration;
import org.example.declarations.MyFunctionDeclaration;
import org.example.declarations.MyVariableDeclaration;
import org.example.declarations.ScopeType;

import java.util.*;

import static org.example.semantic.IfStatementDeclarationHandler.ifStatementDeclaration;
import static org.example.semantic.VariableDeclarationHandler.*;
import static org.example.semantic.ReturnDeclarationHandler.*;

@Getter
public class SemanticAnalyzer extends MyParserBaseListener {
    public static final List<MyVariableDeclaration> globalVariableDeclarations = new ArrayList<>();
    public static final List<MyFunctionDeclaration> functionDeclarations = new ArrayList<>();
    public static final List<MyClassDeclaration> classDeclarations = new ArrayList<>();

    private ScopeType currentScopeType = ScopeType.ALL;
    private String currentScopeName = "all";
    private final String FUNCTION_MAIN = "main";
    private final String VOID_TYPE = "void";
    private final String SCOPE_NAME = "global";

    public void analyze(ParseTree tree) {
        visit(tree);

        for (MyFunctionDeclaration functionDeclaration : functionDeclarations) {
            if (functionDeclaration.getName().equals(FUNCTION_MAIN) &&
                    functionDeclaration.getReturnType().equals(VOID_TYPE) &&
                    functionDeclaration.getScope().equals(SCOPE_NAME)) {
                return;
            }
        }

        throw new RuntimeException("Нет глобальной функции Main с возвращаемым типом - void");
    }

    private void visit(ParseTree node) {
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

        for (int i = 0; i < node.getChildCount(); i++) {
            visit(node.getChild(i));
        }
    }

    private void handleClassDeclaration(MyParser.ClassDeclarationContext ctx) {
        String className = ctx.ID().getText();

        currentScopeName = className;

        String scope = ClassDeclarationHandler.classScope(ctx.LOCAL(), ctx.GLOBAL());

        if (ClassDeclarationHandler.isClassWasDeclared(className, classDeclarations)) {
            throw new RuntimeException("Ошибка: класс " + className + " уже определен.");
        } else {
            classDeclarations.add(new MyClassDeclaration(className));
        }
    }

    private void handleFunctionDeclaration(MyParser.FunctionDeclarationContext ctx) {
        String functionName = ctx.ID().getText();

        currentScopeName = functionName;

        List<MyVariableDeclaration> variables = new ArrayList<>();
        List<MyVariableDeclaration> parameters = new ArrayList<>();

        if (ctx.parameterList() != null) {
            variables = buildParameters(ctx.parameterList().parameter(), currentScopeName,
                    currentScopeType);
            parameters = buildParameters(ctx.parameterList().parameter(), currentScopeName,
                    currentScopeType);
        }

        String returnType = ctx.type().getText();

        String scope = FunctionDeclarationHandler.scope(ctx.LOCAL(), ctx.GLOBAL());

        if (FunctionDeclarationHandler.isFunctionWasDeclared(functionDeclarations, functionName, parameters)) {
            throw new RuntimeException("Ошибка: функция " + functionName + " уже определена.");
        } else {
            functionDeclarations.add(new MyFunctionDeclaration(functionName, variables, returnType, scope,
                    false, parameters));

            handleBlockStatements(ctx.block().statement(), functionDeclarations, variables);
        }
    }

    private void handleBlockStatements(List<MyParser.StatementContext> statementContexts,
                                       List<MyFunctionDeclaration> functionDeclarations,
                                       List<MyVariableDeclaration> variableDeclarations) {
        for (MyParser.StatementContext statementContext : statementContexts) {
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