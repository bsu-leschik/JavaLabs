package kontr;

public class ShopAssistant extends Employee{

    double money;

    final double CONSTANT_PERCENT = 1;

    public ShopAssistant(String surname, String organization, double coefficient, double money){
        super(surname, organization, coefficient);
        if (money <= 0){
            throw new IllegalArgumentException("Money cannot bo less or equals 0");
        }
        this.money = money;
    }
    @Override
    public int getSalary() {
        return (int)(coefficient * money * CONSTANT_PERCENT);
    }

    @Override
    public String toString() {
        return "ShopAssistant{" +
                "money=" + money +
                ", CONSTANT_PERCENT=" + CONSTANT_PERCENT +
                super.toString() +
                '}';
    }

    @Override
    public String[] toStringArray() {
        return new String[]{this.surname, this.organization, String.valueOf(this.coefficient), String.valueOf(this.money)};

    }
}
