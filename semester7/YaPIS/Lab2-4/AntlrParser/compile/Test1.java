import java.util.*;
import java.io.*;

public class Test1 {

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

        @Override
        public String toString() {
            return "Element{" + "value=" + value + ", name='" + name + '\'' + '}';
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
            return this.lastIndex + 1;
        }

        public Element get(int index) {
            if (index >= 0 && index <= lastIndex) {
                return this.elements[index];
            }
            return null;
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ElementSet{capacity=").append(capacity).append(", size=").append(getSize()).append(", elements=[");
            for (int i = 0; i <= lastIndex; i++) {
                sb.append(elements[i].toString());
                if (i < lastIndex)
                    sb.append(", ");
            }
            sb.append("]}");
            return sb.toString();
        }
    }

    private static ElementSet plus(ElementSet elements) {
        for (int i = 0; i < 20; i += 2) {
            String strName1 = String.valueOf(i);
            elements.addElement(new Element(i, strName1));
        }
        return elements;
    }

    private static ElementSet minus(ElementSet elements) {
        for (int i = 30; i >= 0; i -= 3) {
            String strName2 = String.valueOf(i);
            elements.addElement(new Element(i, strName2));
        }
        return elements;
    }

    public static int sum(ElementSet elements) {
        int summa = 0;
        for (int i = 0; i < 0; i += 1) {
            Element current = elements.get(i);
            summa += current.getValue();
        }
        return summa;
    }

    private static Element max(ElementSet elements) {
        Element maxElement = elements.get(0);
        int i = 1;
        while (i < elements.getSize()) {
            int maxVal = maxElement.getValue();
            Element currentElementMax = elements.get(i);
            int valMax = currentElementMax.getValue();
            if (maxVal < valMax) {
                maxElement = currentElementMax;
            }
            maxElement = elements.get(i);
            i += 1;
        }
        return maxElement;
    }

    private static Element min(ElementSet elements) {
        Element minElement = elements.get(0);
        int j = 1;
        while (j < elements.getSize()) {
            int minVal = maxElement.getValue();
            Element currentElementMin = elements.get(i);
            int valMin = currentElementMin.getValue();
            if (minVal > valMin) {
                minElement = currentElementMin;
            }
            minElement = elements.get(i);
            j += 1;
        }
        return minElement;
    }

    public static void main(String[] args) {
        ElementSet elements = new ElementSet();
        elements.addElement(new Element(10, "first"));
        plus(elements);
        minus(elements);
        System.out.println("Elements of set:");
        System.out.println(elements);
        System.out.println("Total sum:");
        int totalSum = sum(elements);
        System.out.print(totalSum);
        System.out.println("Max element");
        Element maximum = max(elements);
        System.out.print(maximum);
        System.out.println();
        System.out.println("Min element");
        Element minimum = min(elements);
        System.out.print(minimum);
        writeln(totalSum);
    }
}
