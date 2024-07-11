import java.util.List;

public class Main {
    public static final int SIZE = 16;
    public static void main(String[] args) {
        Table table = new Table();
        table.randomFilling(SIZE);
        System.out.println(table.getValue(5));
        System.out.println(table.getDiagonal(2));
        table.show();
        System.out.println(table.f2(0, 1));
        System.out.println(table.f7(0, 1));
        System.out.println(table.f8(0, 1));
        System.out.println(table.f13(0, 1));
        System.out.println("Диапазон: 1000001101001010 - 1001010101001010");
        List<String> values = table.rangeSearch("1000001101001010", "1001010101001010");
        for (String value: values){
            System.out.println(value);
        }
        System.out.println("___________________________________");
        List<String> answers = table.find("101");
        for (String value: answers){
            System.out.println(value);
        }
    }
}