import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String text = "aeleven";

        System.out.println("Letter sequence numbers:");
        for (char letter: text.toCharArray()) {
            System.out.println(letter + " - " + getLetterIndex(letter));
        }

        System.out.println("Encoded text: " + encodeText(text));

        System.out.println(decodeText(List.of(encodeText(text))));
    }

    public static String decodeText(List<List<Integer>> binary) {
        List<List<Integer>> subBinary = Stream.iterate(0, i -> i + 5)
                .limit(20)
                .map(i -> binary.get(0).subList(i, Math.min(i + 5, 100)))
                .toList();

        StringBuilder sourceText = new StringBuilder();

        for (List<Integer> binaryLetter: subBinary) {
            StringBuilder letter = new StringBuilder();

            for (int bite: binaryLetter) {
                letter.append(bite);
            }

            int letterIndex = Integer.parseInt(letter.toString(), 2);

            if (letterIndex > 0) {
                sourceText.append(getLetterByIndex(letterIndex));
            }
        }

        return sourceText.toString();
    }

    private static char getLetterByIndex(int index) {
        if (index >= 1 && index <= 26) {
            return (char) ('a' + index - 1);
        }

        return '\0';
    }

    public static List<Integer> encodeText(String text) {
        StringBuilder binaryString = new StringBuilder();

        for (char letter : text.toCharArray()) {
            int letterIndex = getLetterIndex(letter);
            String binaryRepresentation = "0".repeat(5 - Integer.toBinaryString(letterIndex).length()) +
                    Integer.toBinaryString(letterIndex);

            binaryString.append(binaryRepresentation);
        }

        return convertToBinaryVector(
                "0".repeat(100 - binaryString.length()) + binaryString.toString());
    }

    private static int getLetterIndex(char letter) {
        if (letter >= 'a' && letter <= 'z') {
            return letter - 'a' + 1;
        }

        throw new IllegalArgumentException("Недопустимый символ: " + letter);
    }

    public static List<Integer> convertToBinaryVector(String binaryString) {
        List<Integer> vector = new ArrayList<>();

        for (int i = 0; i < binaryString.length(); i++) {
            vector.add(binaryString.charAt(i) == '1' ? 1 : 0);
        }

        return vector;
    }
}