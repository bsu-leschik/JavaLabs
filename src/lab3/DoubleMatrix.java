package lab3;

public class DoubleMatrix {
    Matrix matrix1;
    Matrix matrix2;

    DoubleMatrix(Matrix matrix1, Matrix matrix2){
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }

    public void fullSubLines(int line1, int line2){
        double mult = matrix1.getMatrix()[line1][line2];
        matrix1.fullSubLines(line1, line2, mult);
        matrix2.fullSubLines(line1, line2, mult);
    }

    public void fullSubRaws(int raw1, int raw2){
        double mult = matrix1.getMatrix()[raw2][raw1];
        matrix1.fullSubRows(raw1, raw2, mult);
    }

    public void divideLine(int line, double number){
        matrix1.divideLine(line, number);
        matrix2.divideLine(line, number);
    }

    public void swapLines(int line1, int line2){
        matrix1.swapLines(line1, line2);
        matrix2.swapLines(line1, line2);
    }

    public int getHeight(){
        return matrix1.getMatrix().length;
    }

    public int getLength(){
        return 2*matrix1.getMatrix().length;
    }
}
