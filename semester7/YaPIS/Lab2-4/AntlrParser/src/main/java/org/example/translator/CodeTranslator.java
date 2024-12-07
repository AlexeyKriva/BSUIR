package org.example.translator;

public class Translator {
    public static void translateToJava(String fileName, String code) {
        TranslatorFile.readCode(fileName, code);
    }
}