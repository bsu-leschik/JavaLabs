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

    private boolean isMatrixValid() throws NotValidMatrixException{
        if (matrix == null || matrix.length == 1 || matrix.length == 0){
            return false;
        }

        int length = matrix.length;

        for (double[] ints : matrix) {
            if (ints.length < length) {
                throw new NotValidMatrixException("Line is too small");
            }
            else if(ints.length > length){
                throw new NotValidMatrixException("Line is too big");
            }
        }
        return isActuallySquare();
    }

    private boolean isActuallySquare(){
        int countL = 0, countR = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix.length; i1++) {
                if(matrix[i][i1] == 0){
                    countL++;
                }
                if(matrix[i1][i] == 0){
                    countR++;
                }
            }
            if (countL == matrix.length || countR == matrix.length){
                return false;
            }
            countR = 0;
            countL = 0;
        }
        return true;
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

    public void swapRows(int row1, int row2){
        double[] row1Ints = new double[this.matrix.length];
        double[] row2Ints = new double[this.matrix.length];
        for (int i = 0; i < this.matrix.length; i++) {
            row1Ints[i] = matrix[i][row1];
            row2Ints[i] = matrix[i][row2];
        }
        for (int i = 0; i < this.matrix.length; i++) {
            matrix[i][row1] = row2Ints[i];
            matrix[i][row2] = row1Ints[i];
        }
    }

    public void swapLines(int line1Index, int line2Index){
        double [] line1 = matrix[line1Index];
        matrix[line1Index] = matrix[line2Index];
        matrix[line2Index] = line1;
    }
}
