import java.util.*;
import java.io.*;

public class Yapis {

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

        private List<Element> elements;

        public ElementSet() {
            this.elements = new ArrayList<>();
        }

        public ElementSet(int initialCapacity) {
            this.elements = new ArrayList<>(initialCapacity);
        }

        public int getSize() {
            return this.elements.size();
        }

        public Element get(int index) {
            if (index >= 0 && index < elements.size()) {
                return this.elements.get(index);
            }
            return null;
        }

        public boolean addElement(Element element) {
            if (!containsElement(element)) {
                this.elements.add(element);
                return true;
            }
            return false;
        }

        public boolean removeElementByIndex(int index) {
            if (index >= 0 && index < elements.size()) {
                this.elements.remove(index);
                return true;
            }
            return false;
        }

        public boolean containsElement(Element element) {
            return elements.stream().anyMatch(e -> e.getValue() == element.getValue());
        }

        public boolean containsElement(String name) {
            return elements.stream().anyMatch(e -> e.getName().equals(name));
        }

        public boolean containsElement(int value) {
            return elements.stream().anyMatch(e -> e.getValue() == value);
        }

        @Override
        public String toString() {
            return "ElementSet{elements=" + elements + "}";
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
        for (int i = 0; i < elements.getSize(); i += 1) {
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
            i += 1;
        }
        return maxElement;
    }

    private static Element min(ElementSet elements) {
        Element minElement = elements.get(0);
        int j = 1;
        while (j < elements.getSize()) {
            int minVal = minElement.getValue();
            Element currentElementMin = elements.get(j);
            int valMin = currentElementMin.getValue();
            if (minVal > valMin) {
                minElement = currentElementMin;
            }
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
        System.out.println();
        System.out.println("Max element");
        Element maximum = max(elements);
        System.out.print(maximum);
        System.out.println();
        System.out.println("Min element");
        Element minimum = min(elements);
        System.out.print(minimum);
    }
}
