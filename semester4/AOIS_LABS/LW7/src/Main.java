import java.util.Random;

public class Main {
    public static final int SIZE = 20;
    public static final String[] ARRAY= {"0", "1"};
    public static final int LENGTH = 6;
    public static final String TEST = "100000";
    public static final String LEFT_RANGE = "000011";
    public static final String RIGHT_RANGE = "100111";

    public static void main(String[] args) {
        Random random = new Random();
        AssociativeProcessor associativeProcessor = new AssociativeProcessor();
        int length = LENGTH;
        for (int i = 0; i  < 7; i++){
            String value = "";
            for (int j = 0; j < length - 1; j++){
                value += ARRAY[random.nextInt(ARRAY.length)];
            }
            value += String.valueOf(1);
            associativeProcessor.setTable(value);
        }
        associativeProcessor.show();
        System.out.println("Ближайший снизу к " + TEST + ": " + associativeProcessor.searchNearest(TEST, true));
        System.out.println("Ближайший сверху к " + TEST + ": " + associativeProcessor.searchNearest(TEST, false));
        System.out.println("Диапазон: от " + LEFT_RANGE + " до " + RIGHT_RANGE + "\nОтвет:");
        associativeProcessor.rangeSearch(LEFT_RANGE, RIGHT_RANGE);
    }
}