import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> matrixA = new ArrayList<>();
        matrixA.add(new ArrayList<>());
        matrixA.add(new ArrayList<>());
        matrixA.add(new ArrayList<>());
        matrixA.add(new ArrayList<>());
        matrixA.get(0).add(1);
        matrixA.get(1).add(1);
        matrixA.get(2).add(1);
        matrixA.get(3).add(-1);

        List<List<Integer>> matrixB = new ArrayList<>();
        matrixB.add(new ArrayList<>());
        matrixB.get(0).add(-1);
        matrixB.get(0).add(1);
        matrixB.get(0).add(-1);
        matrixB.get(0).add(-1);

//        print(matrixA, "*");
//        print(matrixB, "");
//        System.out.println("=");
//        print(multiply(matrixA, matrixB), "");
//        System.out.println("\n\n\n\n\n\n\n");

        List<List<Integer>> matrixC = fillZeroMatrix(4, 4);

        print(matrixC, "\n\n\n");

        for (int i = 0; i < 3; i++) {
            print(multiply(matrixA, matrixB), "");
            matrixC = sum(matrixC, multiply(matrixA, matrixB));
            print(matrixC, "\n\n\n");
        }

       // print(matrixC, "");
    }
    public static List<List<Integer>> sum(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < matrixA.size(); i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < matrixA.get(0).size(); j++) {
                outputMatrix.get(i).add(matrixA.get(i).get(j) + matrixB.get(i).get(j));
            }
        }

        return outputMatrix;
    }

    public static List<List<Integer>> multiply(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = fillZeroMatrix(matrixA.size(), matrixB.get(0).size());

        for (int i = 0; i < matrixA.size(); i++) {
            for (int j = 0; j < matrixB.get(0).size(); j++) {
                for (int k = 0; k < matrixA.get(0).size(); k++) {
                    outputMatrix.get(i).set(j, outputMatrix.get(i).get(j) + matrixA.get(i).get(k) *
                            matrixB.get(k).get(j));
                }
            }
        }

        return outputMatrix;
    }

    public static List<List<Integer>> fillZeroMatrix(int rows, int cols) {
        List<List<Integer>> outputMatrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            outputMatrix.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                outputMatrix.get(i).add(0);
            }
        }

        return outputMatrix;
    }

    public static List<List<Integer>> transpose(List<List<Integer>> matrix) {
        List<List<Integer>> transposed = new ArrayList<>();

        for (int i = 0; i < matrix.get(0).size(); i++) {
            List<Integer> newRow = new ArrayList<>();

            for (int j = 0; j < matrix.size(); j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposed.add(newRow);
        }

        return transposed;
    }

    public static List<List<Integer>> multiplyWithActivation(List<List<Integer>> matrixA, List<List<Integer>> matrixB) {
        List<List<Integer>> outputMatrix = fillZeroMatrix(matrixA.size(), matrixB.get(0).size());

        for (int i = 0; i < matrixA.size(); i++) {
            for (int j = 0; j < matrixB.get(0).size(); j++) {
                for (int k = 0; k < matrixA.get(0).size(); k++) {
                    outputMatrix.get(i).set(j, outputMatrix.get(i).get(j) + matrixA.get(i).get(k) *
                            matrixB.get(k).get(j));
                }
                outputMatrix.get(i).set(j, activation(outputMatrix.get(i).get(j)));
            }
        }

        return outputMatrix;
    }

    public static int activation(int weightedSum) {
        //System.out.println("weight: " + weightedSum);
        if (weightedSum > 0) {
            return 1;
        } else if (weightedSum < 0) {
            return -1;
        }

        return 0;
    }

    public static void print(List<List<Integer>> matrix, String sign) {
        for (List<Integer> row: matrix) {
            for (int number: row) {
                System.out.print(number + " ");
            }

            System.out.println();
        }
        System.out.println("\n" + sign + "\n");
    }
}