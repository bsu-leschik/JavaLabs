package Lab0;

import java.util.Scanner;

public class Lab0 {

    public static void main(String[] args){
        double[] parameters;
        parameters = getParametersFromConsole();


        double result = calculateSeries(parameters[0], parameters[1]);
        System.out.println("The result is: " + result);
    }

    private static double[] getParametersFromConsole(){
        double x, error;

        System.out.print("Enter 2 values, number is the first parameter, error the second:");

        Scanner reader = new Scanner(System.in);
        reader = new Scanner(reader.nextLine());

        x = reader.nextDouble();
        error = reader.nextDouble();

        if(reader.hasNext()){
            throw new IllegalArgumentException("You need to enter 2 parameters!");
        }

        return new double[] {x, error};
    }

    private static double calculateSeries(double x, double error){
        int k = 2;
        double current = x * x / 2, result = current;
        System.out.println(current);

        while (current > error){
            current *= (x * x) / (2 * k);
            result += current;
            k++;
        }

        return result;
    }
}