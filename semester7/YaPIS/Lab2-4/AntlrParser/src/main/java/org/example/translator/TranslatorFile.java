package org.example.translator;

import com.github.javaparser.ast.CompilationUnit;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TranslatorFile {
    public static String rootDirectory = "/Users/aliaksei/Desktop/BSUIR/semester7/YaPIS/Lab2-4/AntlrParser/compile/";

    public static File writeCodeInJava(String filename, CommonTokenStream tokens) {
        File javaFile = createJavaFile(filename);

        CompilationUnit cu = CodeTranslator.translateToJava(filename, tokens);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(javaFile))) {
            writer.write(cu.toString());
        } catch(Exception exception) {
            exception.printStackTrace();
        }

        return javaFile;
    }

    public static File createJavaFile(String filename) {
        return new File(rootDirectory + filename + ".java");
    }
}
