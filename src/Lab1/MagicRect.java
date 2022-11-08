package Lab1;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MagicRect {
    public static void main(String[] args){
        int[][] matrix = getMatrix();
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int[][] getMatrix(){
        Scanner sc = new Scanner(System.in);

        var lines = new ArrayList<String>();

        System.out.println("Enter matrix:");
        var currentLine = sc.nextLine();

        while (!Objects.equals(currentLine, "")){
            lines.add(currentLine);
            currentLine = sc.nextLine();
        }
        return convertMatrixToInt(lines);
    }


    private static int[][] convertMatrixToInt(ArrayList<String> lines){

        var stringMatrix = new String[lines.size()][];

        for (var i = 0; i < lines.size(); i++) {
            String[] intLine = lines.get(i).split(" ");
            stringMatrix[i] = intLine;
        }



        return stringsToInts(stringMatrix);
    }

    private static int[][] stringsToInts(String[][] strings){
        int[][] ints = new int[strings.length][];

        for (var j = 0; j < strings.length; j++) {
            ints[j] = new int[strings[j].length];
            for (var i = 0; i < strings[j].length; i++) {
                ints[j][i] = Integer.parseInt(strings[j][i]);
            }
        }
        return ints;
    }

    private static boolean isMagicCube(int[][] matrix){
//        boolean result = checkLines(matrix);
//
//        if (!result){
//            return false;
//        }
//
//        matrix = turnMatrix(matrix);
//        result = checkLines(matrix);
//
//        if (!result){
//            return false;
//        }
    return false;

    }

    public static boolean checkDiagonals(int[][] matrix){
        int sum;
        for (int i = 0; i < matrix.length; i++) {

        }
        return false;
    }

    private static int checkLines(int[][] matrix){
        int add = 0;
        boolean first = true;

        for (int[] ints : matrix) {
            int newAdd = 0;
            for (int anInt : ints) {
                newAdd += anInt;
            }

            if (first){
                first = false;
                add = newAdd;
                continue;
            }

            if (newAdd != add){
                return 0;
            }
        }
        return add;
    }
    private static int[][] turnMatrix(int[][] matrix){
        int[][] turnedMatrix = new int[matrix.length][matrix.length];

        for (var i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                turnedMatrix[j][i] = matrix[i][j];
            }
        }
        return turnedMatrix;
    }

    private static boolean areRowsEqual(int[][] matrix){
        int add = 0;
        boolean first = true;

        for (int[] ints : matrix) {
            int newAdd = 0;
            for (int anInt : ints) {
                newAdd += anInt;
            }

            if (first){
                first = false;
                add = newAdd;
                continue;
            }

            if (newAdd != add){
                return false;
            }
        }
        return true;
    }
}
