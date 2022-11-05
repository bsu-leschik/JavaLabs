package kontr;

public class Guard extends Employee{

    double securedArea;

    final double CONSTANT_BASE = 1;

    public Guard(String surname, String organization, double coefficient, double securedArea){
        super(surname, organization, coefficient);

        if (securedArea <= 0){
            throw new IllegalArgumentException("Secured area cannot bo less or equals 0");
        }

        this.securedArea = securedArea;
    }

    @Override
    public int getSalary() {
        return (int)(coefficient * securedArea * CONSTANT_BASE);
    }

    @Override
    public String toString() {
        return "Guard{" +
                "securedArea=" + securedArea +
                ", CONSTANT_BASE=" + CONSTANT_BASE +
                super.toString() +
                '}';
    }
}
