package org.example.launch;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.example.translator.CodeTranslator;
import org.example.translator.TranslatorFile;
import org.example.utils.FileService;
import org.example.MyLexer;
import org.example.MyParser;
import org.example.semantic.SemanticAnalyzer;

public class KovaCompiler {
    public static final String PATH =
            "/Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab2-4/AntlrParser/files/ilya.txt";

    public static void main(String[] args) {
        try {
            String input = FileService.readCodeFromKovaFile(PATH);

            ANTLRInputStream inputStream = new ANTLRInputStream(input);
            MyLexer lexer = new MyLexer(inputStream);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            System.out.println(tokens);

            MyParser parser = new MyParser(tokens);

            ParseTree tree = parser.program();

            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            semanticAnalyzer.analyze(tree);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}