package org.editor.secondorderlineseditor.models;

import lombok.Data;

@Data
public class SecondOrderLine {
    private int pointX;
    private int pointY;
    private int coefficientA;
    private int coefficientB;
    private TypeOfLine typeOfLine;
}