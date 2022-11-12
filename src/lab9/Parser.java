package lab9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Parser {
    public static ArrayList<Student> readFromFile(String path) throws FileNotFoundException {
        Scanner reader = new Scanner(new File(path));
        reader.useDelimiter("\n");

        ArrayList<Student> students = new ArrayList<>();
        while (reader.hasNext()){
            Scanner lineScanner = new Scanner(reader.next());
            lineScanner.useDelimiter(" ");
            students.add(new Student(lineScanner.nextInt(), lineScanner.nextLine(), lineScanner.nextInt(), lineScanner.nextInt()));
        }
        return students;
    }
}
