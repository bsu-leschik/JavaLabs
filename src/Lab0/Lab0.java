package Lab0;

import java.util.Scanner;

public class Lab0 {

    public static void mainLab0(){
        double[] parameters;
        parameters = getParametersFromConsole();


        double result = calculateSeries(parameters[0], parameters[1]);
        System.out.println("The result is: " + result);
    }

    private static double[] getParametersFromConsole(){
        double x, error;

        System.out.print("Enter 2 values, number is the first parameter, error the second:");
        Scanner reader= new Scanner(System.in);


        x = reader.nextDouble();
        error = reader.nextDouble();

        System.out.println(x + " " + error);

        return new double[] {x, error};
    }

    private static double calculateSeries(double x, double error){
        int k = 2;
        double current = x * x / 2, result = current;
        System.out.println(current);

        while (current > error){
            current *= (x * x) / (2 * k);
            System.out.println(current);
            result += current;
            k++;
        }

        return result;
    }
}