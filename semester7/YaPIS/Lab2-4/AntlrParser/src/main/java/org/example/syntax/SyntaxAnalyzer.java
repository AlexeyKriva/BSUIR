package org.example.syntax;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.example.MySimpleLexer;
import org.example.MySimpleParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SyntaxAnalyzer {
    public static String readCodeFromKevaFile() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    public static void printTree(ParseTree tree, Parser parser) {
        System.out.println("Parse Tree (structured):");
        printTree(tree, parser, 0);
    }

    private static void printTree(ParseTree tree, Parser parser, int level) {
        String indent = " ".repeat(level * 2);
        String nodeText = Trees.getNodeText(tree, parser);
        System.out.println(indent + nodeText);

        for (int i = 0; i < tree.getChildCount(); i++) {
            printTree(tree.getChild(i), parser, level + 1);
        }
    }

    public static final String PATH =
            "/Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab3/AntlrParser/files/пример.txt";

    public static void main(String[] args) {
        try {
            String input = readCodeFromKevaFile();

            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            MySimpleLexer lexer = new MySimpleLexer(inputStream);

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            MySimpleParser parser = new MySimpleParser(tokens);

            ParseTree tree = parser.program();

            printTree(tree, parser);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}















































//SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
//            semanticAnalyzer.analyze(tree);