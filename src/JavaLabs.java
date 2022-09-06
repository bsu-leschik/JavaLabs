import Lab0.Lab0;

import java.util.Scanner;

public class JavaLabs {

    public static void main(String[] args){

        do {
            String labN = getLabNumber();
            runLabByNumber(labN);
        }
        while (toContinue());

    }

    private static String getLabNumber(){
        System.out.print("Enter the number of lab you want to start(0-0):");

        String labN;

        Scanner reader = new Scanner(System.in);
        labN = reader.nextLine();

        return labN;
    }

    private static void runLabByNumber(String labN){
        switch (labN){
            case "0":
                Lab0.mainLab0();
                break;
            default: System.out.println("No lab of this number was found");
        }
    }

    private static boolean toContinue(){
        System.out.println("Do you want to run another lab?(write y for yes or anything for no)");

        Scanner reader = new Scanner(System.in);
        String text;

        text = reader.nextLine();
        return text.equals("y");
    }
}
