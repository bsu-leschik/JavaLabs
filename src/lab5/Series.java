package lab5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {

    protected int n;
    protected double firstElement;
    double delta;

    public Series(int n, double firstElement, double delta){
        if(n <= 0){
            throw new IllegalArgumentException("Size cannot be equal or less then zero");
        }
        this.n = n;
        this.firstElement = firstElement;
        this.delta = delta;
    }

    public abstract double calculateElement(int i);

    public double calculateSum() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += calculateElement(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(calculateElement(i));
            sb.append(" ");
        }
        return sb.toString();
    }
    public void saveToFile(File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        writer.write(this.toString());
        writer.close();
    }
}
