package lab3;

public class Matrix {
    private double[][] matrix;
    Matrix(double[][] matrix) throws NotValidMatrixException{
        this.matrix = matrix;
        if(!isMatrixValid()){
            this.matrix = null;
            throw new NotValidMatrixException("Matrix is invalid");
        }
    }

    private boolean isMatrixValid(){
        int length = matrix.length;
        for (double[] ints : matrix) {
            if (ints.length != length) {
                return false;
            }
        }
        return matrix.length != 0;
    }

    public void divideLine(int line, double number){
        for (int i = 0; i < matrix[line].length; i++) {
            matrix[line][i] /= number;
        }
    }

    public void divideRow(int row, double number){
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][row] /= number;
        }
    }

    public void fullSubLines(int line1, int line2, double mult){
        for (int i = 0; i < matrix.length; i++) {
            matrix[line1][i] -= mult*matrix[line2][i];
        }
    }

    public void fullSubRows(int row1, int row2, double mult){
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][row1] -= mult*matrix[i][row2];
        }
    }
    public double[][] getMatrix(){
        return this.matrix;
    }



}
