package org.example.utils;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;

public class TreePrinter {
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
}
