import java.util.*;
import java.io.*;

public class Filename {

    static class Element {

        private int value;

        private String name;

        public Element() {
        }

        public Element(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public Element(int value) {
            this.value = value;
        }

        public Element(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addValue(int value) {
            this.value += value;
        }

        public void subtractValue(int value) {
            this.value -= value;
        }

        public void multiplyValue(int value) {
            this.value *= value;
        }

        public void dividedValue(int value) {
            if (value != 0) {
                this.value /= value;
            } else {
                throw new IllegalArgumentException("Division by zero is not allowed.");
            }
        }

        public void modValue(int value) {
            if (value != 0) {
                this.value %= value;
            } else {
                throw new IllegalArgumentException("Mod by zero is not allowed.");
            }
        }
    }

    static class ElementSet {

        private int capacity = 10;

        private int lastIndex = 0;

        private Element[] elements;

        public ElementSet() {
            this.elements = new Element[capacity];
        }

        public ElementSet(int capacity) {
            this.capacity = capacity;
            this.elements = new Element[capacity];
        }

        public int getSize() {
            return this.capacity;
        }

        public boolean addElement(Element element) {
            boolean isContained = containsElement(element);
            if (!isContained) {
                if (this.lastIndex + 1 < this.capacity) {
                    this.elements[++this.lastIndex] = element;
                } else {
                    this.capacity += this.capacity / 2;
                    Element[] newElements = copy(this.elements, this.capacity);
                    newElements[++this.lastIndex] = element;
                    this.elements = newElements;
                }
                return true;
            }
            return false;
        }

        public Element[] copy(Element[] elements, int capacity) {
            Element[] newElements = new Element[capacity];
            for (int i = 0; i <= this.lastIndex; i++) {
                newElements[i] = elements[i];
            }
            return newElements;
        }

        public boolean removeElementByIndex(int index) {
            if (index >= 0 && index <= this.lastIndex) {
                this.elements[index] = new Element();
                for (int i = index + 1; i <= lastIndex; i++) {
                    elements[i - 1] = elements[i];
                }
                elements[lastIndex] = new Element();
                lastIndex--;
                return true;
            }
            return false;
        }

        public boolean containsElement(Element element) {
            for (int i = 0; i <= this.lastIndex; i++) {
                if (this.elements[i].getValue() == element.getValue()) {
                    return true;
                }
            }
            return false;
        }

        public boolean containsElement(String name) {
            for (int i = 0; i <= this.lastIndex; i++) {
                if (this.elements[i].getName().equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public boolean containsElement(int value) {
            for (int i = 0; i <= this.lastIndex; i++) {
                if (this.elements[i].getValue() == value) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
    }
}
