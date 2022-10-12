package lab5;

import java.io.File;
import java.io.IOException;

public class Lab5 {

    public static void main(String[] args){
        Series s;
        try{
            s = new Linear(20, 2, 2);
            tests(s);

            s = new Exponential(20, 2, 2);
            tests(s);
        }
        catch(IllegalArgumentException | IOException e){
            System.err.println(e.getMessage());
        }
    }

    private static void tests(Series s) throws IOException {
        System.out.println(s);
        System.out.println(s.calculateSum());
        s.saveToFile(new File("/home/skalem/Дакументы/JavaLabs/linear.txt"));
    }
}
