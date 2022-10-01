package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        Matrix matrix;
        try{
            matrix = new Matrix(readFile("/home/skalem/IdeaProjects/JavaLabs/src/lab3/matrix.txt"));
        }
        catch (NotValidMatrixException | NumberFormatException | FileNotFoundException e){
            System.err.println(e.getMessage());
            return;
        }
        Matrix reversed = reverse(matrix);
        writeMatrix(reversed);

    }

    private static int[][] readFile(String path) throws FileNotFoundException {
        File textFile = new File(path);
        Scanner scanner = new Scanner(textFile);

        ArrayList<String> lines = new ArrayList<>();
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }

        int[][] intLines = new int[lines.size()][];
        for (int i = 0; i < lines.size(); i++){
            intLines[i] = getIntLine(lines.get(i));
        }

        return intLines;
    }

    private static int[] getIntLine(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

        int[] intLine = new int[tokenizer.countTokens()];
        int i = 0;

        while(tokenizer.hasMoreElements()){
            String current = tokenizer.nextToken();
            intLine[i] = Integer.parseInt(current);
            i++;
        }
        return intLine;
    }

    public static void getSimpleMatrix(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if (i == i1){
                    matrix[i][i1] = 1;
                }
                else {
                    matrix[i][i1] = 0;
                }
            }
        }
    }

    public static Matrix reverse(Matrix matrix){
        int[][] attachedMatrixArr = new int[matrix.getMatrix().length][matrix.getMatrix().length];
        getSimpleMatrix(attachedMatrixArr);
        Matrix attachedMatrix;
        try {
            attachedMatrix = new Matrix(attachedMatrixArr);
        } catch (NotValidMatrixException e) {
            throw new RuntimeException(e);
        }
        DoubleMatrix doubleMatrix = new DoubleMatrix(matrix, attachedMatrix);

        for (int i = 0; i < matrix.getMatrix().length - 1; i++) {
            matrix.divideLine(0, matrix.getMatrix()[i][i]);
            attachedMatrix.divideLine(0, matrix.getMatrix()[i][i]);
            for (int i1 = i + 1; i1 < matrix.getMatrix().length; i1++) {
                doubleMatrix.fullSubLines(i1, 0);
            }
            for (int i1 = i + 1; i1 < matrix.getMatrix().length; i1++) {
                doubleMatrix.fullSubRaws(i1, 0);
            }
        }

        return attachedMatrix;
    }

    private static void writeMatrix(Matrix matrix){
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int i1 = 0; i1 < matrix.getMatrix()[i].length; i1++) {
                System.out.print(matrix.getMatrix()[i][i1] + " ");
            }
            System.out.println();
        }
    }
}
