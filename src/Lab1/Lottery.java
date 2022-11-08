package Lab1;

import java.util.ArrayList;

public class Lottery {

    public static void main(String[] args){
        var ticket1 = generateTicketArray();

        for (int i : ticket1) {
            System.out.print(i + " ");
        }

        System.out.println();
        var ticket2 = generateTicketArrayList();

        for (int i : ticket2) {
            System.out.print(i + " ");
        }
    }

    private static int generateIndex(int max){
        return (int) (Math.random() * max);
    }

    public static int[] generateTicketArray(){
        var numbers = new ArrayList<Integer>();
        for (int i = 1; i < 50; i++){
            numbers.add(i);
        }

        var results = new int[6];
        for (var i = 0; i < 6; i++){
            var j = generateIndex(numbers.size());
            results[i] = numbers.remove(j);
        }

        return results;
    }

    public static ArrayList<Integer> generateTicketArrayList(){
        var numbers = new ArrayList<Integer>();
        for (int i = 1; i < 50; i++){
            numbers.add(i);
        }

        var results = new ArrayList<Integer>();
        for (var i = 0; i < 6; i++){
            var j = generateIndex(numbers.size());
            results.add(numbers.remove(j));
        }

        return results;
    }
}
