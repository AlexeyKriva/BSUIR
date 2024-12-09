package org.example.translator;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import lombok.SneakyThrows;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeTranslator {
    @SneakyThrows
    public static CompilationUnit translateToJava(String fileName, CommonTokenStream tokens) {
        tokens.fill();

        StringBuilder javaCode = JavaCodeInitializer.initializeJavaCode(fileName);

        javaCode.append(translateToJava(tokens));

        javaCode.append("}");

        System.out.println(javaCode);

        ParseResult<CompilationUnit> result = new JavaParser().parse(javaCode.toString());

        return result.getResult().get();
    }

    public static String translateToJava(CommonTokenStream tokens) {
        List<String> javaCode = new ArrayList<>();


        for (int i = 0; i < tokens.getTokens().size(); i++) {
            Token token = tokens.getTokens().get(i);

            if (!javaCode.isEmpty() && isConstructor(javaCode.getLast(), token.getText())) {
                String object = javaCode.removeLast();

                Map<Integer, List<String>> parameterCode = findParameters(i, tokens);

                i = parameterCode.keySet().iterator().next();

                addConstructor(object, javaCode, parameterCode.get(i));
            } else if (!javaCode.isEmpty() && i + 1 < tokens.getTokens().size() && i + 2 < tokens.getTokens().size() &&
                    isTypeCasting(javaCode.getLast(), token.getText(), tokens.getTokens().get(i + 1).getText())) {
                addTypeCasting(token.getText(), javaCode, tokens.getTokens().get(i + 2).getText());
                i += 2;
            } else {
                javaCode.add(tokenToJavaCode(token.getText()));

                if (javaCode.getLast().equals("main")) {
                    javaCode.add("(");
                    javaCode.add("String args[]");
                    i++;
                }
            }
        }

        javaCode.removeLast();

        return String.join(" ", javaCode);
    }

    public static boolean isTypeCasting(String previousToken, String currentToken, String nextToken) {
        return previousToken.equals("(") && nextToken.equals(")") && isPrimitiveType(currentToken);
    }

    public static boolean isPrimitiveType(String token) {
        return token.equals("string") || token.equals("int") || token.equals("float") || token.equals("boolean")
                || token.equals("element") || token.equals("elementSet");
    }

    public static boolean isConstructor(String previousToken, String currentToken) {
        return (previousToken.equals("Element") || previousToken.equals("ElementSet")) &&
                currentToken.equals("(");
    }

    public static List<String> addTypeCasting(String token, List<String> javaCode, String nextToken) {
        if (token.equals("string")) {
            javaCode.removeLast();
            javaCode.add("String.valueOf(");
            javaCode.add(tokenToJavaCode(nextToken));
            javaCode.add(")");
        } else {
            javaCode.add("(");
            javaCode.add(tokenToJavaCode(token));
            javaCode.add(")");
            javaCode.add(tokenToJavaCode(nextToken));
        }

        return javaCode;
    }

    public static List<String> addConstructor(String object, List<String> javaCode, List<String> parameterCode) {
        javaCode.add("new ");
        javaCode.add(tokenToJavaCode(object));
        javaCode.add("(");

        javaCode.addAll(parameterCode);

        return javaCode;
    }

    public static Map<Integer, List<String>> findParameters(int start, CommonTokenStream tokens) {
        List<String> parameterCode = new ArrayList<>();


        int end = start;

        for (int i = start + 1; i < tokens.getTokens().size(); i++) {
            String token = tokens.getTokens().get(i).getText();

            parameterCode.add(tokenToJavaCode(token));

            end++;

            if (token.equals(")")) {
                break;
            }
        }

        Map<Integer, List<String>> lastIndexAndParameterCode = new HashMap<>();
        lastIndexAndParameterCode.put(end, parameterCode);

        return lastIndexAndParameterCode;
    }

    public static String tokenToJavaCode(String token) {
        if (token.equals("local")) {
            return "private";
        } else if (token.equals("global")) {
            return "public";
        } else if (token.equals("function")) {
            return "static";
        } else if (token.equals("element")) {
            return "Element";
        } else if (token.equals("elementSet")) {
            return "ElementSet";
        } else if (token.equals("string")) {
            return "String";
        } else if (token.equals("write")) {
            return "System.out.print";
        } else if (token.equals("writenl")) {
            return "System.out.println";
        }

        return token;
    }
}