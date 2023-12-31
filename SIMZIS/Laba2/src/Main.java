import java.util.*;
import java.io.*;

public class Main {
    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static final Map<Integer, Integer> REVERSE_INDEX = new HashMap<>(){
        {
            put(-1, 25);
            put(-2, 24);
            put(-3, 23);
            put(-4, 22);
            put(-5, 21);
            put(-6, 20);
            put(-7, 19);
            put(-8, 18);
            put(-9, 17);
            put(-10, 16);
            put(-11, 15);
            put(-12, 14);
            put(-13, 13);
            put(-14, 12);
            put(-15, 11);
            put(-16, 10);
            put(-17, 9);
            put(-18, 8);
            put(-19, 7);
            put(-20, 6);
            put(-21, 5);
            put(-22, 4);
            put(-23, 3);
            put(-24, 2);
            put(-25, 1);
            put(-26, 0);
        }
    };
    public static final int SIZE = 26;

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {
        FileReader file = new FileReader("input.txt");
        BufferedReader br = new BufferedReader(file);
        String word = br.readLine();
        System.out.println("Исходная строка: " + word);
        int k = Integer.parseInt(br.readLine());
        System.out.println("Ключ: " + k);
        String shift = br.readLine();
        System.out.print("Сдвиг: ");
        if (shift.equals("R"))
            System.out.println("вправо");
        else
            System.out.println("влево");
        System.out.println("Алфавит: " + ALPHABET);
        StringBuilder cipher = shiftFun(word, k, shift);
        System.out.println("Зашифрованная строка: " + cipher);
        StringBuilder source = decoding(cipher, k, shift);
        System.out.println("Расшифрованная строка: " + source);
        String source1 = attack(source);
        StringBuilder cipher1 = hardCipher(word, k, shift);
        System.out.println("Усложнённый шифр: " + cipher1);
    }

    public static StringBuilder shiftFun(String word, int k, String shift) {
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
            for (int j = 0; j < SIZE; j++)
                if (word.charAt(i) == ALPHABET.charAt(j)) {
                    if (shift.equals("R"))
                        cipher.append(ALPHABET.charAt((j + k) % SIZE));
                    else
                        cipher.append(ALPHABET.charAt((j - k + SIZE) % SIZE));
                }
        return cipher;
    }

    public static StringBuilder decoding(StringBuilder cipher, int k, String shift){
        StringBuilder source = new StringBuilder();
        for (int i = 0; i < cipher.length(); i++)
            for (int j = 0; j < SIZE; j++)
                if (cipher.charAt(i) == ALPHABET.charAt(j)) {
                    if (shift.equals("R"))
                        source.append(ALPHABET.charAt((j - k + SIZE) % SIZE));
                    else
                        source.append(ALPHABET.charAt((j + k + SIZE) % SIZE));
                }
        return source;
    }

    public static String attack(StringBuilder source){
        boolean key = true;
        String str1 = "";
        while (key) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                Random random = new Random();
                str.append(ALPHABET.charAt(random.nextInt(26)));
            }
            if (str.toString().contentEquals(source)) {
                str1 = str.toString();
                System.out.println("Сгенерированная строка: " + str);
                key = false;
            }
        }
        return str1;
    }

    public static StringBuilder hardCipher(String word, int k, String shift){
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
            for (int j = 0; j < SIZE; j++)
                if (word.charAt(i) == ALPHABET.charAt(j)) {
                    if (shift.equals("R"))
                        cipher.append(ALPHABET.charAt(((j + k) % SIZE) / word.length()));
                    else
                        cipher.append(ALPHABET.charAt(((j - k + SIZE) % SIZE) / word.length()));
                }
        return cipher;
    }
}