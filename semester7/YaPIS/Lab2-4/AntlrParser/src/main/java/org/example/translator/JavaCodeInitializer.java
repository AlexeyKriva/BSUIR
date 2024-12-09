package org.example.translator;

public class JavaCodeInitializer {
    private static String elementClass = """
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
                    return "Element{" +
                        "value=" + value +
                        ", name='" + name + '\\'' +
                    '}';
                }
            }
            """;

    private static String elementSetClass = """ 
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
            """;

    public static StringBuilder initializeJavaCode(String filename) {
        return new StringBuilder().append("import java.util.*;\nimport java.io.*;\n\n").append("public class ").append(filename).append(" {\n\t").append(elementClass).append("\n").append(elementSetClass);
    }
}