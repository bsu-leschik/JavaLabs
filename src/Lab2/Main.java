package Lab2;


import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args){
        String line;
        try{
            line = getLine(args);
        }
        catch(NoArgumentException exception){
            System.err.println(exception.getMessage());
            return;
        }

        int x;
        try{
            x = getX(args);
        }
        catch(NoArgumentException exception){
            System.err.println(exception.getMessage());
            return;
        }
        catch (NumberFormatException e){
            System.err.println("X is not a number");
            return;
        }

        StringTokenizer parsedLine = new StringTokenizer(line, "/", false);
        double result = 0;
        try {
            result = getResult(parsedLine, x);
        } catch (NoArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.println("Result is: " + result);
    }

    private static String getLine(String[] args) throws NoArgumentException{
        if (args.length < 1){
            throw new NoArgumentException("No data was entered");
        }

        return args[0];
    }

    private static int getX(String[] args) throws NoArgumentException{
        if (args.length < 2){
            throw new NoArgumentException("No x value was entered");
        }

        return Integer.parseInt(args[1]);
    }

    private static double getResult(StringTokenizer parsedLine, double xVal) throws NoArgumentException{
        int length = parsedLine.countTokens();

        if (length == 0){
            throw new NoArgumentException("String must not be empty");
        }

        String x = "x";
        double result;

        if (Objects.equals(parsedLine.nextToken(), "x")){
            result = xVal;
        }
        else {
            result = Integer.parseInt(parsedLine.nextToken());
        }

        for (int i = 0;i < length - 1; i++){
            String token = parsedLine.nextToken();
            if (token.compareTo(x) == 0){
                result /= xVal;
            }
            else {
                result /= Integer.parseInt(token.toString());
            }
        }

        return result;
    }
}

