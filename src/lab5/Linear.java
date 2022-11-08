package lab5;

public class Linear extends Series {
    public Linear(int n, double firstElement, double delta) {
        super(n, firstElement, delta);
    }

    @Override
    public double calculateElement(int i) {
        if (i < 0){
            throw new IllegalArgumentException("i cannot be equal or less then 0");
        }
        double element = firstElement;
        for (int j = 0; j < i; j++) {
            element += delta;
        }
        return element;
    }
}
