package lab4;

public class Number implements Comparable<Number> {

    private final int number;

    Number(int number){
        this.number = number;
    }

    @Override
    public int compareTo(Number o) {
        if (this.number < o.number){
            return -1;
        }
        else if (this.number > o.number){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString(){
        return String.valueOf(number);
    }
}
