package kontr;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser {

    public static class TooMuchTokensException extends Exception{
        TooMuchTokensException(){
            super("There are to much tokens in file");
        }
    }

    public static class NotEnoughTokensException extends Exception{
        NotEnoughTokensException(){
            super("There are not enough tokens in file");
        }
    }

    public static ArrayList<Guard> parseGuardFile(File file) throws FileNotFoundException, NumberFormatException,
            NoSuchElementException, TooMuchTokensException, NotEnoughTokensException {
        Scanner scanner = new Scanner(file);
        ArrayList<Guard> guards = new ArrayList<>();
        while (scanner.hasNext()) {
            StringTokenizer current = new StringTokenizer(scanner.nextLine(), " ");
            if (!current.hasMoreTokens()){
                throw new NotEnoughTokensException();
            }
            guards.add(new Guard(current.nextToken(), current.nextToken(), Double.parseDouble(current.nextToken()),
                    Double.parseDouble(current.nextToken())));
            if (current.hasMoreTokens()){
                throw new TooMuchTokensException();
            }
        }
        return guards;
    }

    public static ArrayList<ShopAssistant> parseShopAssistantFile(File file) throws FileNotFoundException, NumberFormatException,
            NoSuchElementException, TooMuchTokensException, NotEnoughTokensException {
        Scanner scanner = new Scanner(file);
        ArrayList<ShopAssistant> shopAssistants = new ArrayList<>();
        while (scanner.hasNext()) {
            StringTokenizer current = new StringTokenizer(scanner.nextLine(), " ");
            if (!current.hasMoreTokens()){
                throw new NotEnoughTokensException();
            }
            shopAssistants.add(new ShopAssistant(current.nextToken(), current.nextToken(), Double.parseDouble(current.nextToken()),
                    Double.parseDouble(current.nextToken())));
            if (current.hasMoreTokens()){
                throw new TooMuchTokensException();
            }
        }
        return shopAssistants;
    }


}
