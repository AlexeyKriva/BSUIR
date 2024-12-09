package org.example.semantic;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.MyParser;
import org.example.declarations.MyVariableDeclaration;
import org.example.declarations.ScopeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.semantic.ClassDeclarationHandler.findClassTypeByName;
import static org.example.semantic.FunctionDeclarationHandler.findFunctionTypeByName;

public class VariableDeclarationHandler {
    public static List<MyVariableDeclaration> buildParameters(List<MyParser.ParameterContext> parameterContexts,
                                                              String scopeName, ScopeType scopeType) {
        List<MyVariableDeclaration> parameters = new ArrayList<>();;

        for (MyParser.ParameterContext parameterContext: parameterContexts) {
            parameters.add(new MyVariableDeclaration(parameterContext.ID().getText(), parameterContext.type().getText(),
                    scopeName, scopeType, "null"));
        }

        return parameters;
    }

    public static void handleVariableDeclaration(MyParser.VariableDeclarationContext ctx,
                                                 List<MyVariableDeclaration> variableDeclarations,
                                                 List<MyVariableDeclaration> globalVariableDeclarations,
                                                 String scopeName, ScopeType scopeType) {
        List<TerminalNode> ids = ctx.ID();

        if (!ids.isEmpty()) {
            String varName = ids.get(0).getText();

            String varType = "";

            if (ctx.type() != null) {
                varType = ctx.type().getText();
            } else {
                varName = ids.get(1).getText();
                varType = ids.get(0).getText();
            }

            String varValue = checkVariableTypeAndFindVariableValue(ctx.expression(), varName, varType, variableDeclarations,
                    globalVariableDeclarations);

            if (isVariableDeclared(varName, variableDeclarations, globalVariableDeclarations)) {
                throw new RuntimeException("Ошибка: переменная " + varName +
                        " уже определена в текущей области видимости.");
            } else {
                variableDeclarations.add(new MyVariableDeclaration(varName, varType, scopeName, scopeType, varValue));
            }
        } else {
            throw new RuntimeException("Ошибка: идентификатор переменной не найден.");
        }
    }

    private static boolean isVariableDeclared(String varName, List<MyVariableDeclaration> variableDeclarations) {
        for (MyVariableDeclaration variableDeclaration: variableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return true;
            }
        }

        return false;
    }

    private static boolean isVariableDeclared(String varName, List<MyVariableDeclaration> variableDeclarations,
                                              List<MyVariableDeclaration> globalVariableDeclarations) {
        for (MyVariableDeclaration variableDeclaration: variableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return true;
            }
        }

        for (MyVariableDeclaration variableDeclaration: globalVariableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return true;
            }
        }

        return false;
    }

    public static String checkVariableTypeAndFindVariableValue(MyParser.ExpressionContext ctx, String varName,
                                                               String varType,
                                                               List<MyVariableDeclaration> variableDeclarations,
                                                               List<MyVariableDeclaration> globalVariableDeclarations) {
        String variableValue = "null";

        String expressionText = ctx.getText();

        if (expressionText.contains("+") || expressionText.contains("-") || expressionText.contains("*") ||
                expressionText.contains("/") || expressionText.contains("%")) {

            List<String> expressionVariables = getTwoExpressionVariablesWithSplit(expressionText);

            if (isVariableDeclared(expressionVariables.get(0), variableDeclarations, globalVariableDeclarations) &&
                isVariableDeclared(expressionVariables.get(1), variableDeclarations, globalVariableDeclarations)) {
                if (varType.equals(findVariableTypeByName(expressionVariables.get(0), variableDeclarations,
                        globalVariableDeclarations)) && varType.equals(findVariableTypeByName(expressionVariables.get(1),
                        variableDeclarations, globalVariableDeclarations))) {
                    variableValue = findVariableValueByName(expressionVariables.get(0), variableDeclarations,
                            globalVariableDeclarations) +
                            findVariableValueByName(expressionVariables.get(1), variableDeclarations,
                                    globalVariableDeclarations);
                } else {
                    throw new RuntimeException("Переменной " + varName +
                            " присваивается выражение с разными типами данных.");
                }
            } else {
                throw new RuntimeException("Переменная " + expressionVariables.get(0) + " или/и " +
                        expressionVariables.get(1) + " не объявлена(ы).");
            }
        } else if (ctx.primary() != null) {
            boolean isDot = false;

            if (ctx.DOT() != null) {
                isDot = true;
            }

            variableValue = getValueWithTypeChecking(varName, varType, ctx.primary(), variableDeclarations,
                    globalVariableDeclarations, isDot);
        }


        return variableValue;
    }

    private static String getValueWithTypeChecking(String varName, String varType, MyParser.PrimaryContext ctx,
                                                   List<MyVariableDeclaration> variableDeclarations,
                                                   List<MyVariableDeclaration> globalVariableDeclarations,
                                                   boolean isDot) {
        boolean isMethod = false;

        if (ctx.INT() != null) {
            if (varType.equals("int")) {
                return ctx.INT().getText();
            }
        } else if (ctx.BOOLEAN() != null) {
            if (varType.equals("boolean")) {
                return ctx.BOOLEAN().getText();
            }
        } else if (ctx.FLOAT() != null) {
            if (varType.equals("float")) {
                return ctx.FLOAT().getText();
            }
        } else if (ctx.STRING() != null) {
            if (varType.equals("string")) {
                return ctx.STRING().getText();
            }
        } else if (ctx.ELEMENT_TYPE() != null) {
            if (varType.equals("element")) {
                return ctx.ELEMENT_TYPE().getText();
            }
        } else if (ctx.ELEMENT_SET_TYPE() != null) {
            if (varType.equals("elementSet")) {
                return ctx.ELEMENT_SET_TYPE().getText();
            }
        } else if (ctx.ID() != null && ctx.LPAREN() == null) {
            if (isDot) {
                return ctx.ID().getText();
            }
            if (varType.equals(findVariableTypeByName(ctx.ID().getText(), variableDeclarations,
                    globalVariableDeclarations))) {
                return findVariableTypeByName(ctx.ID().getText(), variableDeclarations,
                        globalVariableDeclarations);
            }
        } else if (ctx.ID() != null && ctx.LPAREN() != null) {
            isMethod = true;

            if (varType.equals(findFunctionTypeByName(ctx.ID().getText(), SemanticAnalyzer.functionDeclarations)) ||
            varType.equals(findClassTypeByName(ctx.ID().getText(), SemanticAnalyzer.classDeclarations))) {
                return ctx.ID().getText();
            }
        }

        if (!isMethod) {
            throw new RuntimeException("Переменная/метод " + varName + " типа " + varType +
                    " не может принять/вернуть данное значение.");
        } else {
            throw new RuntimeException("Метод " + ctx.ID().getText() + " имеет тип отличный от " + varType + " .");
        }
    }

    public static String getVariableType(MyParser.PrimaryContext ctx, List<MyVariableDeclaration> variableDeclarations,
                                          List<MyVariableDeclaration> globalVariableDeclarations) {
        if (ctx.INT() != null) {
            return "int";
        } else if (ctx.BOOLEAN() != null) {
            return "boolean";
        } else if (ctx.FLOAT() != null) {
            return "float";
        } else if (ctx.STRING() != null) {
            return "string";
        } else if (ctx.ELEMENT_TYPE() != null) {
            return "element";
        } else if (ctx.ELEMENT_SET_TYPE() != null) {
            return "elementSet";
        } else if (ctx.ID() != null && ctx.LPAREN() == null) {
            return findVariableTypeByName(ctx.ID().getText(), variableDeclarations, globalVariableDeclarations);
        } else if (ctx.ID() != null && ctx.LPAREN() != null) {
            return "method";
        }

        return "unknown";
    }

    private static List<String> getTwoExpressionVariablesWithSplit(String expressionText) {
        expressionText = expressionText.replaceAll("\\+", "в");
        expressionText = expressionText.replaceAll("-", "в");
        expressionText = expressionText.replaceAll("\\*", "в");
        expressionText = expressionText.replaceAll("/", "в");
        expressionText = expressionText.replaceAll("%", "в");

        return Arrays.asList(expressionText.split("в"));
    }

    private static String findVariableValueByName(String varName, List<MyVariableDeclaration> variableDeclarations,
                                                  List<MyVariableDeclaration> globalVariableDeclarations) {
        for (MyVariableDeclaration variableDeclaration: variableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return variableDeclaration.getValue();
            }
        }

        for (MyVariableDeclaration variableDeclaration: globalVariableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return variableDeclaration.getValue();
            }
        }

        throw new RuntimeException("Переменная " + varName + " не найдена.");
    }

    public static String findVariableTypeByName(String varName, List<MyVariableDeclaration> variableDeclarations,
                                                List<MyVariableDeclaration> globalVariableDeclarations) {
        for (MyVariableDeclaration variableDeclaration: variableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return variableDeclaration.getType();
            }
        }

        for (MyVariableDeclaration variableDeclaration: globalVariableDeclarations) {
            if (varName.equals(variableDeclaration.getName())) {
                return variableDeclaration.getType();
            }
        }

        throw new RuntimeException("Неизвестный тип данных переменной " + varName + ".");
    }
}
