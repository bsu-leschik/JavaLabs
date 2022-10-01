package lab3;

public class Matrix {
    private int[][] matrix;
    Matrix(int[][] matrix) throws NotValidMatrixException{
        this.matrix = matrix;
        if(!isMatrixValid()){
            this.matrix = null;
            throw new NotValidMatrixException("Matrix is invalid");
        }
    }

    private boolean isMatrixValid(){
        int length = matrix.length;
        for (int[] ints : matrix) {
            if (ints.length != length) {
                return false;
            }
        }
        return matrix.length != 0;
    }

    public void divideLine(int line, int number){
        for (int i = 0; i < matrix[line].length; i++) {
            matrix[line][i] /= number;
        }
    }

    public void divideRow(int row, int number){
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][row] /= number;
        }
    }

    public void fullSubLines(int line1, int line2, int mult){
        for (int i = 0; i < matrix.length; i++) {
            matrix[line1][i] -= mult*matrix[line2][i];
        }
    }

    public void fullSubRows(int row1, int row2, int mult){
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][row1] -= mult*matrix[i][row2];
        }
    }
    public int[][] getMatrix(){
        return this.matrix;
    }


}
