import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final int SIZE = 2;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable(new ArrayList<>());
        hashTable.setCells(hashTable.createHashTable());
        hashTable.showTable();
        for (int i = 0; i < SIZE; i++){
            hashTable.setCells(hashTable.addValue());
        }
        hashTable.showTable();
        System.out.println("Key:");
        String key = scanner.nextLine();
        hashTable.deleteValue(key);
        hashTable.showTable();
        System.out.println("Key:");
        key = scanner.nextLine();
        hashTable.getInfo(key);
        hashTable.showTable();
    }
}