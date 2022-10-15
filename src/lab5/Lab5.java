package lab5;

import java.io.File;
import java.io.IOException;

public class Lab5 {

    public static void main(String[] args){
        Series s;
        try{
            if (args.length < 5){
                throw new IllegalArgumentException("Too small amount of arguments");
            }

            int n = Integer.parseInt(args[0]);
            double firstElement = Double.parseDouble(args[1]);
            double delta = Double.parseDouble(args[2]);
            String path = args[3];

            if (args[4].equalsIgnoreCase("e")){
                s = new Exponential(n, firstElement, delta);
                tests(s, path);
                return;
            }

            if (args[4].equalsIgnoreCase("l")) {
                s = new Linear(n, firstElement, delta);
                tests(s, path);
                return;
            }
            throw new IllegalArgumentException("enter e for exponential or l for linear series as 4th argument");
        }
        catch(IllegalArgumentException | IOException e){
            System.err.println(e.getMessage());
        }
    }

    private static void tests(Series s, String path) throws IOException {
        System.out.println("Elements: " + s);
        System.out.println("Sum: " + s.calculateSum());
        s.saveToFile(new File(path));
        System.out.println("Successfully written file");
    }
}
