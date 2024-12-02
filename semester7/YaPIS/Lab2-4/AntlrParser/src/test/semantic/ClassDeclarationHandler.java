package org.example.semantic;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.example.declarations.MyClassDeclaration;
import org.example.declarations.MyFunctionDeclaration;
import org.example.declarations.MyVariableDeclaration;

import java.util.List;

public class ClassDeclarationHandler {
    public static String classScope(TerminalNode local, TerminalNode global) {
        if (local == null && global != null) {
            return global.getText();
        } else if (local != null && global == null) {
            return local.getText();
        }

        return "Invalid scope";
    }

    public static boolean isClassWasDeclared(String className, List<MyClassDeclaration> myClassDeclarations) {
        for (MyClassDeclaration myClassDeclaration: myClassDeclarations) {
            if (myClassDeclaration.getName().equals(className)) {
                return true;
            }
        }

        return false;
    }

    public static String findClassTypeByName(String className, List<MyClassDeclaration> classDeclarations) {
        for (MyClassDeclaration classDeclaration: classDeclarations) {
            if (classDeclaration.getName().equals(className)) {
                return classDeclaration.getName();
            }
        }

        System.err.println("Класс или метод" + className + " не объявлен.");

        return "unknown";
    }
}
