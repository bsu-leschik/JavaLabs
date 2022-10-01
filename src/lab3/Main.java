package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        Matrix reversed;
        try{

            //../../../../../../../home/skalem/IdeaProjects/JavaLabs/src/lab3/matrix.txt
            Matrix matrix = new Matrix(readFile("/home/skalem/IdeaProjects/JavaLabs/src/lab3/matrix.txt"));
            reversed = reverse(matrix);
        }
        catch (NotValidMatrixException | NumberFormatException | FileNotFoundException e){
            System.err.println(e.getMessage());
            return;
        }
        writeMatrix(reversed);

    }

    private static double[][] readFile(String path) throws FileNotFoundException {
        File textFile = new File(path);
        Scanner scanner = new Scanner(textFile);

        ArrayList<String> lines = new ArrayList<>();
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }

        double[][] intLines = new double[lines.size()][];
        for (int i = 0; i < lines.size(); i++){
            intLines[i] = getDoubleLine(lines.get(i));
        }

        return intLines;
    }

    private static double[] getDoubleLine(String line){
        StringTokenizer tokenizer = new StringTokenizer(line, " ", false);

        double[] doubleLine = new double[tokenizer.countTokens()];
        int i = 0;

        while(tokenizer.hasMoreElements()){
            String current = tokenizer.nextToken();
            doubleLine[i] = Double.parseDouble(current);
            i++;
        }
        return doubleLine;
    }

    public static void getSimpleMatrix(double[][] matrix){
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

    public static Matrix reverse(Matrix matrix) throws  NotValidMatrixException{
        double[][] attachedMatrixArr = new double[matrix.getMatrix().length][matrix.getMatrix().length];
        getSimpleMatrix(attachedMatrixArr);
        Matrix attachedMatrix;
        try {
            attachedMatrix = new Matrix(attachedMatrixArr);
        } catch (NotValidMatrixException e) {
            throw new RuntimeException(e);
        }
        DoubleMatrix doubleMatrix = new DoubleMatrix(matrix, attachedMatrix);

        for (int i = 0; i < matrix.getMatrix().length - 1; i++) {
            if (matrix.getMatrix()[i][i] == 0){
                removeZeroFromStart(matrix, i);
            }
            doubleMatrix.divideLine(i, matrix.getMatrix()[i][i]);
            for (int i1 = i + 1; i1 < matrix.getMatrix().length; i1++) {
                doubleMatrix.fullSubLines(i1, i);
            }
            for (int i1 = i + 1; i1 < matrix.getMatrix().length; i1++) {
                doubleMatrix.fullSubRaws(i1, i);
            }
        }

        if (!isSimpleLinearDependant(matrix)){
            throw new NotValidMatrixException("Matrix is linear dependant");
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

    private static boolean isSimpleLinearDependant(Matrix matrix){
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            if (matrix.getMatrix()[i][i] == 0){
                return false;
            }
        }
        return true;
    }

    private static void removeZeroFromStart(Matrix matrix, int currentLine) throws NotValidMatrixException{
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            if (matrix.getMatrix()[currentLine][i] != 0){
                matrix.swapRows(i, currentLine);
                return;
            }
        }
        throw new NotValidMatrixException("Matrix is linear");

    }
}
